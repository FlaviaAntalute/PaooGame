package PaooGame.Entity;
import PaooGame.GameStates.Playing;
import PaooGame.Tiles.Tile;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;


/*! \class EnemyManager
    \brief Clasa care gestionează inamicii din joc.
*/
public class EnemyManager {
    private EnemyFactory factory=new EnemyFactory();
    private Playing playing; /// Referință către instanța curentă a jocului.
    private ArrayList<Enemy> max=new ArrayList<>(); ///Lista de obiecte Max, care reprezintă inamicii.
    private ArrayList<Enemy> snakes=new ArrayList<>(); ///Lista de obiecte Max, care reprezintă inamicii.
    private ArrayList<Enemy> rex=new ArrayList<>(); ///Lista de obiecte Max, care reprezintă inamicii.

        /*! \fn public EnemyManager(Playing playing)
            \brief Constructorul clasei EnemyManager.
            \ playing obiectul Playing asociat jocului.
        */
    public EnemyManager(Playing playing)
    {
        this.playing=playing;
        addEnemies();
    }

        /*! \fn private void addEnemies()
            \brief  Adaugă toți inamicii la lista de inamici.
             Pentru fiecare element din matricea hărții jocului, verifică dacă acesta
             este de tip Max, și dacă da, adaugă un obiect Max în lista de inamici.
        */
    private void addEnemies() {
        for(int i=0;i<playing.level1.getMap().length;i++) {
            for (int j = 0; j < playing.level1.getMap()[0].length; ++j) {
                if (playing.level1.getMap()[i][j] == Tile.max.GetId()) {
                    max.add( factory.createEnemy("Max",j * Tile.TILE_HEIGHT, i * Tile.TILE_WIDTH, 2, "idle"));
                }
                else if (playing.level1.getMap()[i][j] == Tile.snake.GetId()) {
                    snakes.add( factory.createEnemy("Snake",j * Tile.TILE_HEIGHT, i * Tile.TILE_WIDTH, 1, "idle"));
                }
                else if (playing.level1.getMap()[i][j] == Tile.rex.GetId()) {
                    rex.add( factory.createEnemy("Rex",j * Tile.TILE_HEIGHT, i * Tile.TILE_WIDTH, 2, "right"));
                }
            }
        }
    }

        /*! \fn public void draw(Graphics g, int xLvlOffset)
            \brief Desenează toți inamicii care sunt încă în viață.
            \g obiectul Graphics pe care se face desenarea.
            \ xLvlOffset offset-ul orizontal al nivelului.
         */
    public void draw(Graphics g, int xLvlOffset) {
        drawEach(g,xLvlOffset,max);
        drawEach(g,xLvlOffset,snakes);
        drawEach(g,xLvlOffset,rex);

    }
    public static void drawEach(Graphics g, int xLvlOffset,ArrayList<Enemy> ar)
    {
        for(Enemy e: ar)
            if(e.isAlive)
                e.drawIndividual(g,xLvlOffset);
    }

        /*! \fn public void update(Player player)
            \brief Actualizează toți inamicii care sunt încă în viață.
            \param player obiectul Player asociat jocului.
         */
    public void update(Player player)
    {
        for(Enemy m: max) {
            if(m.isAlive)
                m.update(playing.level1.getMap(), player);
        }
        for(Enemy s: snakes)
            if(s.isAlive)
               s.update(playing.level1.getMap(),player);
        for(Enemy r: rex)
            if(r.isAlive)
                r.update(playing.level1.getMap(),player);
    }

        /*! \fn public void CheckHit(Rectangle2D.Float attackArea,Player player)
            \brief Verifică dacă o zonă de atac a jucătorului se intersectează cu zona solidă a vreunui inamic.
            \param attackArea zona de atac a jucătorului.
            \param player obiectul Player asociat jocului.
         */
    public void CheckHit(Rectangle2D.Float attackArea,Player player)
    {
        for (Enemy m : max)
            if(m.isAlive) {
                if (attackArea.intersects(m.getSolidArea())) {
                    changeEnemyLife(m);
                }
            }
        for (Enemy s : snakes)
            if(s.isAlive) {
                if (attackArea.intersects(s.getSolidArea())) {
                    changeEnemyLife(s);
                }
            }
        for (Enemy r : rex)
            if(r.isAlive) {
                if (attackArea.intersects(r.getSolidArea()) && player.getPoints().getBone()) {
                    changeEnemyLife(r);
                }
            }

    }
        /*! \fn public void changeEnemyLife(Max m)
            \brief Scade viața inamicului cu 1.
            Dacă viața ajunge la 0, schimbă direcția de mișcare a inamicului la "dead"
            și îl marchează ca fiind mort (isAlive = false).
            În caz contrar, schimbă direcția de mișcare a inamicului la "hurt".
            \param m obiectul Max asupra căruia se aplică schimbarea.
         */
    public void changeEnemyLife(Enemy e)
    {
        e.lives--;
        if(e.lives==0) {
            if(e.enemyType==2) {
                e.direction = "hurt";
                e.isHurt=true;
            }
            else {
                e.direction = "dead";
                e.isAlive = false;
            }
        }
        else
            e.direction="hurt";
    }

        /*! \fn public void resetAll()
            \brief Resetează toți inamicii la starea lor inițială.
         */
    public void resetAll() {
        for(Enemy m: max)
            m.resetEnemy();
        for(Enemy s: snakes)
            s.resetEnemy();
        for(Enemy r: rex)
            r.resetEnemy();
    }

    ArrayList<Enemy> getRex()
    {
        return rex;
    }
        /*! \fn public boolean allEnemyAreDead()
            \brief Verifică dacă toți inamicii sunt morți.
            \return True dacă toți inamicii sunt morți, False în caz contrar.
         */
    public boolean allEnemyAreDead() {
        boolean ok=true;
        for(Enemy m : max)
            if (m.getIsAlive())
                ok=false;
        for(Enemy s : snakes)
            if (s.getIsAlive())
                ok=false;
        for(Enemy r : rex)
            if(!r.isHurt)
                ok=false;
        if(ok)
            return true;
        else
            return false;
    }
}
