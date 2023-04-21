package PaooGame.Tiles;

import PaooGame.Graphics.Assets;

public class BlackSoil2 extends Tile {
    public BlackSoil2(int id) {
        super(Assets.BlackSoil[2], id);
    }

    public boolean IsSolid() {
        return true;
    }
}
