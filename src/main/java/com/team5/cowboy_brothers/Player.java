package com.team5.cowboy_brothers;

public class Player {
    private static final int NUM_OF_LEVELS = 5;
    private int currentHealth;
    private int currentAmmo;
    private int maxUnlockedLevel;
    private int currentScore;
    private int[] highScores;
    private static final int MOVE_SPEED = 1;
    private static final int JUMP_HEIGHT = 2;
    private static final int MAX_AMMO = 6;
    private static final int MAX_HEALTH = 3;

    // Constructor
    public Player(int currentHealth, int currentAmmo, int maxUnlockedLevel, int currentScore, int[] highScores) {
        this.currentHealth = currentHealth;
        this.currentAmmo = currentAmmo;
        this.maxUnlockedLevel = maxUnlockedLevel;
        this.currentScore = currentScore;
        this.highScores = new int[NUM_OF_LEVELS];
        
        // Initialize high scores
        if (highScores != null && highScores.length == NUM_OF_LEVELS) {
            System.arraycopy(highScores, 0, this.highScores, 0, NUM_OF_LEVELS);
        }
    }

    // Getters
    public int getCurrentHealth() {
        return currentHealth;
    }

    public int getCurrentAmmo() {
        return currentAmmo;
    }

    public int getMaxUnlockedLevel() {
        return maxUnlockedLevel;
    }

    public int getCurrentScore() {
        return currentScore;
    }

    public int[] getHighScores() {
        return highScores.clone(); // Return a copy to protect encapsulation
    }

    // Setters
    public void setCurrentHealth(int currentHealth) {
        if (currentHealth >= 0 && currentHealth <= MAX_HEALTH) {
            this.currentHealth = currentHealth;
        }
    }

    public void setCurrentAmmo(int currentAmmo) {
        if (currentAmmo >= 0 && currentAmmo <= MAX_AMMO) {
            this.currentAmmo = currentAmmo;
        }
    }

    public void setMaxUnlockedLevel(int maxUnlockedLevel) {
        if (maxUnlockedLevel >= 0 && maxUnlockedLevel <= NUM_OF_LEVELS) {
            this.maxUnlockedLevel = maxUnlockedLevel;
        }
    }

    public void setCurrentScore(int currentScore) {
        this.currentScore = currentScore;
    }

    public void setHighScores(int[] highScores) {
        if (highScores != null && highScores.length == NUM_OF_LEVELS) {
            System.arraycopy(highScores, 0, this.highScores, 0, NUM_OF_LEVELS);
        }
    }
}
