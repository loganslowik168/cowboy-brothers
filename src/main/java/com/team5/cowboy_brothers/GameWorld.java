package com.team5.cowboy_brothers;

import java.util.ArrayList;
import java.util.List;


public class GameWorld {
    public List<GameObject> objects; // List to hold game objects
    public List<MoveableGameObject> moveableObjects;
    public Boss boss;
    private Cowboy_bros_Menu.GameState currentState;
    private Cowboy_bros_Menu menu;
    public int totalOffset = 0;
    public int Selectedlvl;
    //private int DEBUGprohibitedMovementCounter = 0;
    // Constructor
    public GameWorld(Cowboy_bros_Menu menu) {
        this.menu = menu;
        objects = new ArrayList<GameObject>();
        moveableObjects = new ArrayList<MoveableGameObject>();
        currentState = Cowboy_bros_Menu.GameState.GAMEPLAY; // Start in playing state
        
    }
    
    // Method to move all objects in the world
    public void MoveObjects(int dx) {
        if (!Cowboy_brothers.olly.player.CheckGroundCollisionSpecial(-10, dx))
        {
            totalOffset -= dx;
            //System.out.println("MOVING OBJECTS");
            for (GameObject obj : objects) {
                obj.ShiftPosition(dx);

            }
            for (MoveableGameObject obj : moveableObjects){
                obj.ShiftPosition(dx);
            }
            if (boss != null)
            {
                boss.ShiftPosition(dx);
            }
        }
        //else {System.out.println("REQUESTED MOVEMENT PROHIBITED (" + DEBUGprohibitedMovementCounter + ")"); DEBUGprohibitedMovementCounter++;}
            
    }
    
}
