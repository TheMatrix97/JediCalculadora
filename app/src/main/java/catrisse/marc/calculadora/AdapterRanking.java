package catrisse.marc.calculadora;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class AdapterRanking extends RecyclerView.Adapter<AdapterRanking.MyCustomViewHolder> {

    public List<Puntuacion> dataset = new ArrayList<>(); //set de datos a mostrar

    public class MyCustomViewHolder extends RecyclerView.ViewHolder{ //vista de cada tarjeta a mostrar
        CardView cardView;
        TextView username;
        TextView points;
        ImageView image_copa;
        int viewType;

        public MyCustomViewHolder(View itemView, int viewType) {
            super(itemView);
            cardView = itemView.findViewById(R.id.card_item_ranking);
            username = itemView.findViewById(R.id.username_item);
            points = itemView.findViewById(R.id.points_item);
            image_copa = itemView.findViewById(R.id.image_copa);
            this.viewType = viewType;
        }
        public void setItem(Puntuacion p, int posicion){
            username.setText(String.format("Username: %s", p.getUsername()));
            points.setText(String.format("Puntos: %s", Double.valueOf(p.getScore()).toString()));
            switch (viewType) { //insertamos la copa si procede pero solo se puede entrar una vez...
                case 0: //ORO
                    image_copa.setImageResource(R.drawable.copa_oro);
                    break;
                case 1: //plata
                    image_copa.setImageResource(R.drawable.copa_plata);
                    break;
                case 2: //bronce
                    image_copa.setImageResource(R.drawable.copa_bronce);
                    break;
            }
        }

    }
    @Override
    public int getItemViewType(int position) {
        return position;
    }


    @NonNull
    @Override
    public MyCustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_view_item, parent, false);
        return new MyCustomViewHolder(v, viewType);
    }

    @Override
    public void onBindViewHolder(@NonNull MyCustomViewHolder holder, int position) {
        Puntuacion item = dataset.get(position);
        holder.setItem(item,position);

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