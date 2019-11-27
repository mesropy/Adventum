package com.example.ourgame.Games.RunningGame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.widget.FrameLayout;

import com.example.ourgame.R;
import com.example.ourgame.ScreenLoader;
import com.example.ourgame.Statistics.DataWriter;
import com.example.ourgame.ThemeSetters.Theme;
import com.example.ourgame.ThemeSetters.ThemeBuilder;

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
