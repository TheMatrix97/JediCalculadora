package catrisse.marc.calculadora;

import android.content.SharedPreferences;

public class User {
    private String nom;
    private String pass;

    public User(String nom, String pass) {
        this.nom = nom;
        this.pass = pass;
    }
    //se podria pasar el contexto de la aplicacion para llamar a getSharedPreferences desde aqui
    public boolean login(String pass){
        if(!pass.isEmpty() && pass.equals(this.pass)){
            return true;
        }else return false;
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
