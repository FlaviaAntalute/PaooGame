package PaooGame.GameStates;

import PaooGame.Game;
import PaooGame.Graphics.Assets;
import PaooGame.Inteface.UrmButton;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import static PaooGame.Game.GetWndHeight;
import static PaooGame.Game.GetWndWidth;

public class Options extends State implements StateMethods {
    private UrmButton back;
    public Options(Game game) {
        super(game);
        back = new UrmButton(Game.GetWndHeight() / 2+260, 495, Assets.menu);
    }

    @Override
    public void update() {
        back.update();
    }

    @Override
    public void draw(Graphics g) {
        Font f = new Font("font", Font.BOLD, 50);
        g.setFont(f);
        g.setColor(new Color(0, 0, 0, 100));
        g.fillRect(0, 0, GetWndWidth(), GetWndHeight());
        g.drawImage(Assets.gui[3], Game.GetWndWidth() / 2 - 350, 40, 700, 600, null);
        g.drawImage(Assets.gui[8],Game.GetWndWidth() / 2 - 220,110,800,600,null);
        g.setColor(Color.green);
        g.drawString("Regulile jocului", GetWndWidth() / 2 - 200, 50);
        back.draw(g);
    }

    private boolean isIn(MouseEvent e, UrmButton b) {
        return b.getBounds().contains(e.getX(), e.getY());
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (isIn(e, back)) {
            back.setMousePressed(true);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(isIn(e, back))
        {
            if (back.isMousePressed())
                Gamestate.state = Gamestate.MENU;
        }

        back.resetBools();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        back.setMouseOver(false);

        if (isIn(e, back))
            back.setMouseOver(true);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_ESCAPE)
            Gamestate.state = Gamestate.MENU;
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
