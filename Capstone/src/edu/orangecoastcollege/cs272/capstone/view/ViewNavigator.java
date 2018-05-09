package edu.orangecoastcollege.cs272.capstone.view;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ViewNavigator {

	public static final String FIRST_SCENE = "FirstScene.fxml";
	public static final String CUSTOMER_LOGIN_SCENE = "CustomerLoginScene.fxml";
	public static final String CUSTOMER_SIGNUP_SCENE = "CustomerSignUpScene.fxml";
	public static final String EMPLOYEE_LOGIN_SCENE = "EmployeeLoginScene.fxml";
	public static final String EMPLOYEE_SIGNUP_SCENE = "EmployeeSignUpScene.fxml";
	public static final String CUSTOMER_SCENE = "CustomerScene.fxml";
	
	public static Stage mainStage;
	
	public static void setStage(Stage stage) {
		
		mainStage = stage;
		
	}
	
	public static void loadScene(String title, String sceneFXML) {
		
		
		try {
			
			mainStage.setTitle(title);
			Scene scene = new Scene(FXMLLoader.load(ViewNavigator.class.getResource(sceneFXML)));
			mainStage.setScene(scene);
			mainStage.show();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.err.println("Error loading: " + sceneFXML + "\n" + e.getMessage());
			e.printStackTrace();
		}
		
	}

}
