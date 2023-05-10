package PaooGame.GameStates;

import PaooGame.Game;
import PaooGame.Graphics.Assets;
import PaooGame.Graphics.Background;
import PaooGame.Graphics.ImageLoader;
import PaooGame.Inteface.MenuButton;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import static java.awt.Font.BOLD;

public class Menu extends State implements StateMethods {

    private MenuButton[] buttons=new MenuButton[3];
    public Menu(Game game) {

        super(game);
        loadButtons();
    }

    private void loadButtons() {
        buttons[0]=new MenuButton(Game.GetWndWidth()/2,300,Assets.start,Gamestate.PLAYING);
        buttons[1]=new MenuButton(Game.GetWndWidth()/2,390,Assets.options,Gamestate.OPTIONS);
        buttons[2]=new MenuButton(Game.GetWndWidth()/2,480,Assets.quit,Gamestate.QUIT);
    }

    @Override
    public void update() {
        for(MenuButton mb: buttons)
            mb.update();
    }
    @Override
    public void draw(Graphics g) {
        Font f1=new Font("font1", BOLD,100);

        g.setColor(Color.green);
        g.setFont(f1);
//        g.drawString("Press ENTER to start",game.getWnd().GetWndWidth()/2-90, 215);
//        g.drawString("To win you have to collect all the food and defeat all the enemies",game.getWnd().GetWndWidth()/2-250, 245);
//
        Background.drawBgT(g,0);
        g.drawString("Catventure",game.getWnd().GetWndWidth()/2-270, 100);

        g.drawImage(Assets.gui[3],Game.GetWndWidth()/2-250,125,500,500,null);
        g.drawImage(Assets.gui[2],Game.GetWndWidth()/2-80,200,160,80,null);
        for(MenuButton mb: buttons)
            mb.draw(g);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        for(MenuButton mb: buttons)
        {
            if(isIn(e,mb))
            {
                mb.setMousePressed(true);
                break;
            }
        }

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        for(MenuButton mb: buttons)
        {
            if(isIn(e,mb))
            {
                if(mb.isMousePressed())
                    mb.changeGameState();
                break;
            }
        }
        resetButtons();
    }

    private void resetButtons() {
        for(MenuButton mb: buttons)
            mb.resetBools();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        for(MenuButton mb: buttons)
            mb.setMouseOver(false);
        for(MenuButton mb: buttons)
            if(isIn(e,mb)) {
                mb.setMouseOver(true);
                break;
            }
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
