package catrisse.marc.utils;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface APIService {

    @POST("v1/categorias")
    Call<PostPuntuacion> createCategory(@Body PostPuntuacion category);

    @GET("v1/products")
    Call<GetPuntuacion> getPuntuaciones();
}
