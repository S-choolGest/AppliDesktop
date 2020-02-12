package IServices.Education;

import Entite.Education.Note;

public interface IServicesNotes {
	
	public void ajouterNote(Note n);
	public void modifierEtudiant(Note n);
	public void supprimerEtudiant(Note n);
	public void afficherListEtudiant();
	

}
