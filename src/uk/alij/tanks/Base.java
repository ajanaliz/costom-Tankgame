package uk.alij.tanks;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Ali J on 5/2/2015.
 */
public class Base extends Ally{
    private BufferedImage hqImage;
    private int health;
    private boolean dead;

    public Base(float x, float y, ID id, Handler handler, HUD hud) {
        super(x, y, id, handler, hud);
        health = 20;
        loadImages();
    }


    private void loadImages(){
        try {
            hqImage = ImageIO.read(new File("res/images/Base/cityGrey.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void hit(){
        hud.HEALTH -= 2;
    }

    private void collision(){//the collision between the enemy and the player
        for (int i = 0; i < handler.getObject().size(); i++){
            GameObject tempObject = handler.getObject().get(i);
            if (tempObject.getId() == ID.NormalEnemy || tempObject.getId() == ID.FastEnemy || tempObject.getId() == ID.FastEnemy2 ||
                    tempObject.getId() == ID.FastEnemy3 || tempObject.getId() == ID.SmartEnemy || tempObject.getId() == ID.StrongEnemy ||
                    tempObject.getId() == ID.SmartEnemy2 || tempObject.getId() == ID.SmartEnemy3){//if the object we're checking now is the enemy(tempobject has an enemy ID)
                if (getBounds().intersects(tempObject.getBounds())){//if getbounds wich is a rectangle intersects with temp object(the enemy rectangle)
                    //collision code
                    HUD.HEALTH -= 0.01f;
                }
            }

        }
    }

    @Override
    public void tick() {
        collision();
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(hqImage,(int) x,(int) y,null);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) x,(int) y , hqImage.getWidth(),hqImage.getHeight());
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public boolean isDead() {
        return dead;
    }

    public void setDead(boolean dead) {
        this.dead = dead;
    }
}
