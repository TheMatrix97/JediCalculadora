package catrisse.marc.calculadora;

public class User {
    private String nom;
    private String pass;

    public User(String nom, String pass) {
        this.nom = nom;
        this.pass = pass;
    }

    public boolean login(){
        if(nom.equals("Marc") && pass.equals("catrisse")){
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
