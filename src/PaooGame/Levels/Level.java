package PaooGame.Levels;

import PaooGame.Graphics.Map;
import PaooGame.Tiles.Tile;

public class Level {
    private Map m;
    private String path;
    private int pointsNeded=0;
    private boolean win=false;
    private int lvlTilesWide;// numărul de dale din nivel pe axa X
    private int maxTilesOffset ;// numărul maxim de dale cu care se poate muta nivelul într-o parte sau alta
    private int maxLvlOffsetX;// numărul maxim de pixeli cu care se poate muta nivelul într-o parte sau alta
    public Level(int pointsNeded,String path) {
        this.path=path;
        this.m=new Map(path);
        this.pointsNeded=pointsNeded;
        //ceateEnemies();
        calcLvlOffsets();
    }

    private void calcLvlOffsets() {
        lvlTilesWide=m.getMap()[0].length;
        maxTilesOffset=lvlTilesWide- Tile.NrTileWidth;
        maxLvlOffsetX=Tile.TILE_WIDTH;
    }

    public int getMaxLvlOffsetX() {
        return maxLvlOffsetX;
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
    public void setWin(){
        win=true;
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

