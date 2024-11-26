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
import javax.imageio.ImageIO;

/**
 *
 * @author matth
 */
public class PlayerBullet extends Bullet {
    
    private BufferedImage sprite;

    public PlayerBullet(int startX, int startY, int direction, int speed, GamePanel TPB)  {
        super(startX,startY,direction,speed, TPB);        
        loadSprite("sprites/PlayerBullet.png");
    }
    
    
    private void loadSprite(String filePath) {
        try {
            sprite = ImageIO.read(new File(filePath));
            super.setSprite(sprite);
            System.out.println("Sprite loaded successfully.");
        } catch (IOException e) {
            System.err.println("Error loading sprite: " + e.getMessage());
        }
    }
    
    public boolean collision(){
     //check for collision with enemy and ground   
     return false;
    }
    


    

    

    
}
