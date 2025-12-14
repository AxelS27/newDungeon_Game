package com.TPOW.entity.monster;

import com.TPOW.entity.Entity;
import com.TPOW.main.GamePanel;

import java.util.Random;

public class MON_GreenSlime extends Entity {
    GamePanel gp;
    public MON_GreenSlime(GamePanel gp){
        super(gp);

        this.gp = gp;

        type = 2;

        direction = "down";

        name = "Green Slime";
        speed = 2;
        maxLife = 20;
        life = maxLife;

        solidArea.x = 3;
        solidArea.y = 18;
        solidArea.width = 42;
        solidArea.height = 30;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        getImage();
    }

    public void damageReaction(){
        actionLockCounter = 0;
        direction = gp.player.direction;
    }

    public void getImage(){
        up1 = setup("/monsters/greenslime_down_1");
        up2 = setup("/monsters/greenslime_down_2");
        down1 = setup("/monsters/greenslime_down_1");
        down2 = setup("/monsters/greenslime_down_2");
        left1 = setup("/monsters/greenslime_down_1");
        left2 = setup("/monsters/greenslime_down_2");
        right1 = setup("/monsters/greenslime_down_1");
        right2 = setup("/monsters/greenslime_down_2");
    }

    public void setAction() {
        actionLockCounter++;

        if (actionLockCounter <= 10) return;

        int chaseDistance = 5 * gp.tileSize;

        int playerX = gp.player.worldX / gp.tileSize;
        int playerY = gp.player.worldY / gp.tileSize;
        int monsterX = worldX / gp.tileSize;
        int monsterY = worldY / gp.tileSize;

        int deltaX = playerX - monsterX;
        int deltaY = playerY - monsterY;
        int distance = Math.abs(deltaX) + Math.abs(deltaY);

        if (distance <= chaseDistance) {
            if (Math.abs(deltaX) > Math.abs(deltaY)) {
                if (deltaX > 0) direction = "right";
                else direction = "left";
            } else {
                if (deltaY > 0) direction = "down";
                else direction = "up";
            }
        } else {
            Random random = new Random();
            int i = random.nextInt(100) + 1;
            if (i <= 25) direction = "up";
            else if (i <= 50) direction = "down";
            else if (i <= 75) direction = "left";
            else direction = "right";
        }

        actionLockCounter = 0;
    }


}
