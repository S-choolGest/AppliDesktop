
package GUI.ExtraScolaire;

import Extra_Scolaire.Eventt;
import Services.ExtraScolaire.ServiceEvent;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author MMD
 */
public class User_ExtraScolaireController implements Initializable {

    @FXML
    private AnchorPane body;
    
    @FXML
    private TableView<Eventt> Table;
    @FXML
    private TableColumn<Eventt, String> ColumnnomEvent;
    @FXML
    private TableColumn<Eventt, String> Columndescription;
    @FXML
    private TableColumn<Eventt, String> ColumnDate;
        ServiceEvent sa = new ServiceEvent();


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void cacher_note(MouseEvent event) {
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
}
