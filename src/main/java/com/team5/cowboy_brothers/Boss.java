package com.team5.cowboy_brothers;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.Timer;

public class Boss{
    
    private int x, y; // Player's position
    private int direction = -1;
    private final int MAX_HEALTH = 10;
    public int currentHealth;
    private int FALL_DISTANCE = 30;
    private BufferedImage sprite, spriteL, spriteR;
    private GamePanel targetPanel;
    Timer updateTimer, bombTimer, fallTimer;
    protected int height, width;
    private boolean isPaused = false;
    public Boss(int x, int y, GamePanel t)     
    {
        currentHealth = MAX_HEALTH;
        this.x = x;
        this.y = y;
        width = 44;
        height = 74;
        targetPanel = t;
        loadSprites("sprites/bossLeft.png", "sprites/bossRight.png");
        sprite=spriteL;
        targetPanel.SetBoss(this);
        Cowboy_brothers.olly.gameWorld.boss = this;
        //timers
        updateTimer = new Timer(1000/60,null);
        updateTimer.addActionListener(new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e){
            UpdateFacing();
            targetPanel.revalidate();
        }
        });
        updateTimer.start();
        
        bombTimer = new Timer(1000, null);
        bombTimer.addActionListener(new ActionListener() { 
            @Override
            public void actionPerformed(ActionEvent e){
                ThrowBomb();
            }
        });
        bombTimer.start();
        
        fallTimer = new Timer(250, null);
        fallTimer.addActionListener(new ActionListener() { 
            @Override
            public void actionPerformed(ActionEvent e){
                Fall();
            }
        });
        
    }
    public int GetX() {return x;}
    public int GetY() {return y;}
    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
    
    public void Hurt(int h)
    {
        currentHealth-=h;
        if (currentHealth <= 0)
        {
            Die();
        }
    }
    public void Die()
    {
        //needs a dispose
        dispose();
        Cowboy_brothers.olly.VisibleMenu.winMenu();
    }
    private void dispose(){
        if(targetPanel!=null)targetPanel=null;
        if(sprite!=null)sprite=null;
        if(updateTimer!=null){
            updateTimer.removeActionListener(updateTimer.getActionListeners()[0]);
            updateTimer.stop();
            updateTimer=null;
        }
        if(bombTimer!=null){
            bombTimer.removeActionListener(bombTimer.getActionListeners()[0]);
            bombTimer.stop();
            bombTimer=null;
        }
        if(fallTimer!=null){
            fallTimer.removeActionListener(fallTimer.getActionListeners()[0]);
            fallTimer.stop();
            fallTimer=null;
        }
    }
    private void UpdateFacing()
    {
        int oldDir = direction;
        //int i1 = Cowboy_brothers.olly.player.GetX();//-Cowboy_brothers.olly.gameWorld.totalOffset;
        //System.out.println(i1 + " vs " + x); //This statement is not needed for now
        if (Cowboy_brothers.olly.player.GetX() > x) {direction = 1;}
        else {direction = -1;}
        CheckForDirectionChange(oldDir);
    }
    public void CheckForDirectionChange(int dir) {
        //System.out.println(direction + " vs " + dir); //This statement is not needed for now
        if (direction != dir)
        {
            //System.out.println("Boss face left = " + (sprite == spriteL)); //This statement is not needed for now
            switch (direction) {
                case -1 -> 
                {
                    sprite = spriteL;
                }
                case 1 -> 
                {
                    sprite = spriteR;
                }
                default -> throw new IllegalArgumentException("Dirction can only be 1 or -1");
            }
        }
        //targetPanel.repaint();
    }
    public void Dispose() {
        // Stop any timers related to the boss
        if (updateTimer != null) {
            updateTimer.stop();
            updateTimer.removeActionListener(updateTimer.getActionListeners()[0]);
            updateTimer = null;
        }
        if (bombTimer != null) {
            bombTimer.stop();
            bombTimer.removeActionListener(bombTimer.getActionListeners()[0]);
            bombTimer = null;
        }
        if (fallTimer != null) {
            fallTimer.stop();
            fallTimer.removeActionListener(fallTimer.getActionListeners()[0]);
            fallTimer = null;
        }
        
        // Clear sprite or any other resources if needed
        sprite = null;
        spriteL = null;
        spriteR = null;
        
        if (targetPanel != null ) {targetPanel.clearGameObjects();}
    }
    
    
    // Method to load the sprite
    private void loadSprites(String filePathL, String filePathR) {
        try {
            spriteL = ImageIO.read(new File(filePathL));
            //System.out.println("Left sprite loaded successfully."); //This statement is not needed for now
        } catch (IOException e) {
            System.err.println("Error loading left sprite: " + e.getMessage());
        }
        try {
            spriteR = ImageIO.read(new File(filePathR));
            //System.out.println("Right sprite loaded successfully."); //This statement is not needed for now
        } catch (IOException e) {
            System.err.println("Error loading right sprite: " + e.getMessage());
        }
    }
    
    public void draw(Graphics g) {
        g.drawImage(sprite, x, y, null); // Draw the bullet sprite
        //System.out.println("Drawing GameObject");
    }
    public void setPaused(boolean paused) {
        this.isPaused = paused;
    }
    
    private void ThrowBomb()
    {
        if (isPaused) return;
        //int t = x-Cowboy_brothers.olly.gameWorld.totalOffset;
        //System.out.println("Boss position = " + t);
        Point source = new Point(x,y);
        Point target = new Point(Cowboy_brothers.olly.player.GetX()+5, Cowboy_brothers.olly.player.GetY()+37);
        new Bomb(source, target, targetPanel, 0.02f);
    }
    public void BeginFalling()
    {
        fallTimer.start();
    }
    private void Fall()
    {
        if (y<388-FALL_DISTANCE) {this.y+=FALL_DISTANCE;}
        else {y=388; fallTimer.stop();}
    }
    
    
    public void draw(Graphics2D g2) {
        //System.out.println("Drawing Ground");
        if (sprite != null) {
            g2.drawImage(sprite, (int) x, (int) y, targetPanel);
            System.out.println("Drawing Gameobject @ " + x + ", " + y);
        } else {
            System.err.println("Sprite is not loaded.");
        }
    }
    public void ShiftPosition(int shift) //sidescrolling element
    {
        x=x+shift;
    }
}
