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
        int i=0;
        switch(map){
            case 1:
                dialogues[i++] = "Guten Morgen Adventurer!\nHO HO HO";
                dialogues[i++] = "Welcome to The Path Of  Winter";
                dialogues[i++] = "Here you have to find some key to open\nup the door";
                dialogues[i++] = "So you can make a progress to the\nnext level";
                dialogues[i++] = "Good luck adventurer!\nHO HO HO";
                break;
            case 2:
                dialogues[i++] = "The fate has brought us together again\nadventurer! HO HO HO";
                dialogues[i++] = "Sometime the pit can take you into\nloneliness";
                dialogues[i++] = "HO HO HO";

                break;
            case 3:
                dialogues[i++] = "HO HO HO,  Hallo Adventurer!\nwe meet again";
                dialogues[i++] = "There is some items that can boost\nyour attributes!";
                dialogues[i++] = "items = [ ]\nitems.append('boots')\nfor i in items:\n  print(i) #HO HO HO";
                break;
            case 4:
                dialogues[i++] = "Aren't you suprised,\nwhy am i every where?";
                dialogues[i++] = "HO HO HO,  anyway i think there is\na strange kind of slime ahead";
                dialogues[i++] = "Stay save Adventurer!\nHO HO HO";
                break;
            case 5:
                dialogues[i++] = "01012001";
                dialogues[i++] = "01001011";
                dialogues[i++] = "01001101";
                dialogues[i++] = "01001010";
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
