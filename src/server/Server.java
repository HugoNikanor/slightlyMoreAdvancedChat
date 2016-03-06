package server;

import other.packet;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
	private ServerSocket server;
	private Socket client;

	private ObjectOutputStream os;

	public Server() throws IOException {
		server = new ServerSocket(8080);

		client = server.accept();
		os = new ObjectOutputStream(client.getOutputStream());

		System.out.println( "client ready!" );

		Scanner sc = new Scanner( System.in );
	

		System.out.println( "send a message to the client" );
		String sendStr = "";
		while( !sendStr.equals("STOP") ) {
			System.out.print(":");
			sendStr = sc.next();

		Packet packet = new Packet(sendStr);

			try{os.writeObject ( packet );}
			catch(IOException e){
				e.printStackTrace();
			}
		}

		sc.close();
		os.close();
	}
}
