package PaooGame.Entity;

import PaooGame.Graphics.Assets;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.Objects;

import static PaooGame.Useful.Constants.EnemyConstants.*;

public class Rex extends Enemy{
    private static int Width = 60; ///Lățimea în pixeli a imaginii inamicului de tip Rex.
    private static int Height = 55; ///Înălțimea în pixeli a imaginii inamicului de tip Rex.
    public Rex(int x, int y, int s, String dir) {
        super(x, y, s, REX, dir);
        lives=1;
        initSolidArea(x,y,35,31);
        initAttackArea(80,45,25);

    }
    /*! \fn public void drawIndividual(Graphics g, int xLvlOffset)
       \brief Desenează sprite-ul corespunzător stării și direcției entității de tip Rex.

       \ g obiectul Graphics în care se realizează desenarea
       \ xLvlOffset offset-ul orizontal al nivelului, utilizat pentru a actualiza poziția de desenare
       */
    public void drawIndividual(Graphics g, int xLvlOffset) {
        BufferedImage image = null;
        /// Verificăm starea și direcția entității Rex pentru a selecta sprite-ul corespunzător

        if (direction == "right") {
            if (num == 1)
                image = Assets.Rex[0];
            if (num == 2)
                image = Assets.Rex[1];
            if (num == 3)
                image = Assets.Rex[2];
            if (num == 4)
                image = Assets.Rex[3];
            if (num == 5)
                image = Assets.Rex[4];
        } else if (direction == "left") {
            if (num == 1)
                image = Assets.Rex[5];
            if (num == 2)
                image = Assets.Rex[6];
            if (num == 3)
                image = Assets.Rex[7];
            if (num == 4)
                image = Assets.Rex[8];
            if (num == 5)
                image = Assets.Rex[9];
        } else if (direction == "attack") {
            if (lastDir == "left") {
                if (num == 1)
                    image = Assets.Rex[5];
                if (num == 2)
                    image = Assets.Rex[6];
                if (num == 3)
                    image = Assets.Rex[18];
                if (num == 4)
                    image = Assets.Rex[7];
                if (num == 5)
                    image = Assets.Rex[18];
            }
            if (lastDir == "right") {
                if (num == 1)
                    image = Assets.Rex[0];
                if (num == 2)
                    image = Assets.Rex[1];
                if (num == 3)
                    image = Assets.Rex[19];
                if (num == 4)
                    image = Assets.Rex[2];
                if (num == 5)
                    image = Assets.Rex[19];
            }
        }else if (Objects.equals(direction, "calm")) {
            if(Objects.equals(lastDir, "left"))
                image=Assets.Rex[17];
            if(Objects.equals(lastDir, "right"))
                image=Assets.Rex[13];
        }
        else if (Objects.equals(direction, "hurt")) {
            if (Objects.equals(lastDir, "left")) {
                if (num == 1)
                    image = Assets.Rex[14];
                if (num == 2)
                    image = Assets.Rex[15];
                if (num == 3)
                    image = Assets.Rex[16];
                if (num == 4)
                    image = Assets.Rex[17];
                if (num == 5)
                    image = Assets.Rex[17];
            }
            if (lastDir == "right") {
                if (num == 1)
                    image = Assets.Rex[10];
                if (num == 2)
                    image = Assets.Rex[11];
                if (num == 3)
                    image = Assets.Rex[12];
                if (num == 4)
                    image = Assets.Rex[13];
                if (num == 5)
                    image = Assets.Rex[13];
            }
        }
        /// Desenăm sprite-ul corespunzător entității Snake
        /// X și Y sunt coordonatele de desenare ale sprite-ului
        /// MaxXOffset și MaxYOffset sunt constante care reprezintă offset-ul de desenare
        g.drawImage(image, (int) getSolidArea().x - MaxXOffset - xLvlOffset, (int) getSolidArea().y- MaxYOffset, Width, Height, null);
    }

}
