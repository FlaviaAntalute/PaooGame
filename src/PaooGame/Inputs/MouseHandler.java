package PaooGame.Inputs;

import PaooGame.Game;
import PaooGame.GameStates.Gamestate;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseHandler implements MouseListener, MouseMotionListener {
    private Game game;

    public MouseHandler(Game game) {
        this.game=game;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        switch (Gamestate.state){
            case MENU :
                game.getMenu().mouseClicked(e);
                break;
            case PLAYING:
                game.getPlaying().mouseClicked(e);
                break;
            default:
                break;
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }
}
