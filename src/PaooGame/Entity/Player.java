package PaooGame.Entity;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Objects;
import PaooGame.GameStates.Playing;
import PaooGame.Graphics.Assets;
import PaooGame.Inputs.KeyHandler;
import PaooGame.Levels.*;
import static PaooGame.Entity.Collision.*;
import static PaooGame.Useful.Constants.PlayerConstants.*;

/*! \class Player
\brief Clasa ce reprezintă jucătorul din joc.
Clasa Player moștenește clasa Entity și conține toate datele și metodele necesare
pentru a descrie comportamentul jucătorului.
*/
public class Player extends Entity implements Subject {
    private ArrayList<Observer> observers =new ArrayList<>();
    private boolean hasBone=false;
    private KeyHandler keyH;  /// KeyHandler este un obiect care se ocupă de gestionarea tastelor
    public boolean leftPressed,rightPressed,upPressed,attackPressed,collectPressed; /// Starea tastelor
    private int [][] map; /// Mapa jocului
    private float airSpeed=0f; /// Viteza de cădere sau de salt
    private final float gravity=0.06f; /// Gravitatea jocului
    private final float jumpSPEED=-2.8f;  /// Viteza de salt
    protected  int lives=3; /// Numărul de vieți
    private final float fallSpeedAfterCollision=0.5f; /// Viteza de cădere după coliziune
    private boolean inAir=false;  /// Starea jucătorului: în aer sau pe sol
    //attack box
    private Rectangle2D.Float attackArea;/// Zona de atac a jucătorului
    private boolean attackChecked; /// Dacă zona de atac a jucătorului a fost verificată
    private Playing playing; /// Obiectul Playing pentru a permite accesul la diverse obiecte din joc
    public static Points points; /// Obiectul Points pentru a urmări punctajul jucătorului
    public static int totalPoints=0;

    /*! \fn public Player( int x,int y,int speed,String dir,KeyHandler keyH, int[][] map,Playing playing)
    \brief Constructorul clasei Player.

    Constructorul initializează obiectul de tip Player cu valorile primite ca parametri și setează zona solidă și zona de atac.

    \ x Coordonata x a poziției obiectului în cadrul hărții.
    \ y Coordonata y a poziției obiectului în cadrul hărții.
    \ speed Viteza de deplasare a obiectului.
    \ dir Direcția în care se mișcă obiectul .
    \ keyH Handler pentru tastatură pentru a detecta comenzile utilizatorului.
    \ map Harta jocului pe care se află obiectul.
    \ playing Referință la jocul în care se află obiectul.

    */
    public Player( int x,int y,int speed,String dir,KeyHandler keyH, int[][] map,Playing playing) {
        super(x, y,speed,dir);
        points=new Points();
        BoneObserver boneObserver=new BoneObserver();
        this.registerObserver(boneObserver);
        this.keyH = keyH;
        this.playing=playing;
        initSolidArea(x,y,25,30);
        initAttackArea();
        if (!IsEntityOnFloor(getSolidArea(), map))
            inAir = true;
    }

    public Rectangle2D.Float getAttackArea() {
        return attackArea;
    }

    public void setAttackArea(Float x, Float y) {
       attackArea.x=x;
       attackArea.y=y;
    }

    /*!\fn private void initAttackArea()
        \brief Initializează zona de atac a jucătorului.

        Funcția initializează zona de atac a jucătorului cu o formă dreptunghiulară de dimensiunea 20x20.

         */
    private void initAttackArea() {
        attackArea=new Rectangle2D.Float(x,y,20,20);
    }

    /*!
        \fn public void loadMap(int[][] map)
        \brief Încarcă harta jocului.
         Funcția primește ca parametru o matrice bidimensională de întregi și o stochează în membrul de clasă map.
    */
    public void loadMap(int[][] map)
    {
        this.map=map;
    }
    /*!
    \fn public void update(Level level, Mouse mouse, EnemyManager enemyManager)
    \brief Actualizează starea jucătorului.

    Funcția actualizează starea jucătorului în funcție de nivelul curent,
    de obiectul de tip Mouse și de managerul de inamici, primite ca parametri.
    Verifică dacă jucătorul a câștigat nivelul și daca toți inamicii sunt morți,
    precum și dacă jucătorul a rămas fără vieți și îl marchează ca fiind mort în acest caz.
    Actualizează poziția jucătorului și zona de atac și verifică coliziunile cu diversele obiecte din joc, precum pești, șobolani și oase.
     De asemenea, verifică dacă jucătorul se află în apă și îl scufundă în acest caz.
     */
    public void update(Level level,ArrayList<Mouse> mouse,EnemyManager enemyManager) {

        if(points.getPoints()==level.getPoints() && enemyManager.allEnemyAreDead()  && IsAtFinish(this.solidArea,playing.getLevel())) {
            if(playing.getLvlIndex()==2) {
                playing.setGameWon(true);
                playing.setLvlIndex(0);
            }
            else
                playing.setLevelCompleted(true);//aici era set game won

            playing.addPoints();
            playing.savePoints();
        }

        if (lives <= 0) {
            playing.SetGameOver(true);
            playing.setLvlIndex(0);
            direction = "death";
            playing.addPoints();
            playing.savePoints();
            return;
        }


        updatePosition();

        updateAttackArea();
        if (Objects.equals(direction, "attack"))
            checkAttack();

        IsFish(getSolidArea(), level,this);
        for(Mouse m: mouse)
            IsMouse(getSolidArea(),m,this);

        IsBone(getSolidArea(), level,this,keyH);

        IsWater(this, this.map);
    }

    private boolean Intersect( ArrayList<Enemy> rex) {
        for(Enemy r: rex)
            if ((!r.isHurt && solidArea.intersects(r.solidArea)) ) {
                return true;
            }
        return false;
    }

    /*!
    \fn private void checkAttack()
    \brief Verifică dacă jucătorul atacă.

    Funcția verifică dacă jucătorul se află în starea de atac și își marchează atacul ca fiind verificat.
    Apoi, apelează funcția de verificare a loviturii în inamici din obiectul Playing.
     */
    private void checkAttack() {
        if (attackChecked)
            return;
        attackChecked = true;
        playing.checkEnemyHit(attackArea);
    }

    /*! \fn private void updateAttackArea()
        \brief Actualizează zona de atac a jucătorului în funcție de direcția în care este orientat.

        Funcția actualizează zona de atac a jucătorului în funcție de direcția în care este orientat.
        Dacă jucătorul este orientat spre dreapta sau este orientat în sus și ultima direcție apăsată a fost dreapta,
        atunci zona de atac va fi amplasată la o anumită distanță la dreapta de zona solidă a jucătorului.
        Dacă jucătorul este orientat spre stânga sau este orientat în sus și ultima direcție apăsată a fost stânga,
        atunci zona de atac va fi amplasată la o anumită distanță la stânga de zona solidă a jucătorului.
        Zona de atac este poziționată la o anumită înălțime față de zona solidă a jucătorului.
    */

    private void updateAttackArea() {
        if(Objects.equals(direction, "right")
                || (Objects.equals(direction, "up")
                && Objects.equals(lastPressed, "right"))){
            attackArea.x=solidArea.x+solidArea.width+5;
        } else if (Objects.equals(direction, "left")
                || (Objects.equals(direction, "up")
                && Objects.equals(lastPressed, "left"))) {
            attackArea.x=solidArea.x - solidArea.width - 5;
        }
        attackArea.y=solidArea.y+10;
    }

    /*!
        \brief Updatează poziția jucătorului.
        \fn private void updatePosition()
        Această funcție este responsabilă pentru actualizarea poziției jucătorului în funcție de intrările utilizatorului și coliziunile cu harta.
        Funcția are efecte secundare asupra variabilei membru solidArea.
     */
    private void updatePosition() {
        float xSpeed=0;

        //daca s-a apasat tasta de sus, verifica daca jucatorul nu este deja in aer
        if(upPressed)
        {
            if(!inAir) {
                direction="up";
                inAir = true;
                airSpeed = jumpSPEED;
            }
        }
        //daca jucatorul nu este in aer
        if(!inAir) {
            //verifica daca s-a apasat tasta de atac
            if(attackPressed)
                direction="attack";
            //daca nu s-a apasat nicio tasta de miscare sau s-au apasat ambele, jucatorul sta pe loc
            if ((!leftPressed && !rightPressed) || (leftPressed && rightPressed)) {
                if(attackPressed) {
                    direction = "attack";
                }else {
                    direction = "idle";
                }
                return;
            }
        }
        //daca s-a apasat tasta de mers la stanga
        if(leftPressed )
        {
            lastPressed = "left";
            direction = "left";
            xSpeed -= this.speed;
        }
        //daca s-a apasat tasta de mers la dreapta
        if (rightPressed)
        {
            lastPressed = "right";
            direction = "right";
            xSpeed+= this.speed;
        }

        //daca jucatorul nu este deja in aer
        if(!inAir)
            //verifica daca jucatorul nu este pe podea
            if(!IsEntityOnFloor(getSolidArea(),map))
                inAir = true;

        if(inAir) {
            //verifica daca jucatorul poate sa se miste in directia curenta fara coliziuni
            if (CanMoveHere( getSolidArea().x,getSolidArea().y + airSpeed, getSolidArea().width, getSolidArea().height, map))
            {
                direction="up";
                getSolidArea().y += airSpeed;
                airSpeed += gravity;
                updateXPos(xSpeed);//actualizeaza pozitia pe axa X
            }else {
                //daca jucatorul a cazut si a lovit ceva, reseteaza variabila inAir
                if(airSpeed>0)
                    resetInAir();
                else
                    airSpeed = fallSpeedAfterCollision;
            }
        }else
            updateXPos(xSpeed);

        if(inAir)
            if((!leftPressed && !rightPressed)||(leftPressed && rightPressed))
                direction="down";

        if(attackPressed)
            direction="attack";
        updateCounter();

    }

    /*! \fn private void resetInAir()
    \brief Resetarea stării "inAir" a jucătorului.

    Această funcție setează starea "inAir" a jucătorului la fals și resetează viteza aerului la 0. Această funcție este apelată ori de câte ori jucătorul aterizează pe o suprafață solidă sau se ciocnește de un obstacol în timp ce sare.

    */
    private void resetInAir() {
        inAir=false;
        airSpeed=0;
    }

    /*! \fn private void updateXPos(float xSpeed)
    \brief Actualizează poziția pe axa X a jucătorului.

    Această funcție actualizează poziția jucătorului pe axa X, cu o viteză dată ca parametru.
    Funcția verifică dacă există coliziuni cu zone solide de pe hartă și actualizează poziția doar dacă nu există coliziuni.

    \param xSpeed Viteza cu care se va actualiza poziția jucătorului pe axa X.

    */
    private void updateXPos(float xSpeed) {
        if(CanMoveHere(getSolidArea().x+xSpeed,getSolidArea().y,getSolidArea().width,getSolidArea().height,map) && !Intersect(playing.getEnemyManager().getRex()))
            getSolidArea().x+=xSpeed;
    }
    /*! \fn public void draw(Graphics g,int lvlOffset)
        \brief Desenează jucătorul.

        Această funcție primește ca parametri un obiect de tip Graphics pentru a desena jucătorul și un offset .
        Funcția apelează funcția drawPlayer pentru a desena jucătorul .

        \param g Obiectul de tip Graphics folosit pentru a desena jucătorul și nivelul.
        \param lvlOffset Offset-ul de pe axa X folosit pentru a desena jucatorul  corect fata de harta.

        */
    public void draw(Graphics g,int lvlOffset) {

        drawPlayer(g,lvlOffset);
    }

    /*! \fn private void drawPlayer(Graphics g,int lvlOffset)
    \brief Desenează personajul pe ecran.

    Această funcție primește obiectul Graphics și un offset pentru nivelul curent. Funcția desenează
    personajul cu imaginea corespunzătoare stării sale curente, cu ajutorul obiectului Graphics.

    \param g Obiectul Graphics folosit pentru a desena personajul.
    \param lvlOffset Offset-ul nivelului curent, folosit pentru a desena personajul la poziția corectă.

    */
    private void drawPlayer(Graphics g,int lvlOffset) {
        BufferedImage image = Assets.M_walkD0;

        if (Objects.equals(lastPressed, "right"))
            image = Assets.M_jumpD2;
        else if (Objects.equals(lastPressed, "left"))
            image = Assets.M_jumpS2;

        switch (direction) {
            case "up":
                if (Objects.equals(lastPressed, "right")) {
                    if (num == 1)
                        image = Assets.M_jumpD1;
                    if (num == 2)
                        image = Assets.M_jumpD2;
                    if (num == 3)
                        image = Assets.M_jumpD3;
                    if (num == 4)
                        image = Assets.M_jumpD2;
                    break;
                }
                else if (Objects.equals(lastPressed, "left")) {
                    if (num == 1)
                        image = Assets.M_jumpS1;
                    if (num == 2)
                        image = Assets.M_jumpS2;
                    if (num == 3)
                        image = Assets.M_jumpS3;
                    if(num==4)
                        image = Assets.M_jumpS2;

                    break;
                }
            case "left":
                if (num == 1)
                    image = Assets.M_walkS0;
                if (num == 2)
                    image = Assets.M_walkS1;
                if (num == 3)
                    image = Assets.M_walkS2;
                if (num == 4)
                    image = Assets.M_walkS3;
                break;
            case "right":
                if (num == 1)
                    image = Assets.M_walkD0;
                if (num == 2)
                    image = Assets.M_walkD1;
                if (num == 3)
                    image = Assets.M_walkD2;
                if (num == 4)
                    image = Assets.M_walkD3;
                break;
            case "down":
                if (Objects.equals(lastPressed, "right"))
                    image = Assets.M_jumpD2;
                else if (Objects.equals(lastPressed, "left"))
                    image = Assets.M_jumpS2;
                break;
            case "idle":
                if(num==1 || num==3)
                    image = Assets.M_idle1;
                if(num==2 || num==4)
                    image = Assets.M_idle2;
                updateCounter();
                break;
            case "attack":
                if (Objects.equals(lastPressed, "right")) {
                    if (num == 1)
                        image = Assets.M_attackD0;
                    if (num == 2)
                        image = Assets.M_attackD1;
                    if (num == 3)
                        image = Assets.M_attackD3;
                    if (num == 4)
                        image = Assets.M_attackD1;
                    updateCounter();
                }
                else if (Objects.equals(lastPressed, "left")) {
                    if (num == 1)
                        image = Assets.M_attackS0;
                    if (num == 2)
                        image = Assets.M_attackS1;
                    if (num == 3)
                        image = Assets.M_attackS3;
                    if (num == 4)
                        image = Assets.M_attackS1;
                    updateCounter();
                }
                break;
            case "death":
                if (Objects.equals(lastPressed, "right")) {

                    image = Assets.MarthaDeath[2];
                    if(!playing.gameOver)
                        updateCounter();
                }
                else if (Objects.equals(lastPressed, "left")) {
                    image = Assets.MarthaDeath[6];
                    if(!playing.gameOver)
                        updateCounter();
                }
                break;
        }

        g.drawImage(image,(int)(getSolidArea().x-xOffset)-lvlOffset,(int) (getSolidArea().y-yOffset), Width, Height, null);
    }

    /*! \fn private void updateCounter()
    \brief Actualizează contorul pentru animația jucătorului.

    Această funcție actualizează contorul pentru animația jucătorului.
    Contorul se utilizează pentru a schimba imaginea jucătorului și a crea o animație.
    Funcția incrementează contorul și, dacă acesta depășește 12, actualizează numărul de animație și resetează contorul.

    */
    private void updateCounter() {
        counter++;
        if (counter > 12) {
            this.updateNum();
            counter = 0;
        }
    }
    protected void updateNum() {
        if(num==1)
            num = 2;
        else if(num==2)
            num = 3;
        else if(num==3)
            num=4;
        else if(num==4) {
            num = 1;
            attackChecked=false;
        }
    }

    /*!
\fn public void changeCoord(int x, int y)
\brief Schimbă coordonatele zonei solide a obiectului.

Această funcție primește două valori întregi, x și y, care reprezintă noile coordonate ale zonei solide a obiectului.
 Aceste coordonate sunt setate în membrul de date solidArea al obiectului.

\param x Coordonata x a zonei solide.
\param y Coordonata y a zonei solide.
*/
    public  void changeCoord(int x, int y)
    {
        solidArea.x=x;
        solidArea.y=y;
    }

    /*!
    \fn public void changeLife()
    \brief Scade numărul de vieți al obiectului cu 1.

    Această funcție scade numărul de vieți al obiectului curent cu 1.
    Dacă numărul de vieți este deja 0, funcția nu face nimic.
    */
    public  void  changeLife()
    {
        if(this.lives>0)
            lives--;
    }

    /*!
    \fn public int getLives()
    \brief Funcția returnează numărul de vieți ale jucătorului.

    Această funcție returnează numărul de vieți ale jucătorului stocat în variabila "lives".
    \return Numărul de vieți ale jucătorului.
    */
    public  int getLives()
    {
        return lives;
    }


    /*!

    \fn public void resetAll()
    \brief Funcția resetează toate variabilele și proprietățile jucătorului la valorile lor inițiale.
    Această funcție este utilizată pentru a reseta jocul și a-l reporni de la început.
     Ea resetează direcția jucătorului la "dreapta", starea "inAir" la fals,
     numărul de vieți la 3 și poziția jucătorului la poziția inițială.
     De asemenea, resetează punctele și starea "bone" ale jucătorului la valorile lor inițiale.
     Dacă jucătorul nu se află pe o suprafață solidă după resetare, starea "inAir" va fi setată la adevărat.
    \return Nicio valoare nu este returnată de această funcție.
    */
    public void resetAll() {
        direction="right";
        inAir=false;
        this.lives=3;

        solidArea.x=STARTX;
        solidArea.y=STARTY;
        attackArea.x=x;
        attackArea.y=y;
        points.setPoints(0);
        if (!IsEntityOnFloor(getSolidArea(), map))
            inAir = true;
    }

    public Points getPoints() {
        return points;
    }
    public void collectBone(){
        hasBone=true;
        notifyObservers();
    }
    public void loseBone(){
        hasBone=false;
        notifyObservers();
    }
    public boolean getHasBone()
    {
        return hasBone;
    }
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(hasBone);
        }
    }

    public boolean getInAir() {
        return inAir;
    }
    public void setHasBone(boolean b) {
        hasBone=b;
    }

    public void setLives(int anInt) {
        lives=anInt;
    }

    public void setInAir(boolean b) {
        inAir=b;
    }
}

