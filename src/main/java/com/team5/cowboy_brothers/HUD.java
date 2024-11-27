package com.team5.cowboy_brothers;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class HUD {
    private Player player; // Reference to the player
    private int levelNumber;
    //private int timer; // Timer in seconds

    // Constructor
    public HUD(Player player, int levelNumber) {
        this.player = player;
        this.levelNumber = levelNumber;
       // this.timer = 0; // Initialize timer
    }

    // Method to update the timer
   // public void updateTimer() {
     //   timer++;
    //}

    // Method to draw the HUD
    public void draw(Graphics g) {
        g.setColor(Color.YELLOW);
        g.setFont(new Font("Arial", Font.BOLD, 20));

        // Display health
        g.drawString("Health: " + player.getCurrentHealth(), 10, 20);
        // Display ammo
        g.drawString("Ammo: " + player.getCurrentAmmo(), 10, 50);
        // Display timer
        //g.drawString("Time: " + timer + "s", 10, 80);
        // Display level number
        g.drawString("Level: " + levelNumber, 10, 110);
    }

    // Getters and Setters for level number, if needed
    public void setLevelNumber(int levelNumber) {
        this.levelNumber = levelNumber;
    }
}