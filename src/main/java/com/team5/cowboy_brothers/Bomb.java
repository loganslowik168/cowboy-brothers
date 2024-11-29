package com.team5.cowboy_brothers;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.imageio.ImageIO;
import javax.swing.Timer;

public class Bomb extends MoveableGameObject{

    private Point startPosition;
    private Point targetPosition;
    private Point currentPosition;
    private BufferedImage sprite;
    private double progress;
    private static final double ARC_HEIGHT = 100; // Height of the arc (can be adjusted)
    GamePanel targetPanel;
    
    private float SPEED;
    
    private Timer updateTimer;
    
    public Bomb(Point startPosition, Point targetPosition, GamePanel targetPanel, float speed) {
        this.startPosition = startPosition;
        this.targetPosition = targetPosition;
        this.currentPosition = new Point(startPosition);
        this.progress = 0.0; // Bomb hasn't moved yet
        this.targetPanel = targetPanel;
        this.SPEED = speed;
        System.out.println("Throwing bomb from point " + startPosition.x + ", " + startPosition.y);
        // Load the sprite image from file
        try {
            this.sprite = ImageIO.read(new File("sprites/Dynamite.png"));
        } catch (IOException e) {
            System.err.println("Error loading sprite image: " + e.getMessage());
        }
        
        updateTimer = new Timer(1000/10,null);
        updateTimer.addActionListener(new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e){
            update();
            targetPanel.repaint();
        }
        });
        updateTimer.start();
        targetPanel.AddBomb(this);
    }

    // Update the bomb's position based on the progress
    public void update() {
        // Calculate the distance between the start and target positions
        double distanceX = targetPosition.x - startPosition.x;
        double distanceY = targetPosition.y - startPosition.y;
        //System.out.println("Bomb position: " + currentPosition);
                // Progress goes from 0 to 1
        progress += SPEED; // Adjust the speed of the bomb's movement by modifying this value

        // If the bomb has reached the target, stop
        if (progress >= 1.0) {
            progress = 1.0;
        }

        // Interpolate the bomb's position with a parabolic arc
        double t = progress; // t is a value between 0 and 1
        double arcOffset = ARC_HEIGHT * Math.sin(Math.PI * t); // Simple sine curve for arc
        int x = (int) (startPosition.x + t * distanceX);
        int y = (int) (startPosition.y + t * distanceY - arcOffset);

        // Update the bomb's current position
        currentPosition.setLocation(x, y);
        
        if (hasReachedTarget()) {Explode();}
    }

    // Render the bomb at its current position
    public void draw(Graphics g) {
        // Draw the sprite image at the bomb's current position
        if (sprite != null) {
            g.drawImage(sprite, currentPosition.x, currentPosition.y, null);
        }
    }
    public void draw(Graphics2D g2) {
        if (sprite != null) {
            g2.drawImage(sprite, (int) currentPosition.x, (int) currentPosition.y, targetPanel);
            //System.out.println("Drawing player sprite at position: (" + x + ", " + y + ")");
        } else {
            //System.err.println("Sprite is not loaded.");
        }
    }

    // Get the current position of the bomb
    public Point getCurrentPosition() {
        return currentPosition;
    }

    // Check if the bomb has reached the target
    public boolean hasReachedTarget() {
        return progress >= 1.0;
    }
    
    @Override
    public void Dispose()
    {
        updateTimer.removeActionListener(updateTimer.getActionListeners()[0]);
        updateTimer.stop();
        updateTimer = null;
        targetPanel = null;
        sprite = null;
    }
    private void Explode()
    {
        try {
            this.sprite = ImageIO.read(new File("sprites/Boom.png"));
        } catch (IOException e) {
            System.err.println("Error loading sprite image: " + e.getMessage());
        }
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
        
        // Schedule the Dispose() method to be called after 0.25 seconds (250 milliseconds)
        scheduler.schedule(new Runnable() {
            @Override
            public void run() {
                Dispose();
            }
        }, 250, TimeUnit.MILLISECONDS);
        
        // You can also shut down the scheduler if no further tasks are needed
        scheduler.shutdown();
    }
}
