package com.team5.cowboy_brothers;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

public abstract class Map {
    protected int width;
    protected int height;
    protected Player player;
     private Image backgroundImage;

    public Map(int width, int height) {
        this.width = width;
        this.height = height;
    }
    
     public Map(String backgroundPath) {
        loadBackgroundImage(backgroundPath);
    }
// Lines 22 to 34 contain work in progress code for setting the background image, this method may be chnaged later
      // Load the background image
    private void loadBackgroundImage(String path) {
        ImageIcon icon = new ImageIcon(path); // Ensure the path is correct
        backgroundImage = icon.getImage();
    }

    // Render the background image
    public void render(Graphics g, int panelWidth, int panelHeight) {
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, panelWidth, panelHeight, null);
        }
    }
    
    // Abstract method for loading map stats
    public abstract void loadMapStats();

    // Method to set the player
    public void setPlayer(Player player) {
        this.player = player;
    }

}
