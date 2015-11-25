import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

//
// YodafyServidorIterativo
// (CC) jjramos, 2012
//
public class YodafyServidorIterativo {

	public static void main(String[] args) {
	
		// Puerto de escucha
		int port=8989;
		// array de bytes auxiliar para recibir o enviar datos.
		byte []buffer=new byte[256];
		// Número de bytes leídos
		int bytesLeidos=0;

		DatagramSocket socketServidor;
		DatagramPacket paquete;
				
		try {
			socketServidor = new DatagramSocket (puerto);
			
			// Mientras ... siempre!
			do {
				paquete = new DatagramPacket(buffer, buffer.length);
				// Se recibe el paquete con el mensaje a yodificar.
				try {
					socketServidor.receive(paquete);
			    	} catch (IOException e) {
					System.err.println("Error de entrada/salida al abrir el socket.");
			    	}
				// Creamos un objeto de la clase ProcesadorYodafy, pasándole como 
				// argumento el nuevo socket, para que realice el procesamiento
				// Este esquema permite que se puedan usar hebras más fácilmente.
				ProcesadorYodafy procesador=new ProcesadorYodafy(socketServidor);
				procesador.procesa();
			} while (true);
			
		} catch (IOException e) {
			System.err.println("Error al escuchar en el puerto "+port);
		}

	}

}
