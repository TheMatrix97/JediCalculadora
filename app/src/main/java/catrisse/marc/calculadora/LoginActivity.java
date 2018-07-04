package catrisse.marc.calculadora;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    EditText editUser;
    EditText editPass;
    Button login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ini();
    }

    private void ini() {
        Toolbar tb = findViewById(R.id.toolbar2);
        editPass = findViewById(R.id.editTextPass);
        editUser = findViewById(R.id.editTextUser);
        login = findViewById(R.id.buttonLogin);
        setSupportActionBar(tb);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User aux = new User(editUser.getText().toString(), editPass.getText().toString());
                if(aux.login()){
                    Intent i = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(i);
                }else{
                    Toast.makeText(getApplicationContext(),"Login incorrecto", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

}
