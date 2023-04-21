package PaooGame.Tiles;

import PaooGame.Graphics.Assets;

public class soil5 extends Tile {
    public soil5(int id) {
        super(Assets.soil5, id);
    }

    public boolean IsSolid() {
        return true;
    }
}
