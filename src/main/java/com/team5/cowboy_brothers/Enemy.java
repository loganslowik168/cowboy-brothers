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
    private final int MOVE_SPEED = 50; // Movement speed of the box
    
    DblLinkList Level1, Level2, Level3, PersonalList;
    int[] Lv1A={50,55,60,65,70,75,80,85,90,95,100};
    int[] Lv2A={300,305,310,315,320,325,330,335,340,345,350};
    int[] Lv3A={500,505,510,515,520,525,530,535,540,545,550};
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
    public Enemy(String s, int k, int x, int y, Color color){
        //Level1.insertAtEnd(Lv1A[0]);
        //Level2.insertAtEnd(Lv2A[0]);
        //Level3.insertAtEnd(Lv3A[0]);
        IDName =s;
        alive=true;
        species=k;
        boX=x;
        boY=y;
        this.color=color;
        
        //path();
        
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
        BoW=w;
    }
    public void setBoH(int h){
        BoH=h;
    }
    //The predefined path will be set here
    public void path(){
        /*int[] lv1A={50,55,60,65,70,75,80,85,90,95,100};
        for(int i=0; i<lv1A.length;i++){
            Level1.insertAtEnd(lv1A[i]);
        }
        int[] Lv2A={300,305,310,315,320,325,330,335,340,345,350};
        for(int i=0; i<Lv2A.length;i++){
            Level2.insertAtEnd(Lv2A[i]);
        }
        int[] Lv3A={500,505,510,515,520,525,530,535,540,545,550};
        for(int i=0; i<Lv3A.length;i++){
            Level3.insertAtEnd(Lv3A[i]);
        }*/
        
        
        
        switch(this.species){
            case 1 -> {
                for(int m=1;m<Level1.sizeL();m++){
                    PersonalList.insertAtEnd((Level1.getvalue(m)).getData());
                }
            }
            case 2 -> {
                for(int m=1;m<Level2.sizeL();m++){
                    PersonalList.insertAtEnd(Level2.getvalue(m).getData());
                }
            }
            case 3 -> {
                for(int m=1;m<Level3.sizeL();m++){
                    PersonalList.insertAtEnd(Level3.getvalue(m).getData());
                }
            }
            case -1->{
                break;
            }
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
