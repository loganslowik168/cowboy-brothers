package com.team5.cowboy_brothers;

public class Lvl1 extends Map {
    Enemy enemy1;
    
    public Lvl1() {
        super(800, 600); // Example dimensions
        loadMapStats();
    }

    @Override
    public void loadMapStats() {
        // Load level 1 map statistics here
        // This might involve setting up terrain, enemies, etc.
        System.out.println("Loading Level 1 map stats...");
        
    }

    @Override
    public double[] getStartingPosition() {
        return new double[] {100, 100}; // Example starting position
    }
}
