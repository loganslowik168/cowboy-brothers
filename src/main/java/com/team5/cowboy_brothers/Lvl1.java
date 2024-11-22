package com.team5.cowboy_brothers;

public class Lvl1 extends Map {
    
    public Lvl1() {
        super(800, 600); // Example dimensions
        loadMapStats();
        initializeGround();
        
    }
private void initializeGround() {
        // example lvl 1{if (olly == null) olly = new GameMaster();}
        Ground ground1 = new Ground(0, 400, 800, 50, "sprites/DesertGroundTile.png", Cowboy_brothers.olly.VisibleMenu.gameplayPanel);
        Ground ground2 = new Ground(0, 300, 800, 50, "sprites/DesertGroundTile.png", Cowboy_brothers.olly.VisibleMenu.gameplayPanel);
        Ground ground3 = new Ground(0, 200, 800, 50, "sprites/DesertGroundTile.png", Cowboy_brothers.olly.VisibleMenu.gameplayPanel);
        
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
