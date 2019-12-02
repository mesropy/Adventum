package com.example.ourgame.Games.RunningGame;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;


import com.example.ourgame.Languages.LanguageFactory;
import com.example.ourgame.Languages.Language;
import com.example.ourgame.Utilities.DataWriter;
import com.example.ourgame.Utilities.ScreenLoader;
import com.example.ourgame.Utilities.WriteData;

import java.util.List;

public class EndlessRunner extends SurfaceView implements SurfaceHolder.Callback, EndlessRunnerView {
    private SurfaceHolder surfaceHolder;
    private Language language;
    private EndlessRunnerGame game;
    private EndlessRunnerThread thread;
    private Paint paint;
    private Context context;
    private Rect screen;
    private Sprite background;
    private BitmapFactory.Options options;

    public EndlessRunner(Context context, AttributeSet attrs) {
        super(context, attrs);

        surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);
        this.context = context;

        options = new BitmapFactory.Options();
        options.inScaled = false;
    }

    private void setTheme(int theme) {
        background = new Sprite(BitmapFactory.decodeResource(context.getResources(), theme, options),
                0, 0, screen.width(), screen.height(), 0);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        // Gameview created
        screen = new Rect(0, 0, getWidth(), getHeight());
        WriteData dataWriter = new DataWriter(context);

        game = new EndlessRunnerGame(this, dataWriter);

        LanguageFactory text = new LanguageFactory(game.getLanguage(), context);
        language = text.getTextSetter();

        setTheme(dataWriter.getThemeData());

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
            //canvas.drawColor(Color.WHITE);
            background.draw(canvas);
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
        ScreenLoader screenLoader = new ScreenLoader(context);
        screenLoader.loadStatisticsAfterGame();
    }

    @Override
    public void loseGame(int score) {
        Canvas canvas = surfaceHolder.lockCanvas();
        if (canvas != null){
            paint.setTextSize(50);
            paint.setColor(Color.RED);
            canvas.drawText(language.getPictureNoMoreAttempts(), getWidth()/2-85, getHeight()/2, paint);
            paint.setTextSize(40);
            paint.setColor(Color.BLUE);
            String scoreString = language.score() + score;
            canvas.drawText(scoreString, getWidth()/2-85, getHeight()/2+50, paint);
        }
        surfaceHolder.unlockCanvasAndPost(canvas);
    }

    @Override
    public EndlessRunnerThread getThread() {
        return thread;
    }

}
