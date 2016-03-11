package client;

import javafx.application.Application;

public class Main {
	public static void main( String[] args ) {
		if( args.length < 2 ) {
			System.err.println( "usage: program ip port" );
			System.exit( -1 );
		}

		if( args.length >= 3 ) {
			if( args[2].equals( "term" ) ) {
				new TerminalInterface( args[0], Integer.parseInt(args[1]) );
			}
			if( args[2].equals( "fx" ) ) {
				Application.launch(FXInterface.class, args);
			}
		} else {
			new TerminalInterface( args[0], Integer.parseInt(args[1]) );
		}

	}
}
