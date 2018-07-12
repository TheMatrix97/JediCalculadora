package catrisse.marc.calculadora;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.fathzer.soft.javaluator.DoubleEvaluator;

import catrisse.marc.utils.ApiClient;
import catrisse.marc.utils.PostPuntuacion;
import catrisse.marc.utils.Puntuacion;


/**
 * A simple {@link Fragment} subclass.
 */
public class BlankFragment extends Fragment {
    Button testPost;
    Button getpost;

    public BlankFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_blank, container, false);
        testPost = v.findViewById(R.id.ButtonTestApi);
        getpost = v.findViewById(R.id.ButtonGetApi);
        testPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Puntuacion puntuacion_test = new Puntuacion();
                puntuacion_test.setUsername("Pepe");
                puntuacion_test.setScore(Double.valueOf(128));
                PostPuntuacion test = new PostPuntuacion();
                test.setPuntuacion(puntuacion_test);
                ApiClient api = new ApiClient();
                api.postPeticion(test);
            }
        });
        getpost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApiClient api = new ApiClient();
                api.peticionGet();

            }
        });
        return v;
    }

}
