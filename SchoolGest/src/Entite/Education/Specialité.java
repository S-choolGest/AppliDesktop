package Entite.Education;
import java.util.*;

public class Specialité {
	private int idSpecialite;
	private String nomSpecialite;
	private List<Matiere> listMatiere;
	public Specialité(int idSpecialite, String nomSpecialite, List<Matiere> listMatiere) {
		super();
		this.idSpecialite = idSpecialite;
		this.nomSpecialite = nomSpecialite;
		this.listMatiere = listMatiere;
	}
	public Specialité(String nomSpecialite, List<Matiere> listMatiere) {
		super();
		this.nomSpecialite = nomSpecialite;
		this.listMatiere = listMatiere;
	}
	public Specialité() {
		super();
	}
	public int getIdSpecialite() {
		return idSpecialite;
	}
	public void setIdSpecialite(int idSpecialite) {
		this.idSpecialite = idSpecialite;
	}
	public String getNomSpecialite() {
		return nomSpecialite;
	}
	public void setNomSpecialite(String nomSpecialite) {
		this.nomSpecialite = nomSpecialite;
	}
	public List<Matiere> getListMatiere() {
		return listMatiere;
	}
	public void setListMatiere(List<Matiere> listMatiere) {
		this.listMatiere = listMatiere;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idSpecialite;
		result = prime * result + ((listMatiere == null) ? 0 : listMatiere.hashCode());
		result = prime * result + ((nomSpecialite == null) ? 0 : nomSpecialite.hashCode());
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
		Specialité other = (Specialité) obj;
		if (idSpecialite != other.idSpecialite)
			return false;
		if (listMatiere == null) {
			if (other.listMatiere != null)
				return false;
		} else if (!listMatiere.equals(other.listMatiere))
			return false;
		if (nomSpecialite == null) {
			if (other.nomSpecialite != null)
				return false;
		} else if (!nomSpecialite.equals(other.nomSpecialite))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Specialité [idSpecialite=" + idSpecialite + ", nomSpecialite=" + nomSpecialite + ", listMatiere="
				+ listMatiere + "]";
	}
	
	
	

}
