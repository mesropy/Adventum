package com.example.ourgame.RunningGame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.util.Log;

public class Player extends Sprite {
    private final static int GRAVITY = 3;
    private final static int JUMP = -35;
    private boolean jump = false;

    Player(Bitmap image, int x, int y, int width, int height, int roadHeight) {
        super(image, x, y, width, height, roadHeight);
    }

    public void update() {
        // See if the player has hit the ground
        if (jump && this.getHitbox().bottom == getGroundHeight()){
            setDy(JUMP);
            jump = false;
        }
        else if (this.getHitbox().bottom >= getGroundHeight()){
            setY(getGroundHeight() - getHeight());
            setDy(0);
        }else{
            setDy(getDy()+GRAVITY);
        }
        super.update();
    }

    void jump() {
        jump = true;
    }
}
