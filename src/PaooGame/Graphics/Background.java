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
}
