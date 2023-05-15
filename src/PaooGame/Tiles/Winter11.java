package PaooGame.Tiles;

import PaooGame.Graphics.Assets;

public class Winter11 extends Tile {

    public Winter11(int id) {
        /// Apel al constructorului clasei de baza
        super(Assets.winter[11], id);
    }

    public boolean IsSolid() {
        return true;
    }
}
