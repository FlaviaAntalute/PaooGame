package PaooGame.GameStates;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

    /*!
    \brief Interfața care definește metodele pentru starea jocului.

    Această interfață definește metodele necesare pentru starea jocului, cum ar fi actualizarea jocului,
    desenarea elementelor jocului pe ecran și manipularea intrărilor utilizatorului.
    Implementarea acestei interfețe într-o clasă de stare specifică va asigura că metodele necesare sunt definite și pot fi apelate în mod corespunzător în funcție de starea curentă a jocului.

    Metodele definite în această interfață sunt următoarele:

    update(): Această metodă actualizează starea jocului.
    draw(Graphics g): Această metodă desenează elementele jocului pe ecran, folosind contextul grafic specificat.
    mouseClicked(MouseEvent e): Această metodă este apelată atunci când utilizatorul face clic pe mouse.
    mousePressed(MouseEvent e): Această metodă este apelată atunci când utilizatorul apasă pe butonul mouse-ului.
    mouseReleased(MouseEvent e): Această metodă este apelată atunci când utilizatorul eliberează butonul mouse-ului.
    mouseMoved(MouseEvent e): Această metodă este apelată atunci când utilizatorul mișcă mouse-ul.
    keyPressed(KeyEvent e): Această metodă este apelată atunci când utilizatorul apasă o tastă.
    keyReleased(KeyEvent e): Această metodă este apelată atunci când utilizatorul eliberează o tastă.
    */


public interface StateMethods {
    public void update();
    public void draw(Graphics g);
    public void mouseClicked(MouseEvent e);
    public void mousePressed(MouseEvent e);
    public void mouseReleased(MouseEvent e);
    public void mouseMoved(MouseEvent e);
    public void keyPressed(KeyEvent e);
    public void keyReleased(KeyEvent e);
}
