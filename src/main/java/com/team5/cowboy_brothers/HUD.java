package com.team5.cowboy_brothers;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class HUD {
    private Player player; // Reference to the player
    private int levelNumber,bossHealth;
    //private int timer; // Timer in seconds

    // Constructor
    public HUD(Player player, int levelNumber, GamePanel targetPanel) {
        this.player = player;
        this.levelNumber = levelNumber;
        targetPanel.setHUD(this);
       // this.timer = 0; // Initialize timer
    }

    // Method to update the timer
   // public void updateTimer() {
     //   timer++;
    //}

    // Method to draw the HUD
    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.setFont(new Font("Arial", Font.BOLD, 20));

        // Display health
        g.drawString("Health: " + player.getCurrentHealth(), 10, 20);
        g.setColor(Color.GREEN);
        // Display ammo
        g.drawString("Ammo: " + player.getCurrentAmmo(), 10, 50);
        
        // Display level number
        g.drawString("Level: " + levelNumber, 600, 20);
        if(levelNumber==5&&Cowboy_brothers.olly.gameWorld.boss!=null){
            g.setColor(Color.RED);
            g.drawString("Boss Health: "+Cowboy_brothers.olly.gameWorld.boss.currentHealth,300,490);
        }
        
    }

    // Getters and Setters for level number, if needed
    public void setLevelNumber(int levelNumber) {
        this.levelNumber = levelNumber;
    }
}