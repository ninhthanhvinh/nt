package main;

import entity.Entity;

public class CollisionChecker {

    GamePanel gp;
    public CollisionChecker(GamePanel gp) {
        this.gp = gp;
    }

    public void checkTile(Entity entity) {

        int entityCurrentWorldX = entity.worldX;
        int entityCurrentWorldY = entity.worldY;

        int entityCurrentCol = entityCurrentWorldX / gp.tileSize;
        //int entityRightCol = entityRightWorldX / gp.tileSize;
        int entityCurrentRow = entityCurrentWorldY / gp.tileSize;
        //int entityBottomRow = entityBottomWorldY / gp.tileSize;

        int tileNum1, tileNum2;

        switch(entity.direction) {
            case "up":
                int entityTopRow = (entityCurrentWorldY - entity.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityCurrentCol][entityTopRow];
                if(gp.tileM.tile[tileNum1].collision ||
                        gp.tileM.tile[tileNum1].downCollision){
                    entity.collisionOn = true;
                }
                break;
            case "down":
                int entityBottomRow = (entityCurrentWorldY + entity.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityCurrentCol][entityBottomRow];
                if(gp.tileM.tile[tileNum1].collision ||
                        gp.tileM.tile[tileNum1].upCollision
                        ) {
                    entity.collisionOn = true;
                }
                break;
            case "left":
                int entityLeftCol = (entityCurrentWorldX - entity.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityCurrentRow];
                if(gp.tileM.tile[tileNum1].collision /*||
                        gp.tileM.tile[tileNum1].rightCollision*/) {
                    entity.collisionOn = true;
                }
                break;
            case "right":
                int entityRightCol = (entityCurrentWorldX + entity.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityRightCol][entityCurrentRow];
                if(gp.tileM.tile[tileNum1].collision /*||
                        gp.tileM.tile[tileNum1].leftCollision*/) {
                    entity.collisionOn = true;
                }
                break;
        }


    }
}
