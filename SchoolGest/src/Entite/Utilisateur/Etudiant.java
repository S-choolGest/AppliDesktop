package Entite.Utilisateur;

import java.util.*;

public class Etudiant {
	private int idEtudiant;
	private String cinEtudiant;
	private String nomEtudiant;
	private String prenomEtudiant;
	private Date Date_NaissEtudiant;
	private String emailEtudiant;
	private int num_telEtudiant;
	private String adressEtudiant;
	private NiveauEtudiant niveauEt;
	
	
	
	public Etudiant( String cinEtudiant, String nomEtudiant, String prenomEtudiant, Date date_NaissEtudiant,
			String emailEtudiant, int num_telEtudiant, String adressEtudiant, NiveauEtudiant niveauEt ) {
		super();
		
		this.cinEtudiant = cinEtudiant;
		this.nomEtudiant = nomEtudiant;
		this.prenomEtudiant = prenomEtudiant;
		Date_NaissEtudiant = date_NaissEtudiant;
		this.emailEtudiant = emailEtudiant;
		this.num_telEtudiant = num_telEtudiant;
		this.adressEtudiant = adressEtudiant;
		this.niveauEt = niveauEt ;
		
		
		
	}



	public Etudiant() {
		
	}



	public int getIdEtudiant() {
		return idEtudiant;
	}



	public void setIdEtudiant(int idEtudiant) {
		this.idEtudiant = idEtudiant;
	}



	public String getCinEtudiant() {
		return cinEtudiant;
	}



	public void setCinEtudiant(String cinEtudiant) {
		this.cinEtudiant = cinEtudiant;
	}



	public String getNomEtudiant() {
		return nomEtudiant;
	}



	public void setNomEtudiant(String nomEtudiant) {
		this.nomEtudiant = nomEtudiant;
	}



	public String getPrenomEtudiant() {
		return prenomEtudiant;
	}



	public void setPrenomEtudiant(String prenomEtudiant) {
		this.prenomEtudiant = prenomEtudiant;
	}



	public Date getDate_NaissEtudiant() {
		return Date_NaissEtudiant;
	}



	public void setDate_NaissEtudiant(Date date_NaissEtudiant) {
		Date_NaissEtudiant = date_NaissEtudiant;
	}



	public String getEmailEtudiant() {
		return emailEtudiant;
	}



	public void setEmailEtudiant(String emailEtudiant) {
		this.emailEtudiant = emailEtudiant;
	}



	public int getNum_telEtudiant() {
		return num_telEtudiant;
	}



	public void setNum_telEtudiant(int num_telEtudiant) {
		this.num_telEtudiant = num_telEtudiant;
	}



	public String getAdressEtudiant() {
		return adressEtudiant;
	}



	public void setAdressEtudiant(String adressEtudiant) {
		this.adressEtudiant = adressEtudiant;
	}



	public NiveauEtudiant getNiveauEt() {
		return niveauEt;
	}



	public void setNiveauEt(NiveauEtudiant niveauEt) {
		this.niveauEt = niveauEt;
	}



	@Override
	public String toString() {
		return "Etudiant [idEtudiant=" + idEtudiant + ", cinEtudiant=" + cinEtudiant + ", nomEtudiant=" + nomEtudiant
				+ ", prenomEtudiant=" + prenomEtudiant + ", Date_NaissEtudiant=" + Date_NaissEtudiant
				+ ", emailEtudiant=" + emailEtudiant + ", num_telEtudiant=" + num_telEtudiant + ", adressEtudiant="
				+ adressEtudiant + ", niveauEt=" + niveauEt + "]";
	}



	//@Override
//	public int hashCode() {
//		final int prime = 31;
//		int result = 1;
//		result = prime * result + ((Date_NaissEtudiant == null) ? 0 : Date_NaissEtudiant.hashCode());
//		result = prime * result + ((adressEtudiant == null) ? 0 : adressEtudiant.hashCode());
//		result = prime * result + cinEtudiant;
//		result = prime * result + ((emailEtudiant == null) ? 0 : emailEtudiant.hashCode());
//		result = prime * result + idEtudiant;
//		result = prime * result + ((niveauEt == null) ? 0 : niveauEt.hashCode());
//		result = prime * result + ((nomEtudiant == null) ? 0 : nomEtudiant.hashCode());
//		result = prime * result + num_telEtudiant;
//		result = prime * result + ((prenomEtudiant == null) ? 0 : prenomEtudiant.hashCode());
//		return result;
//	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Etudiant other = (Etudiant) obj;
		if (Date_NaissEtudiant == null) {
			if (other.Date_NaissEtudiant != null)
				return false;
		} else if (!Date_NaissEtudiant.equals(other.Date_NaissEtudiant))
			return false;
		if (adressEtudiant == null) {
			if (other.adressEtudiant != null)
				return false;
		} else if (!adressEtudiant.equals(other.adressEtudiant))
			return false;
		if (cinEtudiant != other.cinEtudiant)
			return false;
		if (emailEtudiant == null) {
			if (other.emailEtudiant != null)
				return false;
		} else if (!emailEtudiant.equals(other.emailEtudiant))
			return false;
		if (idEtudiant != other.idEtudiant)
			return false;
		if (niveauEt != other.niveauEt)
			return false;
		if (nomEtudiant == null) {
			if (other.nomEtudiant != null)
				return false;
		} else if (!nomEtudiant.equals(other.nomEtudiant))
			return false;
		if (num_telEtudiant != other.num_telEtudiant)
			return false;
		if (prenomEtudiant == null) {
			if (other.prenomEtudiant != null)
				return false;
		} else if (!prenomEtudiant.equals(other.prenomEtudiant))
			return false;
		return true;
	}
	
	
	
	
	
	

}
