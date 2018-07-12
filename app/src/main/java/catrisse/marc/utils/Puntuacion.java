
package catrisse.marc.utils;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Puntuacion {

    @SerializedName("score")
    @Expose
    private Integer score;
    @SerializedName("username")
    @Expose
    private String username;

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
