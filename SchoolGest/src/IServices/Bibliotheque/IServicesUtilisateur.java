/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IServices.Bibliotheque;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author william
 */
public interface IServicesUtilisateur<T> {
	String ajouter(T t) throws SQLException;
    Boolean delete(int id) throws SQLException;
    Boolean update(T t) throws SQLException;
    List<T> readAll() throws SQLException;
    List<T> search(String t) throws SQLException;
    T search(int id) throws SQLException;
}
