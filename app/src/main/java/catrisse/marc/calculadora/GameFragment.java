package catrisse.marc.calculadora;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class GameFragment extends Fragment {
    View layoutGame;
    TimerTask timerTask;
    TextView time;

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
                }
                return true;
            case R.id.game_stop:
                if(timerTask != null && timerTask.getStatus() == AsyncTask.Status.RUNNING) {
                    timerTask.cancel(true);
                    timerTask = null; //limpiamos timer task
                }
                return true;
        }
        return super.onOptionsItemSelected(item);
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
