package com.team5.cowboy_brothers;

import java.io.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.util.Timer;
import java.util.TimerTask;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Player extends Rectangle implements Serializable {
    private static final int NUM_OF_LEVELS = 5;
    private int x, y; // Player's position
    private int currentHealth;
    private int currentAmmo;
    private int maxUnlockedLevel;
    private int currentScore;
    private int[] highScores;
    private BufferedImage sprite, spriteL, spriteR;
    private final int MOVE_SPEED = 20;
    public final int JUMP_HEIGHT = 20;
    private final int MAX_AMMO = 6;
    private final int MAX_HEALTH = 3;
    private final int GRAVITY = 2;
    public boolean ShouldGravitate = true; //see Cowboy_brothers.java

    private List<Bullet> bullets = new ArrayList<>();
    private int direction; // Player's direction
    private int bulletSpeed = 30; // Speed of the bullets
    private int screenWidth = 800; // Example screen width
    private int screenHeight = 600; // Example screen height
    private Timer gravityTimer; // Timer for sending position messages
    private GamePanel targetPanel; // Panel to draw the player on
    private final int height = 74;
    private final int width = 44;
    
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
        loadSprites("sprites/playerLeft.png", "sprites/playerRight.png");
        sprite=spriteL;
        // Start the timer to send position messages
        SetupGravityTimer();

        // Set up a timer to repaint the panel regularly
        setupRepaintTimer();
        
        // Set up a timer to replebnish the player's ammo amount by 1 every 5 seconds
        startAmmoTimer();
    }

    // Method to load the sprite
    private void loadSprites(String filePathL, String filePathR) {
        try {
            spriteL = ImageIO.read(new File(filePathL));
            System.out.println("Left sprite loaded successfully.");
        } catch (IOException e) {
            System.err.println("Error loading left sprite: " + e.getMessage());
        }
        try {
            spriteR = ImageIO.read(new File(filePathR));
            System.out.println("Right sprite loaded successfully.");
        } catch (IOException e) {
            System.err.println("Error loading right sprite: " + e.getMessage());
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
        }, 100, 1000 / 6); // ~60 FPS
    }

    public int GetX() {return x;}
    public int GetY() {return y;}
    public void ChangeY(int dy) {y+=dy;}

    // Getter for the sprite
    public BufferedImage getSprite() {
        return sprite;
    }


    public void setPosition(int newX, int newY) {
        x = newX;
        y = newY;
    }
    
    public void CheckForDirectionChange(int dir) {
        if (direction != dir)
        {
            direction = -direction;
            switch (direction) {
                case -1 -> sprite = spriteR;
                case 1 -> sprite = spriteL;
                default -> throw new IllegalArgumentException("Dirction can only be 1 or -1");
            }
        }
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
 

 // Replenishes 1 ammo every 5 seconds
    private void startAmmoTimer() {
        Timer Ammotimer = new Timer();
        Ammotimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                replenishAmmo();
            }
        }, 0, 5000); // Run task every 5 seconds
    }

    // Function to Replenish ammo if below max ammo amount
    private synchronized void replenishAmmo() {
        if (currentAmmo < MAX_AMMO) {
            currentAmmo++;
            System.out.println("Ammo replenished: " + currentAmmo + "/" + MAX_AMMO);
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
    
    private boolean CheckGroundColission()
    {
        // Use CopyOnWriteArrayList which allows for safe iteration even if modified
        for (Ground g : new CopyOnWriteArrayList<>(targetPanel.grounds)) {
            int pX = x;// + Cowboy_brothers.olly.gameWorld.totalOffset;
            /*Lvl1 l1 = (Lvl1) Cowboy_brothers.olly.LoadedLevel;
            Ground g1 = l1.g;
            System.out.println("gnd @ " + g1.GetX() + "," + g1.GetY() + " p @ " + pX + "," + y);*/
            int GND_WIDTH = 33*g.tilesize;
            int GND_HEIGHT = 33;
            //var bb = new BoundingBox(g.GetX(), g.GetY(), g.GetX()+GND_WIDTH, g.GetY(), g.GetX()+GND_WIDTH,
            //                    g.GetY()+GND_HEIGHT,g.GetX(),g.GetY()+GND_HEIGHT, targetPanel);
            //targetPanel.AddBoundingBox(bb);
            if (pX + this.width > g.GetX() && pX < g.GetX() + GND_WIDTH &&
                        this.y + this.height > g.GetY() && this.y < g.GetY() + GND_HEIGHT) {
                System.out.println("Ground colission!");
                return true;
            }
        }
        
        return false;
    }
    public int getCurrentHealth() {
                return currentHealth;  // Return current health
            }
    public int GetMoveSpeed() {return MOVE_SPEED;}
    private void ApplyGravity()
    {
        if (!CheckGroundColission()) {y+=GRAVITY;}
        

    }
    public int getCurrentAmmo() {
        return currentAmmo;
    }
    public void ClearPlayerBullets()
    {
        bullets.clear();
    }
    public void Hurt(int h)
    {
        //System.out.println("Player took " + h + " damage!");
        currentHealth-=h;
        if (currentHealth <= 0) {Die();}
    }
    private void Die()
    {
        
    }
}
