package PaooGame.Entity;
import java.awt.*;
import java.awt.image.BufferedImage;
import PaooGame.Game;
import PaooGame.Graphics.Assets;
import PaooGame.Inputs.KeyHandler;
import PaooGame.Levels.Level;

import static PaooGame.Entity.Collision.*;

public class Player extends Entity {
    private KeyHandler keyH;
    private int [][] map;
    //jumping or falling
    public float airSpeed=0f;
    public float gravity=0.06f;
    public float jumpSPEED=-2.8f;
    public static int Height=48;
    public static int Width=48;
    private float fallSpeedAfterCollision=0.5f;
    private boolean inAir=false;
    public Player(Game game, KeyHandler keyH, int[][] map) {
        super(0, 513, 3);
        this.keyH = keyH;
        initSolidArea(x,y,25,30);
        if (!IsEntityOnFloor(getSolidArea(), map))
            inAir = true;
    }
    public void loadMap(int[][] map)
    {
        this.map=map;
    }
    public void update(Level level,Mouse mouse) {
        updatePosition();
        IsFish(getSolidArea(),level);
        IsMouse(getSolidArea(),mouse);
        IsBone(getSolidArea(),level);
    }

      private void updatePosition() {
        float xSpeed=0;


        if(keyH.upPressed)
        {
            if(!inAir) {
                direction="up";
                inAir = true;
                airSpeed = jumpSPEED;
            }
        }
        if(!inAir) {
            if(keyH.attackPressed)
                direction="attack";
            if ((!keyH.leftPressed && !keyH.rightPressed) || (keyH.leftPressed && keyH.rightPressed)) {
                if(keyH.attackPressed) {
                    direction = "attack";
                }else {
                    direction = "idle";
                }
                return;
            }
        }
        if(keyH.leftPressed )
        {
            lastPressed = "left";
            direction = "left";
            xSpeed -= this.speed;
        }
        if (keyH.rightPressed)
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
            if((!keyH.leftPressed && !keyH.rightPressed)||(keyH.leftPressed && keyH.rightPressed))
                direction="down";

        if(keyH.attackPressed)
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
    }

    private void drawPlayer(Graphics g,int lvlOffset) {
            BufferedImage image = Assets.M_walkD0;

            if (lastPressed == "right")
                image = Assets.M_jumpD2;
            else if (lastPressed == "left")
                image = Assets.M_jumpS2;

            switch (direction) {
                case "up":
                    if (lastPressed == "right") {
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
                    else if (lastPressed == "left") {
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
                    if (lastPressed == "right")
                        image = Assets.M_jumpD2;
                    else if (lastPressed == "left")
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
                    if (lastPressed == "right") {
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
                    else if (lastPressed == "left") {
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
        else if(num==4)
            num=1;
    }



}

