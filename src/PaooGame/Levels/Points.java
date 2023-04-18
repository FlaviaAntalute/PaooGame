package PaooGame.Levels;

import java.awt.*;

import static java.awt.Font.BOLD;

public class Points {
    private static int Points=0;
    private static boolean isBone=false;
    public static void addPointsFish()
    {
        Points++;
    }
    public static void addPointsMouse()
    {
        Points+=5;
    }
    public static void PrintPoints(Graphics g,Level level)
    {
        Font f1=new Font("font1", BOLD,18);
        char []msg=("Points:  "+Points+"/"+level.getPoints()).toCharArray();
        g.setColor(Color.ORANGE);
        g.setFont(f1);
        g.drawChars(msg,0, msg.length, 100,25);
    }
    public static void PrintBone(Graphics g)
    {
        Font f1=new Font("font1", BOLD,18);
        int is=0;
        if(isBone)
            is=1;
        char []msg=("Bone:  "+is).toCharArray();
        g.setColor(Color.ORANGE);
        g.setFont(f1);
        g.drawChars(msg,0, msg.length, 210,25);
    }
    public static void addBone()
    {
        isBone=true;
    }
    public static boolean wining(Graphics g,Level level)
    {
        if(level.getPoints()==Points)
            level.setWin();
        else
            return false;

        return true;
    }
    public static int getPoints()
    {
        return Points;
    }
 }
