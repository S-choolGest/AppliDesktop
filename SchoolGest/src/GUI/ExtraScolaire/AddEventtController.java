package GUI.ExtraScolaire;

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
import Extra_Scolaire.Eventt;
import Services.ExtraScolaire.*;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
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
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;

import mail.MailApp;
import javax.swing.JFrame;
import mapa.Mapa;



public class AddEventtController implements Initializable {
  
	@FXML
    private TextField TextnomEvent;
    @FXML
    private TextField Textdescription;
    @FXML
    private TextField TextdescriptionM;
    @FXML
    private TextField TextIDEvent;
    @FXML
    private DatePicker DateEventt;
    @FXML
    private DatePicker DateEvent;
    @FXML
    private ChoiceBox<String> IDTime;
    @FXML
    private ChoiceBox<String> modiftime;
    private Tab ModifierEventt;
    @FXML
    private Button ValidBTN;
    @FXML
    private TableView<Eventt> Table;
    @FXML
    private Tab AfficherAbs;
    ServiceEvent sa = new ServiceEvent();
    @FXML
    private TableColumn<Eventt, String> ColumnnomEvent;
    @FXML
    private TableColumn<Eventt, String> ColumnDate;
    @FXML
    private TableColumn<Eventt, String> Columndescription;
    
    
    
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
    private Button btn_mail;
    @FXML
    private ImageView imageEvents;
    @FXML
    private TextField tf_nom_chcercher;
    @FXML
    private Tab AfficherAbs1;
    @FXML
    private AnchorPane maps;

    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {     addButtonToTable(); 
        //Separator separator = new Separator();
         ObservableList<?> Time = FXCollections.observableArrayList("9:00:00", "10:00:00","11:00:00","13:00:00","14:00:00","15:00:00","16:00:00","17:00:00");
         ObservableList<?> Time2 = FXCollections.observableArrayList("9:00:00", "10:00:00","11:00:00","13:00:00","14:00:00","15:00:00","16:00:00","17:00:00");

         IDTime.setItems((ObservableList<String>) Time);
         modiftime.setItems((ObservableList<String>) Time2);
    } 
    @FXML
    private void Ajouter(ActionEvent event) {
    	
    	System.out.println("DateTime");
    	
    	
        try {
            String nomEvent = TextnomEvent.getText();
            String description = Textdescription.getText();
            LocalDate date_abs=DateEvent.getValue();
            DateTimeFormatter dateFormatter=DateTimeFormatter.ofPattern("yyyy/MM/dd");
            String DateS = dateFormatter.format(date_abs);
            String time = IDTime.getSelectionModel().getSelectedItem();
            String DateTime = DateS +" "+time;
            System.out.println(nomEvent);
            System.out.println(description);
            System.out.println(DateTime);
            Eventt p = new Eventt(nomEvent,description, DateTime);
            sa.ajouterEvent(p);            

          /* FXMLLoader loader = new FXMLLoader
                        (getClass()
                         .getResource("AffichePersonne.fxml"));
            try {
                Parent root = loader.load();
                AffichePersonneController apc = loader.getController();
                apc.setResNom(tfNom);
                apc.setResPrenom(tfPrenom);
                tfNom.getScene().setRoot(root);
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
                }*/
        } 
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

@FXML
private void afficher(Event event) {
    try { 
        ColumnnomEvent.setCellValueFactory(c-> new SimpleStringProperty(c.getValue().getNomEvent()));
        Columndescription.setCellValueFactory(c-> new SimpleStringProperty(c.getValue().getDescription())); 
        ColumnDate.setCellValueFactory(c-> new SimpleStringProperty(c.getValue().getDatet()));  
       
        ObservableList<Eventt> list = sa.readAllV2();
        System.out.println(list);
        Table.setItems(list);
     } catch (SQLException ex) {
        System.out.println(ex);
    }
}

private void addButtonToTable() {
    TableColumn<Eventt, Void> colBtn = new TableColumn("Action");
    TableColumn<Eventt, Void> colBtnS = new TableColumn("Action");
    colBtn.setMinWidth(140);
    colBtnS.setMinWidth(140);
    Callback<TableColumn<Eventt, Void>, TableCell<Eventt, Void>> cellFactory = new Callback<TableColumn<Eventt, Void>, TableCell<Eventt, Void>>() {
        @Override
        public TableCell<Eventt, Void> call(final TableColumn<Eventt, Void> param) {
            final TableCell<Eventt, Void> cell = new TableCell<Eventt, Void>() 
                {

                private final Button btn = new Button("Modifier");          

                {
                    btn.setOnAction((ActionEvent event) -> {
                    	Eventt data = getTableView().getItems().get(getIndex());
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
    
    Callback<TableColumn<Eventt, Void>, TableCell<Eventt, Void>> cellFactorySupp = new Callback<TableColumn<Eventt, Void>, TableCell<Eventt, Void>>() {
        @Override
        public TableCell<Eventt, Void> call(final TableColumn<Eventt, Void> param) {
            final TableCell<Eventt, Void> cell = new TableCell<Eventt, Void>() {

                private final Button btnS = new Button("Supprimer");
                {
                    btnS.setOnAction((ActionEvent event) -> {
                    	Eventt data = getTableView().getItems().get(getIndex());
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
    
    private void ModifierEvent(Eventt data)
       {    

        tabGestion.getSelectionModel().select(ModifierEvent);
        
        TextIDEvent.setText(data.getIdEvent());
        TextIDEvent.setDisable(true);
      
        
        TextnomEventM.setText(data.getNomEvent());
        
        TextdescriptionM.setText(data.getDescription());
  
        
        String dateTime = ( data.getDatet()).substring(0,10);
        Date DataDate = null;
        try {
            DataDate = new SimpleDateFormat("yyyy-MM-dd").parse(dateTime);
        } catch (ParseException ex) {
            System.out.println(ex);
        }
        System.out.println("ffffe");
        LocalDate localDate = DataDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        System.out.println(localDate);
        LocalDate DateLocal = localDate;
        DateEventt.setValue(DateLocal);
        
        String dateTime2 = data.getDatet().substring(11,19);
        modiftime.setValue(dateTime2);
        modiftime.setItems((ObservableList<String>) Time );
           
    }

    @FXML
    private void Modifier(ActionEvent event) {
         try {
        int id =  Integer.parseInt(TextIDEvent.getText());
        System.out.print(id);
         String nomEvent = TextnomEventM.getText();
         System.out.print(nomEvent);
            String description = TextdescriptionM.getText();
      

        LocalDate date_abs=DateEventt.getValue();
        DateTimeFormatter dateFormatter=DateTimeFormatter.ofPattern("yyyy/MM/dd");
        String DateS = dateFormatter.format(date_abs);

        //String etat =ChoiceEtat.getSelectionModel().getSelectedItem();
        String time = modiftime.getSelectionModel().getSelectedItem();
        String DateTime = DateS+" "+time;
        
        System.out.println(DateTime);


           
        //sa.update(p);
                sa.update1(description,nomEvent,DateTime,id);
        
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    
    @FXML
    private void oo(Event event) {
        // TODO Auto-generated method stub
		final Mapa example = new Mapa("test");
		example.generateMarker(maps.getCenter());
    }
    
    
   @FXML
    private void envoyer_mail(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader
                      (getClass()
                        .getResource("AddEvent2.fxml"));
        MailApp app = new MailApp();
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       app.setSize(800,400);
      app.setVisible(true);
    }

}



    