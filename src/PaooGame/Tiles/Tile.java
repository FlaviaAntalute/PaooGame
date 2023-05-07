package PaooGame.Tiles;
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
    public static final int NrTileWidth=29;
    public static final int NrTileHeight=21;

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
    public static Tile soil1= new Soil1(35);
    public static Tile soil2 = new Soil2(36);
    public static Tile soil3 = new Soil3(37);
    public static Tile soil4 = new Soil4(38);
    public static Tile soil5 = new soil5(22);

    public static Tile BlackSoil0=new BlackSoil0(11);
    public static Tile BlackSoil1=new BlackSoil1(12);
    public static Tile BlackSoil2=new BlackSoil2(13);
    public static Tile BlackSoil3=new BlackSoil3(14);
    public static Tile BlackSoil4=new BlackSoil4(15);
    public static Tile BlackSoil5=new BlackSoil5(16);
    public static Tile BlackSoil6=new BlackSoil6(17);
    public static Tile BlackSoil7=new BlackSoil7(18);
    public static Tile BlackSoil8=new BlackSoil8(19);
    public static Tile BlackSoil9=new BlackSoil9(20);
    public static Tile soilPlatform=new soilPlatform(21);

    public static Tile tufis1 = new tufis1(27);
    public static Tile tufis2  = new tufis2(28);
    public static Tile copac1 = new copac1(29);
    public static Tile copac2 = new copac2(30);
    public static Tile iarba1 = new iarba1(31);
    public static Tile pietre = new pietre(32);
    public static Tile peste = new peste(39);
    public static Tile bone = new bone(40);
    public static Tile max=new max(41);
    public static Tile snake=new snake(42);




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
        else if(idd==Tile.tufis1.GetId())
            g.drawImage(img, x, y,2* TILE_WIDTH, TILE_HEIGHT, null);
        else if(idd==Tile.peste.GetId())
            g.drawImage(img, x, y,20, 20, null);
        else if(idd==Tile.iarba1.GetId())
            g.drawImage(img, x, y+22,25,15 , null);
        else if(idd==Tile.tufis2.GetId())
            g.drawImage(img, x, y,45,TILE_HEIGHT , null);
        else if(idd==Tile.pietre.GetId())
            g.drawImage(img, x, y+22,25,15 , null);
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
