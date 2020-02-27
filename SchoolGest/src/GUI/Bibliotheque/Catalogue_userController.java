/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Bibliotheque;

import Entite.Bibliotheque.Livre;
import Entite.Utilisateur.Utilisateur;
import GUI.Bibliotheque.Utilisateur.Bibliotheque_user_menuController;
import Services.Bibliotheque.ServicesEmprunt;
import Services.Bibliotheque.ServicesEmprunteur;
import Services.Bibliotheque.ServicesLivreEmprunte;
import Services.Bibliotheque.ServicesLivres;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
public class Catalogue_userController implements Initializable {

	@FXML
	private ScrollPane page_liste_livre;
	@FXML
	private VBox catalogues;
	@FXML
	private Pane page_detail;
	@FXML
	private Pane img_livre;
	@FXML
	private Label editeur;
	@FXML
	private Label titre;
	@FXML
	private Label categorie;
	@FXML
	private Pane emprunter;
	@FXML
	private Label day_sortie;
	@FXML
	private Label month_sortie;
	@FXML
	private Label year_sortie;
	@FXML
	private Label day_ajout;
	@FXML
	private Label month_ajout;
	@FXML
	private Label year_ajout;
	@FXML
	private Label identifiant;
	@FXML
	private Label auteur;
	@FXML
	private Label taille;
	@FXML
	private Label quantite;
	@FXML
	private Label error_emprunter;
	@FXML
	private DatePicker date_debut;
	@FXML
	private DatePicker date_fin;
	private Stage stage;
	public Utilisateur user;
	private List<Boolean> ordre = new ArrayList<>();
	private String etat = "tout";
	private ServicesLivres ser = new ServicesLivres();
	private ServicesEmprunt ser_emp = new ServicesEmprunt();
	private ServicesEmprunteur ser_user = new ServicesEmprunteur();
	private ServicesLivreEmprunte ser_livre = new ServicesLivreEmprunte();
	@FXML
	private Pane aucun_livre;
	@FXML
	private ChoiceBox<String> choix_tri_livre;
	@FXML
	private ChoiceBox<String> choix_categorie;
	@FXML
	private JFXTextField search;
	@FXML
	private ImageView btn_back;
	private AnchorPane body;
	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO
	}	
	public void getStage(Stage stage) {
		this.stage = stage;
	}
	public void getInfo(Utilisateur u) throws SQLException, SQLException{
		this.user = u;
		afficher_page_catalogue(null);
	}
	private void afficher_page_catalogue(MouseEvent event) throws SQLException {
		catalogues.setSpacing(30);
		page_detail.setVisible(false);
		aucun_livre.setVisible(false);
		List<Livre> livres = ser.search(search.getText());
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
					try {
						String[] date = l.getDateSortie().split("-");
						day_sortie.setText(date[2]);
						month_sortie.setText(date[1]);
						year_sortie.setText(date[0]);
					} catch (Exception ex) {
						System.out.println(ex);
						day_sortie.setText("jj");
						month_sortie.setText("mois");
						year_sortie.setText("année");
					}
					try {
						String[] date = l.getDateajout().split("-");
						day_ajout.setText(date[2]);
						month_ajout.setText(date[1]);
						year_ajout.setText(date[0]);
					} catch (Exception ex) {
						System.out.println(ex);
						day_ajout.setText("jj");
						month_ajout.setText("mois");
						year_ajout.setText("année");
					}
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
								if (date_debut.getValue() == null || date_fin.getValue() == null) {
									error_emprunter.setText("Veillez définir la période d'emprunt !!!");
								} else {
									DateTimeFormatter dateformat = DateTimeFormatter.ofPattern("yyyy/MM/dd");
									String datedebut = dateformat.format(date_debut.getValue());
									String datefin = dateformat.format(date_fin.getValue());
									if(datedebut.compareTo(datefin)<0)
									error_emprunter.setText(ser_emp.emprunter(Integer.valueOf(user.getId()), l.getId(), datedebut, datefin));
									else
										error_emprunter.setText("Date de début > date de fin");
								}
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
					try {
						String[] date = l.getDateSortie().split("-");
						day_sortie.setText(date[2]);
						month_sortie.setText(date[1]);
						year_sortie.setText(date[0]);
					} catch (Exception ex) {
						System.out.println(ex);
						day_sortie.setText("jj");
						month_sortie.setText("mois");
						year_sortie.setText("année");
					}
					try {
						String[] date = l.getDateajout().split("-");
						day_ajout.setText(date[2]);
						month_ajout.setText(date[1]);
						year_ajout.setText(date[0]);
					} catch (Exception ex) {
						System.out.println(ex);
						day_ajout.setText("jj");
						month_ajout.setText("mois");
						year_ajout.setText("année");
					}
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
	private void afficher_livre(KeyEvent event) throws SQLException {
		afficher_page_catalogue(null);
	}

	@FXML
	private void back(MouseEvent event) throws IOException, SQLException {
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

	public void getBody(AnchorPane body) {
		this.body = body;
	}
}
