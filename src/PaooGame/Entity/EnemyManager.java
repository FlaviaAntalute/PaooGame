package PaooGame.Entity;

import PaooGame.GameStates.Playing;
import PaooGame.Tiles.Tile;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class EnemyManager {
    private Playing playing;
    private ArrayList<Max> max=new ArrayList<>();

    public EnemyManager(Playing playing)
    {
        this.playing=playing;
        addEnemies();
    }
    private void addEnemies() {
        for(int i=0;i<playing.level1.getMap().length;i++) {
            for (int j = 0; j < playing.level1.getMap()[0].length; ++j) {
                if (playing.level1.getMap()[i][j] == Tile.max.GetId()) {
                    max.add( new Max(j * Tile.TILE_HEIGHT, i * Tile.TILE_WIDTH, 2, "idle"));

                }
            }
        }
    }
    public void draw(Graphics g, int xLvlOffset) {
       for(Max m: max)
           m.drawMax(g,xLvlOffset);
    }

    public void update()
    {
        for(Max m: max)
            m.update();
    }
}
