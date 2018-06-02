package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;


public class Client {
	private static final String IP = "192.168.0.3";
	private static final int PORT = 3333;
	
	public void send(String message) throws UnknownHostException, IOException{
		Socket socket = new Socket(IP,PORT);
		BufferedWriter socketWriter = new BufferedWriter(
				new OutputStreamWriter(socket.getOutputStream()));
		
		socketWriter.write(message+"\n");
		socketWriter.flush();
		
		socket.close();
		
	}
	
	public String sendReturn(String message) throws UnknownHostException, IOException{
		Socket socket = new Socket(IP,PORT);
		String response = "UNKNOWN";
		BufferedWriter socketWriter = new BufferedWriter(
				new OutputStreamWriter(socket.getOutputStream()));
		BufferedReader socketReader = new BufferedReader(
				new InputStreamReader(socket.getInputStream()));
		
		socketWriter.write(message+"\n");
		socketWriter.flush();
		response = socketReader.readLine();
		
		socket.close();
		
		return response;
	}
	
}
