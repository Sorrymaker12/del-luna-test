package com.example.labux;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class Home extends AppCompatActivity {

    DrawerLayout DL;
    NavigationView NV;
    Toolbar TB;
    ActionBarDrawerToggle AB;
    TextView TV_Home;
    Bundle Extras;

    Button btnLookItem, btnImageNext, btnImagePrevious;
    ViewPager2 viewPager2;
    List<Image> images;
    ImageAdapter imageAdapter;
    Handler sliderHandler = new Handler();
    int index;

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

        //Button Look Items
        btnLookItem = findViewById(R.id.BTN_lookitem);
        btnLookItem.setOnClickListener(e->{
            Intent intent1 = new Intent(Home.this, Items.class);
            intent1.putExtra("username", a);
            startActivity(intent1);
            finish();
        });

        //Image Slider
        viewPager2 = findViewById(R.id.VP_imgSlide);
        images = new ArrayList<>();

        images.add(new Image(R.drawable.slide1));
        images.add(new Image(R.drawable.slide2));
        images.add(new Image(R.drawable.slide3));
        images.add(new Image(R.drawable.slide4));
        images.add(new Image(R.drawable.slide5));

        imageAdapter = new ImageAdapter(images, viewPager2);
        viewPager2.setAdapter(imageAdapter);

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                sliderHandler.removeCallbacks(sliderRunnable);
                sliderHandler.postDelayed(sliderRunnable, 5000);
            }
        });

        //next and prev button
        initButtons();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (AB.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void initButtons(){
        index=0;
        btnImageNext = findViewById(R.id.BTN_imageNext);
        btnImagePrevious = findViewById(R.id.BTN_imagePrevious);

        btnImageNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                next();
            }
        });

        btnImagePrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prev();
            }
        });
    }

    public void next(){
        index++;
        if(index>=imageAdapter.getItemCount()) index=0;
        viewPager2.setCurrentItem(index);
    }

    public void prev(){
        if(index<=0) index=imageAdapter.getItemCount();
        else index--;
        viewPager2.setCurrentItem(index);
    }

    public Runnable sliderRunnable = new Runnable() {
        @Override
        public void run() {
            viewPager2.setCurrentItem(viewPager2.getCurrentItem()+1);
        }
    };

}