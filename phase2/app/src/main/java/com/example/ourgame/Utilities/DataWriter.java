package com.example.ourgame.Utilities;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.ourgame.R;

import java.util.HashMap;
import java.util.Map;

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
    private int[] characterImageIds = {R.drawable.boy, R.drawable.female, R.drawable.girl};
    private String currentUser;

    private SharedPreferences loginData;
    private SharedPreferences currUserData;
    private SharedPreferences pointsData;
    private SharedPreferences timeData;
    private SharedPreferences rankingData;
    private SharedPreferences lastGameData;
    private SharedPreferences languageData;
    private SharedPreferences themeData;
    private SharedPreferences characterData;

    private Context context;

    public DataWriter(Context context) {
        this.context = context;

        loginData = context.getSharedPreferences(context.getString(R.string.preference_file_login),
                Context.MODE_PRIVATE);
        currUserData = context.getSharedPreferences(context.getString(R.string.preference_file_user),
                Context.MODE_PRIVATE);
        pointsData = context.getSharedPreferences(context.getString(R.string.preference_file_points),
                Context.MODE_PRIVATE);
        timeData = context.getSharedPreferences(context.getString(R.string.preference_file_time),
                Context.MODE_PRIVATE);
        rankingData = context.getSharedPreferences(context.getString(R.string.preference_file_ranking),
                Context.MODE_PRIVATE);
        lastGameData = context.getSharedPreferences(context.getString(R.string.preference_file_lastgame),
                Context.MODE_PRIVATE);
        languageData = context.getSharedPreferences(context.getString(R.string.preference_file_language),
                Context.MODE_PRIVATE);
        themeData = context.getSharedPreferences(context.getString(R.string.preference_file_theme),
                Context.MODE_PRIVATE);
        characterData = context.getSharedPreferences(context.getString(R.string.preference_file_character),
                Context.MODE_PRIVATE);

        currentUser = currUserData.getString(context.getString(R.string.preference_file_user),
                "Not found");
    }

    /**
     * Method to create a new account for a new user
     *
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

        // Lastly add the user's default last game played
        editor = lastGameData.edit();
        editor.putString(username, "Not Found");
        editor.apply();

        editor = languageData.edit();
        editor.putString(username, "english");
        editor.apply();

        editor = themeData.edit();
        editor.putString(username, "autumn");
        editor.apply();

        editor = characterData.edit();
        editor.putInt(username, characterImageIds[0]);
        editor.apply();
    }

    public int getCharacterData() {
        return characterData.getInt(currentUser, characterImageIds[0]);
    }

    public void setCharacterData(int characterImageId) {
        SharedPreferences.Editor editor = characterData.edit();
        editor.putInt(currentUser, characterImageId);
        editor.apply();
    }

    public void setThemeData(String theme) {
        SharedPreferences.Editor editor = themeData.edit();
        editor.putString(currentUser, theme);
        editor.apply();
    }

    public String getThemeData() {
        return themeData.getString(currentUser, "Not Found");
    }

    @Override
    public String getPassword() {
        return loginData.getString(currentUser, "Not Found");
    }

    @Override
    public int getPoints() {
        return pointsData.getInt(currentUser, -1);
    }

    @Override
    public void setCurrentUser(String username) {
        SharedPreferences.Editor editor = currUserData.edit();
        editor.putString(context.getString(R.string.preference_file_user), username);
        editor.apply();
        currentUser = username;
    }

    @Override
    public String getCurrentUser() {
        return currentUser;
    }

    @Override
    public int getPlayTime() {
        return timeData.getInt(currentUser, -1);
    }

    @Override
    public String getRanking() {
        return rankingData.getString(currentUser, "Not Found");
    }

    /**
     * Add points the user has obtained in a game
     *
     * @param points the amount of points to give to the user
     */
    @Override
    public void addPoints(int points) {
        int currentPoints = getPoints();
        SharedPreferences.Editor editor = pointsData.edit();
        editor.putInt(currentUser, points + currentPoints);
        editor.apply();
    }

    @Override
    public String getLanguage() {
        return languageData.getString(currentUser, "Not Found");
    }

    @Override
    public void setLanguage(String newLanguage) {
        SharedPreferences.Editor editor = languageData.edit();
        editor.putString(currentUser, newLanguage);
        editor.apply();
    }

    /**
     * Add the amount of time this player has played in a game
     *
     * @param playTime the amount of time played
     */
    @Override
    public void addPlayTime(int playTime) {
        SharedPreferences.Editor editor = timeData.edit();
        editor.putInt(currentUser, playTime + getPlayTime());
        editor.apply();
    }

    /**
     * Updates the user's ranking at the end of a game if it achieved something difficult
     * (ex/ solve every level very quickly)
     */
    @Override
    public void increaseRanking() {
        String currentRanking = getRanking();
        String newRanking;

        // only increase if not the highest ranking
        for (int i = 0; i < rankings.length - 1; i++)
            if (currentRanking.equals(rankings[i])) {
                newRanking = rankings[i + 1];
                SharedPreferences.Editor editor = rankingData.edit();
                editor.putString(currentUser, newRanking);
                editor.apply();
                break;
            }
    }

    /**
     * Adds the name of the last game the user had played
     *
     * @param lastGame the game that was last played by the user
     */
    @Override
    public void addLastGame(String lastGame) {
        SharedPreferences.Editor editor = lastGameData.edit();
        editor.putString(currentUser, lastGame);
        editor.apply();
    }

    /**
     * Gets the name of the game the user had last played
     *
     * @return the name of the game
     */
    @Override
    public String getLastGame() {
        return lastGameData.getString(currentUser, "Not Found");
    }

    /**
     * Check if an account with the given username exists in the raw2
     *
     * @return a boolean representing if the username was found or not
     */
    @Override
    public boolean checkUser(String username) {
        return !(loginData.getString(username, "Not Found").equals("Not Found"));
    }

    /**
     * Return a map containing user names and their corresponding point values
     *
     * @return a Map storing points data
     */
    public Map<String, Integer> getPointsData() {

        Map<String, ?> data = pointsData.getAll();

        Map<String, Integer> returnData = new HashMap<>();

        for (String key : data.keySet()) {
            returnData.put(key, pointsData.getInt(key, -1));
        }
        return returnData;
    }

    /**
     * Return a map containing user names and their corresponding play time
     *
     * @return a Map storing play time data
     */
    public Map<String, Integer> getPlayTimeData() {

        Map<String, ?> data = timeData.getAll();

        Map<String, Integer> returnData = new HashMap<>();

        for (String key : data.keySet()) {
            returnData.put(key, timeData.getInt(key, -1));
        }

        return returnData;

    }

    /**
     * Return a map containing user names and their corresponding ranking
     *
     * @return a Map storing ranking of users
     */
    public Map<String, String> getRankingData() {

        Map<String, ?> data = rankingData.getAll();

        Map<String, String> returnData = new HashMap<>();

        for (String key : data.keySet()) {
            returnData.put(key, rankingData.getString(key, ""));
        }

        return returnData;

    }
}
