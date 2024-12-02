package com.team5.cowboy_brothers;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class GamePanel extends JPanel {
    public Player player;
    public ArrayList<Ground> grounds;
    public Flag flag;
    private HUD hud; // Reference to the HUD
    private BufferedImage backgroundImage;
    private ArrayList<PlayerBullet> bullets;
    private ArrayList<EnemyBullet> Enbullets; 
    protected ArrayList<Enemy> listOfEnemys;
    
    private ArrayList<Bomb> bombs;
    private BossSaloon Sal;
    protected Boss boss;
    
    //public ArrayList<BoundingBox> bbs;


    // Constructor without the Player parameter
    public GamePanel() {
        setPreferredSize(new Dimension(800, 600)); // Set the panel size
        grounds = new ArrayList<Ground>();
        bullets = new ArrayList<>();
        Enbullets = new ArrayList<>();
        listOfEnemys = new ArrayList<>();
        bombs = new ArrayList<>();
        loadBackgroundImage();

    }

    // Setter method to assign the player later
    public void setPlayer(Player player) {
        this.player = player;
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

    public void setHUD(HUD h) {
        this.hud = h;
    }
    public ArrayList<Ground> getGrounds() {
        return grounds;
    }
    private void loadBackgroundImage() {
        try {
            backgroundImage = ImageIO.read(new File("sprites/DesertBackground.png")); // Adjust the path as needed
        } catch (IOException e) {
            System.err.println("Error loading background image: " + e.getMessage());
        }
    }

    public void AddBullet(EnemyBullet bullet){
        Enbullets.add(bullet);
    }
    public void setEnemyList(Enemy enemy){
        listOfEnemys.add(enemy);
    }
    public void SetSaloon(BossSaloon saloon)
    {
        Sal = saloon;
    }
    public void SetBoss(Boss b)
    {
        boss = b;
    }
    public void AddBomb(Bomb b)
    {
        bombs.add(b);
    }
    
    public void clearLevel(){
        for(int i = grounds.size()-1;i>=0;i--){
            grounds.remove(i);
        }
        for(int z = listOfEnemys.size()-1;z>=0;z--){
            listOfEnemys.remove(z);
        }
    }
    


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw the background image
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this); // Draw the image to fill the panel
        }
        if (Sal != null) {Sal.draw(g);}
        if (player != null) {
            Graphics2D playerG2 = (Graphics2D) g;
            player.draw(playerG2);
        }
        if(listOfEnemys!=null){
            for(Enemy enemy : listOfEnemys){
                Graphics2D g2 = (Graphics2D) g;
                enemy.draw(g2);
            }
        }
        if(bullets!=null){
        for (Bullet bull : bullets) {
            Graphics2D bulletG2 = (Graphics2D) g;
            bull.draw(bulletG2);
            }
        }
        if(Enbullets!=null){
        for (Bullet bull : Enbullets) {
            Graphics2D EnbulletG2 = (Graphics2D) g;
            bull.draw(EnbulletG2);
            }
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
            
        
        
        if (boss != null) {boss.draw(g);}
        if (bombs!=null)
        {
            for (Bomb b : bombs){
                Graphics2D bombG2 = (Graphics2D) g;
                b.draw(bombG2);
            }
        }
        

       /* if (bbs!=null)
        {
            for (BoundingBox bb : bbs){
                Graphics2D bbG2 = (Graphics2D) g;
                bb.draw(bbG2);
            }
        }*/

    }
    //check if bullets are on screen or off and remove if they are off
    public void alterList(){
        for(int i=0; i<bullets.size();i++){
            if(bullets.get(i).checkDeleteBullet()) 
            {
                bullets.get(i).pauseTimers();
                bullets.remove(i); 
                i--;
            }
        }
        for(int x=0;x<Enbullets.size();x++){
            if(Enbullets.get(x).checkDeleteBullet()){
                Enbullets.get(x).pauseTimers();
                Enbullets.remove(x);
                x--;
            }
        }
    }
    public void bulletDispose(){
        for(EnemyBullet enb:Enbullets){
            enb.Dispose();
        }
    }
    public void alterEnemyList(){
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
    public BossSaloon GetSaloon()
    {
        return Sal;
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
    public void releaseAllKeysUnfocused(KeyHandler kh){
        if(!(this.hasFocus())){
            kh.MenuRelease();
            //Cowboy_brothers.olly.VisibleMenu.RecreateKeyHandler();
        }
    }
}
