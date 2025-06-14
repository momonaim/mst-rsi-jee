public class Professor extends Personne {
    private String specialite;
    private double salaire;

    public Professor(int id, String nom, int age, String specialite, double salaire) {
        super(id, nom, age);
        this.specialite = specialite;
        this.salaire = salaire;
    }

    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

    public double getSalaire() {
        return salaire;
    }

    public void setSalaire(double salaire) {
        this.salaire = salaire;
    }

    @Override
    public String toString() {
        return "Professor [id=" + id + ", nom=" + nom + ", specialite=" + specialite + ", salaire=" + salaire + ", age="
                + age + "]";
    }

}
