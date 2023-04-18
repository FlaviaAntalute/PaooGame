package PaooGame.GameStates;

import PaooGame.Game;

public class State {
    protected Game game;
    public State(Game game)
    {
        this.game=game;
    }
    public Game getGame()
    {
        return game;
    }
}