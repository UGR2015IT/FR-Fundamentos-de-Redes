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
		int port=8989;	
		try {
			ServerSocket socketClient = new ServerSocket(port);
			do {
				Socket socketServicio = socketClient.accept();
				ProcesadorYodafy procesador=new ProcesadorYodafy(socketServicio);
				//No se debe llamar ningun procedimiento porque se va a ejecutar concurrentemente por el metodo run()				
			} while (true);
			
		} catch (IOException e) {
			System.err.println("Error al escuchar en el puerto "+port);
		}

	}

}
