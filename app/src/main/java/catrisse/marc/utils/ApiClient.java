package catrisse.marc.utils;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApiClient {
    //hacer peticion
    private ApiService apiService;

    public ApiClient() {
        this.apiService = ApiUtils.getAPIService();
    }

    public void peticionGet(){
        Call<GetPuntuacion> call = apiService.getPuntuaciones();
       /* PostPuntuacion pp = new PostPuntuacion();
        pp.username = "alvaro";
        pp.puntuacion = "99999";*/
        call.enqueue(new Callback<GetPuntuacion>(){

            @Override
            public void onResponse(Call<GetPuntuacion> call, Response<GetPuntuacion> response) {
                List<Puntuacion> aux = response.body().getPuntuaciones();
                Log.v("API","SUCCESS");

            }

            @Override
            public void onFailure(Call<GetPuntuacion> call, Throwable t) {

            }
        });

    }

    public void postPeticion(PostPuntuacion pp){
        Call<PostPuntuacion> callPost = apiService.createPuntuacion(pp);
        callPost.enqueue(new Callback<PostPuntuacion>(){

            @Override
            public void onResponse(Call<PostPuntuacion> call, Response<PostPuntuacion> response) {
                Log.v("API","Hay respuesta!!");

            }

            @Override
            public void onFailure(Call<PostPuntuacion> call, Throwable t) {
                Log.v("API","Error");

            }
        });

    }
}