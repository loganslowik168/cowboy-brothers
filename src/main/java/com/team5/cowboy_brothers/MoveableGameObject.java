package com.team5.cowboy_brothers;

/**
 *
 * @author matth
 */
public abstract class MoveableGameObject {
    protected int x; // X position
    protected int y; // Y position
    protected int width; // Width of the object
    protected int height; // Height of the object
    private int speed;


    
    public int GetX() {return x;}
    public int GetY() {return y;}
    public int getWidth() {
        return width;
    }

    public int getHeight() {
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

    /*public void render() {
        // Logic to draw the object, if using graphics//
    }*/
    public void ShiftPosition(int shift) //sidescrolling element
    {
        x+=shift;
    }
    public abstract void Dispose();
    protected int GetXOffset() {return x;}
}
