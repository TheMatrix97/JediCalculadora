package catrisse.marc.calculadora;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

//Esta clase contiene la configuacion del RecyclerView y su adapter

public abstract class RankingFragmentContainer extends android.support.v4.app.Fragment {
    public AdapterRanking adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        int idFrag = R.layout.fragment_recycler_ranking;
        View rootLayout = inflater.inflate(idFrag, container, false);
        int idRecycler = R.id.recycler_view;
        RecyclerView recycler = rootLayout.findViewById(idRecycler);
        RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recycler.setLayoutManager(linearLayoutManager);
        adapter = new AdapterRanking();
        recycler.setAdapter(adapter);
        return rootLayout;
    }
}
