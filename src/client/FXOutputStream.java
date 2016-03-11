package client;

import java.io.PrintStream;

import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class FXOutputStream extends PrintStream {

	private TextFlow out;

	public FXOutputStream( TextFlow out ) {
		super( System.out, true );
		this.out = out;
	}

	@Override
	public void println( String msg ) {
		out.getChildren().add(
				new Text( String.format( "%s%n", msg )));
	}

	@Override
	public void print( String msg ) {
		out.getChildren().add(
				new Text( msg ));
	}
}
