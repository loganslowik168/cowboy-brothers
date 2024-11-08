
package com.team5.cowboy_brothers;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class SpriteSheet {
    
    public String path;
    public int width;
    public int height;
    
    public int pixels;
    
    // will load sprite sheet image
    public SpriteSheet(String path) {
    BufferedImage image = null;
    try {
            image = ImageIO.read(SpriteSheet.class.getResourceAsStream(path));
        } catch (IOException ex) {
            Logger.getLogger(SpriteSheet.class.getName()).log(Level.SEVERE, null, ex);
        }
  // In case the image stays null after the above code is ran
 /*private void If(image = null) {
        return;
    }*/
    
    
    }
}

