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
        speed = 1;
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

    public void setAction(){

        actionLockCounter++;

        if (actionLockCounter != 120) return;

        Random random = new Random();
        int i = random.nextInt(100)+1;
        if (i<=25){
            direction = "up";
        }
        else if (i > 25 && i <= 50){
            direction = "down";
        }
        else if (i > 50 && i <= 75){
            direction = "left";
        }
        else if (i > 75 && i <= 100){
            direction = "right";
        }

        actionLockCounter = 0;
    }


}
