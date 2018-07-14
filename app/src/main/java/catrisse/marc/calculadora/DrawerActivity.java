package catrisse.marc.calculadora;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import catrisse.marc.utils.BDController;
import catrisse.marc.utils.Misc;
import io.realm.Realm;
import io.realm.RealmResults;

public class DrawerActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    DrawerLayout drawer;
    NavigationView navigationView;
    User user;
    BDController bd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);
        //Generamos la toolbar y hacemos que sea la actionbar de la activity
        bd = BDController.getInstance(getApplicationContext());
        Toolbar toolbar = findViewById(R.id.toolbar_drawer);
        setSupportActionBar(toolbar);
        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.drawer_nav_view);
        //añadimos en navigationView al boton izq de la toolbar
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        //configurar el listener del menu
        navigationView.setNavigationItemSelectedListener(this);
        try {
            user = bd.load_user(getIntent().getStringExtra("user"));
        } catch (Misc.UserNotFound userNotFound) {
            userNotFound.printStackTrace();
        }
        View header = navigationView.getHeaderView(0);
        TextView draw_name = header.findViewById(R.id.textView_drawer_name);
        TextView draw_surname = header.findViewById(R.id.textView_drawer_surname);
        draw_name.setText(user.getNom());
        draw_surname.setText(user.getSurname());
        if(savedInstanceState == null) load_firstFrag();


    }


    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        navigationView.setCheckedItem(item.getItemId());
        switch (item.getItemId()){
            case R.id.drawer_calculadora:
                //change fragment
                CalculadoraFragment calc = new CalculadoraFragment();
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container,calc)
                        .commit();
                break;
            case R.id.drawer_game:
                GameFragment gf = new GameFragment();
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container,gf)
                        .commit();
                break;

            case R.id.drawer_ranking:
                RankingFragment rf = new RankingFragment();
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container,rf)
                        .commit();
                break;
            case R.id.drawer_logout:
                finish();
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void load_firstFrag() {
        BlankFragment bf = new BlankFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container,bf)
                .commit();
    }

}
