
package com.team5.cowboy_brothers;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.swing.Timer;

public class Ground extends GameObject {
    // Additional properties specific to the ground, if needed
    private int x; // X position
    private int y; // Y position
    private int width; // Width of the object
    private int height; // Height of the object
    private BufferedImage sprite;
    private GamePanel targetPanel;
    Timer repaintTimer;
    // Constructor
    public Ground(int x, int y, int tilesize, GamePanel targetPanel) {
        
        super(x, y, "sprites/DesertGroundTile.png", targetPanel);
        String path = "";
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


    @Override
    public void draw(Graphics g){super.draw(g);} //System.out.println("Drawing in ground");}
    
    @Override
    public void draw(Graphics2D g2){super.draw(g2);}
    
    
    // Optional: Override the update method if needed
//    @Override
//    public void update() {
//        // Logic to update ground state, if applicable
//    }

    // Optional: Override the render method for drawing the ground
//    @Override
//    public void render() {
//        // Logic to draw the ground using its texture
//        // For example, you might use a graphics context to draw the texture at the ground's position
//    }

}

