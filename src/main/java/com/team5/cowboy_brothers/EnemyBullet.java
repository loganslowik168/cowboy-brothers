/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.team5.cowboy_brothers;
import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.imageio.ImageIO;

/**
 *
 * @author matth
 */
public class EnemyBullet extends Bullet {
    
    private BufferedImage sprite;
    //protected Timer updateTimer;
    GamePanel targetPanel;

    public EnemyBullet(int startX, int startY, int direction, int playerX, int playerY, int speed, GamePanel TPB, int width, int height) {
        super(startX,startY,direction,speed, TPB,"sprites/EnemyBulleLeft.png","sprites/EnemyBulletRight.png", width, height);
        targetPanel = TPB;
        Cowboy_brothers.olly.gameWorld.moveableObjects.add(this);
        // Calculate direction towards player
        //calculateDirection(playerX, playerY);
        //Super setDirection()
    }
   /* private void calculateDirection(int playerX, int playerY) {
        // Calculate angle between enemy and player
        double angle = Math.atan2(playerY - y, playerX - x);
        this.direction = (int) Math.toDegrees(angle);
    }*/
    
    public void clearSprite(){
        try {
            sprite = ImageIO.read(new File("sprites/black.png"));
            super.setSprite(sprite);
            System.out.println("Sprite loaded successfully.");
        } catch (IOException e) {
            System.err.println("Error loading sprite: " + e.getMessage());
        }
    }
    @Override
    protected boolean CheckCollision()
    {
        int pX = Cowboy_brothers.olly.player.GetX();
        int pY = Cowboy_brothers.olly.player.GetY();
        int P_WIDTH = 44;
        int P_HEIGHT = 74;
        if (this.x + this.width > pX && this.x < pX + P_WIDTH &&
                    this.y + this.height > pY && this.y < pY + P_HEIGHT) {
            System.out.println("Bullet hit player!");
            Cowboy_brothers.olly.player.Hurt(1);
            Dispose();
            return true;
        }
        
        return false;
    }
    
}
