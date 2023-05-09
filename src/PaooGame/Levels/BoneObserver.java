package PaooGame.Levels;


import PaooGame.Graphics.Assets;

import java.awt.*;

import static java.awt.Font.BOLD;

public class BoneObserver implements Observer{
    private  boolean hasBone;

    public void update(boolean hasBone) {
        this.hasBone = hasBone;

    }

}
