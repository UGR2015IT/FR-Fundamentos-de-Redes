// ==================================================== //
// 		Procesador YODAFY 			//
// ==================================================== //

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Random;

public class ProcesadorYodafy extends Thread{
	// Referencia a un socket para enviar/recibir las peticiones/respuestas
	private Socket socketServicio;
	private InputStream inputStream;
	private OutputStream outputStream;
	private Random random;	

	// Constructor
	public ProcesadorYodafy(Socket socketServicio) {
		this.socketServicio=socketServicio;
		random=new Random();
	}
	
	// Aquí es donde se realiza el procesamiento realmente:
	void procesa(){	
		String bufferReceive, respuesta;
		try {
			inputStream = socketServicio.getInputStream();
			outputStream = socketServicio.getOutputStream();

			// datosRecibidos se obtienen desde BufferedReader en vez desde InputStream
			BufferedReader inReader = new BufferedReader(new InputStreamReader(inputStream));
			bufferReceive = inReader.readLine();
			respuesta = yodaDo(bufferReceive);

			// respuesta se mandan por PrintWriter en vez por OutputStream
			PrintWriter outPrinter = new PrintWriter(outputStream, true);
			outPrinter.println(respuesta);			
		} catch (IOException e) {
			System.err.println("Error al obtener los flujos de entrada/salida.");
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

	public void run(){
		procesa();
	}
}
