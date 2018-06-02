

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	public static int PORT = 3333;
	
	public static void main(String[] args) throws IOException{
		ServerSocket serverSocket = new ServerSocket(PORT);
		System.out.println("Server started");
		while (true){
			Socket socket= serverSocket.accept();
			ClientThread clientThread = new ClientThread(socket);
			clientThread.start();
			
		}
		
	}
}
