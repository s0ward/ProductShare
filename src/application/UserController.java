package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class UserController {
	@FXML
	private Label welcome;
	
	@FXML
	private Label balance;
	
	public void setWelcomeText(String name){
		welcome.setText("Welcome "+name+" !");
	}
	public void setBalanceText(Double balanceValue){
		balance.setText("Balance: "+balanceValue.toString());
	}
	public void addItem(ActionEvent e) throws IOException{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/AddItem.fxml"));
		Parent root = loader.load();
		((Button) e.getSource()).getScene().setRoot(root);
		AddItemController addItemController = loader.getController();
		addItemController.setName(welcome.getText().split(" ")[1]);
	}
	
	public void viewLog(ActionEvent e) throws IOException, InterruptedException{
		String message = "GET";
		Client client = new Client();
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/Log.fxml"));
		Parent root = loader.load();
		
		((Button) e.getSource()).getScene().setRoot(root);
		LogController logController = loader.getController();
		logController.setNameText(welcome.getText().split(" ")[1]);
		logController.setAreaText(client.sendReturn(message));
	}
	
	public void transfer(ActionEvent e) throws IOException{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/Transfer.fxml"));
		Parent root = loader.load();
		((Button) e.getSource()).getScene().setRoot(root);
		TransferController transferController = loader.getController();
		transferController.setName(welcome.getText().split(" ")[1]);
		transferController.setCombo(welcome.getText().split(" ")[1]);
	}
	
	
	public void back(ActionEvent e) throws IOException{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/Identification.fxml"));
		Parent root = loader.load();
		((Button) e.getSource()).getScene().setRoot(root);
	}
}
