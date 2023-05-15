package PaooGame.Tiles;

import PaooGame.Graphics.Assets;

public class Winter6 extends Tile {

    public Winter6(int id) {
        /// Apel al constructorului clasei de baza
        super(Assets.winter[6], id);
    }

    public boolean IsSolid() {
        return true;
    }
}
