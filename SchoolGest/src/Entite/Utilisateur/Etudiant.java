package Entite.Utilisateur;


public class Etudiant extends Utilisateur{
	 int id ;
    String nom;
    String prenom;

    public Etudiant(int id, String nom, String prenom, String email, String password, String cin, int numTel, String dateNaissance, String adresse, int type) {
        super(id, nom, prenom, email, password, cin, numTel, dateNaissance, adresse, type);
    }

    

   

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    @Override
    public String toString() {
        return "Etudiant{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + '}';
    }



	


	
	
	
	
	
	

}
