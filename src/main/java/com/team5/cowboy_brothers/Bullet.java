
package com.team5.cowboy_brothers;

import javax.swing.Timer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;


public abstract class Bullet extends MoveableGameObject{
    //protected int x, y; // Position of the bullet
    private int speed; // Speed of the bullet
    private int direction; 
    private BufferedImage sprite, spriteR, spriteL;
    private GamePanel targetPanelBullet;
    protected Timer updateTimer=new Timer(1000/60,null);;
    

    public Bullet(int startX, int startY, int direction, int speed,GamePanel TPB, String spritePathL, String spritePathR, int width, int height) { 
        this.x = startX;
        this.y = startY;
        this.direction = direction;
        this.speed = speed;
        this.width = width;
        this.height = height;
        //travelBullet();
        targetPanelBullet = TPB;
        
        setupUpdateTimer();
        updateTimer.start();
        loadSprites(spritePathL, spritePathR);
        switch (direction) {
            case 1 -> sprite=spriteR;
            case -1 -> sprite=spriteL;
            default -> throw new IllegalArgumentException("Bullet irection must be 1 or -1");
        }
        //System.out.println("Bullet size = " + this.width + "x" + this.height); //This statement is not needed for now
        
        //Cowboy_brothers.olly.gameWorld.moveableObjects.add(this);
    }
    // Method to load the sprite
    private void loadSprites(String filePathL, String filePathR) {
        try {
            spriteL = ImageIO.read(new File(filePathL));
        //    System.out.println("Left sprite loaded successfully."); //This statement is not needed for now
        } catch (IOException e) {
            System.err.println("Error loading left sprite: " + e.getMessage());
        }
        try {
            spriteR = ImageIO.read(new File(filePathR));
          //  System.out.println("Right sprite loaded successfully."); //This statement is not needed for now
        } catch (IOException e) {
            System.err.println("Error loading right sprite: " + e.getMessage());
        }
    }
    @Override
    public void update() {
       // Update the bullet's position based on its speed and direction
       int travelOffset = speed * direction; // Move left or right
       x += travelOffset;
    }

    public void draw(Graphics g) {
        g.drawImage(sprite, x, y, null); // Draw the bullet sprite
        //System.out.println("Drawing BUllet");
    }
    
    public void draw(Graphics2D g2) {
        if (sprite != null) {
            g2.drawImage(sprite, (int) x, (int) y, targetPanelBullet);
            //System.out.println("Drawing player sprite at position: (" + x + ", " + y + ")");
        } else {
            //System.err.println("Sprite is not loaded.");
        }
    }
    
    protected void setupUpdateTimer(){
        updateTimer.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                update();
                CheckCollision();
                if(targetPanelBullet!=null){
                if(isOffScreen(targetPanelBullet.getWidth(),targetPanelBullet.getHeight()))
                    targetPanelBullet.alterList();
                }
                //System.out.println(x+", "+y);
            }
        });
    }


    public boolean isOffScreen(int screenWidth, int screenHeight) {
        // Check if the bullet is off the screen
        //System.out.println("check Onscreen");
        return (x < 0 || x > screenWidth || y < 0 || y > screenHeight);
        
    }
    
    public boolean checkDeleteBullet(){
        //Check if it is onscreen or colliding with anything
        if(targetPanelBullet!=null){
        return (isOffScreen(targetPanelBullet.getWidth(),targetPanelBullet.getHeight())|| CheckCollision());
        }
        return true;
    }
    public boolean collision(){
        return false;
    }
    
    
    public void pauseTimers(){
        if(updateTimer!=null)
        updateTimer.stop();
    }
    
    public void unPauseTimers(){
        if(updateTimer!=null)
        updateTimer.start();
    }
    // Getters for position
    public int getX() { return x; }
    public int getY() { return y; }
    //Setters
    public void setSprite(BufferedImage x){sprite=x;}
    public void setDirection(int i){direction=i;}
    
    @Override
    public void Dispose()
    {
        if(sprite!=null)sprite = null;
        if(targetPanelBullet!=null)targetPanelBullet = null;
        if(updateTimer!=null){
        updateTimer.removeActionListener(updateTimer.getActionListeners()[0]);
        updateTimer.stop();
        updateTimer = null;}
        
        
    }
    
    protected abstract boolean CheckCollision();
}
