package PaooGame.Entity;
import PaooGame.Game;
import PaooGame.Inputs.KeyHandler;
import PaooGame.Levels.Level;
import PaooGame.Tiles.Tile;
import java.awt.geom.Rectangle2D;

import static PaooGame.Entity.Mouse.getyOffset;
import static PaooGame.Useful.Constants.PlayerConstants.STARTX;
import static PaooGame.Useful.Constants.PlayerConstants.STARTY;

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

    public static boolean IsTileSolid(int x, int y, int[][] map)
    {
        if(map[(int)y][(int)x]==0)
            return false;
        return Tile.tiles[map[(int)y][(int)x]].IsSolid();

    }
   public static boolean IsEntityOnFloor(Rectangle2D.Float solidArea,int [][] map)
   {
       if(!isSolid(solidArea.x,solidArea.y+solidArea.height+1,map))
           if(!isSolid(solidArea.x+solidArea.width,solidArea.y+solidArea.height+1,map))
               return false;
       return true;
   }

   public static void IsFish(Rectangle2D.Float solidArea,Level level,Player player)
   {
       float xIndex= solidArea.x/ Tile.TILE_HEIGHT;
       float yIndex= solidArea.y/ Tile.TILE_HEIGHT;
       if(level.getMap()[(int)yIndex][(int)xIndex]==Tile.peste.GetId()) {
           level.setId((int) yIndex, (int) xIndex, 0);
           player.points.addPointsFish();
       }
   }
    public static void IsBone(Rectangle2D.Float solidArea, Level level, Player player, KeyHandler keyH)
    {
        float xIndex= solidArea.x/ Tile.TILE_HEIGHT;
        float yIndex= solidArea.y/ Tile.TILE_HEIGHT;
        if(level.getMap()[(int)yIndex][(int)xIndex]==Tile.bone.GetId() && player.collectPressed) {
            level.setId((int) yIndex, (int) xIndex, 0);
            player.points.addBone();
        }
    }
    public static void IsMouse(Rectangle2D.Float solidArea,Mouse mouse,Player player)
    {
        if(mouse.isMouse && (int)solidArea.x==mouse.x && (int)solidArea.y== mouse.y-getyOffset())  {
            player.points.addPointsMouse();
            mouse.isMouse=false;
        }
    }
    public static boolean IsFloor(Rectangle2D.Float solidArea,float xSpeed,String dir ,int [][] map)
    {
        if(dir=="left")
            return isSolid(solidArea.x+xSpeed,solidArea.y+ solidArea.height+1,map);
        else
            return isSolid(solidArea.x+xSpeed+solidArea.width,solidArea.y+ solidArea.height+1,map);
    }
    public static void IsWater(Player player ,int [][] map )
    {
        float xIndex= player.getSolidArea().x/ Tile.TILE_HEIGHT;
        float yIndex= player.getSolidArea().y/ Tile.TILE_HEIGHT;
        if(map[(int)yIndex][(int)xIndex]==Tile.waterTile.GetId() || map[(int)yIndex][(int)xIndex]==Tile.waterTile.GetId()
                || map[(int)yIndex][(int)xIndex]==Tile.waterTile1.GetId()
                || map[(int)yIndex][(int)xIndex]==Tile.waterTile2.GetId()) {
           player.changeLife();
           player.changeCoord(STARTX,STARTY);
        }

    }
    public static boolean IsClear(int[][] map, Rectangle2D.Float solidArea, Rectangle2D.Float solidArea1, int yIndexEnemy) {
        int XTile=(int)solidArea.x/Tile.TILE_HEIGHT;
        int XTile1=(int)solidArea1.x/Tile.TILE_HEIGHT;
        if(XTile>XTile1)
        {
            for(int i=0;i<XTile-XTile1;++i)
            {
                if(IsTileSolid(XTile1+i,yIndexEnemy,map))
                    return false;
                if(!IsTileSolid(XTile1+i,yIndexEnemy+1,map))
                    return false;
            }
        }
        else {
            for(int i=0;i<XTile1-XTile;++i)
            {
                if(IsTileSolid(XTile+i,yIndexEnemy,map))
                    return false;
                if(!IsTileSolid(XTile+i,yIndexEnemy+1,map))
                    return false;
            }
        }
        return true;
    }

}
