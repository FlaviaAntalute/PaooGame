package PaooGame.Entity;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.Objects;

import PaooGame.Game;
import PaooGame.GameStates.Playing;
import PaooGame.Graphics.Assets;
import PaooGame.Inputs.KeyHandler;
import PaooGame.Levels.Level;
import PaooGame.Levels.Points;

import javax.xml.transform.SourceLocator;

import static PaooGame.Entity.Collision.*;
import static PaooGame.Useful.Constants.PlayerConstants.*;

public class Player extends Entity {
    private KeyHandler keyH;
    public boolean leftPressed,rightPressed,upPressed,attackPressed,collectPressed;
    private int [][] map;
    //jumping or falling
    private float airSpeed=0f;
    private final float gravity=0.06f;
    private final float jumpSPEED=-2.8f;
    protected  int lives=3;
    private final float fallSpeedAfterCollision=0.5f;
    private boolean inAir=false;
    //attack box
    private Rectangle2D.Float attackArea;
    private boolean attackChecked;
    private Playing playing;
    public static Points points;
    public Player( int x,int y,int speed,String dir,KeyHandler keyH, int[][] map,Playing playing) {
        super(x, y,speed,dir);
        points=new Points();
        this.keyH = keyH;
        this.playing=playing;
        initSolidArea(x,y,25,30);
        initAttackArea();
        if (!IsEntityOnFloor(getSolidArea(), map))
            inAir = true;
    }

    private void initAttackArea() {
        attackArea=new Rectangle2D.Float(x,y,20,20);
    }

    public void loadMap(int[][] map)
    {
        this.map=map;
    }
    public void update(Level level,Mouse mouse,EnemyManager enemyManager) {

            if(points.getPoints()==level.getPoints() && enemyManager.allEnemyAreDead())
                playing.getGameWon(true);

            if (lives <= 0) {
                playing.SetGameOver(true);
                direction = "death";
                return;
            }

            updatePosition();
            updateAttackArea();
            if (Objects.equals(direction, "attack"))
                checkAttack();

            IsFish(getSolidArea(), level,this);
            IsMouse(getSolidArea(), mouse,this);
            IsBone(getSolidArea(), level,this,keyH);
            IsWater(this, this.map);
        }

    private void checkAttack() {
        if (attackChecked)
            return;
        attackChecked = true;
        playing.checkEnemyHit(attackArea);
    }

    private void updateAttackArea() {
        if(Objects.equals(direction, "right")
                || (Objects.equals(direction, "up")
                && Objects.equals(lastPressed, "right"))){
            attackArea.x=solidArea.x+solidArea.width+5;
        } else if (Objects.equals(direction, "left")
                || (Objects.equals(direction, "up")
                && Objects.equals(lastPressed, "left"))) {
            attackArea.x=solidArea.x - solidArea.width - 5;
        }
        attackArea.y=solidArea.y+10;
    }

    private void updatePosition() {
        float xSpeed=0;


        if(upPressed)
        {
            if(!inAir) {
                direction="up";
                inAir = true;
                airSpeed = jumpSPEED;
            }
        }
        if(!inAir) {
            if(attackPressed)
                direction="attack";
            if ((!leftPressed && !rightPressed) || (leftPressed && rightPressed)) {
                if(attackPressed) {
                    direction = "attack";
                }else {
                    direction = "idle";
                }
                return;
            }
        }
        if(leftPressed )
        {
            lastPressed = "left";
            direction = "left";
            xSpeed -= this.speed;
        }
        if (rightPressed)
        {
            lastPressed = "right";
            direction = "right";
            xSpeed+= this.speed;
        }

        if(!inAir)
            if(!IsEntityOnFloor(getSolidArea(),map))
                inAir = true;

        if(inAir) {
            if (CanMoveHere( getSolidArea().x,getSolidArea().y + airSpeed, getSolidArea().width, getSolidArea().height, map))
            {
                direction="up";
                getSolidArea().y += airSpeed;
                airSpeed += gravity;
                updateXPos(xSpeed);
            }else {
                if(airSpeed>0)
                    resetInAir();
                else
                    airSpeed = fallSpeedAfterCollision;
            }
        }else
            updateXPos(xSpeed);

        if(inAir)
            if((!leftPressed && !rightPressed)||(leftPressed && rightPressed))
                direction="down";

        if(attackPressed)
            direction="attack";
       updateCounter();

    }

    private void resetInAir() {
        inAir=false;
        airSpeed=0;
    }

    private void updateXPos(float xSpeed) {
        if(CanMoveHere(getSolidArea().x+xSpeed,getSolidArea().y,getSolidArea().width,getSolidArea().height,map))
            getSolidArea().x+=xSpeed;
    }

    public void draw(Graphics g,int lvlOffset) {

        drawPlayer(g,lvlOffset);
        g.setColor(Color.RED);
    }

    private void drawPlayer(Graphics g,int lvlOffset) {
            BufferedImage image = Assets.M_walkD0;

            if (Objects.equals(lastPressed, "right"))
                image = Assets.M_jumpD2;
            else if (Objects.equals(lastPressed, "left"))
                image = Assets.M_jumpS2;

            switch (direction) {
                case "up":
                    if (Objects.equals(lastPressed, "right")) {
                        if (num == 1)
                            image = Assets.M_jumpD1;
                        if (num == 2)
                            image = Assets.M_jumpD2;
                        if (num == 3)
                            image = Assets.M_jumpD3;
                        if (num == 4)
                            image = Assets.M_jumpD2;
                        break;
                    }
                    else if (Objects.equals(lastPressed, "left")) {
                        if (num == 1)
                            image = Assets.M_jumpS1;
                        if (num == 2)
                            image = Assets.M_jumpS2;
                        if (num == 3)
                            image = Assets.M_jumpS3;
                        if(num==4)
                            image = Assets.M_jumpS2;

                        break;
                    }
                case "left":
                    if (num == 1)
                        image = Assets.M_walkS0;
                    if (num == 2)
                        image = Assets.M_walkS1;
                    if (num == 3)
                        image = Assets.M_walkS2;
                    if (num == 4)
                        image = Assets.M_walkS3;
                    break;
                case "right":
                    if (num == 1)
                        image = Assets.M_walkD0;
                    if (num == 2)
                        image = Assets.M_walkD1;
                    if (num == 3)
                        image = Assets.M_walkD2;
                    if (num == 4)
                        image = Assets.M_walkD3;
                    break;
                case "down":
                    if (Objects.equals(lastPressed, "right"))
                        image = Assets.M_jumpD2;
                    else if (Objects.equals(lastPressed, "left"))
                        image = Assets.M_jumpS2;
                    break;
                case "idle":
                    if(num==1 || num==3)
                        image = Assets.M_idle1;
                    if(num==2 || num==4)
                        image = Assets.M_idle2;
                    updateCounter();
                    break;
                case "attack":
                    if (Objects.equals(lastPressed, "right")) {
                        if (num == 1)
                            image = Assets.M_attackD0;
                        if (num == 2)
                            image = Assets.M_attackD1;
                        if (num == 3)
                            image = Assets.M_attackD3;
                        if (num == 4)
                            image = Assets.M_attackD1;
                        updateCounter();
                    }
                    else if (Objects.equals(lastPressed, "left")) {
                        if (num == 1)
                            image = Assets.M_attackS0;
                        if (num == 2)
                            image = Assets.M_attackS1;
                        if (num == 3)
                            image = Assets.M_attackS3;
                        if (num == 4)
                            image = Assets.M_attackS1;
                        updateCounter();
                    }
                    break;
                case "death":
                    if (Objects.equals(lastPressed, "right")) {

                        image = Assets.MarthaDeath[2];
                        if(!playing.gameOver)
                            updateCounter();
                    }
                    else if (Objects.equals(lastPressed, "left")) {
                        image = Assets.MarthaDeath[6];
                        if(!playing.gameOver)
                            updateCounter();
                    }
                    break;
            }

            g.drawImage(image,(int)(getSolidArea().x-xOffset)-lvlOffset,(int) (getSolidArea().y-yOffset), Width, Height, null);
    }
    private void updateCounter() {
        counter++;
        if (counter > 12) {
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
        else if(num==4) {
            num = 1;
            attackChecked=false;
        }
    }
    public  void changeCoord(int x, int y)
    {
        solidArea.x=x;
        solidArea.y=y;
    }
    public  void  changeLife()
    {
        if(this.lives>0)
            lives--;
    }

    public  int getLives()
    {
        return lives;
    }
//    public boolean getCollectPressed(){
//        return collectPressed;
//    }
    public void resetAll() {
        direction="right";
        inAir=false;
        this.lives=3;

        solidArea.x=STARTX;
        solidArea.y=STARTY;
        attackArea.x=x;
        attackArea.y=y;
        points.setPoints(0);
        points.setBone(false );
        if (!IsEntityOnFloor(getSolidArea(), map))
            inAir = true;
  }
}

