package PaooGame.Entity;

import PaooGame.Graphics.Assets;

import java.awt.*;
import java.awt.image.BufferedImage;

import static PaooGame.Useful.Constants.EnemyConstants.MAX;

public class Max extends Enemy{
    private static int Width=68;
    private int times=0;
    private static int Height=58;
    protected static int yOffset=20;//CA SA FIE DESENAT PE PAMANT
    public Max(int x, int y, int s,String dir) {
        super(x, y, s, MAX,dir);
    }

    public static int GetYOffset() {
        return yOffset;
    }

    public void drawMax(Graphics g,int xLvlOffset){
        BufferedImage image=null;

        if (direction=="idle") {
            if (num == 1)
                image = Assets.Max[10];
            if (num == 2)
                image = Assets.Max[11];
            if (num == 3)
                image = Assets.Max[12];
            if (num == 4)
                image = Assets.Max[13];
        } else if (direction=="right") {
            if (num == 1)
                image = Assets.Max[0];
            if (num == 2)
                image = Assets.Max[1];
            if (num == 3)
                image = Assets.Max[2];
            if (num == 4)
                image = Assets.Max[3];
        } else if (direction=="left") {
                if(num==1)
                    image=Assets.Max[4];
                if(num==2)
                    image=Assets.Max[5];
                if(num==3)
                    image=Assets.Max[6];
                if(num==4)
                    image=Assets.Max[7];
        }
        g.drawImage(image,(int)getSolidArea().x-xLvlOffset,(int)getSolidArea().y, Width, Height, null);
    }

}
