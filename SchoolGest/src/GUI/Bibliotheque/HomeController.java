/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Bibliotheque;

import Entite.Bibliotheque.Livre;
import Services.Bibliotheque.ServicesLivres;
import com.mysql.cj.result.LocalDateTimeValueFactory;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
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

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		error.setText("");
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
			Livre l = new Livre(id_bibliothequef, titre.getText(), editeur.getText(), auteur.getText(), categorie.getText(), datef, taillef, quantitef);
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
	}

	@FXML
	private void afficher_page_liste_livre(MouseEvent event) throws SQLException {
		refresh_view_livre("");
	}
//
//	private ObservableList<Livre> getLivreList() throws SQLException {
//		ObservableList<Livre> list = FXCollections.observableArrayList();
//		for (Livre l : ser.readAll()) {
//			list.add(l);
//		}
//		return list;
//	}

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

	@FXML
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
	}

	@FXML
	private void modifier_livre(ActionEvent event) {

		try {
			LocalDate datel = date_sortie2.getValue();
			DateTimeFormatter dateformat = DateTimeFormatter.ofPattern("yyyy/MM/dd");
			String datef = dateformat.format(datel);
			Livre l = new Livre(Integer.valueOf(id_livre2.getText()), Integer.valueOf(id_bibliotheque2.getText()), titre2.getText(), editeur2.getText(), auteur2.getText(), categorie2.getText(), datef, Integer.valueOf(taille2.getText()), Integer.valueOf(quantite2.getText()));
			if (l.getAuteur().equals("") || l.getCategorie().equals("") || l.getDateSortie().equals("") || l.getEditeur().equals("") || l.getTaille() < 5 || l.getTitre().equals("")) {
				error_modifier_livre.setText("Vérifier vos données !!!");
			} else {
				if (ser.update(l)) {
					refresh_view_livre("");
					page_ajout_livre.setVisible(false);
					page_liste_livre.setVisible(true);
					page_modifier_livre.setVisible(false);
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
		if (ser.delete(new Livre(Integer.valueOf(id_livre2.getText())))) {
			refresh_view_livre("");
			page_ajout_livre.setVisible(false);
			page_liste_livre.setVisible(true);
			page_modifier_livre.setVisible(false);
		}else{
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
}
