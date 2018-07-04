package catrisse.marc.calculadora;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.fathzer.soft.javaluator.DoubleEvaluator;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class MainActivity extends AppCompatActivity {
    DoubleEvaluator evaluator = new DoubleEvaluator();
    List<Button> numbers = new ArrayList<>();
    List<Button> operations = new ArrayList<>();
    TextView textViewOp;
    TextView textViewResult;
    ImageView callButton;
    View.OnClickListener listenerButtonNumber = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Button b = (Button) v;
            String num = (String) b.getText();
            textViewOp.append(num); //añadimos el numero tal cual
        }
    };

    View.OnClickListener listenerButtonOperation = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Button b = (Button) v;
            String operation = (String) b.getText();
            if(operation.equals("C")){
                reset();
            }else if(operation.equals("=")){
                resuelve();
            }else textViewOp.append(operation); //Si no es una de las opciones especiales, se inserta el caracter tal cual
        }
    };

    //inflar toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu_example,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("operationField", textViewOp.getText().toString());
        outState.putString("resultField", textViewResult.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        String textOp = savedInstanceState.getString("operationField");
        String textRes = savedInstanceState.getString("resultField");
        textViewOp.setText(textOp);
        textViewResult.setText(textRes);
    }

    //Configurar acciones Menú toolbar
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.item1:
                Log.v("I", "Item 1 selected!");
                break;
            case R.id.item2:
                Log.v("I", "Item 2 selected!");
                break;
            case R.id.item3:
                Log.v("I", "Item 3 selected!");

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textViewResult = findViewById(R.id.textViewResult);
        textViewOp = findViewById(R.id.textView2);
        callButton = findViewById(R.id.callButton);
        Toolbar tb = findViewById(R.id.toolbar);
        tb.inflateMenu(R.menu.options_menu_example);
        setSupportActionBar(tb);
        iniNums();
        iniOp();
        iniMisc();
    }
    private void iniMisc(){ //Set listener call button
        callButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel: " + textViewOp.getText().toString()));
                startActivity(intent);
            }
        });
    }

    private void resuelve() { //funcion que resuelve según textViewOp
        Double d = 0.0;
        try{
            d = this.evaluator.evaluate(textViewOp.getText().toString());
        }catch(Exception e){
            this.textViewOp.setError("No valid operation");
        }
        this.textViewResult.setText(String.format(Locale.ENGLISH,"%.2f",d));
    }

    private void iniNums(){ //inicializamos los numeros
        numbers.add((Button) findViewById(R.id.button1));
        numbers.add((Button) findViewById(R.id.button2));
        numbers.add((Button) findViewById(R.id.button3));
        numbers.add((Button) findViewById(R.id.button5));
        numbers.add((Button) findViewById(R.id.button6));
        numbers.add((Button) findViewById(R.id.button7));
        numbers.add((Button) findViewById(R.id.button9));
        numbers.add((Button) findViewById(R.id.button10));
        numbers.add((Button) findViewById(R.id.button11));
        numbers.add((Button) findViewById(R.id.button13));
        for(Button aux : numbers) aux.setOnClickListener(listenerButtonNumber);
    }

    private void iniOp(){ //inicializamos los buttons de las operaciones
        operations.add((Button) findViewById(R.id.button4));
        operations.add((Button) findViewById(R.id.button8));
        operations.add((Button) findViewById(R.id.button12));
        operations.add((Button) findViewById(R.id.button14));
        operations.add((Button) findViewById(R.id.button15));
        operations.add((Button) findViewById(R.id.button16));
        for(Button aux : operations) aux.setOnClickListener(listenerButtonOperation);
    }

    private void reset(){ //AC pressed
        textViewOp.setText("");
        textViewResult.setText("");
    }

    //codigo ejemplo para esconder la ActionBar
    private void esconderActionBar(){
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

    }
}
