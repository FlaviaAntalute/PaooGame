package PaooGame.Graphics;

import PaooGame.Exceptions.IndexCropException;

import java.awt.image.BufferedImage;
import java.io.IOException;

import static PaooGame.Exceptions.IndexCropException.handleException;

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
    public static BufferedImage soil5;

    public static BufferedImage soilPlatform;

    public static BufferedImage grass1;
    public static BufferedImage grass2;
    public static BufferedImage grass3;
    public static BufferedImage grass4;
    public static BufferedImage grass5;
    public static BufferedImage grass6;
    public static BufferedImage grass7;
    public static BufferedImage grass8;

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
    public static BufferedImage bgw;
    public static BufferedImage star1;
    public static BufferedImage [] Max=new BufferedImage[24];
    public static BufferedImage [] MarthaDeath=new BufferedImage[10];
    public static BufferedImage [] BlackSoil=new  BufferedImage[10];
    public static BufferedImage [] Snake=new  BufferedImage[20];
    public static BufferedImage [] Rex=new  BufferedImage[20];
    public static BufferedImage [] gui=new BufferedImage[20];
    public static BufferedImage [] start=new BufferedImage[3];
    public static BufferedImage [] quit=new BufferedImage[3];
    public static BufferedImage [] options=new BufferedImage[3];
    public static BufferedImage [] replay=new BufferedImage[3];
    public static BufferedImage [] menu=new BufferedImage[3];
    public static BufferedImage [] continuee=new BufferedImage[3];
    public static BufferedImage [] next=new BufferedImage[3];
    public static BufferedImage [] save=new BufferedImage[3];
    public static BufferedImage [] load=new BufferedImage[3];


    public static BufferedImage [] winter=new BufferedImage[30];
    public static BufferedImage cats;
    public static BufferedImage [] forest=new BufferedImage[30];

    /*! \fn public static void Init()
        \brief Functia initializaza referintele catre elementele grafice utilizate.

        Aceasta functie poate fi rescrisa astfel incat elementele grafice incarcate/utilizate
        sa fie parametrizate. Din acest motiv referintele nu sunt finale.
     */
    public static void Init()
    {
        try {
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
            //SpriteSheet MarthaDeathSSheet=new SpriteSheet(ImageLoader.LoadImage("/textures/MarthaDeathS.png"));
            SpriteSheet SnakeSheetD=new SpriteSheet(ImageLoader.LoadImage("/textures/sarpe_drt.png"));
            SpriteSheet SnakeSheetS=new SpriteSheet(ImageLoader.LoadImage("/textures/sarpe_stg.png"));
            SpriteSheet RexSheetD=new SpriteSheet(ImageLoader.LoadImage("/textures/RexD.png"));
            SpriteSheet RexSheetS=new SpriteSheet(ImageLoader.LoadImage("/textures/RexS.png"));
            SpriteSheet GUI=new SpriteSheet(ImageLoader.LoadImage("/gui/Pixel UI pack 1.png"));
            SpriteSheet Winter=new SpriteSheet(ImageLoader.LoadImage("/textures/iarna/winter.png"));
            //imagini gui
            gui[0]=ImageLoader.LoadImage("/gui/No.png");
            gui[1]=ImageLoader.LoadImage("/gui/Yes.png");
            gui[2]=GUI.crop2(581,133,86,22);//menu write
            gui[3]=GUI.crop2(0,0,48,48);//menu
            gui[4]=ImageLoader.LoadImage("/gui/LevelComplete.png");
            gui[5]=ImageLoader.LoadImage("/gui/GameWonImg.png");
            gui[6]=GUI.crop2(177,97,62,30);
            gui[7]=GUI.crop2(177,97,62,30);
            gui[8]=ImageLoader.LoadImage("/gui/optiuni.png");
            start[0]=GUI.crop2(385,321,62,30);//start1
            start[1]=GUI.crop2(449,321,62,30);//start2
            start[2]=GUI.crop2(513,321,62,30);//start3

            quit[0]=GUI.crop2(385,449,62,30);//quit1
            quit[1]=GUI.crop2(449,449,62,30);//quit2
            quit[2]=GUI.crop2(513,449,62,30);//quit3

            options[0]=GUI.crop2(577,65,78,30);//options
            options[1]=GUI.crop2(705,65,78,30);//options
            options[2]=GUI.crop2(832,65,78,30);//options

            replay[0]=GUI.crop2(577,33,78,30);//replay
            replay[1]=GUI.crop2(705,33,78,30);//replay
            replay[2]=GUI.crop2(832,33,78,30);//replay

            menu[0]=GUI.crop2(386,353,62,30);//menu
            menu[1]=GUI.crop2(449,353,62,30);//menu
            menu[2]=GUI.crop2(513,353,62,30);//menu

            continuee[0]=GUI.crop2(577,193,94,30);//continue
            continuee[1]=GUI.crop2(705,193,94,30);//continue
            continuee[2]=GUI.crop2(832,193,94,30);//continue

            next[0]=GUI.crop2(1,449,62,30);//next
            next[1]=GUI.crop2(65,449,62,30);//next
            next[2]=GUI.crop2(129,449,62,30);//next

            save[0]=GUI.crop2(1,353,62,30);//next
            save[1]=GUI.crop2(65,353,62,30);//next
            save[2]=GUI.crop2(129,353,62,30);//next

            load[0]=GUI.crop2(1,385,62,30);//next
            load[1]=GUI.crop2(65,385,62,30);//next
            load[2]=GUI.crop2(129,385,62,30);//next


            /// Se obtin subimaginile corespunzatoare elementelor necesare.

            grass1 = sheet.crop(0, 0);
            grass2 = sheet.crop(1, 0);
            grass3 = sheet.crop(2, 0);
            grass4 = sheet.crop(0, 6);
            grass5 = sheet.crop(4, 6);
            grass6 = sheet.crop(1, 5);
            grass7=sheet.crop(5, 3);
            grass8=sheet.crop(0, 5);

            water = sheet.crop(6, 4);
            water1 = sheet.crop(6, 5);
            water2 = sheet.crop(6, 6);

            soil = sheet.crop(1, 1);
            soil1 = sheet.crop(0, 2);
            soil2 = sheet.crop(2, 2);
            soil3 = sheet.crop(1, 2);
            soil4 = sheet.crop(2, 1);
            soil5 = sheet.crop(0, 1);


            soilPlatform = sheet.crop(2, 3);

            BlackSoil[0] = sheet.crop(7, 0);
            BlackSoil[1] = sheet.crop(8, 0);
            BlackSoil[2] = sheet.crop(9, 0);
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

            //Mers dreapta
            Snake[0]=SnakeSheetD.crop2(43,86,10,10);
            Snake[1]=SnakeSheetD.crop2(74,86,13,10);
            Snake[2]=SnakeSheetD.crop2(107,86,14,10);
            Snake[3]=SnakeSheetD.crop2(140,86,13,10);
            Snake[4]=SnakeSheetD.crop2(171,85,12,11);
            //Mers stanga
            Snake[5]=SnakeSheetS.crop2(267,86,10,10);
            Snake[6]=SnakeSheetS.crop2(233,86,13,10);
            Snake[7]=SnakeSheetS.crop2(199,86,14,10);
            Snake[8]=SnakeSheetS.crop2(167,86,13,10);
            Snake[9]=SnakeSheetS.crop2(137,85,12,11);
            //atac dreapta
            Snake[10]=SnakeSheetD.crop2(10,118,12,10);
            Snake[11]=SnakeSheetD.crop2(43,120,13,8);
            Snake[12]=SnakeSheetD.crop2(75,120,15,8);
            Snake[13]=SnakeSheetD.crop2(106,119,18,8);
            Snake[14]=SnakeSheetD.crop2(136,119,17,8);
            //atac stanga
            Snake[15]=SnakeSheetS.crop2(298,118,12,10);
            Snake[16]=SnakeSheetS.crop2(264,118,13,10);
            Snake[17]=SnakeSheetS.crop2(230,118,15,10);
            Snake[18]=SnakeSheetS.crop2(196,118,18,9);
            Snake[19]=SnakeSheetS.crop2(167,118,17,10);

            //idle dreapta
            Rex[0]=RexSheetD.crop2(772,17,52,31);
            Rex[1]=RexSheetD.crop2(708,17,52,31);
            Rex[2]=RexSheetD.crop2(645,17,51,31);
            Rex[3]=RexSheetD.crop2(581,17,50,31);
            Rex[4]=RexSheetD.crop2(521,17,50,31);
            //idle stanga
            Rex[5]=RexSheetS.crop2(8,17,52,31);
            Rex[6]=RexSheetS.crop2(72,17,52,31);
            Rex[7]=RexSheetS.crop2(136,17,51,31);
            Rex[8]=RexSheetS.crop2(201,17,50,31);
            Rex[9]=RexSheetS.crop2(264,17,50,31);
            //jos dreapta
            Rex[10]=RexSheetD.crop2(716,256,46,32);
            Rex[11]=RexSheetD.crop2(649,256,46,32);
            Rex[12]=RexSheetD.crop2(586,256,47,32);
            Rex[13]=RexSheetD.crop2(523,256,47,32);
            //jos stanga
            Rex[14]=RexSheetS.crop2(74 ,256,46,32);
            Rex[15]=RexSheetS.crop2(137,256,46,32);
            Rex[16]=RexSheetS.crop2(198,256,47,32);
            Rex[17]=RexSheetS.crop2(262,256,47,32);

            //latrat stanga
            Rex[18]=RexSheetS.crop2(8,256,47,32);
            //latrat dreapta
            Rex[19]=RexSheetD.crop2(777,256,47,32);


            int k=0;
            for(int i=0;i<4;i++)
            {
                for(int j=0;j<4;++j) {
                    winter[k] = Winter.crop1(j , i, 128, 128);
                    k++;
                }
            }
            winter[k-1]=ImageLoader.LoadImage("/textures/iarna/17.png");
            winter[16]=ImageLoader.LoadImage("/textures/iarna/14.png");
            winter[17]=ImageLoader.LoadImage("/textures/iarna/15.png");
            winter[18]=ImageLoader.LoadImage("/textures/iarna/16.png");

            winter[19]=ImageLoader.LoadImage("/textures/iarna/Crystal.png");
            winter[20]=ImageLoader.LoadImage("/textures/iarna/Igloo.png");
            winter[21]=ImageLoader.LoadImage("/textures/iarna/Sign_1S.png");
            winter[22]=ImageLoader.LoadImage("/textures/iarna/Sign_2.png");
            winter[23]=ImageLoader.LoadImage("/textures/iarna/SnowMan.png");
            winter[24]=ImageLoader.LoadImage("/textures/iarna/Stone.png");
            winter[25]=ImageLoader.LoadImage("/textures/iarna/Tree_1.png");
            winter[26]=ImageLoader.LoadImage("/textures/iarna/Tree_2.png");
            winter[27]=ImageLoader.LoadImage("/textures/iarna/IceBox.png");

            bgw=ImageLoader.LoadImage("/BG/BG.png");
            star1=ImageLoader.LoadImage("/BG/star1.png");

            cats=ImageLoader.LoadImage("/textures/cats.png");

        }catch (IndexCropException e){
            handleException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
