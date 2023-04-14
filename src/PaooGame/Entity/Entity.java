package PaooGame.Entity;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Entity {
    public int x;
    public int y;
    public int speed;
    public String direction;
    public int counter=0;
    public int num=1;
    public String lastPressed="right";
    private static Rectangle2D.Float solidArea;
    public static int Height=48;
    public static int Width=48;
    public static int xOffset=12;
    public static int yOffset=12;

    public Entity(int x, int y, int s)
    {
        this.x=x;
        this.y=y;
        this.speed=s;
        direction="right";

    }

    protected void initSolidArea(float x,float y, float width,float height)
    {
        solidArea=new Rectangle2D.Float(x,y,width,height);
    }
    public Rectangle2D.Float getSolidArea() {
        return solidArea;
    }

    protected void updateNum() {
        if(num==1) {
            num = 2;
        }
        else if(num==2) {
            num = 3;
        }
        else if(num==3)
        {
            num=4;
        }
        else if(num==4)
        {
            num=1;
        }
    }
}
