package PaooGame.Entity;

import static PaooGame.Entity.Collision.*;

public abstract class Enemy extends Entity{
    private int enemyType;
    private boolean first=true;
    private boolean inAir=false;
    private float fall;
    private float gravity=0.04f;
    public Enemy(int x, int y, int s, int type,String dir) {
        super(x, y, s,dir);
        this.enemyType=type;
        initSolidArea(x,y,Width,Height);
    }

    public void update(int [][] map){
        updateWalk(map);
        updateCounter();
    }
    public void updateWalk(int [][] map)
    {
        if(first) {
            if (!IsEntityOnFloor(solidArea, map)) {
                inAir = true;
            }
            first=false;
        }
        if(inAir) {
            if (CanMoveHere(solidArea.x, solidArea.y+fall, solidArea.width, solidArea.height, map)) {
                solidArea.y += fall;
                fall += gravity;
            } else {
                inAir = false;
            }
        }else
            {
                float xSpeed=0;

                if(direction=="idle")
                    direction="left";
                else if(direction=="left")
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
    }

    private void changeDirection() {
        if (direction == "left")
            direction = "right";
        else {
            direction = "left";
        }
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
        else if (num==5)
            num=1;
    }
    public String getDirection()
    {
        return direction;
    }
}
