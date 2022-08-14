package com.example.labux;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.Intent;
import android.graphics.Color;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import java.util.concurrent.atomic.AtomicBoolean;

public class ItemDetail extends AppCompatActivity {
    ImageView imageView;
    TextView pName, pPrice;
    EditText quantity;
    Button buyBtn;
    String qty;

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

        buyBtn.setOnClickListener(e ->  {
            flag.set(true);
            qty = quantity.getText().toString();

            if (!checkEmpty(qty)) {
                openDialogBox();
//                quantity.setError("Quantity must be more than 0");
                flag.set(false);
            }


            if (flag.get()) {
                buyBtn.setBackgroundColor(Color.WHITE);
                Intent intent = new Intent(this, Items.class);
                startActivity(intent);
                finish();
            }
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

        Bundle Extras = getIntent().getExtras();
        String itemname = Extras.getString("name");
        String itemprice = Extras.getString("price");
        Object itemimage = Extras.get("image");
        pName.setText(itemname);
        pPrice.setText(itemprice);
        imageView.setImageResource((Integer) itemimage);

        init();
    }
}