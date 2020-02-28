/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.ExtraScolaire;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author MMD
 */
public class Choix_ExtraScolaireController implements Initializable {

    private Pane info;
    @FXML
    private AnchorPane body;
    private Boolean info_vue = false;
    @FXML
    private Button Go_to_Eventt;
    @FXML
    private Button Go_To_Clubb;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    


    @FXML
    private void cacher_note(MouseEvent event) {
    }


    @FXML
    private void Go_to_Event(ActionEvent event) throws IOException {
                
		body.getChildren().clear();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("../ExtraScolaire/AddClub.fxml"));
		Parent n = (Parent) loader.load();
		AddClubController emp = loader.getController();
		body.getChildren().add(n);
    }

    @FXML
    private void Go_To_Club(ActionEvent event) throws IOException{
        
    
                body.getChildren().clear();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("../ExtraScolaire/AddEvent2.fxml"));
		Parent n = (Parent) loader.load();
		AddEventtController emp = loader.getController();
		body.getChildren().add(n);
    
    }
}