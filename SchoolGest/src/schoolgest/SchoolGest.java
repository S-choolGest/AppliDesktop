/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package schoolgest;

import Entite.Education.Note;
import Entite.Formulaire.Formulaire;
import Services.Education.ServicesNote;
import Services.Formulaire.ServicesFormulaire;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class SchoolGest {

    
    public static void main(String[] args) {
    	
    	 ServicesNote sn = new ServicesNote();
    	 Note n = new Note("texte");
    	 sn.ajouterNote(n);
    	 //sn.modifierNote(n);
    	 //sn.supprimerNote(n);
    	 sn.afficherNote(); 
       
    }
    
    
}
