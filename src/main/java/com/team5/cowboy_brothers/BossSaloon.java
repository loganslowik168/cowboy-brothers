package com.team5.cowboy_brothers;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.Timer;

public class BossSaloon extends GameObject{
    private int x; // X position
    private int y; // Y position
    private int width; // Width of the object
    private int height; // Height of the object
    private BufferedImage sprite;
    private GamePanel targetPanel;
    Timer repaintTimer;
    public BossSaloon(int x, int y, GamePanel targetPanel)
    {
        super(x, y, "sprites/saloon.png", targetPanel);
        targetPanel.SetSaloon(this);
        System.out.println("Creating saloon");
    }
    
    @Override
    public void draw(Graphics g){super.draw(g);}
    
    @Override
    public void draw(Graphics2D g2){super.draw(g2);}
}
