package PaooGame.Tiles;

import PaooGame.Graphics.Assets;

public class BlackSoil0 extends Tile {
    public BlackSoil0(int id) {
        super(Assets.BlackSoil[0], id);
    }

    public boolean IsSolid() {
        return true;
    }
}


