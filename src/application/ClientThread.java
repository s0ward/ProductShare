

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Date;

class ClientThread extends Thread {

	private int CLIENT_REQUEST_TIMEOUT = 15*60*1000;
	private BufferedWriter bw = null;
	private FileWriter fw = null;
	private Socket socket;
	private BufferedReader socketReader;
	private BufferedWriter socketWriter;

	public ClientThread(Socket socket) throws IOException{
		this.socket = socket;
		socket.setSoTimeout(CLIENT_REQUEST_TIMEOUT);
		socketReader = new BufferedReader(
				new InputStreamReader(socket.getInputStream()));
		socketWriter = new BufferedWriter(
				new OutputStreamWriter(socket.getOutputStream()));


	}

	public void run(){
		Database db = new Database();

		System.out.println(new Date().toString() + " : " + 
				"Accepted client : " + socket.getInetAddress() + 
				":" + socket.getPort());
		try {
				String line = socketReader.readLine();
				String cmd = line.split("\\s+")[0];
				
				
				
				switch(line.split("\\s+")[0]){

				case "ADD":
					Double price = Double.parseDouble(line.split("\\s+")[2]);
					//System.out.println("Price: "+price);
					String nameAdd = line.split("\\s+")[3];
					//System.out.println("Name: "+nameAdd);
					String Kristiyan = "TRANSFER "+nameAdd+" Kristiyan "+(price/3);
				 	//System.out.println("Kristiyan: "+ Kristiyan);
					String Kristina = "TRANSFER "+nameAdd+" Kristina "+(price/3);
					//System.out.println("Kristina: "+ Kristina);
					String Mette = "TRANSFER "+nameAdd+" Mette "+(price/3);
					//System.out.println("Mette: " + Mette);
					db.writeLog(line);
					//System.out.println("Line: " + line);
					db.writeBalance(Kristiyan);
					db.writeBalance(Kristina);
					db.writeBalance(Mette);
				break;
				
				case "TRANSFER":
					db.writeLog(line);
					db.writeBalance(line);
				break;
				
				case "GET":
					String messageGet = db.readLog();
					
					socketWriter.write(messageGet+"\n");
					socketWriter.flush();
				break;
				
				case "GETB":
					String nameGetb = line.split("\\s+")[1];
					String messageGetb = db.readBalance(nameGetb);
					
					socketWriter.write(messageGetb+"\n");
					socketWriter.flush();
					
				break;
				
				default:
					System.out.println("Default case");
					break;
				}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
