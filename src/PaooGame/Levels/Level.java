package PaooGame.Levels;

import PaooGame.Graphics.Map;

public class Level {
    private Map m;
    private int pointsNeded=0;
    private boolean win=false;
    public Level(Map map,int pointsNeded) {
        this.m=map;
        this.pointsNeded=pointsNeded;
    }

    public int[][] getMap() {
        return this.m.getMap();
    }
    public void setId(int x, int y, int val)
    {
        m.setId(x,y,val);
    }
    public int getPoints(){
        return pointsNeded;
    }
    public void setWin(){
        win=true;
    }
}

