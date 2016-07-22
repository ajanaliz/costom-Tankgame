package uk.alij.tanks;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Collections;

/**
 * Created by Ali J on 4/29/2015.
 */
public class KeyInput implements KeyListener {//this class is going to handle the keyboard inputs

    private GamePanel gamePanel;
    private boolean[] keyDown = new boolean[6];
    private Handler handler;

    public KeyInput(Handler handler, GamePanel gamePanel) {
        this.handler = handler;
        this.gamePanel = gamePanel;
        KeyboardFocusManager kfm = KeyboardFocusManager.getCurrentKeyboardFocusManager();
        kfm.setDefaultFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.EMPTY_SET);
        kfm.setDefaultFocusTraversalKeys(KeyboardFocusManager.BACKWARD_TRAVERSAL_KEYS, Collections.EMPTY_SET);
        /*AWT intercepts tab presses, so it can move focus between components-->therefor i disabled this ability*/
        keyDown[0] = false;//w key
        keyDown[1] = false;//s key
        keyDown[2] = false;//d key
        keyDown[3] = false;//a key
        keyDown[4] = false;//TAB key
        keyDown[5] = false;//m key
    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();//when we press a key on the keyboard its going to release a number value corresponding to the key pressed
/*now we get to the point where we need to differentiate the players in our key input,how do we know if we hit the W key,we only want Player1 to go up,not Player2-->what we need to do is
* we need to loop through all of our objects in the game and say:which one has the ID of Player and that if it has the ID of player,then move the Player Object --> thats why we need the handler here*/
        for (int i = 0; i < gamePanel.getHandler().getObject().size(); i++) {
            GameObject tempObject = gamePanel.getHandler().getObject().get(i);
            if (!keyDown[4]){
                if (tempObject.getId() == ID.Player ) {
                    //all of the key events for Player
                    if (key == KeyEvent.VK_UP) {
                        tempObject.setVelY(-5);
                        keyDown[0] = true;
                        ((Player)(tempObject)).setDirection(0);
                    }
                    if (key == KeyEvent.VK_DOWN){
                        tempObject.setVelY(5);
                        keyDown[1] = true;
                        ((Player)(tempObject)).setDirection(3);
                    }
                    if (key == KeyEvent.VK_RIGHT) {
                        tempObject.setVelX(5);
                        keyDown[2] = true;
                        ((Player)(tempObject)).setDirection(2);
                    }
                    if (key == KeyEvent.VK_LEFT){
                        tempObject.setVelX(-5);
                        keyDown[3] = true;
                        ((Player)(tempObject)).setDirection(1);
                    }
                    if (key == KeyEvent.VK_SPACE){
                        gamePanel.getBullets().add(new Bullet(((Player) tempObject).getDirection(),(int)tempObject.getX() + 32,(int)tempObject.getY() + 32, handler));
                    }
                    break;
                }
            }else {
                if (tempObject.getId() == ID.Player2 ){
                    //all of the key events for Player
                    if (key == KeyEvent.VK_UP) {
                        tempObject.setVelY(-5);
                        keyDown[0] = true;
                        ((Player)(tempObject)).setDirection(0);
                    }
                    if (key == KeyEvent.VK_DOWN){
                        tempObject.setVelY(5);
                        keyDown[1] = true;
                        ((Player)(tempObject)).setDirection(3);
                    }
                    if (key == KeyEvent.VK_RIGHT) {
                        tempObject.setVelX(5);
                        keyDown[2] = true;
                        ((Player)(tempObject)).setDirection(2);
                    }
                    if (key == KeyEvent.VK_LEFT){
                        tempObject.setVelX(-5);
                        keyDown[3] = true;
                        ((Player)(tempObject)).setDirection(1);
                    }
                    if (key == KeyEvent.VK_SPACE){
                        gamePanel.getBullets().add(new Bullet(((Player) tempObject).getDirection(),(int)tempObject.getX() + 32,(int)tempObject.getY() + 32, handler));
                    }
                }
            }
        }
        if (key == KeyEvent.VK_TAB){
            keyDown[4] = !keyDown[4];
        }
        if (key == KeyEvent.VK_M){
            keyDown[5] = !keyDown[5];
        }
        if (key == KeyEvent.VK_ESCAPE) System.exit(1);
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        for (int i = 0; i < gamePanel.getHandler().getObject().size(); i++) {
            GameObject tempObject = gamePanel.getHandler().getObject().get(i);
            if (tempObject.getId() == ID.Player || tempObject.getId() == ID.Player2) {
                //all of the key events for Player
                if (key == KeyEvent.VK_UP) keyDown[0] = false; //tempObject.setVelY(0);
                else if (key == KeyEvent.VK_DOWN) keyDown[1] = false; //tempObject.setVelY(0);
                else if (key == KeyEvent.VK_RIGHT) keyDown[2] = false; //tempObject.setVelX(0);
                else if (key == KeyEvent.VK_LEFT) keyDown[3] = false; //tempObject.setVelX(0);

                //Vertical Movement

                if ( !keyDown[0] && !keyDown[1] )
                    tempObject.setVelY(0);
                if ( !keyDown[2] && !keyDown[3])
                    tempObject.setVelX(0);
                if (key == KeyEvent.VK_SPACE){

                }
                if (keyDown[5]){
                    SoundEffect.volume = SoundEffect.volume.MUTE;
                }else {
                    SoundEffect.volume = SoundEffect.Volume.LOW;
                }
            }
        }
    }
}
