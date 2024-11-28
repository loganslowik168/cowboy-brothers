package com.team5.cowboy_brothers;
import javax.swing.*;
import java.util.*;
import java.awt.*;
       
public class Lvl1 extends Map {
    Enemy enemy1;
    
    public Lvl1() {
        super(800, 600); // Example dimensions
        loadMapStats();
        InitializeTerrain();
        
    }
private void InitializeTerrain() {
        // example lvl 1{if (olly == null) olly = new GameMaster();}
        GamePanel t = Cowboy_brothers.olly.VisibleMenu.gameplayPanel;
        final int GROUND_TILE_SIZE = 33;
        // draws from first to last
        for (int i = 0; i < 100; i++)
        {
            new Ground((GROUND_TILE_SIZE*i), (GROUND_TILE_SIZE*14), t);
        }
        
        new Ground(GROUND_TILE_SIZE*0, GROUND_TILE_SIZE*10, t);
        // draws from first to last
        new Ground((GROUND_TILE_SIZE*0), (GROUND_TILE_SIZE*10), t);
        new Ground((GROUND_TILE_SIZE*0), (int)(GROUND_TILE_SIZE*9.5), t);
        new Ground(GROUND_TILE_SIZE*1, GROUND_TILE_SIZE*10, t);
        new Ground(GROUND_TILE_SIZE*2, GROUND_TILE_SIZE*10, t);
        new Flag(100,100, t);
        new Ground((int)(GROUND_TILE_SIZE*3.1), GROUND_TILE_SIZE*10, t);
        new Flag(1000,100, t);

        
        new Flag(1000,100, t);

        //enemy1 = new Enemy("Kyle",1,Color.black, Cowboy_brothers.olly.VisibleMenu.gameplayPanel);
        //Cowboy_brothers.olly.VisibleMenu.gameplayPanel.setEnemy(enemy1);

    }
    @Override
    public void loadMapStats() {
        // Load level 1 map statistics here
        System.out.println("Loading Level 1 map stats...");
        
    }
}
