package com.example.ourgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * An activity class for the Picture Game instructions
 */
public class PictureInstructions extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture_instructions);
    }

    public void onGotItPressed(View view){
        Intent intent = new Intent(this, PictureGameActivity.class);
        startActivity(intent);
    }
}
