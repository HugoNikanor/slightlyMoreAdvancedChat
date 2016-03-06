package server;

import other.Packet;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.ArrayList;

public class Server {
	private ServerSocket server;
	private Socket client;

	private ObjectOutputStream os;

	public Server() throws IOException {

		ArrayList clientList = new ArrayList();
		client = server.accept();

		server = new ServerSocket(8080);
		clientList.add(client);
		
		os = new ObjectOutputStream(client.getOutputStream());

		System.out.println( "client ready!" );

		Scanner sc = new Scanner( System.in );

		System.out.println( "send a message to the client" );
		String sendStr = "";
		while( !sendStr.equals("STOP") ) {
			System.out.print(":");
			sendStr = sc.next();

			try {
				os.writeObject( new Packet(sendStr) );
			} catch( IOException e ) {
				e.printStackTrace();
			}
		}

		sc.close();
		os.close();
	}
}
