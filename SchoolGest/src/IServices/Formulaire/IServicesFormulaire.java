/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IServices.Formulaire;

import Entite.Formulaire.Formulaire;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Ahmed
 */
public interface IServicesFormulaire<F> {
    
    public void ajouter(Formulaire f) throws SQLException;
    public void delete(int i) throws SQLException;
    public void update(Formulaire f) throws SQLException;
    public List<F>readall() throws SQLException;

    
}
