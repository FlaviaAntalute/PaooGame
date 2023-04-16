package PaooGame.Levels;

import java.awt.*;

import static java.awt.Font.BOLD;

public class Points {
    private static int Points=0;
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
        g.drawChars(msg,0, msg.length, 10,20);
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
