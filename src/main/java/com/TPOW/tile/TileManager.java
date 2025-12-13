package com.TPOW.tile;

import com.TPOW.main.GamePanel;
import com.TPOW.main.UtilityTool;

import java.awt.*;
import java.io.*;

public class TileManager {

    GamePanel gp;
    public Tile[] tile;
    public int[][] mapTileNum;


    public TileManager(GamePanel gp) {
        this.gp = gp;
        tile = new Tile[50];
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
        getTileImage();
    }

    public void getTileImage(){

        setup(0, "earth", false);
        setup(1, "floor", false);
        setup(2, "hut", true);
        int[] indexes1 = {3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
        for(int i = 0; i < indexes1.length; i++){
            String roadIndex = "road" + String.format("%02d", i);
            setup(indexes1[i], roadIndex, false);
        }
        setup(16, "sand", false);
        setup(17, "snow", false);
        setup(18, "table", true);
        setup(19, "tree", true);
        setup(20, "wall", true);
        int[] indexes2 = {21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34};
        for(int i = 0; i < indexes2.length; i++){
            String waterIndex = "water" + String.format("%02d", i);
            setup(indexes2[i], waterIndex, true);
        }

    }

    public void setup(int index, String imageName, boolean collision){
        UtilityTool uTool = new UtilityTool();

        tile[index] = new Tile(imageName);
        tile[index].image = uTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
        tile[index].collision = collision;

    }

    public void loadMap(String filePath){
        try {
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;
            while (col < gp.maxWorldCol && row < gp.maxWorldRow) {
                String line = br.readLine();
                while (col < gp.maxWorldCol){
                    String[] numbers = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[col][row] = num;
                    col++;
                }
                if (col == gp.maxWorldCol){
                    col = 0;
                    row++;
                }
            }
            br.close();

        } catch (Exception e){
            e.printStackTrace();
        }

    }

    public void draw(Graphics2D g2){
       int worldCol = 0;
       int worldRow = 0;

       while (worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {

           int tileNum = mapTileNum[worldCol][worldRow];

           int worldX = worldCol * gp.tileSize;
           int worldY = worldRow * gp.tileSize;

           int screenX = worldX - gp.player.worldX + gp.player.screenX;
           int screenY = worldY - gp.player.worldY + gp.player.screenY;

           if (worldX + gp.tileSize> gp.player.worldX - gp.player.screenX &&
               worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
               worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
               worldY - gp.tileSize < gp.player.worldY + gp.player.screenY ){
                g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
           }
           worldCol++;
           if (worldCol == gp.maxWorldCol){
               worldCol = 0;
               worldRow++;
           }
       }

    }
}
