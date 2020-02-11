package Entite.Education;

import java.util.*;

public class Matiere {
	private int idMatiere;
	private String nomMatiere;
	private int coefMatiere;
	private List<Module> listModule;
	public Matiere(int idMatiere, String nomMatiere, int coefMatiere, List<Module> listModule) {
		super();
		this.idMatiere = idMatiere;
		this.nomMatiere = nomMatiere;
		this.coefMatiere = coefMatiere;
		this.listModule = listModule;
	}
	public Matiere(String nomMatiere, int coefMatiere, List<Module> listModule) {
		super();
		this.nomMatiere = nomMatiere;
		this.coefMatiere = coefMatiere;
		this.listModule = listModule;
	}
	public Matiere() {
		super();
	}
	public int getIdMatiere() {
		return idMatiere;
	}
	public void setIdMatiere(int idMatiere) {
		this.idMatiere = idMatiere;
	}
	public String getNomMatiere() {
		return nomMatiere;
	}
	public void setNomMatiere(String nomMatiere) {
		this.nomMatiere = nomMatiere;
	}
	public int getCoefMatiere() {
		return coefMatiere;
	}
	public void setCoefMatiere(int coefMatiere) {
		this.coefMatiere = coefMatiere;
	}
	public List<Module> getListModule() {
		return listModule;
	}
	public void setListModule(List<Module> listModule) {
		this.listModule = listModule;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + coefMatiere;
		result = prime * result + idMatiere;
		result = prime * result + ((listModule == null) ? 0 : listModule.hashCode());
		result = prime * result + ((nomMatiere == null) ? 0 : nomMatiere.hashCode());
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
		Matiere other = (Matiere) obj;
		if (coefMatiere != other.coefMatiere)
			return false;
		if (idMatiere != other.idMatiere)
			return false;
		if (listModule == null) {
			if (other.listModule != null)
				return false;
		} else if (!listModule.equals(other.listModule))
			return false;
		if (nomMatiere == null) {
			if (other.nomMatiere != null)
				return false;
		} else if (!nomMatiere.equals(other.nomMatiere))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Matiere [idMatiere=" + idMatiere + ", nomMatiere=" + nomMatiere + ", coefMatiere=" + coefMatiere
				+ ", listModule=" + listModule + "]";
	}
	
	
	
	
	
}
	
	
	
	
	
	
	


