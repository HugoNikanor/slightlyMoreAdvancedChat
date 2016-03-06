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

	private String name;

	public Client( String ip, int port ) {
		try {
			sc = new Scanner( System.in );

			name = System.getProperty("user.name");
			System.out.println(name);

			socket = new Socket(ip, port);
			in = new ObjectInputStream( socket.getInputStream() );
			out = new ObjectOutputStream( socket.getOutputStream() );

			new Thread(() -> {
				try {
					String str = ((Packet) in.readObject()).getMessage();
					System.out.println( str );
				} catch( IOException e ) {
					e.printStackTrace();
				} catch( ClassNotFoundException e ) {
					e.printStackTrace();
				}
			}).start();

			while( true ) {
				System.out.print( ":" );
				String outStr = sc.next();
				out.writeObject( new Packet(name, outStr) );
			}
		} catch( UnknownHostException e ) {
		} catch( IOException e ) {
		}
	}
}
