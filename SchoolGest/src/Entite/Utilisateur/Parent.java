package Entite.Utilisateur;
import java.util.*;

public class Parent {
	private int idParent;
	private String emailParent;
	private String numTelParent;
	private List<Etudiant> listEtudiant;
	
	public Parent(int idParent, String emailParent, String numTelParent, List<Etudiant> listEtudiant) {
		super();
		this.idParent = idParent;
		this.emailParent = emailParent;
		this.numTelParent = numTelParent;
		this.listEtudiant = listEtudiant;
	}

	public Parent() {
		super();
	}

	public Parent(String emailParent, String numTelParent, List<Etudiant> listEtudiant) {
		super();
		this.emailParent = emailParent;
		this.numTelParent = numTelParent;
		this.listEtudiant = listEtudiant;
	}

	public int getIdParent() {
		return idParent;
	}

	public void setIdParent(int idParent) {
		this.idParent = idParent;
	}

	public String getEmailParent() {
		return emailParent;
	}

	public void setEmailParent(String emailParent) {
		this.emailParent = emailParent;
	}

	public String getNumTelParent() {
		return numTelParent;
	}

	public void setNumTelParent(String numTelParent) {
		this.numTelParent = numTelParent;
	}

	public List<Etudiant> getListEtudiant() {
		return listEtudiant;
	}

	public void setListEtudiant(List<Etudiant> listEtudiant) {
		this.listEtudiant = listEtudiant;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((emailParent == null) ? 0 : emailParent.hashCode());
		result = prime * result + idParent;
		result = prime * result + ((listEtudiant == null) ? 0 : listEtudiant.hashCode());
		result = prime * result + ((numTelParent == null) ? 0 : numTelParent.hashCode());
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
		Parent other = (Parent) obj;
		if (emailParent == null) {
			if (other.emailParent != null)
				return false;
		} else if (!emailParent.equals(other.emailParent))
			return false;
		if (idParent != other.idParent)
			return false;
		if (listEtudiant == null) {
			if (other.listEtudiant != null)
				return false;
		} else if (!listEtudiant.equals(other.listEtudiant))
			return false;
		if (numTelParent == null) {
			if (other.numTelParent != null)
				return false;
		} else if (!numTelParent.equals(other.numTelParent))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Parent [idParent=" + idParent + ", emailParent=" + emailParent + ", numTelParent=" + numTelParent
				+ ", listEtudiant=" + listEtudiant + "]";
	}
	
	
	

}
