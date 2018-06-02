package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class LogController {
	@FXML
	private TextArea textArea;
	
	@FXML
	private Label name;
	
	public void setNameText(String s){
		name.setText(s);
	}
	
	public void setAreaText(String message){
		for(String s: message.split(";")){
			textArea.appendText(s+"\n");
		}
	}
	
	public void back(ActionEvent e) throws IOException{
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
