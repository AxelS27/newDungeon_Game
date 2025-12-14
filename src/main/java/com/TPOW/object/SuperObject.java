package com.TPOW.object;
import com.TPOW.main.GamePanel;
import com.TPOW.main.UtilityTool;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
public class SuperObject {
    public BufferedImage image, image2, image3;
    public String name;
    public boolean collision = false;
    public int worldX, worldY;
    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
    public int solidAreaDefaultX = 0;
    public int solidAreaDefaultY = 0;
    UtilityTool uTool = new UtilityTool();
    public void draw(Graphics2D g2, GamePanel gp){
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;
        if (worldX + gp.tileSize> gp.player.worldX - gp.player.screenX &&
                worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                worldY - gp.tileSize < gp.player.worldY + gp.player.screenY ){
            g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
        }
    }
    public BufferedImage setup(String filePath, int width, int height){
        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;
        try{
            InputStream is = getClass().getResourceAsStream(filePath + ".png");
            if (is == null) return null;
            image = ImageIO.read(is);
            image = uTool.scaleImage(image, width, height);
        } catch(IOException e){
            e.printStackTrace();
        }
        return image;
    }
    public BufferedImage setup(String filePath){
        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;
        try{
            InputStream is = getClass().getResourceAsStream(filePath + ".png");
            if (is == null) return null;
            image = ImageIO.read(is);
            image = uTool.scaleImage(image, 48, 48);
        } catch(IOException e){
            e.printStackTrace();
        }
        return image;
    }
}