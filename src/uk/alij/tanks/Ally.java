package uk.alij.tanks;

/**
 * Created by Ali J on 5/2/2015.
 */
public abstract class Ally extends GameObject {
    protected Handler handler;
    protected HUD hud;

    public Ally(float x, float y, ID id, Handler handler, HUD hud) {
        super(x, y, id);
        this.handler = handler;
        this.hud = hud;
    }

    public void hit(){
        hud.HEALTH -= 2;
    }


}
