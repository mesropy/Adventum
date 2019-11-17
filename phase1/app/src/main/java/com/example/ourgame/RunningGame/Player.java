package com.example.ourgame.RunningGame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;

public class Player extends Sprite {
    public Player(Bitmap image, int x, int y, int width, int height, Rect screen) {
        super(image, x, y, width, height, screen);
    }

    public void update() {
        Rect screen = getScreen();

        // See if the player has hit the ground
        if (this.getHitbox().bottom >= screen.height() - screen.width() / 10){
            this.setY(screen.height() - screen.width() / 10 - getHeight());
            setDy(0);

        }
        super.update();
    }
}
