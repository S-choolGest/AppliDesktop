/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package test.Bibliotheque;

import Entite.Utilisateur.*;
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
        ServicesBibliothecaire ser = new ServicesBibliothecaire();
        
        Bibliothecaire p1 = new Bibliothecaire("1321aa123", "aaaaa", "bbbbb", "1999-12-16", "ng@za.com", 565654, "@adresse");
        Bibliothecaire p2 = new Bibliothecaire("1321bb123", "adsaaa", "bbsdb", "2000-1-1", "qsg@za.com", 565654, "@adrqdsse");
        
        try {
//         
            ser.ajouter(p2);
            ser.ajouter(p1);
            List<Bibliothecaire> list = ser.readAll();
            System.out.println(list);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
}
