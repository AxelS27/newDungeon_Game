package com.TPOW.main;

import com.TPOW.entity.NPCs.NPC_OldMan;
import com.TPOW.entity.monster.MON_GreenSlime;
import com.TPOW.object.OBJ_Boots;
import com.TPOW.object.OBJ_Chest;
import com.TPOW.object.OBJ_Door;
import com.TPOW.object.OBJ_Key;

public class AssetSetter {
    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject() {
        int map = gp.currentMap;
        for (int i = 0; i < gp.obj.length; i++) {
            gp.obj[i] = null;
        }
        int i = 0;
        switch (map) {
            case 1:
                
                gp.player.worldX = 38 * gp.tileSize;
                gp.player.worldY = 13 * gp.tileSize;
                
                gp.obj[i] = new OBJ_Key(gp);
                gp.obj[i].worldX = 9 * gp.tileSize;
                gp.obj[i++].worldY = 19 * gp.tileSize;
                
                gp.obj[i] = new OBJ_Key(gp);
                gp.obj[i].worldX = 7 * gp.tileSize;
                gp.obj[i++].worldY = 31 * gp.tileSize;
                
                gp.obj[i] = new OBJ_Chest(gp);
                gp.obj[i].worldX = 44 * gp.tileSize;
                gp.obj[i++].worldY = 39 * gp.tileSize;
                
                gp.obj[i] = new OBJ_Door(gp);
                gp.obj[i].worldX = 31 * gp.tileSize;
                gp.obj[i++].worldY = 32 * gp.tileSize;
                
                gp.obj[i] = new OBJ_Door(gp);
                gp.obj[i].worldX = 42 * gp.tileSize;
                gp.obj[i++].worldY = 39 * gp.tileSize;
                
                break;
            case 2:

                gp.player.worldX = 37 * gp.tileSize;
                gp.player.worldY = 12 * gp.tileSize;

                gp.obj[i] = new OBJ_Key(gp);
                gp.obj[i].worldX = 33 * gp.tileSize;
                gp.obj[i++].worldY = 28 * gp.tileSize;

                gp.obj[i] = new OBJ_Chest(gp);
                gp.obj[i].worldX = 33 * gp.tileSize;
                gp.obj[i++].worldY = 39 * gp.tileSize;

                gp.obj[i] = new OBJ_Door(gp);
                gp.obj[i].worldX = 30 * gp.tileSize;
                gp.obj[i++].worldY = 39 * gp.tileSize;

                break;
                
            case 3:
                
                gp.player.worldX = 37 * gp.tileSize;
                gp.player.worldY = 11 * gp.tileSize;
                
                gp.obj[i] = new OBJ_Key(gp);
                gp.obj[i].worldX = 30 * gp.tileSize;
                gp.obj[i++].worldY = 36 * gp.tileSize;
                
                gp.obj[i] = new OBJ_Key(gp);
                gp.obj[i].worldX = 25 * gp.tileSize;
                gp.obj[i++].worldY = 9 * gp.tileSize;
                
                gp.obj[i] = new OBJ_Chest(gp);
                gp.obj[i].worldX = 9 * gp.tileSize;
                gp.obj[i++].worldY = 37 * gp.tileSize;
                
                gp.obj[i] = new OBJ_Door(gp);
                gp.obj[i].worldX = 18 * gp.tileSize;
                gp.obj[i++].worldY = 37 * gp.tileSize;
                
                gp.obj[i] = new OBJ_Door(gp);
                gp.obj[i].worldX = 10 * gp.tileSize;
                gp.obj[i++].worldY = 37 * gp.tileSize;
                
                gp.obj[i] = new OBJ_Boots(gp);
                gp.obj[i].worldX = 22 * gp.tileSize;
                gp.obj[i++].worldY = 22 * gp.tileSize;
                break;
                
            case 4:
                gp.player.worldX = 40 * gp.tileSize;
                gp.player.worldY = 30 * gp.tileSize;
                
                gp.obj[i] = new OBJ_Key(gp);
                gp.obj[i].worldX = 9 * gp.tileSize;
                gp.obj[i++].worldY = 36 * gp.tileSize;
                
                gp.obj[i] = new OBJ_Key(gp);
                gp.obj[i].worldX = 9 * gp.tileSize;
                gp.obj[i++].worldY = 35 * gp.tileSize;
                
                gp.obj[i] = new OBJ_Key(gp);
                gp.obj[i].worldX = 9 * gp.tileSize;
                gp.obj[i++].worldY = 9 * gp.tileSize;
                
                gp.obj[i] = new OBJ_Key(gp);
                gp.obj[i].worldX = 38 * gp.tileSize;
                gp.obj[i++].worldY = 15 * gp.tileSize;
                
                gp.obj[i] = new OBJ_Door(gp);
                gp.obj[i].worldX = 41 * gp.tileSize;
                gp.obj[i++].worldY = 42 * gp.tileSize;
                
                gp.obj[i] = new OBJ_Door(gp);
                gp.obj[i].worldX = 42 * gp.tileSize;
                gp.obj[i++].worldY = 42 * gp.tileSize;
                
                gp.obj[i] = new OBJ_Door(gp);
                gp.obj[i].worldX = 43 * gp.tileSize;
                gp.obj[i++].worldY = 42 * gp.tileSize;
                
                gp.obj[0] = new OBJ_Chest(gp);
                gp.obj[0].worldX = 44 * gp.tileSize;
                gp.obj[0].worldY = 44 * gp.tileSize;
                
                gp.obj[i] = new OBJ_Boots(gp);
                gp.obj[i].worldX = 8 * gp.tileSize;
                gp.obj[i++].worldY = 22 * gp.tileSize;
                break;
                
            case 5:
                gp.player.worldX = 7 * gp.tileSize;
                gp.player.worldY = 41 * gp.tileSize;

                gp.obj[i] = new OBJ_Key(gp);
                gp.obj[i].worldX = 43 * gp.tileSize;
                gp.obj[i++].worldY = 43 * gp.tileSize;

                gp.obj[i] = new OBJ_Key(gp);
                gp.obj[i].worldX = 8 * gp.tileSize;
                gp.obj[i++].worldY = 14 * gp.tileSize;

                gp.obj[i] = new OBJ_Door(gp);
                gp.obj[i].worldX = 29 * gp.tileSize;
                gp.obj[i++].worldY = 34 * gp.tileSize;

                gp.obj[i] = new OBJ_Door(gp);
                gp.obj[i].worldX = 29 * gp.tileSize;
                gp.obj[i++].worldY = 35 * gp.tileSize;

                gp.obj[i] = new OBJ_Boots(gp);
                gp.obj[i].worldX = 29 * gp.tileSize;
                gp.obj[i++].worldY = 25 * gp.tileSize;

                gp.obj[i] = new OBJ_Chest(gp);
                gp.obj[i].worldX = 29 * gp.tileSize;
                gp.obj[i++].worldY = 39 * gp.tileSize;

                break;
           
        }
    }

    public void setNPC() {
        int map = gp.currentMap;
        for (int i = 0; i < gp.npc.length; i++) {
            gp.npc[i] = null;
        }
        int i=0;
        switch (map) {
            
            case 1:
                
                gp.npc[i] = new NPC_OldMan(gp);
                gp.npc[i].worldX = gp.tileSize * 18;
                gp.npc[i++].worldY = gp.tileSize * 36;
                break;
            
            case 2:

                gp.npc[i] = new NPC_OldMan(gp);
                gp.npc[i].worldX = gp.tileSize * 13;
                gp.npc[i++].worldY = gp.tileSize * 18;
                break;
                
            case 3:
                
                gp.npc[i] = new NPC_OldMan(gp);
                gp.npc[i].worldX = gp.tileSize * 19;
                gp.npc[i++].worldY = gp.tileSize * 24;
                break;
                
            case 4:
                
                gp.npc[i] = new NPC_OldMan(gp);
                gp.npc[i].worldX = gp.tileSize * 24;
                gp.npc[i++].worldY = gp.tileSize * 23;
                break;
                
            case 5:
                gp.npc[i] = new NPC_OldMan(gp);
                gp.npc[i].worldX = gp.tileSize * 9;
                gp.npc[i++].worldY = gp.tileSize * 32;
                break;
            
        }
    }

    public void setMonster() {
        int map = gp.currentMap;
        for (int i = 0; i < gp.monster.length; i++) {
            gp.monster[i] = null;
        }
        int i=0;
        switch (map) {
            
            case 1:  
                gp.monster[i] = new MON_GreenSlime(gp);
                gp.monster[i].worldX = gp.tileSize * 27;
                gp.monster[i++].worldY = gp.tileSize * 27;
                gp.monster[i] = new MON_GreenSlime(gp);
                gp.monster[i].worldX = gp.tileSize * 22;
                gp.monster[i++].worldY = gp.tileSize * 17;
                gp.monster[i] = new MON_GreenSlime(gp);
                gp.monster[i].worldX = gp.tileSize * 13;
                gp.monster[i++].worldY = gp.tileSize * 14;
                gp.monster[i] = new MON_GreenSlime(gp);
                gp.monster[i].worldX = gp.tileSize * 15;
                gp.monster[i++].worldY = gp.tileSize * 34;
                gp.monster[i] = new MON_GreenSlime(gp);
                gp.monster[i].worldX = gp.tileSize * 36;
                gp.monster[i++].worldY = gp.tileSize * 36;
                break;
            case 2:
                gp.monster[i] = new MON_GreenSlime(gp);
                gp.monster[i].worldX = gp.tileSize * 42;
                gp.monster[i++].worldY = gp.tileSize * 28;
                gp.monster[i] = new MON_GreenSlime(gp);
                gp.monster[i].worldX = gp.tileSize * 35;
                gp.monster[i++].worldY = gp.tileSize * 28;
                break;
            case 3:
                gp.monster[i] = new MON_GreenSlime(gp);
                gp.monster[i].worldX = gp.tileSize * 35;
                gp.monster[i++].worldY = gp.tileSize * 26;
                gp.monster[i] = new MON_GreenSlime(gp);
                gp.monster[i].worldX = gp.tileSize * 40;
                gp.monster[i++].worldY = gp.tileSize * 26;
                gp.monster[i] = new MON_GreenSlime(gp);
                gp.monster[i].worldX = gp.tileSize * 35;
                gp.monster[i++].worldY = gp.tileSize * 38;
                gp.monster[i] = new MON_GreenSlime(gp);
                gp.monster[i].worldX = gp.tileSize * 27;
                gp.monster[i++].worldY = gp.tileSize * 17;
                gp.monster[i] = new MON_GreenSlime(gp);
                gp.monster[i].worldX = gp.tileSize * 25;
                gp.monster[i++].worldY = gp.tileSize * 31;
                gp.monster[i] = new MON_GreenSlime(gp);
                gp.monster[i].worldX = gp.tileSize * 14;
                gp.monster[i++].worldY = gp.tileSize * 17;
                gp.monster[i] = new MON_GreenSlime(gp);
                gp.monster[i].worldX = gp.tileSize * 12;
                gp.monster[i++].worldY = gp.tileSize * 37;
                gp.monster[i] = new MON_GreenSlime(gp);
                gp.monster[i].worldX = gp.tileSize * 15;
                gp.monster[i++].worldY = gp.tileSize * 34;
                gp.monster[i] = new MON_GreenSlime(gp);
                gp.monster[i].worldX = gp.tileSize * 20;
                gp.monster[i++].worldY = gp.tileSize * 11;
                break;
            case 4:
                gp.monster[i] = new MON_GreenSlime(gp);
                gp.monster[i].worldX = gp.tileSize * 40;
                gp.monster[i++].worldY = gp.tileSize * 22;
                gp.monster[i] = new MON_GreenSlime(gp);
                gp.monster[i].worldX = gp.tileSize * 11;
                gp.monster[i++].worldY = gp.tileSize * 26;
                gp.monster[i] = new MON_GreenSlime(gp);
                gp.monster[i].worldX = gp.tileSize * 10;
                gp.monster[i++].worldY = gp.tileSize * 19;
                gp.monster[i] = new MON_GreenSlime(gp);
                gp.monster[i].worldX = gp.tileSize * 25;
                gp.monster[i++].worldY = gp.tileSize * 9;
                gp.monster[i] = new MON_GreenSlime(gp);
                gp.monster[i].worldX = gp.tileSize * 14;
                gp.monster[i++].worldY = gp.tileSize * 9;
                gp.monster[i] = new MON_GreenSlime(gp);
                gp.monster[i].worldX = gp.tileSize * 39;
                gp.monster[i++].worldY = gp.tileSize * 6;
                gp.monster[i] = new MON_GreenSlime(gp);
                gp.monster[i].worldX = gp.tileSize * 39;
                gp.monster[i++].worldY = gp.tileSize * 15;
                gp.monster[i] = new MON_GreenSlime(gp);
                gp.monster[i].worldX = gp.tileSize * 34;
                gp.monster[i++].worldY = gp.tileSize * 35;
                gp.monster[i] = new MON_GreenSlime(gp);
                gp.monster[i].worldX = gp.tileSize * 14;
                gp.monster[i++].worldY = gp.tileSize * 35;
                gp.monster[i] = new MON_GreenSlime(gp);
                gp.monster[i].worldX = gp.tileSize * 35;
                gp.monster[i++].worldY = gp.tileSize * 42;
                gp.monster[i] = new MON_GreenSlime(gp);
                gp.monster[i].worldX = gp.tileSize * 25;
                gp.monster[i++].worldY = gp.tileSize * 43;
                gp.monster[i] = new MON_GreenSlime(gp);
                gp.monster[i].worldX = gp.tileSize * 13;
                gp.monster[i++].worldY = gp.tileSize * 42;
                gp.monster[i] = new MON_GreenSlime(gp);
                gp.monster[i].worldX = gp.tileSize * 6;
                gp.monster[i++].worldY = gp.tileSize * 42;
                break;
            case 5:
                gp.monster[i] = new MON_GreenSlime(gp);
                gp.monster[i].worldX = gp.tileSize * 20;
                gp.monster[i++].worldY = gp.tileSize * 24;
                gp.monster[i] = new MON_GreenSlime(gp);
                gp.monster[i].worldX = gp.tileSize * 30;
                gp.monster[i++].worldY = gp.tileSize * 21;
                gp.monster[i] = new MON_GreenSlime(gp);
                gp.monster[i].worldX = gp.tileSize * 30;
                gp.monster[i++].worldY = gp.tileSize * 25;
                gp.monster[i] = new MON_GreenSlime(gp);
                gp.monster[i].worldX = gp.tileSize * 39;
                gp.monster[i++].worldY = gp.tileSize * 14;
                gp.monster[i] = new MON_GreenSlime(gp);
                gp.monster[i].worldX = gp.tileSize * 16;
                gp.monster[i++].worldY = gp.tileSize * 14;
                gp.monster[i] = new MON_GreenSlime(gp);
                gp.monster[i].worldX = gp.tileSize * 11;
                gp.monster[i++].worldY = gp.tileSize * 10;
                gp.monster[i] = new MON_GreenSlime(gp);
                gp.monster[i].worldX = gp.tileSize * 11;
                gp.monster[i++].worldY = gp.tileSize * 18;
                gp.monster[i] = new MON_GreenSlime(gp);
                gp.monster[i].worldX = gp.tileSize * 22;
                gp.monster[i++].worldY = gp.tileSize * 18;
                gp.monster[i] = new MON_GreenSlime(gp);
                gp.monster[i].worldX = gp.tileSize * 22;
                gp.monster[i++].worldY = gp.tileSize * 10;
                break;
        }
    }
}