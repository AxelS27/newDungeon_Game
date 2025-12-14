package com.TPOW.object;

import com.TPOW.main.GamePanel;

public class OBJ_Key extends SuperObject {
    GamePanel gp;
    public OBJ_Key(GamePanel gp){
        this.gp = gp;
        name = "Key";
        image = setup("/objects/key", gp.tileSize, gp.tileSize);
    }
}