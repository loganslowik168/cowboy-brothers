package com.team5.cowboy_brothers;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import javax.imageio.ImageIO;

/**
 *
 * @author matth
 */
public class PlayerBullet extends Bullet {
    
    private BufferedImage sprite;
    protected GamePanel targetPanel;

    public PlayerBullet(int startX, int startY, int direction, int speed, GamePanel TPB, int width, int height)  {
        super(startX,startY,direction,speed, TPB,"/sprites/PlayerBulletLeft.png","/sprites/PlayerBulletRight.png", width, height);
        targetPanel = TPB;
        //loadSprite("sprites/PlayerBullet.png");
    }
    
    
    private void loadSprites(String filePathL, String filePathR) {
    try {
        // Load left sprite from the classpath (using getResourceAsStream)
        InputStream spriteStreamL = getClass().getResourceAsStream(filePathL);
        if (spriteStreamL != null) {
            spriteL = ImageIO.read(spriteStreamL);
            // System.out.println("Left sprite loaded successfully.");
        } else {
            System.err.println("Error: Left sprite not found at " + filePathL);
        }
    } catch (IOException e) {
        System.err.println("Error loading left sprite: " + e.getMessage());
    }

    try {
        // Load right sprite from the classpath (using getResourceAsStream)
        InputStream spriteStreamR = getClass().getResourceAsStream(filePathR);
        if (spriteStreamR != null) {
            spriteR = ImageIO.read(spriteStreamR);
            // System.out.println("Right sprite loaded successfully.");
        } else {
            System.err.println("Error: Right sprite not found at " + filePathR);
        }
    } catch (IOException e) {
        System.err.println("Error loading right sprite: " + e.getMessage());
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
        Iterator<Enemy> iterator = targetPanel.listOfEnemys.iterator();
        while (iterator.hasNext()) {
            Enemy e = iterator.next();
            final int ENEMY_WIDTH = e.getWidth();
            final int ENEMY_HEIGHT = e.getHeight();
            //System.out.println("CHECKING " + this.x + "+" + this.width + ">" + e.GetX() + "&" + x + "<" + e.GetX() + "+" + ENEMY_WIDTH
              //  + "&" + y + "+" + height + ">" + e.GetY() + "&" + y + "<" + e.GetY() + "+" + ENEMY_HEIGHT);

            if (this.x + this.width > e.GetX() && this.x < e.GetX() + ENEMY_WIDTH &&
                this.y + this.height > e.GetY() && this.y < e.GetY() + ENEMY_HEIGHT) {
               // System.out.println("bullet hit enemy");

                // Dispose the enemy and remove it from the list
                
                iterator.remove();  // Removes the current enemy from the original list
                e.Dispose();  // Ensure this handles enemy cleanup
                // Dispose the bullet
                Dispose();  // Ensure this disposes of the bullet

                return true;  // Indicate that a collision happened
            }
        }

        if (targetPanel.boss != null)
        {
            Boss e=targetPanel.boss;
            final int ENEMY_WIDTH = e.getWidth();
            final int ENEMY_HEIGHT = e.getHeight();
            if (this.x + this.width > e.GetX() && this.x < e.GetX() + ENEMY_WIDTH &&
                        this.y + this.height > e.GetY() && this.y < e.GetY() + ENEMY_HEIGHT) {
             //   System.out.println("bullet hit boss!!");
                e.Hurt(1);
                Dispose();
                return true;
            }
        }
        
        return false;
    }
    
}
