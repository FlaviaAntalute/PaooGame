package PaooGame.Entity;

import PaooGame.Graphics.Assets;

import java.awt.*;
import java.awt.image.BufferedImage;
import static PaooGame.Useful.Constants.EnemyConstants.*;

    /*! class Max
        Clasa Max reprezintă o subclasă a clasei Enemy și definește caracteristicile inamicului Max din joc.
    */
public class Max extends Enemy {
    private static int Width = 60; ///Lățimea în pixeli a imaginii inamicului Max.
    private static int Height = 55; ///Înălțimea în pixeli a imaginii inamicului Max.

        /*! \fn public Max(int x, int y, int s, String dir)
            \brief Constructor pentru clasa Max.

            \ x coordonata x a pozitiei initiale a obiectului.
            \ y coordonata y a pozitiei initiale a obiectului.
            \ s viteza de miscare a obiectului.
            \ dir directia initiala de miscare a obiectului.

             Acest constructor initializeaza obiectul Max cu pozitia initiala, viteza de miscare si directia initiala date, si seteaza numarul initial de vieti la 1.
             De asemenea, initializeaza zona solida si zona de atac asociate obiectului.
        */
    public Max(int x, int y, int s, String dir) {
        super(x, y, s, MAX, dir);
        lives=1;
        initSolidArea(x, y, 35, 31);
        initAttackArea(80,45,25);

    }


        /*! \fn public void drawMax(Graphics g, int xLvlOffset)
         \brief Desenează sprite-ul corespunzător stării și direcției entității Max.

         \ g obiectul Graphics în care se realizează desenarea
         \ xLvlOffset offset-ul orizontal al nivelului, utilizat pentru a actualiza poziția de desenare
         */
    public void drawIndividual(Graphics g, int xLvlOffset) {
        BufferedImage image = null;
        /// Verificăm starea și direcția entității Max pentru a selecta sprite-ul corespunzător
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
        /// Desenăm sprite-ul corespunzător entității Max
        /// X și Y sunt coordonatele de desenare ale sprite-ului
        /// MaxXOffset și MaxYOffset sunt constante care reprezintă offset-ul de desenare
        g.drawImage(image, (int) getSolidArea().x - MaxXOffset - xLvlOffset, (int) getSolidArea().y - MaxYOffset, Width, Height, null);
    }
}
