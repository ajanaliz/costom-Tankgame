package uk.alij.tanks;


import java.awt.*;

/**
 * Created by Ali J on 4/29/2015.
 */
public class Bullet {
    //Fields
    private double x;
    private double y;
    //we're dealing with radians and we need doubles for them
    private int r;//radius

    private double velX;
    private double velY;
    private double speed;
    private int delta,start;

    private GameObject buildinghq,building;

    private Handler handler;
    private Color color1;


    //Constructor
    public Bullet(int direction, int x, int y,Handler handler){
        this.x = x;
        this.y = y;
        r = 5;
        this.handler = handler;
        delta = 150;
        start = (int) (System.nanoTime()/1000000);
        SoundEffect.FIRING.play();
        speed = 10;
        if (direction == 0){//up
            velY = -speed;
        }if (direction == 1) {//left
            velX =  -speed;
        }if (direction == 2){//right
            velX = speed;
        }if (direction == 3){//down
            velY = speed;
        }
        color1 = Color.yellow;
    }

    public Bullet(int x, int y,Handler handler,int velX,int velY){
        this.x = x;
        this.y = y;
        r = 5;
        this.handler = handler;
        delta = 200;
        start = (int) (System.nanoTime()/1000000);
        SoundEffect.FIRING.play();
        speed = 10;
        this.velX = 5 * velX;
        this.velY = 5 * velY;
        color1 = Color.yellow;
    }

    public Bullet(float x, float y, Handler handler, ID id) {
        this.x = x;
        this.y = y;
        r = 5;
        this.handler = handler;
        delta = 200;
        start = (int) (System.nanoTime()/1000000);
        SoundEffect.FIRING.play();
        speed = 10;
        for (int i = 0; i < handler.getObject().size(); i++){
            if (handler.getObject().get(i).getId() == ID.HQBuilding) buildinghq = handler.getObject().get(i);
            if (handler.getObject().get(i).getId() == ID.Building) building = handler.getObject().get(i);
        }

        if (id == ID.Building){
            float diffX = x - building.getX();
            float diffY = y - building.getY();
            float distance = (float) Math.sqrt((x - building.getX()) * (x - building.getX()) + (y - building.getY()) * (y - building.getY()));

            this.velX = (int) (((-1.0 / distance) * diffX) * 7);
            this.velY = (int) (((-1.0 / distance) * diffY) * 7);
        }
        if (id == ID.HQBuilding){
            float diffX = x - buildinghq.getX();
            float diffY = y - buildinghq.getY();
            float distance = (float) Math.sqrt((x - buildinghq.getX()) * (x - buildinghq.getX()) + (y - buildinghq.getY()) * (y - buildinghq.getY()));

            this.velX = (int) (((-1.0 / distance) * diffX) * 7);
            this.velY = (int) (((-1.0 / distance) * diffY) * 7);
        }
        color1 = Color.yellow;
    }

    //Functions

    public boolean update(){
        x += velX;
        y += velY;
        for (int i = 0; i < handler.getObject().size(); i ++){
            if ((delta  < (System.nanoTime()/1000000) - start )) {
                if (handler.getObject().get(i).getId() == ID.Player || handler.getObject().get(i).getId() == ID.Player2) {
                    if (getBounds().intersects(handler.getObject().get(i).getBounds())) {
                        ((Player) handler.getObject().get(i)).hit();
                        return true;
                    }
                }
            }
            if (handler.getObject().get(i).getId() == ID.NormalEnemy || handler.getObject().get(i).getId() == ID.SmartEnemy || handler.getObject().get(i).getId() == ID.StrongEnemy ||
                    handler.getObject().get(i).getId() == ID.FastEnemy || handler.getObject().get(i).getId() == ID.FastEnemy2 || handler.getObject().get(i).getId() == ID.FastEnemy3 ||
                    handler.getObject().get(i).getId() == ID.SmartEnemy2 || handler.getObject().get(i).getId() == ID.SmartEnemy3 || handler.getObject().get(i).getId() == ID.SmartEnemy4){
                if (getBounds().intersects(handler.getObject().get(i).getBounds())){
                    ((Enemy)handler.getObject().get(i)).hit();
                    return true;
                }
            }
            if (handler.getObject().get(i).getId() == ID.HQBuilding || handler.getObject().get(i).getId() == ID.Building){
                if (getBounds().intersects(handler.getObject().get(i).getBounds())){
                    ((Ally)handler.getObject().get(i)).hit();
                    return true;
                }
            }
        }
        if (x < -r || x >GamePanel.WIDTH + r || y < -r || y > GamePanel.HEIGHT + r ) {
            return true;
        }
        handler.addObject(new Trail((float) x, (float) y, r , ID.Trail, color1 , 0.1f,handler));
        return false;/*this function is going to return a boolean(the purpose of this boolean is weather or not we want to remove this bullet from the screen,so if it returns true u remove it and if false you
        keep doing the shit normally) so by default this function returns false,but if we want to remove the bullet,like if it hits the enemy or if it gets out of bounds then we want to return true*/
    }

    public void render(Graphics2D g){
        g.setColor(color1);
        g.fillOval((int) (x-r) , (int) (y-r), 2*r,2*r);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public int getR(){
        return r;
    }

    private Rectangle getBounds(){
        return new Rectangle((int)x,(int)y, 4*r, 4*r);
    }

}
