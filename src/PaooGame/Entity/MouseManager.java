package PaooGame.Entity;

import PaooGame.Levels.Level;
import PaooGame.Tiles.Tile;

import java.awt.*;
import java.util.ArrayList;

public class MouseManager {
    private ArrayList<Mouse> mouse=new ArrayList<>();

    public MouseManager(Level level)
    {
        addMouse(level);
    }

    public void addMouse(Level level) {
        for (int i = 0; i < level.getMap().length; i++) {
            for (int j = 0; j < level.getMap()[0].length; ++j) {
                if (level.getMap()[i][j] == Tile.mouse.GetId()) {
                    mouse.add(new Mouse(j * Tile.TILE_HEIGHT, i * Tile.TILE_WIDTH,1,"right"));
                }
            }
        }
    }

    public void draw(Graphics g,int xLvlOffset)
    {
        for(Mouse m: mouse)
            m.draw(g,xLvlOffset);
    }
    public void update(){
        for(Mouse m: mouse)
            m.update();
    }

    public void reset()
    {
        for(Mouse m: mouse)
            m.resetAll();
    }

    public ArrayList<Mouse> getMouse() {
        return mouse;
    }
}
