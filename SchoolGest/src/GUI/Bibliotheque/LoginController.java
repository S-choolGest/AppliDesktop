/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Bibliotheque;

import GUI.Bibliotheque.admin.Backoffice_adminController;
import Entite.Utilisateur.Utilisateur;
import GUI.Etudiant.InterfaceEtudiantController;
import Services.Utilisateur.ServicesUtilisateur;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author william
 */
public class LoginController implements Initializable {

	@FXML
	private JFXTextField email;
	@FXML
	private JFXButton login;
	@FXML
	private Label error;
	@FXML
	private JFXPasswordField password;
	private ServicesUtilisateur ser = new ServicesUtilisateur();
	@FXML
	private ImageView btn_close;
	private double sx = 0;
	private double sy = 0;
	private double stagex = 0;
	private double stagey = 0;
	private Stage stage;

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO
	}

	@FXML
	private void se_connecter(ActionEvent event) throws SQLException, IOException {
		String emailL = email.getText();
		String pwd = password.getText();
		int type;
//		try {
		if (emailL.length() == 0 || pwd.length() == 0) {
			error.setText("Remplir tous les champs !!!");
		} else {
			Utilisateur u = ser.recuperer_type_compte(emailL, pwd);
			System.out.println(u.getType());
			if (u.getType() == 5) {
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(getClass().getResource("home.fxml"));
				Parent n = (Parent) loader.load();
				HomeController user = loader.getController();
				user.setProfil(u);
				Stage stage = new Stage();
				stage.setTitle("Edutech : Bibliotheque : Gestion");
				Scene scene = new Scene(n);
				stage.setResizable(false);
//        scene.setFill(javafx.scene.paint.Color.TRANSPARENT);
				stage.setScene(scene);
				stage.show();
			} else if (u.getType() == 3) {
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(getClass().getResource("admin/backoffice_admin.fxml"));
				Parent n = (Parent) loader.load();
				Backoffice_adminController user = loader.getController();
				user.setProfil(u);
				Stage stage = new Stage();
				stage.setTitle("Edutech : Back-Office");
				Scene scene = new Scene(n);
				stage.setResizable(false);
//        scene.setFill(javafx.scene.paint.Color.TRANSPARENT);
				stage.setScene(scene);
				stage.show();
				System.out.println("ezrzrze");
			} else if (u.getType() == 0) {
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(getClass().getResource("../Etudiant/InterfaceEtudiant.fxml"));
				Parent n = (Parent) loader.load();
				InterfaceEtudiantController user = loader.getController();
//				user.setProfil(u);
				Stage stage = new Stage();
				stage.setTitle("Edutech : Bibliotheque");
				Scene scene = new Scene(n);
				stage.setResizable(false);
//        scene.setFill(javafx.scene.paint.Color.TRANSPARENT);
				stage.setScene(scene);
				stage.show();
			}
		}
//		} catch (Exception e) {
//			System.out.println(e);
//			error.setText(e.toString());
//		}

	}

	@FXML
	private void close_window(MouseEvent event) {

	}

	public ImageView close() {
		btn_close.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {

				System.out.println("fuck");
				stage.close();
			}
		});
		return btn_close;
	}

	@FXML
	private void controle_saisie(KeyEvent event) {
		error.setText("");

	}

	@FXML
	private void deplacer_window(MouseEvent event) {
		this.stage.setX(event.getScreenX() - sx);
		this.stage.setY(event.getScreenY() - sy);
		this.stage.setOpacity(0.9);
	}

	@FXML
	private void recuperer_position(MouseEvent event) {
		sx = event.getScreenX() - event.getSceneX();
		sy = event.getScreenY() - event.getSceneY();
		stagex = event.getSceneX();
		stagey = event.getSceneY();
		System.out.println("posx = "+event.getX());
		System.out.println("posy = "+event.getY());
	}

	public void getStage(Stage stage) {
		this.stage = stage;
	}

	@FXML
	private void setOpacity(MouseEvent event) {
		this.stage.setOpacity(1);
	}
}
