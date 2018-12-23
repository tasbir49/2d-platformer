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
public class SpriteSheetLoader
/******************************************

*Name: Tasbir Rahman

*Date: June 14, 2016

*Class Name: SpriteSheetLoader

*Description: loads images from a sprite sheet given tile size and coordinates

********************************************/
{
    BufferedImage sheet;
    int xSize;
    int ySize;
    BufferedImage getSprite(int x, int y)
             /******************************************

*Name: Tasbir Rahman

*Date: June 14 2016

*Function Name:getSprite

*Description: returns image of sprite given x and y coords in tiles
*Inputs: x and y as int

*Outputs: BufferedImage of sprite

********************************************/
    {
        return sheet.getSubimage(x*xSize, y*ySize, xSize, ySize);
    }
    SpriteSheetLoader(String path, int xSize,int ySize)
            /******************************************

*Name: Tasbir Rahman

*Date: June 14 2016

*Function Name:constructor

*Description: creates a sheetloader given tile size and path of sprite sheet
*Inputs: path of sheet as string, size of x and y tiles as int

*Outputs: none

********************************************/
    {
        this.xSize = xSize;
        this.ySize = ySize;
        sheet = ExtraFunct.getImage(path);
    }
    
}
