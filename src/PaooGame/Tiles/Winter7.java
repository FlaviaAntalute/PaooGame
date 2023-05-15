package PaooGame.Tiles;

import PaooGame.Graphics.Assets;

public class Winter7 extends Tile {

    public Winter7(int id) {
        /// Apel al constructorului clasei de baza
        super(Assets.winter[7], id);
    }

    public boolean IsSolid() {
        return true;
    }
}
