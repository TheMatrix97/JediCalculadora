
package catrisse.marc.utils;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import catrisse.marc.calculadora.Puntuacion;

public class GetPuntuacion {

    @SerializedName("puntuaciones")
    @Expose
    private List<Puntuacion> puntuaciones = null;

    public List<Puntuacion> getPuntuaciones() {
        return puntuaciones;
    }

    public void setPuntuaciones(List<Puntuacion> puntuaciones) {
        this.puntuaciones = puntuaciones;
    }

}
