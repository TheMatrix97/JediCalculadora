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

import catrisse.marc.utils.BDController;
import catrisse.marc.utils.Misc;
import io.realm.RealmList;


/**
 * A simple {@link Fragment} subclass.
 */
public class LocalRankingFragment extends Fragment {
    private View rootLayout;
    private RecyclerView recycler;
    public LocalRankingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootLayout = inflater.inflate(R.layout.fragment_local_ranking, container, false);
        recycler = rootLayout.findViewById(R.id.recycler_view_local);
        RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recycler.setLayoutManager(linearLayoutManager);
        AdapterRanking adapter = new AdapterRanking();
        try {
            List<Puntuacion> data = load_data_set();
            adapter.dataset = data;
        } catch (Misc.UserNotFound userNotFound) {
            userNotFound.printStackTrace();
        }
        recycler.setAdapter(adapter);
        return rootLayout;
    }

    private List<Puntuacion> load_data_set() throws Misc.UserNotFound {
        DrawerActivity drawerActivity = (DrawerActivity)getActivity();
        return BDController.getInstance(drawerActivity.getApplicationContext()).load_puntuaciones(drawerActivity.getUser().getUsername());
    }

}
