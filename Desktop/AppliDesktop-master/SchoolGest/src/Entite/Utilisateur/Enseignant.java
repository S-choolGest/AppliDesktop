/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entite.Utilisateur;

/**
 *
 * @author MehdiS
 */
public class Enseignant extends Utilisateur{

    public Enseignant(int id, String nom, String prenom, String email, String password, String cin, int numTel, String dateNaissance, String adresse, int type) {
        super(id, nom, prenom, email, password, cin, numTel, dateNaissance, adresse, type);
    }

   
    
}
