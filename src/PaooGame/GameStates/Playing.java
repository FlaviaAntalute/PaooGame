package PaooGame.GameStates;

import PaooGame.Entity.EnemyManager;
import PaooGame.Entity.Mouse;
import PaooGame.Entity.Player;
import PaooGame.Game;
import PaooGame.Graphics.Background;
import PaooGame.Graphics.Map;
import PaooGame.Inputs.KeyHandler;
import PaooGame.Inteface.GameOverScreen;
import PaooGame.Inteface.GameWonScreen;
import PaooGame.Levels.Level;
import PaooGame.Tiles.Tile;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;

import static PaooGame.Levels.Lives.drawLives;
import static PaooGame.Useful.Constants.PlayerConstants.*;

public class Playing extends State implements StateMethods{
    private static Player Martha;
    public  Level level1;
    private Mouse mouse,mouse1;
    private EnemyManager enemyManager;
    private int xLvlOffset=0;
    private int leftBorder ;
    private int rightBorder ;
    private int lvlTilesWide;
    private int maxTilesOffset ;
    private int maxLvlOffsetX;
    public boolean gameOver=false;
    private GameOverScreen gameOverScreen;
    public boolean gameWon=false;
    private GameWonScreen gameWonScreen;

    public Playing(Game game) {
        super(game);
        init(game.getKeyH());
        leftBorder = (int) (0.2 * game.getWnd().GetWndWidth());
        rightBorder = (int) (0.8 * game.getWnd().GetWndWidth());
        lvlTilesWide = getLevel1().getMap()[0].length;
        maxTilesOffset = lvlTilesWide- Tile.NrTileWidth;
        maxLvlOffsetX = maxTilesOffset * Tile.TILE_WIDTH;

    }

    public void init(KeyHandler keyH)
    {
        level1=new Level(9);
        enemyManager=new EnemyManager(this);
        Martha=new Player(STARTX,STARTY,SPEED,STARTDIR,keyH,level1.getMap(), this);
        Martha.loadMap(level1.getMap());
        mouse=new Mouse(50,12*32+18,1,"right");
        //mouse1 = new Mouse(500, 17*32+18, 1, "right");
        gameOverScreen=new GameOverScreen(this);
        gameWonScreen=new GameWonScreen(this);
    }
    public static Player getMartha()
    {
        return Martha;
    }
    public  Level getLevel1()
    {
        return this.level1;
    }

    @Override
    public void update() {
        if(!gameOver) {
            Martha.update(level1, mouse,enemyManager);
            IsCloseToBorder();
            mouse.update();
            enemyManager.update(Martha);
        }
    }

    @Override
    public void draw(Graphics g) {
        Background.drawBgT(g,xLvlOffset);
        level1.GetMap().drawMap(g,xLvlOffset);
        mouse.draw(g,xLvlOffset);
        Martha.draw(g,xLvlOffset);
        Player.points.PrintPoints(g,level1);
        Player.points.PrintBone(g);
        drawLives(g,xLvlOffset,Martha);
        enemyManager.draw(g,xLvlOffset);

        if(gameOver)
        {
            gameOverScreen.draw(g);
        }
        if(gameWon)
        {
            gameWonScreen.draw(g);
        }
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
        if(gameOver)
        {
            gameOverScreen.keyPressed(e);
        } else if (gameWon) {
            gameWonScreen.keyPressed(e);
        } else {
            int code = e.getKeyCode();
            if (code == KeyEvent.VK_UP)
                Martha.upPressed = true;
            if (code == KeyEvent.VK_DOWN)
                Martha.collectPressed = true;
            if (code == KeyEvent.VK_RIGHT)
                Martha.rightPressed = true;
            if (code == KeyEvent.VK_LEFT)
                Martha.leftPressed = true;
            if (code == KeyEvent.VK_SPACE)
                Martha.attackPressed = true;
            if (code == KeyEvent.VK_ESCAPE)
                Gamestate.state = Gamestate.MENU;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(!gameOver) {
            int code = e.getKeyCode();
            if (code == KeyEvent.VK_RIGHT)
                Martha.rightPressed = false;
            if (code == KeyEvent.VK_LEFT)
                Martha.leftPressed = false;
            if (code == KeyEvent.VK_UP)
                Martha.upPressed = false;
            if (code == KeyEvent.VK_DOWN)
                Martha.collectPressed = false;
            if (code == KeyEvent.VK_SPACE)
                Martha.attackPressed = false;
        }

    }
    private void IsCloseToBorder() {
        int playerX=(int)getMartha().getSolidArea().x;
        int dif=playerX-xLvlOffset;

        if(dif>rightBorder)
            xLvlOffset+=dif-rightBorder;
        else if (dif<leftBorder)
            xLvlOffset+=dif-leftBorder;
        if(xLvlOffset>maxLvlOffsetX)
            xLvlOffset = maxLvlOffsetX;

        else if(xLvlOffset<0)
            xLvlOffset=0;
    }
    public void checkEnemyHit(Rectangle2D.Float attackArea)
    {
        enemyManager.CheckHit(attackArea,Martha);
    }
    public void SetGameOver(boolean value)
    {
        this.gameOver=value;
    }
    public void resetAll()
    {
        gameOver=false;
        gameWon=false;
        Martha.resetAll();
        enemyManager.resetAll();
        level1.resetAll("res/map.txt",9);
        mouse.resetAll();

    }

    public void getGameWon(boolean value) {
        gameWon=true;
    }
}
