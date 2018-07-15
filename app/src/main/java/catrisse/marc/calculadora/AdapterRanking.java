package catrisse.marc.calculadora;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class AdapterRanking extends RecyclerView.Adapter<AdapterRanking.MyCustomViewHolder> {

    public List<Puntuacion> dataset = new ArrayList<>(); //set de datos a mostrar

    public class MyCustomViewHolder extends RecyclerView.ViewHolder{ //vista de cada tarjeta a mostrar
        CardView cardView;
        TextView username;
        TextView points;

        public MyCustomViewHolder(View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.card_item_ranking);
            username = itemView.findViewById(R.id.username_item);
            points = itemView.findViewById(R.id.points_item);
        }
        public void setItem(Puntuacion p){
            username.setText(String.format("Username: %s", p.getUsername()));
            points.setText(String.format("Puntos: %s", Double.valueOf(p.getScore()).toString()));
        }
    }


    @NonNull
    @Override
    public MyCustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_view_item, parent, false);
        return new MyCustomViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyCustomViewHolder holder, int position) {
        Puntuacion item = dataset.get(position);
        holder.setItem(item);

    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public void swapDataSet(List<Puntuacion> aux){
        dataset = aux;
        notifyDataSetChanged();
    }
}