package catrisse.marc.calculadora;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.NotificationCompat;
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
    Button register;
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
        register = findViewById(R.id.buttonRegisterLog);
        setSupportActionBar(tb);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editUser.getText().toString();
                String password = editPass.getText().toString();
                User aux = new User(name, password);
                SharedPreferences settings = getSharedPreferences(RegisterActivity.PREFS_NAME, Context.MODE_PRIVATE);
                if(aux.login(settings.getString(name,""))){
                    Intent i = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(i);
                }else{
                    Toast.makeText(getApplicationContext(),"Login incorrecto", Toast.LENGTH_LONG).show();
                    mostrar_notificacion(aux.getNom());
                }
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),RegisterActivity.class);
                startActivity(i);
            }
        });
    }

    private void mostrar_notificacion(String nom) {
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(getApplicationContext(),"default") //se que esta deprecated, a partir de la 26 te obligan a usar un NotificationChannelID
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentText(String.format("Se ha intentado hacer login con el usuario %s sin éxito",nom))
                .setContentTitle("Notificación");
        Intent resultIntent = new Intent(getApplicationContext(), LoginActivity.class);
        // Indicamos que la Activity empiece en una nueva task
        resultIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                | Intent.FLAG_ACTIVITY_CLEAR_TASK);

        // Creamos el PendingIntent
        PendingIntent notifyPendingIntent =
                PendingIntent.getActivity(
                        this,
                        0,
                        resultIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );
        mBuilder.setContentIntent(notifyPendingIntent);

        NotificationManager
                mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        int myId = 2;
        Notification notification = mBuilder.build();
        notification.flags |= Notification.FLAG_NO_CLEAR; //añadimos flag para que no se pueda 'limpiar'

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel  nc = new NotificationChannel("default",getString(R.string.channelName)
                    ,NotificationManager.IMPORTANCE_LOW);
            mNotificationManager.createNotificationChannel(nc);
        }
        mNotificationManager.notify(myId,notification);
    }

}
