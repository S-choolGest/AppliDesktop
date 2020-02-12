/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package IServices;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author william
 */
public interface IServices<T> {
    boolean ajouter(T t) throws SQLException;
    boolean delete(T t) throws SQLException;
    boolean update(T t) throws SQLException;
    List<T> readAll() throws SQLException;
}
