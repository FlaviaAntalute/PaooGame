package PaooGame.Tiles;

import PaooGame.Graphics.Assets;

public class Winter17 extends Tile {

    public Winter17(int id) {
        /// Apel al constructorului clasei de baza
        super(Assets.winter[17], id);
    }

    public boolean IsSolid() {
        return true;
    }
}
