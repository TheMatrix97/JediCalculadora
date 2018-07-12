package catrisse.marc.calculadora;


import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.ResourceBundle;

import catrisse.marc.utils.CoolImageFlipper;


/**
 * A simple {@link Fragment} subclass.
 */
public class GameFragment extends Fragment {
    View layoutGame;
    TimerTask timerTask;
    TextView time;
    MemoryGameController controller;

    public GameFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true);
        layoutGame = inflater.inflate(R.layout.fragment_game, container, false);
        time = layoutGame.findViewById(R.id.drawerTextTime);
        timerTask = null;
        return layoutGame;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.game_start:
                if(timerTask == null ||(timerTask.getStatus() != AsyncTask.Status.RUNNING)){
                    timerTask = new TimerTask();
                    timerTask.execute();
                    controller = new MemoryGameController(getContext());
                    link_listeners();

                }
                return true;
            case R.id.game_stop:
                if(timerTask != null && timerTask.getStatus() == AsyncTask.Status.RUNNING) {
                    timerTask.cancel(true);
                    timerTask = null; //limpiamos timer task
                    reset_game();
                }
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void reset_game() {
        ArrayList<Integer> lista = controller.getIdButtons();
        Drawable back = getResources().getDrawable(R.drawable.hearth_cardback);
        Drawable.ConstantState backconstant = back.getConstantState();
        CoolImageFlipper flipper = new CoolImageFlipper(getContext());
        for(Integer aux : lista){
            ImageButton carta = layoutGame.findViewById(aux);
            Drawable.ConstantState c1 = carta.getDrawable().getConstantState();
            if(c1 != null && !c1.equals(backconstant)){
                flipper.flipImage(back,carta);
            }
            carta.setOnClickListener(null);
        }
    }

    private void link_listeners() {
        ArrayList<Integer> lista = controller.getIdButtons();
        final ArrayList<Drawable> images = controller.getMapImages();
        int contador = 0;
        final CoolImageFlipper flipper = new CoolImageFlipper(getContext());
        for(final Integer aux : lista){
            final int finalContador = contador;
            layoutGame.findViewById(aux).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ImageButton b = layoutGame.findViewById(aux);
                    flipper.flipImage(images.get(finalContador),b);
                }
            });
            contador++;
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.activity_drawer_game, menu);
    }

    private class TimerTask extends AsyncTask<Void, Integer, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            Integer progress = 0;
            while(!isCancelled()){
                try {
                    Thread.sleep(1000);
                    progress += 1000;
                    publishProgress(progress);
                } catch (InterruptedException e) {
                    e.printStackTrace(); //no se deberia interrumpir nunca...
                    break;
                }
            }
            return null;
        }

        @Override
        protected void onCancelled() { //reset Timer
            time.setText("0");
            super.onCancelled();
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            update_timer(values[0]/1000);
        }
    }

    private void update_timer(Integer val) {
        time.setText(val.toString());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if(timerTask != null && timerTask.getStatus() == AsyncTask.Status.RUNNING) {
            timerTask.cancel(true);
            timerTask = null; //limpiamos timer task
        }
    }
}
