package PaooGame.Tiles;

import PaooGame.Graphics.Assets;

public class Winter9 extends Tile {

    public Winter9(int id) {
        /// Apel al constructorului clasei de baza
        super(Assets.winter[9], id);
    }

    public boolean IsSolid() {
        return true;
    }
}
