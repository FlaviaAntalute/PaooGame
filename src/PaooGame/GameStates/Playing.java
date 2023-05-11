package PaooGame.GameStates;

import PaooGame.Entity.EnemyManager;
import PaooGame.Entity.Mouse;
import PaooGame.Entity.Player;
import PaooGame.Exceptions.IndexOutOfRangeException;
import PaooGame.Game;
import PaooGame.Graphics.Assets;
import PaooGame.Graphics.Background;
import PaooGame.Inputs.KeyHandler;
import PaooGame.Inteface.GameOverScreen;
import PaooGame.Inteface.GameWonScreen;
import PaooGame.Inteface.LevelCompletedScreen;
import PaooGame.Inteface.PauseScreen;
import PaooGame.Levels.Level;
import PaooGame.Tiles.Tile;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;


import static PaooGame.Exceptions.IndexOutOfRangeException.handleException;
import static PaooGame.Levels.Lives.drawLives;
import static PaooGame.Useful.Constants.PlayerConstants.*;

public class Playing extends State implements StateMethods{
    private static Player Martha; /// jucătorul controlat de utilizator
    public Level [] levels=new Level[3];    // lista cu nivele
    int lvlIndex=0;
    private Mouse mouse,mouse1;// obiecte de tipul Mouse
    private EnemyManager enemyManager; // managerul inamicilor
    private int xLvlOffset=0;/// offset-ul pe axa X al nivelului, pentru a-l muta la stânga sau la dreapta
    private int leftBorder ;// marginea stângă a ferestrei, folosită pentru a detecta când jucătorul se apropie de ea
    private int rightBorder ;// marginea dreaptă a ferestrei, folosită pentru a detecta când jucătorul se apropie de ea
    private int lvlTilesWide;// numărul de dale din nivel pe axa X
    private int maxTilesOffset ;// numărul maxim de dale cu care se poate muta nivelul într-o parte sau alta
    private int maxLvlOffsetX;// numărul maxim de pixeli cu care se poate muta nivelul într-o parte sau alta
    public boolean gameOver=false;// indica dacă jocul s-a terminat cu eșec
    private GameOverScreen gameOverScreen;// ecranul de sfârșit de joc cu eșec
    public boolean gameWon=false; // indica dacă jocul s-a terminat cu succes
    private GameWonScreen gameWonScreen;// ecranul de sfârșit de joc cu succes
    private LevelCompletedScreen levelCompletedScreen;
    private boolean paused=false;
    private PauseScreen pauseScreen;
    private boolean leveleCompleted=false;
    /*!
    \brief Constructorul clasei Playing.
    \param game Obiectul de tip Game pentru care se creează instanța clasei.
    Acest constructor inițializează instanța clasei și apelează metoda init pentru inițializarea variabilelor membru.
     De asemenea, se calculează limitele ecranului în care jucătorul poate mișca stânga/dreapta
      și se inițializează variabilele ce țin de dimensiunea hărții.
    \return Nicio valoare nu este returnată.
    */
    public Playing(Game game) {
        super(game);
        init(game.getKeyH());
        // se calculează limitele ecranului în care jucătorul poate mișca
        leftBorder = (int) (0.3 * game.getWnd().GetWndWidth());
        rightBorder = (int) (0.7 * game.getWnd().GetWndWidth());
        lvlTilesWide = levels[lvlIndex].getMap()[0].length;
        maxTilesOffset = lvlTilesWide- Tile.NrTileWidth;
        maxLvlOffsetX = maxTilesOffset * Tile.TILE_WIDTH;

    }

    /*!

    \fn public void init(KeyHandler keyH)

    \brief Funcția initializează nivelul, jucătorul, inamicul, șoarecele și ecranele de joc.

    \param keyH obiectul de tip KeyHandler care este utilizat pentru a gestiona intrările de la tastatură.

    \return Nicio valoare nu este returnată de această funcție.
    */
    public void init(KeyHandler keyH)
    {
        initLevels();
        enemyManager=new EnemyManager(this,levels[lvlIndex]);
        Martha=new Player(STARTX,STARTY,SPEED,STARTDIR,keyH,levels[lvlIndex].getMap(), this);
        Martha.loadMap(levels[lvlIndex].getMap());
        mouse=new Mouse(50,5*32+18,1,"right");
        //mouse1 = new Mouse(500, 17*32+18, 1, "right");
        gameOverScreen=new GameOverScreen(this);
        gameWonScreen=new GameWonScreen(this);
        pauseScreen=new PauseScreen(this);
        levelCompletedScreen=new LevelCompletedScreen(this);
    }
    private void initLevels() {
        levels[0]=new Level(6,"res/Maps/map.txt");
        levels[1]=new Level(7,"res/Maps/map1.txt");
        levels[2]=new Level(6,"res/Maps/map.txt");
    }
    public static Player getMartha()
    {
        return Martha;
    }

    /*! \fn  public void update()

    \brief Actualizează starea jocului.
    Această metodă este apelată de obiectul Game pentru a actualiza starea jocului.
    Dacă jocul nu este în starea de game over, atunci se actualizează poziția jucătorului Martha,
    se verifică dacă se apropie de marginea ecranului, se actualizează poziția șoarecelui mouse și se actualizează pozițiile inamicilor în funcție de poziția jucătorului Martha.
    \return Nicio valoare nu este returnată.
    */
    @Override
    public void update() {
        if(paused)
            pauseScreen.update();
        else if (leveleCompleted)
            levelCompletedScreen.update();
        else if (!gameOver){
            Martha.update(levels[lvlIndex], mouse, enemyManager);
            IsCloseToBorder();
            mouse.update();
            enemyManager.update(Martha,levels[lvlIndex]);
        }
    }



    /*! \fn public void draw(Graphics g)
    \brief Funcția de desenare a jocului.
    Funcția desenează fundalul, harta, obiectele de pe hartă (șobolani, jucător, șoricel),
    numărul de puncte, numărul de vieți și ecranele de final (dacă este cazul).
    De asemenea, calculează și desenează offset-ul nivelului, pentru a putea realiza
    efectul de deplasare a jocului pe axa X.
    \param g Un obiect Graphics, pe care se realizează desenarea.
    \return Nicio valoare nu este returnată de această funcție.
    */
    @Override
    public void draw(Graphics g) {
        try {
            Background.drawBgT(g, xLvlOffset);
            levels[lvlIndex].GetMap().drawMap(g, xLvlOffset);
            mouse.draw(g, xLvlOffset);
            Martha.draw(g, xLvlOffset);
            Player.points.PrintPoints(g, levels[lvlIndex]);
            this.PrintBone(g);
            drawLives(g, xLvlOffset, Martha);
            enemyManager.draw(g, xLvlOffset);

        if(gameOver)
            gameOverScreen.draw(g);
        else if(gameWon)
            gameWonScreen.draw(g);
        else if(paused)
            pauseScreen.draw(g);
        else if(leveleCompleted)
            levelCompletedScreen.draw(g);

        }catch (IndexOutOfRangeException e)
        {
            handleException(e);
            game.StopGame();

        }

    }
    public void PrintBone(Graphics g)
    {
        g.drawImage(Assets.bone,270,15,45,45,null);
        if(Martha.getHasBone())
            g.drawImage(Assets.gui[1],240,15,35,35,null);
        else
            g.drawImage(Assets.gui[0],240,15,35,35,null);

//        Font f1=new Font("font1", BOLD,18);
//        int is=0;
//        if(Martha.getHasBone())
//            is=1;
//        char []msg=("Bone:  "+is).toCharArray();
//        g.setColor(Color.RED);
//        g.setFont(f1);
//        g.drawChars(msg,0, msg.length, 240,35);
    }
    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(!gameOver) {
            if (paused)
                pauseScreen.mousePressed(e);
            else if (leveleCompleted)
                levelCompletedScreen.mousePressed(e);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (!gameOver) {
            if (paused)
                pauseScreen.mouseReleased(e);
            else if (leveleCompleted)
                levelCompletedScreen.mouseReleased(e);
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if (!gameOver) {
            if (paused)
                pauseScreen.mouseMoved(e);
            else if (leveleCompleted)
                levelCompletedScreen.mouseMoved(e);
        }
    }
    /*!

    \fn public void keyPressed(KeyEvent e)
    \brief Funcția este apelată atunci când utilizatorul apasă o tastă.
    Funcția primește un obiect KeyEvent care conține informații despre tastă și
    verifică dacă jocul s-a terminat, dacă jucătorul a câștigat sau dacă jocul este în desfășurare.
    În funcție de acest lucru, funcția va seta variabilele corespunzătoare ale jucătorului pentru a indica că o anumită tastă a fost apăsată.
    Dacă utilizatorul apasă tasta "Escape", funcția va schimba starea jocului în starea meniu.
    \param e Obiectul KeyEvent care conține informații despre tastă.
    \return Nicio valoare nu este returnată de această funcție.

    */
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
                paused=!paused;
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
        leveleCompleted=false;
        Martha.resetAll();
        enemyManager.resetAll();
        levels[lvlIndex].resetAll(levels[lvlIndex].getPath(),levels[lvlIndex].getPoints());
        mouse.resetAll();
        if(gameOver)
            lvlIndex=0;

    }

    public void setGameWon(boolean value) {
        gameWon=true;
    }

    public EnemyManager getEnemyManager() {
        return enemyManager;
    }
    public void unpauseGame(){
        paused=false;
    }

    public void setLevelCompleted(boolean b) {
        leveleCompleted=true;
    }
    public void loadNextLevel()
    {
        lvlIndex++;
        resetAll();
        enemyManager=new EnemyManager(this,levels[lvlIndex]);
        if(lvlIndex==3)
            gameWon=true;

    }
    public int getLvlIndex() {
        return lvlIndex;
    }
    public Level getLevel() {
        return levels[lvlIndex];
    }
}
