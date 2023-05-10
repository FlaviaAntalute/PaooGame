package PaooGame.Levels;

import PaooGame.Entity.Entity;
import PaooGame.Entity.Player;
import PaooGame.Graphics.Assets;

import java.awt.*;


public class Lives {
    private static int width=25;
    private static int height=23;
    private static int x=25;
    private static int y=20;
    public static void drawLives(Graphics g, int lvlOffset, Player player) {
        if (player.getLives() == 3) {
            g.drawImage(Assets.lives[0], x, y, width, height, null);
            g.drawImage(Assets.lives[0], x + 30, y, width, height, null);
            g.drawImage(Assets.lives[0], x + 60, y, width, height, null);
        } else if (player.getLives() == 2) {
            g.drawImage(Assets.lives[0], x, y, width, height, null);
            g.drawImage(Assets.lives[0], x + 30, y, width, height, null);
            g.drawImage(Assets.lives[1], x + 60, y, width, height, null);
        } else if (player.getLives() == 1) {
            g.drawImage(Assets.lives[0], x, y, width, height, null);
            g.drawImage(Assets.lives[1], x + 30, y, width, height, null);
            g.drawImage(Assets.lives[1], x + 60, y, width, height, null);
        } else if (player.getLives() == 0) {
            g.drawImage(Assets.lives[1], x, y, width, height, null);
            g.drawImage(Assets.lives[1], x + 30, y, width, height, null);
            g.drawImage(Assets.lives[1], x + 60, y, width, height, null);

        }
    }
}
