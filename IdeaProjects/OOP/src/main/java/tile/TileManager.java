package tile;

import java.awt.Graphics2D;
import java.io.*;

import javax.imageio.ImageIO;

import main.GamePanel;

public class TileManager {
    GamePanel gp;
    public Tile[] tile;
    public int[][] mapTileNum;

    public TileManager(GamePanel gp) {

        this.gp = gp;

        tile = new Tile[100];
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];

        getTileImage();

    }

    public void getTileImage() {

        try {

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(new FileInputStream("src/main/resources/tiles/ground(1).png"));
            tile[1].collision = true;

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(new FileInputStream("src/main/resources/tiles/elevator2.png"));

            tile[3] = new Tile();
            tile[3].image = ImageIO.read(new FileInputStream("src/main/resources/tiles/gate3.png"));
            tile[3].collision = true;

            tile[4] = new Tile();
            tile[4].image = ImageIO.read(new FileInputStream("src/main/resources/tiles/wall4.png"));
            tile[4].collision = true;

            tile[9] = new Tile();
            tile[9].image = ImageIO.read(new FileInputStream("src/main/resources/tiles/room9.png"));
            tile[9].collision = true;

            tile[10] = new Tile();
            tile[10].image = ImageIO.read(new FileInputStream("src/main/resources/tiles/bed10.png"));
            tile[10].collision = true;

            tile[12] = new Tile();
            tile[12].image = ImageIO.read(new FileInputStream("src/main/resources/tiles/upPath12.png"));
            tile[12].downCollision = true;
            tile[12].collision = false;

            tile[20] = new Tile();
            tile[20].image = ImageIO.read(new FileInputStream("src/main/resources/tiles/downPath20.png"));
            tile[20].upCollision = true;
            tile[20].collision = false;

            tile[28] = new Tile();
            tile[28].image = ImageIO.read(new FileInputStream("src/main/resources/tiles/rightPath28.png"));
            tile[28].leftCollision = true;
            tile[28].collision = false;

            tile[36] = new Tile();
            tile[36].image = ImageIO.read(new FileInputStream("src/main/resources/tiles/leftPath36.png"));
            tile[36].rightCollision = true;
            tile[36].collision = false;

            tile[35] = new Tile();
            tile[35].image = ImageIO.read(new FileInputStream("src/main/resources/tiles/35.png"));

            tile[41] = new Tile();
            tile[41].image = ImageIO.read(new FileInputStream("src/main/resources/tiles/41.png"));

            tile[42] = new Tile();
            tile[42].image = ImageIO.read(new FileInputStream("src/main/resources/tiles/42.png"));;

            tile[43] = new Tile();
            tile[43].image = ImageIO.read(new FileInputStream("src/main/resources/tiles/43.png"));

            tile[49] = new Tile();
            tile[49].image = ImageIO.read(new FileInputStream("src/main/resources/tiles/49.png"));

            tile[50] = new Tile();
            tile[50].image = ImageIO.read(new FileInputStream("src/main/resources/tiles/50.png"));

            tile[57] = new Tile();
            tile[57].image = ImageIO.read(new FileInputStream("src/main/resources/tiles/57.png"));

            tile[58] = new Tile();
            tile[58].image = ImageIO.read(new FileInputStream("src/main/resources/tiles/58.png"));

            tile[59] = new Tile();
            tile[59].image = ImageIO.read(new FileInputStream("src/main/resources/tiles/cross59.png"));

            tile[19] = new Tile();
            tile[19].image = ImageIO.read(new FileInputStream("src/main/resources/tiles/19.png"));

            tile[26] = new Tile();
            tile[26].image = ImageIO.read(new FileInputStream("src/main/resources/tiles/26.png"));


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

                    String[] numbers = line.split(",");

                    int num = Integer.parseInt(numbers[col]);

                    if (num != 0)
                    mapTileNum[col][row] = num;
                    col++;
                }
                if(col == gp.maxWorldCol) {
                    col = 0;
                    row++;
                }
            }
            br.close();
        } catch (Exception ignored) {

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
                if (tileNum != 0) {
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
