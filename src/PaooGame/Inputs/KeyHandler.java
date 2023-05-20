package PaooGame.Inputs;
import PaooGame.Game;
import PaooGame.GameStates.Gamestate;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    Game game;
    public KeyHandler(Game game) {
        this.game=game;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (Gamestate.state){
            case MENU :
                game.getMenu().keyPressed(e);
                break;
            case PLAYING:
                game.getPlaying().keyPressed(e);
                break;
            case OPTIONS:
                game.getOptions().keyPressed(e);
                break;
            default:
                break;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (Gamestate.state){
            case MENU :
                game.getMenu().keyReleased(e);
                break;
            case PLAYING:
                game.getPlaying().keyReleased(e);
                break;
            default:
                break;
        }
    }
}
