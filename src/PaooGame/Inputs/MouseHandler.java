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
            case OPTIONS:
                game.getOptions().mouseClicked(e);
                break;
            default:
                break;
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        switch (Gamestate.state){
            case MENU :
                game.getMenu().mousePressed(e);
                break;
            case PLAYING:
                game.getPlaying().mousePressed(e);
                break;
            case OPTIONS:
                game.getOptions().mousePressed(e);
                break;
            default:
                break;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        switch (Gamestate.state){
            case MENU :
                game.getMenu().mouseReleased(e);
                break;
            case PLAYING:
                game.getPlaying().mouseReleased(e);
                break;
            case OPTIONS:
                game.getOptions().mouseReleased(e);
                break;
            default:
                break;
        }
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
        switch (Gamestate.state){
            case MENU :
                game.getMenu().mouseMoved(e);
                break;
            case PLAYING:
                game.getPlaying().mouseMoved(e);
                break;
            case OPTIONS:
                game.getOptions().mouseMoved(e);
                break;
            default:
                break;
        }
    }
}
