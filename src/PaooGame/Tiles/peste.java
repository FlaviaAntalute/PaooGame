package PaooGame.Tiles;

import PaooGame.Graphics.Assets;

public class peste extends Tile {
    /*! \fn public GrassTile(int id)
        \brief Constructorul de initializare al clasei

        \param id Id-ul dalei util in desenarea hartii.
     */
    public peste(int id) {
        /// Apel al constructorului clasei de baza
        super(Assets.peste, id);
    }

    /*! \fn public boolean IsSolid()
      \brief Suprascrie metoda IsSolid() din clasa de baza in sensul ca va fi luat in calcul in caz de coliziune.
   */
    public boolean CanBeCollected() {
        return true;
    }
}
