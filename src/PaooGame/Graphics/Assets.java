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
    public static BufferedImage [] mouse=new BufferedImage[6];
    public static BufferedImage bone;
    public static BufferedImage [] lives=new BufferedImage[2];
    public static BufferedImage bg1;
    public static BufferedImage bg2;
    public static BufferedImage bg3;
    public static BufferedImage bg4;
    public static BufferedImage [] Max=new BufferedImage[24];
    public static BufferedImage [] MarthaDeath=new BufferedImage[10];
    public static BufferedImage [] BlackSoil=new  BufferedImage[10];


    /*! \fn public static void Init()
        \brief Functia initializaza referintele catre elementele grafice utilizate.

        Aceasta functie poate fi rescrisa astfel incat elementele grafice incarcate/utilizate
        sa fie parametrizate. Din acest motiv referintele nu sunt finale.
     */
    public static void Init()
    {
        /// Se creaza temporar un obiect SpriteSheet initializat prin intermediul clasei ImageLoader
        SpriteSheet sheet = new SpriteSheet(ImageLoader.LoadImage("/textures/TilesNonSliced.png"));
        SpriteSheet mouseSheetD=new SpriteSheet(ImageLoader.LoadImage("/textures/mouse_right.png"));
        SpriteSheet mouseSheetS=new SpriteSheet(ImageLoader.LoadImage("/textures/mouse_left.png"));
        SpriteSheet livesSheet=new SpriteSheet(ImageLoader.LoadImage("/textures/lives.png"));
        SpriteSheet MaxSSheet=new SpriteSheet(ImageLoader.LoadImage("/textures/Max_walk_S.png"));
        SpriteSheet MaxDSheet=new SpriteSheet(ImageLoader.LoadImage("/textures/Max_walk_D.png"));
        SpriteSheet MaxIdleSheet=new SpriteSheet(ImageLoader.LoadImage("/textures/Max_idle.png"));
        SpriteSheet MaxSheet=new SpriteSheet(ImageLoader.LoadImage("/textures/max.png"));
        SpriteSheet MaxSheetD=new SpriteSheet(ImageLoader.LoadImage("/textures/maxD.png"));
        SpriteSheet MarthaDeathDSheet=new SpriteSheet(ImageLoader.LoadImage("/textures/MarthaDeathD.png"));
        SpriteSheet MarthaDeathSSheet=new SpriteSheet(ImageLoader.LoadImage("/textures/MarthaDeathS.png"));

        ///sheet cu martha
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

        BlackSoil[0] = sheet.crop(7, 0);
        BlackSoil[1] = sheet.crop(8, 0);
        BlackSoil[2]=sheet.crop(9, 0);
        BlackSoil[3] = sheet.crop(7, 1);
        BlackSoil[4] = sheet.crop(8, 1);
        BlackSoil[5] = sheet.crop(9, 1);
        BlackSoil[6] = sheet.crop(7, 2);
        BlackSoil[7] = sheet.crop(8, 2);
        BlackSoil[8] = sheet.crop(9, 2);
        BlackSoil[9] = sheet.crop(10, 6);


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

        bone=ImageLoader.LoadImage("/textures/os.png");
        peste=ImageLoader.LoadImage("/textures/peste.png");

        mouse[0]=mouseSheetD.crop1(0,0,32,20);
        mouse[1]=mouseSheetD.crop1(1,0,32,20);
        mouse[2]=mouseSheetD.crop1(2,0,32,20);
        mouse[3]=mouseSheetS.crop1(0,0,32,20);
        mouse[4]=mouseSheetS.crop1(1,0,32,20);
        mouse[5]=mouseSheetS.crop1(2,0,32,20);

        lives[0]=livesSheet.crop1(0,0,36,32);
        lives[1]=livesSheet.crop2(75,0,36,32);

        bg1=ImageLoader.LoadImage("/BG/1.png");
        bg2=ImageLoader.LoadImage("/BG/2.png");
        bg3=ImageLoader.LoadImage("/BG/3.png");
        bg4=ImageLoader.LoadImage("/BG/4.png");

        Max[0]=MaxDSheet.crop2(254,5,56,31);
        Max[1]=MaxDSheet.crop2(197,5,49,31);
        Max[2]=MaxDSheet.crop2(131,5,51,31);
        Max[3]=MaxDSheet.crop2(62,5,56,31);
        Max[4]=MaxDSheet.crop2(0,5,54,31);

        Max[5]=MaxSSheet.crop2(0,5,56,31);
        Max[6]=MaxSSheet.crop2(64,5,49,31);
        Max[7]=MaxSSheet.crop2(128,5,51,31);
        Max[8]=MaxSSheet.crop2(192,5,56,31);
        Max[9]=MaxSSheet.crop2(256,5,54,31);

        Max[10]=MaxIdleSheet.crop2(0,0,52,31);
        Max[11]=MaxIdleSheet.crop2(64,0,52,31);
        Max[12]=MaxIdleSheet.crop2(128,0,51,31);
        Max[13]=MaxIdleSheet.crop2(192,0,51,31);
        Max[14]=MaxIdleSheet.crop2(256,0,47,31);

        Max[15]=MaxSheet.crop2(8,256,47,32);
        Max[16]=MaxSheet.crop2(7,63,49,33);

        Max[17]=MaxSheetD.crop2(777,256,47,32);
        Max[18]=MaxSheetD.crop2(776,63,49,33);

        MarthaDeath[0]=MarthaDeathDSheet.crop2(0,0,28,14);
        MarthaDeath[1]=MarthaDeathDSheet.crop2(32,0,29,13);
        MarthaDeath[2]=MarthaDeathDSheet.crop2(64,0,29,12);
        MarthaDeath[3]=ImageLoader.LoadImage("/textures/MarthaDeathD0.png");

        MarthaDeath[4]=MarthaDeathDSheet.crop2(0,0,28,14);
        MarthaDeath[5]=MarthaDeathDSheet.crop2(31,0,29,13);
        MarthaDeath[6]=MarthaDeathDSheet.crop2(63,0,29,12);
        MarthaDeath[7]=ImageLoader.LoadImage("/textures/MarthaDeathS0.png");
    }
}
