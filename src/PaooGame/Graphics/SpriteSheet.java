package PaooGame.Graphics;

import PaooGame.Exceptions.IncorectPathException;
import PaooGame.Exceptions.IndexCropException;

import java.awt.image.BufferedImage;

/*! \class public class SpriteSheet
    \brief Clasa retine o referinta catre o imagine formata din dale (sprite sheet)

    Metoda crop() returneaza o dala de dimensiuni fixe (o subimagine) din sprite sheet
    de la adresa (x * latimeDala, y * inaltimeDala)
 */
public class SpriteSheet
{
    private BufferedImage       spriteSheet;              /*!< Referinta catre obiectul BufferedImage ce contine sprite sheet-ul.*/
    private static final int    tileWidth   = 16;   /*!< Latimea unei dale din sprite sheet.*/
    private static final int    tileHeight  = 16;   /*!< Inaltime unei dale din sprite sheet.*/

    /*! \fn public SpriteSheet(BufferedImage sheet)
        \brief Constructor, initializeaza spriteSheet.

        \param img Un obiect BufferedImage valid.
     */
    public SpriteSheet(BufferedImage buffImg)    {
        /// Retine referinta catre BufferedImage object.
        spriteSheet = buffImg;
    }

    /*! \fn public BufferedImage crop(int x, int y)
        \brief Returneaza un obiect BufferedImage ce contine o subimage (dala).

        Subimaginea este localizata avand ca referinta punctul din stanga sus.

        \param x numarul dalei din sprite sheet pe axa x.
        \param y numarul dalei din sprite sheet pe axa y.
     */
    public BufferedImage crop(int x, int y)throws IndexCropException
    {
        if(spriteSheet.getWidth()<(x*tileWidth) || spriteSheet.getHeight()<(y*tileHeight))
            throw new IndexCropException("Ati depasit dimensiunea imaginii!!");
        else
            return spriteSheet.getSubimage(x * tileWidth, y * tileHeight, tileWidth, tileHeight);

    }
    public BufferedImage crop1(int x, int y,int Width, int Height)throws IndexCropException
    {
        if(spriteSheet.getWidth()<(x*Width) || spriteSheet.getHeight()<(y*Height))
            throw new IndexCropException("Ati depasit dimensiunea imaginii!!");
        else
            return spriteSheet.getSubimage(x * Width, y * Height,Width,Height);
    }

    public BufferedImage crop2(int x, int y,int Width, int Height)throws IndexCropException
    {
        if(x>spriteSheet.getWidth() || y>spriteSheet.getHeight())
            throw new IndexCropException("Ati depasit dimensiunea imaginii!!");
        else
            return spriteSheet.getSubimage(x, y ,Width,Height);

    }
}
