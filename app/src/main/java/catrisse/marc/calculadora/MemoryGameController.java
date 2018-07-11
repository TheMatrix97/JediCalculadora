package catrisse.marc.calculadora;

import android.content.Context;
import android.graphics.drawable.Drawable;

import java.util.ArrayList;
import java.util.Collections;

public class MemoryGameController {

    ArrayList<Integer> drawables;
    ArrayList<Integer> idButtons;
    Context c;

    public MemoryGameController(Context c) {
        this.drawables = load_drawables();
        this.idButtons = load_button_id();
        this.c = c;
    }

    public ArrayList<Drawable> getMapImages(){ //devuelve en orden las imagenes para cada botón (desordenadas)
        ArrayList<Drawable> res = new ArrayList<Drawable>(16);
        for(Integer imageid : drawables){
            Drawable aux = c.getResources().getDrawable(imageid,c.getTheme());
            res.add(aux);
            res.add(aux);
        }
        Collections.shuffle(res); //desordenamos
        return res;
    }
    private ArrayList<Integer> load_button_id() {
        ArrayList<Integer> res = new ArrayList<>(16);
        res.add(R.id.imageButton);res.add(R.id.imageButton2);res.add(R.id.imageButton3);res.add(R.id.imageButton4);
        res.add(R.id.imageButton5);res.add(R.id.imageButton6);res.add(R.id.imageButton7);res.add(R.id.imageButton8);
        res.add(R.id.imageButton9);res.add(R.id.imageButton10);res.add(R.id.imageButton11);res.add(R.id.imageButton12);
        res.add(R.id.imageButton13);res.add(R.id.imageButton14);res.add(R.id.imageButton15);res.add(R.id.imageButton16);
        return res;
    }

    private ArrayList<Integer> load_drawables() {
        ArrayList<Integer> res = new ArrayList<>(8);
        res.add(R.drawable.alex);res.add(R.drawable.dr_boom);res.add(R.drawable.dummy);res.add(R.drawable.hex);
        res.add(R.drawable.leeroy);res.add(R.drawable.lord);res.add(R.drawable.molino_tormenta); res.add(R.drawable.mana);
        return res;
    }

}
