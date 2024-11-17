package com.team5.cowboy_brothers;

import java.io.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.util.Timer;
import java.util.TimerTask;
import java.util.ArrayList;
import java.util.List;

public class Player implements Serializable {
    private static final int NUM_OF_LEVELS = 5;
    private double x, y; // Player's position
    private int currentHealth;
    private int currentAmmo;
    private int maxUnlockedLevel;
    private int currentScore;
    private int[] highScores;
    private BufferedImage sprite; // BufferedImage for sprite
    private static final int MOVE_SPEED = 1;
    private static final int JUMP_HEIGHT = 2;
    private static final int MAX_AMMO = 6;
    private static final int MAX_HEALTH = 3;
    private List<PlayerBullet> bullets = new ArrayList<>();
    private int direction; // Player's direction
    private int bulletSpeed = 10; // Speed of the bullets
    private int screenWidth = 800; // Example screen width
    private int screenHeight = 600; // Example screen height
    private Timer positionTimer; // Timer for sending position messages
    private JPanel targetPanel; // Panel to draw the player on

    // Constructor: Takes targetPanel as a parameter
    public Player(int currentHealth, int currentAmmo, int maxUnlockedLevel, int currentScore, int[] highScores, int startX, int startY, JPanel targetPanel) {
        this.currentHealth = currentHealth;
        this.currentAmmo = currentAmmo;
        this.maxUnlockedLevel = maxUnlockedLevel;
        this.currentScore = currentScore;
        this.highScores = new int[NUM_OF_LEVELS];
        this.x = startX;
        this.y = startY;
        this.targetPanel = targetPanel;

        // Initialize high scores
        if (highScores != null && highScores.length == NUM_OF_LEVELS) {
            System.arraycopy(highScores, 0, this.highScores, 0, NUM_OF_LEVELS);
        }

        // Load the sprite
        System.out.println("LOADING SPRITE");
        loadSprite("sprites/player.png");

        // Start the timer to send position messages
        startPositionTimer();

        // Set up a timer to repaint the panel regularly
        setupRepaintTimer();
    }

    // Method to load the sprite
    private void loadSprite(String filePath) {
        try {
            sprite = ImageIO.read(new File(filePath));
            System.out.println("Sprite loaded successfully.");
        } catch (IOException e) {
            System.err.println("Error loading sprite: " + e.getMessage());
        }
    }

    // Method to start a timer that sends position messages every second
    private void startPositionTimer() {
        positionTimer = new Timer();
        positionTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                System.out.println("Player @ pos " + getPositionString());
            }
        }, 0, 1000); // Initial delay of 0 ms, repeat every 1000 ms (1 second)
    }

    // Helper method to get the player's position as a string
    private String getPositionString() {
        return "(" + x + ", " + y + ")";
    }

    // Getter for the sprite
    public BufferedImage getSprite() {
        return sprite;
    }

    // Setter to update player's position
    public void setPosition(double newX, double newY) {
        this.x = newX;
        this.y = newY;
        System.out.println("Player position updated to: (" + x + ", " + y + ")");
    }

    // Getter for maxUnlockedLevel
    public int getMaxUnlockedLevel() {
        return maxUnlockedLevel;
    }

    // Setter for maxUnlockedLevel (if you need to modify it)
    public void setMaxUnlockedLevel(int newMaxLevel) {
        this.maxUnlockedLevel = newMaxLevel;
        System.out.println("Max Unlocked Level updated to: " + maxUnlockedLevel);
    }

    // Setup a timer to trigger repaint of the targetPanel at ~60 FPS
    private void setupRepaintTimer() {
        Timer repaintTimer = new Timer();
        repaintTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                targetPanel.repaint(); // Repaint the panel regularly
                //System.out.println("CALLING REPAINT");
                
                Cowboy_brothers.olly.VisibleMenu.gameplayPanel.repaint();
            }
        }, 0, 1000 / 60); // ~60 FPS
    }

    // Method to handle the rendering of the player on the panel
    public void draw(Graphics2D g2) {
        if (sprite != null) {
            g2.drawImage(sprite, (int) x, (int) y, targetPanel);
            System.out.println("Drawing player sprite at position: (" + x + ", " + y + ")");
        } else {
            System.err.println("Sprite is not loaded.");
        }
    }

}
