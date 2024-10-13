/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.team5.cowboy_brothers;

/**
 *make an enemy class that follows a predefined path. instantiate several of these 
 * enemies that come from the same enemy class but follow different paths. detect collision between any object and enemy.  
 * on collision, print to console "HIT {object_id}" 
 * or something similar. make some stationary object that the path will 
 * collide with to demonstrate that it can collide with both players and terrain
 * @author matth
 */
public class Enemy {
    private String IDName;
    private boolean alive;
    private int species;
    
    public Enemy(){
        IDName=null;
        alive=false;
        species=-1;
    }
    //Instantiator Has ID name for profiling, alive to know if to display them,
    //and species value for type of path to walk and type of sprite to use
    public Enemy(String s, int i){
        IDName =s;
        alive=true;
        species=i;
    }
    
    //To paint the temp sprite
    public void create(){
        
    }
    
    //The predefined path will be set here
    public void path(){
        
    }
    
    //Collision detection
    private boolean collide(){
        //get position of enemy if overlap return true else false
        
        
        
        return false;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
