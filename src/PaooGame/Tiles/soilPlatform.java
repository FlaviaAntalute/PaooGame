package PaooGame.Tiles;

import PaooGame.Graphics.Assets;

public class soilPlatform extends Tile {
    public soilPlatform(int id) {
        super(Assets.soilPlatform, id);
    }

    public boolean IsSolid() {
        return true;
    }
}

