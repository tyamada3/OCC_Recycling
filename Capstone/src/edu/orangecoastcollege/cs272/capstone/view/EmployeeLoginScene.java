package edu.orangecoastcollege.cs272.capstone.view;

import javafx.fxml.FXML;

import javafx.scene.control.TextField;
import edu.orangecoastcollege.cs272.capstone.controller.Controller;
import javafx.event.ActionEvent;

import javafx.scene.control.Label;

import javafx.scene.input.MouseEvent;

public class EmployeeLoginScene {
	
	private Controller controller = Controller.getInstance();
	
	@FXML
	private Label UserErrorLabel;
	@FXML
	private Label PasswordErrorLabel;
	@FXML
	private TextField UserTF;
	@FXML
	private TextField PasswordTF;
	@FXML
	private Label LoginErrorLabel;

	// Event Listener on Label.onMouseClicked
	@FXML
	public void loadSignUp(MouseEvent event) {
		// TO EmployeeSignUpScene
		ViewNavigator.loadScene("Please Sign Up", ViewNavigator.EMPLOYEE_SIGNUP_SCENE);
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
		
		//String result = controller.signIn(user, password);
		
		return true;
		
	}
	// Event Listener on Button.onAction
	@FXML
	public void Cancel(ActionEvent event) {
		// TO First Scene
		ViewNavigator.loadScene("OCC Recycling Center", ViewNavigator.FIRST_SCENE);
	}
}
