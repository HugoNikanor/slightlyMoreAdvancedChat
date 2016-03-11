package client;

import java.io.PrintStream;

import javafx.application.Platform;
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
		print( msg.concat("\n") );
	}

	@Override
	public void print( String msg ) {
		Platform.runLater( new Thread( () -> {
			out.getChildren().add(
					new Text( msg ));
		}));
	}
}
