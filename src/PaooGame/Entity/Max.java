package PaooGame.Entity;

import PaooGame.Graphics.Assets;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.Objects;

import static PaooGame.Entity.Collision.*;
import static PaooGame.Useful.Constants.EnemyConstants.*;

    /*! class Max
        Clasa Max reprezintă o subclasă a clasei Enemy și definește caracteristicile inamicului Max din joc.
    */
public class Max extends Enemy {
    private static int Width = 60; ///Lățimea în pixeli a imaginii inamicului Max.
    private static int Height = 55; ///Înălțimea în pixeli a imaginii inamicului Max.
    protected static int yOffset = 20;///Offset-ul în pixeli față de sol pentru a plasa inamicul Max la nivelul solului în joc.
    //attack box
    private Rectangle2D.Float attackArea; /// Zona de atac a inamicului Max, reprezentată ca un obiect de tip Rectangle2D.Float.
    private int attackAreaOffsetX; ///Offset-ul în pixeli față de zona solidă a inamicului Max pentru a plasa zona de atac în mod corespunzător.

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
        initAttackArea();

    }

        /*! \fn private void initAttackArea()

        \Inițializează zona de atac asociată entității.
        \attackArea o formă rectangulară 2D ce reprezintă zona de atac
        \attackAreaOffsetX o valoare numerică ce reprezintă offset-ul poziției x a zonei de atac
    */
    private void initAttackArea() {
        attackArea=new Rectangle2D.Float(x,y,80,45);
        attackAreaOffsetX=25;
    }
        /*!\fn public static int GetYOffset()
        \brief Returnează deplasarea verticală a entității de la sol.
        \return Deplasarea verticală a entității de la sol.
        */
    public static int GetYOffset() {
        return yOffset;
    }

        /*! \fn public void drawMax(Graphics g, int xLvlOffset)
         \brief Desenează sprite-ul corespunzător stării și direcției entității Max.

         \ g obiectul Graphics în care se realizează desenarea
         \ xLvlOffset offset-ul orizontal al nivelului, utilizat pentru a actualiza poziția de desenare
         */
    public void drawMax(Graphics g, int xLvlOffset) {
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
        /*! \fn public void update(int[][] map,Player player)
            \brief Metoda de actualizare a stării inamicului Max.

            \ map harta jocului în format matrice de intregi
            \ player jucătorul din joc

            Metoda actualizează starea inamicului Max prin apelarea a trei metode ajutătoare:
            updateWalk() - actualizează poziția și starea de deplasare a inamicului;
            updateCounter() - actualizează numărul de cadre până la următoarea acțiune a inamicului;
            updateAttackArea() - actualizează poziția și dimensiunile zonei de atac a inamicului.
        */
    public void update(int[][] map,Player player) {
        updateWalk(map,player);
        updateCounter();
        updateAttackArea();
    }

        /*! \fn public void updateAttackArea()
            Actualizează poziția zonei de atac în funcție de poziția zonei solide.
        */
    public void updateAttackArea()
    {
        attackArea.x=solidArea.x-attackAreaOffsetX;
        attackArea.y=solidArea.y-10;
    }

        /*! \fn public void updateWalk(int[][] map,Player player)
        \brief Actualizează poziția și mișcarea inamicului.
        Funcția verifică starea inamicului și actualizează direcția de deplasare și acțiunile în funcție de aceasta.
        - Dacă inamicul nu s-a mișcat încă, se actualizează poziția.
        - Dacă inamicul se află în aer, se apelează funcția InAir().
        - Dacă inamicul este în stare de repaus, se va deplasa spre stânga și va căuta jucătorul.
        - Dacă inamicul este în stare de atac, se verifică dacă jucătorul este în raza de acțiune a atacului și se efectuează atacul.
        - Dacă inamicul este rănit, nu se face nimic.
        \param map Matricea de tip Tile ce reprezintă harta nivelului curent.
        \param player Obiectul de tip Player ce reprezintă jucătorul.
        */
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
                if (PlayerIsClose(map, player)){
                    TurnToPlayer(player);
                    if (CanAttack(player)) {
                        lastDir = direction;
                        direction = "attack";
                    }
                }
                moveEnemy(map);
            } else if (Objects.equals(direction, "attack")) {
                if(num==1)
                    attackChecked=false;
                if(num==2 && !attackChecked)
                    checkPlayerHit(attackArea,player);
            } else if (Objects.equals(direction, "hurt")) {
                
            }
        }
    }
}
