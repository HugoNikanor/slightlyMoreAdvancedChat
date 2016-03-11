package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;

import other.Packet;

public class Client {
	private Socket socket;

	private ObjectInputStream in;
	private ObjectOutputStream out;

	/** used when printing information to the user */
	//private PrintStream outStream;

	public Client( String ip, int port, PrintStream outStream ) throws IOException {

		//this.outStream = outStream;

		try {
			System.out.println( ip );
			System.out.println( port );

			socket = new Socket(ip, port);
			in = new ObjectInputStream( socket.getInputStream() );
			out = new ObjectOutputStream( socket.getOutputStream() );

			new Thread(() -> {
				while( true ) {
				try {
					String str = ((Packet) in.readObject()).getMessage();
					outStream.println( str );
				} catch( IOException e ) {
					e.printStackTrace();
				} catch( ClassNotFoundException e ) {
					e.printStackTrace();
				}
				}
			}).start();

		} catch( UnknownHostException e ) {
		}
	}

	public void sendMessage( Packet message ) {
		try {
			out.writeObject(message);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// TODO possibly have a close method here
}
