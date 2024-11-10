package com.team5.cowboy_brothers;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

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
    private ArrayList<EnemyBullet> bullets = new ArrayList<>();
    private int x, y;     // Enemy position
    private int bulletSpeed = 5; // Bullet speed (slower than player bullets)
    private int screenWidth = 800;
    private int screenHeight = 600;
    
    int count=0;
    
    
    
    
    
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
    
    /*public void ListSettup(){
        int[] lv1A={50,55,60,65,70,75,80,85,90,95,100};
        for(int i=0; i<lv1A.length;i++){
            Level1.insertAtEnd(lv1A[i]);
        }
        int[] Lv2A={300,305,310,315,320,325,330,335,340,345,350};
        for(int i=0; i<Lv2A.length;i++){
            Level2.insertAtEnd(lv1A[i]);
        }
        int[] Lv3A={500,505,510,515,520,525,530,535,540,545,550};
        for(int i=0; i<Lv3A.length;i++){
            Level3.insertAtEnd(lv1A[i]);
        }
    }
    */
    
    
    
    public Enemy(){
        IDName=null;
        alive=false;
        species=-1;
        
    }
    //Instantiator Has ID name for profiling, alive to know if to display them,
    //and species value for type of path to walk and type of sprite to use
    public Enemy(String s, int k, Color color, int startX,int startY){
        //Level1.insertAtEnd(Lv1A[0]);
        //Level2.insertAtEnd(Lv2A[0]);
        //Level3.insertAtEnd(Lv3A[0]);
        IDName =s;
        alive=true;
        species=k;
        boX=x;
        boY=y;
        this.color=color;
        this.x = startX;
        this.y = startY;
        //path();
        
    }

    public void fireBullet() {
        EnemyBullet bullet = new EnemyBullet(x, y, -1, bulletSpeed, 1); // -1 for left
        bullets.add(bullet);
    }
    
    public void updateBullets() {
        // Update and remove off-screen bullets
        for (int i = bullets.size() - 1; i >= 0; i--) {
            EnemyBullet bullet = bullets.get(i);
            bullet.update();

            if (bullet.isOffScreen(screenWidth, screenHeight)) {
                bullets.remove(i);
            }
        }
    }
    public List<EnemyBullet> getBullets() {
        return bullets;
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
        // Method to update the position of the enemy
    public void updatePosition() {
        // Calculate the direction vector
        double dirX = destinationX - pos_x;
        double dirY = destinationY - pos_y;
        double distance = Math.sqrt(dirX * dirX + dirY * dirY);

        // If the enemy is already at the destination
        if (distance < 1e-5) {
            return; // No movement needed
        }

        // Normalize the direction vector
        double normDirX = dirX / distance;
        double normDirY = dirY / distance;

        // Calculate the movement based on max speed
        double moveX = normDirX * MAX_SPEED;
        double moveY = normDirY * MAX_SPEED;

        // Update the position
        pos_x += moveX;
        pos_y += moveY;

        // Check if the enemy has reached the destination
        if (Math.sqrt((pos_x - destinationX) * (pos_x - destinationX) + (pos_y - destinationY) * (pos_y - destinationY)) < MAX_SPEED) {
            pos_x = destinationX; // Snap to destination
            pos_y = destinationY;
        }
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
