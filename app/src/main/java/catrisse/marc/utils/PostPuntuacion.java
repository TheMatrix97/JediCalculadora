
package catrisse.marc.utils;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import catrisse.marc.calculadora.Puntuacion;

public class PostPuntuacion {

    @SerializedName("APIPuntuacion")
    @Expose
    private Puntuacion puntuacion;

    public Puntuacion getAPIPuntuacion() {
        return puntuacion;
    }

    public void setAPIPuntuacion(Puntuacion APIPuntuacion) {
        this.puntuacion = APIPuntuacion;
    }

}
