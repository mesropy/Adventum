package com.example.ourgame;

public class DataWriter implements WriteData {

    // TODO: implement all these methods (write to/ read from file)

    @Override
    public void addUser(String username, String password) {

    }

    @Override
    public String getLastGame(String username) {
        return null;
    }

    @Override
    public boolean checkUser(String username) {
        return false;
    }

    @Override
    public String getPassword(String username) {
        return null;
    }

    @Override
    public int getPoints(String username) {
        return 0;
    }

    @Override
    public int getPlayTime(String username) {
        return 0;
    }

    @Override
    public String getRanking(String username) {
        return null;
    }

    @Override
    public void addPoints(String username, int points) {

    }

    @Override
    public void addPlayTime(String username, double playtime) {

    }
}
