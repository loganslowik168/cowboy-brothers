package com.team5.cowboy_brothers;

public class Lvl1 extends Map {
    
    public Lvl1() {
        super(800, 600); // Example dimensions
        loadMapStats();
    }

    @Override
    public void loadMapStats() {
        // Load level 1 map statistics here
        // This might involve setting up terrain, enemies, etc.
        System.out.println("Loading Level 1 map stats...");
        int[] zeros = new int[5];
        Player p = new Player(1,6,1,0,zeros);
        GameWorld GW = new GameWorld();
        KeyHandler KH = new KeyHandler(p, GW);
        p.setPosition(40,40);
        System.out.println("p+gw=kh");
    }

    @Override
    public double[] getStartingPosition() {
        return new double[] {100, 100}; // Example starting position
    }
}
