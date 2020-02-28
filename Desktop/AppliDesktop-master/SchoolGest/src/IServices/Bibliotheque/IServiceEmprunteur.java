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
public interface IServiceEmprunteur<T> {
	T read(int id) throws SQLException;
}
