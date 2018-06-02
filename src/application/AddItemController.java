package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class AddItemController {
	@FXML
	private TextField name;
	
	@FXML
	private TextField description;
	
	@FXML
	private TextField price;

	public void setName(String user){
		name.setText(user);
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
	
	public void submit(ActionEvent e) throws IOException, InterruptedException{
		String item = description.getText().replaceAll("\\s+", "_");
		String message = "ADD "+item+" "+price.getText()+" "+name.getText();
		Client client = new Client();
		client.send(message);
	}
}
