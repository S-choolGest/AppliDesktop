/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Entite;

import Services.Cours.ServiceMatiere;


/**
 *
 * @author saghir
 */
public class GestionEcole {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       
//        
        
        Services.Cours.ServiceMatiere sv = new ServiceMatiere();
        
        //Matieres m = new Matieres("Probabilite", 5);
        
        //sv.ajouterMatiere(m);
        
         Matieres m = new Matieres("Unix", 5);
         
         //sv.modifierMatiere(m);
         sv.afficherListMatiere();
        
    }
    
}
