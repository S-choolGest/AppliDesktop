/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services.Education;

import Entite.Bibliotheque.LivreEmprunte;
import Entite.Education.*;
import IServices.Cours.IserviceCours;
import Utils.DataBase;
import java.awt.Desktop;
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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 * @author saghir
 */
public class ServiceCour implements IserviceCours<Object> {

    private final Connection con;
    private Statement ste;
    PreparedStatement pst;
    ResultSet rs;

    public ServiceCour() {
        DataBase mc = DataBase.getInstance();
        con = mc.getConnection();
    }

    @Override
    public void ajouter(Object c1) {
        try {
            Cours c = (Cours) c1;

            String requeteInsert = "INSERT INTO cours(nomCours,fichier) VALUES(?,?)";

            pst = con.prepareStatement(requeteInsert);

            pst.setString(1, c.getNomCours());
            ByteArrayInputStream bais = new ByteArrayInputStream(getByteArrayFromFile(c.getChapitre()));
            pst.setBlob(2, bais);
            pst.executeUpdate();
            System.out.println("cours ajouté");

        } catch (IOException | SQLException ex) {
            Logger.getLogger(ServiceCour.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void modifier(int id, Object c1) {
        if (c1 instanceof Cours) {
            Cours c = (Cours) c1;
            try {
                String requeteUpdate
                        = "UPDATE cours SET nomCours = ?, fichier=?  " + "WHERE idCours='" + id + "'";

                pst = con.prepareStatement(requeteUpdate);

                pst.setString(1, c.getNomCours());
                ByteArrayInputStream bais = new ByteArrayInputStream(getByteArrayFromFile(c.getChapitre()));
                pst.setBlob(2, bais);

                pst.executeUpdate();
                System.out.println("matiere modifier");

            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            } catch (IOException ex) {
                Logger.getLogger(ServiceCour.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (c1 instanceof String) {
            String s = (String) c1;
            try {
                String requeteUpdate
                        = "UPDATE cours SET nomCours = ?  " + "WHERE idCours='" + id + "'";

                pst = con.prepareStatement(requeteUpdate);

                pst.setString(1, s);

                pst.executeUpdate();
                System.out.println("matiere modifier");

            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    @Override
    public void supprimer(Object c1) {

        Cours c = (Cours) c1;
        try {
            String requeteDelete
                    = "DELETE FROM cours where nomCours='" + c.getNomCours() + "'";
            ste = con.createStatement();
            ste.executeUpdate(requeteDelete);
            System.out.println("user supprimée____ ");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
    }

    @Override
    public void afficherList() {

        String requete = "select * from matiere ";
        try {
            ste = con.createStatement();
            rs = ste.executeQuery(requete);
            while (rs.next()) {
                System.out.println("ID: " + rs.getString("idMatiere") + " , Nom: " + rs.getString("nomMatiere") + " , Coefficient: " + rs.getString("coefmatiere"));

            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceMatiere.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public byte[] getByteArrayFromFile(final File handledDocument) throws IOException {
        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try ( // Ici c'est un objet Document, mais on peut y mettre un File (voir Javadoc pour FileInputStream).
                InputStream in = new FileInputStream(handledDocument)) {
            final byte[] buffer = new byte[500];
            int read;
            while ((read = in.read(buffer)) > 0) {
                baos.write(buffer, 0, read);
            }
        }
        // Retour un tableau de byte (byte[]).
        return baos.toByteArray();
    }

    public File telechargerFichier(Object c1) throws FileNotFoundException, SQLException, IOException {
        DirectoryChooser directoryChooser = new DirectoryChooser();
            
            File file = directoryChooser.showDialog(new Stage());
        if (c1 instanceof Cours) {
            
            Cours c = (Cours) c1;
            
            File monFichier = new File(file.getPath()+"/" + c.getNomCours() + ".pdf");
            try (FileOutputStream ostreamFichier = new FileOutputStream(monFichier); // La table étant très sommaire, tu peux rajouter des champs pour étendre tes critères et faciliter la récupération de ton BLOB
                    PreparedStatement ps = con.prepareStatement("select fichier from cours where nomCours=?")) {
                ps.setString(1, c.getNomCours());
                rs = ps.executeQuery();
                try {
                    if (rs.next()) {
                        InputStream istreamFichier = rs.getBinaryStream("fichier");

                        byte[] buffer = new byte[1024];
                        int length;

                        while ((length = istreamFichier.read(buffer)) != -1) {
                            ostreamFichier.write(buffer, 0, length);
                        }
                    }
                } finally {
                    rs.close();
                }
                System.out.println("Fichier téléchargé");

                return monFichier;

            }

        } else if (c1 instanceof String) {
            String c = (String) c1;
            File monFichier = new File(file.getPath()+"/" + c + ".pdf");
            try (FileOutputStream ostreamFichier = new FileOutputStream(monFichier); // La table étant très sommaire, tu peux rajouter des champs pour étendre tes critères et faciliter la récupération de ton BLOB
                    PreparedStatement ps = con.prepareStatement("select fichier from cours where nomCours=?")) {
                ps.setString(1, c);
                rs = ps.executeQuery();
                try {
                    if (rs.next()) {
                        InputStream istreamFichier = rs.getBinaryStream("fichier");

                        byte[] buffer = new byte[1024];
                        int length;

                        while ((length = istreamFichier.read(buffer)) != -1) {
                            ostreamFichier.write(buffer, 0, length);
                        }
                    }
                } finally {
                    rs.close();
                }

                return monFichier;
            }

        }
        return null;
    }

    public ObservableList<Cours> getAllCours() throws IOException {

        ObservableList<Cours> list = FXCollections.observableArrayList();
        String requete = "select * from cours ";
        try {
            Statement st = con.createStatement();
            rs = st.executeQuery(requete);

            while (rs.next()) {
                File monFichier = new File("C:\\Users\\saghir\\Desktop\\tmp.pdf");
                FileOutputStream ostreamFichier = new FileOutputStream(monFichier);
                InputStream istreamFichier = rs.getBinaryStream("fichier");

                byte[] buffer = new byte[1024];
                int length;

                while ((length = istreamFichier.read(buffer)) != -1) {
                    ostreamFichier.write(buffer, 0, length);
                }
                list.add(new Cours(rs.getInt(1), rs.getInt(2), rs.getString(3), monFichier));
                monFichier.delete();

            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceSpecialite.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }

    public void consulterFichier(Cours c) {

        Desktop desktop = Desktop.getDesktop();
        try {
            desktop.open(c.getChapitre());
        } catch (IOException e) {
        }

    }

    public ObservableList<Cours> rechercheCours(String text) throws SQLException, IOException {
        ObservableList<Cours> cours = FXCollections.observableArrayList();

        String t = text.toLowerCase();
        ObservableList<Cours> crs = FXCollections.observableArrayList();
        try {

            for (Cours c : getAllCours()) {
                if (c.getNomCours().toLowerCase().contains(t)) {
                    crs.add(c);
                }
            }
        } catch (IOException e) {
            System.out.println(e);
        }
        return crs;
    }
}
