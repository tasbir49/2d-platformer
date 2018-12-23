/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalisugame_tasbirrahman;

import java.awt.image.BufferedImage;

/**
 *
 * @author TASBIR
 */
public class Tile
/******************************************

*Name: Tasbir Rahman

*Date: June 14, 2016

*Class Name: Tile

*Description: Game world is made up using repeated images from this class

********************************************/
{
    static int tileWidthSize = 32;
    static int tileHeightSize = 32;

    BufferedImage tileImg;
    boolean canCollide;
    int tileX;
    int tileY;
    Tile()
    {}
    static int getWidth()
               /******************************************

*Name: Tasbir Rahman

*Date: June 14 2016

*Function Name: getWidth

*Description: returns width of all tiles
*Inputs: none

*Outputs: integer width

********************************************/
    {
        return tileWidthSize;
    }
    static int getHeight()
                /******************************************

*Name: Tasbir Rahman

*Date: June 14 2016

*Function Name: getHeight

*Description: returns height of all tiles
*Inputs: none

*Outputs: integer height

********************************************/
    {
        return tileHeightSize;
    }
    int getX()
                /******************************************

*Name: Tasbir Rahman

*Date: June 14 2016

*Function Name: getX

*Description: returns tile x position in pixels
*Inputs: none

*Outputs: integer x

********************************************/
    {
        return tileX * Tile.tileWidthSize;
    }
    int getY()
               /******************************************

*Name: Tasbir Rahman

*Date: June 14 2016

*Function Name: getX

*Description: returns tilet position in pixels
*Inputs: none

*Outputs: integer t

********************************************/
    {
        return tileY * Tile.tileHeightSize;
    }

    BufferedImage getImg()
               /******************************************

*Name: Tasbir Rahman

*Date: June 14 2016

*Function Name: getImg()

*Description: returns tiles image
*Inputs: none

*Outputs: buffered img 

********************************************/
    {
        return tileImg;
    }
    Tile(BufferedImage tileImg, int x, int y)
               /******************************************

*Name: Tasbir Rahman

*Date: June 14 2016

*Function Name: constructor

*Description: creates tile at specified position with a specific image
*Inputs: none

*Outputs: tileX, tileY asint, image as buffered image

********************************************/
    {
        canCollide = true;   
        this.tileImg = tileImg;
        this.tileX = x;
        this.tileY = y;
    }
}
