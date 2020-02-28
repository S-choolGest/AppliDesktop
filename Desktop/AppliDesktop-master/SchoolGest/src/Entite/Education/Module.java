/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Entite.Education;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author saghir
 */

public class Module {
        private int idModule;
        private int idSpecialite;
	private String nomModule;
	private int coefModule;
        List<Matiere> matieres;

    public Module(int idModule, int idSpecialite, String nomModule, int coefModule) {
        this.idModule = idModule;
        this.idSpecialite = idSpecialite;
        this.nomModule = nomModule;
        this.coefModule = coefModule;
    }
	
	
        public Module(String nomModule, int coefModule) {
        this.matieres=new ArrayList<>();
        this.nomModule = nomModule;
        this.coefModule = coefModule;
        }
        
    public Module() {
        this.matieres=new ArrayList<>();
    }

    public Module(int idModule, String nomModule, int coefModule) {
        this.idModule = idModule;
        this.nomModule = nomModule;
        this.coefModule = coefModule;
    }

    public int getIdSpecialite() {
        return idSpecialite;
    }

    public void setIdSpecialite(int idSpecialite) {
        this.idSpecialite = idSpecialite;
    }
    
	public int getIdModule() {
		return idModule;
	}
	public void setIdModule(int idModule) {
		this.idModule = idModule;
	}
	public String getNomModule() {
		return nomModule;
	}
	public void setNomModule(String nomModule) {
		this.nomModule = nomModule;
	}
	public int getCoefModule() {
		return coefModule;
	}
	public void setCoefModule(int coefModule) {
		this.coefModule = coefModule;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + coefModule;
		result = prime * result + idModule;
		result = prime * result + ((nomModule == null) ? 0 : nomModule.hashCode());
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
		Module other = (Module) obj;
		if (coefModule != other.coefModule)
			return false;
		if (idModule != other.idModule)
			return false;
		if (nomModule == null) {
			if (other.nomModule != null)
				return false;
		} else if (!nomModule.equals(other.nomModule))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return  nomModule ;
	}
}
