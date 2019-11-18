package com.example.ourgame.RunningGame;

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

    public void setWidth(int width) {
        this.width = width;
    }

    public int getX() {
        return x;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public Sprite(Bitmap image, int x, int y, int width, int height, int groundHeight){
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

    public int getGroundHeight() {
        return groundHeight;
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
        hitbox.set(x, y, x + width, y + height);
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
        hitbox.set(x, y, x + width, y + height);
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
        setX(x+dx);
        setY(y+dy);
    }
}
