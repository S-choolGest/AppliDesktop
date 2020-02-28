/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Absence;

import Entite.Education.Absence;
import Entite.Utilisateur.Enseignant;
import Entite.Utilisateur.Etudiant;
import Entite.Utilisateur.Utilisateur;
import Services.Education.ServiceAbsence;
import Services.Education.ServiceClasse;
import Services.Education.ServiceMatiere;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Separator;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author MehdiS
 */
public class AddAbsence1Controller implements Initializable {
    @FXML
    private ChoiceBox<String> TextMatiere;
    @FXML
    private ChoiceBox<String> TextEtudiant;
    @FXML
    private DatePicker DateAbs;
    @FXML
    private ChoiceBox<String> IDTime;
    @FXML
    private Button ValidBTN;
    @FXML
    private TableView<Absence> Table;
    @FXML
    private Tab AfficherAbs;

    
    @FXML
    private TableColumn<Absence, String> ColumnMatiere;
    @FXML
    private TableColumn<Absence, String> ColumnDate;
    @FXML
    private TableColumn<Absence, String> ColumnEtat;
    @FXML
    private TableColumn<Absence, String> ColumnIDEtu;
    
    ServiceAbsence sa = new ServiceAbsence();
    @FXML
    private TabPane TabGestion;
    @FXML
    private Tab ModifierAbs;
    @FXML
    private TextField TextIDEtudiantM;
    @FXML
    private ChoiceBox<String> ChoiceEtat;
    @FXML
    private Button BTNModifier;
   
    ObservableList<String> listM;
    ObservableList<String> listC;
    ObservableList<String> listCE;
    ObservableList<Timestamp> listJours= null ;
    Separator separator = new Separator();
    ObservableList<String> ETATS = FXCollections.observableArrayList("justifié", "non justifié");
    ObservableList<?> Time = FXCollections.observableArrayList("9:00:00", "10:45:00",separator,"13:30:00","15:00:00");
    ObservableList<String> Time2 = FXCollections.observableArrayList("9:00:00", "10:45:00","13:30:00","15:00:00");
    @FXML
    private BarChart<String, Integer> barchart;
    @FXML
    private NumberAxis yaxis;
    @FXML
    private CategoryAxis xAxis;
    @FXML
    private Tab showStat;
    @FXML
    private Button Rafraichir;
    @FXML
    private ChoiceBox<String> classeName;
    
    ServiceClasse sc = new ServiceClasse();
    Object o;
    ObservableList<Absence> list ;
    Absence data;
    @FXML
    private TableColumn<Absence, String> IDColumn;
    @FXML
    private TextField RechercheEtudiantAbs;
    @FXML
    private ChoiceBox<String> ChoiceJourS;
    
    
    int k = 0 ;
                 int f = 0;
                 int d = 0;
                 int e = 0;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
         try {
            listJours = sa.readAllS();
             ObservableList<String> list =FXCollections.observableArrayList("lundi", "mardi","mercredi","jeudi","vendredi","samedi");
        XYChart.Series<String , Integer> series  = new XYChart.Series<>();
        ChoiceJourS.setItems(list);
        ChoiceJourS.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
          @Override
             public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number t1) {
                 if(series.getData().size()!=0){
                     System.out.println("hiiii");
                       series.getData().clear();
                   
                       System.out.println(series.getData());
                    }
                
                  for(int i =0 ; i<listJours.size();i++){
                     String dayOfWeek = new SimpleDateFormat("EEEE").format(listJours.get(i).getTime());
                       String time = new SimpleDateFormat("HH:mm").format(listJours.get(i).getTime());
                       System.out.println(dayOfWeek);
                       System.out.println(time);
                  if(dayOfWeek.compareTo(list.get(t1.intValue())) == 0){
                    if(time.compareTo("9:00")==0){
                        k++;
                    }if(time.compareTo("10:45")==0){
                        f++;
                    } if(time.compareTo("13:30")==0){
                        d++;
                    } if (time.compareTo("15:00")==0){
                        e++;
                    }
                 }
                }
                  System.out.println(k);
                  System.out.println(f);
                  System.out.println(d);
                  series.getData().add(new XYChart.Data<>("09:00:00", k));
                  series.getData().add(new XYChart.Data<>("10:45:00", f));
                  series.getData().add(new XYChart.Data<>("13:30:00", d));
                   series.getData().add(new XYChart.Data<>("15:00:00", e));
                  
                   System.out.println("here"+series.getData()+"barchart ");
                   
                  
             }
         });
        
        
         barchart.getData().add(series);
         
        } catch (SQLException ex) {
            System.out.println(ex);
        }
       
         
         
         
         
       addButtonToTable(); 
       TabGestion.getTabs().remove(2);
        
      ServiceMatiere sm = new ServiceMatiere();
        try {
           listM = sm.readAll();
           TextMatiere.setItems(listM);
           IDTime.setItems((ObservableList<String>) Time);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        
        
        try {
           listC = sc.readAllS();
           classeName.setItems(listC);
           
           classeName.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
               @Override
               public void changed(ObservableValue<? extends Number> ov, Number t, Number t1) {
                   try{
                       System.out.println(listC.get(t1.intValue()));
                       listCE = sc.readNomEtudiantClasse(listC.get(t1.intValue()));
                       System.out.println(listCE);
                       TextEtudiant.setItems(listCE);
                    }catch (SQLException ex) {
                       System.out.println(ex);
                   }
               
               }
           });
        } catch (SQLException ex) {
            System.out.println(ex);
        }
       
          
    }    
/****************************appel Du Service ajouter en cliquant sur le bouton valider***************************************************/
    @FXML
    private void Ajouter(ActionEvent event) {
        try {
            String matiere = TextMatiere.getSelectionModel().getSelectedItem();
            String prenomP =TextEtudiant.getSelectionModel().getSelectedItem();
            int PrenomP = sa.readAbsStudent(prenomP);
            LocalDate date_abs=DateAbs.getValue();
            DateTimeFormatter dateFormatter=DateTimeFormatter.ofPattern("yyyy/MM/dd");
            String DateS = dateFormatter.format(date_abs);
            

            String time = IDTime.getSelectionModel().getSelectedItem();
            String DateTime = DateS +" "+time;
            System.out.println(DateTime);
            
            Absence p = new Absence(matiere,DateTime ,"non justifié",PrenomP);
            sa.ajouter(p);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    
    
 /**********************************Ajout boutons modifier et supprimer lors de l'affichage*****************************************/   
        private void addButtonToTable() {
        TableColumn<Absence, Void> colBtn = new TableColumn("Action");
        TableColumn<Absence, Void> colBtnS = new TableColumn("Action");
        colBtn.setMinWidth(140);
        colBtnS.setMinWidth(140);
        Callback<TableColumn<Absence, Void>, TableCell<Absence, Void>> cellFactory = new Callback<TableColumn<Absence, Void>, TableCell<Absence, Void>>() {
            @Override
            public TableCell<Absence, Void> call(final TableColumn<Absence, Void> param) {
                final TableCell<Absence, Void> cell = new TableCell<Absence, Void>() {

                    private final Button btn = new Button("Modifier");
                    

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            data = getTableView().getItems().get(getIndex());
                            System.out.println("selectedData: " + data);
                            TabGestion.getTabs().add(ModifierAbs);
                            ModifierAbsence(data);
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
        
        Callback<TableColumn<Absence, Void>, TableCell<Absence, Void>> cellFactorySupp = new Callback<TableColumn<Absence, Void>, TableCell<Absence, Void>>() {
            @Override
            public TableCell<Absence, Void> call(final TableColumn<Absence, Void> param) {
                final TableCell<Absence, Void> cell = new TableCell<Absence, Void>() {

                    private final Button btnS = new Button("Supprimer");
                    

                    {
                        btnS.setOnAction((ActionEvent event) -> {
                            Absence data = getTableView().getItems().get(getIndex());
                            System.out.println("selectedData: " + data);
                            try {
                                sa.delete(data);
                                
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
       
        Table.getColumns().addAll(colBtn,colBtnS);
    }
 /**********************************Ajout des valeurs reçu lors du clique sur le bouton modifier de l'affichage*****************************************/          
        private void ModifierAbsence(Absence data){
            
            TabGestion.getSelectionModel().select(ModifierAbs);
            TextIDEtudiantM.setText(data.getIdEtudiantS());
            ChoiceEtat.setValue(data.getetat());
            ChoiceEtat.setItems(ETATS);
            
            
        }

/**********************************modification dans la base de données*****************************************/   
    @FXML
    private void modifier(ActionEvent event) {
        try {
        int id =  data.getId();
        String matiere=  data.getMatiere();
        String Nometudiant = TextIDEtudiantM.getText();
        int IdEtudiant = sa.readAbsStudent(Nometudiant);
        String DateTime = data.getDate().substring(0, 19);
            System.out.println(DateTime);
        String etat = ChoiceEtat.getSelectionModel().getSelectedItem();

        Absence p = new Absence(id,matiere,DateTime ,etat,IdEtudiant);
        sa.update(p);
        
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        TabGestion.getTabs().remove(3);
    }
/**********************************Statistiques*****************************************/   
    private void compterNombreAbsenceParTemps(ObservableList<Timestamp> list2) {
           // xAxis.setCategories(Time2);
        
        
               
           
    }
            

    @FXML
    private void Rafraichir(ActionEvent event) {
    }


    

        
    
/*****************************Afficher etudiant avec cin********************************/
    @FXML
    private void RechercheEtudiantAbs(ActionEvent event) {
        try{ 
            IDColumn.setCellValueFactory(c-> new SimpleStringProperty(c.getValue().getIdS()));
            ColumnMatiere.setCellValueFactory(c-> new SimpleStringProperty(c.getValue().getMatiere()));
            ColumnDate.setCellValueFactory(c-> new SimpleStringProperty(c.getValue().getDate()));  
            ColumnEtat.setCellValueFactory(c-> new SimpleStringProperty(c.getValue().getetat()));
            ColumnIDEtu.setCellValueFactory(c-> new SimpleStringProperty(c.getValue().getIdEtudiantS()));
            ObservableList<Absence> list1 = sa.readEduAbs(RechercheEtudiantAbs.getText());
            list = list1;
            System.out.println(list1);
            IDColumn.setVisible(false);
            Table.setItems(list1);
         } catch (SQLException ex) {
            System.out.println(ex);
        }
        
    }
    
    public void getInstance(Utilisateur o){

        if(o.getType() == 0){
           afficherEtudiant(o);
        }else if (o.getType()==1){
        TabGestion.getTabs().remove(2);
        TabGestion.getTabs().remove(1);
        }else if (o.getType() == 4){
            afficherEtudiantParent(o);
        }


    }

    @FXML
    private void showStat(Event event) {
        
        
    }

      private void afficherEtudiant(Utilisateur o){
        try { 
            IDColumn.setCellValueFactory(c-> new SimpleStringProperty(c.getValue().getIdS()));
            ColumnMatiere.setCellValueFactory(c-> new SimpleStringProperty(c.getValue().getMatiere()));
            ColumnDate.setCellValueFactory(c-> new SimpleStringProperty(c.getValue().getDate()));  
            ColumnEtat.setCellValueFactory(c-> new SimpleStringProperty(c.getValue().getetat()));
            ColumnIDEtu.setCellValueFactory(c-> new SimpleStringProperty(c.getValue().getIdEtudiantS()));
            ObservableList<Absence> list = sa.readAllVEtudiant(o);
            System.out.println(list);
            IDColumn.setVisible(false);
            Table.setItems(list);
         } catch (SQLException ex) {
            System.out.println(ex);
        }
            TabGestion.getTabs().remove(0);
    }

    private void afficherEtudiantParent(Utilisateur o){
        try { 
            IDColumn.setCellValueFactory(c-> new SimpleStringProperty(c.getValue().getIdS()));
            ColumnMatiere.setCellValueFactory(c-> new SimpleStringProperty(c.getValue().getMatiere()));
            ColumnDate.setCellValueFactory(c-> new SimpleStringProperty(c.getValue().getDate()));  
            ColumnEtat.setCellValueFactory(c-> new SimpleStringProperty(c.getValue().getetat()));
            ColumnIDEtu.setCellValueFactory(c-> new SimpleStringProperty(c.getValue().getIdEtudiantS()));
            ObservableList<Absence> list = sa.readAllVEtudiantParent(o);
            System.out.println(list);
            IDColumn.setVisible(false);
            Table.setItems(list);
         } catch (SQLException ex) {
            System.out.println(ex);
        }
            TabGestion.getTabs().remove(0);
    }

    
    
}
    



