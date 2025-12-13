package com.TPOW.main;

import java.awt.*;

public class EventHandler {
    GamePanel gp;
    Rectangle eventRect;
    int eventRectDefaultX, eventRectDefaultY;


    public EventHandler(GamePanel gp){
        this.gp = gp;

        eventRect = new Rectangle();
        eventRect.x = 23;
        eventRect.y = 23;
        eventRect.width = 2;
        eventRect.height = 2;
        eventRectDefaultX = eventRect.x;
        eventRectDefaultY = eventRect.y;

    }

    void teleportTo (int x, int y, int xTarget, int yTarget){
        if (hit(x, y, "up") || hit(x, y, "down") || hit(x, y, "left")|| hit(x, y, "right")){
            teleport(gp.dialogueState, xTarget, yTarget);
        }
    }

    public void checkEvent(){
        int map = gp.currentMap;
        switch(map){
            case 1:
                teleportTo(8, 8, 9, 18);
                teleportTo(8, 18, 9, 8);
                teleportTo(8, 28, 37,22);
                teleportTo(36, 22, 9, 28);
                teleportTo(22, 27, 13, 39);
                teleportTo(11, 39, 22, 26);
                break;

        }
//        if (hit(27, 16, "right")) {
//            damagePit(gp.dialogueState);
//        }
//        if (hit(27, 16, "right")){
//            teleport(gp.dialogueState);
//        }
//        if (hit(23, 12, "up")){
//            healingPool(gp.dialogueState);
//        }
    }

    public void teleport(int gameState, int x, int y){
        gp.gameState = gameState;
        gp.ui.currentDialogue = "Teleport";
        gp.player.worldX = gp.tileSize*x;
        gp.player.worldY = gp.tileSize*y;
    }

    public void healingPool(int gameState){
        if (gp.keyH.ePressed){
            gp.gameState = gameState;
            gp.player.attackCanceled = true;
            gp.ui.currentDialogue = "You drink the water\nYou life has been recovered";
            gp.player.life = gp.player.maxLife;
        }
    }

    public boolean hit(int eventCol, int eventRow, String reqDirection){

        boolean hit = false;

        gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
        gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
        eventRect.x = eventCol*gp.tileSize + eventRect.x;
        eventRect.y = eventRow*gp.tileSize + eventRect.y;

        if (gp.player.solidArea.intersects(eventRect)){
            if (gp.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")){
                hit = true;
            }
        }

        gp.player.solidArea.x = gp.player.solidAreaDefaultX;
        gp.player.solidArea.y = gp.player.solidAreaDefaultY;
        eventRect.x = eventRectDefaultX;
        eventRect.y = eventRectDefaultY;

        return hit;
    }

    public void damagePit(int gameState){
        gp.gameState = gameState;
        gp.ui.currentDialogue = "You fall into a pit!";
        gp.player.life -= 1;
    }

}
