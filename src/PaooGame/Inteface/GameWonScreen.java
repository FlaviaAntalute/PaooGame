package PaooGame.Inteface;

import PaooGame.GameStates.Gamestate;
import PaooGame.GameStates.Playing;

import java.awt.*;
import java.awt.event.KeyEvent;

import static PaooGame.Game.GetWndHeight;
import static PaooGame.Game.GetWndWidth;

public class GameWonScreen {
    private Playing playing;
    public GameWonScreen(Playing playing)
    {
        this.playing=playing;
    }

    public void draw(Graphics g)
    {
        g.setColor(new Color(0, 0, 0, 200));
        g.fillRect(0, 0,GetWndWidth() ,GetWndHeight() );
        g.setColor(Color.white);
        g.drawString("YOU WON!!", GetWndWidth() / 2-40, 200);
        g.drawString("Press esc to enter Main Menu!", GetWndHeight() / 2, 250);
    }
    public void keyPressed(KeyEvent e)
    {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            playing.resetAll();
            Gamestate.state = Gamestate.MENU;
        }
    }
}
