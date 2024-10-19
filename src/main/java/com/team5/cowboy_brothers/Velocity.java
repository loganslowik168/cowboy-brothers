package com.team5.cowboy_brothers;

public class Velocity {
    private double x; // Velocity in the x direction
    private double y; // Velocity in the y direction

    // Constructor to initialize velocity
    public Velocity(double x, double y) {
        this.x = x;
        this.y = y;
    }

    // Get current velocity
    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    // Method to add a change in velocity
    public void applyChange(double deltaX, double deltaY) {
        this.x += deltaX;
        this.y += deltaY;
    }

    // Method to get the new velocity as a formatted string
    public String getNewVelocity() {
        return String.format("New Velocity - X: %.2f, Y: %.2f", x, y);
    }

    // Optional: Method to reset velocity
    public void reset() {
        this.x = 0;
        this.y = 0;
    }
}

