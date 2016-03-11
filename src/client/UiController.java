package client;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.text.TextFlow;

import other.Packet;

public class UiController implements Initializable {

	@FXML
	private TextFlow textFlow;

	@FXML
	private TextField textField;

	private Client client;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}

	public void addClientData( String ip, int port ) {
		try {
			client = new Client(ip, port, new FXOutputStream(textFlow));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void sendMessage() {
		// TODO possibly have some error checking here
		client.sendMessage( new Packet( textField.getText() ) );
		textField.clear();
	}

}
