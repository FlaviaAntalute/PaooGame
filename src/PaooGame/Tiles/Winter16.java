package PaooGame.Tiles;

import PaooGame.Graphics.Assets;

public class Winter16 extends Tile {

    public Winter16(int id) {
        /// Apel al constructorului clasei de baza
        super(Assets.winter[16], id);
    }

    public boolean IsSolid() {
        return true;
    }
}
