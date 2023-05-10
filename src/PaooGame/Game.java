package PaooGame;
import PaooGame.GameStates.Gamestate;
import PaooGame.GameStates.Menu;
import PaooGame.GameStates.Playing;
import PaooGame.Inputs.KeyHandler;
import PaooGame.Inputs.MouseHandler;
import PaooGame.GameWindow.GameWindow;
import PaooGame.Graphics.Assets;
import PaooGame.Tiles.Tile;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;




/*! \class Game
    \brief Clasa principala a intregului proiect. Implementeaza Game - Loop (Update -> Draw)

                ------------
                |           |
                |     ------------
    60 times/s  |     |  Update  |  -->{ actualizeaza variabile, stari, pozitii ale elementelor grafice etc.
        =       |     ------------
     16.7 ms    |           |
                |     ------------
                |     |   Draw   |  -->{ deseneaza totul pe ecran
                |     ------------
                |           |
                -------------
    Implementeaza interfata Runnable:

        public interface Runnable {
            public void run();
        }

    Interfata este utilizata pentru a crea un nou fir de executie avand ca argument clasa Game.
    Clasa Game trebuie sa aiba definita metoda "public void run()", metoda ce va fi apelata
    in noul thread(fir de executie). Mai multe explicatii veti primi la curs.

    In mod obisnuit aceasta clasa trebuie sa contina urmatoarele:
        - public Game();            //constructor
        - private void init();      //metoda privata de initializare
        - private void update();    //metoda privata de actualizare a elementelor jocului
        - private void draw();      //metoda privata de desenare a tablei de joc
        - public run();             //metoda publica ce va fi apelata de noul fir de executie
        - public synchronized void start(); //metoda publica de pornire a jocului
        - public synchronized void stop()   //metoda publica de oprire a jocului
 */
public class Game extends JPanel implements Runnable
{
    private static Game game=null;
    private static GameWindow      wnd;        /*!< Fereastra in care se va desena tabla jocului*/
    private boolean         runState;   /*!< Flag ce starea firului de executie.*/
    private Thread          gameThread; /*!< Referinta catre thread-ul de update si draw al ferestrei*/
    private BufferStrategy  bs;         /*!< Referinta catre un mecanism cu care se organizeaza memoria complexa pentru un canvas.*/
    /// Sunt cateva tipuri de "complex buffer strategies", scopul fiind acela de a elimina fenomenul de
    /// flickering (palpaire) a ferestrei.
    /// Modul in care va fi implementata aceasta strategie in cadrul proiectului curent va fi triplu buffer-at

    ///                         |------------------------------------------------>|
    ///                         |                                                 |
    ///                 ****************          *****************        ***************
    ///                 *              *   Show   *               *        *             *
    /// [ Ecran ] <---- * Front Buffer *  <------ * Middle Buffer * <----- * Back Buffer * <---- Draw()
    ///                 *              *          *               *        *             *
    ///                 ****************          *****************        ***************
    private Graphics        g;          /*!< Referinta catre un context grafic.*/
    private Tile tile; /*!< variabila membra temporara. Este folosita in aceasta etapa doar pentru a desena ceva pe ecran.*/
    private KeyHandler keyH=new KeyHandler(this);/*! Reprezintă un obiect KeyHandler care este asociat cu obiectul curent și este folosit pentru a gestiona input-ul de la tastatură în joc.*/
    private MouseHandler mouseH=new MouseHandler(this);/*!Reprezintă un obiect MouseHandler care este asociat cu obiectul curent și este folosit pentru a gestiona input-ul de la mouse în joc.*/
    private Playing playing;/*!Reprezintă un obiect Playing care este utilizat pentru a gestiona starea și mecanica jocului atunci când jocul este în desfășurare.*/
    private Menu menu;/*!Reprezintă un obiect Menu care este utilizat pentru a afișa meniul principal al jocului și pentru a gestiona selecțiile utilizatorului în meniu.*/


    /*! \fn public Game(String title, int width, int height)
        \brief Constructor de initializare al clasei Game.

        Acest constructor primeste ca parametri titlul ferestrei, latimea si inaltimea
        acesteia avand in vedere ca fereastra va fi construita/creata in cadrul clasei Game.

        \param title Titlul ferestrei.
        \param width Latimea ferestrei in pixeli.
        \param height Inaltimea ferestrei in pixeli.
     */
    private Game()
    {
        /// Obiectul GameWindow este creat insa fereastra nu este construita
        /// Acest lucru va fi realizat in metoda init() prin apelul
        /// functiei BuildGameWindow();
       // wnd = new GameWindow(title, width, height);
        /// Resetarea flagului runState ce indica starea firului de executie (started/stoped)
        runState = false;

    }
    public static Game GetInstance() {
        if (game==null)
            return new Game();
        return game;
    }

    /*!
     Returnează înălțimea ferestrei aplicației.
    */
    public static int GetWndHeight() {
        return wnd.GetWndHeight();
    }

    /*!
     Returnează lățimea ferestrei aplicației.
    */
    public static int GetWndWidth() {
        return wnd.GetWndWidth();
    }

    /*! \fn private void init()
        \brief  Metoda construieste fereastra jocului, initializeaza aseturile, listenerul de tastatura etc.

        Fereastra jocului va fi construita prin apelul functiei BuildGameWindow();
        Sunt construite elementele grafice (assets): dale, player, elemente active si pasive.

     */
    private void InitGame()
    {
        wnd = new GameWindow("Catventure", Tile.NrTileWidth*Tile.TILE_WIDTH, Tile.NrTileHeight*Tile.TILE_HEIGHT);
        /// Este construita fereastra grafica.
        wnd.BuildGameWindow();
        /// Se incarca toate elementele grafice (dale)
        Assets.Init();
        /// Inițializează un nou obiect Menu care este asociat cu obiectul curent
        menu=new Menu(this);
        /// Inițializează o nouă instanță a clasei Playing care este asociată cu obiectul curent.
        playing=new Playing(this);
        /// Adaugă un nou KeyListener la canvas-ul asociat obiectului Wnd curent.
        wnd.GetCanvas().addKeyListener(keyH);
        /// Adaugă un nou MouseListener la canvas-ul asociat obiectului Wnd curent.
        wnd.GetCanvas().addMouseListener(mouseH);
        /// Adaugă un nou MouseMotionListener la canvas-ul asociat obiectului Wnd curent.
        wnd.GetCanvas().addMouseMotionListener(mouseH);
        ///  Setează obiectul curent ca fiind focusabil, ceea ce înseamnă că acesta poate primi focusul tastaturii.
        this.setFocusable(true);
    }

    /*! \fn public void run()
        \brief Functia ce va rula in thread-ul creat.

        Aceasta functie va actualiza starea jocului si va redesena tabla de joc (va actualiza fereastra grafica)
     */
    public void run()
    {
        /// Initializeaza obiectul game
        InitGame();
        long oldTime = System.nanoTime();   /*!< Retine timpul in nanosecunde aferent frame-ului anterior.*/
        long curentTime;                    /*!< Retine timpul curent de executie.*/

        /// Apelul functiilor Update() & Draw() trebuie realizat la fiecare 16.7 ms
        /// sau mai bine spus de 60 ori pe secunda.

        final int framesPerSecond   = 60; /*!< Constanta intreaga initializata cu numarul de frame-uri pe secunda.*/
        final double timeFrame      = 1000000000 / framesPerSecond; /*!< Durata unui frame in nanosecunde.*/

        /// Atat timp timp cat threadul este pornit Update() & Draw()
        while (runState == true)
        {
            /// Se obtine timpul curent
            curentTime = System.nanoTime();
            /// Daca diferenta de timp dintre curentTime si oldTime mai mare decat 16.6 ms
            if((curentTime - oldTime) > timeFrame)
            {
                /// Actualizeaza pozitiile elementelor
                Update();
                /// Deseneaza elementele grafica in fereastra.
                Draw();
                oldTime = curentTime;
            }
        }

    }

    /*! \fn public synchronized void start()
        \brief Creaza si starteaza firul separat de executie (thread).

        Metoda trebuie sa fie declarata synchronized pentru ca apelul acesteia sa fie semaforizat.
     */
    public synchronized void StartGame()
    {
        if(runState == false)
        {
            /// Se actualizeaza flagul de stare a threadului
            runState = true;
            /// Se construieste threadul avand ca parametru obiectul Game. De retinut faptul ca Game class
            /// implementeaza interfata Runnable. Threadul creat va executa functia run() suprascrisa in clasa Game.
            gameThread = new Thread(this);
            /// Threadul creat este lansat in executie (va executa metoda run())
            gameThread.start();
        }
        else
        {
            /// Thread-ul este creat si pornit deja
            return;
        }
    }

    /*! \fn public synchronized void stop()
        \brief Opreste executie thread-ului.

        Metoda trebuie sa fie declarata synchronized pentru ca apelul acesteia sa fie semaforizat.
     */
    public synchronized void StopGame()
    {
        if(runState == true)
        {
            /// Actualizare stare thread
            runState = false;
            /// Metoda join() arunca exceptii motiv pentru care trebuie incadrata intr-un block try - catch.
            try
            {
                /// Metoda join() pune un thread in asteptare panca cand un altul isi termina executie.
                /// Totusi, in situatia de fata efectul apelului este de oprire a threadului.
                gameThread.join();
            }
            catch(InterruptedException ex)
            {
                /// In situatia in care apare o exceptie pe ecran vor fi afisate informatii utile pentru depanare.
                ex.printStackTrace();
            }
        }
        else
        {
            /// Thread-ul este oprit deja.
            return;
        }
    }

    /*! \fn private void Update()
        \brief Actualizeaza starea elementelor din joc.

        Metoda este declarata privat deoarece trebuie apelata doar in metoda run()
     */
    private void Update()
    {
        switch (Gamestate.state)
        {
            case MENU :
                /// Dacă starea jocului este MENU, apelăm metoda update() a obiectului menu.
                menu.update();
                break;
            case PLAYING:
                /// Dacă starea jocului este PLAYING, apelăm metoda update() a obiectului playing.
                playing.update();
                break;
            case OPTIONS:
            case QUIT:
            default:
                System.exit(0);
                break;
        }
    }

    /*! \fn private void Draw()
        \brief Deseneaza elementele grafice in fereastra coresponzator starilor actualizate ale elementelor.

        Metoda este declarata privat deoarece trebuie apelata doar in metoda run()
     */
    private void Draw()
    {
        /// Returnez bufferStrategy pentru canvasul existent
        bs = wnd.GetCanvas().getBufferStrategy();
        /// Verific daca buffer strategy a fost construit sau nu
        if(bs == null)
        {
            /// Se executa doar la primul apel al metodei Draw()
            try
            {
                /// Se construieste tripul buffer
                wnd.GetCanvas().createBufferStrategy(3);
                return;
            }
            catch (Exception e)
            {
                /// Afisez informatii despre problema aparuta pentru depanare.
                e.printStackTrace();
            }
        }
        /// Se obtine contextul grafic curent in care se poate desena.
        g = bs.getDrawGraphics();
        /// Desenează elementele grafice corespunzătoare stării curente a jocului.
        draw();
        // end operatie de desenare
        /// Se afiseaza pe ecran
        bs.show();
        /// Elibereaza resursele de memorie aferente contextului grafic curent (zonele de memorie ocupate de
        /// elementele grafice ce au fost desenate pe canvas).
        g.dispose();
    }


    /*! \fn private void draw()
        \brief Desenează elementele grafice corespunzătoare stării curente a jocului, prin apelarea metodei draw() a obiectului corespunzător.

        Metoda este declarata privat deoarece trebuie apelata doar in metoda Draw()
     */
    private void draw()
    {
        switch (Gamestate.state)
        {
            case MENU :
                ///  Dacă starea jocului este MENU, apelăm metoda draw() a obiectului menu, transmițându-i obiectul Graphics g.
                menu.draw(g);
                break;
            case PLAYING:
                /// Dacă starea jocului este PLAYING, apelăm metoda draw() a obiectului playing, transmițându-i obiectul Graphics g.
                playing.draw(g);
                break;
            default:
                break;
        }
    }

    /*!\fn public KeyHandler getKeyH()
        \brief Returnează obiectul KeyHandler pentru evenimentele tastaturii înregistrate în fereastra jocului.

        Metoda este folosită pentru a obține referința la obiectul KeyHandler creat pentru a trata evenimentele tastaturii în fereastra jocului.
    */
    public KeyHandler getKeyH(){
        /// Returnează obiectul KeyHandler asociat evenimentelor tastaturii.
        return keyH;
    }
    /*!\fn public GameWindow getWnd()
        \brief Returnează obiectul GameWindow asociat cu fereastra jocului.

        Metoda este folosită pentru a obține referința la obiectul GameWindow asociat cu fereastra jocului, care gestionează interfața grafică a utilizatorului (UI). Obiectul GameWindow conține fereastra jocului și metodele necesare pentru a-l gestiona.
     */
    public GameWindow getWnd()
    {
        ///Returnează obiectul GameWindow asociat cu fereastra jocului.
        return  wnd;
    }
    /*!\fn public Menu getMenu()
        \brief Returnează obiectul Menu pentru meniul jocului.

        Metoda este folosită pentru a obține referința la obiectul Menu creat pentru afișarea meniului jocului. Obiectul Menu conține elementele grafice și metodele necesare pentru a afișa și gestiona meniul jocului.
    */
    public Menu getMenu() {
        ///Returnează obiectul Menu pentru meniul jocului.
        return menu;
    }
    /*!\fn public Playing getPlaying()
        \brief Returnează obiectul Playing pentru jocul în sine.

        Metoda este folosită pentru a obține referința la obiectul Playing creat pentru gestionarea jocului în sine. Obiectul Playing conține elementele grafice și metodele necesare pentru a gestiona jocul și a afișa elementele grafice asociate.
    */
    public Playing getPlaying() {
        ///  Returnează obiectul Playing pentru jocul în sine.
        return playing;
    }

}

