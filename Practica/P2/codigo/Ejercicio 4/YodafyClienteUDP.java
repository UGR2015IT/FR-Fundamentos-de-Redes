import java.io.IOException;
import java.net.UnknownHostException;
import java.net.DatagramSocket;
import java.net.DatagramPacket;
import java.net.InetAddress;

public class YodafyClienteUDP {
    public static void main(String[] args) {
        byte[] bufferSend = "Al monte del volcan debes ir sin demora".getBytes();
        byte[] bufferReceive = new byte[256];

        int port = 2888;

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

        // Se envian un DatagramPacket con el mensaje original y se recibe uno con el mensaje yodificado
        try {
            paquete = new DatagramPacket(bufferSend, bufferSend.length, direccion, port);
            socket.send(paquete);

            paqueteModificado = new DatagramPacket(bufferReceive, bufferReceive.length);
            socket.receive(paqueteModificado);
        } catch (IOException e) {
            System.err.println("Error de entrada/salida al abrir el socket.");
        }

        fraseYodificada = new String(paqueteModificado.getData());

        System.out.println("\nRecibido: " + fraseYodificada + "\n");
        socket.close();
    }
}
