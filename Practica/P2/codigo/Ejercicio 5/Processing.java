// ==================================================== //
// 			Processing 			//
// ==================================================== //

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Random;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class Processing extends Thread {
	private Socket socketService;
	private InputStream inputStream;
	private OutputStream outputStream;
	//Local copy of the DB
	private int[][] database = new int[10][2];
	//Get entries from external file
	private static final String fileName = "database.txt";

	public Processing(Socket socketService) {
		this.socketService=socketService;
	}
	
	public void run(){
		query();
	}

	void query(){
		try {
			// Streams			
			InputStream inputStream = socketService.getInputStream();
			OutputStream outputStream = socketService.getOutputStream();
		} catch (UnknownHostException e) {
			System.err.println("Error: Nombre de host no encontrado.");
		} catch (IOException e) {
			System.err.println("Error de entrada/salida al abrir el socket.");
		}
		try{
			BufferedReader inReader = new BufferedReader(new InputStreamReader(inputStream));
			String stringReceived = inReader.readLine();
			int[] newEntry = {0,0};
			String s[] = stringReceived.split("_");
			for (int i=0;i<2;i++) newEntry[i]=Integer.parseInt(s[i]);
			fillInternalDB();
			if (newEntry[0] != -1) {
				addToDB(newEntry);
				writeDB();
			} else {
				// invia sul socket la lista dei prodotti, ordine decrescente
				sorting();
				PrintWriter outPrinter = new PrintWriter(outputStream, true);
				for (int k=0;k<10;k++) outPrinter.println(Arrays.toString(database[k]));
				outPrinter.flush();
				outPrinter.close();		
			}			
		} catch (IOException e) {
			System.err.println("Error al obtener los flujso de entrada/salida.");
		}
				
	}

	void fillInternalDB(){
		int i=0,j=0;
		try {
			BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
			int[] newEntry;
			String line = null;
			while((line = bufferedReader.readLine()) != null) {
				//Riempire la matrice
				String[] s = line.split(" ");
				for (i=0;i<2;i++) database[j][i] = Integer.parseInt(s[i]);
				j++;
			}
			bufferedReader.close();         
		} catch(FileNotFoundException ex) {
			System.out.println("Unable to open file '" + fileName + "'");                
		} catch(IOException ex) {
			ex.printStackTrace();
		}
	}
	
	void addToDB(int[] array){
		int i=0;
		for (;i<10;i++) if (array[0] == database[i][0]) break;
		database[i][1] += array[1];
	}

	void writeDB(){
		try {
			//Write matrix to file
			BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true));
			for (int i=0;i<10;i++) writer.write(database[i][0]+" "+database[i][1]);
			writer.close();         
		} catch(FileNotFoundException ex) {
			System.out.println("Unable to open file '" + fileName + "'");                
		} catch(IOException ex) {
			ex.printStackTrace();
		}
	}
	
	void sorting(){
		int[] temp = {999,999};
		temp[0] = database[0][0];
		temp[1] = database[0][1];
		for (int i=1;i<10;i++)
			if (database[i][1] < temp[1]){
				//temp = database[i][1];
				//database[i][1] = database[i+1][1];
				//database[i+1][1] = temp;
			}
	}
}