/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Bibliotheque;

import Entite.Bibliotheque.LivreEmprunte;
import Entite.Utilisateur.Utilisateur;
import GUI.Bibliotheque.Utilisateur.Bibliotheque_user_menuController;
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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author william
 */
public class Consulter_empruntController implements Initializable {

	@FXML
	private ScrollPane demandes_emprunt;
	@FXML
	private VBox liste_emprunt;
	@FXML
	private TextField search_emprunt;
	@FXML
	private ChoiceBox<String> choix_tri_emprunt;
	@FXML
	private ChoiceBox<String> choix_etat_emprunt;
	@FXML
	private Label aucun_emprunt;
	@FXML
	private ImageView btn_back;
	private Stage stage;
	public Utilisateur user;
	private List<Boolean> ordre = new ArrayList<>();
	private String etat = "tout";
	private ServicesLivres ser = new ServicesLivres();
	private ServicesEmprunt ser_emp = new ServicesEmprunt();
	private ServicesEmprunteur ser_user = new ServicesEmprunteur();
	private ServicesLivreEmprunte ser_livre = new ServicesLivreEmprunte();
	private ServicesLivreEmprunte ser_livre_emp = new ServicesLivreEmprunte();
	private AnchorPane body;

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO
	}

	@FXML
	private void rechercher_emprunt(KeyEvent event) throws SQLException {
		afficher_emprunts();
	}


	@FXML
	private void back(MouseEvent event) throws SQLException, IOException {
		this.body.getChildren().clear();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("Utilisateur/Bibliotheque_user_menu.fxml"));
		Parent n = (Parent) loader.load();
		Bibliotheque_user_menuController emp = loader.getController();
		emp.getStage(stage);
		emp.getBody(body);
		emp.getInfo(user);
		this.body.getChildren().add(n);
	}

	public void getStage(Stage stage) {
		this.stage = stage;
	}

	public void getInfo(Utilisateur u) throws SQLException, SQLException {
		this.user = u;
//		afficher_page_emprunt(null);
	}
	
	public void getBody(AnchorPane body) {
		this.body = body;
	}
	
	private void afficher_emprunts() throws SQLException {
		liste_emprunt.getChildren().clear();
		List<LivreEmprunte> emprunts = new ArrayList<>();
		emprunts = ser_livre.search(user.getId(), search_emprunt.getText());
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

	private void filterByEtat(String etat) throws SQLException {
		liste_emprunt.getChildren().clear();
		List<LivreEmprunte> emprunts = new ArrayList<LivreEmprunte>();
//		emprunts = ser_emprunt.readAll();
//		emprunts = ser_livre_emp.readAllinBibliotheque(email.getText());
//		List<Emprunt> emprunts = new ArrayList<Emprunt>();
		emprunts = ser_livre_emp.filterByEtat(String.valueOf(user.getId()), etat, search_emprunt.getText());
		List<Node> node_emprunt = new ArrayList<>();
		if (emprunts == null) {
			aucun_emprunt.setVisible(true);
		} else {
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

	private void triEmprunts(String type, Boolean ordre, String etat) throws SQLException {
		liste_emprunt.getChildren().clear();
		List<LivreEmprunte> emprunts = new ArrayList<LivreEmprunte>();
		System.out.println("ettt" + etat);
		emprunts = ser_livre_emp.tri(String.valueOf(user.getId()), type, ordre, etat, search_emprunt.getText());
		List<Node> node_emprunt = new ArrayList<>();
		if (emprunts == null) {
			aucun_emprunt.setVisible(true);
		} else {
			for (LivreEmprunte e : emprunts) {
				try {
					FXMLLoader loader = new FXMLLoader();
					loader.setLocation(getClass().getResource("emprunt_user.fxml"));
					Parent n = (Parent) loader.load();
					Emprunt_userController emp = loader.getController();
					emp.init(e);
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
}
