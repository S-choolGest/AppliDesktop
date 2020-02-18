/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IServices.Pfe;

import Entite.pfe.Pfe;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author TOSHIBA
 */
public interface IservicePfe {
 public void ajouterPfe(Pfe p) throws SQLException;
 public void modifiePfe(Pfe p,String s ,String c)throws SQLException;
 public void supprimerPfe(Pfe p) throws SQLException;
 public List<Pfe> readAll() throws SQLException;

    
    
}
