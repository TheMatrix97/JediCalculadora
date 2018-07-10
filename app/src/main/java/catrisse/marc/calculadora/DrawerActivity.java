package catrisse.marc.calculadora;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class DrawerActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    DrawerLayout drawer;
    NavigationView navigationView;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);
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
        user = (User) getIntent().getSerializableExtra("user");
        View header = navigationView.getHeaderView(0);
        TextView draw_name = header.findViewById(R.id.textView_drawer_name);
        TextView draw_surname = header.findViewById(R.id.textView_drawer_surname);
        draw_name.setText(user.getNom());
        draw_surname.setText(user.getSurname());
        load_firstFrag();


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

            case R.id.drawer_logout:
                finish();
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        TextView textViewResult = findViewById(R.id.textViewResult);
        TextView textViewOp = findViewById(R.id.textView2);
        outState.putString("operationField", textViewOp.getText().toString());
        outState.putString("resultField", textViewResult.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        TextView textViewResult = findViewById(R.id.textViewResult);
        TextView textViewOp = findViewById(R.id.textView2);
        String textOp = savedInstanceState.getString("operationField");
        String textRes = savedInstanceState.getString("resultField");
        textViewOp.setText(textOp);
        textViewResult.setText(textRes);
    }
    private void load_firstFrag() {
        BlankFragment bf = new BlankFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container,bf)
                .commit();
    }


}
