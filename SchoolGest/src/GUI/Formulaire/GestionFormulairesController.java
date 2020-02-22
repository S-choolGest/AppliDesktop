/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Formulaire;

import Entite.Formulaire.Formulaire;
import Services.Formulaire.ServicesFormulaire;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;

import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Ahmed
 */
public class GestionFormulairesController implements Initializable {

    @FXML
    private TableView<Formulaire> tableFormulaire;
    @FXML
    private TableColumn<Formulaire, Integer> clFormulaire;
    @FXML
    private TableColumn<Formulaire, String> clObjet;
    @FXML
    private TableColumn<Formulaire, String> clDescription;
    @FXML
    private TableColumn<Formulaire, Boolean> clValidation;
    @FXML
    private TableColumn<Formulaire, Date> clDate;
    @FXML
    private Button btAjouter;
    @FXML
    private RadioButton rbTous;
    @FXML
    private ToggleGroup ConsulterValidation;
    @FXML
    private RadioButton rbValide;
    @FXML
    private ToggleGroup ConsulterValidation1;
    @FXML
    private RadioButton rbNonValide;
    @FXML
    private ToggleGroup ConsulterValidation2;
    @FXML
    private Button btSupprimer;
    
    public Formulaire formulaire = new Formulaire();
    public List<Formulaire> formulaires;
    ServicesFormulaire ser = new ServicesFormulaire();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        try {
            clFormulaire.setCellValueFactory(new PropertyValueFactory<Formulaire, Integer>("idFormulaire"));
            clObjet.setCellValueFactory(new PropertyValueFactory<Formulaire, String>("Objet"));
            clDescription.setCellValueFactory(new PropertyValueFactory<Formulaire, String>("Description"));
            clValidation.setCellValueFactory(new PropertyValueFactory<Formulaire, Boolean>("Validation"));
            clDate.setCellValueFactory(new PropertyValueFactory<Formulaire, Date>("date Envoi"));
            formulaires = new Services.Formulaire.ServicesFormulaire().readall();
            tableFormulaire.getItems().addAll(FXCollections.observableList(formulaires));
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void btAjouterOnClick(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ajouterFormulaire.fxml"));
            Parent root = loader.load();
            AjouterFormulaireController afc = loader.getController();
            tableFormulaire.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void rbTousOnSelect(ActionEvent event) {
        try {
            formulaires = new ServicesFormulaire().readall();
            actualiserTable();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void rbValideOnSelect(ActionEvent event) {
        formulaires = new ServicesFormulaire().getSimpleFormulairesConfirmes();
        actualiserTable();
    }

    @FXML
    private void rbNonValideOnSelect(ActionEvent event) {
        formulaires = new ServicesFormulaire().getSimpleFormulairesNonConfirmes();
        actualiserTable();
    }
    
    public void actualiserTable(){
        tableFormulaire.getItems().clear();
        tableFormulaire.getItems().addAll(FXCollections.observableList(formulaires));
    }

    @FXML
    private void btSupprimerOnClick(ActionEvent event) throws SQLException {
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Suppression Formulaire");
        alert.setHeaderText("supprimer"+formulaire.getIdFormulaire());
        alert.setContentText("vous voulez vraiment supprimer le formulaire "+tableFormulaire.getSelectionModel().getSelectedItem().getIdFormulaire() + "?");
        Optional<ButtonType> result =alert.showAndWait();
        if(result.get() == ButtonType.OK){
            new ServicesFormulaire().delete(tableFormulaire.getSelectionModel().getSelectedItem().getIdFormulaire());
            formulaires = new ServicesFormulaire().readall();
            actualiserTable();
        }
        if(result.get() == ButtonType.CANCEL){
            alert.close();
        }

    }

    

}
