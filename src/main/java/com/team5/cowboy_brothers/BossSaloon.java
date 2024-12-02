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
    private int y;
    private BufferedImage sprite;
    private GamePanel targetPanel;
    private Boss B;
    public BossSaloon(int x, int y, GamePanel targetPanel, Boss B)
    {
        super(x, y, "sprites/saloon.png", targetPanel);
        this.B = B;
        targetPanel.SetSaloon(this);
       // System.out.println("Creating saloon"); //This statement is not needed for now
        Cowboy_brothers.olly.IsThereASaloon = true;
        
    }
    
    @Override
    public void draw(Graphics g){super.draw(g);}
    
    @Override
    public void draw(Graphics2D g2){super.draw(g2);}
    
    public void ExplodeSaloon()
    {
        Cowboy_brothers.olly.IsThereASaloon = false;
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
    public int GetX() {return x;}
    public int GetY() {return y;}
}
