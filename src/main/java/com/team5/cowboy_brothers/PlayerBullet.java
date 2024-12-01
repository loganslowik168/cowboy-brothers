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
public class PlayerBullet extends Bullet {
    
    private BufferedImage sprite;
    protected GamePanel targetPanel;

    public PlayerBullet(int startX, int startY, int direction, int speed, GamePanel TPB, int width, int height)  {
        super(startX,startY,direction,speed, TPB,"sprites/PlayerBulletLeft.png","sprites/PlayerBulletRight.png", width, height);
        targetPanel = TPB;
        //loadSprite("sprites/PlayerBullet.png");
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
    protected void setupUpdateTimer(){
        updateTimer.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                update();
                CheckCollision();
                if(isOffScreen(targetPanel.getWidth(),targetPanel.getHeight()))
                    targetPanel.alterList();
            }
        });
    }
    @Override
    protected boolean CheckCollision()
    {
        for (Enemy e : new CopyOnWriteArrayList<>(targetPanel.listOfEnemys)) 
        {
            final int ENEMY_WIDTH = e.getWidth();
            final int ENEMY_HEIGHT = e.getHeight();
            System.out.println("CHECKING " + this.x + "+"+this.width+">"+e.GetX()+"&"+x+"<"+e.GetX()+"+"+ENEMY_WIDTH
            +"&"+y+"+"+height+">"+e.GetY()+"&"+y+"<"+e.GetY()+"+"+ENEMY_HEIGHT);
            
            if (this.x + this.width > e.GetX() && this.x < e.GetX() + ENEMY_WIDTH &&
                        this.y + this.height > e.GetY() && this.y < e.GetY() + ENEMY_HEIGHT) {
                System.out.println("bullet hit enemy");
                e.Dispose();
                Dispose();
                return true;
            }
            
        }
        if (targetPanel.boss != null)
        {
            Boss e=targetPanel.boss;
            final int ENEMY_WIDTH = e.getWidth();
            final int ENEMY_HEIGHT = e.getHeight();
            if (this.x + this.width > e.GetX() && this.x < e.GetX() + ENEMY_WIDTH &&
                        this.y + this.height > e.GetY() && this.y < e.GetY() + ENEMY_HEIGHT) {
                System.out.println("bullet hit boss!!");
                e.Hurt(1);
                Dispose();
                return true;
            }
        }
        
        return false;
    }
    
}
