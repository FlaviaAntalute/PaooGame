package PaooGame.Tiles;

import PaooGame.Graphics.Assets;

public class Soil1 extends Tile {

    public Soil1(int id) {
        super(Assets.soil1, id);
    }

    public boolean IsSolid() {
        return true;
    }
}
