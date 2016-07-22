package uk.alij.tanks;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

/**
 * Created by Ali J on 4/29/2015.
 */
public class GamePanel extends Canvas implements Runnable{

    private Player player;
    private Handler handler;
    private HUD hud;
    private Spawn spawn;
    public static final int WIDTH = 1366 , HEIGHT = WIDTH / 16 * 9;//a 16:9 ratio :))
    private boolean running;
    private Thread thread;
    private Vector<Bullet> bullets;
    private Vector<Explosion> explosions;
    private Window window;

    public GamePanel(String difficulty){
        explosions = new Vector<Explosion>();
        bullets = new Vector<Bullet>();
        hud = new HUD(difficulty);
        handler = new Handler(this, hud);
        window = new Window(WIDTH,HEIGHT, "Battle Tanks  -  Ali J." ,this);
        this.addKeyListener(new KeyInput(handler, this));
        handler.addObject(new Player(200, 500, ID.Player, handler,hud));
        handler.addObject(new Player(300,500, ID.Player2,handler,hud));
        handler.addObject(new BaseHQ(0,HEIGHT - 155, ID.HQBuilding, handler,hud));
        handler.addObject(new Base(128,HEIGHT - 155 , ID.Building, handler,hud));
        spawn = new Spawn(handler,hud,difficulty);
    }

    public synchronized void start(){
        running = true;
        thread = new Thread(this,"Game");
        thread.start();
    }

    public synchronized void stop(){
        running = false;
        endGameScreen();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void run() {
        long lastTime = System.nanoTime();
        double nsPerTick = 1000000000.0D/60.0D;//how many nano-seconds are in one tick

        int ticks = 0;//number of updates--->for calculating UPS-->updates per second
        int frames = 0;//number of frames--->for calculating FPS-->frames per second

        long lastTimer = System.currentTimeMillis();
        double delta = 0;
        while(running){
            long now = System.nanoTime();
            delta += (now - lastTime)/nsPerTick;/*this is going to add on to delta the difference and its going to divide it by a billion divided by 60*/
            lastTime = now;
            boolean shouldRender = true;
            while(delta >= 1){//this will only happen 60 times a second because of nsPerTick
                ticks++;
                tick();
                delta--;//so we get it back to zero
                shouldRender = true;
            }
            try {
                Thread.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //we're gonna limit the frames that we're going to render
            if (shouldRender){
                frames++;
                render();
            }
            if (System.currentTimeMillis() - lastTimer >= 1000){//if the current time,minus the last time that we updated is greater than or equal to a thousand,update--->update every second
                lastTimer += 1000;
                System.out.println(ticks + " ticks," + frames + " frames.");
                frames = 0;
                ticks = 0;
            }
            if (HUD.HEALTH <= 0 || hud.getScore() >= hud.getMaxScore())
                stop();
        }
        stop();
    }


    public void tick(){
        handler.tick();
        hud.tick();
        //explosion Update
        for (int i = 0; i <explosions.size();i++){
            boolean remove = explosions.get(i).tick();
            if (remove){
                explosions.remove(i);
                i--;
            }
        }
        //bullet update
        for (int i =0; i < bullets.size();i++){
            boolean remove = bullets.get(i).update();//this is for checking weather the bullet has reached the border, in which case we must remove it from the game(by removing it from the Arraylist)
            if (remove){
                bullets.remove(i);
                i--;
            }
        }
        for (int i = 0; i < handler.getObject().size(); i++){
            if (handler.getObject().get(i).getId() == ID.NormalEnemy || handler.getObject().get(i).getId() == ID.SmartEnemy || handler.getObject().get(i).getId() == ID.StrongEnemy ||
                    handler.getObject().get(i).getId() == ID.FastEnemy || handler.getObject().get(i).getId() == ID.FastEnemy2 || handler.getObject().get(i).getId() == ID.FastEnemy3 ||
                    handler.getObject().get(i).getId() == ID.SmartEnemy2 || handler.getObject().get(i).getId() == ID.SmartEnemy3 || handler.getObject().get(i).getId() == ID.SmartEnemy3){
                Enemy tempObject = (Enemy) handler.getObject().get(i);
                if (tempObject.isDead()){
                    explosions.add(new Explosion(handler.getObject().get(i).getX(), handler.getObject().get(i).getY() - 150));
                    hud.setScore(hud.getScore() + ((Enemy) handler.getObject().get(i)).getScore());
                    handler.getObject().remove(i);
                }
            }
            if (handler.getObject().get(i).getId() == ID.HQBuilding){
                BaseHQ tempObject = (BaseHQ) handler.getObject().get(i);
                if (tempObject.isDead()){
                    explosions.add(new Explosion(handler.getObject().get(i).getX(), handler.getObject().get(i).getY()-150));
                    handler.getObject().remove(i);
                }
            }
        }
    }

    public void render(){
        BufferStrategy bs = getBufferStrategy();
        if(bs == null){//if there is no bufferstrategy,we're going to create one
            createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        g.setColor(Color.green.darker().darker().darker().darker().darker());
        g.fillRect(0,0,WIDTH,HEIGHT);
        handler.render(g);
        hud.render(g);
        //draw explosions
        for (int i = 0; i < explosions.size();i++){
            explosions.get(i).render(g);
        }
        //draw Bullet
        for (int i = 0; i < bullets.size(); i++) {
            bullets.get(i).render((Graphics2D) g);
        }
        g.dispose();//we're disposing the graphics,because this will free up the memory in the graphics and free up any resources that the graphics object is using,because we wont be using it anymore-->we've done all our drawings for this loop
        bs.show();
    }


    public static float clamp( float var, float min, float max ){/*this method is mainly for our player class and for our player to not go out of the window we've created,lets say for the var parameter i enter x and for the min and max
    i would enter (in order) 0 and WIDTH,then whenever x would become equal or greater than WIDTH then this method would return the value of WIDTH so that our objects X parameter would never go out of the windows boundaries*/
        if (var >= max )
            return var = max;
        else if (var <= min )
            return var = min;
        else
            return var;
    }

    private void endGameScreen(){
        BufferStrategy bufferStrategy = getBufferStrategy();
        Graphics g = bufferStrategy.getDrawGraphics();
        g.setColor(Color.GREEN.darker().darker().darker().darker().darker().darker());
        g.fillRect(0 , 0 , WIDTH, HEIGHT);
        if (hud.HEALTH <= 0){
            displayLose(g);
        }else {
            displayWin(g);
        }
        g.dispose();
        bufferStrategy.show();

        try { Thread.sleep(2000); }
        catch (Exception e) {e.printStackTrace();	}

        switch(JOptionPane.showConfirmDialog(null, "Try again?", "Game Over", JOptionPane.YES_NO_OPTION)){
            case 0:
                restartGame();
                break;
            default:
                System.exit(0);
        }

        window.getFrame().dispose();
    }



    private void displayWin(Graphics g){
        try {
            BufferedImage a = ImageIO.read(new File("res/images/menu/win.gif"));
            g.drawImage(a, 250, 80, null);
        } catch (IOException e) {
            System.err.println("File Not Found!");
        }

    }

    private void displayLose(Graphics g){
        try {
            BufferedImage a = ImageIO.read(new File("res/images/menu/lose.gif"));
            g.drawImage(a, 250,80, null);
        } catch (IOException e) {
            System.err.println("File Not Found!");
        }
    }

    private void restartGame(){
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    TankMenu frame = new TankMenu();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Vector<Bullet> getBullets() {
        return bullets;
    }

    public void setBullets(Vector<Bullet> bullets) {
        this.bullets = bullets;
    }

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
