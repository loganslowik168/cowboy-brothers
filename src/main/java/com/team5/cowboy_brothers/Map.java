package com.team5.cowboy_brothers;

import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import javax.swing.ImageIcon;

public abstract class Map {
    protected int width;
    protected int height;
    protected Player player;
     private Image backgroundImage;
     protected ArrayList<Enemy> enemies = new ArrayList<>();

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
    
    

    protected void DisposeEnemies()
    {
      for (Enemy e : enemies) {e.Dispose();enemies.remove(e);}
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
    public void DisposeObjects()
    {
        Cowboy_brothers.olly.player.SetGravity(false);
        for (int i = 0; i < Cowboy_brothers.olly.gameWorld.objects.size(); i++) {
            GameObject obj = Cowboy_brothers.olly.gameWorld.objects.get(i);
            obj.Dispose();  // Clean up resources

            // Remove the object from the list
            Cowboy_brothers.olly.gameWorld.objects.remove(i);
            i--; // Adjust the index since the list size decreases after removal
        }
        for (int i = 0; i < Cowboy_brothers.olly.gameWorld.moveableObjects.size(); i++) {
            MoveableGameObject obj = Cowboy_brothers.olly.gameWorld.moveableObjects.get(i);

            obj.Dispose();  // Clean up resources

            // Remove the object from the list
            Cowboy_brothers.olly.gameWorld.moveableObjects.remove(i);
            i--; // Adjust the index since the list size decreases after removal
        }

        Cowboy_brothers.olly.gameWorld.objects.clear();
        Cowboy_brothers.olly.gameWorld.moveableObjects.clear();
        Cowboy_brothers.olly.player.ClearPlayerBullets();
    }

}
