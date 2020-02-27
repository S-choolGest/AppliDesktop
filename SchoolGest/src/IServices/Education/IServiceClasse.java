/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IServices.Education;

import java.sql.SQLException;
import java.util.List;
import javafx.collections.ObservableList;

/**
 *
 * @author MehdiS
 */
public interface IServiceClasse<T> {
     ObservableList<String> readNomEtudiantClasse(String NomClasse) throws SQLException;
}
