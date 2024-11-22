
package com.team5.cowboy_brothers;

import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;


/**
 *
 * @author matth
 */
public abstract class Bullet{
    private int x, y; // Position of the bullet
    private int speed; // Speed of the bullet
    private int direction; 
    private BufferedImage sprite;
    private GamePanel targetPanelBullet;
    Timer repaintTimer;
    Timer updateTimer=new Timer(1000/60,null);
    

    public Bullet(int startX, int startY, int direction, int speed,GamePanel TPB) {
        this.x = startX;
        this.y = startY;
        this.direction = direction;
        this.speed = speed;
        //travelBullet();
        targetPanelBullet = TPB;
        setupRepaintTimer();
        repaintTimer.start();
        updateTimer.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                update();
            }
        });
        updateTimer.start();
        
    }

    public void update() {
       // Update the bullet's position based on its speed and direction
       x += speed * direction; // Move left or right
    }

    public void draw(Graphics g) {
        g.drawImage(sprite, x, y, null); // Draw the bullet sprite
        //System.out.println("Drawing BUllet");
    }
    
    public void draw(Graphics2D g2) {
        //System.out.println("Drawing Bullet");
        if (sprite != null) {
            g2.drawImage(sprite, (int) x, (int) y, targetPanelBullet);
            //System.out.println("Drawing player sprite at position: (" + x + ", " + y + ")");
        } else {
            System.err.println("Sprite is not loaded.");
        }
    }
    
    private void setupRepaintTimer() {
        repaintTimer = new Timer(1000/60,null);
        repaintTimer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                targetPanelBullet.repaint(); // Repaint the panel regularly
                //System.out.println("CALLING REPAINT");
                
                Cowboy_brothers.olly.VisibleMenu.gameplayPanel.repaint();
            }
        }); // ~60 FPS
        
    }

    public boolean isOffScreen(int screenWidth, int screenHeight) {
        // Check if the bullet is off the screen
        return (x < 0 || x > screenWidth || y < 0 || y > screenHeight);
    }
    
    public void travelBullet(){
        //make timer then call update and then call draw
    }

    // Getters for position
    public int getX() { return x; }
    public int getY() { return y; }
    //Setters
    public void setSprite(BufferedImage x){sprite=x;}
    public void setDirection(int i){direction=i;}
}
