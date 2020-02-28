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
    private String NomEtudiant;
    private String etat;

    public Absence( String matiere, String date, String etat ,int IdEtudiant)  {
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

    public Absence(int id, String matiere, Timestamp date, String Etat, int IdEtudiant) {
        this.id = id;
        this.matiere = matiere;
        this.timestamp = date;
        this.IdEtudiant = IdEtudiant;
        this.etat = Etat;
    }

    public Absence(String matiere, Timestamp date, String Etat, int IdEtudiant) {
        this.matiere = matiere;
        this.timestamp = date;
        this.IdEtudiant = IdEtudiant;
        this.etat = Etat;
    }

    public Absence(int id,String matiere, Timestamp timestamp, String NomEtudiant, String etat) {
        this.id = id;
        this.matiere = matiere;
        this.timestamp = timestamp;
        this.NomEtudiant = NomEtudiant;
        this.etat = etat;
    }
    
    
    
    public Absence(int id, String matiere, String DateTime, String etat, int Idetudiant) {
        this.id = id;
        this.matiere = matiere;
        try{
            Date dateTime= new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(DateTime);
            long dateL = dateTime.getTime();
            timestamp = new Timestamp(dateL);
         } catch(ParseException e) {
             e.printStackTrace();
        }
        this.etat = etat;
        this.IdEtudiant = Idetudiant;
    }

    public int getIdEtudiant() {
        return IdEtudiant;
    }
    
      public String getIdEtudiantS() {
        return NomEtudiant;
    }
    
    public String getIdS() {
        return Integer.toString(id);
    }
    
    public int getId() {
        return id;
    }

    public String getMatiere() {
        return matiere;
    }

    public String getDate() {
        return timestamp.toString();
    }
    
    
    public String getetat(){
       return etat;
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

    public void setEtat(String etat) {
        this.etat = etat;
    }

    @Override
    public String toString() {

       String result = "Absence{" + "id=" + id + ", matiere=" + matiere + ", date=" + timestamp  ;
       
       return result +",Etat="+ etat+  " , Etudiant="+NomEtudiant+"}";
    }
    
    public String afficherAbsEtu(){
        String Etat ;
       String result = "Absence{" + "matiere=" + matiere + ", date=" + timestamp ;
      
       
       return result +",Etat="+ etat +"} ";
    }

    
    
    
}

 