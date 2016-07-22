package uk.alij.tanks;

import java.awt.*;

/**
 * Created by Ali J on 4/29/2015.
 */
public abstract class GameObject {
    //what does every object need? an x and a y variable
    protected float x , y;//protected means it can only be accessed by which object inherits the game object
    /*since everything is going to be a game object we need something to identify what a player is and what an enemy is*/
    protected ID id;
    protected float velX, velY;//the speed in our X direction and the speed in our Y direction

    public GameObject(float x , float y, ID id){
        this.x = x;
        this.y = y;
        this.id = id;
    }
    //we're going to make components that we're definently going to need within our class that we're going to need to write code in
    public abstract void tick();
    public abstract void render(Graphics g);
    public abstract Rectangle getBounds();/*basically we're going to be using rectangles to handle all of our collision because within our java libraries,we have a Rectangle class(within the JRE)-->that has a method in it called
    ".intersect" which basically handles if two rectangles intersect each other it will return true*/

    //now we can also do stuff that we don't need inside the player class like our getter and setter methods

    public void setX( int x ){
        this.x = x;
    }

    public void setY( int y ){
        this.y = y;
    }

    public float getX(){
        return x;
    }

    public float getY(){
        return y;
    }

    public void setId(ID id){
        this.id = id;
    }

    public ID getId(){
        return id;
    }

    public void setVelX(int velX){
        this.velX = velX;
    }

    public void setVelY(int velY){
        this.velY = velY;
    }

    public float getVelX(){
        return velX;
    }

    public float getVelY(){
        return velY;
    }
}
