package com.example.ourgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.ourgame.Statistics.DataWriter;
import com.example.ourgame.login.Login;

public class Customization extends AppCompatActivity {

    private String user;
    private DataWriter data;

    RadioGroup rg1;
    RadioButton rb1, rb2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customization);

        rg1 = findViewById(R.id.radiogroup1);
        rb2 = findViewById(R.id.radioButton2);
        rb1 = findViewById(R.id.radioButton1);

        Intent intent = getIntent();
        user = intent.getStringExtra(Login.EXTRA_MESSAGE);
        data = new DataWriter(this);

        if (data.getLanguage(user).equals("french")){
            rb1.setChecked(false);
            rb2.setChecked(true);
        }
    }

    public void onLanguageClicked(View view) {
        int checkedId = rg1.getCheckedRadioButtonId();
        if (checkedId == R.id.radioButton1) {
            data.setLanguage(user, "english");
        } else if (checkedId == R.id.radioButton2) {
            data.setLanguage(user, "french");
        }
    }

    public void onBackToGameClicked(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("username", user);
        startActivity(intent);
    }
}
