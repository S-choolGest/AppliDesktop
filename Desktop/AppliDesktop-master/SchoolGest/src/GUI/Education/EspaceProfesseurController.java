/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Education;

import Entite.Education.Cours;
import Services.Education.ServiceCour;
import Services.Education.ServiceModule;
import Utils.DataBase;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ProtocolException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;
import rest.file.uploader.tn.FileUploader;

/**
 * FXML Controller class
 *
 * @author saghir
 */
public class EspaceProfesseurController implements Initializable {

    @FXML
    private TextField ajoutCoursField;
    @FXML
    private TableView<Cours> tableCoursProf;
    @FXML
    private TableColumn<Cours, String> chapitre;
    @FXML
    private TableColumn<Cours, String> col_upload_cours;
    ObservableList<Cours> listCours = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        chapitre.setCellValueFactory(new PropertyValueFactory<>("nomCours"));

        col_upload_cours = new TableColumn("Insérer cours");
        Callback<TableColumn<Cours, String>, TableCell<Cours, String>> cellFactory
                = //
                new Callback<TableColumn<Cours, String>, TableCell<Cours, String>>() {
                    @Override
                    public TableCell call(final TableColumn<Cours, String> param) {
                        final TableCell<Cours, String> cell = new TableCell<Cours, String>() {

                            final Button btn = new Button("Insérer");

                            @Override
                            public void updateItem(String item, boolean empty) {
                                super.updateItem(item, empty);
                                if (empty) {
                                    setGraphic(null);
                                    setText(null);
                                } else {
                                    btn.setOnAction(event -> {
                                        Cours cour = getTableView().getItems().get(getIndex());
                                        System.out.println(cour.getNomCours());
                                        FileChooser filechooser = new FileChooser();
                                        File file = filechooser.showOpenDialog(new Stage());
                                        if (file.isFile() && file.getName().contains(".pdf")) {
                                            try {
                                                System.out.println("hi!!!" + file);
                                                FileUploader fu = new FileUploader("localhost/sdev");
                                                String fileNameInServer = null;
                                                try {
                                                    fileNameInServer = fu.upload(file.toString());
                                                } catch (ProtocolException ex) {
                                                    Logger.getLogger(EspaceProfesseurController.class.getName()).log(Level.SEVERE, null, ex);
                                                } catch (IOException ex) {
                                                    Logger.getLogger(EspaceProfesseurController.class.getName()).log(Level.SEVERE, null, ex);
                                                }
                                                String path = "http://localhost/sdev/uploads/" + fileNameInServer;
                                                new File(path);

                                                String req = "select * from cours INNER join matiere on cours.idMatiere=matiere.idMatiere INNER JOIN professeur ON matiere.idProf=professeur.idProf";
                                                Statement ste = DataBase.getInstance().getConnection().createStatement();
                                                ResultSet rs = ste.executeQuery(req);
                                                while (rs.next()) {
                                                    File monFichier = new File("C:\\Users\\saghir\\Desktop\\tmp.pdf");
                                                    FileOutputStream ostreamFichier = new FileOutputStream(monFichier);
                                                    InputStream istreamFichier = rs.getBinaryStream("fichier");

                                                    byte[] buffer = new byte[1024];
                                                    int length;

                                                    while ((length = istreamFichier.read(buffer)) != -1) {
                                                        ostreamFichier.write(buffer, 0, length);
                                                    }
                                                    //listCours.add(new Cours(rs.getInt("idCours"), rs.getInt("idMatiere"), rs.getString("nomCours"), monFichier));

                                                    monFichier.delete();
                                                }

                                            } catch (SQLException ex) {
                                                Logger.getLogger(EspaceProfesseurController.class.getName()).log(Level.SEVERE, null, ex);
                                            } catch (FileNotFoundException ex) {
                                                Logger.getLogger(EspaceProfesseurController.class.getName()).log(Level.SEVERE, null, ex);
                                            } catch (IOException ex) {
                                                Logger.getLogger(EspaceProfesseurController.class.getName()).log(Level.SEVERE, null, ex);
                                            }
                                        }

                                    });
                                    setGraphic(btn);
                                    setText(null);
                                }
                            }
                        };
                        return cell;
                    }
                };
        col_upload_cours.setCellFactory(cellFactory);
        tableCoursProf.getColumns().add(col_upload_cours);
        try {
            tableCoursProf.getItems().setAll(new Services.Education.ServiceCour().getAllCours());
        } catch (IOException ex) {
            Logger.getLogger(EspaceProfesseurController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void ajouterCours(ActionEvent event) throws IOException {
        Cours md = new Cours();
        ServiceCour sm = new ServiceCour();
        md.setNomCours(ajoutCoursField.getText());
        
        ajoutCoursField.setText("Nouveau cours ajouté nommé " + ajoutCoursField.getText());
        ajoutCoursField.setText(null);
        tableCoursProf.getItems().clear();
        tableCoursProf.getItems().setAll(sm.getAllCours());
        FileChooser filechooser = new FileChooser();
        File file = filechooser.showOpenDialog(new Stage());
        if (file.isFile() && file.getName().contains(".pdf")) {

            System.out.println("hi!!!" + file);
            FileUploader fu = new FileUploader("localhost/sdev");
            String fileNameInServer = null;
            try {
                fileNameInServer = fu.upload(file.toString());
            } catch (ProtocolException ex) {
                Logger.getLogger(EspaceProfesseurController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(EspaceProfesseurController.class.getName()).log(Level.SEVERE, null, ex);
            }
            String path = "http://localhost/sdev/uploads/" + fileNameInServer;
            new File(path);
            
            sm.ajouter(md);

        }

    }
}
