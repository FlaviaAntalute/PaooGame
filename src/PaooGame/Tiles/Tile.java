package PaooGame.Tiles;
import  PaooGame.Martha.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/*! \class public class Tile
    \brief Retine toate dalele intr-un vector si ofera posibilitatea regasirii dupa un id.
 */
public class Tile
{
    private static final int NO_TILES   = 60;
    public static Tile[] tiles = new Tile[NO_TILES];       /*!< Vector de referinte de tipuri de dale.*/
    public static final int TILE_WIDTH  = 32;                       /*!< Latimea unei dale.*/
    public static final int TILE_HEIGHT = 32;                       /*!< Inaltimea unei dale.*/
    public static final int NrTileWidth=25;
    public static final int NrTileHeight=19;

    protected BufferedImage img;                                    /*!< Imaginea aferenta tipului de dala.*/
    protected final int id;                                         /*!< Id-ul unic aferent tipului de dala.*/

    /// De remarcat ca urmatoarele dale sunt statice si publice. Acest lucru imi permite sa le am incarcate
    /// o singura data in memorie
    public static Tile grassTile1 = new GrassTile1(1);     /*!< Dala de tip iarba*/
    public static Tile grassTile2 = new GrassTile2(2);
    public static Tile grassTile3= new GrassTile3(3);
    public static Tile grassTile4= new GrassTile4(4);
    public static Tile grassTile5 = new GrassTile5(5);
    public static Tile grassTile6 = new GrassTile6(6);

    public static Tile waterTile = new WaterTile(7);     /*!< Dala de tip apa*/
    public static Tile waterTile1 = new WaterTile1(8);
    public static Tile soilTile = new SoilTile(9);      /*!< Dala de tip sol/pamant*/
    public static Tile waterTile2 = new WaterTile2(10);
    public static Tile M_walkD1 = new M_walkD1(11);
    public static Tile M_walkD2 = new M_walkD2(12);
    public static Tile M_walkD3 = new M_walkD3(13);
    public static Tile M_walkD4 = new M_walkD4(14);
    public static Tile M_walkS1  = new M_walkS1(15);
    public static Tile M_walkS2= new M_walkS2(16);
    public static Tile M_walkS3 = new M_walkS3(17);
    public static Tile M_walkS4   = new M_walkS4(18);
    public static Tile M_jumpD1 = new M_jumpD1(19);
    public static Tile M_jumpD2 = new M_jumpD2(20);
    public static Tile M_jumpD3= new M_jumpD3(21);
    public static Tile M_jumpS1 = new M_jumpS1(22);
    public static Tile M_jumpS2 = new M_jumpS2(23);
    public static Tile M_jumpS3 = new M_jumpS3(24);
    public static Tile M_walkD0= new M_walkD0(25);
    public static Tile M_walkS0  = new M_walkS0(26);
    public static Tile tufis1 = new tufis1(27);
    public static Tile tufis2  = new tufis2(28);
    public static Tile copac1 = new copac1(29);
    public static Tile copac2 = new copac2(30);
    public static Tile iarba1 = new iarba1(31);
    public static Tile pietre = new pietre(32);
    public static Tile M_idle1 = new M_idle1(33);

    public static Tile M_idle2= new M_idle2(34);
    public static Tile soil1= new Soil1(35);      /*!< Dala de tip sol/pamant*/
    public static Tile soil2 = new Soil2(36);      /*!< Dala de tip sol/pamant*/
    public static Tile soil3 = new Soil3(37);      /*!< Dala de tip sol/pamant*/
    public static Tile soil4 = new Soil4(38);      /*!< Dala de tip sol/pamant*/
    public static Tile peste = new peste(39);      /*!< Dala de tip sol/pamant*/

    /*! \fn public Tile(BufferedImage texture, int id)
        \brief Constructorul aferent clasei.

        \param image Imaginea corespunzatoare dalei.
        \param id Id-ul dalei.
     */
    public Tile(BufferedImage image, int idd)
    {
        img = image;
        id = idd;
        tiles[id] = this;
    }

    /*! \fn public void Update()
        \brief Actualizeaza proprietatile dalei.
     */
    public void Update()
    {

    }

    /*! \fn public void Draw(Graphics g, int x, int y)
        \brief Deseneaza in fereastra dala.

        \param g Contextul grafic in care sa se realizeze desenarea
        \param x Coordonata x in cadrul ferestrei unde sa fie desenata dala
        \param y Coordonata y in cadrul ferestrei unde sa fie desenata dala
     */
    public void Draw(Graphics g, int x, int y,int idd)
    {
        /// Desenare dala
        if(idd==Tile.copac2.GetId()||idd==Tile.copac1.GetId())
            g.drawImage(img, x, y,4* TILE_WIDTH, 4*TILE_HEIGHT, null);
        else if(idd==Tile.tufis1.GetId()||idd==Tile.pietre.GetId())
            g.drawImage(img, x, y,2* TILE_WIDTH, TILE_HEIGHT, null);
        else if(idd==Tile.peste.GetId())
            g.drawImage(img, x, y,20, 20, null);
        else
            g.drawImage(img, x, y, TILE_WIDTH, TILE_HEIGHT, null);
    }

    /*! \fn public boolean IsSolid()
        \brief Returneaza proprietatea de dala solida (supusa coliziunilor) sau nu.
     */
    public boolean IsSolid()
    {
        return false;
    }

    /*! \fn public int GetId()
        \brief Returneaza id-ul dalei.
     */
    public int GetId()
    {
        return id;
    }

}
