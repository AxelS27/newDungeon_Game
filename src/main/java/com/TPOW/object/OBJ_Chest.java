package com.TPOW.object;

import com.TPOW.main.GamePanel;

public class OBJ_Chest extends SuperObject {
    GamePanel gp;
    public OBJ_Chest(GamePanel gp){
        this.gp = gp;
        name = "Chest";
        image = setup("/objects/chest", gp.tileSize, gp.tileSize);
    }
}