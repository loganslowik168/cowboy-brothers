package com.team5.cowboy_brothers;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.util.Timer;
import java.util.TimerTask;

public class Player implements Serializable {
    private static final int NUM_OF_LEVELS = 5;
    double[] position = {3.5, 7.2};
    private int x, y; // Player's position
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

    // Constructor
    public Player(int currentHealth, int currentAmmo, int maxUnlockedLevel, int currentScore, int[] highScores,int startX,int startY) {
        this.currentHealth = currentHealth;
        this.currentAmmo = currentAmmo;
        this.maxUnlockedLevel = maxUnlockedLevel;
        this.currentScore = currentScore;
        this.highScores = new int[NUM_OF_LEVELS];

        this.x = startX;
        this.y = startY;


        // Initialize high scores
        if (highScores != null && highScores.length == NUM_OF_LEVELS) {
            System.arraycopy(highScores, 0, this.highScores, 0, NUM_OF_LEVELS);
        }

        // Load the sprite
        System.out.println("LOADING SPRITE");
        loadSprite("sprites/player.png");

        // Start the timer to send position messages
        startPositionTimer();
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
        return "(" + position[0] + ", " + position[1] + ")";
    }

    // Getters
    public double[] getPosition() {
        return position;
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public int getCurrentAmmo() {
        return currentAmmo;
    }

    public int getMaxUnlockedLevel() {
        return maxUnlockedLevel;
    }

    public int getCurrentScore() {
        return currentScore;
    }

    public int[] getHighScores() {
        return highScores.clone(); // Return a copy to protect encapsulation
    }

    public BufferedImage getSprite() {
        return sprite; // Getter for the sprite
    }

    // Setters
    public void setPosition(double x, double y) {
        position[0] = x;
        position[1] = y;
    }

    public void setX(double x) {
        position[0] = x;
    }

    public void setY(double y) {
        position[1] = y;
    }

    public void setCurrentHealth(int currentHealth) {
        if (currentHealth >= 0 && currentHealth <= MAX_HEALTH) {
            this.currentHealth = currentHealth;
        }
    }

    public void setCurrentAmmo(int currentAmmo) {
        if (currentAmmo >= 0 && currentAmmo <= MAX_AMMO) {
            this.currentAmmo = currentAmmo;
        }
    }

    public void setMaxUnlockedLevel(int maxUnlockedLevel) {
        if (maxUnlockedLevel >= 0 && maxUnlockedLevel <= NUM_OF_LEVELS) {
            this.maxUnlockedLevel = maxUnlockedLevel;
        }
    }

    public void setCurrentScore(int currentScore) {
        this.currentScore = currentScore;
    }

    public void setHighScores(int[] highScores) {
        if (highScores != null && highScores.length == NUM_OF_LEVELS) {
            System.arraycopy(highScores, 0, this.highScores, 0, NUM_OF_LEVELS);
        }
    }

    // Serialize the Player object
    public void serialize(String filename) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
            out.writeObject(this);
            System.out.println("Player serialized successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getMoveSpeed() {
        return MOVE_SPEED;
    }

    // Deserialize the Player object
    public static Player deserialize(String filename) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
            Player player = (Player) in.readObject();
            System.out.println("Player deserialized successfully.");
            return player;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void fireBullet() {
        PlayerBullet bullet = new PlayerBullet( x, y, direction, bulletSpeed);
        bullets.add(bullet);
    }

    public void updateBullets() {
        // Update each bullet
        for (int i = bullets.size() - 1; i >= 0; i--) {
            PlayerBullet bullet = bullets.get(i);
            bullet.update();

            // Check if the bullet is off-screen
            if (bullet.isOffScreen(screenWidth, screenHeight)) {
                bullets.remove(i); // Remove bullet if it is off-screen
            }
        }
    }
}
