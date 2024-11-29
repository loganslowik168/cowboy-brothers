package com.team5.cowboy_brothers;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.Timer;

public class Boss extends GameObject{
    
    private int x, y; // Player's position
    private int direction = -1;
    private final int MAX_HEALTH = 5;
    private int currentHealth;
    private BufferedImage sprite, spriteL, spriteR;
    private GamePanel targetPanel;
    Timer updateTimer;
    
    public Boss(int x, int y, GamePanel t)
    {
        super(x,y,"sprites/bossLeft.png",t);
        this.x = x;
        this.y = y;
        targetPanel = t;
        loadSprites("sprites/bossLeft.png", "sprites/bossRight.png");
        sprite=spriteL;
        targetPanel.SetBoss(this);
        
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
                case -1 -> sprite = spriteR;
                case 1 -> sprite = spriteL;
                default -> throw new IllegalArgumentException("Dirction can only be 1 or -1");
            }
        }
        targetPanel.repaint();
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
    
    @Override
    public void draw(Graphics g){super.draw(g);} //System.out.println("Drawing in ground");}
    
    @Override
    public void draw(Graphics2D g2){super.draw(g2);}
}
