package com.example.ourgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.ourgame.login.Login;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        String message = intent.getStringExtra(Login.EXTRA_MESSAGE);
        TextView textView = findViewById(R.id.welcome);
        textView.setText("Welcome " + message + " !");
    }

    public void playReactionGame(View view){
        Intent intent = new Intent(this, ReactionTime.class);
        startActivity(intent);
    }

}
