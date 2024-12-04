package com.team5.cowboy_brothers;

import java.io.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.util.Timer;
import java.util.TimerTask;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Player extends Rectangle implements Serializable {
    private static final int NUM_OF_LEVELS = 5;
    private int x, y; // Player's position
    int currentHealth;
    private int currentAmmo;
    private int maxUnlockedLevel;
    private int currentScore;
    private int[] highScores;
    private BufferedImage sprite, spriteL, spriteR;
    private final int MOVE_SPEED = 15;
    public final int JUMP_HEIGHT = 20;
    private final int MAX_AMMO = 6;
    final int MAX_HEALTH = 3;
    private final int GRAVITY = 6;
    private final int INVERSE_GRAVITY = GRAVITY*3;
    public boolean ShouldGravitate = true; //see Cowboy_brothers.java
    private static final int DEATH_LIMIT = 600; // Y-coordinate limit for falling off the map
    private List<Bullet> bullets = new ArrayList<>();
    private int direction; // Player's direction
    private int bulletSpeed = 10; // Speed of the bullets
    private int screenWidth = 800; // Example screen width
    private int screenHeight = 600; // Example screen height
    private Timer gravityAndCollisionTimer; // Timer for sending position messages
    private GamePanel targetPanel; // Panel to draw the player on
    private final int height = 74;
    private final int width = 44;
    
    //private int DEBUGgroundCollisionCounter = 0;
    
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
      //  System.out.println("LOADING SPRITE"); //This statement is not needed for now
        loadSprites("sprites/playerLeft.png", "sprites/playerRight.png");
        sprite=spriteL;
        // Start the timer to send position messages
        SetupGravityTimer();

        // Set up a timer to repaint the panel regularly
        setupRepaintTimer();
        
        // Set up a timer to replebnish the player's ammo amount by 1 every 5 seconds
        startAmmoTimer();
        
        //Timer function to check every 3 seconds if player fell off screen and makes them lose level if they did
        startOffScreenTimer();
        
    }

    // Method to load the sprite
    private void loadSprites(String filePathL, String filePathR) {
        try {
            spriteL = ImageIO.read(new File(filePathL));
       //     System.out.println("Left sprite loaded successfully."); //This statement is not needed for now
        } catch (IOException e) {
            System.err.println("Error loading left sprite: " + e.getMessage());
        }
        try {
            spriteR = ImageIO.read(new File(filePathR));
        //    System.out.println("Right sprite loaded successfully."); //This statement is not needed for now
        } catch (IOException e) {
            System.err.println("Error loading right sprite: " + e.getMessage());
        }
    }

    // Method to start a timer that sends position messages every second
    private void SetupGravityTimer() {
        gravityAndCollisionTimer = new Timer();
        gravityAndCollisionTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (ShouldGravitate) ApplyGravity();
                CheckFlagCollision();
            }
        }, 100, 1000 / 60); // ~60 FPS
    }
    //stop gravity for set time and jump a certain distance
    public void AttemptJump(){
        //setup a timer to do a jump motion then to restart gravity
        if(ShouldGravitate&&!isFalling){
        jump();
        SetGravity(false);
        }
    }
    public void SetGravity(boolean g)
    {
        ShouldGravitate = g;
    }
    int jumpDistanceTraveled=0;
    public void jump(){
        Timer jumpTimer = new Timer();
        jumpTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                y-=1;
                if(jumpDistanceTraveled==50){
                    SetGravity(true);
                    jumpDistanceTraveled=0;
                    jumpTimer.cancel();
                }
                jumpDistanceTraveled++;
            }
        }, 0, 1000/60);

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
        if(targetPanel!=null){targetPanel.repaint();}
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
            int bOffset = 0;
            if (direction == 1) {bOffset = this.width;}
            PlayerBullet bullet = new PlayerBullet(x+bOffset, y+10, direction, bulletSpeed, Cowboy_brothers.olly.VisibleMenu.gameplayPanel, 12, 8);
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
                if(targetPanel!=null){targetPanel.repaint();} // Repaint the panel regularly
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
            //System.err.println("Sprite is not loaded.");
        }
    }
    // Method to check collision with another object
    private boolean isFalling;
    private boolean CheckGroundCollision()
    {
        // Use CopyOnWriteArrayList which allows for safe iteration even if modified
        if(targetPanel!=null){
        for (Ground g : new CopyOnWriteArrayList<>(targetPanel.grounds)) {
            int pX = x;
            //System.out.println("gnd @ " + g1.GetX() + "," + g1.GetY() + " p @ " + pX + "," + y);
            int GND_WIDTH = 33*g.tilesize;
            int GND_HEIGHT = 33;
            if (pX + this.width > g.GetXOffset() && pX < g.GetXOffset() + GND_WIDTH && 
                        this.y + this.height > g.GetY() && this.y +(((this.height*0.9))) < g.GetY() + GND_HEIGHT) {
                
                
                isFalling = false;
                if (CheckGroundCollisionSpecial(0,0)) {y-=INVERSE_GRAVITY; isFalling = true;}
                return true;
            }
        }
        }
        isFalling = true;
        return false;
    }
    
    public boolean CheckGroundCollisionSpecial(int exOffset, int xOffset)
    {
        // Use CopyOnWriteArrayList which allows for safe iteration even if modified
        for (Ground g : new CopyOnWriteArrayList<>(targetPanel.grounds)) {
            int pX = x;
            final int trueOffset = exOffset-10;
            //System.out.println("gnd @ " + g1.GetX() + "," + g1.GetY() + " p @ " + pX + "," + y);
            final int GND_WIDTH = 33*g.tilesize;
            final int GND_HEIGHT = 33;
            if (pX + this.width+trueOffset+xOffset > g.GetXOffset() && pX-trueOffset+xOffset < g.GetXOffset() + GND_WIDTH &&
                        this.y + this.height+trueOffset > g.GetY() && this.y-trueOffset < g.GetY() + GND_HEIGHT) {

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
        if (!CheckGroundCollision()) {y+=GRAVITY;}
        //else {System.out.println("Ground collision (" + DEBUGgroundCollisionCounter + ")"); DEBUGgroundCollisionCounter++;}

        

    }
    
    public int getCurrentAmmo() {
        return currentAmmo;
    }
    public void ClearPlayerBullets()
    {
        bullets.clear();
    }
    public void setHealthToFull(){
        currentHealth=3;
    }
    public void Hurt(int h)
    {
        //System.out.println("Player took " + h + " damage!");
        currentHealth-=h;
        if (currentHealth <= 0) {Die();}
    }
    public int getMaxHealth() {
        return MAX_HEALTH;
    }
    private void Die()
    {
        //change screen to lose screen
       System.out.print("You died to bullets :( "); 
        if(Cowboy_brothers.olly.gameWorld.boss!=null){Cowboy_brothers.olly.gameWorld.boss.Die();}
        Cowboy_brothers.olly.VisibleMenu.loseMenu();

    }
    // Function to see if player is offscreen and makes them lose level if they are
   public boolean isPlayerOffScreen() {
    if (y > DEATH_LIMIT && targetPanel.hasFocus()) {
        System.out.println("Player fell to death!");

         Cowboy_brothers.olly.VisibleMenu.loseMenu();
        return true; // Player is off-screen
    }
    return false; // Player is still on screen
}
   // Timer function to check every 3 seconds if player fell off screen
    private void startOffScreenTimer() {
        Timer OffScreenTimer = new Timer();
        OffScreenTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                isPlayerOffScreen();
            }
        }, 0, 250); // Run task every 5 seconds
    }
    

    private boolean CheckFlagCollision()
    {
        // Use CopyOnWriteArrayList which allows for safe iteration even if modified
        if(targetPanel.flag != null){
        Flag f = targetPanel.flag;
            if (x + this.width > f.GetXOffset() && x < f.GetXOffset() + f.width && 
                        this.y + this.height > f.GetY() && this.y +(((this.height*0.9))) < f.GetY() + f.height) {
                Cowboy_brothers.olly.VisibleMenu.winMenu();
                targetPanel.flag = null;
                if (Cowboy_brothers.olly.Selectedlvl == maxUnlockedLevel)
                {
                    maxUnlockedLevel++;
                }
                
                return true;
            }
        }
        return false;
    }
}
