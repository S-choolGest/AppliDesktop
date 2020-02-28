package Services.ExtraScolaire;
import IServices.ExtraScolaire.*;

import Utils.DataBase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import Extra_Scolaire.Eventt;
import IServices.ExtraScolaire.IService;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServiceEvent  implements IService {

	
	private Connection con;
    private Statement ste;
     public ServiceEvent() {
        con = DataBase.getInstance().getConnection();
    }
     public List readAll() throws SQLException {
         List<Eventt> arr=new ArrayList<>();
     ste=con.createStatement();
     ResultSet rs=ste.executeQuery("select * from `edutech`.`event`");
     while (rs.next()) {                
                int idEvent=rs.getInt(1);
                String nomEvent=rs.getString(2);
                String description=rs.getString(2);
                Timestamp date=rs.getTimestamp(3);
                Eventt e = new Eventt(idEvent, nomEvent, description,date);
      arr.add(e);
      }
     return arr;
     }
     
     public ObservableList<Eventt> readAllV2() throws SQLException {
    	    ObservableList<Eventt> AbsenceData = FXCollections.observableArrayList();
    	    ste=con.createStatement();
    	    ResultSet rs=ste.executeQuery("select * from `edutech`.`event`");
    	    while (rs.next()) {                
    	               int idEvent=rs.getInt(1);
    	               String nomEvent=rs.getString(2);
    	               String description=rs.getString(3);
    	               Timestamp date=rs.getTimestamp(4);
    	               
    	               Eventt p = new Eventt(idEvent, nomEvent, description, date);
    	     AbsenceData.add(p);
    	     }
    	    return AbsenceData;
    	    }
     
     
     
      public ObservableList<Eventt> readAll1() throws SQLException {
          ObservableList<Eventt> AbsenceData = FXCollections.observableArrayList();
     ste=con.createStatement();
     ResultSet rs=ste.executeQuery("select * from `edutech`.`event`");
     while (rs.next()) {                
    	 int idEvent=rs.getInt(1);
         String nomEvent=rs.getString(2);
         String description=rs.getString(2);
         Timestamp date=rs.getTimestamp(3);
         Eventt e = new Eventt(idEvent, nomEvent, description,date);
      AbsenceData.add(e);
      }
     return AbsenceData;
     }
     
    public void ajouterEvent(Eventt e) throws SQLException
    { 
    	
        ste = con.createStatement();
        String requeteInsert = "INSERT INTO `edutech`.`event` (`idEvent`, `nomEvent`, `description`, `timestamp`) VALUES (NULL, '" +  e.getNomEvent() + "', '" + e.getDescription() + "', '"+e.getDate() +"');";        
        ste.executeUpdate(requeteInsert);
    
    }
    
    public boolean delete(Eventt a) throws SQLException {
        ste = con.createStatement();
        String requeteInsert = "DELETE FROM `edutech`.`event` WHERE `idEvent` = '"+a.getIdEvent()+"'";
        if(ste.executeUpdate(requeteInsert) == 1){
            System.out.println("Suppression effectué");
            return true;
        };
        System.out.println("Suppression non effectué");
        return false;
    }
    
    
 
   
  /*  public boolean update(Eventt a) throws SQLException {
        ste = con.createStatement();
        //String requeteInsert ="UPDATE `edutech`.`event` SET `timestamp`= '"+a.getDate()+"',`nomEvent` = '"+a.getNomEvent()+"',`description` = '"+a.getDescription()+"' WHERE `idEvent` = '"+a.getIdEvent()+"'";
        String requeteInsert ="UPDATE `edutech`.`event` SET `description` = '"+a.getDescription()+"', `nomEvent` = '"+a.getNomEvent()+"',`timestamp`= '"+a.getDate()+"'  WHERE `idEvent` = '"+a.getIdEvent()+"'";

        if(ste.executeUpdate(requeteInsert) == 1){
           System.out.println("modification effectué");
           return true;
       };
       System.out.println("modification non effectué");
       return false;
   }*/
      public void update1(String desc,String nom,String date,int id) throws SQLException {
        
        //String requeteInsert ="UPDATE `edutech`.`event` SET `timestamp`= '"+a.getDate()+"',`nomEvent` = '"+a.getNomEvent()+"',`description` = '"+a.getDescription()+"' WHERE `idEvent` = '"+a.getIdEvent()+"'";
        PreparedStatement requeteInsert;
            requeteInsert = con.prepareStatement("UPDATE event SET `description` = ?, `nomEvent` = ?,`timestamp`= ?  WHERE `idEvent` = '"+id+"' ;");
        requeteInsert.setString(1, desc);
        requeteInsert.setString(2, nom);
        requeteInsert.setString(3, date);
        
        requeteInsert.execute();
       
    
   }
    
    
    public void delete(int id) throws SQLException {
        PreparedStatement PS = con.prepareStatement("DELETE FROM `edutech`.`event` WHERE `idEvent`=?");
        PS.setInt(1,id);
        PS.executeUpdate();
    }
    
    
    public void supprimerEvent(Eventt m) {
        try {
        	Statement st ;
        String requeteDelete =
                "DELETE FROM Event where Event.idEvent=2";
                st = con.createStatement();
                st.executeUpdate(requeteDelete);
                System.out.println("user supprimée____ "+m.toString());
        }
        catch (SQLException ex){
            System.out.println(ex.getMessage());
        }     }

   // @Override
    public void afficherListEvent() {
        ResultSet rs;
        Statement st ;
        String requete = "select * from event ";
        try {
            st = con.createStatement();
            rs= st.executeQuery(requete);
            while(rs.next()){
                System.out.println("ID: "+rs.getString("idEvent")+" , Nom: "+rs.getString("nomEvent")+" , Type: "+rs.getString("type")+" , Date Event "+rs.getString("dateEvent")+" , heure "+rs.getString("heure"));

               
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEvent.class.getName()).log(Level.SEVERE, null, ex);
        }   
         }
	/*@Override
	public void update(String nomm, String description, Timestamp timestamp) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void modifierEvent(Eventt m) {
		// TODO Auto-generated method stub
		
	}*/
	
	
	
	
	}
    

