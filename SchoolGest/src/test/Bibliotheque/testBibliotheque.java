/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package test.Bibliotheque;

import Entite.Bibliotheque.*;
import Entite.Utilisateur.*;
import Services.Bibliotheque.*;
import Services.Utilisateur.*;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author william
 */
public class testBibliotheque {
    public static void main(String[] args) {
        // TODO code application logic here
//        ServicesBibliothecaire ser = new ServicesBibliothecaire();
//        
//        Bibliothecaire p1 = new Bibliothecaire("1321aa123", "aaaaa", "bbbbb", "1999-12-16", "ng@za.com", 565654, "@adresse");
//        Bibliothecaire p2 = new Bibliothecaire("1321bb123", "adsaaa", "bbsdb", "2000-1-1", "qsg@za.com", 565654, "@adrqdsse");
//        Bibliothecaire p3 = new Bibliothecaire(2,"1321cc123", "adsaaa", "bbsdb", "2000-1-1", "qsg@za.com", 565654, "@adrqdsse");
//        Bibliothecaire p4 = new Bibliothecaire(1);
//        try {
////         
////            ser.ajouter(p2);
////            ser.ajouter(p1);
////            ser.update(p3);
//            ser.delete(p4);
//            List<Bibliothecaire> list = ser.readAll();
//            System.out.println(list);
//        } catch (SQLException ex) {
//            System.out.println(ex);
//        }
        
        
//        ServicesEmprunt ser = new ServicesEmprunt();
//        
//        Emprunt e1 = new Emprunt(1, 2, Etat.attente, "2020-01-01", null, null);
//        Emprunt e2 = new Emprunt(4, 45, Etat.rendu, "2019-01-01", null, null);
//        Emprunt e3 = new Emprunt(1, 4, 45, Etat.rendu, "2019-01-01", "2020-01-05", null);
//        Emprunt e4 = new Emprunt(2);
//        
//        try{
////            ser.ajouter(e2);
////            ser.ajouter(e1);
//            //ser.update(e3);
//            ser.delete(e4);
//            List<Emprunt> list = ser.readAll();
//            System.out.println(list);
//        } catch(SQLException ex){
//            System.out.println(ex);
//        }
        
        ServicesBibliotheque ser = new ServicesBibliotheque();
        
        Bibliotheque l1 = new Bibliotheque("bb1", 1000);
        Bibliotheque l2 = new Bibliotheque("bb2", 505);
        Bibliotheque l3 = new Bibliotheque(1, "biblio_1", 1200);
        Bibliotheque l4 = new Bibliotheque(2);
        
        try{
//            ser.ajouter(l2);
//            ser.ajouter(l1);
//            ser.update(l3);
            ser.delete(l4);
            List<Bibliotheque> list = ser.readAll();
            System.out.println(list);
        } catch(SQLException ex){
            System.out.println(ex);
        }
        
//        ServicesLivres ser = new ServicesLivres();
//        
//        Livre l1 = new Livre(1, "aaa", "bbb", "ccc", "ddd", "2020-01-01", 100,12);
//        Livre l2 = new Livre(2, "aaa2", "bbb2", "ccc2", "ddd2", "1852-01-01", 504,54);
//        Livre l3 = new Livre(4, 1, "aaa1", "bbb1", "ccc1", "ddd1", "2020-01-01", 600,55);
//        Livre l4 = new Livre(3);
//        
//        try{
//            ser.ajouter(l2);
////            ser.ajouter(l1);
////            ser.update(l3);
////            ser.delete(l4);
//            List<Livre> list = ser.readAll();
//            System.out.println(list);
//        } catch(SQLException ex){
//            System.out.println(ex);
//        }
    }
}
