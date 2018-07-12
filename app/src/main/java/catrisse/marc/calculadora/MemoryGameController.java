package catrisse.marc.calculadora;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

public class MemoryGameController {
    private ImageButton firstCardFlipped;
    private int parejas;
    private ArrayList<Integer> drawables;
    private ArrayList<Integer> idButtons;
    private ArrayList<Drawable.ConstantState> parejasencontradas;
    private Context c;

    public MemoryGameController(Context c) {
        this.drawables = load_drawables();
        this.idButtons = load_button_id();
        this.c = c;
        this.firstCardFlipped = null;
        this.parejas = 0;
        this.parejasencontradas = new ArrayList<>();
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

    public ArrayList<Integer> getIdButtons() {
        return idButtons;
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

    public ImageButton getFirstCardFlipped() {
        return firstCardFlipped;
    }

    public void setFirstCardFlipped(ImageButton firstCardFlipped) {
        this.firstCardFlipped = firstCardFlipped;
        if(firstCardFlipped != null) this.firstCardFlipped.setClickable(false); //desactivamos click en la primera carta girada
    }

    public boolean compareCartas(Drawable drawable) {
        Drawable.ConstantState fc = firstCardFlipped.getDrawable().getConstantState();
        Drawable.ConstantState dc = drawable.getConstantState();
        if(fc != null && dc != null){
            if(fc.equals(dc)){
                parejasencontradas.add(fc);
                this.firstCardFlipped = null;
                return true;
            }
            return false;
        }
        return false;
    }
    public boolean isDrawableYaEcontrado(Drawable.ConstantState d){
        return parejasencontradas.contains(d);
    }

}
