/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Bibliotheque;

import Entite.Bibliotheque.Emprunt;
import Entite.Bibliotheque.Livre;
import Entite.Utilisateur.Utilisateur;
import Services.Bibliotheque.ServicesEmprunt;
import Services.Bibliotheque.ServicesLivres;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javax.swing.text.DateFormatter;

/**
 *
 * @author william
 */
public class HomeController implements Initializable {

	@FXML
	private TextField editeur;
	@FXML
	private TextField auteur;
	@FXML
	private TextField titre;
	@FXML
	private Button ajouter;
	@FXML
	private TextField categorie;
	@FXML
	private DatePicker date_sortie;
	@FXML
	private TextField quantite;
	@FXML
	private TextField id_bibliotheque;
	@FXML
	private TextField taille;
	@FXML
	private Label error;
	@FXML
	private AnchorPane biblio;
	@FXML
	private TableView<Livre> list_livre;
	@FXML
	private TableColumn<Livre, Integer> identifiant1;
	@FXML
	private TableColumn<Livre, String> titre1;
	@FXML
	private TableColumn<Livre, String> auteur1;
	@FXML
	private TableColumn<Livre, String> editeur1;
	@FXML
	private TableColumn<Livre, String> categorie1;
	@FXML
	private TableColumn<Livre, String> date_sortie1;
	@FXML
	private TableColumn<Livre, Integer> taille1;
	@FXML
	private TableColumn<Livre, Integer> quantite1;
	@FXML
	private Pane btn_page_ajout_livre;
	@FXML
	private Pane btn_page_liste_livre;
	@FXML
	private Pane page_ajout_livre;
	@FXML
	private Pane page_liste_livre;

	ServicesLivres ser = new ServicesLivres();
	@FXML
	private TextField field_rechercher_livre;
	@FXML
	private Pane btn_page_modifier_livre;
	@FXML
	private Pane page_modifier_livre;
	@FXML
	private TextField id_bibliotheque2;
	@FXML
	private TextField titre2;
	@FXML
	private TextField auteur2;
	@FXML
	private TextField editeur2;
	@FXML
	private TextField categorie2;
	@FXML
	private TextField taille2;
	@FXML
	private DatePicker date_sortie2;
	@FXML
	private TextField quantite2;
	@FXML
	private Button modifier_livre;
	@FXML
	private Label error1;
	@FXML
	private Button supprimer_livre;
	@FXML
	private Pane page_id_modifier_livre;
	@FXML
	private TextField id_livre2;
	@FXML
	private Button btn_chercher_livre;
	@FXML
	private Label erreur_id_livre;
	@FXML
	private Pane page_modifier_livre1;
	@FXML
	private Label error_modifier_livre;
	@FXML
	private Pane btn_page_demandes;
	@FXML
	private Pane page_demandes_emprunt;
	@FXML
	private ScrollPane demandes_emprunt;
	private ServicesEmprunt ser_emprunt = new ServicesEmprunt();
	@FXML
	private VBox liste_emprunt;
	@FXML
	private ImageView btn_modifier_compte;
	@FXML
	private ImageView profil;
	@FXML
	private Label nom;
	@FXML
	private Label email;
	@FXML
	private Label id_user;
	@FXML
	private ChoiceBox<String> choix_tri_emprunt;
	@FXML
	private ChoiceBox<String> choix_etat_emprunt;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		choix_etat_emprunt.getItems().addAll("tout", "attente", "refuse", "accepte", "rendu");
		choix_tri_emprunt.getItems().addAll("aucun", "titre", "auteur", "editeur", "datesortie", "categorie");
	}

	@FXML
	private void ajout_livre(ActionEvent event) {
		try {
			LocalDate datel = date_sortie.getValue();
			DateTimeFormatter dateformat = DateTimeFormatter.ofPattern("yyyy/MM/dd");
			String datef = dateformat.format(datel);
			int taillef = Integer.valueOf(taille.getText());
			int quantitef = Integer.valueOf(quantite.getText());
			int id_bibliothequef = Integer.valueOf(id_bibliotheque.getText());
			Livre l = new Livre(id_bibliothequef, titre.getText(), editeur.getText(), auteur.getText(), categorie.getText(), datef, taillef, quantitef, null, null);
			ser.ajouter(l);
		} catch (Exception e) {
			error.setText(e.getMessage());
		}
	}

	@FXML
	private void afficher_page_ajout_livre(MouseEvent event) {
		page_ajout_livre.setVisible(true);
		page_liste_livre.setVisible(false);
		page_modifier_livre.setVisible(false);
		page_demandes_emprunt.setVisible(false);
	}

	@FXML
	private void afficher_page_liste_livre(MouseEvent event) throws SQLException {
		refresh_view_livre("");
	}

	private ObservableList<Livre> getLivreList(String input_text) throws SQLException {
		ObservableList<Livre> list = FXCollections.observableArrayList();
		for (Livre l : ser.search(input_text)) {
			list.add(l);
		}
		return list;
	}

	private void refresh_view_livre(String input_text) throws SQLException {
		page_ajout_livre.setVisible(false);
		page_liste_livre.setVisible(true);
		page_modifier_livre.setVisible(false);
		page_demandes_emprunt.setVisible(false);
		ObservableList<Livre> list = getLivreList(input_text);
		identifiant1.setCellValueFactory(new PropertyValueFactory<Livre, Integer>("id"));
		titre1.setCellValueFactory(new PropertyValueFactory<Livre, String>("titre"));
		auteur1.setCellValueFactory(new PropertyValueFactory<Livre, String>("auteur"));
		editeur1.setCellValueFactory(new PropertyValueFactory<Livre, String>("editeur"));
		categorie1.setCellValueFactory(new PropertyValueFactory<Livre, String>("categorie"));
		date_sortie1.setCellValueFactory(new PropertyValueFactory<Livre, String>("dateSortie"));
		taille1.setCellValueFactory(new PropertyValueFactory<Livre, Integer>("taille"));
		quantite1.setCellValueFactory(new PropertyValueFactory<Livre, Integer>("quantite"));
		list_livre.setItems(list);
	}

	@FXML
	private void refresh_list_livre(KeyEvent event) throws SQLException {
		refresh_view_livre(field_rechercher_livre.getText());
	}

	private void refresh_list_livre(InputMethodEvent event) throws SQLException {
		refresh_view_livre(field_rechercher_livre.getText());
	}

	@FXML
	private void afficher_page_modifier_livre(MouseEvent event) {
		page_ajout_livre.setVisible(false);
		page_liste_livre.setVisible(false);
		page_modifier_livre.setVisible(true);
		page_id_modifier_livre.setVisible(true);
		page_modifier_livre1.setVisible(false);
		page_demandes_emprunt.setVisible(false);
	}

	@FXML
	private void modifier_livre(ActionEvent event) {

		try {
			LocalDate datel = date_sortie2.getValue();
			DateTimeFormatter dateformat = DateTimeFormatter.ofPattern("yyyy/MM/dd");
			String datef = dateformat.format(datel);
			Livre l = new Livre(Integer.valueOf(id_livre2.getText()), Integer.valueOf(id_bibliotheque2.getText()), titre2.getText(), editeur2.getText(), auteur2.getText(), categorie2.getText(), datef, Integer.valueOf(taille2.getText()), Integer.valueOf(quantite2.getText()), null, null);
			if (l.getAuteur().equals("") || l.getCategorie().equals("") || l.getDateSortie().equals("") || l.getEditeur().equals("") || l.getTaille() < 5 || l.getTitre().equals("")) {
				error_modifier_livre.setText("Vérifier vos données !!!");
			} else {
				if (ser.update(l)) {
					refresh_view_livre("");
					page_ajout_livre.setVisible(false);
					page_liste_livre.setVisible(true);
					page_modifier_livre.setVisible(false);
					page_demandes_emprunt.setVisible(false);
				} else {
					error_modifier_livre.setText("Impossible de modifer le livre " + l.getId());
				}
			}
		} catch (Exception e) {
			error_modifier_livre.setText("Vérifier vos données !!!(quantite, bibliotheque id et pages doivent être des nombres)");
		}

	}

	@FXML
	private void supprimer_livre(ActionEvent event) throws SQLException {
		if (ser.delete(Integer.valueOf(id_livre2.getText()))) {
			refresh_view_livre("");
			page_ajout_livre.setVisible(false);
			page_liste_livre.setVisible(true);
			page_modifier_livre.setVisible(false);
			page_demandes_emprunt.setVisible(false);
		} else {
			error_modifier_livre.setText("Echec de suppression du livre " + id_livre2.getText());
		}
	}

	@FXML
	private void chercher_livre(ActionEvent event) throws SQLException {
		try {
			int id = Integer.valueOf(id_livre2.getText());
			Livre l = ser.search(id);
			if (l == null) {
				erreur_id_livre.setText("Identifiant de livre incorrect !!!");
			} else {
				page_ajout_livre.setVisible(false);
				page_liste_livre.setVisible(false);
				page_modifier_livre.setVisible(true);
				page_id_modifier_livre.setVisible(false);
				page_modifier_livre1.setVisible(true);
				page_demandes_emprunt.setVisible(false);
				id_bibliotheque2.setText(String.valueOf(l.getId_bibliotheque()));
				taille2.setText(String.valueOf(l.getTaille()));
				quantite2.setText(String.valueOf(l.getQuantite()));
				titre2.setText(l.getTitre());
				auteur2.setText(l.getAuteur());
				editeur2.setText(l.getEditeur());
				categorie2.setText(l.getCategorie());
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				LocalDate localDate = LocalDate.parse(l.getDateSortie(), formatter);
				date_sortie2.setValue(localDate);
			}
		} catch (Exception e) {
			erreur_id_livre.setText("Veillez un nombre comme identifiant");
		}

	}

	@FXML
	private void afficher_page_demandes(MouseEvent event) throws SQLException {
		page_ajout_livre.setVisible(false);
		page_liste_livre.setVisible(false);
		page_modifier_livre.setVisible(false);
		page_demandes_emprunt.setVisible(true);
		liste_emprunt.getChildren().clear();
		List<Emprunt> emprunts = new ArrayList<Emprunt>();
		emprunts = ser_emprunt.readAll();
		List<Node> node_emprunt = new ArrayList<>();
		for (Emprunt e : emprunts) {
			try {
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(getClass().getResource("emprunt.fxml"));
				Parent n = (Parent) loader.load();
				EmpruntController emp = loader.getController();
				emp.init(e.getId());

//				AnchorPane n = FXMLLoader.load(getClass().getResource("emprunt.fxml"));
//				AnchorPane n = FXMLLoader.lo
//				n.getChildren().getClass().getResource();
				node_emprunt.add(n);
			} catch (IOException ex) {
				Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
		for (Node n : node_emprunt) {
			liste_emprunt.getChildren().add(n);
		}
	}

	@FXML
	private void modifier_compte(MouseEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("update_account.fxml"));
		Parent n = (Parent) loader.load();
		Update_accountController del = loader.getController();
		del.setId(id_user.getText());
		Stage stage = new Stage();
		stage.setTitle("Edutech : Bibliotheque : Gestion : Edit account");
		Scene scene = new Scene(n);
		stage.setResizable(false);
//        scene.setFill(javafx.scene.paint.Color.TRANSPARENT);
		stage.setScene(scene);
		stage.show();
	}
	public void setProfil(Utilisateur u){
		nom.setText(u.getNom()+" "+u.getPrenom());
		email.setText(u.getEmail());
		if(u.getProfil() != null)
			profil.setImage(new Image(u.getProfil()));
		id_user.setText(String.valueOf(u.getId()));
	}

	@FXML
	private void tri_par_choix_colonne(ContextMenuEvent event) {
		
	}

	@FXML
	private void tri_par_choix_etat(MouseEvent event) throws SQLException {
		page_ajout_livre.setVisible(false);
		page_liste_livre.setVisible(false);
		page_modifier_livre.setVisible(false);
		page_demandes_emprunt.setVisible(true);
		liste_emprunt.getChildren().clear();
		List<Emprunt> emprunts = new ArrayList<Emprunt>();
		emprunts = ser_emprunt.filterByEtat(choix_etat_emprunt.getValue());
		List<Node> node_emprunt = new ArrayList<>();
		for (Emprunt e : emprunts) {
			try {
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(getClass().getResource("emprunt.fxml"));
				Parent n = (Parent) loader.load();
				EmpruntController emp = loader.getController();
				emp.init(e.getId());

//				AnchorPane n = FXMLLoader.load(getClass().getResource("emprunt.fxml"));
//				AnchorPane n = FXMLLoader.lo
//				n.getChildren().getClass().getResource();
				node_emprunt.add(n);
			} catch (IOException ex) {
				Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
		for (Node n : node_emprunt) {
			liste_emprunt.getChildren().add(n);
		}
	}
}
