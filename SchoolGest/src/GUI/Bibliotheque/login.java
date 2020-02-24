/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Bibliotheque;

import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author william
 */
public class login extends Application {

	@Override
	public void start(Stage Stage) throws IOException {
//        Parent root = FXMLLoader
//        .load(getClass().getResource("login.fxml"));
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("login.fxml"));
		Parent n = (Parent) loader.load();
		LoginController log = loader.getController();
		log.close();
		Scene scene = new Scene(n);
		Stage.setTitle("Edutech");
		Stage.setResizable(false);
		scene.setFill(javafx.scene.paint.Color.TRANSPARENT);
		Stage.setScene(scene);
		Stage.show();

	}

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		launch(args);
	}

}
