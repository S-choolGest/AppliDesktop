package Entite.Utilisateur;


public class Parents extends Utilisateur {
	private int idParent;

    public Parents(int idParent,int id, String nom, String prenom, String email, String password, String cin, int numTel, String dateNaissance, String adresse, int type) {
        super(id, nom, prenom, email, password, cin, numTel, dateNaissance, adresse, type);
        this.idParent = idParent;
    }
}
