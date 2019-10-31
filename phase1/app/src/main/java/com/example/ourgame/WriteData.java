package com.example.ourgame;

public interface WriteData {

    // Create a new user with "username" and "password"
    void addUser(String username, String password);

    String getPassword(String username);

    int getPoints(String username);

    int getPlayTime(String username);

    String getRanking(String username);

    void addPoints(String username, int points);

    void addPlayTime(String username, int playTime);

    void addRanking(String username, String ranking);

    String getLastGame(String username);


    // Check if "username" exists
    boolean checkUser(String username);
}
