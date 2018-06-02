package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;

public class IdentificationController {

	
	public void buttonClick(ActionEvent e) throws IOException{
		String name = ((Button) e.getSource()).getText();
		String message = "GETB "+name;
		Double balance;
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/User.fxml"));
		Parent root = loader.load();
		
		Client client = new Client();
		balance = Double.parseDouble(client.sendReturn(message));
		
		UserController userController = loader.getController();
		userController.setWelcomeText(name);
		userController.setBalanceText(balance);
		((Button) e.getSource()).getScene().setRoot(root);
	}
	
}
