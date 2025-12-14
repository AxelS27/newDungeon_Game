package com.TPOW.entity.NPCs;

import com.TPOW.entity.Entity;
import com.TPOW.main.GamePanel;

import java.util.Random;

public class NPC_OldMan extends Entity {
    GamePanel gp;
    public NPC_OldMan(GamePanel gp){
        super(gp);
        this.gp = gp;
        
        direction = "down";
        speed = 1;

        getImage();
        setDialogue();
    }

    public void getImage(){
        up1 = setup("/NPCs/oldman_up_1");
        up2 = setup("/NPCs/oldman_up_2");
        down1 = setup("/NPCs/oldman_down_1");
        down2 = setup("/NPCs/oldman_down_2");
        left1 = setup("/NPCs/oldman_left_1");
        left2 = setup("/NPCs/oldman_left_2");
        right1 = setup("/NPCs/oldman_right_1");
        right2 = setup("/NPCs/oldman_right_2");
    }
    public void setDialogue() {
        int map = gp.currentMap;
        switch(map){
            case 1:
                dialogues[0] = "Hello adventurer!";
                dialogues[1] = "Ganbate";
                break;
            case 2:
                dialogues[0] = "Hello adventurer!";
                dialogues[1] = "Ganbate";
                break;
            case 3:
                dialogues[0] = "Hello adventurer!";
                dialogues[1] = "Ganbate";
                break;
            case 4:
                dialogues[0] = "Hello adventurer!";
                dialogues[1] = "Ganbate";
                break;
            case 5:
                dialogues[0] = "Hello adventurer!";
                dialogues[1] = "Ganbate";
                break;
        }

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
    public void speak(){
       super.speak(); 
    }


}
