package PaooGame.Tiles;

import PaooGame.Graphics.Assets;

public class Winter4 extends Tile {

    public Winter4(int id) {
        /// Apel al constructorului clasei de baza
        super(Assets.winter[4], id);
    }

    public boolean IsSolid() {
        return true;
    }
}
