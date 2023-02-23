package main;

import entity.NPC_Rudeus;
import monster.GreenSlime;
import object.Door;

public class AssetSetter {

    GamePanel gp;
    public AssetSetter(GamePanel gp){
        this.gp = gp;
    }

    public void setNPC(){
        gp.npcs[0] = new NPC_Rudeus(gp);
        gp.npcs[0].worldX = gp.tileSize*21;
        gp.npcs[0].worldY = gp.tileSize*21;
        gp.npcs[1] = new NPC_Rudeus(gp);
        gp.npcs[1].worldX = gp.tileSize*21;
        gp.npcs[1].worldY = gp.tileSize*22;





    }

    public void setMonster(){
        gp.monsters[0] = new GreenSlime(gp);
        gp.monsters[0].worldX = gp.tileSize * 37;
        gp.monsters[0].worldY = gp.tileSize * 9;

        gp.monsters[1] = new GreenSlime(gp);
        gp.monsters[1].worldX = gp.tileSize * 22;
        gp.monsters[1].worldY = gp.tileSize * 22;

    }

    public void setObject(){

        gp.objs[0] = new Door(gp);
        gp.objs[0].worldX = 10 * gp.tileSize;
        gp.objs[0].worldY = 12 * gp.tileSize;

        gp.objs[1] = new Door(gp);
        gp.objs[1].worldX = 8 * gp.tileSize;
        gp.objs[1].worldY = 27 * gp.tileSize;

        gp.objs[2] = new Door(gp);
        gp.objs[2].worldX = 10 * gp.tileSize;
        gp.objs[2].worldY = 22 * gp.tileSize;


//        gp.obj[0] = new Key(gp);
//        gp.obj[0].worldX = 25 * gp.tileSize; // column
//        gp.obj[0].worldY =6 * gp.tileSize;  // row
//
//        gp.obj[1] = new Key(gp);
//        gp.obj[1].worldX = 23 * gp.tileSize;
//        gp.obj[1].worldY = 40 * gp.tileSize;
//
//        gp.obj[2] = new Key(gp);
//        gp.obj[2].worldX = 37 * gp.tileSize;
//        gp.obj[2].worldY = 7 * gp.tileSize;
//
//        gp.obj[3] = new Door(gp);
//        gp.obj[3].worldX = 10 * gp.tileSize;
//        gp.obj[3].worldY = 12 * gp.tileSize;
//
//        gp.obj[4] = new Door(gp);
//        gp.obj[4].worldX = 8 * gp.tileSize;
//        gp.obj[4].worldY = 27 * gp.tileSize;
//
//        gp.obj[5] = new Door(gp);
//        gp.obj[5].worldX = 10 * gp.tileSize;
//        gp.obj[5].worldY = 22 * gp.tileSize;
//
//        gp.obj[6] = new Chest(gp);
//        gp.obj[6].worldX = 10 * gp.tileSize;
//        gp.obj[6].worldY = 8 * gp.tileSize;
//
//        gp.obj[7] = new Boots(gp);
//        gp.obj[7].worldX = 37 * gp.tileSize;
//        gp.obj[7].worldY = 42 * gp.tileSize;


    }
}
