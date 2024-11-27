package com.team5.cowboy_brothers;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.awt.image.BufferedImage;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.imageio.ImageIO;
import java.io.*;
import javax.swing.Timer;


/**
 *make an enemy class that follows a predefined path. instantiate several of these 
 * enemies that come from the same enemy class but follow different paths. detect collision between any object and enemy.  
 * on collision, print to console "HIT {object_id}" 
 * or something similar. make some stationary object that the path will 
 * collide with to demonstrate that it can collide with both players and terrain
 * @author matth
 */
public class Enemy extends Rectangle {
    private String IDName;
    private boolean alive, forward=true;
    private int species;
    private int MAX_SPEED = 5;
    //current position
    int pos_x = 0;
    int pos_y = 0;
    
    private int x, y;     // Enemy position
    private int bulletSpeed = 5; // Bullet speed (slower than player bullets)
    private int screenWidth = 800;
    private int screenHeight = 600;
    private int[][] path; // Array to define the path with coordinates
    private int pathIndex; // Current index in the path
    int count=0;
    BufferedImage sprite;
    
    GamePanel targetPanel;
    Timer updateTimer,repaintTimer,bulletFireTimer;
    
    
    Color color;
    private int boX, boY, BoW=20,BoH=20;
    private final int MOVE_SPEED = 50; // Movement speed of the box
    
    int[] Lv1A={50,55,60,65,70,75,80,85,90,95,100};
    int[] Lv2A={300,305,310,315,320,325,330,335,340,345,350};
    int[] Lv3A={500,505,510,515,520,525,530,535,540,545,550};
    int[] YPosit= {240,240,240,240,240,240,240,240,240,240};
    
    //UPDATE THE FOLLOWING VALUES FOR THE DESTINATION
    int destinationX = 0;
    int destinationY = 0;
    
    //Instantiator Has ID name for profiling, alive to know if to display them,
    //and species value for type of path to walk and type of sprite to use
    public Enemy(String s, int k, Color color,GamePanel targetPanel){
        IDName =s;
        alive=true;
        species=k;
        boX=x;
        boY=y;
        this.color=color;
        this.targetPanel=targetPanel;
        path = new int[][] {
            {100, 100},
            {200, 200},
            {300, 100},
            {400, 200},
            {500, 100}
        };
        pathIndex = 0; // Start at the first point in the path
        this.x = path[0][0];
        this.y = path[0][1];
        loadSprite("sprites/Rough_OutlawEnemy.png");
        settupTimerEnemy();
    }
    
    private void loadSprite(String filePath) {
        try {
            sprite = ImageIO.read(new File(filePath));
            System.out.println("Sprite loaded successfully.");
        } catch (IOException e) {
            System.err.println("Error loading sprite: " + e.getMessage());
        }
    }
    public void draw(Graphics2D g2) {
        if (sprite != null) {
            g2.drawImage(sprite, (int) x, (int) y, targetPanel);
            //System.out.println("Drawing player sprite at position: (" + x + ", " + y + ")");
        } else {
            System.err.println("Sprite is not loaded.");
        }
    }

    // Update the position of the enemy
    public void updatePosition() {
        if (pathIndex < path.length) {
            destinationX = path[pathIndex][0];
            destinationY = path[pathIndex][1];

            // Calculate the direction vector
            double dirX = destinationX - pos_x;
            double dirY = destinationY - pos_y;
            double distance = Math.sqrt(dirX * dirX + dirY * dirY);

            // If the enemy is already at the destination
            if (distance < 1e-5) {
                pathIndex++; // Move to the next point in the path
                System.out.println("Reached: (" + destinationX + ", " + destinationY + ")");
            } else {
                // Normalize the direction vector
                double normDirX = dirX / distance;
                double normDirY = dirY / distance;

                // Calculate the movement based on max speed
                double moveX = normDirX * MAX_SPEED;
                double moveY = normDirY * MAX_SPEED;

                // Update the position
                pos_x += moveX;
                pos_y += moveY;

                // Print current position
                System.out.println("Current Position: (" + pos_x + ", " + pos_y + ")");
            }
        }
        
    }

    public void fireBullet() {
        EnemyBullet bullet = new EnemyBullet(x, y, -1, bulletSpeed, 1, targetPanel); // -1 for left
        targetPanel.setBullet(bullet);
    }
    
    //To paint the temp sprite
    public void draw(Graphics g){
        g.setColor(this.color);
        g.fillRect(this.boX, this.boY, this.BoW, this.BoH);
        
    }
    public int getCount(){
        return count;
    }
    public void nextPosit(){
        if(count ==0||count==10){
            forward= !(forward);
        }
        if(forward){
            count++;
        }else{
            count--;
        }
        
    }
    
    //Need a timer for traveling on their paths
    public void settupTimerEnemy(){
        updateTimer = new Timer(1000,null);
        repaintTimer = new Timer(1000,null);
        bulletFireTimer = new Timer(3000,null);
        updateTimer.addActionListener(new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e){
            updatePosition();
            targetPanel.revalidate();
        }
        });
        repaintTimer.addActionListener(new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e){
            targetPanel.repaint();
        }
        });
        bulletFireTimer.addActionListener(new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e){
            fireBullet();
        }
        });
        updateTimer.start();
        repaintTimer.start();
        bulletFireTimer.start();
    }
    public void pauseTimers(){
        updateTimer.stop();
        repaintTimer.stop();
        bulletFireTimer.stop();
    }
    public void unPauseTimers(){
        updateTimer.start();
        repaintTimer.start();
        bulletFireTimer.start();
    }
    
    
    
    
    public String getName(){
        return IDName;
    }
    public boolean IsAlive(){
        return alive;
    }
    public void setboX(int bx){
        boX+=bx;
    }
    public void setboY(int by){
        boY+=by;
    }
    public void setBoW(int bw){
        BoW=bw;
    }
    public void setBoH(int bh){
        BoH=bh;
    }
    public void setColor(Color color){
        this.color=color;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
