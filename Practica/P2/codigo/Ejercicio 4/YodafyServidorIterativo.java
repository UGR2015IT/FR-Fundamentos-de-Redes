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
        int port = 888;

        // Añadidos elementos necesarios para establecer la comunicación mediante datagramas.
        DatagramSocket socketServidor = null;
        byte[] buferRecepcion = new byte[256];
        byte[] buferEnvio = new byte[256];
        DatagramPacket paquete = null;
        DatagramPacket paqueteModificado = null;
        InetAddress direccion;
        int puerto;

        String mensaje;

        // Se crea el socket para la comunicación mediante datagramas
        try {
            socketServidor = new DatagramSocket(port);
        } catch (IOException e) {
            System.err.println("Error de entrada/salida al abrir el socket.");
        }

        do {
            paquete = new DatagramPacket(buferRecepcion, buferRecepcion.length);

            // Se recibe el paquete con el mensaje a yodificar.
            try {
                socketServidor.receive(paquete);
            } catch (IOException e) {
                System.err.println("Error de entrada/salida al abrir el socket.");
            }

            // Se extraen todos los datos del paquete.
            mensaje = new String(paquete.getData());
            direccion = paquete.getAddress();
            puerto = paquete.getPort();

            // Incluimos la función de yodificar
            ////////////////////////////////////
            String[] s = mensaje.split(" ");
            String resultado = "";

            Random random = new Random();

            for (int i = 0; i < s.length; i++) {
                int j = random.nextInt(s.length);
                int k = random.nextInt(s.length);
                String tmp = s[j];

                s[j] = s[k];
                s[k] = tmp;
            }

            resultado = s[0];

            for (int i = 1; i < s.length; i++) {
                resultado += " " + s[i];
            }
            ////////////////////////////////////

            buferEnvio = resultado.getBytes();

            // Se crea un nuevo paquete con el mensaje yodificado
            paqueteModificado = new DatagramPacket(buferEnvio, buferEnvio.length, direccion, puerto);

            // Se envia el nuevo paquete con el mensaje yodificado incluido
            try {
                socketServidor.send(paqueteModificado);
            } catch (IOException e) {
                System.err.println("Error de entrada/salida al abrir el socket.");
            }
        } while (true);
    }
}
