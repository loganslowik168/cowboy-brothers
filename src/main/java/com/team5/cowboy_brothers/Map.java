package com.team5.cowboy_brothers;

public abstract class Map {
    protected int width;
    protected int height;
    protected Player player;

    public Map(int width, int height) {
        this.width = width;
        this.height = height;
    }

    // Abstract method for loading map stats
    public abstract void loadMapStats();

    // Method to set the player
    public void setPlayer(Player player) {
        this.player = player;
    }

}
