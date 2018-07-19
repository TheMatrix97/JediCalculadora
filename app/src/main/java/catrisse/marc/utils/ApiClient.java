package catrisse.marc.utils;

import android.util.Log;

import java.util.Collections;
import java.util.List;

import catrisse.marc.calculadora.AdapterRanking;
import catrisse.marc.calculadora.Puntuacion;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApiClient {
    //hacer peticion
    private APIService apiService;

    public ApiClient() {
        this.apiService = ApiUtils.getAPIService();
    }

    public void peticionGet(final AdapterRanking adapter){
        Call<GetPuntuacion> call = apiService.getPuntuaciones();
       /* PostPuntuacion pp = new PostPuntuacion();
        pp.username = "alvaro";
        pp.puntuacion = "99999";*/
        call.enqueue(new Callback<GetPuntuacion>(){

            @Override
            public void onResponse(Call<GetPuntuacion> call, Response<GetPuntuacion> response) {
                List<Puntuacion> aux = response.body().getPuntuaciones();
                Collections.sort(aux, new Misc.comparadorPuntuaciones());
                adapter.swapDataSet(aux);
                Log.v("API","SUCCESS");

            }

            @Override
            public void onFailure(Call<GetPuntuacion> call, Throwable t) {
                Log.v("Api", "fail");

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