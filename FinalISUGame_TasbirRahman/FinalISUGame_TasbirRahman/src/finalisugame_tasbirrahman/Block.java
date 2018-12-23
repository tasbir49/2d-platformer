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
public class Block extends Tile
/******************************************

*Name: Tasbir Rahman

*Date: June 14, 2016

*Class Name: Block

*Description: Objects that take up a tile space and can't be collided with

********************************************/
{
    Block(){}
    double x;
    double y;
    boolean leftEnabled;//if edges should be considered in collision
    boolean rightEnabled;
    boolean topEnabled;
    boolean bottomEnabled;
    Block(BufferedImage tileImg, int x, int y)
            /******************************************

*Name: Tasbir Rahman

*Date: June 14 2016

*Function Name: Constructor

*Description: creates instance of block

*Inputs:image of block, x coord in tiles, y coord in tiles

*Outputs: none

********************************************/
    {
        super( tileImg,  x,  y);
        this.x = x*32;
        this.y = y*32;
        leftEnabled = true;
        rightEnabled = true;
        topEnabled = true;
        bottomEnabled = true;
    } 
    double getXAsDouble()
            /******************************************

*Name: Tasbir Rahman

*Date: June 14 2016

*Function Name: getXAsDouble

*Description: returns x value in terms of pixels as a double

*Inputs: none

*Outputs: x as double

********************************************/
    {
        return x;
    }
    double getYAsDouble()
                        /******************************************

*Name: Tasbir Rahman

*Date: June 14 2016

*Function Name: getYasDouble

*Description: returns Y value in terms of pixels as a double

*Inputs: none

*Outputs: y as double

********************************************/
    {
        return y;
    }
    boolean isLeftEnabled()
                /******************************************

*Name: Tasbir Rahman

*Date: June 14 2016

*Function Name: isLeftEnabled

*Description: returns leftEnabled field

*Inputs: none

*Outputs: leftEnabled as boolean

********************************************/
    {
        return leftEnabled;
    }
    boolean isRightEnabled()
                 /******************************************

*Name: Tasbir Rahman

*Date: June 14 2016

*Function Name: isRightEnabled

*Description: returns leftEnabled field

*Inputs: none

*Outputs: rightEnabled as boolean

********************************************/
    {
        return rightEnabled;
    }
    boolean isBottomEnabled()
                 /******************************************

*Name: Tasbir Rahman

*Date: June 14 2016

*Function Name: isBottomEnabled

*Description: returns bottomEnabled field

*Inputs: none

*Outputs: bottomEnabled as boolean

********************************************/
    {
        return bottomEnabled;
    }
    boolean isTopEnabled()
                 /******************************************

*Name: Tasbir Rahman

*Date: June 14 2016

*Function Name: isTopEnabled

*Description: returns topEnabled field

*Inputs: none

*Outputs: topEnabled as boolean

********************************************/
    {
        return topEnabled;
    }
    void disableLeft()
                 /******************************************

*Name: Tasbir Rahman

*Date: June 14 2016

*Function Name: disableLeft

*Description: sets leftEnabled field to false

*Inputs: none

*Outputs: none

********************************************/
    {
        leftEnabled = false;
    }
    void disableRight()
                    /******************************************

*Name: Tasbir Rahman

*Date: June 14 2016

*Function Name: disableLeft

*Description: sets rightEnabled field to false

*Inputs: none

*Outputs: none

********************************************/
    {
        rightEnabled = false;
    }
    void disableBottom()
                    /******************************************

*Name: Tasbir Rahman

*Date: June 14 2016

*Function Name: disableLeft

*Description: sets botttomEnabled field to false

*Inputs: none

*Outputs: none

********************************************/
    {
        bottomEnabled = false;
    }
    void disableTop()
                    /******************************************

*Name: Tasbir Rahman

*Date: June 14 2016

*Function Name: disableLeft

*Description: sets topEnabled field to false

*Inputs: none

*Outputs: none

********************************************/
    {
        topEnabled = false;
    }
    
}
