/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.team5.cowboy_brothers;

/**
 *
 * @author matth
 */
public class MoveableGameObject {
    private int x; // X position
    private int y; // Y position
    private float width; // Width of the object
    private float height; // Height of the object
    private int speed;

    // Constructor
    public MoveableGameObject(int x, int y, float width, float height, int speed) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.speed = speed;
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
    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    // Optional: Additional methods for behavior
    public void update() {
        // Logic to update the object's state, if needed
    }

    public void render() {
        // Logic to draw the object, if using graphics//
    }
}
