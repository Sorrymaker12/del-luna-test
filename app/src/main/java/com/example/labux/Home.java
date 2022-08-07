package com.example.labux;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.material.navigation.NavigationView;

public class Home extends AppCompatActivity {

    DrawerLayout DL;
    NavigationView NV;
    Toolbar TB;
    ActionBarDrawerToggle AB;
    TextView TV_Home;
    Bundle Extras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


//      Log.d("TAG TAG TAG", "onCreate: TEST TEST");
        TV_Home = findViewById(R.id.TV_Home);
        Extras = getIntent().getExtras();

        String a = Extras.getString("username");
        TV_Home.setText(a);


        DL = findViewById(R.id.DL_Drawer);
        NV = findViewById(R.id.NV_nav);
        AB = new ActionBarDrawerToggle(this, DL, R.string.menu_open, R.string.menu_close);
        DL.addDrawerListener(AB);
        AB.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        NV.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_home:
                        DL.closeDrawer(GravityCompat.START);
                        Intent intent = new Intent(Home.this, Home.class);
                        intent.putExtra("username", a);
                        startActivity(intent);
                        finish();
                        break;

                    case R.id.nav_items:
                        DL.closeDrawer(GravityCompat.START);
                        Intent intent1 = new Intent(Home.this, Items.class);
                        intent1.putExtra("username", a);
                        startActivity(intent1);
                        finish();
                        break;

                    case R.id.nav_about:
                        DL.closeDrawer(GravityCompat.START);
                        Intent intent2 = new Intent(Home.this, AboutUs.class);
                        intent2.putExtra("username", a);
                        startActivity(intent2);
                        finish();
                        break;

                    case R.id.nav_signout:
                        DL.closeDrawer(GravityCompat.START);
                        Intent intent3 = new Intent(Home.this, MainActivity.class);
                        intent3.putExtra("username", a);
                        startActivity(intent3);
                        finish();
                        break;

                }
                return true;
            }
        });


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (AB.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}