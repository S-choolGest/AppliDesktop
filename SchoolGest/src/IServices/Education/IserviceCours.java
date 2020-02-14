/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package IServices.Cours;


/**
 *
 * @author saghir
 * @param <Object>
 */
public interface IserviceCours<Object> {
        public void ajouter(Object t);
	public void modifier(int id,Object t);
	public void supprimer(Object t);
	public void afficherList();
}
