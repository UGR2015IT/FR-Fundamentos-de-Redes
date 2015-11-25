//
// YodafyServidorIterativo
// (CC) jjramos, 2012
//
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class YodafyClienteUDP {
    public static void main(String[] args) {
        byte[] buferEnvio = new byte[256];
        byte[] buferRecepcion = new byte[256];

        int port = 888;

        // Añadidos elementos necesarios para establecer la comunicación mediante datagramas.
        DatagramSocket socket = null;
        DatagramPacket paquete = null;
        DatagramPacket paqueteModificado = null;
        InetAddress direccion = null;

        String fraseYodificada;

        // Se crea el socket para la comunicación mediante datagramas
        try {
            socket = new DatagramSocket();
        } catch (IOException e) {
            System.err.println("Error de entrada/salida al abrir el socket.");
        }

        // Se obtiene la dirección con la que se quiere realizar la comunicación
        try {
            direccion = InetAddress.getByName("localhost");
        } catch (UnknownHostException e) {
            System.out.println("Error al recuperar la direccion.");
        }

        buferEnvio = "Hola caracola".getBytes();

        // Se envian un DatagramPacket con el mensaje original y se recibe uno con el mensaje yodificado
        try {
            paquete = new DatagramPacket(buferEnvio, buferEnvio.length, direccion, port);
            socket.send(paquete);

            paqueteModificado = new DatagramPacket(buferRecepcion, buferRecepcion.length);
            socket.receive(paqueteModificado);
        } catch (IOException e) {
            System.err.println("Error de entrada/salida al abrir el socket.");
        }

        fraseYodificada = new String(paqueteModificado.getData());

        System.out.println("\nRecibido: " + fraseYodificada + "\n");
        socket.close();
    }
}
