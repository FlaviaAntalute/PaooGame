package PaooGame.Tiles;

import PaooGame.Graphics.Assets;

public class Winter2 extends Tile {

    public Winter2(int id) {
        /// Apel al constructorului clasei de baza
        super(Assets.winter[2], id);
    }

    public boolean IsSolid() {
        return true;
    }
}
