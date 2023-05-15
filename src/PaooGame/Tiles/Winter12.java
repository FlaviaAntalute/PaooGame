package PaooGame.Tiles;

import PaooGame.Graphics.Assets;

public class Winter12 extends Tile {

    public Winter12(int id) {
        /// Apel al constructorului clasei de baza
        super(Assets.winter[12], id);
    }

    public boolean IsSolid() {
        return true;
    }
}
