package com.team5.cowboy_brothers;

public abstract class Map {
    protected int width;
    protected int height;
    protected Player player;

    public Map(int width, int height) {
        this.width = width;
        this.height = height;
    }

    // Abstract method for loading map stats
    public abstract void loadMapStats();

    // Method to set the player
    public void setPlayer(Player player) {
        this.player = player;
    }
    public void DisposeObjects()
    {
        System.out.println("Nullifying gameobjects");
        
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
        System.gc();
    }

}
