package PaooGame.Tiles;

import PaooGame.Graphics.Assets;

public class BlackSoil5 extends Tile {
    public BlackSoil5(int id) {
        super(Assets.BlackSoil[5], id);
    }

    public boolean IsSolid() {
        return true;
    }
}
