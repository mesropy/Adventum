package com.example.ourgame.Games.RunningGame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.List;

public class EndlessRunner extends SurfaceView implements SurfaceHolder.Callback, EndlessRunnerView {
    private SurfaceHolder surfaceHolder;
    private EndlessRunnerGame game;
    private EndlessRunnerThread thread;

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

    }

    @Override
    public EndlessRunnerThread getThread() {
        return thread;
    }
}
