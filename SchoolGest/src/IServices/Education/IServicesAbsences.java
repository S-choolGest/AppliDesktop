package IServices.Education;

import Entite.Education.Absence;

public interface IServicesAbsences {
	public void ajouterAbsnence(Absence abs);
	public void modifierAbsence(Absence abs);
	public void supprimerAbsence(int idAbsence);
	public void afficherListAbsence();

}
