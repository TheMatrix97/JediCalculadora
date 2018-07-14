package catrisse.marc.calculadora;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import catrisse.marc.utils.BDController;
import catrisse.marc.utils.Misc;
import io.realm.Realm;
import io.realm.RealmResults;


public class RegisterActivity extends AppCompatActivity {
    EditText editName;
    EditText editPass;
    EditText editConfirmPass;
    EditText editNom;
    EditText editSurname;
    Button buttonRegister;
    BDController bd;
    public static String PREFS_NAME = "config";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ini();
    }

    private void ini() {
        //OnChange editText??
        bd = BDController.getInstance(getApplicationContext());
        editName = findViewById(R.id.editName);
        editPass = findViewById(R.id.editPass);
        editConfirmPass = findViewById(R.id.editConfirmPass);
        editNom = findViewById(R.id.editNom);
        editSurname = findViewById(R.id.editSurname);
        buttonRegister = findViewById(R.id.buttonRegister);
        //todo hacerlo mas chachi
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String password = editPass.getText().toString() ;
                String confpass = editConfirmPass.getText().toString();
                String nom = editNom.getText().toString();
                String surname = editSurname.getText().toString();
                if(!editName.getText().toString().isEmpty() && !password.isEmpty() && password.equals(confpass) && !nom.isEmpty() && !surname.isEmpty()){
                    String userName = editName.getText().toString();
                    try {
                        if(bd.registrarUserNuevo(userName, password, nom, surname)) show_snackbar();
                    }catch(Misc.RegisterException e){
                        Toast.makeText(getApplicationContext(),"Error al registrar en la BD",Toast.LENGTH_SHORT).show();
                    }catch (Misc.UserExitsException e){
                        Toast.makeText(getApplicationContext(),"Ya existe ese usuario",Toast.LENGTH_SHORT).show();
                    }

                }else{
                    Toast.makeText(getApplicationContext(),"error en el formulario",Toast.LENGTH_SHORT).show();
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
                        /*
                        Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                        startActivity(i);*/
                        //finish activity
                        finish();
                    }
                })
                .show(); // Importante!!! No olvidar mostrar la Snackbar.
    }
}
