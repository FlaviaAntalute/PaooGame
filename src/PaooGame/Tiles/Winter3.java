package PaooGame.Tiles;

import PaooGame.Graphics.Assets;

public class Winter3 extends Tile {

    public Winter3(int id) {
        /// Apel al constructorului clasei de baza
        super(Assets.winter[3], id);
    }

    public boolean IsSolid() {
        return true;
    }
}
