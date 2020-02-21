/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Bibliotheque;

import com.github.sarxos.webcam.Webcam;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.Initializable;
import javax.imageio.ImageIO;

/**
 * FXML Controller class
 *
 * @author william
 */
public class CameraController implements Initializable {

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO
		Webcam webcam = Webcam.getDefault();
		webcam.open();
		try {
			ImageIO.write(webcam.getImage(), "PNG", new File("profil.png"));
		} catch (IOException ex) {
			Logger.getLogger(CameraController.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

}
