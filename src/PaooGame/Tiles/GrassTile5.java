package PaooGame.Tiles;

import PaooGame.Graphics.Assets;

/*! \class public class GrassTile5 extends Tile
    \brief Abstractizeaza notiunea de dala de tip iarba.
 */
public class GrassTile5 extends Tile
{
    /*! \fn public GrassTile5(int id)
        \brief Constructorul de initializare al clasei

        \param id Id-ul dalei util in desenarea hartii.
     */
    public GrassTile5(int id)
    {
        /// Apel al constructorului clasei de baza
        super(Assets.grass5, id);
    }
    public boolean IsSolid()
    {
        return true;
    }
}