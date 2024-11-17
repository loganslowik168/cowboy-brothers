package com.team5.cowboy_brothers;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.*;

import com.team5.cowboy_brothers.Cowboy_bros_Menu.GameState;

import java.util.Timer;
import java.util.TimerTask;

public class Player extends Rectangle implements Serializable {
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
    private double width;
    private double height;

        // Constructor
        public Player(int currentHealth, int currentAmmo, int maxUnlockedLevel, int currentScore, int[] highScores, int startX, int startY) {
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
            return "(" + x + ", " + y + ")";
        }
    
        // Getters and Setters
        public double[] getPosition() {
            return new double[]{x, y};
        }
    
        public int getCurrentHealth() {
            return currentHealth;  // Return current health
        }
    
        public BufferedImage getSprite() {
            return sprite; // Getter for the sprite
        }
    
        public void setPosition(double newX, double newY) {
            x = newX;
            y = newY;
        }
    
        // Update bullets
        public void fireBullet() throws IOException {
            PlayerBullet bullet = new PlayerBullet(x, y, direction, bulletSpeed);
    
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
    
        // Serialize and deserialize
        public void serialize(String filename) {
            try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
                out.writeObject(this);
                System.out.println("Player serialized successfully.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    
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
    
        int getMaxUnlockedLevel() {
            return maxUnlockedLevel;
        }
    
        void setMaxUnlockedLevel(int i) {
            maxUnlockedLevel=i;
        }
    
        // Custom JPanel for rendering the player sprite
        public static class PlayerPanel extends JPanel {
            private Player player;
            private Timer repaintTimer; // Timer to trigger repainting at regular intervals
    
            public PlayerPanel(Player player) {
                this.player = player;
                // Set up a timer to repaint the panel every 1000/60 ms (~60 FPS)
                repaintTimer = new Timer();
                repaintTimer.scheduleAtFixedRate(new TimerTask() {
                    @Override
                    public void run() {
                        repaint(); // Repaint the panel regularly
                    }
                }, 0, 1000 / 60); // ~60 FPS
            }
    
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
    
                // If the sprite is loaded, draw it at the player's current position
                if (player.getSprite() != null) {
                    g.drawImage(player.getSprite(), (int) player.x, (int) player.y, this);
                } else {
                    g.setColor(Color.RED);
                    g.drawString("Sprite failed to load", 60, 60);
                }
            }
        }
    
        // Retaining the getMoveSpeed function
        public int getMoveSpeed() {
            return MOVE_SPEED;
        }
        // Method to check collision with another object
        public boolean collidesWith(Rectangle other) {
            return this.intersects(other);
        }
        public boolean collidesWithFlag(Flag flag) {
            return this.intersects(flag);
        }
}
