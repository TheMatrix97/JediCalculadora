package catrisse.marc.calculadora;


import android.annotation.SuppressLint;
import android.content.pm.ActivityInfo;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

import catrisse.marc.utils.CoolImageFlipper;
import catrisse.marc.utils.Misc;


/**
 * A simple {@link Fragment} subclass.
 */
public class GameFragment extends Fragment {
    View layoutGame;
    Timer timerTask;
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
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(R.string.game);
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        return layoutGame;
    }

    @Override
    public void onDestroy() {
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
        super.onDestroy();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.game_start:
                if(timerTask == null || timerTask.isInterrupted()){
                    timerTask = new Timer();
                    timerTask.start();
                    controller = new MemoryGameController(getContext());
                    link_listeners();
                    setClickableAllCards(true);

                }
                return true;
            case R.id.game_stop:
                if(timerTask != null && timerTask.isAlive()) {
                    timerTask.interrupt();
                    timerTask = null; //limpiamos timer task
                    update_timer(0L);
                    reset_game(true);
                }
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void reset_game(boolean total) {
        ArrayList<Integer> lista = controller.getIdButtons();
        Drawable back = getResources().getDrawable(R.drawable.hearth_cardback);
        Drawable.ConstantState backconstant = back.getConstantState();
        CoolImageFlipper flipper = new CoolImageFlipper(getContext());
        for(Integer aux : lista){
            ImageButton carta = layoutGame.findViewById(aux);
            Drawable.ConstantState c1 = carta.getDrawable().getConstantState();
            if(c1 != null && !c1.equals(backconstant) && (!controller.isDrawableYaEcontrado(c1) || total)){
                flipper.flipImage(back,carta);
            }
            if(total) carta.setOnClickListener(null);
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
                    if(controller.getFirstCardFlipped() == null){
                        controller.setFirstCardFlipped(b);
                    }else{
                        //Se ha levantado otra, hay que comparar
                        try {
                            if (!controller.compareCartas(images.get(finalContador))) { //si estan mal hay que darles la vuelta
                                //esperar
                                WaitTask wt = new WaitTask();
                                wt.execute();
                            } else {
                                //hacemos que esta segunda no sea clickeable como la primera
                                b.setClickable(false);
                            }
                        }catch (Misc.GameFinalizado e){
                            //fin del juego setAll no clickable y popout para preguntar si guardar la partida
                            fin_juego();
                        }
                    }
                }
            });
            contador++;
        }
    }

    private void fin_juego() {
        long time = timerTask.getContador();
        timerTask.interrupt();
        ConfirmFinishGame c = new ConfirmFinishGame();
        c.show(getFragmentManager(), "go");
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.activity_drawer_game, menu);
    }

    private class Timer extends Thread{
        private Long contador = 0L;
        @Override
        public void run() {
            contador = 0L;
            while(!Thread.interrupted()){
                try {
                    Thread.sleep(1000);
                    contador += 1000;
                    update_timer(contador);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    break;
                }
            }
        }

        public Long getContador() {
            return contador;
        }
    }


    private void update_timer(final Long val) {
        //Lo ejecutamos en el Thread que contiene la UI para poder modificar el textview
        getActivity().runOnUiThread(new Runnable() {
            @SuppressLint("DefaultLocale")
            @Override
            public void run() {
                time.setText(String.format("%tT",val));
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if(timerTask != null && timerTask.isAlive()) {
            timerTask.interrupt();
            timerTask = null; //limpiamos timer task
        }
    }

    private class WaitTask extends AsyncTask<Void,Void,Boolean>{
        @Override
        protected void onPreExecute() {
            setClickableAllCards(false);
            super.onPreExecute();
        }

        @Override
        protected Boolean doInBackground(Void... voids) {
            //disable all cards, tengo que comparar
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
                return false;
            }
            return true;
        }

        @Override
        protected void onPostExecute(Boolean aVoid) {
            controller.setFirstCardFlipped(null); //post execute
            setClickableAllCards(true);
            reset_game(false);
            super.onPostExecute(aVoid);
        }


    }
    private void setClickableAllCards(boolean state) {
        ArrayList<Integer> lista = controller.getIdButtons();
        for(Integer i : lista){
            ImageButton aux = layoutGame.findViewById(i);
            if(!controller.isDrawableYaEcontrado(aux.getDrawable().getConstantState())) aux.setClickable(state); //no queremos alterar el estado de cartas ya giradas
        }
    }
}
