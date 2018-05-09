package edu.orangecoastcollege.cs272.capstone.view;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;

public class FirstScene {

	// Event Listener on Button.onAction
	@FXML
	public void LoadCustomer(ActionEvent event) {
		// To Customer Login Scene
		ViewNavigator.loadScene("Please Log In", ViewNavigator.CUSTOMER_LOGIN_SCENE);
		
	}
	// Event Listener on Button.onAction
	@FXML
	public void LoadEmployee(ActionEvent event) {
		// To Employee Login Scene
		ViewNavigator.loadScene("Please Log In", ViewNavigator.EMPLOYEE_LOGIN_SCENE);
	}
}
