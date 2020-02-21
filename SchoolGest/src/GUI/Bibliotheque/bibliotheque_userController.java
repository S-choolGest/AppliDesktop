/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Bibliotheque;

import Entite.Bibliotheque.Emprunt;
import Entite.Bibliotheque.Livre;
import Entite.Bibliotheque.LivreEmprunte;
import Entite.Utilisateur.Utilisateur;
import Services.Bibliotheque.ServicesEmprunt;
import Services.Bibliotheque.ServicesEmprunteur;
import Services.Bibliotheque.ServicesLivreEmprunte;
import Services.Bibliotheque.ServicesLivres;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author william
 */
public class bibliotheque_userController implements Initializable {

	private ServicesLivres ser = new ServicesLivres();
	private ServicesEmprunt ser_emp = new ServicesEmprunt();
	private ServicesEmprunteur ser_user = new ServicesEmprunteur();
	private ServicesLivreEmprunte ser_livre = new ServicesLivreEmprunte();
	@FXML
	private AnchorPane biblio;
	@FXML
	private Pane btn_catalogue;
	@FXML
	private Pane btn_emprunts;
	@FXML
	private VBox catalogues;
	@FXML
	private Pane page_catalogue;
	@FXML
	private ScrollPane page_liste_livre;
	@FXML
	private Pane page_detail;
	@FXML
	private Label identifiant;
	@FXML
	private Label editeur;
	@FXML
	private Label titre;
	@FXML
	private Label categorie;
	@FXML
	private Pane emprunter;
	@FXML
	private Label auteur;
	@FXML
	private Label taille;
	@FXML
	private Label quantite;
	@FXML
	private Label datesortie;
	@FXML
	private Label dateajout;
	@FXML
	private Pane img_livre;
	@FXML
	private Label error_emprunter;
	@FXML
	private Pane page_emprunts;
	@FXML
	private ScrollPane demandes_emprunt;
	@FXML
	private VBox liste_emprunt;
	@FXML
	private Pane btn_reservation;
	@FXML
	private TextField search_emprunt;
	@FXML
	private ChoiceBox<String> choix_tri_emprunt;
	@FXML
	private ChoiceBox<String> choix_etat_emprunt;
	@FXML
	private Label id_user;
	@FXML
	private ImageView profil;
	@FXML
	private Label nom;
	@FXML
	private Label email;
	@FXML
	private ImageView btn_modifier_compte;

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO
		page_catalogue.setVisible(true);
		page_liste_livre.setVisible(true);
		page_detail.setVisible(false);
		page_emprunts.setVisible(false);
		choix_etat_emprunt.getItems().addAll("tout", "attente", "refuse", "accepte", "rendu");
		choix_tri_emprunt.getItems().addAll("aucun", "titre", "auteur", "editeur", "datesortie", "categorie");
	}

	@FXML
	private void afficher_page_catalogue(MouseEvent event) throws SQLException {
		catalogues.setSpacing(30);
		page_catalogue.setVisible(true);
		page_liste_livre.setVisible(true);
		page_detail.setVisible(false);
		page_emprunts.setVisible(false);
		List<Livre> livres = ser.readAll();
		int i = 4;
		catalogues.getChildren().clear();
		List<HBox> lines = new ArrayList<HBox>();
		HBox line_livre = new HBox(40);
		lines.add(line_livre);
		int j = 0;
		for (Livre l : livres) {
			if (i == 0) {
				i = 4;
				line_livre = new HBox(40);
				lines.add(line_livre);
				j++;
			}
			VBox livre = new VBox();
			Image img = new Image(l.getImg());
			ImageView imgv = new ImageView();
			imgv.setImage(img);
			imgv.setFitWidth(200);
			imgv.setFitHeight(200);
			Button show = new Button("afficher détail");
			Label titre = new Label(l.getTitre() + " est un livre " + l.getCategorie() + " de " + l.getAuteur());
			livre.getChildren().add(imgv);
//			livre.getChildren().add(titre);
			livre.getChildren().add(show);
			show.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					error_emprunter.setText("");
					page_detail.setVisible(true);
					titre.setText(l.getTitre());
					identifiant.setText(" Id : " + String.valueOf(l.getId()));
					taille.setText(String.valueOf(l.getTaille()));
					quantite.setText(String.valueOf(l.getQuantite()));
					auteur.setText(l.getAuteur());
					editeur.setText(l.getEditeur());
					categorie.setText(l.getCategorie());
					datesortie.setText(l.getDateSortie());
					dateajout.setText(l.getDateajout());
					Image img = new Image(l.getImg());
					ImageView imgv = new ImageView();
					imgv.setImage(img);
					imgv.setFitWidth(200);
					imgv.setFitHeight(400);
					img_livre.getChildren().add(imgv);
					page_liste_livre.setOnMouseClicked(new EventHandler<MouseEvent>() {
						@Override
						public void handle(MouseEvent event) {
							page_detail.setVisible(false);
						}
					});
					emprunter.setOnMouseClicked(new EventHandler<MouseEvent>() {
						@Override
						public void handle(MouseEvent event) {
							try {
								error_emprunter.setText(ser_emp.emprunter(Integer.valueOf(id_user.getText()), l.getId()));
							} catch (SQLException ex) {
								Logger.getLogger(bibliotheque_userController.class.getName()).log(Level.SEVERE, null, ex);
							}
						}
					});
				}
			});
			imgv.setOnMouseClicked(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					page_detail.setVisible(true);
					titre.setText(l.getTitre());
					identifiant.setText(" Id : " + String.valueOf(l.getId()));
					taille.setText(String.valueOf(l.getTaille()));
					quantite.setText(String.valueOf(l.getQuantite()));
					auteur.setText(l.getAuteur());
					editeur.setText(l.getEditeur());
					categorie.setText(l.getCategorie());
					datesortie.setText(l.getDateSortie());
					dateajout.setText(l.getDateajout());
					Image img = new Image(l.getImg());
					ImageView imgv = new ImageView();
					imgv.setImage(img);
					imgv.setFitWidth(200);
					imgv.setFitHeight(400);
					img_livre.getChildren().add(imgv);
					page_liste_livre.setOnMouseClicked(new EventHandler<MouseEvent>() {
						@Override
						public void handle(MouseEvent event) {
							page_detail.setVisible(false);
						}
					});
				}
			});
			lines.get(j).getChildren().add(livre);
			i--;
		}
		for (HBox hb : lines) {
			catalogues.getChildren().add(hb);
		}
	}

	@FXML
	private void afficher_page_reservation(MouseEvent event) {
	}

	@FXML
	private void rechercher_emprunt(KeyEvent event) throws SQLException {
		page_catalogue.setVisible(false);
		page_emprunts.setVisible(true);
		liste_emprunt.getChildren().clear();
		List<LivreEmprunte> emprunts = new ArrayList<>();
		emprunts = ser_livre.search(Integer.valueOf(id_user.getText()), search_emprunt.getText());
		List<Node> node_emprunt = new ArrayList<>();
		for (LivreEmprunte e : emprunts) {
			try {
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(getClass().getResource("emprunt_user.fxml"));
				Parent n = (Parent) loader.load();
				Emprunt_userController emp = loader.getController();
				emp.init(e);
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
	private void tri_par_choix_colonne(ContextMenuEvent event) {
		
	}

	@FXML
	private void tri_par_choix_etat(ContextMenuEvent event) {

	}

	public void setProfil(Utilisateur u) {
		nom.setText(u.getNom() + " " + u.getPrenom());
		email.setText(u.getEmail());
		if (u.getProfil() != null) {
			profil.setImage(new Image(u.getProfil()));
		}
		id_user.setText(String.valueOf(u.getId()));
	}

	@FXML
	private void modifier_compte(MouseEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("update_account.fxml"));
		Parent n = (Parent) loader.load();
		Update_accountController del = loader.getController();
		del.setId(id_user.getText());
		Stage stage = new Stage();
		stage.setTitle("Edutech : Bibliotheque : Etudiant : Edit account");
		Scene scene = new Scene(n);
		stage.setResizable(false);
//        scene.setFill(javafx.scene.paint.Color.TRANSPARENT);
		stage.setScene(scene);
		stage.show();
	}

	@FXML
	private void afficher_page_emprunts(MouseEvent event) throws SQLException {
		page_catalogue.setVisible(false);
		page_emprunts.setVisible(true);
		liste_emprunt.getChildren().clear();
		List<LivreEmprunte> emprunts = new ArrayList<>();
		emprunts = ser_livre.search(Integer.valueOf(id_user.getText()));
		List<Node> node_emprunt = new ArrayList<>();
		for (LivreEmprunte e : emprunts) {
			try {
				System.out.println("nice ");
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(getClass().getResource("emprunt_user.fxml"));
				Parent n = (Parent) loader.load();
				Emprunt_userController emp = loader.getController();
				emp.init(e);

//				AnchorPane n = FXMLLoader.load(getClass().getResource("emprunt.fxml"));
//				AnchorPane n = FXMLLoader.lo
//				n.getChildren().getClass().getResource();
				node_emprunt.add(n);
			} catch (IOException ex) {
				Logger.getLogger(bibliotheque_userController.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
		for (Node n : node_emprunt) {
			liste_emprunt.getChildren().add(n);
		}
	}

	@FXML
	private void afficher_page_emprunts(KeyEvent event) throws SQLException {
		page_catalogue.setVisible(false);
		page_emprunts.setVisible(true);
		liste_emprunt.getChildren().clear();
		List<LivreEmprunte> emprunts = new ArrayList<>();
		emprunts = ser_livre.search(Integer.valueOf(id_user.getText()));
		List<Node> node_emprunt = new ArrayList<>();
		for (LivreEmprunte e : emprunts) {
			try {
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(getClass().getResource("emprunt_user.fxml"));
				Parent n = (Parent) loader.load();
				Emprunt_userController emp = loader.getController();
				emp.init(e);

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
