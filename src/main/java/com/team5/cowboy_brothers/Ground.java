
package com.team5.cowboy_brothers;

import java.awt.image.BufferedImage;
import javax.swing.Timer;

public class Ground extends GameObject {
    // Additional properties specific to the ground, if needed
    private int x; // X position
    private int y; // Y position
    private final int width = 33; // Width of the object
    private final int height = 33; // Height of the object
    private BufferedImage sprite;
    private GamePanel targetPanel;
    public int tilesize;
    Timer repaintTimer;
    // Constructor
    public Ground(int x, int y, int tilesize, GamePanel targetPanel) {
        
        super(x, y, "sprites/DesertGroundTile.png", targetPanel);
        String path = "";
        this.tilesize=tilesize;
        switch(tilesize){
            case 1:
                path = "sprites/DesertGroundTile.png";
                break;
            case 3:
                path = "sprites/Ground3x1.png";
                break;
            case 5:
                path = "sprites/Ground5x1.png";
                break;
            case 10:
                path = "sprites/Ground10x1.png";
                break;
            default:
                throw new IllegalArgumentException("Invalid tilesize value: " + tilesize);
        }
        this.loadSprite(path);
        //System.out.println("Ground created");
        targetPanel.addGround(this);
    }
    

 
}

