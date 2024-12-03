package com.team5.cowboy_brothers;

import java.awt.Graphics;
import java.awt.Graphics2D;
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
    private final int EXPLOSION_RADIUS = 100;
    GamePanel targetPanel;
    private boolean isExploded = false;
    private float SPEED;
    
    private Timer updateTimer;
    
    public Bomb(Point startPosition, Point targetPosition, GamePanel targetPanel, float speed) {
        this.startPosition = startPosition;
        this.targetPosition = targetPosition;
        this.currentPosition = new Point(startPosition);
        this.progress = 0.0; // Bomb hasn't moved yet
        this.targetPanel = targetPanel;
        this.SPEED = speed;
        //System.out.println("Throwing bomb from point " + startPosition.x + ", " + startPosition.y); //This statement is not needed for now
        // Load the sprite image from file
        try {
            this.sprite = ImageIO.read(new File("sprites/Dynamite.png"));
        } catch (IOException e) {
            System.err.println("Error loading sprite image: " + e.getMessage());
        }
        
        
        updateTimer = new Timer(1000/60,null);
        updateTimer.addActionListener(new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e){
            update();
            targetPanel.repaint();
        }
        });
        updateTimer.start();
        targetPanel.AddBomb(this);
        Cowboy_brothers.olly.gameWorld.moveableObjects.add(this);
    }

    // Update the bomb's position based on the progress
    @Override
    public void update() {
        // Calculate the distance between the start and target positions
        double distanceX = targetPosition.x - startPosition.x;
        double distanceY = targetPosition.y - startPosition.y;
        //System.out.println("Bomb position: " + currentPosition); //This statement is not needed for now
                // Progress goes from 0 to 1
        progress += SPEED; // Adjust the speed of the bomb's movement by modifying this value

        // If the bomb has reached the target, stop
        if (progress >= 1.0) {
            progress = 1.0;
        }

        // Interpolate the bomb's position with a parabolic arc
        double t = progress; // t is a value between 0 and 1
        double arcOffset = ARC_HEIGHT * Math.sin(Math.PI * t); // Simple sine curve for arc
        int x = (int) (startPosition.x + t * distanceX + GetXOffset());
        int y = (int) (startPosition.y + t * distanceY - arcOffset);

        // Update the bomb's current position
        currentPosition.setLocation(x, y);
        
        if (hasReachedTarget() && !isExploded) {Explode();}
        //System.out.println(currentPosition.x + "vs" + GetXOffset()); //This statement is not needed for now
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
            //System.out.println("Drawing player sprite at position: (" + x + ", " + y + ")"); //This statement is not needed for now
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
        if (sprite != null) {sprite = null;}
        if(targetPanel!=null){targetPanel.repaint();}
        if (targetPanel != null) {targetPanel = null;}
        if (updateTimer != null)
        {
            updateTimer.removeActionListener(updateTimer.getActionListeners()[0]);
            updateTimer.stop();
            updateTimer = null;
        }
    }
    public void pauseTimer() {
    if (updateTimer != null) {
        updateTimer.stop();
    }
}
    public void resumeTimer() {
    if (updateTimer != null) {
        updateTimer.start();
    }
}
    private void Explode()
    {
        isExploded = true;
        
        CheckForDamage();
        
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
    
    private void CheckForDamage()
    {
        Point p = new Point(Cowboy_brothers.olly.player.GetX(),Cowboy_brothers.olly.player.GetY());
        if (currentPosition.distance(p)<=EXPLOSION_RADIUS)
        {
            Cowboy_brothers.olly.player.Hurt(1);
        }
        Point b = new Point(Cowboy_brothers.olly.gameWorld.boss.GetX(),Cowboy_brothers.olly.gameWorld.boss.GetY());
        if (currentPosition.distance(b)<=EXPLOSION_RADIUS)
        {
            Cowboy_brothers.olly.gameWorld.boss.Hurt(1);
        }
        
        if (Cowboy_brothers.olly.IsThereASaloon)
        {
            BossSaloon saloon = targetPanel.GetSaloon();
            int lX = saloon.GetX();
            int rX = lX+578;
            int tY = saloon.GetY();
            int bY = tY+261;

            Point saloonPosition = new Point(saloon.GetX()+saloon.GetXOffset(),201);
            boolean withinX = ((currentPosition.x>saloonPosition.x) && (currentPosition.x<saloonPosition.x+578));
            boolean withinY = ((currentPosition.y>saloonPosition.y) && (currentPosition.y<saloonPosition.y+261));
            if (withinX && withinY)
            {
                //System.out.println("EGGASPLOSSSSION!!!"); //This statement is not needed for now
                saloon.ExplodeSaloon();
                saloon = null;
            }
            //System.out.println("val"+lX+" "+rX+" / "+tY+" "+bY); //This statement is not needed for now
            //System.out.println("("+currentPosition.x+","+currentPosition.y+") vs ("+saloonPosition.x+","+saloonPosition.y+")"); //This statement is not needed for now

        }
        
        
    }
}
