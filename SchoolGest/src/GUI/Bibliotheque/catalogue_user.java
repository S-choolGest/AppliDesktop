/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Bibliotheque;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author william
 */
public class catalogue_user extends Application {
	
	@Override
	public void start(Stage Stage) throws IOException {
		
		Parent root = FXMLLoader
        .load(getClass().getResource("bibliotheque_user.fxml"));
        Scene scene = new Scene(root);
        Stage.setTitle("Edutech Bibliotheque");
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
