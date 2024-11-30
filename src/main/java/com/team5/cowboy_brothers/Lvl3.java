package com.team5.cowboy_brothers;


public class Lvl3 extends Map {
      Enemy enemy1;
    
    public Lvl3() {
        super(800,600);
        loadMapStats();
        InitializeTerrain();
        
    }
private void InitializeTerrain() {
        // example lvl 1{if (olly == null) olly = new GameMaster();}
        GamePanel t = Cowboy_brothers.olly.VisibleMenu.gameplayPanel;
        final int GROUND_TILE_SIZE = 33;
        // draws from first to last
        /*for (int i = 0; i < 30; i++)
        {
            new Ground((GROUND_TILE_SIZE*i*10), (GROUND_TILE_SIZE*14),10, t);
        }*/
       
        
      //beginning cliff
        new Ground(GROUND_TILE_SIZE * 0, (int)(GROUND_TILE_SIZE * 14.8), 5, t);
	new Ground(GROUND_TILE_SIZE * 0, (int)(GROUND_TILE_SIZE * 14.2), 5, t);
	new Ground(GROUND_TILE_SIZE * 0, (int)(GROUND_TILE_SIZE * 13.6), 5, t);
	new Ground(GROUND_TILE_SIZE * 0, (int)(GROUND_TILE_SIZE * 13), 5, t);
	new Ground(GROUND_TILE_SIZE * 0, (int)(GROUND_TILE_SIZE * 12.4), 5, t);
	new Ground(GROUND_TILE_SIZE * 0, (int)(GROUND_TILE_SIZE * 11.8), 5, t);
	new Ground(GROUND_TILE_SIZE * 0, (int)(GROUND_TILE_SIZE * 11.2), 5, t);
	new Ground(GROUND_TILE_SIZE * 0, (int)(GROUND_TILE_SIZE * 10.6), 5, t);
	new Ground(GROUND_TILE_SIZE * 0, (int)(GROUND_TILE_SIZE * 10), 5, t);
	new Ground(GROUND_TILE_SIZE * 0, (int)(GROUND_TILE_SIZE * 9.4), 5, t);
        new Ground(GROUND_TILE_SIZE * 0, (int)(GROUND_TILE_SIZE * 8.8), 5, t);
        new Ground(GROUND_TILE_SIZE * 0, (int)(GROUND_TILE_SIZE * 8.2), 5, t);
        new Ground(GROUND_TILE_SIZE * 0, (int)(GROUND_TILE_SIZE * 7.6), 5, t);
        new Ground(GROUND_TILE_SIZE * 0, (int)(GROUND_TILE_SIZE * 7.0), 5, t);
        
        //platforms
        new Ground(GROUND_TILE_SIZE * 8, (int)(GROUND_TILE_SIZE * 10), 5, t);
        new Ground(GROUND_TILE_SIZE * 15, (int)(GROUND_TILE_SIZE * 13), 5, t);
        
       //Ground Level
        new Ground(GROUND_TILE_SIZE * 22, (int)(GROUND_TILE_SIZE * 14.8), 1, t);
        new Ground(GROUND_TILE_SIZE * 24, (int)(GROUND_TILE_SIZE * 14.8), 10, t);
        new Ground(GROUND_TILE_SIZE * 36, (int)(GROUND_TILE_SIZE * 14.8), 5, t);
       //height increase platform
        new Ground(GROUND_TILE_SIZE * 42, (int)(GROUND_TILE_SIZE * 14.8), 5, t);
	new Ground(GROUND_TILE_SIZE * 42, (int)(GROUND_TILE_SIZE * 14.2), 5, t);
       
        //3 steps
	new Ground(GROUND_TILE_SIZE * 49, (int)(GROUND_TILE_SIZE * 13.4), 3, t);
	new Ground(GROUND_TILE_SIZE * 54, (int)(GROUND_TILE_SIZE * 12.5), 3, t);
        new Ground(GROUND_TILE_SIZE * 59, (int)(GROUND_TILE_SIZE * 11.6), 3, t);
        
        //Upper Path (Harder)
        new Ground(GROUND_TILE_SIZE * 64, (int)(GROUND_TILE_SIZE * 10.8), 1, t);
        new Ground(GROUND_TILE_SIZE * 67, (int)(GROUND_TILE_SIZE * 9.9), 1, t);
        new Ground(GROUND_TILE_SIZE * 70, (int)(GROUND_TILE_SIZE * 8.7), 1, t);
        new Ground(GROUND_TILE_SIZE * 73, (int)(GROUND_TILE_SIZE * 7.7), 1, t);
        new Ground(GROUND_TILE_SIZE * 76, (int)(GROUND_TILE_SIZE * 7.2), 3, t);
        new Ground(GROUND_TILE_SIZE * 81, (int)(GROUND_TILE_SIZE * 7.2), 1, t);
        new Ground(GROUND_TILE_SIZE * 84, (int)(GROUND_TILE_SIZE * 6.4), 3, t);
        
        // Lower Path (Easier)
        new Ground(GROUND_TILE_SIZE * 62, (int)(GROUND_TILE_SIZE * 14.2), 3, t);
        new Ground(GROUND_TILE_SIZE * 67, (int)(GROUND_TILE_SIZE * 13.2), 5, t);
        new Ground(GROUND_TILE_SIZE * 74, (int)(GROUND_TILE_SIZE * 13.2), 5, t);
        new Ground(GROUND_TILE_SIZE * 81, (int)(GROUND_TILE_SIZE * 14.8), 10, t);
        new Ground(GROUND_TILE_SIZE * 84, (int)(GROUND_TILE_SIZE * 14.2), 1, t);
        new Ground(GROUND_TILE_SIZE * 84, (int)(GROUND_TILE_SIZE * 13.8), 1, t);
        new Ground(GROUND_TILE_SIZE * 88, (int)(GROUND_TILE_SIZE * 14.2), 1, t);
        new Ground(GROUND_TILE_SIZE * 88, (int)(GROUND_TILE_SIZE * 13.8), 1, t);
//Finish Flag
        new Flag(2000,100, t);
        
           /* enemy1 = new Enemy("Kyle", Cowboy_brothers.olly.VisibleMenu.gameplayPanel);
        int[][] path1={{100,100},{200,200}};
        enemy1.setPath(path1);
        Cowboy_brothers.olly.VisibleMenu.gameplayPanel.setEnemy(enemy1);*/

    }
    public void loadMapStats() {
        // Load level 1 map statistics here
        System.out.println("Loading Level 3 map stats...");
        
    }
}

