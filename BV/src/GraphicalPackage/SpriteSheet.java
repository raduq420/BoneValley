package GraphicalPackage;

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
    public SpriteSheet(BufferedImage buffImg)
    {
        /// Retine referinta catre BufferedImage object.
        spriteSheet = buffImg;
    }

    /*! \fn public BufferedImage crop(int x, int y)
        \brief Returneaza un obiect BufferedImage ce contine o subimage (dala).

        Subimaginea este localizata avand ca referinta punctul din stanga sus.
     */
    public BufferedImage crop(int x, int y)
    {
        return spriteSheet.getSubimage(x * tileWidth+x, y * tileHeight + y, tileWidth, tileHeight);
    }
    public BufferedImage crop(int x, int y, int width, int height, int spaceX, int spaceY)
    {
        return spriteSheet.getSubimage(y * width + spaceX*y, x * height +spaceY*x, width, height);
    }
    public BufferedImage crop(int x, int y, int width, int height)
    {
        return spriteSheet.getSubimage(x, y, width, height);
    }
}
