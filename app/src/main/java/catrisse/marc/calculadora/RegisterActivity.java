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
    public static String PREFS_NAME = "config";

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
                        if(registrarUserNuevo(userName, password, nom, surname)) show_snackbar();
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

    private boolean registrarUserNuevo(String userName, String s, String nom, String surname) throws Misc.UserExitsException, Misc.RegisterException {
        Realm realm = Realm.getDefaultInstance();
        RealmResults<User> res = realm.where(User.class).equalTo("username",userName).findAll();
        if(res.size() == 0){
            final User user = new User(userName,nom,surname,s);
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    realm.copyToRealm(user);
                }
            });
            //check que se ha guardado correctamente (se puede hacer mejor??)
            res = realm.where(User.class).equalTo("username",userName)
                    .and()
                    .equalTo("pass",s).findAll();
            if(res.size() == 0){
                throw new Misc.RegisterException();
            }
            return res.size() == 1;

        }else {
            throw new Misc.UserExitsException();
        }
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
