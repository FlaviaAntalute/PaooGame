package PaooGame.Tiles;

import PaooGame.Graphics.Assets;

public class Soil1 extends Tile {
    /*! \fn public SoilTile(int id)
        \brief Constructorul de initializare al clasei

        \param id Id-ul dalei util in desenarea hartii.
     */
    public Soil1(int id) {
        super(Assets.soil1, id);
    }

    public boolean IsSolid() {
        return true;
    }
}
