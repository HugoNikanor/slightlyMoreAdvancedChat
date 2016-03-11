package client;

import java.util.Scanner;

import other.Packet;

public class TerminalInterface {

	private Client connection;

	public TerminalInterface( String ip, int port ) {
		try {
		connection = new Client( ip, port, System.out );

		Scanner sc = new Scanner( System.in );
		String msg = "";
		while( !msg.equals("STOP") ) {
			msg = sc.nextLine();
			connection.sendMessage( new Packet(msg) );
		}
		sc.close();
		} catch( Exception e ) {
			e.printStackTrace();
		}
	}

}
