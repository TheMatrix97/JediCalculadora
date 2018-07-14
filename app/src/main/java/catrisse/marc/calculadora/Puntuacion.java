package catrisse.marc.calculadora;

import io.realm.RealmObject;

public class Puntuacion extends RealmObject{
    private long punt;
    private long timestamp;

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

    public Puntuacion() {
    }

    public Puntuacion(long punt, long timestamp) {
        this.punt = punt;
        this.timestamp = timestamp;
    }
}
