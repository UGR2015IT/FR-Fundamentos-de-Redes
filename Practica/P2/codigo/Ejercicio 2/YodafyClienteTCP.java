//======================================================================//
//			YODAFY CLIENT TCP				//
//======================================================================//
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class YodafyClienteTCP {
	public static void main(String[] args) {		
		String bufferSend, bufferReceive;		
		// Nombre del host donde se ejecuta el servidor:
		String host="localhost";
		// Puerto en el que espera el servidor:
		int port=8989;
		// Socket para la conexión TCP
		Socket socketServicio=null;
		
		try {
			// Creamos un socket que se conecte a "hist" y "port":
			socketServicio = new Socket(host, port);
		} catch (UnknownHostException e) {
			System.err.println("Error: equipo desconocido.");
		} catch (IOException e) {
			System.err.println("Error: no se pudo establecer la conexion.");
		}
		try {		
			// Flujos:			
			InputStream inputStream = socketServicio.getInputStream();
			OutputStream outputStream = socketServicio.getOutputStream();			
			// Si queremos enviar una cadena de caracteres por un OutputStream, hay que pasarla primero a un array de bytes:
			bufferSend="Al monte del volcan debes ir sin demora";
			
			// buferEnvio se mandan por PrintWriter en vez por OutputStream
			PrintWriter outPrinter = new PrintWriter(outputStream, true);
			outPrinter.println(bufferSend);

			outPrinter.flush();

			// buferRecepcion se obtienen desde BufferedReader en vez desde InputStream
			BufferedReader inReader = new BufferedReader(new InputStreamReader(inputStream));
			bufferReceive = inReader.readLine();

			System.out.println("\nRecibido: " + bufferReceive + "\n");

			socketServicio.close();			
		} catch (UnknownHostException e) {
			System.err.println("Error: Nombre de host no encontrado.");
		} catch (IOException e) {
			System.err.println("Error de entrada/salida al abrir el socket.");
		}
	}
}
