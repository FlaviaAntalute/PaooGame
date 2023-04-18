package PaooGame.GameStates;

import PaooGame.Game;
import PaooGame.Graphics.Background;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class Menu extends State implements StateMethods {

    public Menu(Game game) {
        super(game);
    }

    @Override
    public void update() {

    }
    @Override
    public void draw(Graphics g) {
        Background.drawBgT(g,0);
        g.setColor(Color.ORANGE);
        g.drawString("MENU",game.getWnd().GetWndWidth()/2-20, 180);
        g.drawString("Press ENTER to start",game.getWnd().GetWndWidth()/2-60, 200);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_ENTER)
            Gamestate.state=Gamestate.PLAYING;
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
