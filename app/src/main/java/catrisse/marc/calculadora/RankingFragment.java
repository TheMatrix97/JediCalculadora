package catrisse.marc.calculadora;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class RankingFragment extends Fragment {
    View rootView;
    ViewPager pager;
    ActionBar actionBar;


    public RankingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_ranking, container, false);
        init();
        configurar_pager();

        return rootView;
    }

    private void configurar_pager() {
        PagerAdapter adapter = new PagerAdapter(getActivity().getSupportFragmentManager());
        ViewPager vp = rootView.findViewById(R.id.pager);
        vp.setAdapter(adapter);

    }

    private void init() {
        pager = rootView.findViewById(R.id.pager);
        actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        actionBar.setTitle(R.string.ranking);
    }

    private class PagerAdapter extends FragmentPagerAdapter{
        ArrayList<Fragment> collection;

        public PagerAdapter(FragmentManager fm) {
            super(fm);
            collection = new ArrayList<>();
            collection.add(new LocalRankingFragment());
            collection.add(new GlobalRankingFragment());
        }


        @Override
        public Fragment getItem(int position) {
            return collection.get(position);
        }

        @Override
        public int getCount() {
            return collection.size();
        }
    }

}
