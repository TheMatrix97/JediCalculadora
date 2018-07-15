package catrisse.marc.calculadora;


import io.realm.RealmObject;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Puntuacion extends RealmObject{

    @SerializedName("score")
    @Expose
    private Double score;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("id")
    @Expose
    private Integer id;
    private long timestamp;

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Puntuacion() {
    }

    public Puntuacion(String username, Double punt, long timestamp) {
        this.score = punt;
        this.timestamp = timestamp;
        this.username = username;
    }
}
