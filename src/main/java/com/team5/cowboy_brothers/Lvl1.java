package com.team5.cowboy_brothers;

public class Lvl1 extends Map {
    
    public Lvl1() {
        super(800, 600); // Example dimensions
        loadMapStats();
        initializeGround();
        
    }
private void initializeGround() {
        // example lvl 1{if (olly == null) olly = new GameMaster();}
        GamePanel t = Cowboy_brothers.olly.VisibleMenu.gameplayPanel;
        new Ground(0, 400, t);
        new Ground(33, 400, t);
        new Ground(66, 400, t);
        new Flag(100,100, t);
        
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
