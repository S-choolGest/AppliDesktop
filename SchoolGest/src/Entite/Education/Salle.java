package Entite.Education;

public class Salle {
	private int idSalle;
	private String nomSalle;
	private boolean DisponibilitéSalle;
	private int nbPlace;
	public Salle(int idSalle, String nomSalle, boolean disponibilitéSalle, int nbPlace) {
		super();
		this.idSalle = idSalle;
		this.nomSalle = nomSalle;
		DisponibilitéSalle = disponibilitéSalle;
		this.nbPlace = nbPlace;
	}
	public Salle(String nomSalle, boolean disponibilitéSalle, int nbPlace) {
		super();
		this.nomSalle = nomSalle;
		DisponibilitéSalle = disponibilitéSalle;
		this.nbPlace = nbPlace;
	}
	public Salle() {
		super();
	}
	public int getIdSalle() {
		return idSalle;
	}
	public void setIdSalle(int idSalle) {
		this.idSalle = idSalle;
	}
	public String getNomSalle() {
		return nomSalle;
	}
	public void setNomSalle(String nomSalle) {
		this.nomSalle = nomSalle;
	}
	public boolean isDisponibilitéSalle() {
		return DisponibilitéSalle;
	}
	public void setDisponibilitéSalle(boolean disponibilitéSalle) {
		DisponibilitéSalle = disponibilitéSalle;
	}
	public int getNbPlace() {
		return nbPlace;
	}
	public void setNbPlace(int nbPlace) {
		this.nbPlace = nbPlace;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (DisponibilitéSalle ? 1231 : 1237);
		result = prime * result + idSalle;
		result = prime * result + nbPlace;
		result = prime * result + ((nomSalle == null) ? 0 : nomSalle.hashCode());
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
		Salle other = (Salle) obj;
		if (DisponibilitéSalle != other.DisponibilitéSalle)
			return false;
		if (idSalle != other.idSalle)
			return false;
		if (nbPlace != other.nbPlace)
			return false;
		if (nomSalle == null) {
			if (other.nomSalle != null)
				return false;
		} else if (!nomSalle.equals(other.nomSalle))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Salle [idSalle=" + idSalle + ", nomSalle=" + nomSalle + ", DisponibilitéSalle=" + DisponibilitéSalle
				+ ", nbPlace=" + nbPlace + "]";
	}
	
	
	

}
