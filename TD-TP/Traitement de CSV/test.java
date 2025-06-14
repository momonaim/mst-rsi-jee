import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;

public class test {

    public static void main(String[] args) {
        System.out.println("test");
        List<Employe> listEmpl = new ArrayList<>();
        try {

            BufferedReader in = new BufferedReader(new FileReader("./employes.csv"));
            String line = in.readLine();
            line = in.readLine();
            while (line != null) {

                String[] tab = line.split(",");

                int id = Integer.parseInt(tab[0]);
                String nom = tab[1];
                int age = Integer.parseInt(tab[2]);
                double salaire = Double.parseDouble(tab[3]);
                String deparetement = tab[4];
                System.out.println(line);
                Employe e = new Employe(id, nom, age, salaire, deparetement);
                listEmpl.add(e);
                System.out.println(e);
                line = in.readLine();

            }
            // System.out.println(listEmpl.size());

            // 1- Salaire moyen par dept
            OptionalDouble ageMoyMarketing = listEmpl.stream()
                    .filter(e -> e.getDepartement().equals("Marketing"))
                    .mapToInt(e -> e.getAge())
                    .average();
            System.out.println("Age moyene des employes du dept Marketing : " + ageMoyMarketing.getAsDouble());

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}