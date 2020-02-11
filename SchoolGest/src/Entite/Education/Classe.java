package Entite.Education;

public class Classe {
	private int idClasse;
	private int numClasse;
	
	public Classe(int idClasse, int numClasse) {
		super();
		this.idClasse = idClasse;
		this.numClasse = numClasse;
	}

	public Classe(int numClasse) {
		super();
		this.numClasse = numClasse;
	}

	public Classe() {
		super();
	}

	public int getIdClasse() {
		return idClasse;
	}

	public void setIdClasse(int idClasse) {
		this.idClasse = idClasse;
	}

	public int getNumClasse() {
		return numClasse;
	}

	public void setNumClasse(int numClasse) {
		this.numClasse = numClasse;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idClasse;
		result = prime * result + numClasse;
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
		Classe other = (Classe) obj;
		if (idClasse != other.idClasse)
			return false;
		if (numClasse != other.numClasse)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Classe [idClasse=" + idClasse + ", numClasse=" + numClasse + "]";
	}
	
	
	

}
