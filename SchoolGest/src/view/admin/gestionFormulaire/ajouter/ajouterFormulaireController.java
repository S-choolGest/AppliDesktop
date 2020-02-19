/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.admin.gestionFormulaire.ajouter;

import Services.Formulaire.ServicesFormulaire;
import Entite.Formulaire.Formulaire;
import java.io.File;
import java.text.ParseException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Pagination;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import view.admin.gestionFormulaire.GestionFormulaireController;


/**
 *
 * @author Ahmed
 */
public class ajouterFormulaireController {
    
    
    @FXML
    private TextField tfObjet;
    @FXML
    private TextArea taDescription;
    @FXML
    private TextField tfFichier;
    @FXML
    private Button btEnvoyer;
    @FXML
    private Button btAnnuler;
    
    private Pagination pagination;
    
    File[] affiches;
    
    @FXML
    private void ajouter(ActionEvent event)throws ParseException{
        Formulaire f;
        String objet = tfObjet.getText();
        String Description = taDescription.getText();
        String fichier = tfFichier.getText();
        f = new Formulaire(objet, Description, fichier);
        new Services.Formulaire.ServicesFormulaire().ajouter(f);
       
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("gestionFormulaire.fxml"));
        
        try{
            parent root = loader.load();
            GestionFormulaireController gfc = loader.getController();
            //gfc.setResObjet(tfObjet);
            
            
        }
    
    }
    
    
    
}
