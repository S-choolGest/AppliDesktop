/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Formulaire;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Ahmed
 */
public class HomePage extends Application {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {    
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Parent root;
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("gestionFormulaires.fxml"));
            Parent n = (Parent) loader.load();
            GestionFormulairesController  gf= loader.getController();
            gf.getStage(primaryStage);

  
                
            
//          root = FXMLLoader.load(getClass().getResource("gestionFormulaires.fxml"));
//          GestionFormulairesController g = new GestionFormulairesController();
            Scene scene = new Scene(n);
            primaryStage.setTitle("gestion formulaire");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }

}
