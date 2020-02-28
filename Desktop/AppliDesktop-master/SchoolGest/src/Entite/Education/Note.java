package Entite.Education;

public class Note {
	private int idNote;
	private String typeNote;
	
	
	public Note(int idNote, String typeNote) {
		super();
		this.idNote = idNote;
		this.typeNote = typeNote;
	}


	public Note() {
		super();
	}


	public Note(String typeNote) {
		super();
		this.typeNote = typeNote;
	}


	public int getIdNote() {
		return idNote;
	}


	public void setIdNote(int idNote) {
		this.idNote = idNote;
	}


	public String getTypeNote() {
		return typeNote;
	}


	public void setTypeNote(String typeNote) {
		this.typeNote = typeNote;
	}


	@Override
	public String toString() {
		return "Note [idNote=" + idNote + ", typeNote=" + typeNote + "]";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idNote;
		result = prime * result + ((typeNote == null) ? 0 : typeNote.hashCode());
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
		Note other = (Note) obj;
		if (idNote != other.idNote)
			return false;
		if (typeNote == null) {
			if (other.typeNote != null)
				return false;
		} else if (!typeNote.equals(other.typeNote))
			return false;
		return true;
	}
	
	
	
	

}
