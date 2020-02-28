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
public class DemandeEncadrement {
    private int id ;
    private int id_prof;
    private int id_pfe;
    private String etat ="en_attente";
    private String sujet;
    private String titre;
    public DemandeEncadrement (int id_prof,int id_pfe){
    this.id_prof=id_prof;
    this.id_pfe=id_pfe;
    }
     public DemandeEncadrement(int id ,String sujet,String titre,String etat)
    {
        this.id=id;
        this.sujet=sujet;
        this.titre=titre;
        this.etat=etat;
    }
    public DemandeEncadrement (int id_prof,int id_pfe,String etat ){
    this.id=id ;
    this.id_prof=id_prof;
    this.id_pfe=id_pfe;
    
    this.etat=etat;
    }

    public DemandeEncadrement() {
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
     * @return the id_prof
     */
    public int getId_prof() {
        return id_prof;
    }

    /**
     * @param id_prof the id_prof to set
     */
    public void setId_prof(int id_prof) {
        this.id_prof = id_prof;
    }

    /**
     * @return the id_pfe
     */
    public int getId_pfe() {
        return id_pfe;
    }

    /**
     * @param id_pfe the id_pfe to set
     */
    public void setId_pfe(int id_pfe) {
        this.id_pfe = id_pfe;
    }

    /**
     * @return the req
     */
    

    /**
     * @return the etat
     */
    public String getEtat() {
        return etat;
    }

    /**
     * @param etat the etat to set
     */
    public void setEtat(String etat) {
        this.etat = etat;
    }
    
    @Override
    
 public String toString(){
    return this.id+" "+this.id_pfe+" "+this.id_prof+" "+this.etat;
    } 

    public String getSujet() {
        return sujet;
    }

    public void setSujet(String sujet) {
        this.sujet = sujet;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }
   
    
   
}