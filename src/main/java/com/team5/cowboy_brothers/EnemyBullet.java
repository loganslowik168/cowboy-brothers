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
public class EnemyBullet extends Bullet {
    
    private int targetX, targetY; // Player's position (target)
    private BufferedImage sprite;

    public EnemyBullet(int startX, int startY, int playerX, int playerY, int speed) {
        super(startX,startY,0,speed);
        loadSprite("sprites/EnemyBullet.png");
        // Calculate direction towards player
        //calculateDirection(playerX, playerY);
        //Super setDirection()
    }

   /* private void calculateDirection(int playerX, int playerY) {
        // Calculate angle between enemy and player
        double angle = Math.atan2(playerY - y, playerX - x);
        this.direction = (int) Math.toDegrees(angle);
    }*/

    private void loadSprite(String filePath) {
        try {
            sprite = ImageIO.read(new File(filePath));
            System.out.println("Sprite loaded successfully.");
        } catch (IOException e) {
            System.err.println("Error loading sprite: " + e.getMessage());
        }
    }
    public void collision(){
        //check collision with the ground and the player
        
    }


    

    

    // Getters
    
}
