package PaooGame.Tiles;

import PaooGame.Graphics.Assets;

public class Winter5 extends Tile {

    public Winter5(int id) {
        /// Apel al constructorului clasei de baza
        super(Assets.winter[5], id);
    }

    public boolean IsSolid() {
        return true;
    }
}
