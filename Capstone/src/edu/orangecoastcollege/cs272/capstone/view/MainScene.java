package edu.orangecoastcollege.cs272.capstone.view;


import javafx.application.Application;
import javafx.stage.Stage;

public class MainScene extends Application{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Application.launch(args);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		ViewNavigator.setStage(primaryStage);
		ViewNavigator.loadScene("OCC Recycling Center", ViewNavigator.FIRST_SCENE);
		
	}

}
