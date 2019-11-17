package com.example.ourgame.RunningGame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.ourgame.R;

public class EndlessRunnerActivity extends AppCompatActivity {

    private EndlessRunnerView gameView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_endless_runner);

        gameView = new EndlessRunner(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        gameView.getThread().setRunning(false);
    }
}
