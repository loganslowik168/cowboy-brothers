package com.team5.cowboy_brothers;

import java.util.ArrayList;
import java.util.List;

public class GameWorld {
    private List<GameObject> objects; // List to hold game objects

    // Constructor
    public GameWorld() {
        objects = new ArrayList<>();
        initializeGround();
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

    // Optional: Update all game objects
    public void update() {
        for (GameObject obj : objects) {
            obj.update(); // Call the update method for each object
        }
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
