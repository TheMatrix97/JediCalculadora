package catrisse.marc.calculadora;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import catrisse.marc.utils.ApiClient;


/**
 * A simple {@link Fragment} subclass.
 */
public class GlobalRankingFragment extends RankingFragmentContainer {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = super.onCreateView(inflater,container,savedInstanceState);
        ApiClient api = new ApiClient();
        api.peticionGet(adapter); //hacemos la peticion pasando el adapter para rellenarlo on success
        return v;
    }

}
