package PaooGame.Tiles;

import PaooGame.Graphics.Assets;

/*! \class public class GrassTile extends Tile
    \brief Abstractizeaza notiunea de dala de tip iarba.
 */
public class GrassTile4 extends Tile
{
    /*! \fn public GrassTile(int id)
        \brief Constructorul de initializare al clasei

        \param id Id-ul dalei util in desenarea hartii.
     */
    public GrassTile4(int id)
    {
        /// Apel al constructorului clasei de baza
        super(Assets.grass4, id);
    }
    public boolean IsSolid()
    {
        return true;
    }
}