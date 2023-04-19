package PaooGame.Entity;
import PaooGame.Tiles.Tile;

import java.awt.geom.Rectangle2D;
import java.util.Objects;

import static PaooGame.Entity.Collision.*;

public abstract class Enemy extends Entity{
    protected int enemyType;
    protected boolean first=true;
    protected boolean inAir=false;
    protected float fall;
    protected float gravity=0.04f;
    protected String lastDir="left";
    protected float attackRange=Tile.TILE_WIDTH+5;
    protected int tileY;
    public Enemy(int x, int y, int s, int type,String dir) {
        super(x, y, s,dir);
        this.enemyType=type;
        initSolidArea(x,y,Width,Height);
    }
    protected void firstUpdate(int [][] map)
    {
        if (!IsEntityOnFloor(solidArea, map))
            inAir = true;
        first=false;
    }
    protected void InAir(int[][]map)
    {
        if (CanMoveHere(solidArea.x, solidArea.y+fall, solidArea.width, solidArea.height, map)) {
            solidArea.y += fall;
            fall += gravity;
        } else {
            inAir = false;
        }
    }
    protected void moveEnemy(int[][] map)
    {
        float xSpeed=0;

        if(Objects.equals(direction, "left"))
            xSpeed = -speed;
        else
            xSpeed=speed;

        if(CanMoveHere(solidArea.x+xSpeed,solidArea.y,solidArea.width,solidArea.height,map)) {
            if (IsFloor(solidArea, xSpeed, direction,map)) {
                solidArea.x += xSpeed;
                return;
            }
        }
        changeDirection();
    }
    protected boolean PlayerIsClose(int [][] map, Player player)
    {
        int yIndexEnemy= (int)solidArea.y/ Tile.TILE_HEIGHT;
        int yIndexPlayer= (int)player.solidArea.y/ Tile.TILE_HEIGHT;
        if(yIndexPlayer==yIndexEnemy)
            if(IsPlayerInRange(player))
                return IsClear(map, solidArea, player.getSolidArea(), yIndexEnemy);
        return false;
    }

    protected boolean IsPlayerInRange(Player player) {
        int distance=(int)Math.abs(player.solidArea.x-solidArea.x);
        return distance<=attackRange*5;
    }
    protected void TurnToPlayer(Player player)
    {
        if(player.solidArea.x>solidArea.x)
            direction="right";
        else
            direction="left";
    }
    protected boolean CanAttack(Player player) {
            int distance = (int) Math.abs(player.solidArea.x - solidArea.x);
            return distance <= attackRange;
    }

    void changeDirection() {
        if (Objects.equals(direction, "left"))
            direction = "right";
        else
            direction = "left";
    }

    public void updateCounter() {
        counter++;
        if (counter > 15) {
            this.updateNum();
            counter = 0;
        }

    }
    protected void updateNum() {
        if(num==1)
            num = 2;
        else if(num==2)
            num = 3;
        else if(num==3)
            num=4;
        else if(num==4)
            num=5;
        else if (num==5) {
            num = 1;
            if (Objects.equals(direction, "attack"))
                direction = "idle";
        }
    }
    public String getDirection()
    {
        return direction;
    }
}
