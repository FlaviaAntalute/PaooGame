package PaooGame.Levels;

import java.awt.*;

import static java.awt.Font.BOLD;

public class Points {
    private  int Points=0;
    private  boolean isBone=false;
    public Points()
    {
        isBone=false;
        Points =0;
    }
    public  void addPointsFish()
    {
        Points++;
    }
    public  void addPointsMouse()
    {
        Points+=5;
    }
    public  void PrintPoints(Graphics g,Level level)
    {
        Font f1=new Font("font1", BOLD,18);
        char []msg=("Points:  "+Points+"/"+level.getPoints()).toCharArray();
        g.setColor(Color.RED);
        g.setFont(f1);
        g.drawChars(msg,0, msg.length, 120,35);
    }
    public  void PrintBone(Graphics g)
    {
        Font f1=new Font("font1", BOLD,18);
        int is=0;
        if(isBone)
            is=1;
        char []msg=("Bone:  "+is).toCharArray();
        g.setColor(Color.RED);
        g.setFont(f1);
        g.drawChars(msg,0, msg.length, 240,35);
    }
    public  void addBone()
    {
        isBone=true;
    }
    public  boolean wining(Graphics g,Level level)
    {
        if(level.getPoints()==Points)
            level.setWin();
        else
            return false;

        return true;
    }
    public int getPoints()
    {
        return Points;
    }
    public void setPoints(int p)
    {
         Points=p;
    }
    public void setBone(boolean p)
    {
        isBone=p;
    }

 }
