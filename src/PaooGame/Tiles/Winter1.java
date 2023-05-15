package PaooGame.Tiles;

import PaooGame.Graphics.Assets;

public class Winter1 extends Tile
{

    public Winter1(int id)
    {
        /// Apel al constructorului clasei de baza
        super(Assets.winter[1], id);
    }
    public boolean IsSolid()
    {
        return true;
    }
}
