package catrisse.marc.calculadora;

import io.realm.RealmObject;

public class Puntuacion extends RealmObject{
    private long punt;
    private long timestamp;
    private String username;

    public long getPunt() {
        return punt;
    }

    public void setPunt(long punt) {
        this.punt = punt;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Puntuacion() {
    }

    public Puntuacion(String username, long punt, long timestamp) {
        this.punt = punt;
        this.timestamp = timestamp;
        this.username = username;
    }
}
