package com.team5.cowboy_brothers;


public class Lvl5 extends Map {
    
    public Lvl5() {
        super(800, 600); // Example dimensions
        loadMapStats();
        InitializeTerrain();
        
    }
private void InitializeTerrain() {
        // example lvl 1{if (olly == null) olly = new GameMaster();}
        GamePanel t = Cowboy_brothers.olly.VisibleMenu.gameplayPanel;
        final int GROUND_TILE_SIZE = 33;
        // draws from first to last
        for (int i = -2; i < 7; i++)
        {
            //build the base for the map
            new Ground((GROUND_TILE_SIZE*i*10), (GROUND_TILE_SIZE*14),10, t);
        }
        Boss B = new Boss((GROUND_TILE_SIZE*3*10+(578/2)-(44/2)),(GROUND_TILE_SIZE*14-261-74),t);
        new BossSaloon((GROUND_TILE_SIZE*3*10),(GROUND_TILE_SIZE*14)-261,t, B);
        
        
    }

    @Override
    public void loadMapStats() {
        // Load level 1 map statistics here
        System.out.println("Loading Level 5 map stats...");
        
    }
}