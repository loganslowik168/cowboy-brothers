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

public abstract class GameObject {
    private int x; // X position
    private int y; // Y position
    private int width; // Width of the object
    private int height; // Height of the object
    private BufferedImage sprite;
    private GamePanel targetPanel;
    Timer repaintTimer;

    // Constructor
    public GameObject(int x, int y, int width, int height, String spriteFilePath, GamePanel targetPanel) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        loadSprite(spriteFilePath);
        this.targetPanel = targetPanel;
        setupRepaintTimer();
        repaintTimer.start();
        
    }
    private void loadSprite(String filePath) {
        try {
            sprite = ImageIO.read(new File(filePath));
            System.out.println("Sprite loaded successfully.");
        } catch (IOException e) {
            System.err.println("Error loading sprite: " + e.getMessage());
        }
    }

    public void draw(Graphics g) {
        g.drawImage(sprite, x, y, null); // Draw the bullet sprite
        //System.out.println("Drawing Ground");
    }
    
    public void draw(Graphics2D g2) {
        //System.out.println("Drawing Ground");
        if (sprite != null) {
            g2.drawImage(sprite, (int) x, (int) y, targetPanel);
            //System.out.println("Drawing player sprite at position: (" + x + ", " + y + ")");
        } else {
            System.err.println("Sprite is not loaded.");
        }
    }
    
    private void setupRepaintTimer() {
        repaintTimer = new Timer(1000/60,null);
        repaintTimer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                targetPanel.repaint(); // Repaint the panel regularly
                //System.out.println("CALLING REPAINT");
                
                Cowboy_brothers.olly.VisibleMenu.gameplayPanel.repaint();
            }
        }); // ~60 FPS
        
    }
    // Getters
    public int[] getPosition() {
        return new int[]{x, y};
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    // Setters
    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    // Optional: Additional methods for behavior
    public void update() {
        // Logic to update the object's state, if needed
    }

    public void render() {
        // Logic to draw the object, if using graphics//
    }
}
