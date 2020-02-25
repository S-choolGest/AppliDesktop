/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entite.pfe;

/**
 *
 * @author TOSHIBA
 */
public class Pfe {
private int id ;
private int id_etudiant;
private String nom_etudiant;
private String sujet;
private String titre;
public Pfe(int ide,String sujet,String titre){
this.id_etudiant=ide;
this.sujet=sujet;
this.titre=titre;
}
public Pfe(int id ,int ide,String sujet,String titre){
this.id=id;
this.id_etudiant=ide;
this.sujet=sujet;
this.titre=titre;
}

    public Pfe() {
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the id_etudiant
     */
    public int getId_etudiant() {
        return id_etudiant;
    }

    /**
     * @param id_etudiant the id_etudiant to set
     */
    public void setId_etudiant(int id_etudiant) {
        this.id_etudiant = id_etudiant;
    }

    /**
     * @return the sujet
     */
    public String getSujet() {
        return sujet;
    }

    /**
     * @param sujet the sujet to set
     */
    public void setSujet(String sujet) {
        this.sujet = sujet;
    }

    public String getNom_etudiant() {
        return nom_etudiant;
    }

    public void setNom_etudiant(String nom_etudiant) {
        this.nom_etudiant = nom_etudiant;
    }

    
    public String getTitre(){
    return titre;
    }

    /**
     * @return the cahier_charge
     */
    public void setTitre(String titre) {
        this.titre = titre;
    }
        public String getsId()
    {
        return Integer.toString(id);
    }
    public String getsIde()
    {
        return Integer.toString(id_etudiant);
    }
    public String toString() {
        return this.id+" "+this.titre+" "+this.sujet;
    }
    
    
    
}
