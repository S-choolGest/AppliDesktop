/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package schoolgest;

import Entite.Education.Note;
import Services.Education.ServicesNote;

public class SchoolGest {

    
    public static void main(String[] args) {
    	
    	 ServicesNote sn = new ServicesNote();
    	 Note n = new Note("chet9il");
    	 sn.ajouterNote(n);
    	 //sn.modifierNote(n);
    	// sn.supprimerNote(n);
    	 sn.afficherNote();
    	 
    }
    
}
