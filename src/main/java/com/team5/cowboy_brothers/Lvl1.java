package com.team5.cowboy_brothers;

public class Lvl1 extends Map {
    
    public Lvl1() {
        super(800, 600); // Example dimensions
        loadMapStats();
        InitializeTerrain();
        
    }
private void InitializeTerrain() {
        // example lvl 1{if (olly == null) olly = new GameMaster();}
        GamePanel t = Cowboy_brothers.olly.VisibleMenu.gameplayPanel;
        final int GROUND_TILE_SIZE = 33;
        new Ground(GROUND_TILE_SIZE*0, GROUND_TILE_SIZE*10, t);
        new Ground(GROUND_TILE_SIZE*1, GROUND_TILE_SIZE*10, t);
        new Ground(GROUND_TILE_SIZE*2, GROUND_TILE_SIZE*10, t);
        new Flag(100,100, t);
        
    }
    @Override
    public void loadMapStats() {
        // Load level 1 map statistics here
        System.out.println("Loading Level 1 map stats...");
        
    }
}
