/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Entite.Bibliotheque;

/**
 *
 * @author william
 */
public class Bibliotheque {
    private int id;
    private String nom;
    private int capacite;
	private String adresse;
	private String email;

    public Bibliotheque(int id, String nom, int capacite, String adresse, String email) {
        this.id = id;
        this.nom = nom;
        this.capacite = capacite;
        this.email = email;
        this.adresse = adresse;
    }

    public Bibliotheque(String nom, int capacite, String adresse, String email) {
        this.nom = nom;
        this.capacite = capacite;
        this.email = email;
        this.adresse = adresse;
    }

	public Bibliotheque(String nom, int capacite, String adresse) {
		this.nom = nom;
		this.capacite = capacite;
		this.adresse = adresse;
	}

	public Bibliotheque(int id, String email) {
		this.id = id;
		this.email = email;
	}

    public Bibliotheque(int id) {
        this.id = id;
    }
    
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getCapacite() {
        return capacite;
    }

    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	@Override
	public String toString() {
		return "Bibliotheque{" + "id=" + id + ", nom=" + nom + ", capacite=" + capacite + ", adresse=" + adresse + ", email=" + email + '}';
	}
    
}
