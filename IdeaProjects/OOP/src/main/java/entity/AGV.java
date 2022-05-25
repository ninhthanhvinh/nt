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
        worldX = 23 * gp.tileSize;
        worldY = 21 * gp.tileSize;
        speed = 4;
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
        if (keyH.upPressed == true || keyH.downPressed == true ||
                keyH.leftPressed == true || keyH.rightPressed == true) {
            if (keyH.upPressed == true) {
                direction = "up";
            }
            else if (keyH.downPressed == true) {
                direction = "down";
            }
            else if (keyH.leftPressed == true) {
                direction = "left";
            }
            else if (keyH.rightPressed == true) {
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

            spriteCounter++;
            if (spriteCounter >= 12) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                }
                else if (spriteNum == 2)
                    spriteNum = 1;
                spriteCounter = 0;
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
