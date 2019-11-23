package com.example.ourgame.Games.RunningGame;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

class Sprite {

    private Bitmap image;
    private Rect hitbox;
    private int groundHeight;

    private Paint paint;

    private int x;
    private int y;
    private int dx;
    private int dy;
    private int width;
    private int height;

    void setWidth(int width) {
        this.width = width;
    }

    int getX() {
        return x;
    }

    void setHeight(int height) {
        this.height = height;
    }

    int getWidth() {
        return width;
    }

    Sprite(Bitmap image, int x, int y, int width, int height, int groundHeight){
        this.image = image;
        this.hitbox = new Rect(x, y, x+width, y+height);
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
            canvas.drawBitmap(image, null, hitbox, null);
        }else{
            canvas.drawRect(hitbox, paint);
        }
    }

    int getHeight() {
        return height;
    }

    private void setX(int x) {
        this.x = x;
        hitbox.set(x, y, x + width, y + height);
    }

    int getY() {
        return y;
    }

    void setY(int y) {
        this.y = y;
        hitbox.set(x, y, x + width, y + height);
    }

    void setDx(int dx) {
        this.dx = dx;
    }

    void setDy(int dy) {
        this.dy = dy;
    }

    public int getDx() {
        return dx;
    }

    int getDy() {
        return dy;
    }

    Rect getHitbox() {
        return hitbox;
    }

    void update(){
        setX(x+dx);
        setY(y+dy);
    }
}
