package catrisse.marc.calculadora;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {
    private TextView a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        this.a = findViewById(R.id.textView2);
        Intent a = getIntent();
        String aux = a.getStringExtra("text");
        this.a.setText(aux);

    }
}
