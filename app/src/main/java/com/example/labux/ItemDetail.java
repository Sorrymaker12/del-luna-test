package com.example.labux;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

import java.util.concurrent.atomic.AtomicBoolean;

public class ItemDetail extends AppCompatActivity {
    ImageView imageView;
    TextView pName, pPrice;
    EditText quantity;
    Button buyBtn, backBtn;
    String qty;
    DrawerLayout DL;
    NavigationView NV;
    Toolbar TB;
    ActionBarDrawerToggle AB;
    Bundle Extras, Extras1;


    boolean checkEmpty (String qty) {
        if (qty.length() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public void init(){
        AtomicBoolean flag = new AtomicBoolean(true);
        quantity = findViewById(R.id.ET_itemQty);
        buyBtn = findViewById(R.id.BTN_buy);
        backBtn = findViewById(R.id.BTN_back);

        buyBtn.setOnClickListener(e ->  {
            flag.set(true);
            qty = quantity.getText().toString();

            if (!checkEmpty(qty)) {
                openDialogBox();
//                quantity.setError("Quantity must be more than 0");
                flag.set(false);
            }


            if (flag.get()) {
                Extras = getIntent().getExtras();
                String a = Extras.getString("username");
                buyBtn.setBackgroundColor(Color.BLACK);
                Intent intent1 = new Intent(ItemDetail.this, Items.class);
                intent1.putExtra("username", a);
                startActivity(intent1);
                finish();
            }
        });

        backBtn.setOnClickListener(e -> {
            Extras = getIntent().getExtras();
            String a = Extras.getString("username");
            Intent intent1 = new Intent(ItemDetail.this, Items.class);
            intent1.putExtra("username", a);
            startActivity(intent1);
            finish();
        });
    }

    private void openDialogBox() {
        DialogBox dialogBox = new DialogBox();
        dialogBox.show(getSupportFragmentManager(), "Error Dialog Box");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);
        imageView = findViewById(R.id.IV_itemImg);
        pName = findViewById(R.id.TV_itemName);
        pPrice = findViewById(R.id.TV_itemPrice);

        Extras = getIntent().getExtras();
        Extras1 = getIntent().getExtras();
        String itemname = Extras1.getString("name");
        String itemprice = Extras1.getString("price");
        Object itemimage = Extras1.get("image");
        pName.setText(itemname);
        pPrice.setText(itemprice);
        imageView.setImageResource((Integer) itemimage);

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
                        Intent intent = new Intent(ItemDetail.this, Home.class);
                        intent.putExtra("username", a);
                        startActivity(intent);
                        finish();
                        break;

                    case R.id.nav_items:
                        DL.closeDrawer(GravityCompat.START);
                        Intent intent1 = new Intent(ItemDetail.this, Items.class);
                        intent1.putExtra("username", a);
                        startActivity(intent1);
                        finish();
                        break;

                    case R.id.nav_about:
                        DL.closeDrawer(GravityCompat.START);
                        Intent intent2 = new Intent(ItemDetail.this, AboutUs.class);
                        intent2.putExtra("username", a);
                        startActivity(intent2);
                        finish();
                        break;

                    case R.id.nav_signout:
                        DL.closeDrawer(GravityCompat.START);
                        Intent intent3 = new Intent(ItemDetail.this, MainActivity.class);
                        intent3.putExtra("username", a);
                        startActivity(intent3);
                        finish();
                        break;

                }
                return true;
            }
        });

        init();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (AB.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}