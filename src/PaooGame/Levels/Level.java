package PaooGame.Levels;

import PaooGame.Graphics.Map;


public class Level {
    private Map m;
    private String path;
    private int pointsNeded=0;

    public Level(int pointsNeded,String path) {
        this.path=path;
        this.m=new Map(path);
        this.pointsNeded=pointsNeded;
    }

    public int[][] getMap() {
        return this.m.getMap();
    }
    public Map GetMap(){
        return m;
    }
    public void setId(int x, int y, int val)
    {
        m.setId(x,y,val);
    }
    public int getPoints(){
        return pointsNeded;
    }

    public void resetAll(String path,int points) {
        Map temp=new Map(path);
        this.path=path;
        this.m=temp;
        pointsNeded=points;

    }
    public String getPath() {
        return path;
    }
}

