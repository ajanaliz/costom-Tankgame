package uk.alij.tanks;

/**
 * Created by Ali J on 4/29/2015.
 */
public class Spawn {

    private Handler handler;
    private HUD hud;
    private int scoreKeep = 0;

    public Spawn(Handler handler, HUD hud,String difficulty){
        this.handler = handler;
        this.hud = hud;
        if (difficulty.equalsIgnoreCase("easy")){
            easy();
        }
        if (difficulty.equalsIgnoreCase("medium")){
            medium();
        }
        if (difficulty.equalsIgnoreCase("hard")){
            hard();
        }
    }

    public void easy(){
        handler.addObject(new NormalEnemy(1200, 400, ID.NormalEnemy));
        handler.addObject(new SmartEnemy(1200,500, ID.SmartEnemy,handler));
        handler.addObject(new SmartEnemy(1200,600, ID.SmartEnemy2,handler));
        handler.addObject(new SmartEnemy(1250,500, ID.SmartEnemy3,handler));
        handler.addObject(new StrongEnemy(1250,300, ID.StrongEnemy));
        handler.addObject(new FastEnemy(1250,400 , ID.FastEnemy));
        hud.setMaxScore(1050);
    }

    public void medium(){
        handler.addObject(new NormalEnemy(1200, 400, ID.NormalEnemy));
        handler.addObject(new SmartEnemy(1200,500, ID.SmartEnemy,handler));
        handler.addObject(new SmartEnemy(1100,600, ID.SmartEnemy2,handler));
        handler.addObject(new SmartEnemy(1111,500, ID.SmartEnemy3,handler));
        handler.addObject(new StrongEnemy(1250,300, ID.StrongEnemy));
        handler.addObject(new FastEnemy(1150,400 , ID.FastEnemy));
        handler.addObject(new FastEnemy(1250,400 , ID.FastEnemy2));
        handler.addObject(new FastEnemy(1000,400 , ID.FastEnemy3));
        hud.setMaxScore(1250);
    }

    public void hard(){
        handler.addObject(new NormalEnemy(1200, 400, ID.NormalEnemy));
        handler.addObject(new SmartEnemy(1200,500, ID.SmartEnemy,handler));
        handler.addObject(new SmartEnemy(1100,600, ID.SmartEnemy2,handler));
        handler.addObject(new SmartEnemy(1111,500, ID.SmartEnemy3,handler));
        handler.addObject(new StrongEnemy(1250,300, ID.StrongEnemy));
        handler.addObject(new StrongEnemy(750,100, ID.StrongEnemy));
        handler.addObject(new FastEnemy(1150,400 , ID.FastEnemy));
        handler.addObject(new FastEnemy(1250,400 , ID.FastEnemy2));
        handler.addObject(new FastEnemy(1000,400 , ID.FastEnemy3));
        handler.addObject(new SmartEnemy(800,200, ID.SmartEnemy3,handler));
        handler.addObject(new SmartEnemy(800,300, ID.SmartEnemy3,handler));
        handler.addObject(new SmartEnemy(800,400, ID.SmartEnemy3,handler));
        handler.addObject(new SmartEnemy(400,100, ID.SmartEnemy2,handler));
        handler.addObject(new SmartEnemy(250,100, ID.SmartEnemy,handler));
        handler.addObject(new SmartEnemy(800,400, ID.SmartEnemy4,handler));
        hud.setMaxScore(2900);
    }
}
