package com.TPOW.object;

import com.TPOW.main.GamePanel;

public class OBJ_Door extends SuperObject {
    GamePanel gp;
    public OBJ_Door(GamePanel gp){
        this.gp = gp;
        name = "Door";
        image = setup("/objects/door", gp.tileSize, gp.tileSize);
        collision = true;
    }
}