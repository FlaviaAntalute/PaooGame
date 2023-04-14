package PaooGame.Tiles;

import PaooGame.Graphics.Assets;

/*! \class public class GrassTile extends Tile
    \brief Abstractizeaza notiunea de dala de tip iarba.
 */
public class GrassTile3 extends Tile
{
    /*! \fn public GrassTile(int id)
        \brief Constructorul de initializare al clasei

        \param id Id-ul dalei util in desenarea hartii.
     */
    public GrassTile3(int id)
    {
        /// Apel al constructorului clasei de baza
        super(Assets.grass3, id);
    }
    /*! \fn public boolean IsSolid()
      \brief Suprascrie metoda IsSolid() din clasa de baza in sensul ca va fi luat in calcul in caz de coliziune.
   */
    public boolean IsSolid()
    {
        return true;
    }
}