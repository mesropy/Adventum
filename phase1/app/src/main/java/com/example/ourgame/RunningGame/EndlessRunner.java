package com.example.ourgame.RunningGame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class EndlessRunner extends SurfaceView implements SurfaceHolder.Callback, EndlessRunnerView {
    private SurfaceHolder surfaceHolder;
    private EndlessRunnerGame game;
    private EndlessRunnerThread thread;
    private Paint paint;

    private Rect screen;

    public EndlessRunner(Context context, AttributeSet attrs) {
        super(context, attrs);

        surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        // Gameview created
        screen = new Rect(0, 0, getWidth(), getHeight());

        game = new EndlessRunnerGame(this);
        paint = new Paint();
        thread = new EndlessRunnerThread(game);

        thread.setRunning(true);
        thread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    @Override
    public void draw(Player player, List<Sprite> obstacles) {
        Canvas canvas = surfaceHolder.lockCanvas();
        if (canvas != null) {
            canvas.drawColor(Color.WHITE);
            player.draw(canvas);
            for (Sprite obstacle : obstacles) {
                obstacle.draw(canvas);
            }
        }
        surfaceHolder.unlockCanvasAndPost(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == android.view.MotionEvent.ACTION_UP){
            game.onTapEvent();
        }
        return true;

    }

    public Rect getScreen() {
        return screen;
    }

    @Override
    public void loseGame() {
        Canvas canvas = surfaceHolder.lockCanvas();
        if (canvas != null){
            paint.setTextSize(50);
            paint.setColor(Color.RED);
            canvas.drawText("Game Over", getWidth()/2-85, getHeight()/2, paint);
        }
        surfaceHolder.unlockCanvasAndPost(canvas);
    }

    @Override
    public EndlessRunnerThread getThread() {
        return thread;
    }
}
