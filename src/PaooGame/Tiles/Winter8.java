package PaooGame.Tiles;

import PaooGame.Graphics.Assets;

public class Winter8 extends Tile {

    public Winter8(int id) {
        /// Apel al constructorului clasei de baza
        super(Assets.winter[8], id);
    }

    public boolean IsSolid() {
        return true;
    }
}
