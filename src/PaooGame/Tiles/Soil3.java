package PaooGame.Tiles;

import PaooGame.Graphics.Assets;

public class Soil3 extends Tile {
    /*! \fn public SoilTile(int id)
        \brief Constructorul de initializare al clasei

        \param id Id-ul dalei util in desenarea hartii.
     */
    public Soil3(int id) {
        super(Assets.soil3, id);
    }

    public boolean IsSolid() {
        return true;
    }
}
