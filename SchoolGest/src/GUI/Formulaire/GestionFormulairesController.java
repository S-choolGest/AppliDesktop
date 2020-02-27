/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Formulaire;

import Entite.Formulaire.Formulaire;
import Services.Formulaire.ServicesFormulaire;
import java.io.IOException;
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
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;

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
    @FXML
    private Button btConfirmer;
    @FXML
    private Button btSMS;
    @FXML
    private TextField txtapi;
    
    
    ToggleGroup group = new ToggleGroup();
    
    public Formulaire formulaire = new Formulaire();
    public List<Formulaire> formulaires;
    ServicesFormulaire ser = new ServicesFormulaire();
    @FXML
    private TextField tfNumber;
    
    
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        try {
            clObjet.setCellValueFactory(c->new SimpleStringProperty(c.getValue().getObjet()));
            clDescription.setCellValueFactory(c->new SimpleStringProperty(c.getValue().getDescription()));
            clValidation.setCellValueFactory(c->new SimpleStringProperty(c.getValue().isValidation()));
            clDate.setCellValueFactory(c->new SimpleStringProperty(c.getValue().getDateEnvoi()));
            formulaires = new Services.Formulaire.ServicesFormulaire().readall();
            tableFormulaire.setItems((ObservableList<Formulaire>) formulaires);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        rbTous.setToggleGroup(group);
        rbValide.setToggleGroup(group);
        rbNonValide.setToggleGroup(group);
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

        if (rbTous.isSelected() && !rbValide.isSelected() && !rbNonValide.isSelected()) {
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
        if (!rbTous.isSelected() &&rbValide.isSelected() && !rbNonValide.isSelected()) {
            formulaires = new ServicesFormulaire().getSimpleFormulairesConfirmes();
            actualiserTable();
        }

    }

    @FXML
    private void rbNonValideOnSelect(ActionEvent event) {
        if (!rbTous.isSelected() && !rbValide.isSelected() && rbNonValide.isSelected()) {
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
        alert.setContentText("voulez vous supprimer la reclamation "+tableFormulaire.getSelectionModel().getSelectedItem().getIdFormulaire() + "?");
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
        alert.setTitle("Modification Reclamation");
        alert.setHeaderText("Mise à Jour" + formulaire.getIdFormulaire());
        alert.setContentText("Voulez vous modifier la réclamation " + tableFormulaire.getSelectionModel().getSelectedItem().getIdFormulaire() + "?");
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

    @FXML
    private void btConfirmerOnClick(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmeation Reclamation");
        alert.setHeaderText("Confirmer" + formulaire.getIdFormulaire());
        alert.setContentText("Voulez vous confirmer la réclamation " + tableFormulaire.getSelectionModel().getSelectedItem().getIdFormulaire() + "?");
        Optional<ButtonType> result = alert.showAndWait();
        if(result.get() == ButtonType.OK) {
            try {
                new ServicesFormulaire().confirmerValidation(tableFormulaire.getSelectionModel().getSelectedItem().getIdFormulaireInt());
                formulaires = new ServicesFormulaire().readall();
                actualiserTable();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    @FXML
    private void btSMSOnClick(ActionEvent event) {
        try {
			// Construct data
			String apiKey = "apikey=" + txtapi.getText();
			String message = "&message=" + "nous avons repondu a votre Reclamation";
			String sender = "&sender=" + "membre scolarite";
			String numbers = "&numbers=" + tfNumber.getText();
			
			// Send data
			HttpURLConnection conn = (HttpURLConnection) new URL("https://api.txtlocal.com/send/?").openConnection();
			String data = apiKey + numbers + message + sender;
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
			conn.getOutputStream().write(data.getBytes("UTF-8"));
			final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			final StringBuffer stringBuffer = new StringBuffer();
			String line;
			while ((line = rd.readLine()) != null) {
				//stringBuffer.append(line);
                                JOptionPane.showMessageDialog(null,"message"+line);
			}
			rd.close();
			
			//return stringBuffer.toString();
		} catch (Exception e) {
			//System.out.println("Error SMS "+e);
                        JOptionPane.showMessageDialog(null, e);
			//return "Error "+e;
		}
        
        
	}
        
    
    
    


}
