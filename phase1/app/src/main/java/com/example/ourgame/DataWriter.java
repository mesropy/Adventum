package com.example.ourgame;

public class DataWriter implements WriteData {


    @Override
    public void addUser(String username, String password) {

    }

    @Override
    public String getPassword(String username) {
        return null;
    }

    @Override
    public int[] getReactionStats(String username) {
        return new int[0];
    }

    @Override
    public int[] getTileStats(String username) {
        return new int[0];
    }

    @Override
    public int[] getPictureStats(String username) {
        return new int[0];
    }

    @Override
    public void setReactionStats(int[] stats) {

    }

    @Override
    public void setTileStats(int[] stats) {

    }

    @Override
    public void setPictureStats(int[] stats) {

    }

    @Override
    public String getLastGame(String username) {
        return null;
    }

    @Override
    public boolean checkUser(String username) {
        return false;
    }
}
