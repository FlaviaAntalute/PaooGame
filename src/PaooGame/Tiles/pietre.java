package PaooGame.Tiles;

import PaooGame.Graphics.Assets;

public class pietre extends Tile {
    /*! \fn public GrassTile(int id)
        \brief Constructorul de initializare al clasei

        \param id Id-ul dalei util in desenarea hartii.
     */
    public pietre(int id) {
        /// Apel al constructorului clasei de baza
        super(Assets.pietre, id);
    }
}
