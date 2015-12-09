import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	public static void main(String[] args) {
		int port = 8989;
		ServerSocket serverSocket = null;
		Socket socket = null;
		initializeDB();
		try {
			serverSocket = new ServerSocket(port);
		} catch (IOException e) {
			System.err.println("Error de entrada/salida al abrir el socket.");
		}
		while (true) {
			try {
				socket = serverSocket.accept();
			} catch (IOException e) {
				System.out.println("I/O error: " + e);
			}
			Processing procesador=new Processing(socket);
		}
	}

	static void initializeDB(){
		String fileName = "database.txt";
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
			for (int i=0;i<10;i++) writer.write(i+" 0");
			writer.close();         
		} catch(FileNotFoundException ex) {
			System.out.println("Unable to open file '" + fileName + "'");                
		} catch(IOException ex) {
			ex.printStackTrace();
		}
	}

}
