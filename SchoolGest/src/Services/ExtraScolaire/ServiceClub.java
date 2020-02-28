

package Services.ExtraScolaire;

import IServices.ExtraScolaire.*;


import Utils.DataBase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import Entite.ExtraScolaire.Club;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServiceClub  implements IServiceClub {

    private Connection con;
    private Statement ste;
     public ServiceClub() {
        con = DataBase.getInstance().getConnection();
    }
     public List readAll() throws SQLException {
         List<Club> arr=new ArrayList<>();
     ste=con.createStatement();
     ResultSet rs=ste.executeQuery("select * from `edutech`.`club`");
     while (rs.next()) {                
                int idClub=rs.getInt(1);
                String nomClub=rs.getString(2);
                String categorie=rs.getString(2);
                Club e = new Club(idClub, nomClub , categorie);
      arr.add(e);
      }
     return arr;
     }
     public void update1(String cate,String nomClub,int id) throws SQLException {
        
        //String requeteInsert ="UPDATE `edutech`.`event` SET `timestamp`= '"+a.getDate()+"',`nomEvent` = '"+a.getNomEvent()+"',`description` = '"+a.getDescription()+"' WHERE `idEvent` = '"+a.getIdEvent()+"'";
        PreparedStatement requeteInsert;
            requeteInsert = con.prepareStatement("UPDATE club SET `categorieClub` = ?, `nomClub` = ? WHERE `idClub` = '"+id+"' ;");
        requeteInsert.setString(1, cate);
        requeteInsert.setString(2, nomClub);
        
        
        requeteInsert.execute();
       
    
   }
     
     public ObservableList<Club> readAllV2() throws SQLException {
    	    ObservableList<Club> AbsenceData = FXCollections.observableArrayList();
    	    ste=con.createStatement();
    	    ResultSet rs=ste.executeQuery("select * from `edutech`.`club`");
    	    while (rs.next()) {                
    	               int idClub=rs.getInt(1);
                String nomClub=rs.getString(2);
                String categorie=rs.getString(2);
                Club p = new Club(idClub, nomClub , categorie);
    	     AbsenceData.add(p);
    	     }
    	    return AbsenceData;
    	    }
     
     
     
      public ObservableList<Club> readAll1() throws SQLException {
          ObservableList<Club> ClubData = FXCollections.observableArrayList();
     ste=con.createStatement();
     ResultSet rs=ste.executeQuery("select * from `edutech`.`club`");
     while (rs.next()) {                
    	  int idClub=rs.getInt(1);
                String nomClub=rs.getString(2);
                String categorie=rs.getString(2);
                Club e = new Club(idClub, nomClub , categorie);
      ClubData.add(e);
      }
     return ClubData;
     }
     
    public void ajouterclub(Club e) throws SQLException
    { 
    	
        ste = con.createStatement();
        String requeteInsert = "INSERT INTO `edutech`.`club` (`idClub`, `nomClub`, `categorieClub`) VALUES (NULL, '" +  e.getNomClub() + "', '" + e.getCategorieClub() + "');";        
        ste.executeUpdate(requeteInsert);
    
    }
    
    public boolean delete(Club a) throws SQLException {
        ste = con.createStatement();
        String requeteInsert = "DELETE FROM `edutech`.`club` WHERE `idClub` = '"+a.getIdClub()+"'";
        if(ste.executeUpdate(requeteInsert) == 1){
            System.out.println("Suppression effectué");
            return true;
        };
        System.out.println("Suppression non effectué");
        return false;
    }
    
    
 
   
    public boolean update(Club a) throws SQLException {
        ste = con.createStatement();
        //String requeteInsert ="UPDATE `edutech`.`event` SET `timestamp`= '"+a.getDate()+"',`nomEvent` = '"+a.getNomEvent()+"',`description` = '"+a.getDescription()+"' WHERE `idEvent` = '"+a.getIdEvent()+"'";
        String requeteInsert ="UPDATE `edutech`.`club` SET `nomClub` = '"+a.getNomClub()+"', `categorieClub` = '"+a.getCategorieClub()+"'  WHERE `idEvent` = '"+a.getIdClub()+"'";

        if(ste.executeUpdate(requeteInsert) == 1){
           System.out.println("modification effectué");
           return true;
       };
       System.out.println("modification non effectué");
       return false;
   }
    
    
    public void delete(int id) throws SQLException {
        PreparedStatement PS = con.prepareStatement("DELETE FROM `edutech`.`event` WHERE `idEvent`=?");
        PS.setInt(1,id);
        PS.executeUpdate();
    }
    
    
    public void supprimerClub(Club m) {
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
        String requete = "select * from club ";
        try {
            st = con.createStatement();
            rs= st.executeQuery(requete);
            while(rs.next()){
                System.out.println("ID: "+rs.getString("idClub")+" , Nom: "+rs.getString("nomClub")+" , categorie: "+rs.getString("categorieClub"));

               
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
	public void modifierEvent(Club m) {
		// TODO Auto-generated method stub
		
	}*/
    
         public List<Club> RECHERCHER(String nomm) throws SQLException {   //recherche par spécialité
    List<Club> arr=new ArrayList<>();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from club where `nomClub` = '"+nomm+"' ");
     while (rs.next()) {                
               int idClub=rs.getInt(1);
               String nomClub=rs.getString("nomClub");
                String categorieClub=rs.getString("categorieClub");
       
               Club p=new Club(idClub, nomClub, categorieClub);
     arr.add(p);
     }
    return arr;
    }
       
      public List<Club> trier() throws SQLException {
    List<Club> arr=new ArrayList<>();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from club order by idClub desc");
     while (rs.next()) {                
               int idClub=rs.getInt(1);
               String nomClub=rs.getString("nomClub");
                String categorieClub=rs.getString("categorieClub");
                
               Club p=new Club(idClub, nomClub, categorieClub);
     arr.add(p);
     }
    return arr;
    }

    @Override
    public void AjouterClub(Club e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modifierClub(Club m) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void afficherListClub() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   
   
   
	
	
	
	}
    
