package catrisse.marc.calculadora;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import io.realm.Realm;
import io.realm.RealmResults;

public class LoginActivity extends AppCompatActivity {
    TextInputEditText editUser;
    TextInputEditText editPass;
    Button login;
    Button register;
    final int idNotification = 2;
    Realm realm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ini();
    }

    private void ini() {
        iniRealm();
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
                User userauth = estaRegistrado(aux);
                if(userauth != null){
                    cancelarNotificacion();
                    Intent i = new Intent(getApplicationContext(),DrawerActivity.class);
                    i.putExtra("user", userauth);
                    startActivity(i);
                }else{
                    Toast.makeText(getApplicationContext(),"Login incorrecto", Toast.LENGTH_LONG).show();
                    mostrar_notificacion_estado(aux.getUsername());
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

    private User estaRegistrado(User aux) {
        String nom = aux.getUsername();
        String passw = aux.getPass();
        RealmResults<User> result = realm.where(User.class).equalTo("username",nom) //buscamos user con mismo nombre y pass
                .and()
                .equalTo("pass",passw).findAll();
        if(result.size() == 1) return realm.copyFromRealm(result.get(0));
        else return null;
    }

    private void iniRealm() {
        Realm.init(getApplicationContext());
        this.realm = Realm.getDefaultInstance();
    }

    private void mostrar_notificacion_estado(String nom) {
        //mas info https://developer.android.com/training/notify-user/build-notification
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
        Notification notification = mBuilder.build();
        notification.flags |= Notification.FLAG_NO_CLEAR; //añadimos flag para que no se pueda 'limpiar'

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel  nc = new NotificationChannel("default",getString(R.string.channelName)
                    ,NotificationManager.IMPORTANCE_HIGH);
            mNotificationManager.createNotificationChannel(nc);
        }
        mNotificationManager.notify(idNotification,notification);
    }

    private void cancelarNotificacion(){
        NotificationManager
                mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.cancel(idNotification);
    }


    /* TEST THREAD */
    private void thread_start(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                int i = 0;
                while(i < 10000){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Log.v("thread", "Awake");
                    i += 1000;
                }
            }
        }).start();

    }

}
