package PaooGame.Inteface;

import PaooGame.Game;
import PaooGame.GameStates.Gamestate;
import PaooGame.GameStates.Playing;
import PaooGame.Graphics.Assets;

import java.awt.*;
import java.awt.event.KeyEvent;

import static PaooGame.Game.GetWndHeight;
import static PaooGame.Game.GetWndWidth;
import static java.awt.Font.BOLD;

public class GameWonScreen {
    private Playing playing;
    public GameWonScreen(Playing playing)
    {
        this.playing=playing;
    }

    public void draw(Graphics g)
    {
        g.drawImage(Assets.gui[5],0,0,950,700,null);
        g.setColor(new Color(0, 0, 0, 150));
        g.fillRect(0, 0,GetWndWidth() ,GetWndHeight() );
        g.drawImage(Assets.gui[6], Game.GetWndWidth()/2-150,50,300,150,null);
        Font f1=new Font("font1", BOLD,40);
        g.setFont(f1);
        g.setColor(new Color(255,128,0));
        g.drawString("YOU WON!!", GetWndWidth() / 2-110, 130);
        g.drawString("Press esc to enter Main Menu!", GetWndHeight() / 2-120, 600);
    }
    public void keyPressed(KeyEvent e)
    {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            playing.resetAll();
            playing.setLvlIndex(0);
            playing.resetTotalPoints();
            Gamestate.state = Gamestate.MENU;
        }
    }
}
