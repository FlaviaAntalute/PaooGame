package PaooGame.Entity;

import PaooGame.Graphics.Assets;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Objects;

import static PaooGame.Entity.Collision.*;
import static PaooGame.Useful.Constants.EnemyConstants.*;

public class Max extends Enemy {
    private static int Width = 60;
    private static int Height = 55;
    protected static int yOffset = 20;//CA SA FIE DESENAT PE PAMANT

    public Max(int x, int y, int s, String dir) {
        super(x, y, s, MAX, dir);
        initSolidArea(x, y, 35, 31);
    }

    public static int GetYOffset() {
        return yOffset;
    }

    public void drawMax(Graphics g, int xLvlOffset) {
        BufferedImage image = null;

        if (direction == "idle") {
            if(lastDir=="left") {
                if (num == 1)
                    image = Assets.Max[10];
                if (num == 2)
                    image = Assets.Max[11];
                if (num == 3)
                    image = Assets.Max[12];
                if (num == 4)
                    image = Assets.Max[13];
                if (num == 5)
                    image = Assets.Max[14];
            } else if (lastDir=="right") {
                image = Assets.Max[17];
            }
        } else if (direction == "right") {
            if (num == 1)
                image = Assets.Max[0];
            if (num == 2)
                image = Assets.Max[1];
            if (num == 3)
                image = Assets.Max[2];
            if (num == 4)
                image = Assets.Max[3];
            if (num == 5)
                image = Assets.Max[4];
        } else if (direction == "left") {
            if (num == 1)
                image = Assets.Max[5];
            if (num == 2)
                image = Assets.Max[6];
            if (num == 3)
                image = Assets.Max[7];
            if (num == 4)
                image = Assets.Max[8];
            if (num == 5)
                image = Assets.Max[9];
        } else if (direction == "attack") {
            if(lastDir=="left") {
                if (num == 1)
                    image = Assets.Max[15];
                if (num == 2)
                    image = Assets.Max[16];
                if (num == 3)
                    image = Assets.Max[15];
                if (num == 4)
                    image = Assets.Max[15];
                if (num == 5)
                    image = Assets.Max[16];
            }
            if(lastDir=="right") {
                if (num == 1)
                    image = Assets.Max[17];
                if (num == 2)
                    image = Assets.Max[18];
                if (num == 3)
                    image = Assets.Max[17];
                if (num == 4)
                    image = Assets.Max[17];
                if (num == 5)
                    image = Assets.Max[18];
            }
        }

        g.drawImage(image, (int) getSolidArea().x - MaxXOffset - xLvlOffset, (int) getSolidArea().y - MaxYOffset, Width, Height, null);
    }

    public void update(int[][] map,Player player) {
        updateWalk(map,player);
        updateCounter();
    }

    public void updateWalk(int[][] map,Player player) {
        if (first)
            firstUpdate(map);
        if (inAir)
            InAir(map);
        else {
            if(Objects.equals(direction, "idle"))
                direction="left";
            else if(Objects.equals(direction, "right") || Objects.equals(direction, "left"))
            {
                if (PlayerIsClose(map, player))
                    TurnToPlayer(player);
                if (CanAttack(player)) {
                    lastDir = direction;
                    direction = "attack";
                }
                moveEnemy(map);
            }
        }
    }
}
