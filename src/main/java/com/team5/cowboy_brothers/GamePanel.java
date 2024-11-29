package com.team5.cowboy_brothers;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import javax.swing.JPanel;
import java.awt.Component;

public class GamePanel extends JPanel {
    private Player player;
    public ArrayList<Ground> grounds;
    private Flag flag;
    private HUD hud; // Reference to the HUD

    private ArrayList<PlayerBullet> bullets;
    private ArrayList<EnemyBullet> Enbullets; 
    private Enemy enemy;
    private ArrayList<Enemy> listOfEnemys;


    // Constructor without the Player parameter
    public GamePanel() {
        setPreferredSize(new Dimension(800, 600)); // Set the panel size
        grounds = new ArrayList<Ground>();
        bullets = new ArrayList<>();
        Enbullets = new ArrayList<>();
        listOfEnemys = new ArrayList<>();
        //setHUD(Cowboy_brothers.olly.Selectedlvl);
    }

    // Setter method to assign the player later
    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setEnemy(Enemy enemy){
        this.enemy=enemy;
    }
    public void AddPlayerBullet(PlayerBullet bullet){
        bullets.add(bullet);
    }
    public void addGround(Ground ground){
        grounds.add(ground);
    }
    
    public void setFlag(Flag f){
        flag = f;
    }
    public ArrayList<Ground> getGrounds() {
        return grounds;
    }
    public void setHUD(int levelNumber) {
        this.hud = new HUD(player, levelNumber); // Initialize HUD;
    }
    

    public void AddEnemyBullet(EnemyBullet bullet){
        Enbullets.add(bullet);
    }
    public void setEnemyList(){
        //add the level number's list of enemies
    }
    


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (player != null) {
            Graphics2D playerG2 = (Graphics2D) g;
            player.draw(playerG2);
        }
        if (enemy != null) {
            Graphics2D g2 = (Graphics2D) g;
            enemy.draw(g2);
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

        // Draw the HUD
        if (hud != null) {
            hud.draw(g);
        }
            
        }
    }
    //check if bullets are on screen or off and remove if they are off
    public void alterList(){
        for(int i=0; i<bullets.size();i++){
            if(bullets.get(i).checkDeleteBullet()) 
            {
                bullets.get(i).clearSprite();
                repaint();
                bullets.get(i).pauseTimers();
                bullets.remove(i); 
                i--;
            }
            
            
        }  
    }
    public void EnemyalterList(){
        for(int i=0; i<Enbullets.size();i++){
            if(Enbullets.get(i).checkDeleteBullet()) 
            {
                Enbullets.get(i).clearSprite();
                repaint();
                Enbullets.get(i).pauseTimers();
                Enbullets.remove(i); 
                i--;
            }
            
            
        }  
    }
    public void pauseTimers(){
        for (EnemyBullet Enbullet : Enbullets) {
            Enbullet.pauseTimers();
        }
        for(PlayerBullet bullet:bullets){
            bullet.pauseTimers();
        }
        for(Enemy enemys:listOfEnemys){
            enemys.pauseTimers();
        }
    }
    public void unpauseTimers(){
        for (EnemyBullet Enbullet : Enbullets) {
            Enbullet.unPauseTimers();
        }
        for(PlayerBullet bullet:bullets){
            bullet.unPauseTimers();
        }
        for(Enemy enemys:listOfEnemys){
            enemys.unPauseTimers();
        }
    }
    public void clearGameObjects() {
        // Clear bullets
        bullets.clear();
        
        // Clear enemies
        listOfEnemys.clear();
        grounds.clear(); // Clear ground objects
        // Clear other objects as necessary
        repaint();
    }
}
