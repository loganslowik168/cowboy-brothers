/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.team5.cowboy_brothers;
import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author matth
 */
public class PlayerBullet {
    private int x, y; // Position of the bullet
    private int speed; // Speed of the bullet
    private int direction; // Direction in degrees (0 = right, 90 = down, etc.)
    private BufferedImage sprite;

    public PlayerBullet(int startX, int startY, int direction, int speed) throws IOException {
        this.x = startX;
        this.y = startY;
        this.direction = direction;
        this.speed = speed;
        loadSprite("sprites/PlayerBullet.png");
    }

    private void loadSprite(String filePath) throws IOException {
        sprite = ImageIO.read(new File(filePath));
        System.out.println("Sprite loaded successfully.");
    }
    public void draw(Graphics g) {
        g.drawImage(sprite, x, y, null); // Draw the enemy bullet sprite
    }


    public void update() {
        // Update the bullet's position based on its speed and direction
        x += speed * Math.cos(Math.toRadians(direction));
        y += speed * Math.sin(Math.toRadians(direction));
    }

    public boolean isOffScreen(int screenWidth, int screenHeight) {
        // Check if the bullet is off the screen
        return (x < 0 || x > screenWidth || y < 0 || y > screenHeight);
    }

    // Getters for position
    public int getX() { return x; }
    public int getY() { return y; }
}
