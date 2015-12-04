// ==================================================== //
// 			Processing 			//
// ==================================================== //

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Random;

public class Processing{
	private Socket socketServicio;
	private InputStream inputStream;
	private OutputStream outputStream;

	public ProcesadorYodafy(Socket socketServicio) {
		this.socketServicio=socketServicio;
	}
	
	int[] procesa(){	
		// Como máximo leeremos un bloque de 99 bytes (max 33 clientes). Esto se puede modificar.
		byte [] datosRecibidos = new byte[99];
		try {
			// Obtiene los flujos de escritura/lectura
			inputStream=socketServicio.getInputStream();
			outputStream=socketServicio.getOutputStream();
			int bytesRecibidos=inputStream.read(datosRecibidos);
			if (bytesRecibidos != 0) System.out.println("Received input from client "+datosRecibidos[0]+"!");

			if (datosRecibidos[1]!=-1){
				updateSelling(datosRecibidos);
				int[] toBeReturned = {datosRecibidos[0], 0};
			} else{
				int[] toBeReturned = {datosRecibidos[0], 0};
			}
			return toBeReturned;		
		} catch (IOException e) {
			System.err.println("Error al obtener los flujso de entrada/salida.");
		}

	}

	private void updateSelling(int[] product) {
		FileOutputStream fos = new FileOutputStream("database",true);
		fos.write(product, 0, product.length);
		fos.flush();
		fos.close();
	}
}
