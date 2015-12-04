// ==================================================== //
// 		Procesador YODAFY 			//
// ==================================================== //

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.util.Random;

public class ProcesadorYodafy{
	// Referencia a un socket para enviar/recibir las peticiones/respuestas
	private DatagramSocket socketService;
	private DatagramPacket paquete, paqueteMod;
	private Random random;
	private byte[] bufferSend = new byte[256];	
	private int port;
	private InetAddress address;
	private String message;

	// Constructor
	public ProcesadorYodafy(DatagramSocket socketServicio) {
		System.out.println("Constructing");
		this.socketService=socketServicio;
		this.paquete=paquete;
		random=new Random();
	}
	
	void procesa(){
		try{
			// Extraer datos del paquete
			message = new String(paquete.getData());
			address = paquete.getAddress();
			port = paquete.getPort();
			//Yodafy
			bufferSend = (yodaDo(message)).getBytes();
			paqueteMod = new DatagramPacket(bufferSend, bufferSend.length, address, port);
			// Enviar el nuevo paquete
			socketService.send(paqueteMod);
		} catch (IOException e) {
			System.err.println("Error en enviar el paquete!");
		}

	}

	// Yoda interpreta una frase y la devuelve en su "dialecto":
	private String yodaDo(String peticion) {
		// Desordenamos las palabras:
		String[] s = peticion.split(" ");
		String resultado="";
		
		for(int i=0;i<s.length;i++){
			int j=random.nextInt(s.length);
			int k=random.nextInt(s.length);
			String tmp=s[j];
			
			s[j]=s[k];
			s[k]=tmp;
		}
		
		resultado=s[0];
		for(int i=1;i<s.length;i++){
		  resultado+=" "+s[i];
		}
		
		return resultado;
	}
}
