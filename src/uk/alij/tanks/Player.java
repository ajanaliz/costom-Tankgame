package uk.alij.tanks;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Vector;

/**
 * Created by Ali J on 4/29/2015.
 */
public class Player extends Ally {
    private int width;
    private int height;
    private int direction;
    private Vector<BufferedImage> playerFrames;

    public Player(float x, float y, ID id, Handler handler, HUD hud) {
        super(x, y, id, handler, hud);
        playerFrames = new Vector<BufferedImage>();
        loadImages();
    }

    private void loadImages() {
        try {
            for(int i = 1; i < 5; i++){
                BufferedImage a = ImageIO.read(new File("res/images/tank/" + i + ".png"));
                playerFrames.add(a);
            }
        } catch (Exception e) { e.printStackTrace(); }
    }

    @Override
    public void tick() {
        x += velX;
        y += velY;
        collision();
        x = GamePanel.clamp(x , 0 ,GamePanel.WIDTH - 70);
        y = GamePanel.clamp(y , 0 ,GamePanel.HEIGHT - 95);
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(playerFrames.get(direction),(int) x, (int) y,null);
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

    public void hit(){
        hud.HEALTH -= 2;
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) x,(int) y, 64,64 );
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
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
