/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Formulaire;

import Entite.Formulaire.Formulaire;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
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
public class ModifierFormulaireController implements Initializable {

    @FXML
    private AnchorPane anchorAffiches;
    @FXML
    private TextField tfObjet;
    @FXML
    private TextField tfFichier;
    @FXML
    private Button btModifier;
    @FXML
    private TextArea taDescription;
    @FXML
    private Button btAnnuler;
    
    private Formulaire f;
    private Services.Formulaire.ServicesFormulaire ser = new Services.Formulaire.ServicesFormulaire();
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }    

    @FXML
    private void btModifierOnClick(ActionEvent event) {
        try {
            f.setObjet(tfObjet.getText());
            f.setDescription(taDescription.getText());
            f.setFichier(tfFichier.getText());
            ser.update(f);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void btAnnulerOnClick(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Retour");
        alert.setHeaderText("");
        alert.setContentText("voulez vous retourner a la page précédante ?");
        Optional<ButtonType> result =alert.showAndWait();
        if(result.get() == ButtonType.OK){
            try {
                Parent root;
                root = FXMLLoader.load(getClass().getResource("gestionFormulaires.fxml"));
                btAnnuler.getScene().setRoot(root);
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
        if(result.get() == ButtonType.CANCEL){
            alert.close();
        }
    }
    
    
    public void setFormulaire(Formulaire f){
		this.f = f;
		tfObjet.setText(f.getObjet());
		taDescription.setText(f.getDescription());
		tfFichier.setText(f.getFichier());
		System.out.println(f);
	}
    
}
