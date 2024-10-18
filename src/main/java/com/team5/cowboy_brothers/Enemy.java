/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.team5.cowboy_brothers;
import java.awt.*;
import java.awt.event.*;

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
    private boolean alive;
    private int species;
    private int[] siz={3,2,1}, sig = {1,2,3}, pathway;
    Color color;
    private int boX, boY, BoW=20,BoH=20;
    private final int MOVE_SPEED = 5; // Movement speed of the box
    public Enemy(){
        IDName=null;
        alive=false;
        species=-1;
        
    }
    //Instantiator Has ID name for profiling, alive to know if to display them,
    //and species value for type of path to walk and type of sprite to use
    public Enemy(String s, int i, int x, int y, Color color){
        IDName =s;
        alive=true;
        species=i;
        boX=x;
        boY=y;
        this.color=color;
        
    }
    
    //To paint the temp sprite
    public void draw(Graphics g){
        g.setColor(this.color);
        g.fillRect(this.boX, this.boY, this.BoW, this.BoH);
        
    }
    public String getName(){
        return IDName;
    }
    public boolean getAl(){
        return alive;
    }
    public void setboX(int x){
        boX+=x;
    }
    public void setboY(int y){
        boY+=y;
    }
    public void setBoW(int w){
        BoW=y;
    }
    public void setBoH(int h){
        BoH=h;
    }
    //The predefined path will be set here
    public void path(){
        switch(this.species){
            case 1-> pathway=siz;
            case 2->pathway=sig;
        }
    }
    public void setColor(Color color){
        this.color=color;
    }
    public void keyPressed(KeyEvent e){
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W, KeyEvent.VK_UP ->    // Move up
                this.setboY(-MOVE_SPEED);
            case KeyEvent.VK_S, KeyEvent.VK_DOWN ->  // Move down
                this.setboY(MOVE_SPEED);
            case KeyEvent.VK_A, KeyEvent.VK_LEFT ->  // Move left
                this.setboX(MOVE_SPEED);
            case KeyEvent.VK_D, KeyEvent.VK_RIGHT -> // Move right
                this.setboX(-MOVE_SPEED);
            
        }
    }
    //Collision detection
    private boolean collide(){
        //get position of enemy if overlap return true else false
        
        
        
        return false;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
