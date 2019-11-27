package com.example.ourgame.Statistics;

public interface WriteData {

    // Create a new user with "username" and "password"
    void addUser(String username, String password);

    // Return the password belonging to the given username
    String getPassword();

    // Return the points of the given user
    int getPoints();

    void setCurrentUser(String username);

    String getCurrentUser();

    // Return the total playing time across all 3 games for this user.
    int getPlayTime();

    // Return the rank of the given user
    String getRanking();

    // Give this user some points
    void addPoints(int points);

    // Add on to this user's total time
    void addPlayTime(int playTime);

    void increaseRanking();

    void addLastGame(String lastGame);

    String getLastGame();

    String getLanguage();

    void setLanguage(String language);

    // Check if "username" exists
    boolean checkUser();

    String getThemeData();

    String getCharacterData();
    void setThemeData(String theme);
    void setCharacterData(String character);


}
