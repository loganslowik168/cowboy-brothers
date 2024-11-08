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

/**
 *
 * @author matth
 */
public class EnemyBullet {
    private int x, y;           // Position of the bullet
    private int speed;          // Speed of the bullet
    private int direction;      // Direction in degrees
    private int targetX, targetY; // Player's position (target)

    public EnemyBullet(int startX, int startY, int playerX, int playerY, int speed) {
        this.x = startX;
        this.y = startY;
        this.speed = speed;
        
        // Calculate direction towards player
        calculateDirection(playerX, playerY);
    }

    private void calculateDirection(int playerX, int playerY) {
        // Calculate angle between enemy and player
        double angle = Math.atan2(playerY - y, playerX - x);
        this.direction = (int) Math.toDegrees(angle);
    }

    public void update() {
        // Update bullet position based on direction and speed
        x += speed * Math.cos(Math.toRadians(direction));
        y += speed * Math.sin(Math.toRadians(direction));
    }

    public boolean isOffScreen(int screenWidth, int screenHeight) {
        return (x < 0 || x > screenWidth || y < 0 || y > screenHeight);
    }

    // Getters
    public int getX() { return x; }
    public int getY() { return y; }
}
