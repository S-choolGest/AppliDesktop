/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entite.Education;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author MehdiS
 */
public class Absence {
    private int id;
    private String matiere;
    private Timestamp timestamp;
    private int IdEtudiant;
    private boolean etat;

    public Absence(int id, String matiere, Timestamp date, boolean etat ) {
        this.id = id;
        this.matiere = matiere;
        this.timestamp = date;
        this.etat = etat;
        
    }

    public Absence(int id, String matiere, Timestamp timestamp,  boolean etat ,int IdEtudiant) {
        this.id = id;
        this.matiere = matiere;
        this.timestamp = timestamp;
        this.IdEtudiant = IdEtudiant;
        this.etat = etat;
    }

    public Absence( String matiere, String date, boolean etat ,int IdEtudiant)  {
        this.matiere = matiere;
        try{
            Date dateTime= new SimpleDateFormat("yyyy/MM/dd hh:mm:ss").parse(date);
            long dateL = dateTime.getTime();
            timestamp = new Timestamp(dateL);
         } catch(ParseException e) {
             e.printStackTrace();
        }
        this.etat = etat;
        this.IdEtudiant = IdEtudiant;
    }

    public Absence(String matiere, Timestamp date, boolean etat) {
        this.matiere = matiere;
        this.timestamp = date;
        this.etat = etat;
    }

    public int getIdEtudiant() {
        return IdEtudiant;
    }
    
      public String getIdEtudiantS() {
        return Integer.toString(IdEtudiant);
    }
    
    public String getIdS() {
        return Integer.toString(id);
    }

    public String getMatiere() {
        return matiere;
    }

    public String getDate() {
        return timestamp.toString();
    }
    
    public String getetat(){
        if(etat == false){
            return "Justifier";
        }else
        return "non Justifier";
    }

    public void setId(int id) {
        this.id = id;
    }
   
    public void setMatiere(String matiere) {
        this.matiere = matiere;
    }

    public void setDate(Timestamp date) {
        this.timestamp = date;
    }

    public void setIdEtudiant(int IdEtudiant) {
        this.IdEtudiant = IdEtudiant;
    }

    public void setEtat(boolean etat) {
        this.etat = etat;
    }

    @Override
    public String toString() {
        String Etat ;
       String result = "Absence{" + "id=" + id + ", matiere=" + matiere + ", date=" + timestamp  ;
       if(this.etat == false){
           Etat = "Justifier";
       }else {
           Etat = "non Justifier";
       }
       
       return result +",Etat="+ Etat+  " , Etudiant="+IdEtudiant+"}";
    }
    
    public String afficherAbsEtu(){
        String Etat ;
       String result = "Absence{" + "matiere=" + matiere + ", date=" + timestamp ;
       if(this.etat == false){
           Etat = "Justifier";
       }else {
           Etat = "non Justifier";
       }
       
       return result +",Etat="+ Etat +"} ";
    }

    
    
    
}

 