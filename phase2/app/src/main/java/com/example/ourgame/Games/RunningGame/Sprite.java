package com.example.ourgame.Games.RunningGame;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

class Sprite {

    private Bitmap image;
    private Rect hitBox;
    private int groundHeight;

    private Paint paint;

    private int x;
    private int y;
    private int dx;
    private int dy;
    private int width;
    private int height;

    int getX() {
        return x;
    }

    Sprite(Bitmap image, int x, int y, int width, int height, int groundHeight){
        this.image = image;
        this.hitBox = new Rect(x, y, x+width, y+height);
        this.groundHeight = groundHeight;

        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
        this.dx = 0;
        this.dy = 0;

        paint = new Paint();
        paint.setStrokeWidth(10);
        paint.setStyle(Paint.Style.STROKE);
    }

    int getGroundHeight() {
        return groundHeight;
    }

    void draw(Canvas canvas){
        if (image != null){
            canvas.drawBitmap(image, null, hitBox, null);
        }else{
            canvas.drawRect(hitBox, paint);
        }
    }

    int getHeight() {
        return height;
    }

    private void setX(int x) {
        this.x = x;
        hitBox.set(x, y, x + width, y + height);
    }

    void setY(int y) {
        this.y = y;
        hitBox.set(x, y, x + width, y + height);
    }

    void setDx(int dx) {
        this.dx = dx;
    }

    void setDy(int dy) {
        this.dy = dy;
    }

    int getDy() {
        return dy;
    }

    Rect getHitBox() {
        return hitBox;
    }

    void update(){
        setX(x+dx);
        setY(y+dy);
    }
}
