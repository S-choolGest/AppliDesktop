/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Bibliotheque;

import Entite.Bibliotheque.Bibliotheque;
import Entite.Bibliotheque.Livre;
import Services.Bibliotheque.ServicesEmprunt;
import Services.Bibliotheque.ServicesEmprunteur;
import Services.Bibliotheque.ServicesLivreEmprunte;
import Services.Bibliotheque.ServicesLivres;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
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
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author william
 */
public class Catalogue_bibliothecaireController implements Initializable {

	@FXML
	private Pane page_catalogue;
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
	private DatePicker date_debut;
	private DatePicker date_fin;
	private ServicesLivres ser = new ServicesLivres();
	private ServicesEmprunt ser_emp = new ServicesEmprunt();
	private ServicesEmprunteur ser_user = new ServicesEmprunteur();
	private ServicesLivreEmprunte ser_livre = new ServicesLivreEmprunte();
	private Bibliotheque bib;
	@FXML
	private Pane aucun_livre;
	@FXML
	private Pane editer;
	@FXML
	private Pane page_edit;
	@FXML
	private Pane img_livre1;
	@FXML
	private Pane valider;
	@FXML
	private Label identifiant1;
	@FXML
	private Label error;
	@FXML
	private DatePicker field_date_sortie;
	@FXML
	private JFXTextField field_titre;
	@FXML
	private JFXTextField field_editeur;
	@FXML
	private JFXTextField field_auteur;
	@FXML
	private JFXTextField field_categorie;
	@FXML
	private JFXTextField field_page;
	@FXML
	private JFXTextField field_quantite;
	@FXML
	private Label day_ajout1;
	@FXML
	private Label month_ajout1;
	@FXML
	private Label year_ajout1;

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		page_detail.setVisible(false);
		aucun_livre.setVisible(false);
		page_edit.setVisible(false);
		// TODO
	}

	private void afficher_page_catalogue(Bibliotheque bib) throws SQLException {
		catalogues.setSpacing(30);
		List<Livre> livres = ser.readAllBibliotheque(bib.getId());
		int i = 4;
		catalogues.getChildren().clear();
		List<HBox> lines = new ArrayList<HBox>();
		HBox line_livre = new HBox(40);
		lines.add(line_livre);
		int j = 0;
		if (livres.size() == 0) {
			aucun_livre.setVisible(true);
		}
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
			livre.getChildren().add(imgv);
			livre.getChildren().add(show);
			show.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					error.setText("");
					page_detail.setVisible(true);
					page_edit.setVisible(false);
					refresh_detail_livre(l, 1);
					page_liste_livre.setOnMouseClicked(new EventHandler<MouseEvent>() {
						@Override
						public void handle(MouseEvent event) {
							page_detail.setVisible(false);
							page_edit.setVisible(false);
						}
					});
					editer.setOnMouseClicked(new EventHandler<MouseEvent>() {
						@Override
						public void handle(MouseEvent event) {
							page_detail.setVisible(false);
							page_edit.setVisible(true);
							field_titre.setText(titre.getText());
							field_auteur.setText(auteur.getText());
							field_categorie.setText(categorie.getText());
							DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
//							LocalDate dateA = LocalDate.parse(day_ajout.getText()+" "+month_ajout.getText()+" "+year_ajout.getText(), formatter);
//							field_date_ajout.setValue(dateA);
//							LocalDate dateS = LocalDate.parse(day_sortie.getText()+" "+month_sortie.getText()+" "+year_sortie.getText()+" 11:05:30", formatter);
//							field_date_sortie.setValue(dateS);
							field_editeur.setText(editeur.getText());
							field_quantite.setText(quantite.getText());
							field_page.setText(taille.getText());
							day_ajout1.setText(day_ajout.getText());
							month_ajout1.setText(month_ajout.getText());
							year_ajout1.setText(year_ajout.getText());
							img_livre1.getChildren().add(img_livre.getChildren().get(0));
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

	void init(Bibliotheque b) {
		this.bib = b;
		System.out.println(this.bib);
		if (this.bib != null) {
			try {
				afficher_page_catalogue(this.bib);
			} catch (SQLException ex) {
				Logger.getLogger(Catalogue_bibliothecaireController.class.getName()).log(Level.SEVERE, null, ex);
			}
		} else {
			aucun_livre.setVisible(true);
		}
	}

	@FXML
	private void editer_livre(MouseEvent event) {
		try {
			LocalDate datel = field_date_sortie.getValue();
			DateTimeFormatter dateformat = DateTimeFormatter.ofPattern("yyyy/MM/dd");
			String datef = dateformat.format(datel);
			Livre l = new Livre(Integer.valueOf(identifiant.getText()), field_titre.getText(), field_editeur.getText(), field_auteur.getText(), field_categorie.getText(), datef, Integer.valueOf(taille.getText()), Integer.valueOf(field_quantite.getText()), "http://localhost/mobile/livre.png");
			if (l.getAuteur().equals("") || l.getCategorie().equals("") || l.getDateSortie().equals("") || l.getEditeur().equals("") || l.getTaille() < 5 || l.getTitre().equals("")) {
				error.setText("Vérifier vos données !!!");
			} else {
				if (ser.update(l)) {
					System.out.println("date " + l.getDateSortie() + " " + l.getDateajout());
					afficher_page_catalogue(this.bib);
					System.out.println("this.bib " + this.bib);
					refresh_detail_livre(l, 2);
					page_detail.setVisible(true);
					page_edit.setVisible(false);
				} else {
					error.setText("Impossible de modifer le livre " + l.getId());
				}
			}
		} catch (Exception e) {
			error.setText("Vérifier vos données !!!(quantite, bibliotheque id et pages doivent être des nombres)");
		}
	}

	private void refresh_detail_livre(Livre l, int type) {
		titre.setText(l.getTitre());
		identifiant.setText(String.valueOf(l.getId()));
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
		if (type == 2) {
			try {
				String[] date = l.getDateSortie().split("/");
				day_sortie.setText(date[2]);
				month_sortie.setText(date[1]);
				year_sortie.setText(date[0]);
			} catch (Exception ex) {
				System.out.println(ex);
				day_sortie.setText("jj");
				month_sortie.setText("mois");
				year_sortie.setText("année");
			}
		}
		if (type == 1) {
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
		}
		Image img = new Image(l.getImg());
		ImageView imgv = new ImageView();
		imgv.setImage(img);
		imgv.setFitWidth(200);
		imgv.setFitHeight(400);
		if (type == 1) {
			img_livre.getChildren().clear();
			img_livre.getChildren().add(imgv);
		}else{
			img_livre1.getChildren().clear();
			img_livre1.getChildren().add(imgv);
		}
	}
}
