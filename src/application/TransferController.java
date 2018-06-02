package application;

import java.io.IOException;
import java.net.UnknownHostException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class TransferController {
	@FXML
	private Label name;
	
	@SuppressWarnings("rawtypes")
	@FXML
	private ComboBox from;
	
	@SuppressWarnings("rawtypes")
	@FXML
	private ComboBox to;
	
	@FXML
	private TextField amount;
	
	
	
	public void setName(String name){
		this.name.setText(name);
	}
	
	@SuppressWarnings("unchecked")
	public void setCombo(String item){
		this.from.setValue(item);
	}
	
	public void transfer(ActionEvent e) throws UnknownHostException, IOException {
		String message = "TRANSFER "+from.getValue().toString()+" "+to.getValue().toString()+" "+amount.getText();
		Client client = new Client();
		client.send(message);
	}
	
	public void back(ActionEvent e) throws IOException {
		String message = "GETB "+name.getText();
		Double balance;
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/User.fxml"));
		Parent root = loader.load();
		UserController userController = loader.getController();
		Client client = new Client();
		
		balance = Double.parseDouble(client.sendReturn(message));
		userController.setWelcomeText(name.getText());
		userController.setBalanceText(balance);
		((Button) e.getSource()).getScene().setRoot(root);
	}
	
}
