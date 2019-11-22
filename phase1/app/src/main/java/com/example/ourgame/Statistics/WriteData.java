package com.example.ourgame.Statistics;

public interface WriteData {

    // Create a new user with "username" and "password"
    void addUser(String username, String password);

    void setUser(String username);

    String getUser();

    // Return the password belonging to the given username
    String getPassword(String username);

    // Return the points of the given user
    int getPoints(String username);

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

    // Check if "username" exists
    boolean checkUser(String username);
}
