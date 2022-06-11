package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;
import javax.swing.plaf.DimensionUIResource;

import entity.AGV;
import tile.TileManager;
//import tile.TileManager;


public class GamePanel extends JPanel implements Runnable {


    private static final long serialVersionUID = 1L;
    final int originalTileSize = 16;
    final int scale = 2;
    public final int tileSize = originalTileSize * scale;
    public final int maxScreenCol = 32;
    public final int maxScreenRow = 24;
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;

    //WORLD SETTINGS
    public final int maxWorldCol = 52;
    public final int maxWorldRow = 28;
    public final int worldWidth = maxWorldCol * tileSize;
    public final int worldHeight = maxWorldRow * tileSize;


    int FPS = 60;

    TileManager tileM = new TileManager(this);

    KeyHandler keyH = new KeyHandler();

    Thread gameThread;
    public CollisionChecker cChecker = new CollisionChecker(this);

    public AGV agv = new AGV(this, keyH);

    public GamePanel() {
        this.setPreferredSize(new DimensionUIResource(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        // If this set to true, all drawings in this component will be done in an offscreen painting buffer
        // Enable this can improve game's rendering performance
        this.addKeyListener(keyH);      //ThÃªm KeyListener vÃ o GamePanel
        this.setFocusable(true);    //LÃ m cho GamePanel focus vÃ o nháº­n cÃ¡c keyinput

    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
	/*public void run() {             // Core game here : GAMELOOP

    while (gameThread != null)
    {
        //long currentTime = System.nanoTime();  //HÃ m tráº£ vá»� thá»�i gian hiá»‡n táº¡i cá»§a há»‡ thá»‘ng = Ä‘v nano giÃ¢y
        double drawInterval = 1000000000/FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;

        //1.UPDATE: Update information such as characters position
            update();
        //2.DRAWING: Draw the screen with the update information
            repaint();
        //3.Checking: Check xem cÃ²n bao nhiÃªu thá»�i gian Ä‘á»ƒ tá»›i nextDrawTime

        //4.Sleep: Sleep trong remainingTime:
            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime/1000000;
                if (remainingTime < 0) remainingTime = 0;

                Thread.sleep((long) remainingTime);

                nextDrawTime += drawInterval;
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        //System.out.println("The gameloop is running");
    }

}*/

    public void run()  {
        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while (gameThread != null) {
            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if (delta >= 1) {
                update();
                repaint();
                delta--;
                drawCount++;
            }

            if (timer >= 1000000000)  {
                System.out.println("FPS: " + drawCount);
                timer = 0;
                drawCount = 0;
            }

        }
    }

    public void update() {

        agv.update();

    }


    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;

        tileM.loadMap("/maps/id1.txt");

        tileM.draw(g2);

        tileM.loadMap("/maps/id2.txt");

        tileM.draw(g2);

        tileM.loadMap("/maps/id3.txt");

        tileM.draw(g2);

        tileM.loadMap("/maps/id4.txt");

        tileM.draw(g2);

        tileM.loadMap("/maps/id5.txt");

        tileM.draw(g2);

        tileM.loadMap("/maps/id6.txt");

        tileM.draw(g2);

        tileM.loadMap("/maps/id9.txt");

        tileM.draw(g2);

        tileM.loadMap("/maps/id7.txt");

        tileM.draw(g2);


        /*tileM.draw(g2);*/

        agv.draw(g2);

        g2.dispose();       // Dispose of this graphics context and release any system resources that it's using

    }


}
