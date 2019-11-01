package com.example.ourgame;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;


public class DataWriter implements WriteData {

    /* CSV File Format:
     * Every ROW is a different user with the stats in this order:
     * [Username, Password, ReactionGameScore, ReactionGameTime, TileGameScore, TileGameTime,
     *  PictureGameScore, PictureGameTime, LastGamePlayed]
     */


    private SharedPreferences loginData;
    private SharedPreferences pointsData;
    private SharedPreferences timeData;
    private SharedPreferences rankingData;
    private SharedPreferences lastGameData;


    public DataWriter(Context context){
        loginData = context.getSharedPreferences(context.getString(R.string.preference_file_login), Context.MODE_PRIVATE);
        pointsData = context.getSharedPreferences(context.getString(R.string.preference_file_points), Context.MODE_PRIVATE);
        timeData = context.getSharedPreferences(context.getString(R.string.preference_file_time), Context.MODE_PRIVATE);
        rankingData = context.getSharedPreferences(context.getString(R.string.preference_file_ranking), Context.MODE_PRIVATE);
        lastGameData = context.getSharedPreferences(context.getString(R.string.preference_file_lastgame), Context.MODE_PRIVATE);
    }

    @Override
    public void addUser(String username, String password) {
        // First add the user's password
        SharedPreferences.Editor editor = loginData.edit();
        editor.putString(username, password);
        editor.apply();

        // Secondly add the user's default points
        editor = pointsData.edit();
        editor.putInt(username, 0);
        editor.apply();

        // Thirdly add the user's default time
        editor = timeData.edit();
        editor.putInt(username, 0);
        editor.apply();

        // Fourth add the user's default ranking
        editor = rankingData.edit();
        editor.putString(username, "Bronze");
        editor.apply();

        // Lastly add the user's default last game played
        editor = lastGameData.edit();
        editor.putString(username, "Reaction");
        editor.apply();
    }

    @Override
    public String getPassword(String username) {
        return loginData.getString(username, "NotFound");
    }

    @Override
    public int getPoints(String username) {
        return pointsData.getInt(username, -1);
    }

    @Override
    public int getPlayTime(String username) {
        return timeData.getInt(username, -1);
    }

    @Override
    public String getRanking(String username) {
        return rankingData.getString(username, "Not Found");
    }

    @Override
    public void addPoints(String username, int points) {
        int currentPoints = getPoints(username);
        SharedPreferences.Editor editor = pointsData.edit();
        editor.putInt(username, points + currentPoints);
        editor.apply();
    }

    @Override
    public void addPlayTime(String username, int playTime) {
        SharedPreferences.Editor editor = timeData.edit();
        editor.putInt(username, playTime + getPlayTime(username));
        editor.apply();
    }

    @Override
    public void addRanking(String username, String ranking) {
        SharedPreferences.Editor editor = rankingData.edit();
        editor.putString(username, ranking);
        editor.apply();
    }

    @Override
    public void addLastGame(String username, String lastgame) {
        SharedPreferences.Editor editor = lastGameData.edit();
        editor.putString(username, lastgame);
        editor.apply();
    }

    @Override
    public String getLastGame(String username) {
        return lastGameData.getString(username, "Not Found");
    }

    @Override
    public boolean checkUser(String username) {
        return !((loginData.getString(username, "Not Found")).equals("Not Found"));
    }
}
