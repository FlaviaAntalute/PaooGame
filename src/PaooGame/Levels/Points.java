package PaooGame.Levels;

import java.awt.*;

import static java.awt.Font.BOLD;

public class Points {
    private  int Points=0;
//    private  boolean isBone=false;
    public Points()
    {
//        isBone=false;
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


    public int getPoints()
    {
        return Points;
    }
    public void setPoints(int p)
    {
         Points=p;
    }
//    public void setBone(boolean p)
//    {
//        isBone=p;
//    }
//
//    public boolean getBone() {
//        return isBone;
//    }
}
