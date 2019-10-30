package com.example.ourgame;

public interface WriteData {

    // Create a new user with "username" and "password"
    void addUser(String username, String password);

    String getPassword(String username);

    int[] getReactionStats(String username);

    int[] getTileStats(String username);

    int[] getPictureStats(String username);

    void setReactionStats(int[] stats);

    void setTileStats(int[] stats);

    void setPictureStats(int[] stats);

    String getLastGame(String username);


    // Check if "username" exists
    boolean checkUser(String username);
}
