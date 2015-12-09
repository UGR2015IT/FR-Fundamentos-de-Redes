//======================================================================//
//			YODAFY CLIENT TCP				//
//======================================================================//
import java.io.*;
import java.net.Socket;
import java.net.ServerSocket;
import java.net.UnknownHostException;
import java.util.Random;

public class Client {
	public static void main(String[] args, int argv) {
		String bufferSend,bufferReceive;
		int productID=0, productNumber = 0, port=8989;
		String host="localhost";
		Socket socketService=null;

		try {
			socketService = new Socket(host, port);
		} catch (IOException e) {
			System.err.println("Error de entrada/salida al abrir el socket.");
		}
		do {
			try {
				// Streams			
				InputStream inputStream = socketService.getInputStream();
				OutputStream outputStream = socketService.getOutputStream();
				
				// Ask for product info
				System.out.println("Enter -1 to exit.");
				System.out.println("Enter the product number (0-9) and how many there are in the inventory (divided by a space):");
				String input = System.console().readLine();
				String[] s = input.split(" ");
				productID = Integer.parseInt(s[0]);
				productNumber = Integer.parseInt(s[1]);
				bufferSend = productID+"_"+productNumber;

				// Send stream with product info
				PrintWriter outPrinter = new PrintWriter(outputStream, true);
				outPrinter.println(bufferSend);
				outPrinter.flush();

				
				// Read server's response
				System.out.println("Waiting for the query to be accepted ...");
				BufferedReader inReader = new BufferedReader(new InputStreamReader(inputStream));
				bufferReceive = inReader.readLine();
				System.out.println("> Message received by the server: ");
				System.out.println("> "+bufferReceive);
				System.out.println("> ");
				System.out.println("");
			} catch (UnknownHostException e) {
				System.err.println("Error: Nombre de host no encontrado.");
			} catch (IOException e) {
				System.err.println("Error de entrada/salida al abrir el socket.");
			}
		} while (productID != -1);

		System.out.println("Done sending product info to server. Waiting for summary...");

		// Close the socket
		try {
			socketService.close();			
		} catch (UnknownHostException e) {
			System.err.println("Error: Nombre de host no encontrado.");
		} catch (IOException e) {
			System.err.println("Error de entrada/salida al abrir el socket.");
		}
	}
}