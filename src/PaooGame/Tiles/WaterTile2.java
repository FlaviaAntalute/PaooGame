package PaooGame.Tiles;

import PaooGame.Graphics.Assets;

/*! \class public class WaterTile2 extends Tile
    \brief Abstractizeaza notiunea de dala de tip apa.
 */public class WaterTile2 extends Tile
{
    /*! \fn public WaterTile2(int id)
       \brief Constructorul de initializare al clasei

       \param id Id-ul dalei util in desenarea hartii.
    */
    public WaterTile2(int id)
    {
        super(Assets.water2, id);
    }

}
