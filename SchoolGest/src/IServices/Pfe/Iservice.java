/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IServices.Pfe;

/**
 *
 * @author TOSHIBA
 */
import Entite.pfe.DemandeEncadrement;
import java.sql.SQLException;
import static java.util.Collections.list;
import java.util.List;

/**
 *
 * @author TOSHIBA
 */
public interface Iservice {
    public void ajouterDemande(DemandeEncadrement d) throws SQLException;
    /*public void modifierDemande(DemandeEncadrement d,String desc)throws SQLException;*/
    public void supprimerDemande(DemandeEncadrement d) throws SQLException;
    public List<DemandeEncadrement> readAll() throws SQLException;
    public String AccepterDemande(DemandeEncadrement d) throws SQLException;
    public String refuserDemande(DemandeEncadrement d) throws SQLException;
    

            
    
}

