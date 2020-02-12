package IServices.Education;

import Entite.Education.Note;

public interface IServicesNotes {
	
	public void ajouterNote(Note n);
	public void modifierNote(Note n);
	public void supprimerNote(Note n);
	public void afficherNote();
	

}
