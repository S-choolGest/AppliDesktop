/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Formulaire;

import Entite.Formulaire.Formulaire;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import rest.file.uploader.tn.FileUploader;


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
    @FXML
    private Label error;
    @FXML
    private Label reussi;
    @FXML
    private Button btParcourir;
    private Stage stage;
    private String url_pdf;

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
        error.setText("");
        reussi.setText("");
        Boolean verif = true;
        if (tfObjet.getText().equals("") && taDescription.getText().equals("")) {
            error.setText("veuillez remplir les champs manquants.");
            verif = false;
        }
        else if(verif = true) {
            String objet = tfObjet.getText();
            String description = taDescription.getText();
            String fichier = tfFichier.getText();
            f = new Formulaire(objet, description, fichier);
            new Services.Formulaire.ServicesFormulaire().ajouter(f);
            reussi.setText("ajout avec succés.");
        }

    }

    @FXML
    private void btAnnulerOnClick(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Interruption !");
        alert.setHeaderText("Annuler l'ajout");
        alert.setContentText("voulez vous vraiment abondonner l'ajout?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            Parent root = FXMLLoader.load(getClass().getResource("gestionFormulaires.fxml"));
            btAnnuler.getScene().setRoot(root);
        }
        if (result.get() == ButtonType.CANCEL) {
            alert.close();
        }

    }

    public void getStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    private void edit_fichier(ActionEvent event) throws IOException {

        FileChooser filechooser = new FileChooser();
        File file = filechooser.showOpenDialog(this.stage);
        if (file.isFile() && file.getName().contains(".pdf")) {
            System.out.println("hi!!!" + file);
            FileUploader fu = new FileUploader("localhost/pidev");
            String fileNameInServer = fu.upload(file.toString());
            this.url_pdf = "http://localhost/pidev/uploads/" + fileNameInServer;
            tfFichier.setText(url_pdf);
        }

    }


    
}
