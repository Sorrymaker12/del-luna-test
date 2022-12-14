package com.example.labux;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

public class    Items extends AppCompatActivity {

    TabLayout tlTab;
    ViewPager2 vpFragment;
    FragmentAdapter fragmentAdapter;

    void init(){
        tlTab = findViewById(R.id.tlTab);
        vpFragment = findViewById(R.id.vpFragment);
        fragmentAdapter = new FragmentAdapter(getSupportFragmentManager(), getLifecycle());
        vpFragment.setAdapter(fragmentAdapter);
    }

    void populateTab(){
        tlTab.addTab(tlTab.newTab().setText("Cloth"));
        tlTab.addTab(tlTab.newTab().setText("Album"));
        tlTab.addTab(tlTab.newTab().setText("Other"));
    }

    void setupTab() {
        tlTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                vpFragment.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }


    DrawerLayout DL;
    NavigationView NV;
    Toolbar TB;
    ActionBarDrawerToggle AB;
    Bundle Extras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items);

        Extras = getIntent().getExtras();
        String a = Extras.getString("username");

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
                        Intent intent = new Intent(Items.this, Home.class);
                        intent.putExtra("username", a);
                        startActivity(intent);
                        finish();
                        break;

                    case R.id.nav_items:
                        DL.closeDrawer(GravityCompat.START);
                        Intent intent1 = new Intent(Items.this, Items.class);
                        intent1.putExtra("username", a);
                        startActivity(intent1);
                        finish();
                        break;

                    case R.id.nav_about:
                        DL.closeDrawer(GravityCompat.START);
                        Intent intent2 = new Intent(Items.this, AboutUs.class);
                        intent2.putExtra("username", a);
                        startActivity(intent2);
                        finish();
                        break;

                    case R.id.nav_signout:
                        DL.closeDrawer(GravityCompat.START);
                        Intent intent3 = new Intent(Items.this, MainActivity.class);
                        intent3.putExtra("username", a);
                        startActivity(intent3);
                        finish();
                        break;

                }
                return true;
            }
        });

        init();
        populateTab();
        setupTab();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {


        if (AB.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}


