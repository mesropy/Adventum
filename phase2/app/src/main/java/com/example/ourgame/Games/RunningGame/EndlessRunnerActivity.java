package com.example.ourgame.Games.RunningGame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.ourgame.R;

public class EndlessRunnerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_endless_runner);
    }

    @Override
    protected void onPause() {
        super.onPause();
        ((EndlessRunner) findViewById(R.id.gameView)).getThread().setRunning(false);
    }
}
