package com.team5.cowboy_brothers;
       
public class Lvl1 extends Map {
    Enemy enemy1, enemy2;
    
    public Lvl1() {
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
        //var g = new Ground((GROUND_TILE_SIZE*1*10), (GROUND_TILE_SIZE*12),10, t);
        for (int i = 0; i < 20; i++)
        {
            //holes
            if (i == 2 || i == 8 || i == 15 || i == 16 || i == 17) {continue;}
            
            //build the base for the map
            new Ground((GROUND_TILE_SIZE*i*10), (GROUND_TILE_SIZE*14),10, t);
        }
        //fill in the holes so they are jumpable
        new Ground(GROUND_TILE_SIZE*20, (GROUND_TILE_SIZE*14),5, t);
        new Ground(GROUND_TILE_SIZE*25, (GROUND_TILE_SIZE*14),3, t);
        
        new Ground((GROUND_TILE_SIZE*80), (GROUND_TILE_SIZE*14),5, t);
        new Ground((GROUND_TILE_SIZE*86), (GROUND_TILE_SIZE*13),3, t);
        new Ground((GROUND_TILE_SIZE*87), (int)(GROUND_TILE_SIZE*12.4),1, t);
        
        new Ground((GROUND_TILE_SIZE*149), (int)(GROUND_TILE_SIZE*13.4),5, t);
        new Ground((GROUND_TILE_SIZE*153), (int)(GROUND_TILE_SIZE*12.8),5, t);
        new Ground((GROUND_TILE_SIZE*157), (int)(GROUND_TILE_SIZE*12.2),5, t);
        new Ground((GROUND_TILE_SIZE*164), (int)(GROUND_TILE_SIZE*12.2),3, t);
        new Ground((GROUND_TILE_SIZE*168), (int)(GROUND_TILE_SIZE*12.2),1, t);
        new Ground((GROUND_TILE_SIZE*171), (int)(GROUND_TILE_SIZE*12.2),1, t);
        new Ground((GROUND_TILE_SIZE*174), (int)(GROUND_TILE_SIZE*12.2),5, t);
        new Ground((GROUND_TILE_SIZE*178), (int)(GROUND_TILE_SIZE*11.6),1, t);
        
        
        //new Ground((GROUND_TILE_SIZE*100), (int)(GROUND_TILE_SIZE*12.4),1, t);
        new Flag((GROUND_TILE_SIZE*(20*10-1)),(GROUND_TILE_SIZE*14-148), t);


    }
    private void InitializeEnemies(){
            //make the level's enemies
            int TileSize=33;
            GamePanel t = Cowboy_brothers.olly.VisibleMenu.gameplayPanel;
            int[][] path = {{TileSize*10*18,TileSize*14-84},{TileSize*10*19,TileSize*14-84}};
            int[][] path2 = {{1000,TileSize*14-84},{1500,TileSize*14-84}};
            enemy1=new Enemy("Kyle",t);
            enemy2=new Enemy("Sam",t);
            enemy1.setPath(path);
            enemy2.setPath(path2);

        }
    @Override
    public void loadMapStats() {
        // Load level 1 map statistics here
        System.out.println("Loading Level 1 map stats...");
        
    }
}
