package PaooGame.Inteface;

import PaooGame.Game;
import PaooGame.GameStates.Gamestate;
import PaooGame.GameStates.Playing;
import PaooGame.Graphics.Assets;
import PaooGame.Graphics.Background;

import java.awt.*;
import java.awt.event.MouseEvent;

import static PaooGame.Game.GetWndHeight;
import static PaooGame.Game.GetWndWidth;

public class LevelCompletedScreen implements NextButtonObserver {
    private Playing playing;
    UrmButton menu;
    NextButton next;

    public LevelCompletedScreen(Playing playing){
        this.playing=playing;
        initButtons();
    }

    private void initButtons() {
        int X= Game.GetWndHeight()/2;

        menu=new UrmButton(X+40,300, Assets.menu);
        next=new NextButton(X+170,300, Assets.next);
        next.addObserver(this);
    }
    public void update()
    {
        menu.update();
        next.update();
    }
    private boolean isIn(PauseButton b,MouseEvent e)
    {
        return b.getBounds().contains(e.getX(),e.getY());
    }
    public void draw(Graphics g){
        g.setColor(new Color(0, 0, 0, 150));
        g.fillRect(0, 0,GetWndWidth() ,GetWndHeight() );

        Font f=new Font("font",Font.BOLD,50);
        g.setFont(f);
        g.setColor(Color.green);
        g.drawString("Level Completed!!", GetWndWidth() / 2-150, 200);

        menu.draw(g);
        next.draw(g);
    }
    public void mouseMoved(MouseEvent e){
        next.setMouseOver(false);
        menu.setMouseOver(false);

        if(isIn(menu,e))
            menu.setMouseOver(true);
        else if(isIn(next,e))
            next.setMouseOver(true);
    }
    public void mouseReleased(MouseEvent e){
        if(isIn(menu,e)) {
            if (menu.isMousePressed())
            {
                playing.resetAll();
                playing.setLvlIndex(0);
                playing.addPoints();
                Gamestate.state=Gamestate.MENU;
            }
        }
        else if(isIn(next,e)) {
            if (next.isMousePressed())
                next.pressButton();
        }

        menu.resetBools();
        next.resetBools();
    }
    public void mousePressed(MouseEvent e){
        if(isIn(menu,e)) {
            menu.setMousePressed(true);
        }
        else if(isIn(next,e)) {
            next.setMousePressed(true);
        }

    }

    @Override
    public void updateNextL() {
        playing.loadNextLevel();
    }
}