package PaooGame.GameStates;

import PaooGame.Entity.Mouse;
import PaooGame.Entity.Player;
import PaooGame.Game;
import PaooGame.Graphics.Background;
import PaooGame.Graphics.Map;
import PaooGame.Inputs.KeyHandler;
import PaooGame.Levels.Level;
import PaooGame.Tiles.Tile;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import static PaooGame.Levels.Lives.drawLives;
import static PaooGame.Levels.Points.PrintBone;
import static PaooGame.Levels.Points.PrintPoints;

public class Playing extends State implements StateMethods{
    private static Player Martha;
    public Map map1;
    public static Level level1;
    private Mouse mouse;
    private int xLvlOffset=0;
    private int leftBorder ;
    private int rightBorder ;
    private int lvlTilesWide;
    private int maxTilesOffset ;
    private int maxLvlOffsetX;


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
        map1=new Map("res/map.txt");
        level1=new Level(map1,9);
        Martha=new Player(keyH,level1.getMap());
        Martha.loadMap(level1.getMap());
        mouse=new Mouse(10,366,1);
    }
    public static Player getMartha()
    {
        return Martha;
    }
    public static Level getLevel1()
    {
        return level1;
    }

    @Override
    public void update() {
        Martha.update(level1,mouse);
        IsCloseToBorder();
        mouse.update();
    }

    @Override
    public void draw(Graphics g) {
        Background.drawBgT(g,xLvlOffset);         map1.drawMap(g,xLvlOffset);
        mouse.draw(g,xLvlOffset);
        Martha.draw(g,xLvlOffset);
        PrintPoints(g,level1);
        PrintBone(g);
        drawLives(g,xLvlOffset,Martha);
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
        int code=e.getKeyCode();
        if(code==KeyEvent.VK_UP)
            Martha.upPressed=true;
        if(code==KeyEvent.VK_DOWN)
            Martha.collectPressed=true;
        if(code==KeyEvent.VK_RIGHT)
            Martha.rightPressed=true;
        if(code==KeyEvent.VK_LEFT)
            Martha.leftPressed=true;
        if(code==KeyEvent.VK_SPACE)
            Martha.attackPressed=true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code=e.getKeyCode();
        if(code==KeyEvent.VK_RIGHT)
            Martha.rightPressed=false;
        if(code==KeyEvent.VK_LEFT)
            Martha.leftPressed=false;
        if(code==KeyEvent.VK_UP)
            Martha.upPressed=false;
        if(code==KeyEvent.VK_DOWN)
            Martha.collectPressed=false;
        if(code==KeyEvent.VK_SPACE)
            Martha.attackPressed=false;

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

}
