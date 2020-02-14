/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Services.Cours;

import Entite.Cours;
import IServices.Cours.IserviceCours;
import Utils.DataBase;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author saghir
 */
public class ServiceCour implements IserviceCours<Object>
{
    private final Connection con;
    private Statement ste;
    PreparedStatement pst;
    ResultSet rs;

    public ServiceCour()
    {
        DataBase mc=DataBase.getInstance();
        con=mc.getConnection();
    }



    @Override
    public void ajouter(Object c1)
    {
        try
        {
            Cours c = (Cours)c1;


            String requeteInsert = "INSERT INTO cours(nomCours,fichier) VALUES(?,?)";

            pst =con.prepareStatement(requeteInsert);

            pst.setString(1, c.getNomCours());
            ByteArrayInputStream bais = new ByteArrayInputStream(getByteArrayFromFile(c.getFichier()));
            pst.setBlob(2, bais);
            pst.executeUpdate();
            System.out.println("cours ajouté");

        }
        catch (IOException | SQLException ex)
        {
            Logger.getLogger(ServiceCour.class.getName()).log(Level.SEVERE, null, ex);
        }

    }


    @Override
    public void modifier(int id,Object c1)
    {
        if(c1 instanceof Cours)
        {
            Cours c = (Cours)c1;
            try
            {
                String requeteUpdate =
                    "UPDATE cours SET nomCours = ?, fichier=?  "+"WHERE idCours='"+id+"'";

                pst =con.prepareStatement(requeteUpdate);

                pst.setString(1, c.getNomCours());
                ByteArrayInputStream bais = new ByteArrayInputStream(getByteArrayFromFile(c.getFichier()));
            pst.setBlob(2, bais);

                pst.executeUpdate();
                System.out.println("matiere modifier");


            }
            catch (SQLException ex)
            {
                System.out.println(ex.getMessage());
            } catch (IOException ex) {
                Logger.getLogger(ServiceCour.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else if(c1 instanceof String)
        {
            String s = (String) c1;
            try
            {
                String requeteUpdate =
                    "UPDATE cours SET nomCours = ?  "+"WHERE idCours='"+id+"'";

                pst =con.prepareStatement(requeteUpdate);

                pst.setString(1, s);

                pst.executeUpdate();
                System.out.println("matiere modifier");


            }
            catch (SQLException ex)
            {
                System.out.println(ex.getMessage());
            }
        }
    }

    @Override
    public void supprimer(Object c1)
    {

        Cours c= (Cours)c1;
        try
        {
            String requeteDelete =
                "DELETE FROM cours where nomCours='"+c.getNomCours()+"'";
            ste =con.createStatement();
            ste.executeUpdate(requeteDelete);
            System.out.println("user supprimée____ ");
        }
        catch (SQLException ex)
        {
            System.out.println(ex.getMessage());


        }
    }

    @Override
    public void afficherList()
    {

        String requete = "select * from matiere ";
        try
        {
            ste =con.createStatement();
            rs= ste.executeQuery(requete);
            while(rs.next())
            {
                System.out.println("ID: "+rs.getString("idMatiere")+" , Nom: "+rs.getString("nomMatiere")+" , Coefficient: "+rs.getString("coefmatiere"));


            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(ServiceMatiere.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public byte[] getByteArrayFromFile(final File handledDocument) throws IOException
    {
        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try ( // Ici c'est un objet Document, mais on peut y mettre un File (voir Javadoc pour FileInputStream).
                InputStream in = new FileInputStream(handledDocument)) {
            final byte[] buffer = new byte[500];
            int read ;
            while ((read = in.read(buffer)) > 0)
            {
                baos.write(buffer, 0, read);
            }
        }
        // Retour un tableau de byte (byte[]).
        return baos.toByteArray();
    }

    public void telechargerFichier(Cours c) throws FileNotFoundException, SQLException, IOException
    {
        File monFichier= new File("C:\\Users\\saghir\\Desktop\\"+c.getNomCours()+".pdf");
        try
        (FileOutputStream ostreamFichier = new FileOutputStream(monFichier); // La table étant très sommaire, tu peux rajouter des champs pour étendre tes critères et faciliter la récupération de ton BLOB
                PreparedStatement ps = con.prepareStatement("select fichier from cours where nomCours=?")) {
            ps.setString(1,c.getNomCours());
            rs = ps.executeQuery();
            try
            {
                if(rs.next())
                {
                    InputStream istreamFichier = rs.getBinaryStream("fichier");
                    
                    byte[] buffer = new byte[1024];
                    int length ;
                    
                    while((length = istreamFichier.read(buffer)) != -1)
                    {
                        ostreamFichier.write(buffer, 0, length);
                    }
                }
            }
            finally
            {
                rs.close();
            }
        }
        System.out.println("Fichier téléchargé");
    }


}
