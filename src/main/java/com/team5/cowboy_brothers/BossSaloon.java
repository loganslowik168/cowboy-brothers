package com.team5.cowboy_brothers;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.swing.Timer;

public class BossSaloon extends GameObject{
    private int x; // X position
    private int y; // Y position
    private int width; // Width of the object
    private int height; // Height of the object
    private BufferedImage sprite;
    private GamePanel targetPanel;
    Timer repaintTimer;
    private Boss B;
    public BossSaloon(int x, int y, GamePanel targetPanel, Boss B)
    {
        super(x, y, "sprites/saloon.png", targetPanel);
        this.B = B;
        targetPanel.SetSaloon(this);
        System.out.println("Creating saloon");
        
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
        
        // Schedule the Dispose() method to be called after 0.25 seconds (250 milliseconds)
        scheduler.schedule(new Runnable() {
            @Override
            public void run() {
                ExplodeSaloon();
            }
        }, 3000, TimeUnit.MILLISECONDS);
        
        // You can also shut down the scheduler if no further tasks are needed
        scheduler.shutdown();
    }
    
    @Override
    public void draw(Graphics g){super.draw(g);}
    
    @Override
    public void draw(Graphics2D g2){super.draw(g2);}
    
    public void ExplodeSaloon()
    {
        loadSprite("sprites/saloonBoom.png");
        
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
        
        // Schedule the Dispose() method to be called after 0.25 seconds (250 milliseconds)
        scheduler.schedule(new Runnable() {
            @Override
            public void run() {
                Dispose();
                B.BeginFalling();
            }
        }, 500, TimeUnit.MILLISECONDS);
        
        // You can also shut down the scheduler if no further tasks are needed
        scheduler.shutdown();
    }
    
}
