package com.team5.cowboy_brothers;

abstract public class GameObject {
    private float x; // X position
    private float y; // Y position
    private float width; // Width of the object
    private float height; // Height of the object

    // Constructor
    public GameObject(float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    // Getters
    public float[] getPosition() {
        return new float[]{x, y};
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    // Setters
    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    // Optional: Additional methods for behavior
    public void update() {
        // Logic to update the object's state, if needed
    }

    public void render() {
        // Logic to draw the object, if using graphics
    }
}
