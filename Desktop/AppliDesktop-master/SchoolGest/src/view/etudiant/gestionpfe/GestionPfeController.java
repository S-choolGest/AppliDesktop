/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.etudiant.gestionpfe;

import Entite.Utilisateur.Utilisateur;
import Entite.pfe.Pfe;
import Services.Pfe.PfeService;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author TOSHIBA
 */
public class GestionPfeController implements Initializable {
    int userId;
    @FXML
    private Tab panAjouterPfe;
    @FXML
    private TextField tfTitre;
    @FXML
    private TextArea tfSujet;
    @FXML
    private Tab panModifierPfe;
    @FXML
    private Tab panAfficherPfe;
    private TableColumn<Pfe, String> colNomEtudiant;
    @FXML
    private TableColumn<Pfe, String> colTitre;
    @FXML
    private TableColumn<Pfe, String> colSujet;
    @FXML
    private TextField tfModifierTitre;
    @FXML
    private TextArea tfModfierSujet;
    @FXML
    private TableView<Pfe> tablePfe;
    @FXML
    private TextField tfIdPfe;
    @FXML
    private TableColumn<Pfe, String> colId;
    @FXML
    private TabPane panpfe;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        addButtonToTable();
        // TODO
        
    }    

    @FXML
    private void ajouterPfe(ActionEvent event) throws SQLException {
        String titre= tfTitre.getText();
        String sujet = tfSujet.getText();
        PfeService ps= new PfeService();
        
        Pfe p = new Pfe(userId, sujet, titre);/*a changer avec auth id */
        ps.ajouterPfe(p);
    }

    private void modifier (Pfe data)
    {    
          panpfe.getSelectionModel().select(panModifierPfe);
        tfIdPfe.setText(data.getsId());
         tfIdPfe.setDisable(true);
        
        tfModfierSujet.setText(data.getSujet());
        tfModifierTitre.setText(data.getTitre());
    }
    
    @FXML
    private void modifierPfe(ActionEvent event) throws SQLException {
        String sid =tfIdPfe.getText();
        int id = Integer.parseInt(sid);
        
        PfeService pes = new PfeService();
        String txtSujet = tfModfierSujet.getText();
        String txtTitre = tfModifierTitre.getText();
        Pfe p = pes.getPfe(id);
        pes.modifiePfe(p, txtSujet, txtTitre);
        
        
    }
    private void addButtonToTable() {
            PfeService ps = new PfeService();
        TableColumn<Pfe, Void> colBtn = new TableColumn("Action");
        TableColumn<Pfe, Void> colBtnS = new TableColumn("Action");
        colBtn.setMinWidth(140);
        colBtnS.setMinWidth(140);
        Callback<TableColumn<Pfe, Void>, TableCell<Pfe, Void>> cellFactory = new Callback<TableColumn<Pfe, Void>, TableCell<Pfe, Void>>() {
            @Override
            public TableCell<Pfe, Void> call(final TableColumn<Pfe, Void> param) {
                final TableCell<Pfe, Void> cell = new TableCell<Pfe, Void>() {

                    private final Button btn = new Button("modifier");
                    

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            Pfe data = getTableView().getItems().get(getIndex());
                            System.out.println("selectedData: " + data);
                            modifier(data);
                        });
                    
                       
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };
        
        Callback<TableColumn<Pfe, Void>, TableCell<Pfe, Void>> cellFactorySupp = new Callback<TableColumn<Pfe, Void>, TableCell<Pfe, Void>>() {
            @Override
            public TableCell<Pfe, Void> call(final TableColumn<Pfe, Void> param) {
                final TableCell<Pfe, Void> cell = new TableCell<Pfe, Void>() {

                    private final Button btnS = new Button("Supprimer");
                    

                    {
                        btnS.setOnAction((ActionEvent event) -> {
                            Pfe data = getTableView().getItems().get(getIndex());
                            System.out.println("selectedData: " + data);
                            try {
                                ps.supprimerPfe(data);
                                
                            } catch (SQLException ex) {
                                System.out.println(ex);
                            }
                            
                        });
                    
                       
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btnS);
                        }
                    }
                };
                return cell;
            }
        };
        
        colBtn.setCellFactory(cellFactory);
        colBtnS.setCellFactory(cellFactorySupp);
       
        tablePfe.getColumns().addAll(colBtn,colBtnS);
    }

    private void afficher(Event event) throws SQLException {
        colId.setCellValueFactory(c-> new SimpleStringProperty(c.getValue().getsId()));  
            colTitre.setCellValueFactory(c-> new SimpleStringProperty(c.getValue().getTitre()));
            colSujet.setCellValueFactory(c-> new SimpleStringProperty(c.getValue().getSujet()));  
            colNomEtudiant.setCellValueFactory(c-> new SimpleStringProperty(c.getValue().getNom_etudiant()));  
            PfeService ps = new PfeService();
            ObservableList<Pfe> list = ps.getMesPfe(userId);
            System.out.println(list);
            tablePfe.setItems(list);
    }   
     public void getInstance(Utilisateur o){
       this.userId = o.getId(); 
    }

    @FXML
    private void afficherPan(Event event) {
        try {  
            colId.setCellValueFactory(c-> new SimpleStringProperty(c.getValue().getsId()));
            colId.setVisible(false);
            colTitre.setCellValueFactory(c-> new SimpleStringProperty(c.getValue().getTitre()));
            colSujet.setCellValueFactory(c-> new SimpleStringProperty(c.getValue().getSujet()));
            /*colNomEtudiant.setCellValueFactory(c-> new SimpleStringProperty(c.getValue().getNom_etudiant())); */ 
            PfeService ps = new PfeService();
            ObservableList<Pfe> list = ps.getMesPfe(userId);
            System.out.println(list);
            tablePfe.setItems(list);
        } catch (SQLException ex) {
            Logger.getLogger(GestionPfeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

        
        
    
    

