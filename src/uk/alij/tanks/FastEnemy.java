package uk.alij.tanks;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Vector;

/**
 * Created by Ali J on 4/29/2015.
 */
public class FastEnemy extends Enemy {

    private Vector<BufferedImage> enemyFrames;
    private int enemyFrameNr;
    private int width;
    private int height;
    private int health;


    public FastEnemy(float x, float y, ID id) {
        super(x, y, id);
        width = 32;
        height = 35;
        score = 100;
        enemyFrames = new Vector<BufferedImage>();
        loadImages();
        if (id == ID.FastEnemy){
            velX = -8;
            velY = -5;
        }
        if (id == ID.FastEnemy2){
            velX = +8;
            velY = +5;
        }
        if (id == ID.FastEnemy3){
            velY = +5;
            velX = -8;
        }
        dead = false;
        health = 8;
    }




    private void loadImages(){
        try {
            for(int i = 1; i < 13; i++){
                BufferedImage a = ImageIO.read(new File("res/images/Enemy/" + i + ".gif"));
                enemyFrames.add(a);
            }
        } catch (Exception e) { e.printStackTrace(); }
    }

    @Override
    public void tick() {
        x += velX;
        y += velY;

        if (y <= 0 || y >= GamePanel.HEIGHT - 85) velY *= -1;
        if (x <= 0 || x >= GamePanel.WIDTH - 80) velX*= -1;


        if(enemyFrameNr>= (enemyFrames.size()-1)){
            enemyFrameNr = 0;
        }else{	enemyFrameNr++; }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(enemyFrames.get(enemyFrameNr), (int) x, (int) y, null);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) (x + 33),(int) (y + 30),width,height);
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }
}
