package PaooGame.GameStates;

    /*!
    \enum Gamestate
    \brief Enumerație pentru starea jocului.

    Acest enum descrie stările în care se poate afla jocul. Enumerația conține următoarele stări:

    PLAYING: starea jocului când jucătorul este în mijlocul unei runde și poate să interacționeze cu elementele jocului.
    MENU: starea jocului când se afișează meniul principal și jucătorul poate alege între diferite opțiuni.
    Variabila statică "state" este de tipul Gamestate și este folosită pentru a păstra starea curentă a jocului.
    Deoarece este statică, variabila poate fi accesată din orice parte a programului și poate fi modificată fără a fi necesară o instanță a clasei.

    Pentru a accesa starea curentă a jocului, se poate folosi sintaxa "Gamestate.state", unde "state" este variabila statică definită în cadrul acestei enumerații.
    */
public enum Gamestate {
    PLAYING,MENU,OPTIONS,QUIT;
    public static Gamestate state=MENU;
}
