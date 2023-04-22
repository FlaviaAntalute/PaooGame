package PaooGame.Entity;
import PaooGame.Tiles.Tile;

import java.awt.geom.Rectangle2D;
import java.util.Objects;
import static PaooGame.Entity.Collision.*;

    /*! \class public abstract class Enemy extends Entity{
        \brief Clasa abstractă pentru entitățile inamice din joc.

        Clasa Enemy este o clasă abstractă care servește drept bază pentru toate entitățile inamice din joc.
         Ea moștenește clasa Entity și adaugă câmpuri și metode specifice pentru inamici.

     */
public abstract class Enemy extends Entity{
    protected int enemyType; /// Tipul inamicului. Această variabilă este folosită pentru a distinge între diferitele tipuri de inamici.
    protected boolean first=true; /// Starea primului update. Această variabilă este utilizată pentru a asigura că metodele specifice inamicului nu sunt apelate înainte ca entitatea să fie inițializată.
    protected boolean inAir=false; /// Această variabilă este utilizată pentru a indica dacă inamicul se află în aer sau nu.
    protected float fall; /// Viteza caderii. Această variabilă este utilizată pentru a stoca valoarea cu care inamicul se va deplasa pe verticală atunci când se află în aer.
    protected float gravity=0.04f; ///Gravitate. Această variabilă este utilizată pentru a stoca valoarea cu care inamicul va cădea pe verticală atunci când se află în aer.
    protected String lastDir="left"; ///Direcția ultimului update. Această variabilă este utilizată pentru a stoca ultima direcție de mișcare a inamicului.
    protected float attackRange=Tile.TILE_WIDTH+5; ///Raza de atac. Această variabilă este utilizată pentru a stoca raza de acțiune a inamicului.
    protected boolean isAlive=true; ///Starea vieții. Această variabilă este utilizată pentru a indica dacă inamicul este încă în viață sau nu.
    protected boolean attackChecked;  ///Starea verificării atacului. Această variabilă este utilizată pentru a indica dacă atacul a fost verificat sau nu.
    public int lives; ///Numărul de vieți ale inamicului.

        /*! \fn public Enemy(int x, int y, int s, int type,String dir)
            \brief Constructorul clasei.

            Constructorul primește ca parametri poziția inițială (x, y),
            dimensiunea și direcția de pornire a inamicului.

            \ x Coordonata x a poziției inițiale a inamicului.
            \ y Coordonata y a poziției inițiale a inamicului.
            \ s Dimensiunea inamicului.
            \ type Tipul inamicului.
            \ dir Direcția de pornire a inamicului.
         */
    public Enemy(int x, int y, int s, int type,String dir) {
        super(x, y, s,dir);
        this.enemyType=type;
        initSolidArea(x,y,Width,Height);
    }

    /*! \fn protected void firstUpdate(int [][] map)
            \brief Actualizează starea inițială a entității și verifică dacă este în aer.

            Metoda actualizează starea inițială a entității și verifică dacă se află în aer.
            Aceasta verifică dacă entitatea este pe podea prin intermediul funcției IsEntityOnFloor,
            iar dacă nu este pe podea, setează variabila inAir la true.

            \ map Harta nivelului curent.
    */
    protected void firstUpdate(int [][] map)
    {
        if (!IsEntityOnFloor(solidArea, map))
            inAir = true;
        first=false;
    }

    /*!\fn protected void InAir(int[][] map)
           \brief Verifică dacă entitatea se află în aer și ajustează poziția entității dacă este cazul.

            Metoda verifică dacă entitatea se află în aer și ajustează poziția entității în funcție de gravitație
            și de harta nivelului. Dacă entitatea poate fi mutată în jos, atunci se deplasează către acea direcție.
            În caz contrar, înseamnă că entitatea a atins solul și starea inAir este setată la fals.

           \ map Harta nivelului curent.
        */
    protected void InAir(int[][]map)
    {
        if (CanMoveHere(solidArea.x, solidArea.y+fall, solidArea.width, solidArea.height, map)) {
            solidArea.y += fall;
            fall += gravity;
        } else {
            inAir = false;
        }
    }

    /*! \fn protected void moveEnemy(int[][] map)
            \brief Mută inamicul pe harta.

            Funcția calculează viteza de deplasare a inamicului în funcție de direcție și o verifică apoi dacă poate fi mutat la noua poziție.
            Dacă inamicul se poate mișca pe podea, atunci acesta este mutat la noua poziție, altfel schimbă direcția de deplasare.

            \ map Harta curentă.
         */
    protected void moveEnemy(int[][] map)
    {
        float xSpeed=0;
        /// Verifică dacă inamicul se poate mișca la noua poziție și mută inamicul dacă este posibil
        if(Objects.equals(direction, "left"))
            xSpeed = -speed;
        else
            xSpeed=speed;

        if(CanMoveHere(solidArea.x+xSpeed,solidArea.y,solidArea.width,solidArea.height,map)) {
            if (IsFloor(solidArea, xSpeed, direction,map)) {
                solidArea.x += xSpeed;
                return;
            }
        }
        /// Schimbă direcția de deplasare dacă inamicul nu se poate mișca la noua poziție
        changeDirection();
    }

    /*! \fn protected boolean PlayerIsClose(int [][] map, Player player)
            \brief Verifică dacă jucătorul este în apropiere și dacă există o cale liberă între jucător și inamic.

            Această metodă verifică dacă jucătorul este pe aceeași linie verticală cu inamicul și dacă este în raza de acțiune a inamicului.
            Dacă cele două condiții sunt îndeplinite, metoda verifică dacă există un obstacol între jucător și inamic, folosind funcția IsClear.
            \ map Harta nivelului curent.
            \ player Jucătorul curent.
            \ Returnează adevărat dacă jucătorul este în apropiere și există o cale liberă între jucător și inamic. În caz contrar, returnează fals.
        */
    protected boolean PlayerIsClose(int [][] map, Player player)
    {
        int yIndexEnemy= (int)solidArea.y/ Tile.TILE_HEIGHT;
        int yIndexPlayer= (int)player.solidArea.y/ Tile.TILE_HEIGHT;
        if(yIndexPlayer==yIndexEnemy)
            if(IsPlayerInRange(player))
                return IsClear(map, solidArea, player.getSolidArea(), yIndexEnemy);
        return false;
    }

    /*! \fn protected void checkPlayerHit(Rectangle2D.Float attackArea,Player player)
            \brief Verifică dacă jucătorul este lovit de inamic și actualizează starea jucătorului.

            Metoda verifică dacă dreptunghiul de atac al inamicului intersectează dreptunghiul solidArea al jucătorului.
            Dacă se întâmplă acest lucru, starea de viață a jucătorului este redusă și poziția sa este actualizată în funcție de direcția atacului.

            \ attackArea Dreptunghiul de atac al inamicului.
            \ player Jucătorul curent.
        */
    protected void checkPlayerHit(Rectangle2D.Float attackArea,Player player)
    {
        if(attackArea.intersects(player.solidArea)) {
            player.changeLife();
            if(Objects.equals(player.lastPressed, "right"))
                player.changeCoord((int)solidArea.x-50,(int)solidArea.y);
            else
                player.changeCoord((int)solidArea.x+50,(int)solidArea.y);

        }
        attackChecked=true;
    }

    /*! \fn protected boolean IsPlayerInRange(Player player)
            \brief Verifică dacă jucătorul se află în raza de atac a inamicului.

            Funcția calculează distanța dintre poziția inamicului și cea a jucătorului și
            verifică dacă este mai mică sau egală cu de cinci ori raza de atac a inamicului.

            \ player Referința către jucătorul curent.

            \ returneaza Adevărat dacă jucătorul este în raza de atac a inamicului, altfel fals.
         */
    protected boolean IsPlayerInRange(Player player) {
        int distance=(int)Math.abs(player.solidArea.x-solidArea.x);
        return distance<=attackRange*5;
    }

    /*! \fn protected void TurnToPlayer(Player player)

        \brief Schimbă direcția inamicului astfel încât acesta să fie orientat spre jucător.

        Funcția verifică poziția jucătorului în raport cu poziția inamicului și setează
        direcția inamicului corespunzător. Dacă poziția jucătorului se află la dreapta inamicului,
        direcția acestuia va fi setată ca "right", în caz contrar, direcția va fi setată ca "left".

        \ player Jucătorul curent.
    */
        protected void TurnToPlayer(Player player)
    {
        if(player.solidArea.x>solidArea.x)
            direction="right";
        else
            direction="left";
    }

    /*! \fn protected boolean CanAttack(Player player)
            \brief Verifică dacă inamicul se află la distanța de atac față de jucător.

            \ player Jucătorul curent.
            \ Returnează valoarea booleană true dacă inamicul se află la distanța de atac față de jucător, altfel false.
    */
    protected boolean CanAttack(Player player) {
            int distance = (int) Math.abs(player.solidArea.x - solidArea.x);
            return distance <= attackRange;
    }

    /*! \fn  void changeDirection()
        \brief Schimbă direcția în care se mișcă inamicul.
        Dacă inamicul se deplasează spre stânga, funcția îi schimbă direcția de mers spre dreapta și invers.
    */
        void changeDirection() {
        if (Objects.equals(direction, "left"))
            direction = "right";
        else
            direction = "left";
    }

    /*! \fn public void updateCounter()
        \brief Actualizează numărul curent din joc.
        Funcția crește variabila counter cu 1 și verifică dacă acesta depășește 15.
        În caz afirmativ, numărul imaginii este actualizat cu ajutorul funcției updateNum() și counter-ul este resetat la 0.
    */
        public void updateCounter() {
        counter++;
        if (counter > 15) {
            this.updateNum();
            counter = 0;
        }
    }

    /*! \fn protected void updateNum()
            \brief Actualizează numărul imaginii.
            Funcția verifică valoarea variabilei num și o actualizează în consecință:
            dacă num este 1, devine 2; dacă este 2, devine 3, și așa mai departe, până când ajunge la 5, moment în care se resetează la 1.
            În plus, dacă variabila direction este "attack" sau "hurt", aceasta va fi actualizată la "idle" atunci când num este resetat, iar dacă direction este "dead", variabila isAlive va fi setată la false.
        */
    protected void updateNum() {
        if(num==1)
            num = 2;
        else if(num==2)
            num = 3;
        else if(num==3)
            num=4;
        else if(num==4)
            num=5;
        else if (num==5) {
            num = 1;
            if (Objects.equals(direction, "attack")
                    || Objects.equals(direction, "hurt"))
                direction = "idle";
            else if (Objects.equals(direction, "dead"))
                isAlive=false;
        }
    }
    /*! \fn public void resetEnemy()
            \brief Resetează starea inamicului.
             Funcția resetează poziția solidArea a inamicului la poziția inițială x și y, setează first la true pentru a marca faptul că inamicul trebuie desenat din nou, setează lives la 1,
              resetează variabila fall la 0, setează direction la "idle" și setează isAlive la true.
        */
    public void resetEnemy()
    {
        solidArea.x=x;
        solidArea.y=y;
        first=true;
        lives=1;
        fall=0;
        direction="idle";
        isAlive=true;

    }
//    public String getDirection()
//    {
//        return direction;
//    }
    /*!\fn public boolean getIsAlive()
            \brief Returnează starea vieții inamicului.
            Funcția returnează valoarea booleană a variabilei isAlive, care indică dacă inamicul este în viață sau nu.
            \return isAlive - starea vieții inamicului (true dacă este în viață, false dacă nu este)
        */
    public boolean getIsAlive()
    {
        return isAlive;
    }
}
