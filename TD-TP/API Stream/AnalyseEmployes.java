
import java.util.*;
import java.util.stream.*;

public class AnalyseEmployes {

    static class Employe {
        private int id;
        private String nom;
        private int age;
        private String genre;
        private String departement;
        private double salaire;

        public Employe(int id, String nom, int age, String genre, String departement, double salaire) {
            this.id = id;
            this.nom = nom;
            this.age = age;
            this.genre = genre;
            this.departement = departement;
            this.salaire = salaire;
        }

        public int getId() {
            return id;
        }

        public String getNom() {
            return nom;
        }

        public int getAge() {
            return age;
        }

        public String getGenre() {
            return genre;
        }

        public String getDepartement() {
            return departement;
        }

        public double getSalaire() {
            return salaire;
        }

        @Override
        public String toString() {
            return "Employe{id=" + id + ", nom='" + nom + "', age=" + age + ", genre='" + genre + "', departement='"
                    + departement + "', salaire=" + salaire + "}";
        }
    }

    public static void main(String[] args) {
        List<Employe> employeList = Arrays.asList(
                new Employe(1, "Nom1", 32, "F", "HR", 25000.0),
                new Employe(2, "Nom2", 25, "H", "Commerciale", 13500.0),
                new Employe(3, "Nom3", 29, "H", "IT", 18000.0),
                new Employe(4, "Nom4", 28, "H", "Developpement", 32500.0),
                new Employe(5, "Nom5", 27, "H", "HR", 22700.0),
                new Employe(6, "Nom6", 43, "H", "Securite", 10500.0),
                new Employe(7, "Nom7", 35, "H", "Finance", 27000.0),
                new Employe(8, "Nom8", 31, "H", "Developpement", 34500.0),
                new Employe(9, "Nom9", 24, "F", "Commerciale", 11500.0),
                new Employe(10, "Nom10", 38, "H", "Securite", 11000.5),
                new Employe(11, "Nom11", 27, "F", "IT", 15700.0),
                new Employe(12, "Nom12", 25, "H", "Developpement", 28200.0),
                new Employe(13, "Nom13", 27, "F", "Finance", 21300.0),
                new Employe(14, "Nom14", 24, "H", "Commerciale", 10700.5),
                new Employe(15, "Nom15", 23, "H", "IT", 12700.0),
                new Employe(16, "Nom16", 26, "F", "Developpement", 28900.0));

        // 1. Nombre d’hommes et de femmes
        Map<String, Long> countByGenre = employeList.stream()
                .collect(Collectors.groupingBy(Employe::getGenre, Collectors.counting()));
        System.out.println("1. Nombre d'hommes et femmes : " + countByGenre);

        // 2. Noms des départements
        System.out.println("2. Départements :");
        employeList.stream().map(Employe::getDepartement).distinct().forEach(System.out::println);

        // 3. Employé le plus jeune
        employeList.stream()
                .min(Comparator.comparingInt(Employe::getAge))
                .ifPresent(e -> System.out
                        .println("3. Plus jeune : " + e.getNom() + ", " + e.getAge() + " ans, " + e.getDepartement()));

        // 4. Âge moyen hommes/femmes
        Map<String, Double> avgAgeByGenre = employeList.stream()
                .collect(Collectors.groupingBy(Employe::getGenre, Collectors.averagingInt(Employe::getAge)));
        System.out.println("4. Âge moyen par genre : " + avgAgeByGenre);

        // 5. Employé le mieux payé
        employeList.stream()
                .max(Comparator.comparingDouble(Employe::getSalaire))
                .ifPresent(e -> System.out.println("5. Mieux payé : " + e));

        // 6. Nombre d’employés par département
        Map<String, Long> countByDept = employeList.stream()
                .collect(Collectors.groupingBy(Employe::getDepartement, Collectors.counting()));
        System.out.println("6. Nombre d'employés par département : " + countByDept);

        // 7. Salaire moyen par département
        Map<String, Double> avgSalaryByDept = employeList.stream()
                .collect(Collectors.groupingBy(Employe::getDepartement,
                        Collectors.averagingDouble(Employe::getSalaire)));
        System.out.println("7. Salaire moyen par département : " + avgSalaryByDept);

        // 8. Plus jeune homme dans Développement
        employeList.stream()
                .filter(e -> e.getGenre().equals("H") && e.getDepartement().equalsIgnoreCase("Developpement"))
                .min(Comparator.comparingInt(Employe::getAge))
                .ifPresent(e -> System.out.println("8. Plus jeune homme en Développement : " + e));

        // 9. Hommes et femmes dans Commerciale
        Map<String, Long> genreInCommerciale = employeList.stream()
                .filter(e -> e.getDepartement().trim().equalsIgnoreCase("Commerciale"))
                .collect(Collectors.groupingBy(Employe::getGenre, Collectors.counting()));
        System.out.println("9. Genre dans Commerciale : " + genreInCommerciale);

        // 10. Salaire moyen par genre
        Map<String, Double> avgSalaryByGenre = employeList.stream()
                .collect(Collectors.groupingBy(Employe::getGenre, Collectors.averagingDouble(Employe::getSalaire)));
        System.out.println("10. Salaire moyen par genre : " + avgSalaryByGenre);

        // 11. Trier par nom puis salaire
        System.out.println("11. Trié par nom puis salaire :");
        employeList.stream()
                .sorted(Comparator.comparing(Employe::getNom).thenComparing(Employe::getSalaire))
                .forEach(System.out::println);

        // 12. Moyenne et total des salaires
        DoubleSummaryStatistics stats = employeList.stream()
                .collect(Collectors.summarizingDouble(Employe::getSalaire));
        System.out.println("12. Total salaires : " + stats.getSum() + ", Moyenne : " + stats.getAverage());

        // 13. Deuxième salaire le plus élevé
        Optional<Double> secondHigh = employeList.stream()
                .map(Employe::getSalaire).distinct()
                .sorted(Comparator.reverseOrder())
                .skip(1).findFirst();
        secondHigh.ifPresent(s -> System.out.println("13. 2ème salaire plus élevé : " + s));

        // 14. Mieux payé par département
        Map<String, Optional<Employe>> bestPaidPerDept = employeList.stream()
                .collect(Collectors.groupingBy(Employe::getDepartement,
                        Collectors.maxBy(Comparator.comparingDouble(Employe::getSalaire))));
        System.out.println("14. Mieux payé par département :");
        bestPaidPerDept.forEach((d, e) -> System.out.println(d + " => " + e.orElse(null)));
    }
}
