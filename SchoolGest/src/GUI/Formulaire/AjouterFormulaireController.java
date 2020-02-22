/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Formulaire;

import Entite.Formulaire.Formulaire;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;


/**
 * FXML Controller class
 *
 * @author Ahmed
 */
public class AjouterFormulaireController implements Initializable {

    @FXML
    private AnchorPane anchorAffiches;
    @FXML
    private TextField tfObjet;
    @FXML
    private TextField tfFichier;
    @FXML
    private Button btEnvoyer;
    @FXML
    private TextArea taDescription;
    @FXML
    private Button btAnnuler;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btEnvoyerOnClick(ActionEvent event) {
        Formulaire f;
        String objet = tfObjet.getText();
        String description = taDescription.getText();
        String fichier = tfFichier.getText();
        f = new Formulaire(objet, description, fichier);
        new Services.Formulaire.ServicesFormulaire().ajouter(f);
        
    }

    @FXML
    private void btAnnulerOnClick(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Interruption !");
        alert.setHeaderText("Annuler l'ajout");
        alert.setContentText("voulez vous vraiment abondonner l'ajout?");
        Optional<ButtonType> result =alert.showAndWait();
        if(result.get() == ButtonType.OK){
            //FXMLLoader loader = FXMLLoader.load(getClass().getResource("gestionFormulaires.fxml"));
            Parent root = FXMLLoader.load(getClass().getResource("gestionFormulaires.fxml"));
            //AjouterFormulaireController afc = loader.getController();
            btAnnuler.getScene().setRoot(root);
        }
        if(result.get() == ButtonType.CANCEL){
            alert.close();
        }
        
    }
    
    
    
}
