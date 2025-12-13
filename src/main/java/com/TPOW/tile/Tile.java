package com.TPOW.tile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Tile {

    public BufferedImage image;
    public boolean collision = false;
    public Tile(String imageName) {
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/tiles/"+imageName+".png"));
        } catch (IOException e){
            e.printStackTrace();
        }
    }

}
