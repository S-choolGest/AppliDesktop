/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Bibliotheque;

import Entite.Bibliotheque.Bibliotheque;
import Entite.Bibliotheque.LivreEmprunte;
import Services.Bibliotheque.ServicesBibliotheque;
import Services.Bibliotheque.ServicesEmprunt;
import Services.Bibliotheque.ServicesEmprunteur;
import Services.Bibliotheque.ServicesLivreEmprunte;
import Services.Bibliotheque.ServicesLivres;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author william
 */
public class gerer_empruntController implements Initializable {

	@FXML
	private Label aucun_emprunt;
	@FXML
	private ScrollPane demandes_emprunt;
	@FXML
	private VBox liste_emprunt;
	@FXML
	private ChoiceBox<String> choix_tri_emprunt;
	@FXML
	private ChoiceBox<String> choix_etat_emprunt;
	private Stage stage;
	@FXML
	private JFXTextField search_emprunt;
	private ServicesBibliotheque ser_bib = new ServicesBibliotheque();
	private ServicesLivres ser = new ServicesLivres();
	private ServicesEmprunt ser_emp = new ServicesEmprunt();
	private ServicesEmprunteur ser_user = new ServicesEmprunteur();
	private ServicesLivreEmprunte ser_livre_emp = new ServicesLivreEmprunte();
	private Bibliotheque bib;
	private List<Boolean> ordre = new ArrayList<>();
	private String etat = "tout";
	@FXML
	private TextField search;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		for (int i = 0; i < 6; i++) {
			ordre.add(true);
		}
		aucun_emprunt.setVisible(false);
		choix_etat_emprunt.getItems().addAll("tout", "attente", "refus", "accepte", "rendu");
		choix_tri_emprunt.getItems().addAll("aucun", "titre", "auteur", "editeur", "datesortie", "categorie");
		choix_etat_emprunt.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				try {
					filterByEtat(choix_etat_emprunt.getItems().get((Integer) newValue));
					etat = choix_etat_emprunt.getItems().get((Integer) newValue);
				} catch (SQLException ex) {
					Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
		});

		choix_tri_emprunt.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				try {
					triEmprunts(bib.getEmail(), choix_tri_emprunt.getItems().get((Integer) newValue), ordre.get((int) newValue), etat);
//					if(ordre.get((int) newValue) == false) ordre.get((int) newValue) = true;
//					ordre.get((int) newValue) == !ordre.get((int) newValue);
				} catch (SQLException ex) {
					Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
		});
	}

	@FXML
	private void rechercher_emprunt(KeyEvent event) throws SQLException {
		afficher_page_demandes();
	}

	private void afficher_page_demandes() throws SQLException {
		aucun_emprunt.setVisible(false);
		liste_emprunt.getChildren().clear();
		List<LivreEmprunte> emprunts = new ArrayList<LivreEmprunte>();
//		emprunts = ser_emprunt.readAll();
		emprunts = ser_livre_emp.readAllinBibliotheque(bib.getEmail(), search_emprunt.getText());
		if (emprunts == null) {
			aucun_emprunt.setText("Vous n'êtes pas connecté à une bibliothèque!!!");
			aucun_emprunt.setVisible(true);
		} else {
			List<Node> node_emprunt = new ArrayList<>();
			for (LivreEmprunte e : emprunts) {
				try {
					FXMLLoader loader = new FXMLLoader();
					loader.setLocation(getClass().getResource("emprunt.fxml"));
					Parent n = (Parent) loader.load();
					EmpruntController emp = loader.getController();
					emp.init(e.getId_emprunt());
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

	private void filterByEtat(String etat) throws SQLException {
		liste_emprunt.getChildren().clear();
		aucun_emprunt.setVisible(false);
		List<LivreEmprunte> emprunts = new ArrayList<LivreEmprunte>();
//		emprunts = ser_emprunt.readAll();
//		emprunts = ser_livre_emp.readAllinBibliotheque(email.getText());
//		List<Emprunt> emprunts = new ArrayList<Emprunt>();
		emprunts = ser_livre_emp.filterByEtat(bib.getEmail(), etat, search_emprunt.getText());
		List<Node> node_emprunt = new ArrayList<>();
		if (emprunts == null) {
			aucun_emprunt.setVisible(true);
		} else {
			for (LivreEmprunte e : emprunts) {
				try {
					FXMLLoader loader = new FXMLLoader();
					loader.setLocation(getClass().getResource("emprunt.fxml"));
					Parent n = (Parent) loader.load();
					EmpruntController emp = loader.getController();
					emp.init(e.getId_emprunt());
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

	private void triEmprunts(String email, String type, Boolean ordre, String etat) throws SQLException {
		liste_emprunt.getChildren().clear();
		aucun_emprunt.setVisible(false);
		List<LivreEmprunte> emprunts = new ArrayList<LivreEmprunte>();
		System.out.println("ettt" + etat);
		emprunts = ser_livre_emp.tri(email, type, ordre, etat, search_emprunt.getText());
		List<Node> node_emprunt = new ArrayList<>();
		if (emprunts == null) {
			aucun_emprunt.setVisible(true);
		} else {
			for (LivreEmprunte e : emprunts) {
				try {
					FXMLLoader loader = new FXMLLoader();
					loader.setLocation(getClass().getResource("emprunt.fxml"));
					Parent n = (Parent) loader.load();
					EmpruntController emp = loader.getController();
					emp.init(e.getId_emprunt());
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

	public void getStage(Stage stage) {
		this.stage = stage;
	}

	public void init(Bibliotheque b) {
		this.bib = b;
		System.out.println(this.bib);
		if (this.bib != null) {
			try {
				aucun_emprunt.setVisible(false);
				afficher_page_demandes();
			} catch (SQLException ex) {
				Logger.getLogger(Catalogue_bibliothecaireController.class.getName()).log(Level.SEVERE, null, ex);
			}
		} else {
			aucun_emprunt.setVisible(true);
		}
	}
}
