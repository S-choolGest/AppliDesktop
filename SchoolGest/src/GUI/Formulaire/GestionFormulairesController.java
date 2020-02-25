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
import java.sql.SQLException;

import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Ahmed
 */
public class GestionFormulairesController implements Initializable {

    @FXML
    private TableView<Formulaire> tableFormulaire;
    @FXML
    private TableColumn<Formulaire, String> clObjet;
    @FXML
    private TableColumn<Formulaire, String> clDescription;
    @FXML
    private TableColumn<Formulaire, String> clValidation;
    @FXML
    private TableColumn<Formulaire, String> clDate;
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
    @FXML
    private Button btModifier;
    
    boolean tous=rbTous.isSelected();
    boolean valide=rbValide.isSelected();
    boolean nonvalide=rbNonValide.isSelected();
    
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
            //clFormulaire.setCellValueFactory(c->new SimpleStringProperty(c.getValue().getIdFormulaire()));
            clObjet.setCellValueFactory(c->new SimpleStringProperty(c.getValue().getObjet()));
            clDescription.setCellValueFactory(c->new SimpleStringProperty(c.getValue().getDescription()));
            clValidation.setCellValueFactory(c->new SimpleStringProperty(c.getValue().isValidation()));
            clDate.setCellValueFactory(c->new SimpleStringProperty(c.getValue().getDateEnvoi()));
            formulaires = new Services.Formulaire.ServicesFormulaire().readall();
            tableFormulaire.setItems((ObservableList<Formulaire>) formulaires);
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

        if (tous && !valide && !nonvalide) {
            try {
                formulaires = new ServicesFormulaire().readall();
                actualiserTable();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    @FXML
    private void rbValideOnSelect(ActionEvent event) {
        if (!tous && valide && !nonvalide) {
            formulaires = new ServicesFormulaire().getSimpleFormulairesConfirmes();
            actualiserTable();
        }

    }

    @FXML
    private void rbNonValideOnSelect(ActionEvent event) {
        if (!tous && !valide && nonvalide) {
            formulaires = new ServicesFormulaire().getSimpleFormulairesNonConfirmes();
            actualiserTable();
        }
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
            new ServicesFormulaire().delete(tableFormulaire.getSelectionModel().getSelectedItem().getIdFormulaireInt());
            formulaires = new ServicesFormulaire().readall();
            actualiserTable();
        }
        if(result.get() == ButtonType.CANCEL){
            alert.close();
        }

    }

    @FXML
    private void btModifierOnClick(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("modification Formulaire");
        alert.setHeaderText("Update" + formulaire.getIdFormulaire());
        alert.setContentText("vous allez modifier le formulaire " + tableFormulaire.getSelectionModel().getSelectedItem().getIdFormulaire() + "?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            try {
                Formulaire f = tableFormulaire.getSelectionModel().getSelectedItem();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifierFormulaire.fxml"));
                Parent root = loader.load();
                ModifierFormulaireController afc = loader.getController();
                afc.setFormulaire(f);
                tableFormulaire.getScene().setRoot(root);
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
        if (result.get() == ButtonType.CANCEL) {
            alert.close();
        }
    }



}
