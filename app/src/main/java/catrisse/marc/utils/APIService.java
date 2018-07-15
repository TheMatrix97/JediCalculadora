package catrisse.marc.utils;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface APIService {

    @POST("/puntuaciones")
    Call<PostPuntuacion> createPuntuacion(@Body PostPuntuacion category);

    @GET("/puntuaciones")
    Call<GetPuntuacion> getPuntuaciones();
}
