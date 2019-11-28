package com.example.ourgame.Utilities;

public interface WriteData {

    // Create a new user with "username" and "password"
    void addUser(String username, String password);

    // Return the password belonging to the given username
    String getPassword(String username);

    // Return the points of the given user
    int getPoints(String username);

    void setUser(String username);

    String getUser();

    // Return the total playing time across all 3 games for this user.
    int getPlayTime(String username);

    // Return the rank of the given user
    String getRanking(String username);

    // Give this user some points
    void addPoints(String username, int points);

    // Add on to this user's total time
    void addPlayTime(String username, int playTime);

    void increaseRanking(String username);

    void addLastGame(String username, String lastgame);

    String getLastGame(String username);

    String getLanguage(String username);

    void setLanguage(String username, String language);

    // Check if "username" exists
    boolean checkUser(String username);

    String getThemeData(String username);

    String getCharacterData(String username);
    void setThemeData(String username, String theme);
    void setCharacterData(String username, String character);


}
