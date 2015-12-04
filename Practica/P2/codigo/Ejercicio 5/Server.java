import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class YodafyServidorIterativo {

	public static void main(String[] args) {
		// Puerto de escucha
		int port=8989;	
		try {
			// Abrimos el socket en modo pasivo, escuchando el en puerto indicado por "port"
			ServerSocket socketClient = new ServerSocket(port);
			do {
				// Aceptamos una nueva conexión con accept()
				Socket socketServicio = socketClient.accept();
				Processing procesador=new Processing(socketServicio);
				procesador.procesa();				
			} while (true);
			
		} catch (IOException e) {
			System.err.println("Error al escuchar en el puerto "+port);
		}

	}

}
