package PaooGame.GameStates;

import PaooGame.Game;
import PaooGame.Graphics.Background;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import static java.awt.Font.BOLD;

public class Menu extends State implements StateMethods {

    public Menu(Game game) {
        super(game);
    }

    @Override
    public void update() {

    }
    @Override
    public void draw(Graphics g) {
        Font f1=new Font("font1", BOLD,18);
        Background.drawBgT(g,0);
        g.setColor(Color.ORANGE);
        g.setFont(f1);
        g.drawString("MENU",game.getWnd().GetWndWidth()/2-20, 180);
        g.drawString("Press ENTER to start",game.getWnd().GetWndWidth()/2-90, 215);
        g.drawString("To win you have to collect all the food and defeat all the enemies",game.getWnd().GetWndWidth()/2-250, 245);

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
