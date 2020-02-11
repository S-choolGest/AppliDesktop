package Entite.Education;

import java.util.*;

import Entite.Utilisateur.Etudiant;

public class Absence {
	private int idAbsence;
	private List<Matiere> listMatiere ;
	private Date DateAbs;
	private List<Etudiant> listEtudiant;
	private EtatAbsence etatAbs;
	
	
	public Absence(int idAbsence, List<Matiere> listMatiere, Date dateAbs, List<Etudiant> listEtudiant,
			EtatAbsence etatAbs) {
		super();
		this.idAbsence = idAbsence;
		this.listMatiere = listMatiere;
		DateAbs = dateAbs;
		this.listEtudiant = listEtudiant;
		this.etatAbs = etatAbs;
	}


	public Absence() {
		super();
	}


	public Absence(List<Matiere> listMatiere, Date dateAbs, List<Etudiant> listEtudiant, EtatAbsence etatAbs) {
		super();
		this.listMatiere = listMatiere;
		DateAbs = dateAbs;
		this.listEtudiant = listEtudiant;
		this.etatAbs = etatAbs;
	}


	public int getIdAbsence() {
		return idAbsence;
	}


	public void setIdAbsence(int idAbsence) {
		this.idAbsence = idAbsence;
	}


	public List<Matiere> getListMatiere() {
		return listMatiere;
	}


	public void setListMatiere(List<Matiere> listMatiere) {
		this.listMatiere = listMatiere;
	}


	public Date getDateAbs() {
		return DateAbs;
	}


	public void setDateAbs(Date dateAbs) {
		DateAbs = dateAbs;
	}


	public List<Etudiant> getListEtudiant() {
		return listEtudiant;
	}


	public void setListEtudiant(List<Etudiant> listEtudiant) {
		this.listEtudiant = listEtudiant;
	}


	public EtatAbsence getEtatAbs() {
		return etatAbs;
	}


	public void setEtatAbs(EtatAbsence etatAbs) {
		this.etatAbs = etatAbs;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((DateAbs == null) ? 0 : DateAbs.hashCode());
		result = prime * result + ((etatAbs == null) ? 0 : etatAbs.hashCode());
		result = prime * result + idAbsence;
		result = prime * result + ((listEtudiant == null) ? 0 : listEtudiant.hashCode());
		result = prime * result + ((listMatiere == null) ? 0 : listMatiere.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Absence other = (Absence) obj;
		if (DateAbs == null) {
			if (other.DateAbs != null)
				return false;
		} else if (!DateAbs.equals(other.DateAbs))
			return false;
		if (etatAbs != other.etatAbs)
			return false;
		if (idAbsence != other.idAbsence)
			return false;
		if (listEtudiant == null) {
			if (other.listEtudiant != null)
				return false;
		} else if (!listEtudiant.equals(other.listEtudiant))
			return false;
		if (listMatiere == null) {
			if (other.listMatiere != null)
				return false;
		} else if (!listMatiere.equals(other.listMatiere))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "Absence [idAbsence=" + idAbsence + ", listMatiere=" + listMatiere + ", DateAbs=" + DateAbs
				+ ", listEtudiant=" + listEtudiant + ", etatAbs=" + etatAbs + "]";
	}
	
	
	
	
	

}

