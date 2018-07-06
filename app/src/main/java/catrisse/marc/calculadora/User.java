package catrisse.marc.calculadora;


import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class User extends RealmObject{
    @PrimaryKey
    private String nom;
    private String pass;

    public User() {
    }

    public User(String nom, String pass) {
        this.nom = nom;
        this.pass = pass;
    }

    public String getNom() {
        return nom;
    }

    public String getPass() {
        return pass;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
