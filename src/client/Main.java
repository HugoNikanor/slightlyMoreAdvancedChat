package client;

public class Main {
	public static void main( String[] args ) {
		if( args.length != 2 ) {
			System.err.println( "usage: program ip port" );
			System.exit( -1 );
		}

		new Client( args[0], Integer.parseInt(args[1]) );

	}
}
