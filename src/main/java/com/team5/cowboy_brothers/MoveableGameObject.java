/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.team5.cowboy_brothers;

/**
 *
 * @author matth
 */
public abstract class MoveableGameObject {
    protected int x; // X position
    protected int y; // Y position
    private float width; // Width of the object
    private float height; // Height of the object
    private int speed;


    

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
    public abstract void update();

    public void render() {
        // Logic to draw the object, if using graphics//
    }
    public void ShiftPosition(int shift) //sidescrolling element
    {
        x+=shift;
    }
    public abstract void Dispose();
    protected int GetXOffset() {return x;}
}
