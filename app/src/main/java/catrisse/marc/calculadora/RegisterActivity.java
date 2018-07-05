package catrisse.marc.calculadora;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


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
                if(!editName.getText().toString().isEmpty() && !password.isEmpty() && password.equals(confpass)){
                    settings = getSharedPreferences(PREFS_NAME, 0);
                    SharedPreferences.Editor editor = settings.edit();
                    String userName = editName.getText().toString();
                    editor.putString(userName, editPass.getText().toString());
                    editor.apply();
                    show_snackbar();
                }else{
                    Toast.makeText(getApplicationContext(),"error",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void show_snackbar() {
        View parentLayout = findViewById(R.id.parentLayoutRegister);
        Snackbar
                .make(parentLayout, R.string.snackbar_text, Snackbar.LENGTH_LONG)
                .setAction(R.string.snackbar_action, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                        startActivity(i);
                    }
                })
                .show(); // Importante!!! No olvidar mostrar la Snackbar.
    }
}
