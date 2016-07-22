package uk.alij.tanks;

import java.awt.*;

/**
 * Created by Ali J on 4/29/2015.
 */
public class HUD {



    public static float HEALTH;/*its not usual to use static methods or variables in your program but since we are not going to have any other health variable in our game whatsoever and this just makes an ease of access
    to that so instead of having to initialize the variable of HUD or initialize that instance of the class we can now just use HUD.HEALTH,we dont need to initialize it nor do we need to say HUD = new HUD-->which is very handy*/

    private float greenValue;

    private int score;

    private int maxScore;

    private String difficulty;
    public HUD(String difficulty){
        greenValue = 255f;
        HEALTH = 100;
        score = 0;
        maxScore = 100;
        this.difficulty = difficulty;
    }


    public  void tick(){//where the process is done
        HEALTH = GamePanel.clamp(HEALTH , 0 , 100);
        greenValue = GamePanel.clamp(greenValue, 0 , 255);

        greenValue = HEALTH*2;
    }

    public void render(Graphics g){//where we draw the graphics for our object
        g.setColor(Color.gray);
        g.fillRect(15 , 15, 200 , 32);
        g.setColor(new Color(75 ,(int) greenValue  , 0));
        g.fillRect(15 , 15, (int)HEALTH * 2 , 32);
        g.setColor(Color.white);
        g.drawRect(15, 15, 200, 32);

        g.setColor(Color.WHITE);
        g.setFont(new Font("Century Gothic" , Font.PLAIN, 16));
        String s = "Score: " + score;
//        int length = (int) g.getFontMetrics().getStringBounds(s , g).getWidth();
        g.drawString(s , 15 , 64);
//        g.drawString("Score: " + score , 15, 64);
        g.drawString("Difficulty: " + difficulty , 15 , 80);
    }

    public int getMaxScore() {
        return maxScore;
    }

    public void setMaxScore(int maxScore) {
        this.maxScore = maxScore;
    }

    public void setScore(int score){
        this.score = score;
    }

    public int getScore(){
        return score;
    }
}
