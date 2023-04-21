package PaooGame.Entity;
import PaooGame.GameStates.Playing;
import PaooGame.Tiles.Tile;
import java.awt.*;
import java.awt.geom.Rectangle2D;
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
           if(m.isAlive)
                m.drawMax(g,xLvlOffset);
    }

    public void update(Player player)
    {
        for(Max m: max) {
            if(m.isAlive)
                m.update(playing.level1.getMap(), player);
        }
    }
    public void CheckHit(Rectangle2D.Float attackArea,Player player)
    {
        for (Max m : max)
            if(m.isAlive) {
                if (attackArea.intersects(m.getSolidArea())) {
                    changeEnemyLife(m);
                }
            }
    }
    public void changeEnemyLife(Max m)
    {
        m.lives--;
        if(m.lives==0) {
            m.direction = "dead";
            m.isAlive=false;
        }
        else
            m.direction="hurt";

    }

    public void resetAll() {
        for(Max m: max)
            m.resetEnemy();
    }

    public boolean allEnemyAreDead() {
        for(Max m : max)
            if (m.getIsAlive())
                return false;

        return true;
    }
}
