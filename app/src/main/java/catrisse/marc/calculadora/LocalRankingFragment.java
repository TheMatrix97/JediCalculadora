package catrisse.marc.calculadora;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import catrisse.marc.utils.BDController;
import catrisse.marc.utils.Misc;


/**
 * A simple {@link Fragment} subclass.
 */
public class LocalRankingFragment extends RankingFragmentContainer {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = super.onCreateView(inflater,container,savedInstanceState);
        try {
            List<Puntuacion> data = load_data_set();
            adapter.swapDataSet(data);
        } catch (Misc.UserNotFound userNotFound) {
            userNotFound.printStackTrace();
        }
        return v;
    }

    private List<Puntuacion> load_data_set() throws Misc.UserNotFound {
        DrawerActivity drawerActivity = (DrawerActivity)getActivity();
        return BDController.getInstance(drawerActivity.getApplicationContext()).load_puntuaciones(drawerActivity.getUser().getUsername());
    }

}
