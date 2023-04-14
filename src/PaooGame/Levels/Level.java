package PaooGame.Levels;

import PaooGame.Graphics.Map;

public class Level {
    private Map m;

    public Level(Map map) {
        this.m=map;
    }

    public int[][] getMap() {
        return this.m.getMap();
    }
}

