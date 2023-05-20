package PaooGame.GameStates;

import PaooGame.Game;
import PaooGame.Graphics.Assets;
import PaooGame.Graphics.Background;
import PaooGame.Graphics.ImageLoader;
import PaooGame.Inteface.MenuButton;
import PaooGame.Inteface.PauseButton;
import PaooGame.Inteface.UrmButton;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import static java.awt.Font.BOLD;

public class Menu extends State implements StateMethods {
    private Playing playing;
    private MenuButton[] buttons=new MenuButton[3];
    private UrmButton loadButton;
    private static int score=0;
    public Menu(Game game) {

        super(game);
        loadScore();
        loadButtons();

    }

    public static void loadScore() {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:gameState.db");
            stmt = c.createStatement();
            c.setAutoCommit(false);
            String str = "SELECT * FROM score";
            ResultSet rs = stmt.executeQuery(str);

            score=rs.getInt(1);

            c.commit();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }

    }

    private void loadButtons() {
        buttons[0]=new MenuButton(Game.GetWndWidth()/2,300,Assets.start,Gamestate.PLAYING);
        buttons[1]=new MenuButton(Game.GetWndWidth()/2,370,Assets.options,Gamestate.OPTIONS);
        buttons[2]=new MenuButton(Game.GetWndWidth()/2,510,Assets.quit,Gamestate.QUIT);
        loadButton=new UrmButton(Game.GetWndWidth()/2-60,440,Assets.load);

    }

    @Override
    public void update() {
        for(MenuButton mb: buttons)
            mb.update();
        loadButton.update();
    }
    @Override
    public void draw(Graphics g) {
        Background.drawBgT(g,0);

        Font f=new Font("font2", BOLD,25);
        g.setColor(Color.ORANGE);
        g.setFont(f);
        g.drawString("Last game score: "+score,680, 30);

        Font f1=new Font("font1", BOLD,100);
        g.setColor(Color.green);
        g.setFont(f1);
        g.drawString("Catventure",game.getWnd().GetWndWidth()/2-270, 100);

        g.drawImage(Assets.gui[3],Game.GetWndWidth()/2-250,125,500,520,null);
        g.drawImage(Assets.gui[2],Game.GetWndWidth()/2-80,200,160,80,null);


        for(MenuButton mb: buttons)
            mb.draw(g);
        loadButton.draw(g);
    }
    private boolean isIn(PauseButton b,MouseEvent e)
    {
        return b.getBounds().contains(e.getX(),e.getY());
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
        if(isIn(loadButton,e))
        {
            loadButton.setMousePressed(true);

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
        if(isIn(loadButton,e))
        {
            if(loadButton.isMousePressed()) {
                playing.loadGame();
                loadButton.changeGameState();
            }
        }

        resetButtons();
    }

    private void resetButtons() {
        for(MenuButton mb: buttons)
            mb.resetBools();
        loadButton.resetBools();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        for(MenuButton mb: buttons)
            mb.setMouseOver(false);
        loadButton.setMouseOver(false);
        for(MenuButton mb: buttons)
            if(isIn(e,mb)) {
                mb.setMouseOver(true);
                break;
            }
        if(isIn(loadButton,e))
            loadButton.setMouseOver(true);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_ENTER)
            Gamestate.state=Gamestate.PLAYING;
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
    public void setPlaying(Playing playing) {
        this.playing=playing;
    }
}
