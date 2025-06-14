public class Etudiant extends Personne {

    private String filiere;

    public Etudiant(int id, String nom, int age, String filiere) {
        super(id, nom, age);
        this.filiere = filiere;
    }

    public String getFiliere() {
        return filiere;
    }

    public void setFiliere(String filiere) {
        this.filiere = filiere;
    }

    @Override
    public String toString() {
        return "Etudiant [id=" + id + ", nom=" + nom + ", age=" + age + ", filiere=" + filiere + "]";
    }

}
