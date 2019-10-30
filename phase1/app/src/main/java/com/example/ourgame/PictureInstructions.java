package com.example.ourgame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class PictureInstructions extends AppCompatActivity {

    TextView instructionsText;
    Button gotItButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture_instructions);

        instructionsText = findViewById(R.id.instructionsText);
        gotItButton = findViewById(R.id.gotItButton); // set up what this does
    }
}
