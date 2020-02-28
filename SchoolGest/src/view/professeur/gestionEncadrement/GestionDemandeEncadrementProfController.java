/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.professeur.gestionEncadrement;

import Entite.Utilisateur.Utilisateur;
import Entite.pfe.DemandeEncadrement;
import Services.Pfe.DemandeEncadrementService;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author TOSHIBA
 */
public class GestionDemandeEncadrementProfController implements Initializable {

    @FXML
    private TableView<DemandeEncadrement> tableDemandeEncadrementProf;
    @FXML
    private TableColumn<DemandeEncadrement, String> colTitre;
    @FXML
    private TableColumn<DemandeEncadrement, String> colSujet;
    @FXML
    private TableColumn<DemandeEncadrement, String> colEtat;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
           
        try {
            DemandeEncadrementService des =new DemandeEncadrementService();
            colTitre.setCellValueFactory(c->new SimpleStringProperty(c.getValue().getTitre()));
            colSujet.setCellValueFactory(c->new SimpleStringProperty(c.getValue().getSujet()));
            colEtat.setCellValueFactory(c->new SimpleStringProperty(c.getValue().getEtat()));
            ObservableList<DemandeEncadrement> list =des.getMesDemandesProf(19);/*a changer avec auth().id*/
            tableDemandeEncadrementProf.setItems(list);
            
            addButtonToTable();
            
            
            // TODO
            
            // TODO
        } catch (SQLException ex) {
            Logger.getLogger(GestionDemandeEncadrementProfController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
private void addButtonToTable(){
    DemandeEncadrementService des = new DemandeEncadrementService();
        TableColumn<DemandeEncadrement, Void> colBtnA = new TableColumn("Action");
        TableColumn<DemandeEncadrement, Void> colBtnR = new TableColumn("Action");
        colBtnA.setMinWidth(140);
        colBtnR.setMinWidth(140);
        Callback<TableColumn<DemandeEncadrement, Void>, TableCell<DemandeEncadrement, Void>> cellFactorySupp = new Callback<TableColumn<DemandeEncadrement, Void>, TableCell<DemandeEncadrement, Void>>() {
            @Override
            public TableCell<DemandeEncadrement, Void> call(final TableColumn<DemandeEncadrement, Void> param) {
                final TableCell<DemandeEncadrement, Void> cell = new TableCell<DemandeEncadrement, Void>() {

                    private final Button btnS = new Button("Accepter");
                    

                    {
                        btnS.setOnAction((ActionEvent event) -> {
                            DemandeEncadrement data = getTableView().getItems().get(getIndex());
                            System.out.println("selectedData: " + data);
                            try {
                                des.AccepterDemande(data);
                                
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
        Callback<TableColumn<DemandeEncadrement, Void>, TableCell<DemandeEncadrement, Void>> cellFactory = new Callback<TableColumn<DemandeEncadrement, Void>, TableCell<DemandeEncadrement, Void>>() {
            @Override
            public TableCell<DemandeEncadrement, Void> call(final TableColumn<DemandeEncadrement, Void> param) {
                final TableCell<DemandeEncadrement, Void> cell = new TableCell<DemandeEncadrement, Void>() {

                    private final Button btnS = new Button("Refuser");
                    

                    {
                        btnS.setOnAction((ActionEvent event) -> {
                            DemandeEncadrement data = getTableView().getItems().get(getIndex());
                            System.out.println("selectedData: " + data);
                            try {
                                des.refuserDemande(data);
                                
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
        colBtnA.setCellFactory(cellFactorySupp);
        colBtnR.setCellFactory(cellFactory);
        tableDemandeEncadrementProf.getColumns().addAll(colBtnA,colBtnR);

    }    
    
}
