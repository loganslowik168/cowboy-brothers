
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
public class Bullet{
    private int x, y; // Position of the bullet
    private int speed; // Speed of the bullet
    private int direction; 
    private BufferedImage sprite;

    public Bullet(int startX, int startY, int direction, int speed) {
        this.x = startX;
        this.y = startY;
        this.direction = direction;
        this.speed = speed;
        
    }

    public void update() {
       // Update the bullet's position based on its speed and direction
       x += speed * direction; // Move left or right
    }

    public void draw(Graphics g) {
        g.drawImage(sprite, x, y, null); // Draw the bullet sprite
    }

    public boolean isOffScreen(int screenWidth, int screenHeight) {
        // Check if the bullet is off the screen
        return (x < 0 || x > screenWidth || y < 0 || y > screenHeight);
    }

    // Getters for position
    public int getX() { return x; }
    public int getY() { return y; }
    //Setters
    public void setSprite(BufferedImage x){sprite=x;}
    public void setDirection(int i){direction=i;}
}
