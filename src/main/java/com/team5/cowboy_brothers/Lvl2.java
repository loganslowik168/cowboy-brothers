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
    Enemy enemy1;
    
    public Lvl2() {
        super(800, 600); // Example dimensions
        loadMapStats();
        InitializeTerrain();
        
    }
private void InitializeTerrain() {
        // example lvl 1{if (olly == null) olly = new GameMaster();}
        GamePanel t = Cowboy_brothers.olly.VisibleMenu.gameplayPanel;
        final int GROUND_TILE_SIZE = 33;
        // draws from first to last
        for (int i = 0; i < 30; i++)
        {
            new Ground((GROUND_TILE_SIZE*i*10), (GROUND_TILE_SIZE*14),10, t);
        }
        
        new Ground(GROUND_TILE_SIZE*0, GROUND_TILE_SIZE*10,1, t);
        // draws from first to last
        new Ground((GROUND_TILE_SIZE*0), (GROUND_TILE_SIZE*10),1, t);
        new Ground((GROUND_TILE_SIZE*0), (int)(GROUND_TILE_SIZE*9.5),1, t);
        new Ground(GROUND_TILE_SIZE*1, GROUND_TILE_SIZE*10,1, t);
        new Ground(GROUND_TILE_SIZE*2, GROUND_TILE_SIZE*10,1, t);
        new Flag(100,100, t);

        

        //enemy1 = new Enemy("Kyle",1,Color.black, Cowboy_brothers.olly.VisibleMenu.gameplayPanel);
        //Cowboy_brothers.olly.VisibleMenu.gameplayPanel.setEnemy(enemy1);

    }
    public void InitializeEnemies(){
        //make the level's enemies
    }
    @Override
    public void loadMapStats() {
        // Load level 1 map statistics here
        System.out.println("Loading Level 1 map stats...");
        
    }
    
}
