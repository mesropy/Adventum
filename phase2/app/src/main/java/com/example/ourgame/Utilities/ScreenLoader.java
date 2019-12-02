package com.example.ourgame.Utilities;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.provider.ContactsContract;

import com.example.ourgame.Games.GameName;
import com.example.ourgame.Games.HangmanGame.HangmanActivity;
import com.example.ourgame.Games.InstructionsActivity;
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
    // improve this
    private String [] games = GameName.names();

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

    // loads instructions of the next game, after which the next game can be played
    public void loadNextGame() {
        Intent intent = new Intent(context, InstructionsActivity.class);

        // randomly pick one of the games
        String nextGameName = games[(int) (Math.random() * games.length)];
        String lastGameName = (new DataWriter(context)).getLastGame();

        // keep picking a new game until it isn't the same as the last one
        while(nextGameName.equals(lastGameName)){
            nextGameName = games[(int) (Math.random() * games.length)];
        }
        intent.putExtra("game", nextGameName);

        context.startActivity(intent);
    }


    public void loadGame(String gameName){
        Intent intent;

        switch (gameName) {
            case "HANGMAN":
                intent = new Intent(context, HangmanActivity.class);
                break;
            case "PICTURE":
                intent = new Intent(context, PictureGameActivity.class);
                break;
            case "REACTION":
                intent = new Intent(context, ReactionGameActivity.class);
                break;
            case "RUNNING":
                intent = new Intent(context, EndlessRunnerActivity.class);
                break;
            default:  // Tile
                intent = new Intent(context, TileGameActivity.class);
                break;
        }

        context.startActivity(intent);
    }
}


