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
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javax.swing.JOptionPane;
import java.net.URL;
import javafx.stage.Stage;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.internet.MimeMessage;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.net.HttpURLConnection;

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
    private TextField tfRechecher;

    ToggleGroup group = new ToggleGroup();

    public Formulaire formulaire = new Formulaire();
    public List<Formulaire> formulaires;
    ServicesFormulaire ser = new ServicesFormulaire();

    private Stage stage;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        try {
            clObjet.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getObjet()));
            clDescription.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getDescription()));
            clValidation.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().isValidation()));
            clDate.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getDateEnvoi()));
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
            afc.getStage(this.stage);
            tableFormulaire.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void getStage(Stage stage) {
        this.stage = stage;
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
        if (!rbTous.isSelected() && rbValide.isSelected() && !rbNonValide.isSelected()) {
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

    public void actualiserTable() {
        tableFormulaire.getItems().clear();
        tableFormulaire.getItems().addAll(FXCollections.observableList(formulaires));
    }

    @FXML
    private void btSupprimerOnClick(ActionEvent event) throws SQLException {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Suppression Formulaire");
        alert.setHeaderText("supprimer" + formulaire.getIdFormulaire());
        alert.setContentText("voulez vous supprimer la reclamation " + tableFormulaire.getSelectionModel().getSelectedItem().getIdFormulaire() + "?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            new ServicesFormulaire().delete(tableFormulaire.getSelectionModel().getSelectedItem().getIdFormulaireInt());
            formulaires = new ServicesFormulaire().readall();
            actualiserTable();
        }
        if (result.get() == ButtonType.CANCEL) {
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
        if (result.get() == ButtonType.OK) {
            try {
                new ServicesFormulaire().confirmerValidation(tableFormulaire.getSelectionModel().getSelectedItem().getIdFormulaireInt());
                formulaires = new ServicesFormulaire().readall();
                actualiserTable();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }

        // ici le code pour l'api SMS apres confirmation
        try {
            String host = "smtp.gmail.com";
            String user = "milleniumwow.ahmed@gmail.com";
            String pss = "warcraft10";
            String to = "4421695495874@txtlocal.co.uk";
            String from = user;
            String subject = "Message";
            String message = "du message et encore du message";
            boolean sessionDebug = false;
            Properties pros = System.getProperties();
            pros.put("mail.smtp.starttls.enable", "true");
            pros.put("mail.smtp.host", "host");
            pros.put("mail.smtp.auth", "true");
            pros.put("mail.smtp.port", "587");
            pros.put("mail.smtp.starttls.required", "true");
            java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
            Session mailSession = Session.getDefaultInstance(pros, null);
            mailSession.setDebug(sessionDebug);
            Message msg = new MimeMessage(mailSession);
            msg.setFrom(new InternetAddress(from));
            InternetAddress[] address = {new InternetAddress(to)};
            msg.setRecipients(Message.RecipientType.TO, address);
            msg.setSubject(subject);
            msg.setSentDate(new Date());
            msg.setText(message);
            Transport transport = mailSession.getTransport("smtp");
            transport.connect(host, user, pss);
            transport.sendMessage(msg, msg.getAllRecipients());
            transport.close();
            JOptionPane.showMessageDialog(null, "message send successfully");

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }

    }

    @FXML
    private void tfRechecherOnKeyReleased(KeyEvent event) {
        try {
            formulaires = new ServicesFormulaire().search(tfRechecher.getText());
            actualiserTable();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }



}
