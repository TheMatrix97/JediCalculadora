
package catrisse.marc.utils;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PostPuntuacion {

    @SerializedName("puntuacion")
    @Expose
    private Puntuacion puntuacion;

    public Puntuacion getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(Puntuacion puntuacion) {
        this.puntuacion = puntuacion;
    }

}
