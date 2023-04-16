package PaooGame.Graphics;

import java.awt.image.BufferedImage;

/*! \class public class Assets
    \brief Clasa incarca fiecare element grafic necesar jocului.

    Game assets include tot ce este folosit intr-un joc: imagini, sunete, harti etc.
 */
public class Assets
{
    /// Referinte catre elementele grafice (dale) utilizate in joc.
    public static BufferedImage soil;
    public static BufferedImage soil1;
    public static BufferedImage soil2;
    public static BufferedImage soil3;
    public static BufferedImage soil4;

    public static BufferedImage grass1;
    public static BufferedImage grass2;
    public static BufferedImage grass3;
    public static BufferedImage grass4;
    public static BufferedImage grass5;
    public static BufferedImage grass6;
    public static BufferedImage water;
    public static BufferedImage water1;
    public static BufferedImage water2;
    public static BufferedImage  M_walkD0;
    public static BufferedImage M_walkD1;
    public static BufferedImage M_walkD2;
    public static BufferedImage M_walkD3;
    public static BufferedImage M_walkD4;
    public static BufferedImage  M_walkS0;
    public static BufferedImage  M_walkS1;
    public static BufferedImage M_walkS2;
    public static BufferedImage  M_walkS3;
    public static BufferedImage  M_walkS4;
    public static BufferedImage M_jumpD1;
    public static BufferedImage M_jumpD2;
    public static BufferedImage M_jumpD3;
    public static BufferedImage M_jumpS1;
    public static BufferedImage M_jumpS2;
    public static BufferedImage M_jumpS3;
    public static BufferedImage M_attackD0;
    public static BufferedImage M_attackD1;
    public static BufferedImage M_attackD3;
    public static BufferedImage M_attackS0;
    public static BufferedImage M_attackS1;
    public static BufferedImage M_attackS3;
    public static BufferedImage tufis1;
    public static BufferedImage tufis2;
    public static BufferedImage copac1;
    public static BufferedImage copac2;
    public static BufferedImage iarba1;
    public static BufferedImage pietre;
    public static BufferedImage M_idle1;
    public static BufferedImage M_idle2;
    public static BufferedImage peste;
    public static BufferedImage mouse;

    public static BufferedImage bg1;
    public static BufferedImage bg2;
    public static BufferedImage bg3;
    public static BufferedImage bg4;



    /*! \fn public static void Init()
        \brief Functia initializaza referintele catre elementele grafice utilizate.

        Aceasta functie poate fi rescrisa astfel incat elementele grafice incarcate/utilizate
        sa fie parametrizate. Din acest motiv referintele nu sunt finale.
     */
    public static void Init()
    {
        /// Se creaza temporar un obiect SpriteSheet initializat prin intermediul clasei ImageLoader
        SpriteSheet sheet = new SpriteSheet(ImageLoader.LoadImage("/textures/TilesNonSliced.png"));
        ///sheet cu martha
        SpriteSheet MarthaSheet=new SpriteSheet(ImageLoader.LoadImage("/textures/Martha.png"));
        /// Se obtin subimaginile corespunzatoare elementelor necesare.

        grass1 = sheet.crop(0, 0);
        grass2 = sheet.crop(1, 0);
        grass3 = sheet.crop(2, 0);
        grass4 = sheet.crop(0, 6);
        grass5 = sheet.crop(4, 6);
        grass6 = sheet.crop(1, 5);

        water = sheet.crop(6, 4);
        water1= sheet.crop(6,5);
        water2= sheet.crop(6,6);

        soil = sheet.crop(1, 1);
        soil1 = sheet.crop(0, 2);
        soil2= sheet.crop(2, 2);
        soil3 = sheet.crop(1, 2);
        soil4 = sheet.crop(2, 1);

        M_walkD0=ImageLoader.LoadImage("/textures/M_walkD0.png");
        M_walkD1=ImageLoader.LoadImage("/textures/M_walkD1.png");
        M_walkD2=ImageLoader.LoadImage("/textures/M_walkD2.png");
        M_walkD3=ImageLoader.LoadImage("/textures/M_walkD3.png");
        M_walkD4=ImageLoader.LoadImage("/textures/M_walkD4.png");
        M_walkS0=ImageLoader.LoadImage("/textures/M_walkS0.png");
        M_walkS1=ImageLoader.LoadImage("/textures/M_walkS1.png");
        M_walkS2=ImageLoader.LoadImage("/textures/M_walkS2.png");
        M_walkS3=ImageLoader.LoadImage("/textures/M_walkS3.png");
        M_walkS4=ImageLoader.LoadImage("/textures/M_walkS4.png");
        M_jumpD1=ImageLoader.LoadImage("/textures/M_jumpD1.png");
        M_jumpD2=ImageLoader.LoadImage("/textures/M_jumpD2.png");
        M_jumpD3=ImageLoader.LoadImage("/textures/M_jumpD3.png");
        M_jumpS1=ImageLoader.LoadImage("/textures/M_jumpS1.png");
        M_jumpS2=ImageLoader.LoadImage("/textures/M_jumpS2.png");
        M_jumpS3=ImageLoader.LoadImage("/textures/M_jumpS3.png");
        tufis1=ImageLoader.LoadImage("/textures/tufis1.png");
        tufis2=ImageLoader.LoadImage("/textures/tufis2.png");
        copac1=ImageLoader.LoadImage("/textures/copac1.png");
        copac2=ImageLoader.LoadImage("/textures/copac2.png");
        iarba1=ImageLoader.LoadImage("/textures/iarba.png");
        pietre=ImageLoader.LoadImage("/textures/pietre.png");
        M_idle1=ImageLoader.LoadImage("/textures/M_idle1.png");
        M_idle2=ImageLoader.LoadImage("/textures/M_idle2.png");
        M_attackD0=ImageLoader.LoadImage("/textures/M_attackD0.png");
        M_attackD1=ImageLoader.LoadImage("/textures/M_attackD1.png");
        M_attackD3=ImageLoader.LoadImage("/textures/M_attackD3.png");
        M_attackS0=ImageLoader.LoadImage("/textures/M_attackS0.png");
        M_attackS1=ImageLoader.LoadImage("/textures/M_attackS1.png");
        M_attackS3=ImageLoader.LoadImage("/textures/M_attackS3.png");

        peste=ImageLoader.LoadImage("/textures/peste.png");
        mouse=ImageLoader.LoadImage("/textures/peste.png");

        bg1=ImageLoader.LoadImage("/BG/1.png");
        bg2=ImageLoader.LoadImage("/BG/2.png");
        bg3=ImageLoader.LoadImage("/BG/3.png");
        bg4=ImageLoader.LoadImage("/BG/4.png");

    }
}
