package PaooGame.Entity;

import PaooGame.Graphics.Assets;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Mouse extends Entity{
    private int Width=30;
    private int Height=20;
    private int times=0;
    protected boolean isMouse=true;
    private static int yOffset=17;
    public Mouse(int x, int y, int s,String dir) {
        super(x, y, s,dir);
    }

    public void draw(Graphics g, int lvlOffset) {
        BufferedImage image = Assets.mouse[0];
        if(direction=="right")
        {
            if(num==1)
                image=Assets.mouse[0];
            if(num==2)
                image=Assets.mouse[1];
            if(num==3)
                image=Assets.mouse[2];
        }
        else if(direction=="left")
        {
            if(num==1)
                image=Assets.mouse[3];
            if(num==2)
                image=Assets.mouse[4];
            if(num==3)
                image=Assets.mouse[5];
        }
        if(isMouse)
            g.drawImage(image,x-lvlOffset,y, Width, Height, null);
    }
    public void update(){

        if(times<=100) {
            direction="right";
            x += speed;
        }
        if(times>100 && times<=200) {
            direction="left";
            x -= speed;
        }
        if(times==200)
            times=0;
        times++;
        updateCounter();

    }
    protected void updateNum() {
        if(num==1)
            num = 2;
        else if(num==2)
            num = 3;
        else if(num==3)
            num=1;
    }

    private void updateCounter() {
        counter++;
        if (counter > 9) {
            this.updateNum();
            counter = 0;
        }
    }
    public static int getyOffset() {
        return yOffset;
    }

    public void resetAll() {
        isMouse=true;
        times=0;
        x=10;
    }
}
