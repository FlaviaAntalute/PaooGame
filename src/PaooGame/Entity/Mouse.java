package PaooGame.Entity;

import PaooGame.Graphics.Assets;

import java.awt.*;
import java.awt.image.BufferedImage;

    /*! \class Mouse
    \brief Clasa pentru obiectele de tip Mouse.

    Clasa implementează caracteristicile și comportamentul obiectelor de tip Mouse,
    care reprezintă hrana pentru jucător.

     */
public class Mouse extends Entity{
    private int Width=30; ///lățimea obiectului de tip Mouse
    private int Height=20; ///înălțimea obiectului de tip Mouse
    private int times=0; ///variabila pentru limitarea timpului de miscare a entitatii
    protected boolean isMouse=true; /// indică dacă obiectul de tip Mouse este inca pe harta
    private static int yOffset=17; ///poziția relativă a obiectului de tip Mouse față de sol


        /*! \fn public Mouse(int x, int y, int s, String dir)
            \brief Constructorul clasei Mouse.

        Acesta primește coordonatele x și y ale poziției obiectului, viteza obiectului s,
        precum și direcția în care se mișcă obiectul și inițializează variabilele membru corespunzătoare.

        \ x Coordonata x a poziției obiectului.
        \ y Coordonata y a poziției obiectului.
        \ s viteza obiectului.
        \ dir Direcția în care se mișcă obiectul (dreapta sau stânga).
         */

    public Mouse(int x, int y, int s,String dir) {
        super(x, y, s,dir);
    }

        /*! \fn public void draw(Graphics g, int lvlOffset)
        \brief Desenează obiectul de tip Mouse.

        Funcția primește contextul grafic g și nivelul de deplasare a jocului lvlOffset și
        desenează obiectul de tip Mouse în poziția corespunzătoare.

        \ g Contextul grafic în care se desenează obiectul.
        \ lvlOffset Deplasarea nivelului de joc față de poziția inițială.
        */
    public void draw(Graphics g, int lvlOffset) {
        BufferedImage image = Assets.mouse[0];
        if(direction=="right")
        {
            if(num==1)
                image=Assets.mouse[0];
            if(num==2)
                image=Assets.mouse[1];
            if(num==3)
                image=Assets.mouse[2];
        }
        else if(direction=="left")
        {
            if(num==1)
                image=Assets.mouse[3];
            if(num==2)
                image=Assets.mouse[4];
            if(num==3)
                image=Assets.mouse[5];
        }
        if(isMouse)
            g.drawImage(image,x-lvlOffset,y, Width, Height, null);
    }

    /*! \fn public void update()
        \brief Actualizează starea obiectului Mouse.

        Funcția actualizează starea obiectului Mouse și determină direcția de deplasare în funcție de timpul scurs.

        Dacă timpul scurs este mai mic sau egal cu 100, direcția de deplasare este spre dreapta, iar poziția obiectului se actualizează în consecință.
        Dacă timpul scurs este mai mare decât 100 și mai mic sau egal cu 200, direcția de deplasare este spre stânga, iar poziția obiectului se actualizează în consecință.
        Dacă timpul scurs este exact 200, acesta este resetat la 0 pentru a permite repetarea ciclului de mișcare.

    */
        public void update(){

        if(times<=100) {
            direction="right";
            x += speed;
        }
        if(times>100 && times<=200) {
            direction="left";
            x -= speed;
        }
        if(times==200)
            times=0;
        times++;
        updateCounter();

    }

        /*!\fn protected void updateNum()
        \brief Actualizează numărul imaginii de animație a obiectului Mouse.

        Funcția actualizează numărul imaginii de animație a obiectului Mouse
        pe baza valorii anterioare.
        - Dacă numărul este 1, se actualizează la 2.
        - Dacă numărul este 2, se actualizează la 3.
        - Dacă numărul este 3, se actualizează la 1.
        */
    protected void updateNum() {
        if(num==1)
            num = 2;
        else if(num==2)
            num = 3;
        else if(num==3)
            num=1;
    }

        /*!
             \fn private void updateCounter()
             \brief Actualizează numărătorul de frame-uri pentru animația obiectului Mouse.
             Funcția actualizează numărătorul de frame-uri pentru animația obiectului Mouse.
             La fiecare apel, se incrementază cu 1 valoarea lui counter.
             Dacă counter este mai mare decât 9, se actualizează numărul de imagine de animație
             prin apelul funcției updateNum() și se resetează counter la 0.
         */
    private void updateCounter() {
        counter++;
        if (counter > 9) {
            this.updateNum();
            counter = 0;
        }
    }

        /*!
            \fn public static int getyOffset()
            \brief Returnează offset-ul pe axa Y al obiectului Mouse.

            \return Offset-ul pe axa Y al obiectului Mouse.
        */
    public static int getyOffset() {
        return yOffset;
    }

        /*!
            \fn public void resetAll()
            \brief Resetarea stării obiectului Mouse.

            Funcția resetează starea obiectului Mouse la valorile inițiale, astfel:
            - isMouse devine true;
            - times devine 0;
            - x devine 10.
        */
    public void resetAll() {
        isMouse=true;
        times=0;
        x=10;
    }
}
