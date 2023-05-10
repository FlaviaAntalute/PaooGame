package PaooGame.Inteface;

import PaooGame.GameStates.Gamestate;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Collection;

public class MenuButton {
    private int Width=120,Height=60;
    private int xPos,yPos,index;
    private int xOffsetCenter=Width/2;
    private Gamestate state;
    private boolean mouseOver,mousePressed;
    private BufferedImage[] imgs;
    private Rectangle bounds;
    public MenuButton(int xPos, int yPos,BufferedImage[] imgs, Gamestate state){
        this.xPos=xPos;
        this.yPos=yPos;
        this.state=state;
        loadImgs(imgs);
        initBounds();
    }

    private void initBounds() {
        bounds=new Rectangle(xPos-xOffsetCenter,yPos,Width,Height);
    }

    public void loadImgs(BufferedImage [] imgs)
    {
        this.imgs=new BufferedImage[3];
        System.arraycopy(imgs, 0, this.imgs, 0, this.imgs.length);
    }
    public void draw(Graphics g)
    {
        g.drawImage(imgs[index],xPos-xOffsetCenter,yPos,Width,Height,null);
    }

    public boolean isMousePressed() {
        return mousePressed;
    }

    public void setMousePressed(boolean mousePressed) {
        this.mousePressed = mousePressed;
    }

    public boolean isMouseOver() {
        return mouseOver;
    }

    public void setMouseOver(boolean mouseOver) {
        this.mouseOver = mouseOver;
    }

    public void update()
    {
        index=0;
        if(mouseOver)
            index=1;
        if(mousePressed)
            index=2;
    }
    public void changeGameState(){
        Gamestate.state=state;
    }
    public void resetBools()
    {
        mouseOver=false;
        mousePressed=false;
    }

    public Rectangle getBounds() {
        return bounds;
    }
}
