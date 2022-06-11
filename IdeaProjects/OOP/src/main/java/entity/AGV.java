package entity;

import main.GamePanel;
import main.KeyHandler;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class AGV extends Entity{

    GamePanel gp;
    KeyHandler keyH;

    public final int screenX;
    public final int screenY;

    public AGV(GamePanel gp, main.KeyHandler keyH2) {

        this.gp = gp;
        this.keyH = keyH2;

        screenX = gp.screenWidth/2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2 - (gp.tileSize/2);

        solidArea = new Rectangle();

        setDefaultValues();
        //getPlayerImage();

    }

    public void setDefaultValues(){
        worldX = gp.tileSize;
        worldY = 14 * gp.tileSize;
        speed = gp.tileSize / 3;
        direction = "down";
    }

    public void getPlayerImage() {
        try {

            img = ImageIO.read(getClass().getResourceAsStream("/agv/agv.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update()  {
        if (keyH.upPressed || keyH.downPressed ||
                keyH.leftPressed || keyH.rightPressed) {
            if (keyH.upPressed) {
                direction = "up";
            }
            else if (keyH.downPressed) {
                direction = "down";
            }
            else if (keyH.leftPressed) {
                direction = "left";
            }
            else {
                direction = "right";
            }

            collisionOn = false;
            gp.cChecker.checkTile(this);

            //IF COLLISION IS FALSE PLAYER CAN MOVE
            if(!collisionOn) {
                switch(direction) {
                    case "up": worldY -= speed; break;
                    case "down": worldY += speed; break;
                    case "left": worldX -= speed; break;
                    case "right": worldX += speed; break;
                }
            }
        }
    }

    public void draw(Graphics2D g2)  {
        //g2.setColor(Color.white);

        //g2.fillRect(worldX, worldY, gp.tileSize, gp.tileSize);

        getPlayerImage();

        BufferedImage image = img;

        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
    }
}
