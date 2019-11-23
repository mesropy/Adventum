package com.example.ourgame.Games.RunningGame;

import android.graphics.Bitmap;

public class Bull extends Sprite{

    private final int SPEED = -10;

    public Bull(Bitmap image, int x, int y, int groundHeight) {
        super(image, x, y, 70, 50, groundHeight);
        setDx(SPEED);
    }


}
