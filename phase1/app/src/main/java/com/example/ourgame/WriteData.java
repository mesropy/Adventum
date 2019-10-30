package com.example.ourgame;

interface WriteData {

    // Create a new user with "username" and "password"
    void addUser(String username, String password);

    // Check if "username" exists
    boolean checkUser(String username);

    String getPassword(String username);

    String getLastGame(String username);


    // stats for each user: points, play time, ranking?

    int getPoints(String username);

    int getPlayTime(String username);

    String getRanking(String username); // based on points and playtime

    void addPoints(String username, int points);

    void addPlayTime(String username, double playtime);
}
