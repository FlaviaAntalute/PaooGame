package PaooGame.Tiles;

import PaooGame.Graphics.Assets;

public class BlackSoil7 extends Tile {
    public BlackSoil7(int id) {
        super(Assets.BlackSoil[7], id);
    }

    public boolean IsSolid() {
        return true;
    }
}
