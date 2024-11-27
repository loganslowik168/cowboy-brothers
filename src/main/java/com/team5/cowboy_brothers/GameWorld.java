package com.team5.cowboy_brothers;

import java.util.ArrayList;
import java.util.List;

import com.team5.cowboy_brothers.Cowboy_bros_Menu.GameState;

public class GameWorld {
    public List<GameObject> objects; // List to hold game objects
    public List<MoveableGameObject> moveableObjects;
    private Cowboy_bros_Menu.GameState currentState;
    private Cowboy_bros_Menu menu;
    
    private int levelNumber;
    // Constructor
    public GameWorld(Cowboy_bros_Menu menu) {
        this.menu = menu;
        objects = new ArrayList<GameObject>();
        moveableObjects = new ArrayList<MoveableGameObject>();

        levelNumber = 1; // Set level number

        
        // Initialize player and flag
        // player = new Player(3,6,1,0,hs,0,0,Cowboy_brothers.olly.VisibleMenu.gameplayPanel); // Example position and size
        //flag = new Flag(200, 100, 30, 30); // Example position and size
        currentState = Cowboy_bros_Menu.GameState.GAMEPLAY; // Start in playing state
        
    }

    // Method to initialize ground objects


    // Method to move all objects in the world
    public void MoveObjects(int dx) {
        //System.out.println("MOVING OBJECTS");
        for (GameObject obj : objects) {
            obj.ShiftPosition(dx);
        }
        for (MoveableGameObject obj : moveableObjects){
            System.out.println("Moving a moveable object");
            obj.ShiftPosition(dx);
        }
    }
    //this entire group of functions needs to be moved
    /*public void CheckCollisions() {
        if (Cowboy_brothers.olly.player.collidesWithFlag(flag)) {
            System.out.println("Collision detected with flag!");
            menu.switchState(Cowboy_bros_Menu.GameState.WIN_MENU); // Switch to win menu if collided with flag
        }
    }
    // Method to check health
    private void CheckHealth() {
        if (player.getCurrentHealth() <= 0) {
            menu.switchState(GameState.LOSE_MENU); // Call the menu's switchState method
        }
    }
    public void update() {
        CheckCollisions(); // Check for collisions in each update
        CheckHealth(); // Check health for lose condition
    }

    // Optional: Render all game objects
    public void render() {
        for (GameObject obj : objects) {
            obj.render(); // Call the render method for each object
        }
    }
*/
    // Method to get the list of objects (if needed)
    public List<GameObject> getObjects() {
        return objects;
    }
}
