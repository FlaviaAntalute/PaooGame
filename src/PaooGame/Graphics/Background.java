package PaooGame.Graphics;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static PaooGame.Graphics.Assets.*;

public class Background {

    public static void drawBgT(Graphics g, int lvlOffset)
    {
        for(int i=0;i<3;++i) {
            g.drawImage(bg1, i*407, 0, 407, 624, null);
            g.drawImage(bg2, i*407-(int)(lvlOffset*0.3), 0, 407, 624, null);
            g.drawImage(bg3, i*407-(int)(lvlOffset*0.3), 0, 407, 624, null);
            g.drawImage(bg4, i*407-(int)(lvlOffset*0.3), 0, 407, 624, null);
        }
    }
}
