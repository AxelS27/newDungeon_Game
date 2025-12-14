package com.TPOW.object;

import com.TPOW.main.GamePanel;

public class OBJ_Boots extends SuperObject {
    GamePanel gp;
    public OBJ_Boots(GamePanel gp){
        this.gp = gp;
        name = "Boots";
        image = setup("/objects/boots", gp.tileSize, gp.tileSize);
    }
}