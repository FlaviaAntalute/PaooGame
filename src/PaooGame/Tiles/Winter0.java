package PaooGame.Tiles;

import PaooGame.Graphics.Assets;

public class Winter0 extends Tile {

    public Winter0(int id) {
        /// Apel al constructorului clasei de baza
        super(Assets.winter[0], id);
    }

    public boolean IsSolid() {
        return true;
    }
}
