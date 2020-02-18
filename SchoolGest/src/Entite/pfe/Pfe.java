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
private String sujet;
private String cahier_charge;
public Pfe(int ide,String sujet,String cahier_charge){
this.id_etudiant=ide;
this.sujet=sujet;
this.cahier_charge=cahier_charge;
}
public Pfe(int id ,int ide,String sujet,String cahier_charge){
this.id=id;
this.id_etudiant=ide;
this.sujet=sujet;
this.cahier_charge=cahier_charge;

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

    /**
     * @return the cahier_charge
     */
    public String getCahier_charge() {
        return cahier_charge;
    }

    /**
     * @param cahier_charge the cahier_charge to set
     */
    public void setCahier_charge(String cahier_charge) {
        this.cahier_charge = cahier_charge;
    }
    public String toString(){
    return this.id+" "+this.cahier_charge+" "+this.sujet;
    }
    
    
    
}
