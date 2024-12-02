package com.team5.cowboy_brothers;


public class Lvl4 extends Map {
    Enemy enemy1;
    Enemy enemy2;
    Enemy enemy3;
    Enemy enemy4;
    public Lvl4() {
        super(800, 600); // Example dimensions
        loadMapStats();
        InitializeTerrain();
        InitializeEnemies();
    }
private void InitializeTerrain() {
        // example lvl 4{if (olly == null) olly = new GameMaster();}
        GamePanel t = Cowboy_brothers.olly.VisibleMenu.gameplayPanel;
        final int GROUND_TILE_SIZE = 33;
       
        // Example terrain for level 4
        for (int i = 0; i < 11; i++)
        {
            //holes
            if (i == 2 || i == 5 ||i == 7 || i == 9 ) {continue;}

            //build the base for the map
            new Ground((GROUND_TILE_SIZE*i*10), (GROUND_TILE_SIZE*14),10, t);
        }

        // Create specific ground tiles for level 4
        //beginning wall
//        new Ground(GROUND_TILE_SIZE * 0, (int)(GROUND_TILE_SIZE * 13.4), 1, t);
//        new Ground(GROUND_TILE_SIZE * 0, (int)(GROUND_TILE_SIZE * 12.8), 1, t);
//        new Ground(GROUND_TILE_SIZE * 0, (int)(GROUND_TILE_SIZE * 12.2), 1, t);
//        new Ground(GROUND_TILE_SIZE * 0, (int)(GROUND_TILE_SIZE * 11.6), 1, t);
//        new Ground(GROUND_TILE_SIZE * 0, GROUND_TILE_SIZE * 11, 1, t);
        //hill
        new Ground(GROUND_TILE_SIZE * 10, (int)(GROUND_TILE_SIZE * 13.4), 5, t);
        new Ground(GROUND_TILE_SIZE * 11, (int)(GROUND_TILE_SIZE * 12.8), 3, t);
        new Ground(GROUND_TILE_SIZE * 12, (int)(GROUND_TILE_SIZE * 12.2), 1, t);
        //first hole
        new Ground(GROUND_TILE_SIZE * 19, (int)(GROUND_TILE_SIZE * 13.4), 1, t);
        new Ground((GROUND_TILE_SIZE* 22), (GROUND_TILE_SIZE*14),1, t);
        new Ground((GROUND_TILE_SIZE* 23), (GROUND_TILE_SIZE*14),1, t);
        new Ground(GROUND_TILE_SIZE * 22, (int)(GROUND_TILE_SIZE * 13.4), 1, t);
        new Ground(GROUND_TILE_SIZE * 23, (int)(GROUND_TILE_SIZE * 13.4), 1, t);
        new Ground(GROUND_TILE_SIZE * 23, (int)(GROUND_TILE_SIZE * 12.8), 1, t);
        new Ground(GROUND_TILE_SIZE * 26, (int)(GROUND_TILE_SIZE * 12.8), 1, t);
        new Ground(GROUND_TILE_SIZE * 27, (int)(GROUND_TILE_SIZE * 12.8), 1, t);
        new Ground(GROUND_TILE_SIZE * 30, (int)(GROUND_TILE_SIZE * 13.4), 1, t);
        new Ground(GROUND_TILE_SIZE * 31, (int)(GROUND_TILE_SIZE * 13.4), 1, t);
        new Ground(GROUND_TILE_SIZE * 30, (int)(GROUND_TILE_SIZE * 12.8), 1, t);
        //"split" 
        //wall
        new Ground(GROUND_TILE_SIZE * 33, (int)(GROUND_TILE_SIZE * 13.4), 1, t);
        new Ground(GROUND_TILE_SIZE * 33, (int)(GROUND_TILE_SIZE * 12.8), 1, t);
        new Ground(GROUND_TILE_SIZE * 33, (int)(GROUND_TILE_SIZE * 12.2), 1, t);
        new Ground(GROUND_TILE_SIZE * 33, (int)(GROUND_TILE_SIZE * 11.6), 1, t);
        new Ground(GROUND_TILE_SIZE * 33, GROUND_TILE_SIZE * 11, 1, t);
        //upper
        new Ground(GROUND_TILE_SIZE * 36, (int)(GROUND_TILE_SIZE * 10.4), 1, t);
        new Ground(GROUND_TILE_SIZE * 38, (int)(GROUND_TILE_SIZE * 9.8), 10, t);
        new Ground(GROUND_TILE_SIZE * 50, (int)(GROUND_TILE_SIZE * 9.2), 1, t);
        new Ground(GROUND_TILE_SIZE * 51, (int)(GROUND_TILE_SIZE * 9.2), 1, t);
        new Ground(GROUND_TILE_SIZE * 51, (int)(GROUND_TILE_SIZE * 8.6), 1, t);
        new Ground(GROUND_TILE_SIZE * 53, (int)(GROUND_TILE_SIZE * 8.6), 5, t);
        new Ground(GROUND_TILE_SIZE * 59, (int)(GROUND_TILE_SIZE * 8.6), 5, t);
        //lower
        new Ground(GROUND_TILE_SIZE * 51, (int)(GROUND_TILE_SIZE * 14), 5, t);
        new Ground(GROUND_TILE_SIZE * 57, (int)(GROUND_TILE_SIZE * 14), 1, t);
        new Ground(GROUND_TILE_SIZE * 58, (int)(GROUND_TILE_SIZE * 14), 1, t);
        //steps
//        new Ground(GROUND_TILE_SIZE * 71, (int)(GROUND_TILE_SIZE * 14), 1, t);
//        new Ground(GROUND_TILE_SIZE * 73, (int)(GROUND_TILE_SIZE * 14), 1, t);
//        new Ground(GROUND_TILE_SIZE * 75, (int)(GROUND_TILE_SIZE * 14), 1, t);
//        new Ground(GROUND_TILE_SIZE * 77, (int)(GROUND_TILE_SIZE * 14), 1, t);
//        new Ground(GROUND_TILE_SIZE * 79, (int)(GROUND_TILE_SIZE * 14), 1, t);
        new Ground(GROUND_TILE_SIZE * 71, (int)(GROUND_TILE_SIZE * 13.4), 1, t);
//        new Ground(GROUND_TILE_SIZE * 73, (int)(GROUND_TILE_SIZE * 13.4), 1, t);
//        new Ground(GROUND_TILE_SIZE * 75, (int)(GROUND_TILE_SIZE * 13.4), 1, t);
//        new Ground(GROUND_TILE_SIZE * 77, (int)(GROUND_TILE_SIZE * 13.4), 1, t);
//        new Ground(GROUND_TILE_SIZE * 79, (int)(GROUND_TILE_SIZE * 13.4), 1, t);
        new Ground(GROUND_TILE_SIZE * 73, (int)(GROUND_TILE_SIZE * 12.8), 1, t);
//        new Ground(GROUND_TILE_SIZE * 75, (int)(GROUND_TILE_SIZE * 12.8), 1, t);
//        new Ground(GROUND_TILE_SIZE * 77, (int)(GROUND_TILE_SIZE * 12.8), 1, t);
//        new Ground(GROUND_TILE_SIZE * 79, (int)(GROUND_TILE_SIZE * 12.8), 1, t);
        new Ground(GROUND_TILE_SIZE * 75, (int)(GROUND_TILE_SIZE * 12.2), 1, t);
//        new Ground(GROUND_TILE_SIZE * 77, (int)(GROUND_TILE_SIZE * 12.2), 1, t);
//        new Ground(GROUND_TILE_SIZE * 79, (int)(GROUND_TILE_SIZE * 12.2), 1, t);
        new Ground(GROUND_TILE_SIZE * 77, (int)(GROUND_TILE_SIZE * 11.6), 1, t);
//        new Ground(GROUND_TILE_SIZE * 79, (int)(GROUND_TILE_SIZE * 11.6), 1, t);
        new Ground(GROUND_TILE_SIZE * 79, (int)(GROUND_TILE_SIZE * 11), 1, t);
        //hill2
        new Ground(GROUND_TILE_SIZE * 84, (int)(GROUND_TILE_SIZE * 13.4), 5, t);
        new Ground(GROUND_TILE_SIZE * 85, (int)(GROUND_TILE_SIZE * 12.8), 3, t);
        new Ground(GROUND_TILE_SIZE * 86, (int)(GROUND_TILE_SIZE * 12.2), 1, t);
        //
        new Ground(GROUND_TILE_SIZE * 89, (int)(GROUND_TILE_SIZE * 10), 10, t);
        new Ground(GROUND_TILE_SIZE * 92, (int)(GROUND_TILE_SIZE * 14), 5, t);
        new Ground(GROUND_TILE_SIZE * 98, (int)(GROUND_TILE_SIZE * 14), 1, t);
        // Adding Flags
        new Flag((GROUND_TILE_SIZE*(11*10-1)),(GROUND_TILE_SIZE*14-148), t);
        
        
    }
public void InitializeEnemies(){
        //make the level's enemies
        int TileSize=33;
        GamePanel t = Cowboy_brothers.olly.VisibleMenu.gameplayPanel;
        int[][] path = {{1100,TileSize*14-84},{1500,TileSize*14-84}};
        int[][] path2 = {{3000,TileSize*14-84},{3500,TileSize*14-84}};
        int[] path3 = {3000,TileSize*10-84};
        int[][] path4 = {{1300,TileSize*10-84},{1500,TileSize*10-84}};
        enemy1=new Enemy("Kylie",t);
        enemy2=new Enemy("Sammy",t);
        enemy3=new Enemy("Sammmy",t);
        enemy4=new Enemy("Sammmmy",t);
        enemy1.setPath(path);
        enemy2.setPath(path2);
        enemy3.setPath(path3);
        enemy4.setPath(path4);
    }
    @Override
    public void loadMapStats() {
        // Load level 4 map statistics here
        System.out.println("Loading Level 4 map stats...");
    }
}
