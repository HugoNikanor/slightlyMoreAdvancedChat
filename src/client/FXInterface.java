package client;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.List;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

import other.Packet;

public class FXInterface extends Application implements Initializable {


	@FXML
	private TextFlow textFlow;

	@FXML
	private TextField textField;

	private Client connection;

	@Override
	public void init() throws NumberFormatException, IOException {
		Application.Parameters param = this.getParameters();
		List<String> args = param.getRaw();

		connection = new Client(
				args.get(0),
				Integer.parseInt( args.get(1) ),
				new FXOutputStream(textFlow) );

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		URL url = Paths.get( "fxml/ui.fxml" ).toUri().toURL();

		FXMLLoader loader = new FXMLLoader( url );

		Pane root = loader.load();

		Scene scene = new Scene( root );
		primaryStage.setScene( scene );
		primaryStage.setTitle( "Chat" );
		primaryStage.show();

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}

	@FXML
	public void sendMessage() {
		connection.sendMessage( new Packet( textField.getText() ) );
		textField.clear();
	}
}
