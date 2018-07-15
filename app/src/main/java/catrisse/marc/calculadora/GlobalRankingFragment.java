package catrisse.marc.calculadora;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import catrisse.marc.utils.APIService;
import catrisse.marc.utils.ApiClient;
import catrisse.marc.utils.ApiUtils;
import catrisse.marc.utils.Misc;


/**
 * A simple {@link Fragment} subclass.
 */
public class GlobalRankingFragment extends Fragment {
    View rootLayout;
    RecyclerView recycler;

    public GlobalRankingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootLayout = inflater.inflate(R.layout.fragment_global_ranking, container, false);
        recycler = rootLayout.findViewById(R.id.recycler_view_global);
        RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recycler.setLayoutManager(linearLayoutManager);
        AdapterRanking adapter = new AdapterRanking();
        ApiClient api = new ApiClient();
        api.peticionGet(adapter);
        try {
            List<Puntuacion> data = new ArrayList<>();
            APIService apiService =  ApiUtils.getAPIService();
            apiService.getPuntuaciones();
            adapter.dataset = data;
        } catch (Exception userNotFound) {
            userNotFound.printStackTrace();
        }
        recycler.setAdapter(adapter);
        return rootLayout;
    }

}
