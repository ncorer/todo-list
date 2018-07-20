package sk.elct.java.todo_list_project.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class TodolistApp extends Application {

	public void start(Stage stage) throws Exception {
		
		
		DruhyController controller = new DruhyController();
		FXMLLoader fmxlLoader = new FXMLLoader(getClass().getResource("druhy.fxml"));
		fmxlLoader.setController(controller);
		Parent rootPane = fmxlLoader.load();
			

		Scene scene = new Scene(rootPane);
		stage.setTitle("To-do list");
		stage.setScene(scene);
		stage.show();
	}
	
	public static void main(String[] args) {
		
		launch(args); 

		
	}

}
