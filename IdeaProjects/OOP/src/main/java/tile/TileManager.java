package tile;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.GamePanel;

public class TileManager {
    GamePanel gp;
    public Tile[] tile;
    public int mapTileNum[][];

    public TileManager(GamePanel gp) {

        this.gp = gp;

        tile = new Tile[10];
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];

        getTileImage();

        gp.setBackground(Color.GRAY);

        loadMap("/maps/id1.txt");
        //loadMap("/maps/id2.txt");
    }

    public void getTileImage() {

        try {

            //tile[0] = new Tile();
            //tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/road1.png"));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/ground.png"));
            tile[1].collision = true;

            //tile[2] = new Tile();
            //tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/water.png"));
            //tile[2].collision = true;

            //tile[3] = new Tile();
            //tile[3].image = ImageIO.read(getClass().getResourceAsStream("/tiles/earth.png"));

            tile[4] = new Tile();
            tile[4].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wall.png"));
            tile[4].collision = true;

            //tile[5] = new Tile();
            //tile[5].image = ImageIO.read(getClass().getResourceAsStream("/tiles/sand.png"));

            /*tile[9] = new Tile();
            tile[9].image = ImageIO.read(getClass().getResourceAsStream("/tiles/room1.png"));
            tile[9].collision = true;*/


        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void loadMap(String address) {
        try {

            InputStream is = getClass().getResourceAsStream(address);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while (col < gp.maxWorldCol && row < gp.maxWorldRow) {

                String line = br.readLine();

                while(col < gp.maxWorldCol) {

                    String numbers[] = line.split(",");

                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[col][row] = num;
                    col++;
                }
                if(col == gp.maxWorldCol) {
                    col = 0;
                    row++;
                }
            }
            br.close();
        } catch (Exception e) {

        }
    }

    public void draw(Graphics2D g2) {

        int worldCol = 0;
        int worldRow = 0;

        while (worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {

            int tileNum = mapTileNum[worldCol][worldRow];

            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.agv.worldX + gp.agv.screenX;
            int screenY = worldY - gp.agv.worldY + gp.agv.screenY;

            if( worldX + gp.tileSize > gp.agv.worldX - gp.agv.screenX &&
                    worldX - gp.tileSize < gp.agv.worldX + gp.agv.screenX &&
                    worldY + gp.tileSize > gp.agv.worldY - gp.agv.screenY &&
                    worldY - gp.tileSize < gp.agv.worldY + gp.agv.screenY) {
                if(tileNum != 0){
                    g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
                }
            }
            worldCol++;
            if(worldCol == gp.maxWorldCol) {
                worldCol = 0;
                worldRow++;
            }
        }
    }

}