package GUI.Education;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import Entite.Education.*;
import Services.Education.*;
import Utils.DataBase;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaErrorEvent;
import javafx.scene.media.MediaException;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;
import schoolgest.SchoolGest;

/**
 * FXML Controller class
 *
 * @author saghir
 */
public class FXMLControllerEtudiant implements Initializable {

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @FXML
    private TableView<Cours> tableRecherche;
    @FXML
    private TableColumn<Cours, String> chapitreRecherche;
    @FXML
    private TextField rechercheCoursField;
    @FXML
    private TreeView<String> etudiantTreeView = new TreeView<>();
    private TableColumn<Cours, String> col_telecharger;
    ObservableList<Cours> treeListCours = FXCollections.observableArrayList();

    private TableColumn<Cours, String> col_regarder_video;
    ObservableList<Specialite> oblistSpecialite = FXCollections.observableArrayList();
    ObservableList<Module> oblistModule = FXCollections.observableArrayList();
    ObservableList<Matiere> oblistMatiere = FXCollections.observableArrayList();
    ServiceSpecialite sp = new ServiceSpecialite();
    ServiceMatiere sma = new ServiceMatiere();
    // Create the Area for Logging
    private TextArea messageArea = new TextArea();
    @FXML
    AnchorPane anchor;
    @FXML
    private TabPane tabPane;
    @FXML
    private AnchorPane acceuilAnchor;
    @FXML
    private AnchorPane acceuilAnchor1;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            anchor.setVisible(false);

            col_telecharger = new TableColumn("Telecharger cours");
            col_regarder_video = new TableColumn<>("Regarder cours");
            Callback<TableColumn<Cours, String>, TableCell<Cours, String>> cellFactory
                    = //
                    new Callback<TableColumn<Cours, String>, TableCell<Cours, String>>() {
                        @Override
                        public TableCell call(final TableColumn<Cours, String> param) {
                            final TableCell<Cours, String> cell = new TableCell<Cours, String>() {

                                final Button btn = new Button("Telecharger");

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
                                            try {
                                                new ServiceCour().telechargerFichier(cour);
                                            } catch (SQLException | IOException ex) {
                                                Logger.getLogger(PopUpFichierController.class.getName()).log(Level.SEVERE, null, ex);
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

            Callback<TableColumn<Cours, String>, TableCell<Cours, String>> cellFactory1
                    = //
                    new Callback<TableColumn<Cours, String>, TableCell<Cours, String>>() {
                        @Override
                        public TableCell call(final TableColumn<Cours, String> param) {
                            final TableCell<Cours, String> cell = new TableCell<Cours, String>() {

                                final Button btn = new Button("Regarder");

                                @Override
                                public void updateItem(String item, boolean empty) {
                                    super.updateItem(item, empty);
                                    if (empty) {
                                        setGraphic(null);
                                        setText(null);
                                    } else {
                                        btn.setOnAction(event -> {

                                            // Locate the media content in the CLASSPATH
                                            URL mediaUrl = getClass().getResource("Partie3__Connexion_avec_BDD_Mysql_sous_JavaFX(720p).mp4");
                                            String mediaStringUrl = mediaUrl.toExternalForm();

                                            // Create a Media
                                            final Media media = new Media(mediaStringUrl);

                                            // Create a Media Player
                                            final MediaPlayer player = new MediaPlayer(media);
                                            // Automatically begin the playback
                                            player.setAutoPlay(true);

                                            // Create a 400X300 MediaView
                                            final MediaView mediaView = new MediaView(player);
                                            mediaView.setFitWidth(400);
                                            mediaView.setFitHeight(300);
                                            mediaView.setSmooth(true);

                                            // Create the DropShadow effect
                                            DropShadow dropshadow = new DropShadow();
                                            dropshadow.setOffsetY(5.0);
                                            dropshadow.setOffsetX(5.0);
                                            dropshadow.setColor(Color.WHITE);

                                            mediaView.setEffect(dropshadow);

                                            // Create the Buttons
                                            Button playButton = new Button("Play");
                                            Button stopButton = new Button("Stop");

                                            // Create the Event Handlers for the Button
                                            playButton.setOnAction(new EventHandler<ActionEvent>() {
                                                public void handle(ActionEvent event) {
                                                    if (player.getStatus() == MediaPlayer.Status.PLAYING) {
                                                        player.stop();
                                                        player.play();
                                                    } else {
                                                        player.play();
                                                    }
                                                }
                                            });

                                            stopButton.setOnAction(new EventHandler<ActionEvent>() {
                                                public void handle(ActionEvent event) {
                                                    player.stop();
                                                }
                                            });

                                            // Create Handlers for handling Errors
                                            player.setOnError(new Runnable() {
                                                public void run() {
                                                    // Handle asynchronous error in Player object.
                                                    printMessage(player.getError());
                                                }
                                            });

                                            media.setOnError(new Runnable() {
                                                public void run() {
                                                    // Handle asynchronous error in Media object.
                                                    printMessage(media.getError());
                                                }
                                            });

                                            mediaView.setOnError(new EventHandler<MediaErrorEvent>() {
                                                public void handle(MediaErrorEvent event) {
                                                    // Handle asynchronous error in MediaView.
                                                    printMessage(event.getMediaError());
                                                }
                                            });

                                            // Add a ChangeListener to the player
                                            player.statusProperty().addListener(new ChangeListener<MediaPlayer.Status>() {
                                                // Log the Message
                                                public void changed(ObservableValue<? extends MediaPlayer.Status> ov,
                                                        final MediaPlayer.Status oldStatus, final MediaPlayer.Status newStatus) {
                                                    messageArea.appendText("\nStatus changed from " + oldStatus + " to " + newStatus);
                                                }
                                            });

                                            // Add a Handler for PLAYING status
                                            player.setOnPlaying(new Runnable() {
                                                public void run() {
                                                    messageArea.appendText("\nPlaying now");
                                                }
                                            });

                                            // Add a Handler for STOPPED status
                                            player.setOnStopped(new Runnable() {
                                                public void run() {
                                                    messageArea.appendText("\nStopped now");
                                                }
                                            });

                                            // Create the HBox
                                            HBox controlBox = new HBox(5, playButton, stopButton);

                                            // Create the VBox
                                            VBox root = new VBox(5, mediaView, controlBox, messageArea);

                                            // Set the Style-properties of the HBox
                                            root.setStyle("-fx-padding: 10;"
                                                    + "-fx-border-style: solid inside;"
                                                    + "-fx-border-width: 2;"
                                                    + "-fx-border-insets: 5;"
                                                    + "-fx-border-radius: 5;"
                                                    + "-fx-border-color: blue;");

                                            Stage stage = new Stage();
                                            // Create the Scene
                                            Scene scene = new Scene(root);
                                            // Add the scene to the Stage
                                            stage.setScene(scene);
                                            // Set the title of the Stage
                                            stage.setTitle("Séance Cours");
                                            // Display the Stage
                                            stage.show();

                                        });
                                        setGraphic(btn);
                                        setText(null);
                                    }
                                }
                            };
                            return cell;
                        }
                    };
            col_regarder_video.setCellFactory(cellFactory1);
            col_telecharger.setCellFactory(cellFactory);
            chapitreRecherche.setCellValueFactory(new PropertyValueFactory<>("nomCours"));
            tableRecherche.getColumns().add(col_telecharger);
            tableRecherche.getColumns().add(col_regarder_video);

            ServiceModule smo = new ServiceModule();
            TreeItem<String> specialite = new TreeItem<>("Specialités");
            oblistSpecialite = sp.getAllSpecialite();
            oblistModule = smo.getAllModule();
            oblistMatiere = sma.getAllMatiere();

            TreeItem<String> tm;
            TreeItem<String> tm1;
            for (Specialite specialitee : oblistSpecialite) {
                tm = new TreeItem<>(specialitee.getNomSpecialite().toUpperCase());
                specialite.getChildren().addAll(tm);
                for (Module module : oblistModule) {
                    tm1 = new TreeItem<>(module.getNomModule().toUpperCase());
                    if (specialitee.getIdSpecialite() == module.getIdSpecialite()) {

                        tm.getChildren().addAll(tm1);
                    }
                    for (Matiere matiere : sma.getAllMatiere()) {
                        if (module.getIdModule() == matiere.getIdModule()) {

                            tm1.getChildren().addAll(new TreeItem<>(matiere.getNomMatiere().toUpperCase()));
                        }
                    }
                }
            }

            etudiantTreeView.setRoot(specialite);
            EventHandler<MouseEvent> mouseEventHandle = (MouseEvent event) -> {
                handleMouseClicked(event);
            };
            etudiantTreeView.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEventHandle);
        } catch (SQLException ex) {
            Logger.getLogger(FXMLControllerEtudiant.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void handleMouseClicked(MouseEvent event) {
        Node node = event.getPickResult().getIntersectedNode();
        if (node instanceof Text || (node instanceof TreeCell && ((TreeCell) node).getText() != null)) {

            String st = (String) ((TreeItem) etudiantTreeView
                    .getSelectionModel()
                    .getSelectedItem())
                    .getValue();
            new PopUpFichierController().setSelectedTreeElement(st);
            ResultSet rs;
            ObservableList<Cours> test = FXCollections.observableArrayList();
            for (Matiere c : oblistMatiere) {
                if (c.getNomMatiere().toLowerCase().equals(st.toLowerCase())) {
                    try {
                        String req = "select * from cours where idMatiere='" + c.getIdMatiere() + "'";
                        Statement ste = DataBase.getInstance().getConnection().createStatement();
                        rs = ste.executeQuery(req);
                        ObservableList<Cours> ca = FXCollections.observableArrayList();
                        while (rs.next()) {
                            File monFichier = new File("C:\\Users\\saghir\\Desktop\\tmp.pdf");
                            FileOutputStream ostreamFichier = new FileOutputStream(monFichier);
                            InputStream istreamFichier = rs.getBinaryStream("fichier");

                            byte[] buffer = new byte[1024];
                            int length;

                            while ((length = istreamFichier.read(buffer)) != -1) {
                                ostreamFichier.write(buffer, 0, length);
                            }
                            ca.add(new Cours(rs.getInt("idCours"), rs.getInt("idMatiere"), rs.getString("nomCours"), monFichier));

                            monFichier.delete();
                        }
                        this.setTreeListCours(ca);
anchor.setVisible(true);
                        anchor.getChildren().clear();
                        FXMLLoader loader = new FXMLLoader();
                        loader.setLocation(getClass().getResource("../Education/popUpFichier.fxml"));
                        Parent n = (Parent) loader.load();
                        GUI.Education.PopUpFichierController emp = loader.getController();
                        anchor.getChildren().add(n);

                    } catch (SQLException ex) {
                        Logger.getLogger(FXMLControllerEtudiant.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(FXMLControllerEtudiant.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }

    }

    private void printMessage(MediaException error) {
        MediaException.Type errorType = error.getType();
        String errorMessage = error.getMessage();
        messageArea.appendText("\n" + "Type:" + errorType + ", error mesage:" + errorMessage);
    }

    @FXML
    public void rechercheCoursValider() throws SQLException, IOException {
        ServiceCour svc = new ServiceCour();
        ObservableList<Cours> obs = FXCollections.observableArrayList();
        obs = svc.rechercheCours(rechercheCoursField.getText());
        if (obs.size() != 0) {

            tableRecherche.getItems().setAll(obs);

        }
    }

    

    public void setTreeListCours(ObservableList<Cours> a) {
        this.treeListCours = a;
    }
}
