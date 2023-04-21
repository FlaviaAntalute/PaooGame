package PaooGame.Tiles;

import PaooGame.Graphics.Assets;

public class BlackSoil3 extends Tile {
    public BlackSoil3(int id) {
        super(Assets.BlackSoil[3], id);
    }

    public boolean IsSolid() {
        return true;
    }
}
