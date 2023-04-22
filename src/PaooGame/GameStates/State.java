package PaooGame.GameStates;

import PaooGame.Game;

    /*!
    \class State
    \brief Clasa reprezintă unul dintre stările jocului.

    Această clasă este o clasă abstractă din care sunt derivate celelalte stări ale jocului.
    Ea conține o referință către instanța de joc și o metodă pentru a obține această instanță.

    \var protected Game game
    Variabila care stochează instanța de joc.

     */
public class State {
    protected Game game;

    /*!
    \fn public State(Game game)
    \brief Constructorul clasei State.

    Acest constructor primește o referință către instanța de joc și o stochează în variabila game.

    \param game - referință către instanța de joc.
     */
    public State(Game game)
    {
        this.game=game;
    }

    /*!
    \fn public Game getGame()
    \brief Funcția returnează instanța de joc.

     Această funcție returnează instanța de joc stocată în variabila game.

     \return Referință către instanța de joc.
    */
    public Game getGame()
    {
        return game;
    }
}
