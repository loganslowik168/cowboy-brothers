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
    private final int MAX_HEALTH = 5;
    private int currentHealth;
    private int FALL_DISTANCE = 30;
    private BufferedImage sprite, spriteL, spriteR;
    private GamePanel targetPanel;
    Timer updateTimer, bombTimer, fallTimer;
    
    public Boss(int x, int y, GamePanel t)
    {
        this.x = x;
        this.y = y;
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
    private void UpdateFacing()
    {
        int oldDir = direction;
        //System.out.println(Cowboy_brothers.olly.player.GetX()+Cowboy_brothers.olly.gameWorld.totalOffset + " vs " + x);
        if (Cowboy_brothers.olly.player.GetX()+Cowboy_brothers.olly.gameWorld.totalOffset > x) {direction = 1;}
        else {direction = -1;}
        CheckForDirectionChange(oldDir);
    }
    public void CheckForDirectionChange(int dir) {
        //the boss is not correctly facing the right direction but at this point i just dont have time to fix it
        //i know the issue lies with the supertype GameObject overwriting its sprite with the given one in the super constructor
        
        //System.out.println(direction + " vs " + dir);
        if (direction != dir)
        {
            System.out.println("Boss face left = " + (sprite == spriteL));
            switch (direction) {
                case -1 -> 
                {
                    sprite = spriteR;
                }
                case 1 -> 
                {
                    sprite = spriteL;
                }
                default -> throw new IllegalArgumentException("Dirction can only be 1 or -1");
            }
        }
        //targetPanel.repaint();
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
    
    public void draw(Graphics g) {
        g.drawImage(sprite, x, y, null); // Draw the bullet sprite
        //System.out.println("Drawing GameObject");
    }
    
    
    private void ThrowBomb()
    {
        int t = x-Cowboy_brothers.olly.gameWorld.totalOffset;
        //System.out.println("Boss position = " + t);
        Point source = new Point(x,y);
        Point target = new Point(Cowboy_brothers.olly.player.GetX()+5, Cowboy_brothers.olly.player.GetY()+37);
        new Bomb(source, target, targetPanel, 0.05f);
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
