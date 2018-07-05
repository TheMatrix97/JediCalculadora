package catrisse.marc.calculadora;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class RegisterActivity extends AppCompatActivity {
    EditText editName;
    EditText editPass;
    EditText editConfirmPass;
    Button buttonRegister;
    public static String PREFS_NAME = "config";
    SharedPreferences settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ini();
    }

    private void ini() {
        //OnChange editText??

        editName = findViewById(R.id.editName);
        editPass = findViewById(R.id.editPass);
        editConfirmPass = findViewById(R.id.editConfirmPass);
        buttonRegister = findViewById(R.id.buttonRegister);
        //todo hacerlo mas chachi
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String password = editPass.getText().toString() ;
                String confpass = editConfirmPass.getText().toString();
                if(!editName.getText().toString().isEmpty() && password.equals(confpass)){
                    settings = getSharedPreferences(PREFS_NAME, 0);
                    SharedPreferences.Editor editor = settings.edit();
                    String userName = editName.getText().toString();
                    editor.putString(userName, editPass.getText().toString());
                    editor.apply();
                    Toast.makeText(getApplicationContext(),String.format("El usuario %s se ha registrado con exito",userName),Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getApplicationContext(),"error",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
