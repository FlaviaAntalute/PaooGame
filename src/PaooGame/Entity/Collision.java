package PaooGame.Entity;
import PaooGame.Game;
import PaooGame.Inputs.KeyHandler;
import PaooGame.Levels.Level;
import PaooGame.Tiles.Tile;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Objects;

import static PaooGame.Entity.Mouse.getyOffset;
import static PaooGame.Useful.Constants.PlayerConstants.STARTX;
import static PaooGame.Useful.Constants.PlayerConstants.STARTY;

public class Collision {

        /*!\fn public static boolean CanMoveHere(float x, float y, float width, float height, int[][] map)
            \brief Verifică dacă un obiect poate fi mutat la o poziție specifică pe hartă.

            \ Această metodă primește coordonatele (x, y) ale poziției obiectului, lățimea și înălțimea acestuia
            \ și o hartă reprezentată sub formă de matrice bidimensională de intregi (int[][] map),
            \ și verifică dacă obiectul poate fi mutat la poziția specificată fără a se intersecta cu un obiect solid de pe hartă,
            \ prin apeluri ale functiei isSolid().

            \ x Coordonata X a poziției obiectului.
            \ y Coordonata Y a poziției obiectului.
            \ width Lățimea obiectului.
            \ height Înălțimea obiectului.
            \ map Matricea reprezentând harta.
            \ Returnează valoarea booleană true dacă obiectul poate fi mutat la poziția specificată și false în caz contrar.
        */
    public static boolean CanMoveHere(float x, float y,float width,float height,int[][] map)
    {
        if(!isSolid(x,y,map))
            if(!isSolid(x+width,y+height,map))
                if(!isSolid(x+width,y,map))
                    if(!isSolid(x,y+height,map))
                        return true;
        return false;

    }
        /*!\fn public static boolean isSolid(float x, float y, int[][] map)
            \brief Verifică dacă un anumit punct de coordonate (x, y) se află pe un tile solid din harta dată.

            \ x Coordonata orizontală a punctului în pixeli.
            \ y Coordonata verticală a punctului în pixeli.
            \ map Harta de verificat, reprezentată ca o matrice bidimensională de intregi.
            \ Returnează valoarea true dacă punctul se află pe un tile solid și false în caz contrar.

            Metoda verifică dacă punctul de coordonate (x, y) se află pe un tile solid din harta dată.
            Mai întâi, verifică dacă coordonatele (x, y) se află în afara hărții,
            caz în care returnează valoarea true. Dacă punctul se află în interiorul hărții,
            calculează indicii tile-ului asociat punctului și verifică dacă tile-ul este solid prin apelul functiei IsSolid() specifica fiecarei dale .
            Dacă tile-ul nu este solid, metoda returnează valoarea false.
            În caz contrar, metoda returnează valoarea true.
        */
    public static boolean isSolid(float x, float y, int [][] map)
    {
        int maxWidth=map[0].length*Tile.TILE_WIDTH;
        if(x<0 || x>=maxWidth)
            return true;
        if(y<0 || y>=Game.GetWndHeight())
            return true;
        float xIndex=x/ Tile.TILE_HEIGHT;
        float yIndex=y/ Tile.TILE_HEIGHT;
        if(map[(int)yIndex][(int)xIndex]==0)
            return false;
        return Tile.tiles[map[(int)yIndex][(int)xIndex]].IsSolid();
    }

        /*!\fn public static boolean IsTileSolid(int x, int y, int[][] map)
            \brief Verifică dacă un tile din harta dată este solid.

             \ x Coordonata x a tile-ului în matricea de hărți.
             \ y Coordonata y a tile-ului în matricea de hărți.
             \ map Matricea de hărți ce reprezintă harta jocului.

            \ Returnează adevărat dacă tile-ul este solid, fals în caz contrar.
            Metoda este folosită pentru a verifica dacă un tile din harta dată este solid sau nu.
            În funcție de codul tile-ului din matricea de hărți,
            se determină dacă tile-ul este solid sau nu.
            Dacă codul este 0, tile-ul este considerat non-solid.
            În caz contrar, se apelează metoda IsSolid() a clasei Tile pentru a determina dacă tile-ul este solid sau nu.
        */

    public static boolean IsTileSolid(int x, int y, int[][] map)
    {
        if(map[(int)y][(int)x]==0)
            return false;
        return Tile.tiles[map[(int)y][(int)x]].IsSolid();

    }

    /*!\fn public static boolean IsEntityOnFloor(Rectangle2D.Float solidArea,int [][] map)
        \brief Verifică dacă o entitate se află pe podea.

        \ solidArea Zona solidă asociată entității.
        \ map Harta pe care se desfășoară jocul.

        \ Returnează true dacă entitatea se află pe podea, altfel false.

        Metoda verifică dacă o entitate este pe podea, prin verificarea dacă zona solidă asociată
        entității atinge orice obiect solid pe harta jocului în poziția sa curentă.
     */
   public static boolean IsEntityOnFloor(Rectangle2D.Float solidArea,int [][] map)
   {
       if(!isSolid(solidArea.x,solidArea.y+solidArea.height+1,map))
           if(!isSolid(solidArea.x+solidArea.width,solidArea.y+solidArea.height+1,map))
               return false;
       return true;
   }

    /*!\fn public static void IsFish(Rectangle2D.Float solidArea, Level level, Player player)
        \brief Verifică dacă jucătorul a ajuns la pește și actualizează scorul.

        \ solidArea Zona solidă asociată cu jucătorul pentru a verifica dacă a ajuns la pește.
        \ level Nivelul curent al jocului.
        \ player Jucătorul curent.

        Metoda verifică dacă jucătorul a ajuns la poziția unui  pește,
        prin verificarea codului de dala din harta nivelului în funcție de poziția solidArea.
        Dacă codul dalei corespunde cu codul dalei pentru pește,
        nivelul este actualizat pentru a elimina peștele și scorul jucătorului este actualizat cu o valoare corespunzătoare punctelor  pentru un pește.

    */
   public static void IsFish(Rectangle2D.Float solidArea,Level level,Player player)
   {
       float xIndex= solidArea.x/ Tile.TILE_HEIGHT;
       float yIndex= solidArea.y/ Tile.TILE_HEIGHT;
       if(level.getMap()[(int)yIndex][(int)xIndex]==Tile.peste.GetId()) {
           level.setId((int) yIndex, (int) xIndex, 0);
           Player.points.addPointsFish();
       }
   }

    /*! \fn public static void IsBone(Rectangle2D.Float solidArea, Level level, Player player, KeyHandler keyH)
        \ Verifică dacă entitatea jucătorului se intersectează cu un os
        și dacă tasta corespunzătoare de colectare a obiectelor este apăsată. Dacă da,
        atunci se elimină osul din nivel și se adaugă un os la inventarul jucătorului.

        \ solidArea Zona solidă a entității jucătorului reprezentată printr-un obiect Rectangle2D.Float.
        \ level Nivelul curent în care se află jucătorul, reprezentat prin obiectul Level.
        \ player Jucătorul, reprezentat prin obiectul Player.
        \ keyH Handlerul tastelor, reprezentat prin obiectul KeyHandler.

     */
    public static void IsBone(Rectangle2D.Float solidArea, Level level, Player player, KeyHandler keyH)
    {
        float xIndex= solidArea.x/ Tile.TILE_HEIGHT;
        float yIndex= solidArea.y/ Tile.TILE_HEIGHT;
        if(level.getMap()[(int)yIndex][(int)xIndex]==Tile.bone.GetId() && player.collectPressed) {
            level.setId((int) yIndex, (int) xIndex, 0);
            Player.points.setBone(true);
        }
    }

    /*!\fn public static void IsMouse(Rectangle2D.Float solidArea, Mouse mouse, Player player)
        \brief Verifică dacă jucătorul a ajuns la șoarece și actualizează scorul.

        Metoda verifică dacă jucătorul a ajuns la poziția șoarecelui,
        prin verificarea coordonatelor x și y ale zonei solide asociate jucătorului și coordonatele x și y ale șoarecelui.
        Dacă jucătorul a ajuns la șoarece, starea variabilei isMouse din obiectul Mouse este actualizată
        și scorul jucătorului este actualizat cu o valoare corespunzătoare punctelor pentru un șoarece.

        \ solidArea Zona solidă asociată cu jucătorul pentru a verifica dacă a ajuns la șoarece.
        \ mouse Obiectul Mouse pentru a verifica coordonatele șoarecelui.
        \ player Jucătorul curent.
    */
    public static void IsMouse(Rectangle2D.Float solidArea,Mouse mouse,Player player)
    {
        if(mouse.isMouse && (int)solidArea.x==mouse.x && (int)solidArea.y== mouse.y-getyOffset())  {
            Player.points.addPointsMouse();
            mouse.isMouse=false;
        }
    }

    /*!\fn public static boolean IsFloor(Rectangle2D.Float solidArea, float xSpeed, String dir, int[][] map)
        \brief Verifică dacă există podea la următoarea poziție pe care entitatea dorește să o ocupe.

        Metoda verifică dacă există podea la următoarea poziție pe care entitatea dorește să o ocupe în funcție de viteza sa
        și direcția de mișcare (stânga/dreapta).
        Verificarea se face prin apelul funcției isSolid() pe zona solidArea a entității,
        adăugându-se viteza de deplasare xSpeed și o unitate la coordonata y (deoarece verificarea se face pe podea).
        Funcția isSolid() verifică dacă punctul respectiv se află pe o țiglă solidă sau nu în funcție de harta nivelului.

        \param solidArea Zona solidă asociată cu entitatea pentru a verifica existența podelei în zona următoare.
        \param xSpeed Viteza de deplasare a entității pe axa x.
        \param dir Direcția de deplasare a entității (stânga/dreapta).
        \param map Harta nivelului curent al jocului.

        \return Funcția returnează o valoare booleană: true dacă există podea în zona următoare și false în caz contrar.

     */
    public static boolean IsFloor(Rectangle2D.Float solidArea,float xSpeed,String dir ,int [][] map)
    {
        if(Objects.equals(dir, "left"))
            return isSolid(solidArea.x+xSpeed,solidArea.y+ solidArea.height+1,map);
        else
            return isSolid(solidArea.x+xSpeed+solidArea.width,solidArea.y+ solidArea.height+1,map);
    }

        /*!\fn public static void IsWater(Player player, int[][] map)
            \brief Verifică dacă jucătorul a intrat în apă și actualizează starea și poziția jucătorului.

             Metoda verifică dacă jucătorul a intrat în apă prin verificarea codului dalei din harta nivelului în funcție de poziția solidArea a jucătorului.
             Dacă codul dalei corespunde cu unul dintre codurile dalelor pentru apă, starea de viață a jucătorului este redusă și poziția sa este resetată la poziția de start.

            \ player Jucătorul curent.
            \ map Harta nivelului curent.
        */
    public static void IsWater(Player player ,int [][] map )
    {
        float xIndex= player.getSolidArea().x/ Tile.TILE_HEIGHT;
        float yIndex= player.getSolidArea().y/ Tile.TILE_HEIGHT;
        if(map[(int)yIndex][(int)xIndex]==Tile.waterTile.GetId() || map[(int)yIndex][(int)xIndex]==Tile.waterTile.GetId()
                || map[(int)yIndex][(int)xIndex]==Tile.waterTile1.GetId()
                || map[(int)yIndex][(int)xIndex]==Tile.waterTile2.GetId()) {
           player.changeLife();
           player.changeCoord(STARTX,STARTY);
        }

    }

    /*! \fn public static boolean IsClear(int[][] map, Rectangle2D.Float solidArea, Rectangle2D.Float solidArea1, int yIndexEnemy) {

         \brief Verifică dacă o anumită zonă este liberă de obstacole.

         Metoda verifică dacă o anumită zonă, definită de cele două zone solide,
         este liberă de obstacole în harta nivelului, verificând dacă există coduri de dale solide în interiorul acestei zone.

         \ map Harta nivelului curent.
         \ solidArea Prima zonă solidă.
         \ solidArea1 A doua zonă solidă.
         \ yIndexEnemy Indicele de poziție pe axa y al inamicului pentru a verifica dacă zona de interes este liberă.

        \return Returnează true dacă zona este liberă și false în caz contrar.
     */
    public static boolean IsClear(int[][] map, Rectangle2D.Float solidArea, Rectangle2D.Float solidArea1, int yIndexEnemy) {
        int XTile=(int)solidArea.x/Tile.TILE_HEIGHT;
        int XTile1=(int)solidArea1.x/Tile.TILE_HEIGHT;
        if(XTile>XTile1)
        {
            for(int i=0;i<XTile-XTile1;++i)
            {
                if(IsTileSolid(XTile1+i,yIndexEnemy,map))
                    return false;
                if(!IsTileSolid(XTile1+i,yIndexEnemy+1,map))
                    return false;
            }
        }
        else {
            for(int i=0;i<XTile1-XTile;++i)
            {
                if(IsTileSolid(XTile+i,yIndexEnemy,map))
                    return false;
                if(!IsTileSolid(XTile+i,yIndexEnemy+1,map))
                    return false;
            }
        }
        return true;
    }

}
