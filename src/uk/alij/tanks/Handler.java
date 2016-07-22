package uk.alij.tanks;

import java.awt.*;
import java.util.LinkedList;
import java.util.Random;

/**
 * Created by Ali J on 4/29/2015.
 */

//this handler class is going to maintain or update and render all of our objects in our room(it has a sort of game-maker functionality
//since we're going to have more than one object in the game we need to handle and process each of those objects,its not something that happens automatically
//so this handler class is going to loop through all of our objects in the game and individually update them and render them to the screen
public class Handler {
    /*here in the handler class we need to make a list of all the objects within our game and because we dont know how many game objects we're going to have we're going
    * to make a linked list of GameObject typed objects*/

    private LinkedList<GameObject> object;
    private GamePanel gamePanel;
    private int maxScoreSetter;
    private HUD hud;

    public Handler(GamePanel gamePanel,HUD hud){
        this.gamePanel = gamePanel;
        this.hud = hud;
        maxScoreSetter = 0;
        object = new LinkedList<GameObject>();
    }
    //in our handler class we're going to need the basic methods so:
    public void tick(){
        for(int i = 0; i < object.size(); i++ ) {//what this for is going to do is basically loop through every1 of our gameobjects
            GameObject tempObject = object.get(i);/*what this does is we're setting the tempObject wich is of type GameObject to object.get(i) which is a function within our linked list
            which allows us to get the ID of what object we are at*/
            shoot(tempObject);
            tempObject.tick();
        }
    }

    public void render(Graphics g){
        for (int i = 0; i < object.size(); i++ ){
            GameObject tempObject = object.get(i);
            tempObject.render(g);
        }
    }

    private void shoot(GameObject tempObject){
        if (tempObject.getId() == ID.NormalEnemy){//if the object we're checking now is the enemy(tempobject has an enemy ID)
            if (System.nanoTime() % 1000 == 50 ){
                gamePanel.getBullets().add(new Bullet(new Random().nextInt(4),(int) tempObject.getX(),(int) tempObject.getY(),this));
            }
        }else if (tempObject.getId() == ID.FastEnemy){
            if (System.nanoTime() % 500 == 50 ){
                gamePanel.getBullets().add(new Bullet(new Random().nextInt(4),(int) tempObject.getX(),(int) tempObject.getY(),this));
            }
        }else if (tempObject.getId() == ID.SmartEnemy){
            if (System.nanoTime() % 100 == 50 ){
                gamePanel.getBullets().add(new Bullet((int) tempObject.getX(),(int) tempObject.getY(),this, (int) tempObject.getVelX(),(int) tempObject.getVelY()));
            }
        }else if (tempObject.getId() == ID.StrongEnemy){
            if (System.nanoTime() % 1000 == 50 ){
                gamePanel.getBullets().add(new Bullet(new Random().nextInt(4),(int) tempObject.getX(),(int) tempObject.getY(),this));
            }
        }else if (tempObject.getId() == ID.SmartEnemy2){
            if (System.nanoTime() % 100 == 50 ){
                gamePanel.getBullets().add(new Bullet((int) tempObject.getX(),(int) tempObject.getY(),this, (int) tempObject.getVelX(),(int) tempObject.getVelY()));
            }
        }else if (tempObject.getId() == ID.SmartEnemy3){
            if (System.nanoTime() % 100 == 50 ){
                gamePanel.getBullets().add(new Bullet((int) tempObject.getX(),(int) tempObject.getY(),this , ID.HQBuilding));
            }
        }else if (tempObject.getId() == ID.SmartEnemy4){
            if (System.nanoTime() % 100 == 50 ){
                gamePanel.getBullets().add(new Bullet((int) tempObject.getX(),(int) tempObject.getY(),this, ID.Building));
            }
        }
    }

    //now we need to handle how we are going to add and remove objects from our linked list of GameObjects
    public synchronized void addObject(GameObject object){
        this.object.add(object);
    }

    public synchronized void removeObject(GameObject object){
        this.object.remove(object);
    }

    public synchronized LinkedList<GameObject> getObject(){
        return object;
    }
}
