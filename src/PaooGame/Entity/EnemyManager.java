package PaooGame.Entity;
import PaooGame.GameStates.Playing;
import PaooGame.Levels.Level;
import PaooGame.Tiles.Tile;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
    public EnemyManager(Playing playing,Level level)
    {
        this.playing=playing;
        addEnemies(level);
    }

    /*! \fn private void addEnemies()
        \brief  Adaugă toți inamicii la lista de inamici.
         Pentru fiecare element din matricea hărții jocului, verifică dacă acesta
         este de tip Max, și dacă da, adaugă un obiect Max în lista de inamici.
    */
    public void addEnemies(Level level) {
        for(int i=0;i<level.getMap().length;i++) {
            for (int j = 0; j < level.getMap()[0].length; ++j) {
                if (level.getMap()[i][j] == Tile.max.GetId()) {
                    max.add( factory.createEnemy("Max",j * Tile.TILE_HEIGHT, i * Tile.TILE_WIDTH, 2, "idle",0));
                }
                else if (level.getMap()[i][j] == Tile.snake.GetId()) {
                    if(playing.getLvlIndex()==2)
                        snakes.add( factory.createEnemy("Snake",j * Tile.TILE_HEIGHT, i * Tile.TILE_WIDTH, 1, "idle",2));
                    else
                        snakes.add( factory.createEnemy("Snake",j * Tile.TILE_HEIGHT, i * Tile.TILE_WIDTH, 1, "idle",1));

                }
                else if (level.getMap()[i][j] == Tile.rex.GetId()) {
                    rex.add( factory.createEnemy("Rex",j * Tile.TILE_HEIGHT, i * Tile.TILE_WIDTH, 2, "right",0));
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
    public void update(Player player,Level level)
    {
        for(Enemy m: max) {
            if(m.isAlive)
                m.update(level.getMap(), player);
        }
        for(Enemy s: snakes)
            if(s.isAlive)
                s.update(level.getMap(),player);
        for(Enemy r: rex)
            if(r.isAlive)
                r.update(level.getMap(),player);
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
                if (attackArea.intersects(r.getSolidArea()) && player.getHasBone()) {
                    changeEnemyLife(r);
                    player.loseBone();
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

    public void saveEnemy(Connection c, Statement stmt) throws SQLException {
        String str;
        int i=0;
        for(Enemy m: max)
        {
            if(m.isAlive)
                str = "UPDATE max SET c" + (i+1)+"="+1+" WHERE rowid =" + 1;
            else
                str = "UPDATE max SET c" + (i+1)+"="+0+" WHERE rowid =" +  1;
            stmt.executeUpdate(str);
            i++;
        }
        i=0;
        for(Enemy s: snakes)
        {
            if(s.isAlive)
                str = "UPDATE snake SET c" +(i+1)+"="+ 1+" WHERE rowid =" +1;
            else
                str = "UPDATE snake SET  c" +(i+1)+"="+ 0+" WHERE rowid =" + 1;
            stmt.executeUpdate(str);
            i++;
        }
        i=0;
        if(!rex.isEmpty()) {
            for (Enemy r : rex) {
                if (r.isAlive)
                    str = "UPDATE rex SET c" + (i + 1) + "=" + 1 + " WHERE rowid =" + 1;
                else
                    str = "UPDATE rex SET  c" + (i + 1) + "=" + 0 + " WHERE rowid =" + 1;
                stmt.executeUpdate(str);
                i++;
            }
        }

    }

    public void loadEnemy(Connection c, Statement stmt) throws SQLException {
        String str;
        int i=0;
        str="SELECT * FROM max";
        ResultSet rs = stmt.executeQuery(str);

        while(rs.next())
        {
            for(i=0;i<max.size();++i)
            {
                if (rs.getInt(i+1) == 1)
                    max.get(i).isAlive = true;
                else
                    max.get(i).isAlive = false;
            }
        }

        str="SELECT * FROM snake";
        rs = stmt.executeQuery(str);
        while(rs.next())
        {
            for(i=0;i<snakes.size();++i)
            {
                if (rs.getInt(i+1) == 1)
                    snakes.get(i).isAlive = true;
                else
                    snakes.get(i).isAlive = false;
            }
        }

        if(!rex.isEmpty()) {
            str = "SELECT * FROM rex";
            rs = stmt.executeQuery(str);
            while (rs.next()) {
                for(i=0;i<rex.size();++i)
                {
                    if (rs.getInt(i+1) == 1)
                        rex.get(i).isAlive = true;
                    else
                        rex.get(i).isAlive = false;
                }
            }
        }

    }
}
