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

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<Button> numbers = new ArrayList<>();
    List<Button> operations = new ArrayList<>();
    int res = 0;
    String operation = "";
    String num2 = "";
    TextView textViewOp;
    TextView textViewResult;
    ImageView callButton;
    View.OnClickListener listenerButtonNumber = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Button b = (Button) v;
            String num = (String) b.getText();
            textViewOp.append(num);
            num2 += num;
            res = operation();
            textViewResult.setText(String.valueOf(res));
        }
    };

    View.OnClickListener listenerButtonOperation = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Button b = (Button) v;
            operation = (String) b.getText();
            num2 = "";
            //TODO implementar = correctamente
            if(operation.equals("C")){
                reset();
            }
            else textViewOp.append(operation);
            Log.v("I", "Click!");
        }
    };
    //inflar toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu_example,menu);
        return super.onCreateOptionsMenu(menu);
    }

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
        textViewOp = findViewById(R.id.textView);
        callButton = findViewById(R.id.callButton);
        Toolbar tb = findViewById(R.id.toolbar);
        tb.inflateMenu(R.menu.options_menu_example);
        setSupportActionBar(tb);
        iniNums();
        iniOp();
        iniMisc();
    }
    private int operation(){
        int d = Integer.parseInt(this.num2);
        switch (operation) {
            case "+":
                d += res;
                break;
            case "*":
                d *= res;
                break;
            case "-":
                d = res - d;
                break;
            case "/":
                d = res / d;
                break;
            case "C":
                d = 0;
                textViewOp.setText("");
                break;
        }
        //TODO acabar de implementar todo
        return d;

    }
    private void iniMisc(){
        callButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel: " + textViewOp.getText().toString()));
                startActivity(intent);
            }
        });
    }

    private void iniNums(){
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

    private void iniOp(){
        operations.add((Button) findViewById(R.id.button4));
        operations.add((Button) findViewById(R.id.button8));
        operations.add((Button) findViewById(R.id.button12));
        operations.add((Button) findViewById(R.id.button14));
        operations.add((Button) findViewById(R.id.button15));
        operations.add((Button) findViewById(R.id.button16));
        for(Button aux : operations) aux.setOnClickListener(listenerButtonOperation);
    }

    private void reset(){
        textViewOp.setText("");
        textViewResult.setText("");
        operation = "";
        num2 = "";
        res = 0;
    }
    //codigo ejemplo para esconder la ActionBar
    private void esconderActionBar(){
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

    }
}
