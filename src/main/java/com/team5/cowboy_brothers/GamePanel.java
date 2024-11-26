/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.team5.cowboy_brothers;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import javax.swing.JPanel;
import java.awt.Component;

public class GamePanel extends JPanel {
    private Player player;
    private ArrayList<PlayerBullet> bullets;

    // Constructor without the Player parameter
    public GamePanel() {
        setPreferredSize(new Dimension(800, 600)); // Set the panel size
        bullets = new ArrayList<PlayerBullet>();
    }

    // Setter method to assign the player later
    public void setPlayer(Player player) {
        this.player = player;
    }
    public void setBullet(PlayerBullet bullet){
        bullets.add(bullet);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (player != null) {
            Graphics2D g2 = (Graphics2D) g;
            player.draw(g2);
        }
        if(bullets!=null){
        for (Bullet bull : bullets) {
            Graphics2D g21 = (Graphics2D) g;
            bull.draw(g21);
        }
            
        }
    }
    //check if bullets are on screen or off and remove if they are off
    public void alterList(){
        for(int i=0; i<bullets.size();i++){
            if(bullets.get(i).checkDeleteBullet()) 
            {
                bullets.remove(i); 
                bullets.get(i).pauseTimers();
                i--;
            }
            
            
        }  
    }
}
