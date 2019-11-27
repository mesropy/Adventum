package com.example.ourgame.Games.RunningGame;

import android.content.Context;
import android.content.Intent;
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

import com.example.ourgame.R;
import com.example.ourgame.ScreenLoader;
import com.example.ourgame.Statistics.DataWriter;
import com.example.ourgame.Statistics.StatisticsActivity;

import java.util.ArrayList;
import java.util.List;

public class EndlessRunner extends SurfaceView implements SurfaceHolder.Callback, EndlessRunnerView {
    private SurfaceHolder surfaceHolder;
    private EndlessRunnerGame game;
    private EndlessRunnerThread thread;
    private Paint paint;
    private Context context;

    private Rect screen;

    public EndlessRunner(Context context, AttributeSet attrs) {
        super(context, attrs);

        surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);
        this.context = context;

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        // Gameview created
        screen = new Rect(0, 0, getWidth(), getHeight());

        game = new EndlessRunnerGame(this, new DataWriter(context));
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
    public void draw(Player player, List<Sprite> obstacles, int score) {
        Canvas canvas = surfaceHolder.lockCanvas();
        if (canvas != null) {
            canvas.drawColor(Color.WHITE);
            player.draw(canvas);
            paint.setTextSize(50);
            paint.setColor(Color.RED);
            canvas.drawText(Integer.toString(score), 10, 40, paint);
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
    public void nextGame() {
        final Intent intent = new Intent(context, StatisticsActivity.class);
        intent.putExtra("next activity", context.getString(R.string.runner_game));
        context.startActivity(intent);
    }

    @Override
    public void loseGame(int score) {
        Canvas canvas = surfaceHolder.lockCanvas();
        if (canvas != null){
            paint.setTextSize(50);
            paint.setColor(Color.RED);
            canvas.drawText("Game Over", getWidth()/2-85, getHeight()/2, paint);
            paint.setTextSize(40);
            paint.setColor(Color.BLUE);
            String scoreString = "Score: " + Integer.toString(score);
            canvas.drawText(scoreString, getWidth()/2-85, getHeight()/2+50, paint);
        }
        surfaceHolder.unlockCanvasAndPost(canvas);
    }

    @Override
    public EndlessRunnerThread getThread() {
        return thread;
    }
}
