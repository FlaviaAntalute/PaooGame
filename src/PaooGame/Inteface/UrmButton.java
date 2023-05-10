package PaooGame.Inteface;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UrmButton extends PauseButton {
    private static int Width=120,Height=60;
    private BufferedImage[] imgs;
    private int index;

    private boolean mouseOver, mousePressed;

    public UrmButton(int x, int y , BufferedImage [] imgs) {
        super(x, y, Width, Height);
        loadImgs(imgs);
    }

    public void loadImgs(BufferedImage [] imgs)
    {
        this.imgs=new BufferedImage[3];
        System.arraycopy(imgs, 0, this.imgs, 0, this.imgs.length);
    }
    public void update() {
        index = 0;
        if (mouseOver)
            index = 1;
        if (mousePressed)
            index = 2;

    }

    public void draw(Graphics g) {
        g.drawImage(imgs[index], x, y, Width, Height, null);
    }

    public void resetBools() {
        mouseOver = false;
        mousePressed = false;
    }

    public boolean isMouseOver() {
        return mouseOver;
    }

    public void setMouseOver(boolean mouseOver) {
        this.mouseOver = mouseOver;
    }

    public boolean isMousePressed() {
        return mousePressed;
    }

    public void setMousePressed(boolean mousePressed) {
        this.mousePressed = mousePressed;
    }

}
