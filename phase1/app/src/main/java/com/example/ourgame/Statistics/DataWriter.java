package com.example.ourgame.Statistics;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.ourgame.R;

/**
 * A class for a DataWriter, which keeps and stores user login information
 */
public class DataWriter implements WriteData {

    /* We set a different SharedPreference file for each stat and password.
     * SharedPreferences are an easy way to store key - value pairs that are then
     * stored in an XML file on the phone. Since the data we are saving is quite
     * simple, this is a easy and efficient solution.
     */

    private String[] rankings = {"Bronze", "Silver", "Gold", "Platinum"};

    private SharedPreferences loginData;
    private SharedPreferences pointsData;
    private SharedPreferences timeData;
    private SharedPreferences rankingData;
    private SharedPreferences lastGameData;
    private SharedPreferences currUserData;
    private Context context;

    public DataWriter(Context context){
        this.context = context;
        loginData = context.getSharedPreferences(context.getString(R.string.preference_file_login), Context.MODE_PRIVATE);
        pointsData = context.getSharedPreferences(context.getString(R.string.preference_file_points), Context.MODE_PRIVATE);
        timeData = context.getSharedPreferences(context.getString(R.string.preference_file_time), Context.MODE_PRIVATE);
        rankingData = context.getSharedPreferences(context.getString(R.string.preference_file_ranking), Context.MODE_PRIVATE);
        lastGameData = context.getSharedPreferences(context.getString(R.string.preference_file_lastgame), Context.MODE_PRIVATE);
        currUserData = context.getSharedPreferences(context.getString(R.string.preference_file_user), Context.MODE_PRIVATE);
    }

    /**
     * Method to create a new account for a new user
     * @param username the username of the new account
     * @param password the password of the new account
     */
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

        // Fifth add the user's default last game played
        editor = lastGameData.edit();
        editor.putString(username, "Reaction");
        editor.apply();

        editor = currUserData.edit();
        editor.putString(context.getString(R.string.preference_file_user), username);
        editor.apply();
    }

    @Override
    public void setUser(String username) {
        SharedPreferences.Editor editor = currUserData.edit();
        editor.putString(context.getString(R.string.preference_file_user), username);
        editor.apply();
    }

    @Override
    public String getUser() {
        return currUserData.getString(context.getString(R.string.preference_file_user), "Not found");
    }

    @Override
    public String getPassword(String username) {
        return loginData.getString(username, "Not Found");
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

    /**
     * Add points the user has obtained in a game
     * @param username the user to add points to
     * @param points the amount of points to give to the user
     */
    @Override
    public void addPoints(String username, int points) {
        int currentPoints = getPoints(username);
        SharedPreferences.Editor editor = pointsData.edit();
        editor.putInt(username, points + currentPoints);
        editor.apply();
    }

    /**
     * Add the amount of time this player has played in a game
     * @param username the user to add play time to
     * @param playTime the amount of time played
     */
    @Override
    public void addPlayTime(String username, int playTime) {
        SharedPreferences.Editor editor = timeData.edit();
        editor.putInt(username, playTime + getPlayTime(username));
        editor.apply();
    }

    /**
     * Updates the user's ranking at the end of a game if it achieved something difficult
     * (ex/ solve every level very quickly)
     *
     * @param username the user to increase the ranking of
     */
    @Override
    public void increaseRanking(String username) {
        String currentRanking = getRanking(username);
        String newRanking;

        // only increase if not the highest ranking
        for (int i = 0; i < rankings.length - 1; i++)
            if (currentRanking.equals(rankings[i])) {
                newRanking = rankings[i + 1];
                SharedPreferences.Editor editor = rankingData.edit();
                editor.putString(username, newRanking);
                editor.apply();
                break;
            }
    }

    /**
     * Adds the name of the last game the user had played
     * @param username the user who had played a game
     * @param lastgame the game that was last played by the user
     */
    @Override
    public void addLastGame(String username, String lastgame) {
        SharedPreferences.Editor editor = lastGameData.edit();
        editor.putString(username, lastgame);
        editor.apply();
    }

    /**
     * Gets the name of the game the user had last played
     * @param username the user who had played a game
     * @return the name of the game
     */
    @Override
    public String getLastGame(String username) {
        return lastGameData.getString(username, "Not Found");
    }

    /**
     * Check if an account with the given username exists in the raw2
     * @param username the username being checked
     * @return a boolean representing if the username was found or not
     */
    @Override
    public boolean checkUser(String username) {
        return !((loginData.getString(username, "Not Found")).equals("Not Found"));
    }

    /**
     * Return the map containing user names and their corresponding point values
     * @return a SharedPreferences instance storing points data
     */
    public SharedPreferences getPointsData(){
        return pointsData;
    }
}
