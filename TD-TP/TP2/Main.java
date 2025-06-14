import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Création de la liste de Personne
        List<Personne> personnes = new ArrayList<>();

        // Ajout d'étudiants
        personnes.add(new Etudiant(1, "Jean Dupont", 20, "Informatique"));
        personnes.add(new Etudiant(2, "Marie Martin", 21, "Mathématiques"));
        personnes.add(new Etudiant(3, "Pierre Durand", 22, "Physique"));

        // Ajout de professeurs
        personnes.add(new Professor(101, "Sophie Lambert", 45, "Algorithmique", 3500.0));
        personnes.add(new Professor(102, "Jacques Petit", 50, "Mécanique quantique", 4200.0));
        personnes.add(new Professor(103, "Isabelle Moreau", 38, "Langues anciennes", 3800.0));

        // Affichage des personnes
        for (Personne personne : personnes) {
            if (personne instanceof Etudiant) {
                Etudiant etudiant = (Etudiant) personne;
                System.out.println("Étudiant: " + etudiant.getNom() +
                        ", Âge: " + etudiant.getAge() +
                        ", Filière: " + etudiant.getFiliere());
            } else if (personne instanceof Professor) {
                Professor prof = (Professor) personne;
                System.out.println("Professeur: " + prof.getNom() +
                        ", Âge: " + prof.getAge() +
                        ", Spécialité: " + prof.getSpecialite() +
                        ", Salaire: " + prof.getSalaire());
            }
        }
    }
}