package com.example.labux;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.concurrent.atomic.AtomicBoolean;

public class MainActivity extends AppCompatActivity {

    String email;
    String pass;
    EditText etEmail;
    EditText etUsername;
    EditText etPass;
    Button btnSubmit;

    boolean checkEmpty (String a) {
        if (a.length() > 0) {
            return true;
        } else {
            return false;
        }
    }

    boolean checkEmailEnding (String a) {
        if (a.endsWith(".com")) {
            return true;
        } else {
            return false;
        }
    }

    boolean checkEmailContain (String a) {
        if (a.contains("@")) {
            return true;
        } else {
            return false;
        }
    }

    boolean checkEmailAdjacent (String a) {
        if (!a.contains("@.com")) {
            return true;
        } else {
            return false;
        }
    }

    boolean checkPassAlphanumeric (String a) {
        String regex1 = ".*[a-zA-Z]+.*";
        String regex2 = ".*[0-9].*";
        String regex3 = "[A-Za-z0-9]*";
        if (a.matches(regex1) && a.matches(regex2) && a.matches(regex3)) {
            return true;
        } else {
            return false;
        }
    }

    void init () {
        AtomicBoolean flag = new AtomicBoolean(true);
        etEmail = findViewById(R.id.ET_Email);
        etPass = findViewById(R.id.ET_Password);
        btnSubmit = findViewById(R.id.BT_Login);
        TextView error = findViewById(R.id.error);
        etUsername = etEmail;

        btnSubmit.setOnClickListener(e ->  {
            flag.set(true);
            email = etEmail.getText().toString();
            pass = etPass.getText().toString();

            if (!checkPassAlphanumeric(pass)) {
                error.setText("Password must be alpha-numeric!");
                error.setVisibility(error.VISIBLE);
                flag.set(false);
            }

            if (!checkEmailAdjacent(email)) {
                error.setText("'@' and '.' must not be adjacent!");
                error.setVisibility(error.VISIBLE);
                flag.set(false);
            }

            if (!checkEmailContain(email)) {
                error.setText("Email must contain '@'!");
                error.setVisibility(error.VISIBLE);
                flag.set(false);
            }

            if (!checkEmailEnding(email)) {
                error.setText("Email must end with '.com'!");
                error.setVisibility(error.VISIBLE);
                flag.set(false);
            }

            if (!checkEmpty(pass)) {
                error.setText("All fields must be filled!");
                error.setVisibility(error.VISIBLE);
                flag.set(false);
            }

            if (!checkEmpty(email)) {
                error.setText("All fields must be filled!");
                error.setVisibility(error.VISIBLE);
                flag.set(false);
            }


            if (flag.get()) {
                btnSubmit.setBackgroundColor(Color.BLACK);
                error.setVisibility(error.INVISIBLE);
                Intent intent = new Intent(this, Home.class);
                String sep[] = email.split("@");
                intent.putExtra("username", sep[0]);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }
}