package com.example.ourgame.Games.RunningGame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.ourgame.R;
import com.example.ourgame.Utilities.DataWriter;
import com.example.ourgame.Utilities.WriteData;

public class EndlessRunnerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_endless_runner);

        WriteData data = new DataWriter(this);

        // TODO: set theme
    }

    @Override
    protected void onPause() {
        super.onPause();
        ((EndlessRunner) findViewById(R.id.gameView)).getThread().setRunning(false);
    }
}
