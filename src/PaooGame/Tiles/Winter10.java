package PaooGame.Tiles;

import PaooGame.Graphics.Assets;

public class Winter10 extends Tile {

    public Winter10(int id) {
        /// Apel al constructorului clasei de baza
        super(Assets.winter[10], id);
    }

    public boolean IsSolid() {
        return true;
    }
}
