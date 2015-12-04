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
import java.util.Random;

public class YodafyClienteTCP {
	public static void main(String[] args, int argv) {
		byte []bufferSend;
		byte []bufferReceive=new byte[256];
		int bytesRead=0, productID=0, productNumber = 0, clientID = 0, port=8989;
		boolean firstTime = true;
		String host="localhost";
		Socket socketServicio=null;

		do {
			if (firstTime) {
				// Generate a new ClientID Random!
				try {
					// New client for the server: add to the list of existing clients
					System.out.println("Establishing connection with remote server...");
					socketServicio = new Socket(host, port);
					// Open streams for communication
					InputStream inputStream = socketServicio.getInputStream();
					OutputStream outputStream = socketServicio.getOutputStream();
					bufferSend = ("I am client: "+clientID).getBytes();
					outputStream.write(bufferSend,0,bufferSend.length);
					outputStream.flush();
					// Sets first time variable to false
					firstTime = false;
				} catch (UnknownHostException e) {
					System.err.println("Error: unknown host.");
				} catch (IOException e) {
					System.err.println("Error: can't establish connection or socket I/O.");
				}
			} else {
				try {
					System.out.println("Connection established!");
					System.out.println("Enter the product number (1-9) and how many were sold (divided by a space):");
					String input = System.console().readLine();
					String[] s = input.split(" ");
					productID = Integer.parseInt(s[0]);
					productNumber = Integer.parseInt(s[1]);
					// Flujos:			
					InputStream inputStream = socketServicio.getInputStream();
					OutputStream outputStream = socketServicio.getOutputStream();
					bufferSend=(argv[0]+","+productID+","+productNumber).getBytes();
				} catch (UnknownHostException e) {
					System.err.println("Error: Nombre de host no encontrado.");
				} catch (IOException e) {
					System.err.println("Error de entrada/salida al abrir el socket.");
				}
			}
			try {
				// Enviamos el array por el outputStream;
				outputStream.write(bufferSend,0,bufferSend.length);
				outputStream.flush();
			} catch (UnknownHostException e) {
				System.err.println("Error: Nombre de host no encontrado.");
			} catch (IOException e) {
				System.err.println("Error de entrada/salida al abrir el socket.");
			}
		} while (productID != -1);

		// Leemos la respuesta del servidor. TO BE REDONE
		bytesRead = inputStream.read(bufferReceive);
		System.out.println("Message received by server: ");
		for(int i=0;i<bytesRead;i++){
			System.out.print((char)bufferReceive[i]);
		}
		System.out.println("");

		// Una vez terminado el servicio, cerramos el socket (automáticamente se cierran
		socketServicio.close();
	}
}
