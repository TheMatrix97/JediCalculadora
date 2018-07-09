package catrisse.marc.calculadora;


import java.io.Serializable;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class User extends RealmObject implements Serializable{
    @PrimaryKey
    private String username;
    private String nom;
    private String surname;
    private String pass;

    public User() {
    }

    public User(String username, String nom,String surname, String pass) {
        this.username = username;
        this.pass = pass;
        this.surname = surname;
        this.nom = nom;
    }

    public User(String username, String pass) {
        this.username = username;
        this.pass = pass;
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
}
