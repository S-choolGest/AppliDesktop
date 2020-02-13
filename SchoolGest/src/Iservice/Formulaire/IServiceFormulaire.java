/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Iservice.Formulaire;

import java.sql.SQLException;

/**
 *
 * @author Ahmed
 */
public class IServiceFormulaire<F> {
    void ajouter(F f) throws SQLException;
    boolean delete(F f) throws SQLException;
    boolean update(F f) throws SQLException;
    List<T>readall() throws SQLException;
}
