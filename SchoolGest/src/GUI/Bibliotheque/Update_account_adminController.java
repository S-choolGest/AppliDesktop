/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Bibliotheque;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author william
 */
public class Update_account_adminController implements Initializable {

	@FXML
	private JFXTextField nom;
	@FXML
	private JFXButton creer;
	@FXML
	private JFXTextField prenom;
	@FXML
	private JFXTextField tel;
	@FXML
	private JFXTextField email;
	@FXML
	private JFXTextField cin;
	@FXML
	private JFXTextField adresse;
	@FXML
	private ImageView close;
	@FXML
	private Label error;
	@FXML
	private ImageView btn_camera;
	@FXML
	private DatePicker date_naissance;
	@FXML
	private JFXTextField id;
	@FXML
	private JFXPasswordField password;
	@FXML
	private JFXPasswordField password2;
	@FXML
	private JFXButton upload_image;

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO
	}	

	@FXML
	private void modifier_compte(ActionEvent event) {
	}

	@FXML
	private void close_window(MouseEvent event) {
	}

	@FXML
	private void take_picture(MouseEvent event) {
	}
	
}
