package PaooGame.Levels;

import PaooGame.Entity.Entity;
import PaooGame.Entity.Player;
import PaooGame.Graphics.Assets;

import java.awt.*;


public class Lives {
    private static int width=22;
    private static int height=20;
    private static int x=30;
    private static int y=20;
    public static void drawLives(Graphics g, int lvlOffset, Player player) {
        if (player.getLives() == 3) {
            g.drawImage(Assets.lives[0], x, y, width, height, null);
            g.drawImage(Assets.lives[0], x + 27, y, width, height, null);
            g.drawImage(Assets.lives[0], x + 54, y, width, height, null);
        } else if (player.getLives() == 2) {
            g.drawImage(Assets.lives[0], x, y, width, height, null);
            g.drawImage(Assets.lives[0], x + 27, y, width, height, null);
            g.drawImage(Assets.lives[1], x + 54, y, width, height, null);
        } else if (player.getLives() == 1) {
            g.drawImage(Assets.lives[0], x, y, width, height, null);
            g.drawImage(Assets.lives[1], x + 27, y, width, height, null);
            g.drawImage(Assets.lives[1], x + 54, y, width, height, null);
        } else if (player.getLives() == 0) {
            g.drawImage(Assets.lives[1], x, y, width, height, null);
            g.drawImage(Assets.lives[1], x + 27, y, width, height, null);
            g.drawImage(Assets.lives[1], x + 54, y, width, height, null);

        }
    }
}
