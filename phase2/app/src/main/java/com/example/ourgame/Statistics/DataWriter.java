package com.example.ourgame.Statistics;

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

    private SharedPreferences loginData;
    private SharedPreferences pointsData;
    private SharedPreferences timeData;
    private SharedPreferences rankingData;
    private SharedPreferences lastGameData;
    private SharedPreferences languageData;
    private SharedPreferences themeData;
    private SharedPreferences characterData;
    private SharedPreferences currUserData;

    private Context context;
    private String currentUsername;

    public DataWriter(Context context){

        loginData = context.getSharedPreferences(context.getString(R.string.preference_file_login), Context.MODE_PRIVATE);
        pointsData = context.getSharedPreferences(context.getString(R.string.preference_file_points), Context.MODE_PRIVATE);
        timeData = context.getSharedPreferences(context.getString(R.string.preference_file_time), Context.MODE_PRIVATE);
        rankingData = context.getSharedPreferences(context.getString(R.string.preference_file_ranking), Context.MODE_PRIVATE);
        lastGameData = context.getSharedPreferences(context.getString(R.string.preference_file_lastgame), Context.MODE_PRIVATE);
        languageData = context.getSharedPreferences(context.getString(R.string.preference_file_language), Context.MODE_PRIVATE);
        currUserData = context.getSharedPreferences(context.getString(R.string.preference_file_user), Context.MODE_PRIVATE);
        themeData = context.getSharedPreferences(context.getString(R.string.preference_file_theme), Context.MODE_PRIVATE);
        characterData = context.getSharedPreferences(context.getString(R.string.preference_file_character), Context.MODE_PRIVATE);

        this.context = context;
        this.currentUsername = getCurrentUser();
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
        editor.putString(username, "male");
        editor.apply();
    }

    @Override
    public String getCharacterData() {
        return characterData.getString(currentUsername, "male");
    }

    @Override
    public void setCharacterData(String character) {
        SharedPreferences.Editor editor = characterData.edit();
        editor.putString(currentUsername, character);
        editor.apply();
    }

    @Override
    public void setThemeData(String theme) {
        SharedPreferences.Editor editor = themeData.edit();
        editor.putString(currentUsername, theme);
        editor.apply();
    }

    @Override
    public String getThemeData() {
        return themeData.getString(currentUsername, "Not Found");
    }

    @Override
    public String getPassword() {
        return loginData.getString(currentUsername, "Not Found");
    }

    @Override
    public int getPoints() {
        return pointsData.getInt(currentUsername, -1);
    }

    @Override
    public void setCurrentUser(String username) {
        SharedPreferences.Editor editor = currUserData.edit();
        editor.putString(context.getString(R.string.preference_file_user), username);
        editor.apply();
        currentUsername = username;
    }

    @Override
    public String getCurrentUser() {
        return currUserData.getString(context.getString(R.string.preference_file_user), "Not found");
    }

    @Override
    public int getPlayTime() {
        return timeData.getInt(currentUsername, -1);
    }

    @Override
    public String getRanking() {
        return rankingData.getString(currentUsername, "Not Found");
    }

    /**
     * Add points the user has obtained in a game
     * @param points the amount of points to give to the user
     */
    @Override
    public void addPoints(int points) {
        int currentPoints = getPoints();
        SharedPreferences.Editor editor = pointsData.edit();
        editor.putInt(currentUsername, points + currentPoints);
        editor.apply();
    }

    @Override
    public String getLanguage() {
        return languageData.getString(currentUsername, "Not Found");
    }

    @Override
    public void setLanguage(String newLanguage) {
        SharedPreferences.Editor editor = languageData.edit();
        editor.putString(currentUsername, newLanguage);
        editor.apply();
    }

    /**
     * Add the amount of time this player has played in a game
     * @param playTime the amount of time played
     */
    @Override
    public void addPlayTime(int playTime) {
        SharedPreferences.Editor editor = timeData.edit();
        editor.putInt(currentUsername, playTime + getPlayTime());
        editor.apply();
    }

    /**
     * Updates the user's ranking at the end of a game if it achieved something difficult
     * (ex/ solve every level very quickly)
     **/
    @Override
    public void increaseRanking() {
        String currentRanking = getRanking();
        String newRanking;

        // TODO: make implementation of this more intuitive

        // only increase if not the highest ranking
        for (int i = 0; i < rankings.length - 1; i++)
            if (currentRanking.equals(rankings[i])) {
                newRanking = rankings[i + 1];
                SharedPreferences.Editor editor = rankingData.edit();
                editor.putString(currentUsername, newRanking);
                editor.apply();
                break;
            }
    }

    /**
     * Adds the name of the last game the user had played
     * @param lastGame the game that was last played by the user
     */
    @Override
    public void addLastGame(String lastGame) {
        SharedPreferences.Editor editor = lastGameData.edit();
        editor.putString(currentUsername, lastGame);
        editor.apply();
    }

    /**
     * Gets the name of the game the user had last played
     * @return the name of the game
     */
    @Override
    public String getLastGame() {
        return lastGameData.getString(currentUsername, "Not Found");
    }

    /**
     * Check if an account with the given username exists in the raw2
     * @return a boolean representing if the username was found or not
     */
    @Override
    public boolean checkUser() {
        return !((loginData.getString(currentUsername, "Not Found")).equals("Not Found"));
    }

    /**
     * Return a map containing user names and their corresponding point values
     * @return a Map storing points data
     */
    public Map<String, Integer> getPointsData(){

        Map<String, ?> data = pointsData.getAll();

        Map<String, Integer> returnData = new HashMap<>();

        for (String key : data.keySet()){
            returnData.put(key, pointsData.getInt(key, -1));
        }
        return returnData;
    }
}
