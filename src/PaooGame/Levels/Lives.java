package PaooGame.Levels;

import PaooGame.Entity.Entity;
import PaooGame.Entity.Player;
import PaooGame.Graphics.Assets;

import java.awt.*;
import java.awt.image.BufferedImage;

import static PaooGame.Entity.Player.getLives;

public class Lives {
    private static int width=22;
    private static int height=20;
    private static int x=10;
    private static int y=10;
    public static void drawLives(Graphics g, int lvlOffset, Player player) {
        if (getLives() == 3) {
            g.drawImage(Assets.lives[0], x, y, width, height, null);
            g.drawImage(Assets.lives[0], x + 27, y, width, height, null);
            g.drawImage(Assets.lives[0], x + 54, y, width, height, null);
        } else if (getLives() == 2) {
            g.drawImage(Assets.lives[0], x, y, width, height, null);
            g.drawImage(Assets.lives[0], x + 27, y, width, height, null);
            g.drawImage(Assets.lives[1], x + 54, y, width, height, null);
        } else if (getLives() == 1) {
            g.drawImage(Assets.lives[0], x, y, width, height, null);
            g.drawImage(Assets.lives[1], x + 27, y, width, height, null);
            g.drawImage(Assets.lives[1], x + 54, y, width, height, null);
        } else if (getLives() == 0) {
            g.drawImage(Assets.lives[1], x, y, width, height, null);
            g.drawImage(Assets.lives[1], x + 27, y, width, height, null);
            g.drawImage(Assets.lives[1], x + 54, y, width, height, null);

        }
    }
}
