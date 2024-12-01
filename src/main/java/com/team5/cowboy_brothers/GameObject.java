package com.team5.cowboy_brothers;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.Timer;

public abstract class GameObject extends Rectangle{
    private int x; // X position
    private int y; // Y position
    protected BufferedImage sprite;
    private GamePanel targetPanel;
    public Timer repaintTimer;
    

    // Constructor
    public GameObject(int x, int y, String spriteFilePath, GamePanel targetPanel) {
        this.x = x;
        this.y = y;
        loadSprite(spriteFilePath);
        this.targetPanel = targetPanel;
        setupRepaintTimer();
        repaintTimer.start();
        
        Cowboy_brothers.olly.gameWorld.objects.add(this);
        
    }
    protected void loadSprite(String filePath) {
        try {
            sprite = ImageIO.read(new File(filePath));
            //System.out.println("Sprite " + filePath + " loaded successfully.");
        } catch (IOException e) {
            System.err.println("Error loading sprite: " + e.getMessage());
        }
    }

    public void draw(Graphics g) {
        g.drawImage(sprite, x, y, null); // Draw the bullet sprite
        //System.out.println("Drawing GameObject");
    }
    
    public void draw(Graphics2D g2) {
        //System.out.println("Drawing Ground");
        if (sprite != null) {
            g2.drawImage(sprite, (int) x, (int) y, targetPanel);
            //System.out.println("Drawing Gameobject " + sprite.toString() + " @ " + x + ", " + y);
        } else {
            //System.err.println("Sprite is not loaded.");
        }
    }
    
    private void setupRepaintTimer() {
        repaintTimer = new Timer(1000/60,null);
        repaintTimer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                targetPanel.repaint(); // Repaint the panel regularly
                //System.out.println("CALLING REPAINT");
                
                //Cowboy_brothers.olly.VisibleMenu.gameplayPanel.repaint();
            }
        }); // ~60 FPS
        
    }
    // Getters
    public int[] getPosition() {
        return new int[]{x, y};
    }



    // Optional: Additional methods for behavior
    public void update() {
        // Logic to update the object's state, if needed
    }

    public void render() {
        // Logic to draw the object, if using graphics//
    }
    public void ShiftPosition(int shift) //sidescrolling element
    {
        x=x+shift;
    }
    public void Dispose()
    {
        if (sprite != null) {sprite = null;}
        if (repaintTimer != null)
        {
            repaintTimer.stop();
            repaintTimer.removeActionListener(repaintTimer.getActionListeners()[0]);
            repaintTimer = null;
        }
        if(targetPanel!=null) {targetPanel.repaint();}
        if (targetPanel != null) {targetPanel = null;}
        

    }
    protected void UpdateSprite(BufferedImage newSprite)
    {
        sprite = newSprite;
    }
    public int GetXOffset() {return x;} //yes i know these are the same
    public int GetY() {return y;}
}
