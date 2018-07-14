package catrisse.marc.calculadora;


import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class User extends RealmObject{
    @PrimaryKey
    private String username;
    private String nom;
    private String surname;
    private String pass;
    private RealmList<Puntuacion> puntuaciones;

    public User() {
    }

    public User(String username, String nom,String surname, String pass) {
        this.username = username;
        this.pass = pass;
        this.surname = surname;
        this.nom = nom;
        this.puntuaciones = new RealmList<>();
    }

    public User(String username, String pass) {
        this.username = username;
        this.pass = pass;
    }
    public void addPuntuacion(Puntuacion a){
        puntuaciones.add(a);
    }

    public String getUsername() {
        return username;
    }

    public String getPass() {
        return pass;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public RealmList<Puntuacion> getPuntuaciones() {
        return puntuaciones;
    }
}
