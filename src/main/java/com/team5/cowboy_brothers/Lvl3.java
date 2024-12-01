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
       //Creates Finish Flag
        new Flag(3800,170, t);
        
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
        new Ground(GROUND_TILE_SIZE * 87, (int)(GROUND_TILE_SIZE * 6.4), 1, t);
        new Ground(GROUND_TILE_SIZE * 90, (int)(GROUND_TILE_SIZE * 6.4), 5, t);
        new Ground(GROUND_TILE_SIZE * 96, (int)(GROUND_TILE_SIZE * 5.7), 5, t);
        new Ground(GROUND_TILE_SIZE * 102, (int)(GROUND_TILE_SIZE * 5.7), 5, t);
        new Ground(GROUND_TILE_SIZE * 108, (int)(GROUND_TILE_SIZE * 5.0), 3, t);
        new Ground(GROUND_TILE_SIZE * 111, (int)(GROUND_TILE_SIZE * 5.0), 1, t);
        
        //End Barrier wall
        new Ground(GROUND_TILE_SIZE * 101, (int)(GROUND_TILE_SIZE * 14.8), 3, t);
	new Ground(GROUND_TILE_SIZE * 101, (int)(GROUND_TILE_SIZE * 14.2), 3, t);
	new Ground(GROUND_TILE_SIZE * 101, (int)(GROUND_TILE_SIZE * 13.6), 3, t);
	new Ground(GROUND_TILE_SIZE * 101, (int)(GROUND_TILE_SIZE * 13), 3, t);
	new Ground(GROUND_TILE_SIZE * 101, (int)(GROUND_TILE_SIZE * 12.6), 3, t);
        // Lower Path (Easier)
        new Ground(GROUND_TILE_SIZE * 62, (int)(GROUND_TILE_SIZE * 14.2), 3, t);
        new Ground(GROUND_TILE_SIZE * 67, (int)(GROUND_TILE_SIZE * 13.2), 5, t);
        new Ground(GROUND_TILE_SIZE * 74, (int)(GROUND_TILE_SIZE * 13.2), 5, t);
        new Ground(GROUND_TILE_SIZE * 81, (int)(GROUND_TILE_SIZE * 14.8), 10, t);
        new Ground(GROUND_TILE_SIZE * 84, (int)(GROUND_TILE_SIZE * 14.2), 1, t);
        new Ground(GROUND_TILE_SIZE * 84, (int)(GROUND_TILE_SIZE * 13.8), 1, t);
        new Ground(GROUND_TILE_SIZE * 88, (int)(GROUND_TILE_SIZE * 14.2), 1, t);
        new Ground(GROUND_TILE_SIZE * 92, (int)(GROUND_TILE_SIZE * 13.8), 1, t);
        new Ground(GROUND_TILE_SIZE * 95, (int)(GROUND_TILE_SIZE * 13.2), 3, t);
        new Ground(GROUND_TILE_SIZE * 99, (int)(GROUND_TILE_SIZE * 12.6), 1, t);
        new Ground(GROUND_TILE_SIZE * 101, (int)(GROUND_TILE_SIZE * 12.0), 3, t);
        new Ground(GROUND_TILE_SIZE * 102, (int)(GROUND_TILE_SIZE * 11.4), 1, t);
        new Ground(GROUND_TILE_SIZE * 103, (int)(GROUND_TILE_SIZE * 11.4), 1, t);
        new Ground(GROUND_TILE_SIZE * 103, (int)(GROUND_TILE_SIZE * 10.8), 1, t);
    
        new Ground(GROUND_TILE_SIZE * 105, (int)(GROUND_TILE_SIZE * 13.4), 5, t);
        new Ground(GROUND_TILE_SIZE * 106, (int)(GROUND_TILE_SIZE * 12.8), 3, t);
        new Ground(GROUND_TILE_SIZE * 107, (int)(GROUND_TILE_SIZE * 12.2), 1, t);
        // path to flag
        new Ground(GROUND_TILE_SIZE * 109, (int)(GROUND_TILE_SIZE * 11.1), 1, t);
        new Ground(GROUND_TILE_SIZE * 111, (int)(GROUND_TILE_SIZE * 10.1), 1, t);
         new Ground(GROUND_TILE_SIZE * 113, (int)(GROUND_TILE_SIZE * 14.8), 5, t);
	new Ground(GROUND_TILE_SIZE * 113, (int)(GROUND_TILE_SIZE * 14.2), 5, t);
	new Ground(GROUND_TILE_SIZE * 113, (int)(GROUND_TILE_SIZE * 13.6), 5, t);
	new Ground(GROUND_TILE_SIZE * 113, (int)(GROUND_TILE_SIZE * 13), 5, t);
	new Ground(GROUND_TILE_SIZE * 113, (int)(GROUND_TILE_SIZE * 12.4), 5, t);
	new Ground(GROUND_TILE_SIZE * 113, (int)(GROUND_TILE_SIZE * 11.8), 5, t);
	new Ground(GROUND_TILE_SIZE * 113, (int)(GROUND_TILE_SIZE * 11.2), 5, t);
	new Ground(GROUND_TILE_SIZE * 113, (int)(GROUND_TILE_SIZE * 10.6), 5, t);
	new Ground(GROUND_TILE_SIZE * 113, (int)(GROUND_TILE_SIZE * 10), 5, t);
	new Ground(GROUND_TILE_SIZE * 113, (int)(GROUND_TILE_SIZE * 9.4), 5, t);
        new Ground(GROUND_TILE_SIZE * 113, (int)(GROUND_TILE_SIZE * 9.4), 5, t);
        
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

