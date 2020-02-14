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
			System.out.println("Note ajout�");
		            
			} catch (SQLException ex) {
				Logger.getLogger(ServicesNote.class.getName()).log(Level.SEVERE, null, ex);
			}
				
	}

	@Override
	public void modifierNote(Note n) {
		try {
            String requeteUpdate =
                    "UPDATE note SET typeNote = ? "+"WHERE idNote=1";

            PreparedStatement st =con.prepareStatement(requeteUpdate);

            st.setString(1, n.getTypeNote());
            
            

            st.executeUpdate();
            System.out.println("matiere modifier");


        }
        catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
		
		
	}

	@Override
	public void supprimerNote(Note n) {
		Statement st;
		try {
	        String requeteDelete =
	                "DELETE FROM note where idNote=1";
	                st =con.createStatement();
	                st.executeUpdate(requeteDelete);
	                System.out.println("user supprim�e____ "+n.toString());
	        }
	        catch (SQLException ex){
	            System.out.println(ex.getMessage());
	        }  
		
	}

	@Override
	public void afficherNote() {
		ResultSet rs;
		Statement st;
        String requete = "select * from note ";
        try {
            st =con.createStatement();
            rs= st.executeQuery(requete);
            while(rs.next()){
                System.out.println("ID: "+rs.getString("idNote")+" , Note: "+rs.getString("typeNote"));

               
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServicesNote.class.getName()).log(Level.SEVERE, null, ex);
        } 
		
	}
	
	

}
