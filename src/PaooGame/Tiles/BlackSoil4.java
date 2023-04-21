package PaooGame.Tiles;

import PaooGame.Graphics.Assets;

public class BlackSoil4 extends Tile {
    public BlackSoil4(int id) {
        super(Assets.BlackSoil[4], id);
    }

    public boolean IsSolid() {
        return true;
    }
}
