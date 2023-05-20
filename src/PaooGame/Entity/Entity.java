package PaooGame.Entity;

import java.awt.*;
import java.awt.geom.Rectangle2D;

/*!
    Clasa abstractă Entity reprezintă entitățile din joc (ex: jucător, inamic).
    Aceasta conține variabile și metode comune tuturor entităților.
*/
public abstract class Entity {
    protected int x;    /// poziția x a entității
    protected int y;       /// poziția y a entității
    protected int speed;/// viteza entității
    protected int Height=48;/// înălțimea entității
    protected int Width=48;/// lățimea entității
    protected String direction; /// direcția entității (stânga, dreapta, sus, jos)
    protected int counter=0;    /// contorul de animație
    protected int num=1;    /// numărul de imagini pentru animație
    protected String lastPressed="right";   /// ultima tastă apăsată/ultima directie (stanga/dreapta)
    protected  Rectangle2D.Float solidArea; /// zona solidă asociată entității
    protected static int xOffset=12;     /// compensație pentru poziționarea imaginii entității
    protected static int yOffset=12;    /// compensație pentru poziționarea imaginii entității

    /*! \fn  public Entity(int x, int y, int s,String dir)

        \Constructor pentru clasa Entity.
        \ x poziția inițială pe axa x a entității
        \ y poziția inițială pe axa y a entității
        \ s viteza entității
        \ dir direcția inițială a entității (stânga, dreapta, sus, jos)
    */
    public Entity(int x, int y, int s,String dir)
    {
        this.x=x;
        this.y=y;
        this.speed=s;
        this.direction=dir;
    }
    /*! \fn protected void initSolidArea(float x,float y, float width,float height)

        \Inițializează zona solidă asociată entității.
        \ x poziția x a zonei solide
        \ y poziția y a zonei solide
        \ width lățimea zonei solide
        \ height înălțimea zonei solide
     */
    protected void initSolidArea(float x,float y, float width,float height)
    {
        solidArea=new Rectangle2D.Float(x,y,width,height);
    }
    /*!
     Returnează zona solidă asociată entității.
     */
    public Rectangle2D.Float getSolidArea() {
        return solidArea;
    }
    public String getDirection() {
        return direction;
    }

    public void setSolidArea(float aFloat, float aFloat1) {
        solidArea.x=aFloat;
        solidArea.y=aFloat1;
    }
    public void setDirection(String string) {
        direction=string;
    }

    public int getX() {
        return this.x;
    }
    public int getY() {
        return this.y;
    }
}
