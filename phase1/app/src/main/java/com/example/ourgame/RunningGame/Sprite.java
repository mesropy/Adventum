package com.example.ourgame.RunningGame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

class Sprite {

    private Bitmap image;
    private Context context;
    private Rect hitbox;
    private Rect screen;

    private Paint paint;

    private int x;
    private int y;
    private int dx;
    private int dy;
    private int width;
    private int height;

    public Sprite(Bitmap image, int x, int y, int width, int height, Rect screen){
        this.image = image;
        this.hitbox = new Rect(x, y, width, height);
        this.screen = screen;

        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
        this.dx = 0;
        this.dy = 0;

        paint.setStrokeWidth(10);
        paint.setStyle(Paint.Style.STROKE);
    }

    public void draw(Canvas canvas){
        if (image != null){
            canvas.drawBitmap(image, null, hitbox, null);
        }else{
            canvas.drawRect(hitbox, paint);
        }
    }

    public int getHeight() {
        return height;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Rect getScreen() {
        return screen;
    }

    public void setDx(int dx) {
        this.dx = dx;
    }

    public void setDy(int dy) {
        this.dy = dy;
    }

    public int getDx() {
        return dx;
    }

    public int getDy() {
        return dy;
    }

    public Rect getHitbox() {
        return hitbox;
    }

    public void update(){
        x += dx;
        y += dy;
    }
}
