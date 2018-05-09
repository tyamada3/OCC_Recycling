package edu.orangecoastcollege.cs272.capstone.view;

import javafx.fxml.FXML;

import javafx.scene.control.TextField;
import edu.orangecoastcollege.cs272.capstone.controller.Controller;
import javafx.event.ActionEvent;

import javafx.scene.control.Label;

import javafx.scene.input.MouseEvent;

public class CustomerLoginScene {
	
	private static Controller controller = Controller.getInstance();
	
	@FXML
	private TextField UserTF;
	@FXML
	private TextField PasswordTF;
	@FXML
	private Label UserErrorLabel;
	@FXML
	private Label PasswordErrorLabel;
	@FXML
	private Label LoginErrorLabel;
	

	// Event Listener on Label.onMouseClicked
	@FXML
	public void loadSignUp(MouseEvent event) {
		// TO CustomerSignUpScene
		ViewNavigator.loadScene("Please Sign Up", ViewNavigator.CUSTOMER_SIGNUP_SCENE);
	}
	// Event Listener on Button.onAction
	@FXML
	public boolean Login(ActionEvent event) {
		
		String user = UserTF.getText();
		String password = PasswordTF.getText();
		
		UserErrorLabel.setVisible(user.isEmpty());
		PasswordErrorLabel.setVisible(password.isEmpty());
		
		if(UserErrorLabel.isVisible() || PasswordErrorLabel.isVisible())
			return false;
		
		String result = controller.customerSignIn(user, password);
		
		return true;
		
	}
	// Event Listener on Button.onAction
	@FXML
	public void Cancel(ActionEvent event) {
		// To first scene
		ViewNavigator.loadScene("OCC Recycling Center", ViewNavigator.FIRST_SCENE);
	}
}
