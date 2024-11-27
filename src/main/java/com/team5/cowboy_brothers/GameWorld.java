package com.team5.cowboy_brothers;

import java.util.ArrayList;
import java.util.List;

import com.team5.cowboy_brothers.Cowboy_bros_Menu.GameState;

public class GameWorld {
    private List<GameObject> objects; // List to hold game objects
    private Player player;
    private Flag flag;
    int[] hs = {0,0,0,0,0};
    private Cowboy_bros_Menu.GameState currentState;
    private Cowboy_bros_Menu menu;
    
    private int levelNumber;
    // Constructor
    public GameWorld(Cowboy_bros_Menu menu) {
        this.menu = menu;
        objects = new ArrayList<>();
        initializeGround();
        levelNumber = 1; // Set level number
        
        // Initialize player and flag
       // player = new Player(3,6,1,0,hs,0,0,Cowboy_brothers.olly.VisibleMenu.gameplayPanel); // Example position and size
        flag = new Flag(200, 100, 30, 30); // Example position and size
        currentState = Cowboy_bros_Menu.GameState.GAMEPLAY; // Start in playing state
        
    }

    // Method to initialize ground objects
    private void initializeGround() {
        // example lvl 1
        Ground ground1 = new Ground(0, 400, 800, 50, "sprites/DesertGroundTile");
        Ground ground2 = new Ground(0, 300, 800, 50, "sprites/DesertGroundTile");
        Ground ground3 = new Ground(0, 200, 800, 50, "sprites/DesertGroundTile");
        
        // Add them to the objects list
        objects.add(ground1);
        objects.add(ground2);
        objects.add(ground3);
        

    }

    // Method to move all objects in the world
    public void moveObjects(float dx, float dy) {
        for (GameObject obj : objects) {
            System.out.println("MOVING OBJECTS");
            obj.setX(obj.getPosition()[0] + dx);
            obj.setY(obj.getPosition()[1] + dy);
        }
    }

    public void checkCollisions() {
        if (player.collidesWithFlag(flag)) {
            System.out.println("Collision detected with flag!");
            menu.switchState(Cowboy_bros_Menu.GameState.WIN_MENU); // Switch to win menu if collided with flag
        }
    }
    // Method to check health
    private void checkHealth() {
        if (player.getCurrentHealth() <= 0) {
            menu.switchState(GameState.LOSE_MENU); // Call the menu's switchState method
        }
    }
    public void update() {
        checkCollisions(); // Check for collisions in each update
        checkHealth(); // Check health for lose condition
    }

    // Optional: Render all game objects
    public void render() {
        for (GameObject obj : objects) {
            obj.render(); // Call the render method for each object
        }
    }

    // Method to get the list of objects (if needed)
    public List<GameObject> getObjects() {
        return objects;
    }
}
