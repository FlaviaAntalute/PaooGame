package PaooGame.Tiles;

import PaooGame.Graphics.Assets;

public class BlackSoil1 extends Tile {
    public BlackSoil1(int id) {
        super(Assets.BlackSoil[1], id);
    }

    public boolean IsSolid() {
        return true;
    }
}

