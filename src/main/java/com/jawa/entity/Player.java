package com.jawa.entity;

import com.jawa.main.GamePanel;
import com.jawa.main.KeyHandler;
import com.jawa.main.UtilityTool;
import com.jawa.object.OBJ_Door;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity {
    GamePanel gp;
    KeyHandler keyH;

    public final int screenX;
    public final int screenY;

    public boolean attackCanceled = false;

    public int hasKey = 5;

    public Player(GamePanel gp, KeyHandler keyH) {

        super(gp);

        this.gp = gp;
        this.keyH = keyH;



        screenX = gp.screenWidth/2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2 - (gp.tileSize/2);

        solidArea = new Rectangle(8, 16, 32, 32);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        attackArea.width = 36;
        attackArea.height = 36;

        setDefaultValues();
        getPlayerImage();
        getPlayerAttackImage();
    }

    public void setDefaultValues(){
        worldX = gp.tileSize * 23;
        worldY = gp.tileSize * 21;
        speed = 4;
        direction = "down";

        maxLife = 6;
        life = maxLife;
    }

    public void getPlayerImage(){
        up1 = setup("/player/boy_up_1", gp.tileSize, gp.tileSize);
        up2 = setup("/player/boy_up_2", gp.tileSize, gp.tileSize);
        down1 = setup("/player/boy_down_1", gp.tileSize, gp.tileSize);
        down2 = setup("/player/boy_down_2", gp.tileSize, gp.tileSize);
        left1 = setup("/player/boy_left_1", gp.tileSize, gp.tileSize);
        left2 = setup("/player/boy_left_2", gp.tileSize, gp.tileSize);
        right1 = setup("/player/boy_right_1", gp.tileSize, gp.tileSize);
        right2 = setup("/player/boy_right_2", gp.tileSize, gp.tileSize);
    }

    public void getPlayerAttackImage(){
        attackUp1 = setup("/player/boy_attack_up_1", gp.tileSize, gp.tileSize*2);
        attackUp2 = setup("/player/boy_attack_up_2", gp.tileSize, gp.tileSize*2);
        attackDown1 = setup("/player/boy_attack_down_1", gp.tileSize, gp.tileSize*2);
        attackDown2 = setup("/player/boy_attack_down_2", gp.tileSize, gp.tileSize*2);
        attackLeft1 = setup("/player/boy_attack_left_1", gp.tileSize*2, gp.tileSize);
        attackLeft2 = setup("/player/boy_attack_left_2", gp.tileSize*2, gp.tileSize);
        attackRight1 = setup("/player/boy_attack_right_1", gp.tileSize*2, gp.tileSize);
        attackRight2 = setup("/player/boy_attack_right_2", gp.tileSize*2, gp.tileSize);
    }

    public void update(){

        if (attacking){
            attacking();
        }

        else if (keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed || keyH.ePressed){
            if (keyH.upPressed){
                direction = "up";
            }
            else if (keyH.downPressed){
                direction = "down";
            }
            else if (keyH.leftPressed){
                direction = "left";
            }
            else if (keyH.rightPressed){
                direction = "right";
            }

            collisionOn = false;
            gp.cChecker.checkTile(this);

            int objectIndex = gp.cChecker.checkObject(this, true);
            pickUpObject(objectIndex);

            int npcIndex =  gp.cChecker.checkEntity(this, gp.npc);
            interactNPC(npcIndex);

            int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
            contactMonster(monsterIndex);

            gp.eventHandler.checkEvent();


            if (!collisionOn && !keyH.ePressed){
                switch(direction){
                    case "up": worldY -= speed; break;
                    case "down": worldY += speed; break;
                    case "left": worldX -= speed; break;
                    case "right": worldX += speed; break;
                }
            }

            if (keyH.ePressed && !attackCanceled){
                gp.playSE(7);
                attacking = true;
                spriteCounter = 0;
            }

            attackCanceled = false;

            gp.keyH.ePressed = false;
            spriteCounter++;
            if (spriteCounter > 12){
                if (spriteNum == 1){
                    spriteNum = 2;
                } else if (spriteNum == 2){
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }
        if (invincible){
            invincibleCounter++;
            if (invincibleCounter > 60){
                invincible = false;
                invincibleCounter = 0;
            }
        }

    }

    public void attacking(){
        spriteCounter++;
        if (spriteCounter <= 5){
            spriteNum = 1;
        } else if (spriteCounter > 5 && spriteCounter <= 25){
            spriteNum = 2;
            int currentWorldX = worldX;
            int currentWorldY = worldY;
            int solidAreaWidth = solidArea.width;
            int solidAreaHeight = solidArea.height;

            switch (direction){
                case "up": worldY -= attackArea.height; break;
                case "down": worldY += attackArea.height; break;
                case "left": worldX -= attackArea.width; break;
                case "right": worldX += attackArea.width; break;
            }

            solidArea.width = attackArea.width;
            solidAreaHeight = attackArea.height;

            int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
            damageMonster(monsterIndex);

            worldX = currentWorldX;
            worldY = currentWorldY;
            solidArea.width = solidAreaWidth;
            solidArea.height = solidAreaHeight;


        } else if (spriteCounter > 25){
            spriteNum = 1;
            spriteCounter = 0;
            attacking = false;
        }
    }

    public void contactMonster(int i){
        if (i != -1){
            if (!invincible){
                gp.playSE(6);
                life -= 1;
                invincible = true;
            }
        }
    }

    public void damageMonster(int i){
        if (i != -1){
            if (!gp.monster[i].invincible){
                gp.playSE(5);
                gp.monster[i].life -= 1;
                gp.monster[i].invincible = true;
                gp.monster[i].damageReaction();

                if (gp.monster[i].life <= 0){
                    gp.monster[i].dying = true;
                }
            }
        }
    }

    public void pickUpObject(int i){
        if (i != -1){
            String objectName = gp.obj[i].name;

            switch(objectName){
                case "Key":

                    gp.playSE(1);

                    hasKey++;
                    gp.obj[i] = null;
                    gp.ui.showMessage("You got a key!");
                    break;
                case "Door":
                    if (hasKey > 0){

                        gp.playSE(3);

                        gp.obj[i] = null;
                        hasKey--;
                        gp.ui.showMessage("You opened the door!");
                    } else {
                        gp.ui.showMessage("You need a key!");
                    }
                    break;
                case "Boots":

                    gp.playSE(2);

                    speed += 4;
                    gp.obj[i] = null;
                    gp.ui.showMessage("Speed up!");
                    break;
                case "Chest":
                    gp.playSE(4);
                    gp.ui.gameFinished = true;
                    gp.chestOpened = true;
                    gp.obj[i] = null;
                    break;
            }
        }
    }

    public void interactNPC(int i){

        if (gp.keyH.ePressed){
            if (i != -1){
                attackCanceled = true;
                gp.gameState = gp.dialogueState;
                gp.npc[i].speak();

            } else {
                gp.playSE(7);
                attacking = true;
            }
        }


    }

    public void draw(Graphics2D g2) {
        BufferedImage image = null;

        int tempScreenX = screenX;
        int tempScreenY = screenY;

        switch (direction) {
            case "up":
                if (attacking){
                    tempScreenY = screenY-gp.tileSize;
                    if (spriteNum == 1) {  image = attackUp1; }
                    if (spriteNum == 2) { image = attackUp2; }
                } else {
                    if (spriteNum == 1) { image = up1; }
                    if (spriteNum == 2) { image = up2; }
                }
                break;
            case "down":
                if (attacking){
                    if (spriteNum == 1) { image = attackDown1; }
                    if (spriteNum == 2) { image = attackDown2; }
                } else{
                    if (spriteNum == 1) { image = down1; }
                    if (spriteNum == 2) { image = down2; }
                }
                break;
            case "left":
                if (attacking){
                    tempScreenX = screenX-gp.tileSize;
                    if (spriteNum == 1) { image = attackLeft1; }
                    if (spriteNum == 2) { image = attackLeft2; }
                } else {
                    if (spriteNum == 1) { image = left1; }
                    if (spriteNum == 2) { image = left2; }
                }
                break;
            case "right":
                if (attacking){
                    if (spriteNum == 1) { image = attackRight1; }
                    if (spriteNum == 2) { image = attackRight2; }
                } else {
                    if (spriteNum == 1) { image = right1; }
                    if (spriteNum == 2) { image = right2; }
                }
                break;
        }

        if (invincible){
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,  0.3f));
        }

        g2.drawImage(image, tempScreenX, tempScreenY, null);
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,  1f));

    }
}
