package edu.orangecoastcollege.cs272.capstone.view;

import javafx.fxml.FXML;

import javafx.scene.control.TextField;
import edu.orangecoastcollege.cs272.capstone.controller.Controller;
import javafx.event.ActionEvent;

import javafx.scene.control.Label;

import javafx.scene.input.MouseEvent;

public class CustomerSignUpScene {
	
	private Controller controller = Controller.getInstance();
	
	@FXML
	private TextField NameTF;
	@FXML
	private TextField EmailTF;
	@FXML
	private TextField PasswordTF;
	@FXML
	private Label NameErrorLabel;
	@FXML
	private Label EmailErrorLabel;
	@FXML
	private Label PasswordErrorLabel;
	@FXML
	private Label SignUpErrorLabel;
	@FXML
	private Label UserErrorLabel;
	@FXML
	private TextField UserTF;

	// Event Listener on Button.onAction
	@FXML
	public boolean SignUp(ActionEvent event) {
		
		String name = NameTF.getText();
		String user = UserTF.getText();
		String email = EmailTF.getText();
		String password = PasswordTF.getText();
		
		NameErrorLabel.setVisible(name.isEmpty());
		UserErrorLabel.setVisible(user.isEmpty());
		EmailErrorLabel.setVisible(email.isEmpty());
		PasswordErrorLabel.setVisible(password.isEmpty());
		
		if(NameErrorLabel.isVisible() || EmailErrorLabel.isVisible() || PasswordErrorLabel.isVisible() || UserErrorLabel.isVisible())
			return false;
		
		String result = controller.customerSignUp(name, user, email, password);
		
		if(result.equalsIgnoreCase("SUCCESS")) {
			
			SignUpErrorLabel.setVisible(false);
			ViewNavigator.loadScene("Customer", ViewNavigator.CUSTOMER_SCENE);
			return true;
			
		}
		
		SignUpErrorLabel.setText(result);
		SignUpErrorLabel.setVisible(true);
			
		
		return false;
	}
	
	// Event Listener on Label.onMouseClicked
	@FXML
	public void loadSignIn(MouseEvent event) {
		
		// TO CustomerLoginScene
		ViewNavigator.loadScene("Please Log In", ViewNavigator.CUSTOMER_LOGIN_SCENE);
		
	}
}
