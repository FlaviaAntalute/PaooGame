package PaooGame.Tiles;

import PaooGame.Graphics.Assets;

public class BlackSoil6 extends Tile {
    public BlackSoil6(int id) {
        super(Assets.BlackSoil[6], id);
    }

    public boolean IsSolid() {
        return true;
    }
}

