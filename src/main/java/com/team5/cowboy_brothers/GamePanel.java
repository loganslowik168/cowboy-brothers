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

public class GamePanel extends JPanel {
    private Player player;
    private ArrayList<Bullet> bullets;
    private ArrayList<Ground> grounds;
    private Flag flag;

    // Constructor without the Player parameter
    public GamePanel() {
        setPreferredSize(new Dimension(800, 600)); // Set the panel size
        bullets = new ArrayList<Bullet>();
        grounds = new ArrayList<Ground>();
    }

    // Setter method to assign the player later
    public void setPlayer(Player player) {
        this.player = player;
    }
    public void addBullet(Bullet bullet){
        bullets.add(bullet);
    }
    public void addGround(Ground ground){
        grounds.add(ground);
    }
    public void setFlag(Flag f){
        flag = f;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (player != null) {
            Graphics2D playerG2 = (Graphics2D) g;
            player.draw(playerG2);
        }
        if(bullets!=null){
        for (Bullet bull : bullets) {
            Graphics2D bulletG2 = (Graphics2D) g;
            bull.draw(bulletG2);
        }
        if(grounds!=null){
            for (Ground gnd : grounds){
                Graphics2D groundG2 = (Graphics2D) g;
                gnd.draw(groundG2);
            }
        }
        if (flag!=null)
        {
            Graphics2D flagG2 = (Graphics2D) g;
            flag.draw(flagG2);
        }
        }
    }
    protected void paintBullet(Graphics g){
        System.out.println("Paint Bullet");
        super.paintComponent(g);
        if(bullets!=null){
        Graphics2D g21 = (Graphics2D) g;
        //bullets.draw(g21);
        }
    }
}
