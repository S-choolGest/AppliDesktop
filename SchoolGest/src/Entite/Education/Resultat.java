package Entite.Education;

public class Resultat {
	private int idResultat;
	private float moyenne;
	private EtatResultat etatRes;
	private EtatMention etatMen;
	public Resultat(int idResultat, float moyenne, EtatResultat etatRes, EtatMention etatMen) {
		super();
		this.idResultat = idResultat;
		this.moyenne = moyenne;
		this.etatRes = etatRes;
		this.etatMen = etatMen;
	}
	public Resultat(float moyenne, EtatResultat etatRes, EtatMention etatMen) {
		super();
		this.moyenne = moyenne;
		this.etatRes = etatRes;
		this.etatMen = etatMen;
	}
	public Resultat() {
		super();
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((etatMen == null) ? 0 : etatMen.hashCode());
		result = prime * result + ((etatRes == null) ? 0 : etatRes.hashCode());
		result = prime * result + idResultat;
		result = prime * result + Float.floatToIntBits(moyenne);
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
		Resultat other = (Resultat) obj;
		if (etatMen != other.etatMen)
			return false;
		if (etatRes != other.etatRes)
			return false;
		if (idResultat != other.idResultat)
			return false;
		if (Float.floatToIntBits(moyenne) != Float.floatToIntBits(other.moyenne))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Resultat [idResultat=" + idResultat + ", moyenne=" + moyenne + ", etatRes=" + etatRes + ", etatMen="
				+ etatMen + "]";
	}
	public int getIdResultat() {
		return idResultat;
	}
	public void setIdResultat(int idResultat) {
		this.idResultat = idResultat;
	}
	public float getMoyenne() {
		return moyenne;
	}
	public void setMoyenne(float moyenne) {
		this.moyenne = moyenne;
	}
	public EtatResultat getEtatRes() {
		return etatRes;
	}
	public void setEtatRes(EtatResultat etatRes) {
		this.etatRes = etatRes;
	}
	public EtatMention getEtatMen() {
		return etatMen;
	}
	public void setEtatMen(EtatMention etatMen) {
		this.etatMen = etatMen;
	}
	
	
	
	

}
