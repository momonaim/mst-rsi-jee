public class Employe {
    private int id;
    private String nom;
    private int age;
    private double salary;
    private String departement;

    public Employe(int id, String nom, int age, double salary, String departement) {
        this.id = id;
        this.nom = nom;
        this.age = age;
        this.salary = salary;
        this.departement = departement;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getDepartement() {
        return departement;
    }

    public void setDepartement(String departement) {
        this.departement = departement;
    }

    @Override
    public String toString() {
        return "Employe [id=" + id + ", nom=" + nom + ", age=" + age + ", salary=" + salary + ", departement="
                + departement + "]";
    }




}
