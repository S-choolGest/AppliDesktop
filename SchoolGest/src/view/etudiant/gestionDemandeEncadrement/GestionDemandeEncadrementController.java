/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.etudiant.gestionDemandeEncadrement;

import Entite.pfe.DemandeEncadrement;
import Entite.pfe.Pfe;
import Services.Pfe.DemandeEncadrementService;
import Services.Pfe.PfeService;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author TOSHIBA
 */
public class GestionDemandeEncadrementController implements Initializable {

    
    @FXML
    private TabPane panGestionDemande;
    @FXML
    private Tab panAfficherDemande;
    @FXML
    private TableColumn<DemandeEncadrement, String> colTitre;
    @FXML
    private TableColumn<DemandeEncadrement, String> colSujet;
    @FXML
    private TableColumn<DemandeEncadrement, String> colEtat;
    @FXML
    private TableView<DemandeEncadrement> tableDemandeEncadrementEtudiant;
    
    @FXML
    private ChoiceBox<Pfe> selectPfe;
    @FXML
    private Label txtSujet;
    @FXML
    private Label txtAcc;
    @FXML
    private Label txtRef;
    @FXML
    private Tab panAjouter;
    @FXML
    private TextField tfMail;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txtAcc.setVisible(false);
        txtRef.setVisible(false);
        txtSujet.setVisible(false);
        // TODO
    }    

    @FXML
    private void ajouter(ActionEvent event) throws SQLException {
        try {
            DemandeEncadrementService des =new DemandeEncadrementService();
            String ids =selectPfe.getSelectionModel().getSelectedItem().getsId();
            int id =Integer.parseInt(ids);
            String mail = tfMail.getText();
            int idprof = des.getIdUserParMail(mail);
            DemandeEncadrement d = new DemandeEncadrement(idprof, id);
            des.ajouterDemande(d);
            txtAcc.setVisible(true);
            txtRef.setVisible(false);
        } catch (Exception ex) {
            txtRef.setVisible(true);
            txtAcc.setVisible(false);
        }
    }
    @FXML
    private void afficher (Event event) throws SQLException
    {
        DemandeEncadrementService des =new DemandeEncadrementService();
            colTitre.setCellValueFactory(c->new SimpleStringProperty(c.getValue().getTitre()));
            colSujet.setCellValueFactory(c->new SimpleStringProperty(c.getValue().getSujet()));
            colEtat.setCellValueFactory(c->new SimpleStringProperty(c.getValue().getEtat()));
            ObservableList<DemandeEncadrement> list =des.getMesDemandesEtudiant();
            tableDemandeEncadrementEtudiant.setItems(list);
            
    }

    @FXML
    private void initializePan(Event event) {
        try {
            PfeService pes = new PfeService();
            ObservableList<Pfe> list= pes.getMesPfe();
            selectPfe.setItems(list);
        } catch (Exception ex) {
            txtSujet.setVisible(true);
        }
        
    }
    
}
