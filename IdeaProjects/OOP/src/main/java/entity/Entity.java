package entity;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Entity {
    public int worldX, worldY, speed;

    public BufferedImage img;
    public String direction;

    public int spriteCounter = 0;
    public int spriteNum = 1;


    public boolean collisionOn = false;



    public Rectangle solidArea;



}
