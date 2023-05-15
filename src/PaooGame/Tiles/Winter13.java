package PaooGame.Tiles;

import PaooGame.Graphics.Assets;

public class Winter13 extends Tile {

    public Winter13(int id) {
        /// Apel al constructorului clasei de baza
        super(Assets.winter[13], id);
    }

    public boolean IsSolid() {
        return true;
    }
}
