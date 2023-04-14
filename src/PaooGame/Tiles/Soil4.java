package PaooGame.Tiles;

import PaooGame.Graphics.Assets;

public class Soil4 extends Tile {
    /*! \fn public SoilTile(int id)
        \brief Constructorul de initializare al clasei

        \param id Id-ul dalei util in desenarea hartii.
     */
    public Soil4(int id) {
        super(Assets.soil4, id);
    }

    public boolean IsSolid() {
        return true;
    }
}
