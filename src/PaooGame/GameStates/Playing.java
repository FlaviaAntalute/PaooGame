package PaooGame.GameStates;

import PaooGame.Entity.EnemyManager;
import PaooGame.Entity.MouseManager;
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
import java.sql.*;

import static PaooGame.Entity.Player.points;
import static PaooGame.Entity.Player.totalPoints;
import static PaooGame.Exceptions.IndexOutOfRangeException.handleException;
import static PaooGame.Game.GetWndWidth;
import static PaooGame.Levels.Lives.drawLives;
import static PaooGame.Useful.Constants.PlayerConstants.*;

public class Playing extends State implements StateMethods {
    private static Player Martha; /// jucătorul controlat de utilizator

    public Level[] levels = new Level[3];    // lista cu nivele
    int lvlIndex = 0;
    //private Mouse mouse, mouse1;// obiecte de tipul Mouse
    private EnemyManager enemyManager; // managerul inamicilor
    private MouseManager mouseManager;
    private int xLvlOffset = 2;/// offset-ul pe axa X al nivelului, pentru a-l muta la stânga sau la dreapta
    private int leftBorder;// marginea stângă a ferestrei, folosită pentru a detecta când jucătorul se apropie de ea
    private int rightBorder;// marginea dreaptă a ferestrei, folosită pentru a detecta când jucătorul se apropie de ea
    private int lvlTilesWide;// numărul de dale din nivel pe axa X
    private int maxTilesOffset;// numărul maxim de dale cu care se poate muta nivelul într-o parte sau alta
    private int maxLvlOffsetX;// numărul maxim de pixeli cu care se poate muta nivelul într-o parte sau alta
    public boolean gameOver = false;// indica dacă jocul s-a terminat cu eșec
    private GameOverScreen gameOverScreen;// ecranul de sfârșit de joc cu eșec
    public boolean gameWon = false; // indica dacă jocul s-a terminat cu succes
    private GameWonScreen gameWonScreen;// ecranul de sfârșit de joc cu succes
    private LevelCompletedScreen levelCompletedScreen;
    private boolean paused = false;
    private PauseScreen pauseScreen;
    private boolean leveleCompleted = false;

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
        maxTilesOffset = lvlTilesWide - Tile.NrTileWidth;
        maxLvlOffsetX = maxTilesOffset * Tile.TILE_WIDTH;

    }

    /*!

    \fn public void init(KeyHandler keyH)

    \brief Funcția initializează nivelul, jucătorul, inamicul, șoarecele și ecranele de joc.

    \param keyH obiectul de tip KeyHandler care este utilizat pentru a gestiona intrările de la tastatură.

    \return Nicio valoare nu este returnată de această funcție.
    */
    public void init(KeyHandler keyH) {
        initLevels();
        enemyManager = new EnemyManager(this, levels[lvlIndex]);
        mouseManager=new MouseManager(levels[lvlIndex]);
        Martha = new Player(STARTX, STARTY, SPEED, STARTDIR, keyH, levels[lvlIndex].getMap(), this);
        Martha.loadMap(levels[lvlIndex].getMap());
        gameOverScreen = new GameOverScreen(this);
        gameWonScreen = new GameWonScreen(this);
        pauseScreen = new PauseScreen(this);
        levelCompletedScreen = new LevelCompletedScreen(this);
    }

    private void initLevels() {
        levels[0] = new Level(9, "res/Maps/map.txt");
        levels[1] = new Level(10, "res/Maps/map1.txt");
        levels[2] = new Level(16, "res/Maps/map2.txt");
    }

    public static Player getMartha() {
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

        if (paused)
            pauseScreen.update();
        else if (leveleCompleted)
            levelCompletedScreen.update();
        else if (!gameOver) {
            Martha.update(levels[lvlIndex], mouseManager.getMouse(), enemyManager);
            IsCloseToBorder();
            enemyManager.update(Martha, levels[lvlIndex]);
            mouseManager.update();
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
            if (lvlIndex == 0 || lvlIndex == 2)
                Background.drawBgT(g, xLvlOffset);
            else if (lvlIndex == 1)
                Background.drawBgW(g, xLvlOffset);
            levels[lvlIndex].GetMap().drawMap(g, xLvlOffset);
            Martha.draw(g, xLvlOffset);
            points.PrintPoints(g, levels[lvlIndex]);
            this.PrintBone(g);
            drawLives(g, xLvlOffset, Martha);
            enemyManager.draw(g, xLvlOffset);
            mouseManager.draw(g,xLvlOffset);
            if (points.getPoints() == levels[lvlIndex].getPoints() && enemyManager.allEnemyAreDead()) {
                Font f = new Font("font", Font.BOLD, 18);
                g.setFont(f);
                g.setColor(Color.ORANGE);
                g.drawString("Your kittens are waiting for you!!", 500, 40);
            }

            if (gameOver)
                gameOverScreen.draw(g);
            else if (gameWon)
                gameWonScreen.draw(g);
            else if (paused)
                pauseScreen.draw(g);
            else if (leveleCompleted)
                levelCompletedScreen.draw(g);

        } catch (IndexOutOfRangeException e) {
            handleException(e);
            game.StopGame();

        }

    }

    public void PrintBone(Graphics g) {
        g.drawImage(Assets.bone, 270, 15, 45, 45, null);
        if (Martha.getHasBone())
            g.drawImage(Assets.gui[1], 240, 15, 35, 35, null);
        else
            g.drawImage(Assets.gui[0], 240, 15, 35, 35, null);

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (!gameOver) {
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
        if (gameOver) {
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
                paused = !paused;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (!gameOver) {
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
        int playerX = (int) getMartha().getSolidArea().x;
        int dif = playerX - xLvlOffset;

        if (dif > rightBorder)
            xLvlOffset += dif - rightBorder;
        else if (dif < leftBorder)
            xLvlOffset += dif - leftBorder;
        if (xLvlOffset > maxLvlOffsetX)
            xLvlOffset = maxLvlOffsetX;

        else if (xLvlOffset < 0)
            xLvlOffset = 0;
    }

    public void checkEnemyHit(Rectangle2D.Float attackArea) {
        enemyManager.CheckHit(attackArea, Martha);
    }

    public void SetGameOver(boolean value) {
        this.gameOver = value;
    }

    public void resetAll() {
        if (gameOver)
            lvlIndex = 0;
        gameOver = false;
        gameWon = false;
        leveleCompleted = false;
        Martha.resetAll();
        Martha.loadMap(levels[lvlIndex].getMap());
        enemyManager = new EnemyManager(this, levels[lvlIndex]);
        mouseManager=new MouseManager(levels[lvlIndex]);
        levels[lvlIndex].resetAll(levels[lvlIndex].getPath(), levels[lvlIndex].getPoints());

    }

    public void setGameWon(boolean value) {
        gameWon = true;
    }

    public EnemyManager getEnemyManager() {
        return enemyManager;
    }

    public void unpauseGame() {
        paused = false;
    }

    public void setLevelCompleted(boolean b) {
        leveleCompleted = true;
    }

    public void loadNextLevel() {
        lvlIndex++;
        resetAll();
        if (gameOver)
            lvlIndex = 0;

    }

    public int getLvlIndex() {
        return lvlIndex;
    }

    public Level getLevel() {
        return levels[lvlIndex];
    }

    public void setLvlIndex(int i) {
        lvlIndex = i;
    }

    public void saveGame() {

        Connection c = null;
        Statement stmt = null;

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:gameState.db");
            stmt = c.createStatement();
            c.setAutoCommit(false);

            for (int i = 0; i < levels[lvlIndex].getMap().length; i++) {
                for (int j = 0; j < levels[lvlIndex].getMap()[i].length; j++) {
                    String str = "UPDATE map SET col" + (j + 1) + " = " + levels[lvlIndex].getMap()[i][j] + " WHERE rowid = " + (i + 1);
                    stmt.executeUpdate(str);
                }
            }

            String str = "UPDATE player SET x= " + Martha.getSolidArea().x;
            stmt.executeUpdate(str);
            str = "UPDATE player SET y= " + Martha.getSolidArea().y;
            stmt.executeUpdate(str);
            str = "UPDATE player SET xAttack= " + Martha.getAttackArea().x;
            stmt.executeUpdate(str);
            str = "UPDATE player SET yAttack= " + Martha.getAttackArea().y;
            stmt.executeUpdate(str);
            if (Martha.getHasBone())
                str = "UPDATE player SET hasBone= " + 1;
            else
                str = "UPDATE player SET hasBone= " + 0;
            stmt.executeUpdate(str);

            str = "UPDATE player SET lives= " + Martha.getLives();
            stmt.executeUpdate(str);

            if (Martha.getInAir())
                str = "UPDATE player SET inAir= " + 1;
            else
                str = "UPDATE player SET inAir= " + 0;
            stmt.executeUpdate(str);

            str = "UPDATE player SET direction= '" + Martha.getDirection() + "'";
            stmt.executeUpdate(str);


            str = "UPDATE player SET level=" + lvlIndex ;
            stmt.executeUpdate(str);

            enemyManager.saveEnemy(c,stmt);

            str = "UPDATE player SET points=" + Martha.getPoints().getPoints(); ;
            stmt.executeUpdate(str);

            c.commit();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }

    }

    public void loadGame() {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:gameState.db");
            stmt = c.createStatement();
            String str = "SELECT * FROM map";
            ResultSet rs = stmt.executeQuery(str);


            int j=0;
            int[][] matrix=new int[21][47];
            while(rs.next()){
                for(int i=0;i<47;++i)
                    matrix[j][i]=rs.getInt(i+1);
                j++;
            }


            str = "SELECT * FROM player";
            rs = stmt.executeQuery(str);

            this.lvlIndex=rs.getInt(9);
            levels[lvlIndex].setMap(matrix);

            resetAll();
            levels[lvlIndex].setMap(matrix);

            Martha.setSolidArea(rs.getFloat(1),rs.getFloat(2));
            Martha.setAttackArea(rs.getFloat(3),rs.getFloat(4));
            if(rs.getInt(5)==0)
                Martha.setHasBone(false);
            else
                Martha.setHasBone(true);

            Martha.setLives(rs.getInt(6));

            if(rs.getInt(7)==0)
                Martha.setInAir(false);
            else
                Martha.setInAir(true);

            Martha.setDirection(rs.getString(8));

            Martha.getPoints().setPoints(rs.getInt(10));
            enemyManager.loadEnemy(c,stmt);


            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    public void resetTotalPoints() {
        totalPoints=0;
    }

    public void addPoints() {

        totalPoints+=Martha.getPoints().getPoints();
    }

    public void savePoints() {

        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:gameState.db");
            stmt = c.createStatement();
            c.setAutoCommit(false);

            String str = "UPDATE score SET score=" +totalPoints;
            stmt.executeUpdate(str);

            c.commit();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

}
