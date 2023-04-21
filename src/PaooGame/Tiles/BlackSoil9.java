package PaooGame.Tiles;

import PaooGame.Graphics.Assets;

public class BlackSoil9 extends Tile {
    public BlackSoil9(int id) {
        super(Assets.BlackSoil[9], id);
    }

    public boolean IsSolid() {
        return true;
    }
}
