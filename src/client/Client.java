package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import other.Packet;

public class Client {
	private Socket socket;

	private ObjectInputStream in;
	private ObjectOutputStream out;

	private Scanner sc;

	public Client( String ip, int port ) {
		try {
			socket = new Socket(ip, port);
			in = new ObjectInputStream( socket.getInputStream() );
			out = new ObjectOutputStream( socket.getOutputStream() );

			sc = new Scanner( System.in );

			new Thread(() -> {
				while( true ) {
				try {
					String str = ((Packet) in.readObject()).getMessage();
					System.out.println( str );
				} catch( IOException e ) {
					e.printStackTrace();
				} catch( ClassNotFoundException e ) {
					e.printStackTrace();
				}
				}
			}).start();

			while( true ) {
				System.out.print( ":" );
				String outStr = sc.next();
				out.writeObject( new Packet(outStr) );
			}
		} catch( UnknownHostException e ) {
		} catch( IOException e ) {
		}
	}
}
