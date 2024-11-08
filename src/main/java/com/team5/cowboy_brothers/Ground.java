
package com.team5.cowboy_brothers;

public class Ground extends GameObject {
    // Additional properties specific to the ground, if needed
    private String texture; // For example, a texture or image path

    // Constructor
    public Ground(float x, float y, float width, float height, String texture) {
        super(x, y, width, height); // Call the constructor of GameObject
        this.texture = texture;
    }

    // Getters
    public String getTexture() {
        return texture;
    }

    // Optional: Override the update method if needed
    @Override
    public void update() {
        // Logic to update ground state, if applicable
    }

    // Optional: Override the render method for drawing the ground
    @Override
    public void render() {
        // Logic to draw the ground using its texture
        // For example, you might use a graphics context to draw the texture at the ground's position
    }

}
