/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.team5.cowboy_brothers;

/**
 *
 * @author matth
 */
public class Lvl2 extends Map {
    Enemy enemy1,enemy2;
    
    
    public Lvl2() {
        super(800, 600); // Example dimensions
        loadMapStats();
        InitializeTerrain();
        InitializeEnemies();
        
    }
private void InitializeTerrain() {
        // example lvl 1{if (olly == null) olly = new GameMaster();}
        GamePanel t = Cowboy_brothers.olly.VisibleMenu.gameplayPanel;
        final int GROUND_TILE_SIZE = 33;
        // draws from first to last
        for (int i = 0; i < 6; i++)
        {
            new Ground((GROUND_TILE_SIZE*i*10), (GROUND_TILE_SIZE*15),10, t);
        }
        for (int i = 12; i < 18; i++)
        {
            new Ground((GROUND_TILE_SIZE*i*10), (GROUND_TILE_SIZE*15),10, t);
        }
        for (int i = 12; i < 16; i++)
        {
            new Ground((GROUND_TILE_SIZE*i*10), (GROUND_TILE_SIZE*10),10, t);
        }
        
        new Ground(GROUND_TILE_SIZE*0, GROUND_TILE_SIZE*0,1, t);
        //steps
        new Ground((GROUND_TILE_SIZE*59), (int)(GROUND_TILE_SIZE*15),5, t);
        new Ground((GROUND_TILE_SIZE*59), (int)(GROUND_TILE_SIZE*14.4),5, t);
        new Ground((GROUND_TILE_SIZE*63), (int)(GROUND_TILE_SIZE*14.4),5, t);
        new Ground((GROUND_TILE_SIZE*63), (int)(GROUND_TILE_SIZE*13.8),5, t);
        new Ground((GROUND_TILE_SIZE*67), (int)(GROUND_TILE_SIZE*13.8),5, t);
        new Ground((GROUND_TILE_SIZE*67), (int)(GROUND_TILE_SIZE*13.2),5, t);
        //jump to a reverse staircase
        new Ground((GROUND_TILE_SIZE*81), (int)(GROUND_TILE_SIZE*15),5, t);
        new Ground((GROUND_TILE_SIZE*77), (int)(GROUND_TILE_SIZE*14.4),5, t);
        new Ground((GROUND_TILE_SIZE*81), (int)(GROUND_TILE_SIZE*14.4),5, t);
        new Ground((GROUND_TILE_SIZE*73), (int)(GROUND_TILE_SIZE*13.8),5, t);
        new Ground((GROUND_TILE_SIZE*77), (int)(GROUND_TILE_SIZE*13.8),5, t);
        new Ground((GROUND_TILE_SIZE*73), (int)(GROUND_TILE_SIZE*13.2),5, t);
        //
        new Ground((GROUND_TILE_SIZE*86), (int)(GROUND_TILE_SIZE*15),5, t);
        new Ground((GROUND_TILE_SIZE*91), (int)(GROUND_TILE_SIZE*15),1, t);
        new Ground((GROUND_TILE_SIZE*92), (int)(GROUND_TILE_SIZE*15),1, t);
        new Ground((GROUND_TILE_SIZE*93), (int)(GROUND_TILE_SIZE*14.4),1, t);
        new Ground((GROUND_TILE_SIZE*94), (int)(GROUND_TILE_SIZE*14.4),1, t);
        new Ground((GROUND_TILE_SIZE*95), (int)(GROUND_TILE_SIZE*13.8),1, t);
        new Ground((GROUND_TILE_SIZE*96), (int)(GROUND_TILE_SIZE*13.8),1, t);
        new Ground((GROUND_TILE_SIZE*97), (int)(GROUND_TILE_SIZE*13.2),1, t);
        
        
       
        
        new Flag(GROUND_TILE_SIZE*179,(GROUND_TILE_SIZE*15-148), t);
}
    public void InitializeEnemies(){
        //make the level's enemies
        GamePanel t = Cowboy_brothers.olly.VisibleMenu.gameplayPanel;
        int[][] path = {{3960,(347)},{5610,347}};
        int[][] path2 = {{100,347},{200,347},{100,200}};
        enemy1=new Enemy("Kyle",t);
        enemy2=new Enemy("Sam",t);
        enemy1.setPath(path);
        enemy2.setPath(path2);
        
    }
    @Override
    public void loadMapStats() {
        // Load level 1 map statistics here
        System.out.println("Loading Level 2 map stats...");
        
    }
    
}
