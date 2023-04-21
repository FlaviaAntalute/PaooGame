package PaooGame.Tiles;

import PaooGame.Graphics.Assets;

public class Soil2 extends Tile {

    public Soil2(int id) {
        super(Assets.soil2, id);
    }

    public boolean IsSolid() {
        return true;
    }
}
