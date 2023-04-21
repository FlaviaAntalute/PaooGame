package PaooGame.Tiles;

import PaooGame.Graphics.Assets;

public class BlackSoil8 extends Tile {
    public BlackSoil8(int id) {
        super(Assets.BlackSoil[8], id);
    }

    public boolean IsSolid() {
        return true;
    }
}
