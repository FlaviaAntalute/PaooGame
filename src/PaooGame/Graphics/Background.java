package PaooGame.Graphics;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static PaooGame.Graphics.Assets.*;
import static PaooGame.Useful.Constants.BGConstants.*;

public class Background {

    public static void drawBgT(Graphics g, int lvlOffset)
    {
        for(int i=0;i<4;++i) {
            g.drawImage(bg1, i*WIDTH, 0, WIDTH, HEIGHT, null);
            g.drawImage(bg2, i*WIDTH-(int)(lvlOffset*0.1), 0, WIDTH, HEIGHT, null);
            g.drawImage(bg3, i*WIDTH-(int)(lvlOffset*0.2), 0, WIDTH, HEIGHT, null);
            g.drawImage(bg4, i*WIDTH-(int)(lvlOffset*0.3), 0, WIDTH, HEIGHT, null);
        }
    }

    public static void drawBgW(Graphics g, int xLvlOffset) {
        for(int i=0;i<1;++i)
            g.drawImage(bgw, i*WIDTH, 40, WIDTH*2+50, HEIGHT, null);

        g.drawImage(star1,150-(int)(xLvlOffset*0.15),120,50,50,null);
        g.drawImage(star1,350-(int)(xLvlOffset*0.15),70,50,50,null);
        g.drawImage(star1,560-(int)(xLvlOffset*0.15),140,50,50,null);
        g.drawImage(star1,770-(int)(xLvlOffset*0.15),200,50,50,null);
        g.drawImage(star1,890-(int)(xLvlOffset*0.15),90,50,50,null);


    }
}
