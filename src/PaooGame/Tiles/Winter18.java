package PaooGame.Tiles;

import PaooGame.Graphics.Assets;

public class Winter18 extends Tile {

    public Winter18(int id) {
        /// Apel al constructorului clasei de baza
        super(Assets.winter[18], id);
    }

    public boolean IsSolid() {
        return true;
    }
}
