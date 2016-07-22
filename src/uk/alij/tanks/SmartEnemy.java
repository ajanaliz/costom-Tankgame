package uk.alij.tanks;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Vector;

/**
 * Created by Ali J on 4/29/2015.
 */
public class SmartEnemy extends Enemy {

    private Vector<BufferedImage> enemyFrames;
    private int enemyFrameNr;
    private int width;
    private int height;

    private GameObject player,player2,buildinghq,building;
    private Handler handler;

    public SmartEnemy(float x, float y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;
        for (int i = 0; i < handler.getObject().size(); i++){
            if (handler.getObject().get(i).getId() == ID.Player ) player = handler.getObject().get(i);/*basically we're running through the for loop of our array and checking if our certain variable gets the id of player
            then we're kind of setting the gameobject to player-->then we use this player variable*/
            if (handler.getObject().get(i).getId() == ID.Player2 ) player2 = handler.getObject().get(i);
            if (handler.getObject().get(i).getId() == ID.HQBuilding) buildinghq = handler.getObject().get(i);
            if (handler.getObject().get(i).getId() == ID.Building) building = handler.getObject().get(i);
        }
        width = 32;
        height = 35;
        score = 250;
        enemyFrames = new Vector<BufferedImage>();
        loadImages();
        velX = -3;
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
        if (id == ID.SmartEnemy) {
            float diffX = x - player.getX();
            float diffY = y - player.getY();
            float distance = (float) Math.sqrt((x - player.getX()) * (x - player.getX()) + (y - player.getY()) * (y - player.getY()));

            velX = (int) (((-1.0 / distance) * diffX) * 4);
            velY = (int) (((-1.0 / distance) * diffY) * 4);
        }
        if (id == ID.SmartEnemy2){
            float diffX = x - player2.getX();
            float diffY = y - player2.getY();
            float distance = (float) Math.sqrt((x - player2.getX()) * (x - player2.getX()) + (y - player2.getY()) * (y - player2.getY()));

            velX = (int) (((-1.0 / distance) * diffX) * 4);
            velY = (int) (((-1.0 / distance) * diffY) * 4);
        }
        if (id == ID.SmartEnemy3){
            float diffX = x - buildinghq.getX();
            float diffY = y - buildinghq.getY();
            float distance = (float) Math.sqrt((x - buildinghq.getX()) * (x - buildinghq.getX()) + (y - buildinghq.getY()) * (y - buildinghq.getY()));

            velX = (int) (((-1.0 / distance) * diffX) * 2);
            velY = (int) (((-1.0 / distance) * diffY) * 2);

        }
        if (id == ID.SmartEnemy4){
            float diffX = x - building.getX();
            float diffY = y - building.getY();
            float distance = (float) Math.sqrt((x - building.getX()) * (x - building.getX()) + (y - building.getY()) * (y - building.getY()));

            velX = (int) (((-1.0 / distance) * diffX) * 2);
            velY = (int) (((-1.0 / distance) * diffY) * 2);

        }
        x += velX;
        y += velY;
        if (y <= 0 || y >= GamePanel.HEIGHT - 32) velY *= -1;
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
