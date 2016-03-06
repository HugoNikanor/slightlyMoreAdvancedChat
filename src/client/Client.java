package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

import other.Packet;

public class Client {
	private Socket socket;

	private ObjectInputStream in;

	public Client( String ip, int port ) {
		try {
			while( true ) {
				socket = new Socket(ip, port);

				in = new ObjectInputStream( socket.getInputStream() );

				String str = ((Packet) in.readObject()).getMessage();
				System.out.println( str );
			}
		} catch( IOException e ) {
			e.printStackTrace();
		} catch( ClassNotFoundException e ) {
			e.printStackTrace();
		}
	}
}
