package uk.alij.tanks;

/**
 * Created by Ali J on 5/2/2015.
 */
public abstract class Enemy extends GameObject{
    protected boolean dead;

    protected int score;
    protected int health;

    public Enemy(float x, float y, ID id) {
        super(x, y, id);
        dead = false;
    }

    public void hit(){
        health--;
        if (health <= 0)
            dead = true;
    }


    public boolean isDead() {
        return dead;
    }


    public void setDead(boolean dead) {
        this.dead = dead;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

}
