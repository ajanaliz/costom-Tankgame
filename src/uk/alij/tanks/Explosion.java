package uk.alij.tanks;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Vector;

/**
 * Created by Ali J on 4/29/2015.
 */
public class Explosion {

    private double x,y;
    private int explodeFrameNr;
    private Vector<BufferedImage> explosionFrames;

    public Explosion(double x,double y){
        this.x = x;
        this.y = y;
        SoundEffect.EXPLOSION1.play();
        explosionFrames = new Vector<BufferedImage>();
        loadImages();
    }

    public void loadImages(){
        try {
            for(int i = 1; i < 24; i++){
                //Image a = (Image)new ImageIcon(getClass().getResource("images/Explosion/" + i + ".gif")).getImage();
                BufferedImage a = ImageIO.read(new File("res/images/Explosion/" + i + ".gif"));
                explosionFrames.add(a);
            }
        } catch (Exception e) { e.printStackTrace(); }
    }// end loadImages()

    public boolean tick(){
        if(explodeFrameNr >= (explosionFrames.size()-1)){
            return true;
        }else{	explodeFrameNr++; }
        return false;
    }

    public void render(Graphics g){
        g.drawImage(explosionFrames.get(explodeFrameNr), (int) x, (int) y, null);
    }
}
