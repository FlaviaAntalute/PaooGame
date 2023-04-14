package PaooGame.Martha;
import PaooGame.Graphics.Assets;
import PaooGame.Tiles.Tile;

/*! \class public class GrassTile extends Tile
    \brief Abstractizeaza notiunea de dala de tip iarba.
 */
public class M_walkD2 extends Tile {
    /*! \fn public GrassTile(int id)
        \brief Constructorul de initializare al clasei

        \param id Id-ul dalei util in desenarea hartii.
     */
    public M_walkD2(int id) {
        /// Apel al constructorului clasei de baza
        super(Assets.M_walkD2, id);
    }
}

