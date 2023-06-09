package PaooGame.Inteface;

import PaooGame.Game;
import PaooGame.GameStates.Gamestate;
import PaooGame.GameStates.Playing;
import PaooGame.Graphics.Assets;
import PaooGame.GameStates.Menu;

import java.awt.*;
import java.awt.event.MouseEvent;


import static PaooGame.Game.GetWndHeight;
import static PaooGame.Game.GetWndWidth;


public class PauseScreen {
    private UrmButton menuB,replayB,continueB,saveB;
    Playing playing;
    public PauseScreen(Playing playing){
        this.playing=playing;
        createUrmButtons();

    }

    private void createUrmButtons() {
        int X= Game.GetWndHeight()/2+70;

        menuB=new UrmButton(X,280, Assets.menu);
        replayB=new UrmButton(X,350, Assets.replay);
        continueB=new UrmButton(X,420, Assets.continuee);
        saveB=new UrmButton(X,490,Assets.save);
    }

    public void update(){
        menuB.update();
        replayB.update();
        continueB.update();
        saveB.update();
    }
    public void draw(Graphics g) {
        Font f=new Font("font",Font.BOLD,50);
        g.setFont(f);
        g.setColor(new Color(0, 0, 0, 200));
        g.fillRect(0, 0,GetWndWidth() ,GetWndHeight() );
        g.setColor(Color.green);
        g.drawString("PAUSE", GetWndWidth() / 2-80, 200);

        menuB.draw(g);
        replayB.draw(g);
        continueB.draw(g);
        saveB.draw(g);
    }

    public void mousePressed(MouseEvent e) {
        if(isIn(e,menuB)) {
            menuB.setMousePressed(true);
        }
        else if(isIn(e,replayB))
            replayB.setMousePressed(true);
        else if(isIn(e,continueB))
            continueB.setMousePressed(true);
        else if(isIn(e,saveB))
            saveB.setMousePressed(true);
    }
    public void mouseReleased(MouseEvent e) {
        if(isIn(e,menuB)){
            if(menuB.isMousePressed()) {
                playing.addPoints();
                playing.savePoints();
                Menu.loadScore();
                Gamestate.state = Gamestate.MENU;
                playing.setLvlIndex(0);
                playing.unpauseGame();
                playing.resetAll();
                playing.resetTotalPoints();
            }
        }else if(isIn(e,continueB))
        {
            if(continueB.isMousePressed())
                playing.unpauseGame();

        } else if (isIn(e,replayB)) {
            if(replayB.isMousePressed())
            {
                playing.resetAll();
                playing.unpauseGame();
            }
        }else if (isIn(e,saveB)) {
            if(saveB.isMousePressed())
            {
                playing.addPoints();
                playing.savePoints();
                Menu.loadScore();
                playing.saveGame();
                playing.unpauseGame();
                Gamestate.state=Gamestate.MENU;
                playing.setLvlIndex(0);
                playing.resetAll();
                playing.resetTotalPoints();
            }
        }

        menuB.resetBools();
        replayB.resetBools();
        continueB.resetBools();
        saveB.resetBools();
    }
    public void mouseMoved(MouseEvent e) {
        menuB.setMouseOver(false);
        replayB.setMouseOver(false);
        continueB.setMouseOver(false);
        saveB.setMouseOver(false);

        if(isIn(e,menuB))
            menuB.setMouseOver(true);
        if(isIn(e,replayB))
            replayB.setMouseOver(true);
        if(isIn(e,continueB))
            continueB.setMouseOver(true);
        if(isIn(e,saveB))
            saveB.setMouseOver(true);

    }
    private boolean isIn(MouseEvent e, PauseButton b) {
        return b.getBounds().contains(e.getX(), e.getY());
    }
}
