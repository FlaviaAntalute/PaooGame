package PaooGame.Tiles;

import PaooGame.Graphics.Assets;

/*! \class public class GrassTile2 extends Tile
    \brief Abstractizeaza notiunea de dala de tip iarba.
 */
public class GrassTile2 extends Tile
{
    /*! \fn public GrassTile2(int id)
        \brief Constructorul de initializare al clasei

        \param id Id-ul dalei util in desenarea hartii.
     */
    public GrassTile2(int id)
    {
        /// Apel al constructorului clasei de baza
        super(Assets.grass2, id);
    }
    /*! \fn public boolean IsSolid()
      \brief Suprascrie metoda IsSolid() din clasa de baza in sensul ca va fi luat in calcul in caz de coliziune.
   */
    public boolean IsSolid()
    {
        return true;
    }
}