import java.io.IOException;
import java.net.DatagramSocket;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.util.Random;

public class YodafyServidorIterativo {
	public static void main(String[] args) {
		int port = 2888;
		byte[] bufferReceive = new byte[256];
		try {
			DatagramSocket socketServidor = new DatagramSocket(port);
			DatagramPacket paquete = null;
			do {
				paquete = new DatagramPacket(bufferReceive, bufferReceive.length);
				socketServidor.receive(paquete);
				System.out.println("Received package");
				ProcesadorYodafy procesador = new ProcesadorYodafy(socketServidor);
			} while (true);
		} catch (IOException e) {
			System.err.println("Error de entrada/salida al abrir el socket.");
        	}
	}
}
