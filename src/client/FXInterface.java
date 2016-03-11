package client;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.List;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class FXInterface extends Application {

	private String ip;
	private int port;


	@Override
	public void init() throws NumberFormatException, IOException {
		Application.Parameters param = this.getParameters();
		List<String> args = param.getRaw();

		ip = args.get(0);
		port = Integer.parseInt( args.get(1) );
		System.out.println( ip );
		System.out.println( port );

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		URL url = Paths.get( "fxml/ui.fxml" ).toUri().toURL();

		FXMLLoader loader = new FXMLLoader( url );

		Pane root = loader.load();
	 	((UiController) loader.getController()).addClientData( ip, port );

		Scene scene = new Scene( root );
		primaryStage.setScene( scene );
		primaryStage.setTitle( "Chat" );
		primaryStage.show();


	}

}
