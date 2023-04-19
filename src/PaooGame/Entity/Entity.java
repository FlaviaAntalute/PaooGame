package PaooGame.Entity;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public abstract class Entity {
    protected int x;
    protected int y;
    protected int speed;
    protected int Height=48;
    protected int Width=48;
    protected String direction;
    protected int counter=0;
    protected int num=1;
    protected String lastPressed="right";
    protected  Rectangle2D.Float solidArea;
    protected static int xOffset=12;
    protected static int yOffset=12;

    public Entity(int x, int y, int s,String dir)
    {
        this.x=x;
        this.y=y;
        this.speed=s;
        this.direction=dir;
    }

    protected void initSolidArea(float x,float y, float width,float height)
    {
        solidArea=new Rectangle2D.Float(x,y,width,height);
    }
    public Rectangle2D.Float getSolidArea() {
        return solidArea;
    }


}
