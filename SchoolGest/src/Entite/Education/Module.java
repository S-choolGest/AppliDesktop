package Entite.Education;

public class Module {
	private int idModule;
	private String nomModule;
	private int coefModule;
	public Module(int idModule, String nomModule, int coefModule) {
		super();
		this.idModule = idModule;
		this.nomModule = nomModule;
		this.coefModule = coefModule;
	}
	public Module(String nomModule, int coefModule) {
		super();
		this.nomModule = nomModule;
		this.coefModule = coefModule;
	}
	public Module() {
		super();
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
		return "Module [idModule=" + idModule + ", nomModule=" + nomModule + ", coefModule=" + coefModule + "]";
	}
	
	
	

}
