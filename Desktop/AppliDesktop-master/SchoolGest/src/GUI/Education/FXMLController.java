package GUI.Education;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Entite.Education.*;
import Services.Education.*;
import Utils.*;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.converter.DefaultStringConverter;
import schoolgest.*;

/**
 * FXML Controller class
 *
 * @author saghir
 */
public class FXMLController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TableView<Specialite> tableSpecialite;
    @FXML
    private TableView<Module> tableModule;
    @FXML
    private TableView<Matiere> tableMatiere;
    @FXML
    private TableColumn<Matiere, String> nomMatiere;
    @FXML
    private TableColumn<Matiere, Integer> coefMatiere;
    @FXML
    private TableColumn<Module, String> nomModule;
    @FXML
    private TableColumn<Module, Integer> coefModule;

    @FXML
    private TextField ajoutField;
    @FXML
    private TextField ajoutModuleField;
    @FXML
    private Label ajoutMatiereLabel;
    @FXML
    private Label affecterModuleLabel;
    @FXML
    private TextField ajoutcoefMatiereField;
    @FXML
    private TextField ajoutMatiereField;
    @FXML
    private Button affecterMatiereAuModule;
    @FXML
    private ComboBox comboModule;
    @FXML
    private ComboBox comboModuleSpecialite;
    @FXML
    private ComboBox comboSpecialite;
    @FXML
    private ComboBox comboMatiere;

    @FXML
    private Label ajoutLabel;
    @FXML
    private Label ajoutModuleLabel;

    @FXML
    private Button affecterModuleAuSpecialite;

    private TableColumn<Specialite, String> col_nom;
    private TableColumn<Specialite, String> col_supprimer;
    private TableColumn<Module, String> col_supprimer_module;
    private TableColumn<Matiere, String> col_supprimer_matiere;
    private ObservableList<Matiere> enumMatiere;
    ServiceMatiere sma = new ServiceMatiere();
    ServiceSpecialite ssp = new ServiceSpecialite();

//    ObservableList<User> oblist = FXCollections.observableArrayList();
    ObservableList<Specialite> oblist = FXCollections.observableArrayList();
    ServiceSpecialite as = new ServiceSpecialite();
    ObservableList<String> nomDesMatieres;
    ObservableList<Module> oblistModule = FXCollections.observableArrayList();
    @FXML
    private TabPane tabPane;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        nomMatiere.setCellValueFactory(new PropertyValueFactory<>("nomMatiere"));
        coefMatiere.setCellValueFactory(new PropertyValueFactory<>("coefMatiere"));
        nomModule.setCellValueFactory(new PropertyValueFactory<>("nomModule"));
        coefModule.setCellValueFactory(new PropertyValueFactory<>("coefModule"));
        try {
            ServiceModule sm = new ServiceModule();
            oblist = as.getAllSpecialite();
            enumMatiere = FXCollections.observableArrayList();
            enumMatiere = sma.getAllMatiere();
            nomDesMatieres = FXCollections.observableArrayList();
            enumMatiere.stream().map((m) -> {
                comboMatiere.getItems().add(m.getNomMatiere());
                return m;
            }).forEach((m) -> {
                nomDesMatieres.add(m.getNomMatiere());
            });

            oblistModule = sm.getAllModule();

            oblistModule.stream().forEach((md) -> {
                comboModule.getItems().add(md.getNomModule());
            });

            oblist.stream().forEach((md) -> {
                comboSpecialite.getItems().add(md.getNomSpecialite());
            });
            oblistModule.stream().forEach((md) -> {
                comboModuleSpecialite.getItems().add(md.getNomModule());
            });
//            TreeItem<String> tm;
//            TreeItem<String> tm1;
//            for (Specialite specialite : ssp.getAllSpecialite()) {
//                tm = new TreeItem<>(specialite.getNomSpecialite().toUpperCase());
//                modules.getChildren().add(tm);
//                for (Module module : sm.getAllModule()) {
//                    if (specialite.getIdSpecialite() == module.getIdSpecialite()) {
//
//                        tm.getChildren().add(new TreeItem<>(module.getNomModule().toUpperCase() + "\n                         Coefficient = " + module.getCoefModule()));
//                    }
//                }
//            }

            initTable();

            //-----------------TABLE VIEW------------------
            tableSpecialite.setItems(oblist);
            //tableSpecialite.getItems().addAll(oblist);
            tableMatiere.setItems(enumMatiere);
            tableModule.getItems().setAll(sm.getAllModule());
            //-----------------TREE VIEW-------------------
        } catch (SQLException ex) {
            Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }

        //-------------------------------------------------- ESPACE ETUDIANT -----------------------------------------------
    }

    public void initTable() throws SQLException {
        ServiceModule smo = new ServiceModule();
        col_supprimer = new TableColumn("Supprimer");
        col_supprimer_module = new TableColumn("Supprimer");
        col_supprimer_matiere = new TableColumn("Supprimer");
        col_nom = new TableColumn("Specialité");

        Callback<TableColumn<Matiere, String>, TableCell<Matiere, String>> cellFactory1
                = //
                new Callback<TableColumn<Matiere, String>, TableCell<Matiere, String>>() {
                    @Override
                    public TableCell call(final TableColumn<Matiere, String> param) {
                        final TableCell<Matiere, String> cell = new TableCell<Matiere, String>() {

                            final Button btn = new Button("supprimer");

                            @Override
                            public void updateItem(String item, boolean empty) {
                                super.updateItem(item, empty);
                                if (empty) {
                                    setGraphic(null);
                                    setText(null);
                                } else {
                                    btn.setOnAction(event -> {
                                        Matiere matiere = getTableView().getItems().get(getIndex());
                                        System.out.println(matiere.getNomMatiere());
                                        sma.supprimer(matiere);
                                        try {
                                            smo.calculCoefModule();
                                        } catch (SQLException ex) {
                                            Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                        tableMatiere.getItems().clear();
                                        tableMatiere.getItems().setAll(sma.getAllMatiere());

                                    });
                                    setGraphic(btn);
                                    setText(null);
                                }
                            }
                        };
                        return cell;
                    }
                };

        Callback<TableColumn<Module, String>, TableCell<Module, String>> cellFactory2
                = //
                new Callback<TableColumn<Module, String>, TableCell<Module, String>>() {
                    @Override
                    public TableCell call(final TableColumn<Module, String> param) {
                        final TableCell<Module, String> cell = new TableCell<Module, String>() {

                            final Button btn = new Button("supprimer");

                            @Override
                            public void updateItem(String item, boolean empty) {
                                super.updateItem(item, empty);
                                if (empty) {
                                    setGraphic(null);
                                    setText(null);
                                } else {
                                    btn.setOnAction(event -> {
                                        Module module = getTableView().getItems().get(getIndex());
                                        System.out.println(module.getNomModule());
                                        try {
                                            new ServiceModule().supprimer(module);
                                            tableModule.getItems().clear();
                                            tableModule.getItems().setAll(new ServiceModule().getAllModule());
                                            System.out.println("Module supprimée");
                                            tableModule.getItems().clear();
                                            tableModule.getItems().setAll(new ServiceModule().getAllModule());
                                            comboModule.getItems().clear();
                                            comboModule.getItems().setAll(new ServiceModule().getAllModule());
                                            comboModuleSpecialite.getItems().clear();
                                            comboModuleSpecialite.getItems().setAll(new ServiceModule().getAllModule());
                                        } catch (SQLException ex) {
                                            Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                        try {
                                            smo.calculCoefModule();
                                        } catch (SQLException ex) {
                                            Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
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

        Callback<TableColumn<Specialite, String>, TableCell<Specialite, String>> cellFactory
                = //
                new Callback<TableColumn<Specialite, String>, TableCell<Specialite, String>>() {
                    @Override
                    public TableCell call(final TableColumn<Specialite, String> param) {
                        final TableCell<Specialite, String> cell = new TableCell<Specialite, String>() {

                            final Button btn = new Button("supprimer");

                            @Override
                            public void updateItem(String item, boolean empty) {
                                super.updateItem(item, empty);
                                if (empty) {
                                    setGraphic(null);
                                    setText(null);
                                } else {
                                    btn.setOnAction(event -> {
                                        Specialite specialite = getTableView().getItems().get(getIndex());
                                        System.out.println(specialite.getNomSpecialite());
                                        ssp.supprimer(specialite.getIdSpecialite());
                                        tableSpecialite.getItems().clear();
                                        tableSpecialite.getItems().setAll(ssp.getAllSpecialite());
                                        try {
                                            for (Module module : new ServiceModule().getAllModule()) {
                                                if (module.getIdSpecialite() == specialite.getIdSpecialite()) {
                                                    module.setIdSpecialite(0);
                                                    (new ServiceModule()).modifier(module.getIdModule(), module);
                                                }

                                            }
                                        } catch (SQLException ex) {
                                            Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                        ObservableList<String> str = FXCollections.observableArrayList();
                                        ssp.getAllSpecialite().stream().forEach((specialit) -> {
                                            str.add(specialit.getNomSpecialite());
                                        });
                                        comboSpecialite.getItems().clear();
                                        comboSpecialite.setItems(str);

                                    });
                                    setGraphic(btn);
                                    setText(null);
                                }
                            }
                        };
                        return cell;
                    }
                };

        col_nom.setCellValueFactory(new PropertyValueFactory<>("nomSpecialite"));
        tableSpecialite.getColumns().add(col_nom);
        col_supprimer_matiere.setCellFactory(cellFactory1);
        col_supprimer_module.setCellFactory(cellFactory2);
        tableModule.getColumns().add(col_supprimer_module);
        tableMatiere.getColumns().add(col_supprimer_matiere);
        col_supprimer.setCellFactory(cellFactory);
        tableSpecialite.getColumns().add(col_supprimer);

        editableCols();

    }

    private void editableCols() {
        col_nom.setCellFactory(ComboBoxTableCell.forTableColumn(new DefaultStringConverter(), nomDesMatieres));

        col_nom.setOnEditCommit(e -> {
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setNomSpecialite(e.getNewValue());
            final int row = tableSpecialite.getEditingCell().getRow();
            System.out.println("_______________" + row);
            String nom = tableSpecialite.getItems().get(row).getNomSpecialite();
            int id = tableSpecialite.getItems().get(row).getIdSpecialite();
            as = new ServiceSpecialite();
            as.modifier(id, nom);
        });

        tableSpecialite.setEditable(true);

    }

    @FXML
    public boolean ajouterSpecialite() {
        boolean ok = true;
        Specialite sp = new Specialite();
        ajoutLabel.setText("Nouvelle specialité ajouté");
        if (ajoutField.getText().contains("'") || ajoutField.getText().contains("\"") || ajoutField.getText().length() > 30 || ajoutField.getText().length() == 0) {
            ajoutField.setText("");
            ajoutLabel.setText("Entrer un nom de specialité valide");
            ok = false;

        } else {
            for (Specialite string : oblist) {
                if (ajoutField.getText().toLowerCase().equals(string.getNomSpecialite().toLowerCase())) {
                    ajoutField.setText("");
                    System.out.println("La spécialité existe deja");
                    ajoutLabel.setText("La spécialité existe deja");
                    ok = false;

                }

            }

        }

        if (ok == true) {

            sp.setNomSpecialite(ajoutField.getText());
            as.ajouter(sp);

            ajoutField.setText("");
            tableSpecialite.getItems().setAll(ssp.getAllSpecialite());
            ObservableList<String> str = FXCollections.observableArrayList();
            ssp.getAllSpecialite().stream().forEach((specialit) -> {
                str.add(specialit.getNomSpecialite());
            });
            comboSpecialite.getItems().clear();
            comboSpecialite.setItems(str);

        } else if (ok == false) {
            System.out.println("Error");
            return false;
        }

        return true;
    }

    @FXML
    public void affecterMatiereAuModule() throws SQLException {
        String selectedModule = (String) comboModule.getSelectionModel().getSelectedItem();

        ResultSet rs;
        String req = "select idModule from module where nomModule='" + selectedModule + "'";
        Statement st = DataBase.getInstance().getConnection().createStatement();
        rs = st.executeQuery(req);

        String selectedMatiere = (String) comboMatiere.getSelectionModel().getSelectedItem();
        String requeteUpdate
                = "UPDATE matiere SET idModule = ?  " + "WHERE nomMatiere='" + selectedMatiere + "'";
        PreparedStatement pst;
        pst = DataBase.getInstance().getConnection().prepareStatement(requeteUpdate);
        int m = 0;
        while (rs.next()) {
            m = rs.getInt(1);
        }
        pst.setInt(1, m);

        pst.executeUpdate();
        (new ServiceModule()).calculCoefModule();
        Parent root;

        try {
            root = FXMLLoader.load(getClass().getResource("../coursInterfaces/template.fxml"));
            affecterMatiereAuModule.getScene().setRoot(root);

        } catch (IOException ex) {
            Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    public boolean ajouterModule() throws SQLException {
        boolean ok = true;
        Module md = new Module();
        if (ajoutModuleField.getText().contains("'") || ajoutModuleField.getText().contains("\"") || ajoutModuleField.getText().length() > 30 || ajoutModuleField.getText().length() == 0) {
            ajoutModuleField.setText("");
            ajoutModuleLabel.setText("Entrer un nom de module valide");
            ok = false;

        } else {
            for (Module string : oblistModule) {
                if (ajoutModuleField.getText().toLowerCase().equals(string.getNomModule().toLowerCase())) {
                    ajoutModuleField.setText("");
                    System.out.println("Le module existe deja");
                    ajoutModuleLabel.setText("Le module existe deja");
                    ok = false;

                }

            }

        }

        if (ok == true) {
            ServiceModule sm = new ServiceModule();
            md.setNomModule(ajoutModuleField.getText());
            sm.ajouter(md);
            ajoutModuleLabel.setText("Nouvelle module ajouté nommé " + ajoutModuleField.getText());
            ajoutModuleField.setText(null);
            tableModule.getItems().clear();
            tableModule.getItems().setAll(new ServiceModule().getAllModule());
            comboModule.getItems().clear();
            comboModule.getItems().setAll(new ServiceModule().getAllModule());
            comboModuleSpecialite.getItems().clear();
            comboModuleSpecialite.getItems().setAll(new ServiceModule().getAllModule());

        } else if (ok == false) {
            System.out.println("Error");
            return false;
        }

        return true;
    }

    @FXML
    public boolean ajouterMatiere() throws SQLException {
        boolean ok = true;
        Matiere sp = new Matiere();

        if (ajoutMatiereField.getText().contains("'") || ajoutMatiereField.getText().contains("\"") || ajoutMatiereField.getText().length() > 30 || ajoutMatiereField.getText().length() == 0 || ajoutcoefMatiereField.getText().contains("'") || ajoutcoefMatiereField.getText().contains("\"") || ajoutcoefMatiereField.getText().length() > 30 || ajoutcoefMatiereField.getText().length() == 0) {
            ajoutMatiereField.setText("");
            ajoutMatiereLabel.setText("Entrer un nom de matière valide");
            ok = false;

        } else {
            for (Matiere string : enumMatiere) {
                if (ajoutMatiereField.getText().toLowerCase().equals(string.getNomMatiere().toLowerCase())) {
                    ajoutMatiereField.setText("");
                    System.out.println("La matière existe deja");
                    ajoutLabel.setText("La matière existe deja");
                    ok = false;

                }

            }

        }

        if (ok == true) {

            sp.setNomMatiere(ajoutMatiereField.getText());
            sp.setCoefMatiere(Integer.parseInt(ajoutcoefMatiereField.getText()));
            sma.ajouter(sp);
            ajoutMatiereLabel.setText("Nouvelle matière ajouté");
            tableMatiere.getItems().clear();
            tableMatiere.getItems().addAll(sma.getAllMatiere());
            comboModule.getItems().clear();
            for (Module module : new ServiceModule().getAllModule()) {
                comboModule.getItems().setAll(module.getNomModule());
            }
            comboModuleSpecialite.getItems().clear();
            for (Module module : new ServiceModule().getAllModule()) {
                comboModuleSpecialite.getItems().setAll(module.getNomModule());
            }

            ajoutMatiereField.setText(null);
            ajoutcoefMatiereField.setText(null);

        } else if (ok == false) {
            System.out.println("Error");
            return false;
        }

        return true;
    }

    @FXML
    public void affecterModuleAuSpecialite() throws SQLException {
        String selectedSpecialite = (String) comboSpecialite.getSelectionModel().getSelectedItem();

        ResultSet rs;
        String req = "select idSpecialite from specialite where nomSpecialite='" + selectedSpecialite + "'";
        Statement st = DataBase.getInstance().getConnection().createStatement();
        rs = st.executeQuery(req);

        String selectedModule = (String) comboModuleSpecialite.getSelectionModel().getSelectedItem().toString();
        String requeteUpdate
                = "UPDATE module SET idSpecialite = ?  " + "WHERE nomModule='" + selectedModule + "'";
        PreparedStatement pst;
        pst = DataBase.getInstance().getConnection().prepareStatement(requeteUpdate);
        int m = 0;
        while (rs.next()) {
            m = rs.getInt(1);
        }
        pst.setInt(1, m);

        pst.executeUpdate();
        affecterModuleLabel.setText("La module " + selectedModule + " a été affecté au specialité " + selectedSpecialite);
        comboModule.getItems().clear();
        for (Module module : new ServiceModule().getAllModule()) {
            comboModule.getItems().setAll(module.getNomModule());
        }
        comboModuleSpecialite.getItems().clear();
        for (Module module : new ServiceModule().getAllModule()) {
            comboModuleSpecialite.getItems().setAll(module.getNomModule());
        }
    }

    
}
