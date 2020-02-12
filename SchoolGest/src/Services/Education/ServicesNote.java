package Services.Education;
import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import Entite.Education.Note;
import IServices.Education.IServicesNotes;
import Utils.DataBase;

public class ServicesNote implements IServicesNotes{
	
	 private final Connection con;
	    private PreparedStatement ste;

	    public ServicesNote() {
	        DataBase mc=DataBase.getInstance();
	        con=mc.getConnection();
	    }

	@Override
	public void ajouterNote(Note n) {
		try {
			String requeteInsert = "INSERT INTO note (typeNote) VALUES(?)";
					            
			 ste =con.prepareStatement(requeteInsert);
					
			ste.setString(1, n.getTypeNote());
			ste.executeUpdate();
			System.out.println("Note ajouté");
		            
			} catch (SQLException ex) {
				Logger.getLogger(ServicesNote.class.getName()).log(Level.SEVERE, null, ex);
			}
				
	}

	@Override
	public void modifierEtudiant(Note n) {
		
	}

	@Override
	public void supprimerEtudiant(Note n) {
		
	}

	@Override
	public void afficherListEtudiant() {
		
	}
	
	

}
