package com.example.ourgame.RunningGame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class EndlessRunner extends SurfaceView implements SurfaceHolder.Callback, EndlessRunnerView {
    private SurfaceHolder surfaceHolder;
    private EndlessRunnerGame game;
    private EndlessRunnerThread thread;

    private Rect screen;

    public EndlessRunner(Context context) {
        super(context);

        surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);
        screen = new Rect(0, 0, getWidth(), getHeight());

        game = new EndlessRunnerGame(this);
        thread = new EndlessRunnerThread(game);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        // Gameview created
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
    public void drawPlayer(Player player) {
        Canvas canvas = surfaceHolder.lockCanvas();
        if (canvas != null) {
            canvas.drawColor(Color.WHITE);
            player.draw(canvas);
        }

        surfaceHolder.unlockCanvasAndPost(canvas);
    }

    @Override
    public void drawBackground() {

    }

    @Override
    public void drawObstacle() {

    }

    public Rect getScreen() {
        return screen;
    }

    @Override
    public EndlessRunnerThread getThread() {
        return thread;
    }
}
