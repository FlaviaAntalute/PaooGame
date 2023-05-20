package PaooGame.Entity;

import PaooGame.Graphics.Assets;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import static PaooGame.Useful.Constants.EnemyConstants.*;

public class Snake extends Enemy{
    private static int Width = 40; ///Lățimea în pixeli a imaginii inamicului de tip Snake.
    private static int Height = 30; ///Înălțimea în pixeli a imaginii inamicului de tip Snake.
    protected static int yOffset = 5;///Offset-ul în pixeli față de sol pentru a plasa inamicul la nivelul solului în joc.
    public Snake(int x, int y, int s, String dir,int life) {
        super(x, y, s, SNAKE, dir);
        lives=life;
        initSolidArea(x,y,35,20);
        initAttackArea(50,30,25);
    }
    /*! \fn public void drawSnake(Graphics g, int xLvlOffset)
       \brief Desenează sprite-ul corespunzător stării și direcției entității de tip Snake.

       \ g obiectul Graphics în care se realizează desenarea
       \ xLvlOffset offset-ul orizontal al nivelului, utilizat pentru a actualiza poziția de desenare
       */
    public void drawIndividual(Graphics g, int xLvlOffset) {
        BufferedImage image = null;
        /// Verificăm starea și direcția entității Snake pentru a selecta sprite-ul corespunzător
        if (direction == "idle") {
            if(lastDir=="left")
                image = Assets.Snake[5];
            else if (lastDir=="right")
                image = Assets.Snake[0];
        } else if (direction == "right") {
            if (num == 1)
                image = Assets.Snake[0];
            if (num == 2)
                image = Assets.Snake[1];
            if (num == 3)
                image = Assets.Snake[2];
            if (num == 4)
                image = Assets.Snake[3];
            if (num == 5)
                image = Assets.Snake[4];
        } else if (direction == "left") {
            if (num == 1)
                image = Assets.Snake[5];
            if (num == 2)
                image = Assets.Snake[6];
            if (num == 3)
                image = Assets.Snake[7];
            if (num == 4)
                image = Assets.Snake[8];
            if (num == 5)
                image = Assets.Snake[9];
        } else if (direction == "attack") {
            if(lastDir=="left") {
                if (num == 1)
                    image = Assets.Snake[15];
                if (num == 2)
                    image = Assets.Snake[16];
                if (num == 3)
                    image = Assets.Snake[17];
                if (num == 4)
                    image = Assets.Snake[18];
                if (num == 5)
                    image = Assets.Snake[19];
            }
            if(lastDir=="right") {
                if (num == 1)
                    image = Assets.Snake[10];
                if (num == 2)
                    image = Assets.Snake[11];
                if (num == 3)
                    image = Assets.Snake[12];
                if (num == 4)
                    image = Assets.Snake[13];
                if (num == 5)
                    image = Assets.Snake[14];
            }
        }
        /// Desenăm sprite-ul corespunzător entității Snake
        /// X și Y sunt coordonatele de desenare ale sprite-ului
        /// MaxXOffset și MaxYOffset sunt constante care reprezintă offset-ul de desenare
        g.drawImage(image, (int) getSolidArea().x - xLvlOffset, (int) getSolidArea().y-yOffset, Width, Height, null);
    }
}
