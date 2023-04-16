package PaooGame.Entity;

import PaooGame.Graphics.Assets;

import java.awt.image.BufferedImage;

public class Mouse extends Entity{
    public Mouse(int x, int y, int s) {
        super(x, y, s);
    }
    public void draw() {
        BufferedImage image = Assets.mouse;

    }
    public void update(){

    }
}
