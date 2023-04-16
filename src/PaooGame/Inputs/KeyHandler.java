package PaooGame.Inputs;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    public boolean leftPressed,rightPressed,upPressed,attackPressed,collectPressed;
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code=e.getKeyCode();
        if(code==KeyEvent.VK_UP)
            upPressed=true;
        if(code==KeyEvent.VK_DOWN)
            collectPressed=true;
        if(code==KeyEvent.VK_RIGHT)
            rightPressed=true;
        if(code==KeyEvent.VK_LEFT)
            leftPressed=true;

        if(code==KeyEvent.VK_SPACE)
            attackPressed=true;

    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code=e.getKeyCode();
        if(code==KeyEvent.VK_RIGHT)
            rightPressed=false;
        if(code==KeyEvent.VK_LEFT)
            leftPressed=false;
        if(code==KeyEvent.VK_UP)
            upPressed=false;
        if(code==KeyEvent.VK_DOWN)
            collectPressed=false;
        if(code==KeyEvent.VK_SPACE)
            attackPressed=false;

    }
}
