/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.admin.gestionFormulaire;

import Entite.Formulaire.Formulaire;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author Ahmed
 */
public class GestionFormulaireController {
    
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
    private Button btSupprimer;
    @FXML
    private Button btModifier;
    @FXML
    private Button btValider;
    @FXML
    private RadioButton rbTous;
    @FXML
    private RadioButton rbValide;
    @FXML
    private RadioButton rbNonValide;
    @FXML
    private TextField tfRechecher;
    
    
    public static Formulaire formulaire;
    public List<Formulaire> formulaires;
    
    public void initialize(URL url, ResourceBundle rb) throws SQLException {
        clFormulaire.setCellValueFactory(new PropertyValueFactory<Formulaire, Integer>("idFormulaire"));
        clObjet.setCellValueFactory(new PropertyValueFactory<Formulaire, String>("Objet"));
        clDescription.setCellValueFactory(new PropertyValueFactory<Formulaire, String>("Description"));
        clValidation.setCellValueFactory(new PropertyValueFactory<Formulaire, Boolean>("Validation"));
        clDate.setCellValueFactory(new PropertyValueFactory<Formulaire, Date>("date Envoi"));
        formulaires = new Services.Formulaire.ServicesFormulaire().readall();
        tableFormulaire.getItems().addAll(FXCollections.observableList(formulaires));
        btModifier.setVisible(false);
        btSupprimer.setVisible(false);
        btValider.setVisible(false);
    }
    
}
