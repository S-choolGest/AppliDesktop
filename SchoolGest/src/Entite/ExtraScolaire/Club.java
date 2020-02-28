package Entite.ExtraScolaire;

public class Club {

	
	private int idClub;
    private String NomClub;
	private String categorieClub;
	public String getIdClub() {
        return Integer.toString(idClub);
    }
	
	
	public void setIdClub(int idClub) {
		this.idClub = idClub;
	}
	public String getNomClub() {
		return NomClub;
	}
	public void setNomClub(String nomClub) {
		NomClub = nomClub;
	}
	public String getCategorieClub() {
		return categorieClub;
	}
	public void setCategorieClub(String categorieClub) {
		this.categorieClub = categorieClub;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((NomClub == null) ? 0 : NomClub.hashCode());
		result = prime * result + ((categorieClub == null) ? 0 : categorieClub.hashCode());
		result = prime * result + idClub;
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
		Club other = (Club) obj;
		if (NomClub == null) {
			if (other.NomClub != null)
				return false;
		} else if (!NomClub.equals(other.NomClub))
			return false;
		if (categorieClub != other.categorieClub)
			return false;
		if (idClub != other.idClub)
			return false;
		return true;
	}
	public Club( String nomClub, String categorieClub) {
		super();
		
		NomClub = nomClub;
		this.categorieClub = categorieClub;
	}
	
	public Club( int idClub, String nomClub, String categorieClub) {
		super();
		this.idClub = idClub;
		NomClub = nomClub;
		this.categorieClub = categorieClub;
	}
	
	
	@Override
	public String toString() {
		return "Club [idClub=" + idClub + ", NomClub=" + NomClub + ", categorieClub=" + categorieClub + ", getIdClub()="
				+ getIdClub() + ", getNomClub()=" + getNomClub() + ", getCategorieClub()=" + getCategorieClub()
				+ ", hashCode()=" + hashCode() + ", getClass()=" + getClass() + ", toString()=" + super.toString()
				+ "]";
	}
	
	public Club() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	

	
}
