package PaooGame.Tiles;

import PaooGame.Graphics.Assets;

public class Winter27 extends Tile {

    public Winter27(int id) {
        /// Apel al constructorului clasei de baza
        super(Assets.winter[27], id);
    }

    @Override
    public boolean IsSolid() {
        return true;
    }
}
