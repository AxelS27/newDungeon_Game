package com.jawa.tile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Tile {

    public BufferedImage image;
    public boolean collision = false;
    public Tile(String filePath) {
        try {
            image = ImageIO.read(getClass().getResourceAsStream(filePath));
        } catch (IOException e){
            e.printStackTrace();
        }
    }

}
