package PaooGame.Entity;
import PaooGame.Game;
import PaooGame.Levels.Level;
import PaooGame.Tiles.Tile;
import java.awt.geom.Rectangle2D;

import static PaooGame.Entity.Mouse.getyOffset;
import static PaooGame.Levels.Points.*;

public class Collision {
    public static boolean CanMoveHere(float x, float y,float width,float height,int[][] map)
    {
        if(!isSolid(x,y,map))
            if(!isSolid(x+width,y+height,map))
                if(!isSolid(x+width,y,map))
                    if(!isSolid(x,y+height,map))
                        return true;
        return false;

    }
    public static boolean isSolid(float x, float y, int [][] map)
    {
        int maxWidth=map[0].length*Tile.TILE_WIDTH;
        if(x<0 || x>=maxWidth)
            return true;
        if(y<0 || y>=Game.GetWndHeight())
            return true;
        float xIndex=x/ Tile.TILE_HEIGHT;
        float yIndex=y/ Tile.TILE_HEIGHT;
        if(map[(int)yIndex][(int)xIndex]==0)
            return false;
        return Tile.tiles[map[(int)yIndex][(int)xIndex]].IsSolid();
    }
   public static boolean IsEntityOnFloor(Rectangle2D.Float solidArea,int [][] map)
   {
       if(!isSolid(solidArea.x,solidArea.y+solidArea.height+1,map))
           if(!isSolid(solidArea.x+solidArea.width,solidArea.y+solidArea.height+1,map))
               return false;
       return true;
   }

   public static void IsFish(Rectangle2D.Float solidArea,Level level)
   {
       float xIndex= solidArea.x/ Tile.TILE_HEIGHT;
       float yIndex= solidArea.y/ Tile.TILE_HEIGHT;
       if(level.getMap()[(int)yIndex][(int)xIndex]==Tile.peste.GetId()) {
           level.setId((int) yIndex, (int) xIndex, 0);
           addPointsFish();
       }
   }
    public static void IsBone(Rectangle2D.Float solidArea,Level level)
    {
        float xIndex= solidArea.x/ Tile.TILE_HEIGHT;
        float yIndex= solidArea.y/ Tile.TILE_HEIGHT;
        if(level.getMap()[(int)yIndex][(int)xIndex]==Tile.bone.GetId()) {
            level.setId((int) yIndex, (int) xIndex, 0);
            addBone();
        }
    }
    public static void IsMouse(Rectangle2D.Float solidArea,Mouse mouse)
    {
        if(mouse.isMouse && (int)solidArea.x==mouse.x && (int)solidArea.y== mouse.y-getyOffset())  {
            addPointsMouse();
            mouse.isMouse=false;
        }
    }

}
