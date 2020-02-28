package GUI.ExtraScolaire;

import Entite.ExtraScolaire.Club;
import java.net.URL;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.Event;
import Entite.ExtraScolaire.Club;
import Services.ExtraScolaire.*;
import java.io.IOException;
import java.util.List;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Separator;
//import javafx.scene.control.Separator;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;



public class AddClubController implements Initializable {
  
	@FXML
    private TextField TextnomEvent;
    @FXML
    private TextField Textdescription;
    @FXML
    private TextField TextdescriptionM;
    @FXML
    private TextField TextIDEvent;
    private DatePicker DateClub;
    private DatePicker DateEvent;
    private ChoiceBox<String> IDTime;
    private ChoiceBox<String> modiftime;
    private Tab ModifierClub;
    @FXML
    private Button ValidBTN;
    @FXML
    private TableView<Club> Table;
    @FXML
    private Tab AfficherAbs;
    
    ServiceClub sa = new ServiceClub();
    @FXML
    private TableColumn<Club, String> ColumnnomEvent;
    @FXML
    private TableColumn<Club, String> Columndescription;
    
    
    
    ObservableList<String> list;
    ObservableList<String> list1 = null ;
    Separator separator = new Separator();
    ObservableList<?> Time = FXCollections.observableArrayList("9:00:00", "10:45:00","13:30:00","15:00:00");
    ObservableList<String> Time2 = FXCollections.observableArrayList("9:00:00", "10:45:00","13:30:00","15:00:00");
    @FXML
    private Tab ModifierEvent;
    @FXML
    private TabPane tabGestion;
    @FXML
    private TextField TextnomEventM;
    @FXML
    private TextField tf_nom_chcercher;
    @FXML
    private Button btn_chercher_guide;
    @FXML
    private Button btn_trier_guide;

    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {     addButtonToTable(); 
        //Separator separator = new Separator();
         

        
    } 
    @FXML
    private void Ajouter(ActionEvent event) {
    	
    	
    	
    	
        try {
            String nomClub = TextnomEvent.getText();
            String categorieClub = Textdescription.getText();
            System.out.println(categorieClub);
            Club p = new Club(nomClub,categorieClub);
            sa.ajouterclub(p);            

        } 
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

@FXML
private void afficher(Event event) {
    try { 
        ColumnnomEvent.setCellValueFactory(c-> new SimpleStringProperty(c.getValue().getNomClub()));
        Columndescription.setCellValueFactory(c-> new SimpleStringProperty(c.getValue().getCategorieClub())); 
         
       
        ObservableList<Club> list = sa.readAllV2();
        System.out.println(list);
        Table.setItems(list);
     } catch (SQLException ex) {
        System.out.println(ex);
    }
}

private void addButtonToTable() {
    TableColumn<Club, Void> colBtn = new TableColumn("Action");
    TableColumn<Club, Void> colBtnS = new TableColumn("Action");
    colBtn.setMinWidth(140);
    colBtnS.setMinWidth(140);
    Callback<TableColumn<Club, Void>, TableCell<Club, Void>> cellFactory = new Callback<TableColumn<Club, Void>, TableCell<Club, Void>>() {
        @Override
        public TableCell<Club, Void> call(final TableColumn<Club, Void> param) {
            final TableCell<Club, Void> cell = new TableCell<Club, Void>() 
                {

                private final Button btn = new Button("Modifier");          

                {
                    btn.setOnAction((ActionEvent event) -> {
                    	Club data = getTableView().getItems().get(getIndex());
                        System.out.println("selectedData: " + data);
                        ModifierEvent(data);
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
    
    Callback<TableColumn<Club, Void>, TableCell<Club, Void>> cellFactorySupp = new Callback<TableColumn<Club, Void>, TableCell<Club, Void>>() {
        @Override
        public TableCell<Club, Void> call(final TableColumn<Club, Void> param) {
            final TableCell<Club, Void> cell = new TableCell<Club, Void>() {

                private final Button btnS = new Button("Supprimer");
                {
                    btnS.setOnAction((ActionEvent event) -> {
                    	Club data = getTableView().getItems().get(getIndex());
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
    
    private void ModifierEvent(Club data)
       {    
        tabGestion.getSelectionModel().select(ModifierEvent);
        
        TextIDEvent.setText(data.getIdClub());
        TextIDEvent.setDisable(true);
      
        
        TextnomEventM.setText(data.getNomClub());
        
        TextdescriptionM.setText(data.getCategorieClub());
  
      
       
           
    }

    @FXML
    private void Modifier(ActionEvent event) throws SQLException {
   
        int id =  Integer.parseInt(TextIDEvent.getText());
        System.out.print(id);
         String nomClub = TextnomEventM.getText();
         System.out.print(nomClub);
            String Categorie = TextdescriptionM.getText();
       // int Idetudiant = Integer.parseInt(TextIDEtudiantM.getText());

     
           
        //sa.update(p);
                sa.update1(Categorie,nomClub,id);
        
      
    }

    @FXML
    private void chercherGuides(ActionEvent event) throws SQLException {
        ServiceClub sp = new ServiceClub();
        String nom=tf_nom_chcercher.getText();
           List<Club>list = sp.RECHERCHER(nom);
            ObservableList<Club> obs = FXCollections.observableArrayList(list);
          
                ColumnnomEvent.setCellValueFactory(new PropertyValueFactory<>("nomClub"));
                Columndescription.setCellValueFactory(new PropertyValueFactory<>("categorieClub"));
                
                Table.setItems(obs);
               
    }

    @FXML
    private void trierGuides(ActionEvent event) throws SQLException{
        ServiceClub sp = new ServiceClub();
           List<Club >list = sp.trier();
            ObservableList<Club> obs = FXCollections.observableArrayList(list);
         ColumnnomEvent.setCellValueFactory(new PropertyValueFactory<>("nomClub"));
                Columndescription.setCellValueFactory(new PropertyValueFactory<>("categorieClub"));
                
                Table.setItems(obs);
    }

   

    
    
   
}



    