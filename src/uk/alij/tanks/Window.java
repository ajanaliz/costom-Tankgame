package uk.alij.tanks;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Ali J on 4/29/2015.
 */
public class Window {
    private JFrame frame;

    public Window(int width, int height , String title, GamePanel game){
        frame = new JFrame(title);

        frame.setPreferredSize(new Dimension(width, height));
        frame.setMaximumSize(new Dimension(width,height));
        frame.setMinimumSize(new Dimension(width,height));

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//having the x button working
        frame.setResizable(false);//can we resize our window?fuck no
        frame.setLocationRelativeTo(null);//automatically our window would go on the top left corner of the screen setting this to null will make it appear on the middle of the screen
        frame.add(game);//here we're adding our game class into our frame

        frame.setVisible(true);//we're setting the frame to visible so we can actually see it
        game.start();//then we're going to run the start method we implement in game

        game.setFocusable(true);
        game.requestFocus();
        System.out.println(game.isFocusable());

    }
    public JFrame getFrame() {
        return frame;
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }
}
