
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        String fichierCSV = "employes.csv";
        List<Employe> listeEmployes = new ArrayList<>();

        //1- lecture de CSV
        try (BufferedReader br = new BufferedReader(new FileReader(fichierCSV))) {
            String ligne;
            boolean premiereLigne = true;

            while ((ligne = br.readLine()) != null) {
                if (premiereLigne) {
                    premiereLigne = false;
                    continue;
                }

                String[] valeurs = ligne.split(",");

                int id = Integer.parseInt(valeurs[0]);
                String nom = valeurs[1];
                int age = Integer.parseInt(valeurs[2]);
                double salaire = Double.parseDouble(valeurs[3]);
                String departement = valeurs[4];

                Employe e = new Employe(id, nom, age, salaire, departement);
                listeEmployes.add(e);
            }

            // Affichage pour verification
            for (Employe e : listeEmployes) {
                System.out.println(e);
            }

        } catch (IOException e) {
            System.err.println("Erreur : " + e.getMessage());
        }

        //2- salaire moyen par dept
        System.out.println("\n\nSalaire moyen par departement :");

        Map<String, Double> salaireMoyenParDept = listeEmployes.stream()
                .collect(Collectors.groupingBy(
                        Employe::getDepartement,
                        Collectors.averagingDouble(Employe::getSalary)
                ));

        salaireMoyenParDept.forEach((dept, moyenne)
                -> System.out.println(dept + " : " + moyenne + " DH")
        );

        //3- le nb d emp par dept 
        Map<String, Long> countParDept = listeEmployes.stream()
                .collect(Collectors.groupingBy(
                        Employe::getDepartement,
                        Collectors.counting()
                ));

        System.out.println("\n\nNombre d employes par departement :");
        countParDept.forEach((dept, count)
                -> System.out.println(dept + " : " + count + " employes")
        );

        //4- salaire min & max et retourner l emp min & max
        // Trouver le salaire minimum
        OptionalDouble salaireMin = listeEmployes.stream()
                .mapToDouble(Employe::getSalary)
                .min();

        // Trouver le salaire maximum
        OptionalDouble salaireMax = listeEmployes.stream()
                .mapToDouble(Employe::getSalary)
                .max();

        // Trouver l'employe avec le salaire minimum
        Optional<Employe> employeMinSalaire = listeEmployes.stream()
                .filter(e -> e.getSalary() == salaireMin.orElse(Double.NaN))
                .findFirst();

        // Trouver l'employe avec le salaire maximum
        Optional<Employe> employeMaxSalaire = listeEmployes.stream()
                .filter(e -> e.getSalary() == salaireMax.orElse(Double.NaN))
                .findFirst();

        // Affichage des resultats
        System.out.println("\n");
        if (salaireMin.isPresent()) {
            System.out.println("Salaire minimum : " + salaireMin.getAsDouble() + "Dh");
            employeMinSalaire.ifPresent(e -> System.out.println("Employe avec salaire minimum : " + e.getNom() + " (" + e.getDepartement() + ")"));
        } else {
            System.out.println("Aucun salaire minimum trouve.");
        }

        if (salaireMax.isPresent()) {
            System.out.println("Salaire maximum : " + salaireMax.getAsDouble() + " DH");
            employeMaxSalaire.ifPresent(e
                    -> System.out.println("Employe avec salaire maximum : " + e.getNom() + " (" + e.getDepartement() + ")"));
        } else {
            System.out.println("Aucun salaire maximum trouve.");
        }

        //5- age moyen de dept Marketing
        OptionalDouble ageMoyenMarketing = listeEmployes.stream()
                .filter(e -> "Marketing".equals(e.getDepartement())) // Filtrer les employes du departement Marketing
                .mapToInt(Employe::getAge) // Extraire l'âge de chaque employe
                .average(); // Calculer la moyenne

        // Affichage du resultat
        System.out.println("\n");
        if (ageMoyenMarketing.isPresent()) {
            System.out.println("Âge moyen des employes du departement Marketing : " + ageMoyenMarketing.getAsDouble() + " ans");
        } else {
            System.out.println("Aucun employe trouve dans le departement Marketing.");
        }

        //Meth simple
        int totalAge = 0;
        int count = 0;

        for (Employe e : listeEmployes) {
            if ("Marketing".equals(e.getDepartement())) {
                totalAge += e.getAge();
                count++;
            }
        }

        //Affichage
        System.out.println("\n");
        if (count > 0) {
            double ageMoyen = (double) totalAge / count;
            System.out.println("Âge moyen des employes du departement Marketing : " + ageMoyen + " ans");
        } else {
            System.out.println("Aucun employe trouve dans le departement Marketing.");
        }

    }
}
