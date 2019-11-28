package com.example.ourgame.Utilities;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;

import com.example.ourgame.Games.HangmanGame.HangmanActivity;
import com.example.ourgame.Games.PictureGame.PictureGameActivity;
import com.example.ourgame.Games.ReactionGame.ReactionGameActivity;
import com.example.ourgame.Games.RunningGame.EndlessRunnerActivity;
import com.example.ourgame.Games.TileGame.TileGameActivity;
import com.example.ourgame.Statistics.LeaderBoardActivity;
import com.example.ourgame.Menus.MainActivity;
import com.example.ourgame.Menus.SettingsActivity;
import com.example.ourgame.Statistics.StatisticsActivity;


public class ScreenLoader {

    private Context context;

    public ScreenLoader(Context context) {
        this.context = context;
    }

    public void loadMainMenu() {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

    public void loadStatisticsAfterGame() {
        Intent intent = new Intent(context, StatisticsActivity.class);
        intent.putExtra("after game", true);
        context.startActivity(intent);
    }

    public void loadStatisticsAfterGame(long delayTime) {
        final Intent intent = new Intent(context, StatisticsActivity.class);
        intent.putExtra("after game", true);
        new Handler().postDelayed(new Runnable() {
            public void run() {
                context.startActivity(intent);
            }
        }, delayTime);
    }

    public void loadStatistics() {
        Intent intent = new Intent(context, StatisticsActivity.class);
        intent.putExtra("after game", false);
        context.startActivity(intent);
    }

    public void loadLeaderBoard() {
        Intent intent = new Intent(context, LeaderBoardActivity.class);
        context.startActivity(intent);
    }

    public void loadSettings() {
        Intent intent = new Intent(context, SettingsActivity.class);
        context.startActivity(intent);
    }

    public void loadNextGame() {

        Intent intent = nextGameIntent();
        context.startActivity(intent);
    }

    // randomly picks the next game to play
    private Intent nextGameIntent() {
        // TODO: if player has already played picture game, make sure it cannot go there

        Intent intent;
        // find better way to do this:
        // randomly pick one of the 5 games
        int i = (int) (Math.random() * 5);
        if (i == 0) {
            intent = new Intent(context, HangmanActivity.class);
        } else if (i == 1) {
            intent = new Intent(context, PictureGameActivity.class);
        } else if (i == 2) {
            intent = new Intent(context, ReactionGameActivity.class);
        } else if (i == 3) {
            intent = new Intent(context, TileGameActivity.class);
        } else {
            intent = new Intent(context, EndlessRunnerActivity.class);
        }
        return intent;
    }
}


