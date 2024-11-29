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

public class Player extends Rectangle implements Serializable {
    private static final int NUM_OF_LEVELS = 5;
    private int x, y; // Player's position
    private int currentHealth;
    private int currentAmmo;
    private int maxUnlockedLevel;
    private int currentScore;
    private int[] highScores;
    private BufferedImage sprite; // BufferedImage for sprite
    private final int MOVE_SPEED = 20;
    public final int JUMP_HEIGHT = 20;
    private final int MAX_AMMO = 6;
    private final int MAX_HEALTH = 3;
    private final int GRAVITY = 2;
    public boolean ShouldGravitate = true; //see Cowboy_brothers.java

    private List<Bullet> bullets = new ArrayList<>();
    private int direction; // Player's direction
    private int bulletSpeed = 10; // Speed of the bullets
    private int screenWidth = 800; // Example screen width
    private int screenHeight = 600; // Example screen height
    private Timer gravityTimer; // Timer for sending position messages
    private GamePanel targetPanel; // Panel to draw the player on

    // Constructor: Takes targetPanel as a parameter
    public Player(int currentHealth, int currentAmmo, int maxUnlockedLevel, int currentScore, int[] highScores, int startX, int startY, GamePanel targetPanel) {
        this.currentHealth = currentHealth;
        this.currentAmmo = currentAmmo;
        this.maxUnlockedLevel = maxUnlockedLevel; 
        this.currentScore = currentScore;
        this.highScores = new int[NUM_OF_LEVELS];
        this.x = startX;
        this.y = startY;
        this.targetPanel = targetPanel;
        direction=1;

        // Initialize high scores
        if (highScores != null && highScores.length == NUM_OF_LEVELS) {
            System.arraycopy(highScores, 0, this.highScores, 0, NUM_OF_LEVELS);
        }

        // Load the sprite
        System.out.println("LOADING SPRITE");
        loadSprite("sprites/player.png");

        // Start the timer to send position messages
        SetupGravityTimer();

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
    private void SetupGravityTimer() {
        gravityTimer = new Timer();
        gravityTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (ShouldGravitate) ApplyGravity();
            }
        }, 0, 1000 / 60); // ~60 FPS
    }

    
    public void ChangeY(int dy) {y+=dy;}

    // Getter for the sprite
    public BufferedImage getSprite() {
        return sprite;
    }


    public void setPosition(int newX, int newY) {
        x = newX;
        y = newY;
    }
    public void update(){
        x += MOVE_SPEED*direction;
    }
    public void changeDirection(int direction) {
        this.direction = direction; // 1 for right, -1 for left
        this.width = Math.abs(this.width) * direction; // Change width to negative for left direction
        targetPanel.repaint();
    }

    // Example method to reset player position
    public void resetPosition(int startX, int startY) {
        this.x = startX;
        this.y = startY;
        this.currentHealth = MAX_HEALTH; // Reset health
        this.currentAmmo = MAX_AMMO; // Reset ammo
    }

    // Update bullets
    public void fireBullet()  {
        if(currentAmmo>0){

            PlayerBullet bullet = new PlayerBullet(x, y, direction, bulletSpeed, Cowboy_brothers.olly.VisibleMenu.gameplayPanel);
            targetPanel.AddPlayerBullet(bullet);

            currentAmmo--;
        }else{
            System.out.println("Out of Ammo");
        }
    }

    public void updateBullets() {
        // Update each bullet
        for (int i = bullets.size() - 1; i >= 0; i--) {
            Bullet bullet = bullets.get(i);
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
    
    public void setBulletCountToFull(){
        currentAmmo=6;
    }
    
    public int getDirection(){
        //get direction
        return direction;
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
        }, 100, 1000 / 60); // ~60 FPS
    }

    // Method to handle the rendering of the player on the panel
    public void draw(Graphics2D g2) {
        if (sprite != null) {
            g2.drawImage(sprite, (int) x, (int) y, targetPanel);
            //System.out.println("Drawing player sprite at position: (" + x + ", " + y + ")");
        } else {
            System.err.println("Sprite is not loaded.");
        }
    }
    // Method to check collision with another object
            public boolean collidesWith(Rectangle other) {
                return this.intersects(other);
            }
            public boolean collidesWithFlag(Flag flag) {
                return this.intersects(flag);
            }
    public int getCurrentHealth() {
                return currentHealth;  // Return current health
            }
    public int GetMoveSpeed() {return MOVE_SPEED;}
    private void ApplyGravity()
    {
        y+=GRAVITY;

    }
    public int getCurrentAmmo() {
        return currentAmmo;
    }
}
