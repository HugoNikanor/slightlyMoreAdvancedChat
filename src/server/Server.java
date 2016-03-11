package server;

import other.Packet;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {

	private ServerSocket server;
	private ArrayList<ObjectOutputStream> outStreams;

	public Server() throws IOException {
		outStreams = new ArrayList<ObjectOutputStream>();
		server = new ServerSocket(8080);

		new Thread( new ConnectionThread() ).start();

	}

	private class ConnectionThread implements Runnable {
		@Override
		public void run() {
			while( true ) {
				try {
					new ClientThread( server.accept() );
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	private class ClientThread implements Runnable {

		private Socket client;
		private ObjectInputStream in;

		public ClientThread( Socket client ) {
			this.client = client;

			try {
				outStreams.add(new ObjectOutputStream(client.getOutputStream()));
				in = new ObjectInputStream( client.getInputStream() );
			} catch (IOException e) {
				e.printStackTrace();
			}

			System.out.println( "New client conneced" );
			new Thread( this ).start();
		}

		private void cleanup() {
			try {
				in.close();
				// TODO note that this might not work.
				// Possibly replace the arrayList with a hashmap and use the client object as the key
				outStreams.remove( client.getOutputStream() );
				client.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

		@Override
		public void run() {
			while( true ) {
				try {
					Packet inPacket = (Packet) in.readObject();
					System.out.println( inPacket );
					for( ObjectOutputStream out : outStreams ) {
						out.writeObject( inPacket );
					}
				} catch( Exception e ) {
					// TODO possibly better exception handling
					e.printStackTrace();
					this.cleanup();
				}
			}
		}
	}
}
