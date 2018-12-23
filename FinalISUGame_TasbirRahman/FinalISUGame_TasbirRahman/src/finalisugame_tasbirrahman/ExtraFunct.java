/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalisugame_tasbirrahman;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;

/**
 *
 * @author TASBIR
 */
public class ExtraFunct 
/******************************************

*Name: Tasbir Rahman

*Date: June 14, 2016

*Class Name: ExtraFunct

*Description: Extraneuous functions that dont belong to any one class

********************************************/
{
    static boolean RectangleIn(int x, int y, int width, int height, int bigX,int bigY, int bigWidth,int bigHeight)
      /******************************************

*Name: Tasbir Rahman

*Date: June 14 2016

*Function Name: RectangleIn

*Description: checks if one rectangle is in another
*Inputs: x y, width height of inner and outer rectangles as int

*Outputs: boolean showing if one in another

********************************************/
    {
        if(x > bigX &&
           y > bigY &&
           x+width < bigX + bigWidth &&
           y + height < bigY + bigHeight )return true;
        return false;
    }
    static boolean RectangleIntersect(int x1, int y1, int width1, int height1,int x2, int y2, int width2, int height2)
        /******************************************

*Name: Tasbir Rahman

*Date: June 14 2016

*Function Name: RectangleIntersect

*Description: checks if one rectangle touches another
*Inputs: x y, width height  rectangles as int

*Outputs: boolean showing if one in another

********************************************/
    {
        int left1 = x1;
        int top1 = y1;
        int right1 = x1 + width1 - 1;
        int bottom1 = y1 + height1 - 1;
        int left2 = x2;
        int top2 = y2;
        int right2 = x2 + width2 - 1;
        int bottom2 = y2 + height2 - 1;
        if(left1 > right2)return false;
        if(right1 < left2) return false;
        if(top1 > bottom2)return false;
        if(bottom1 < top2)return false;
        return true;
    }
    static boolean RectangleIntersect(double x1, double y1, double width1, double height1,double x2, double y2, double width2, double height2)
     /******************************************

*Name: Tasbir Rahman

*Date: June 14 2016

*Function Name: RectangleIntersect

*Description: checks if one rectangle touches another
*Inputs: x y, width height  rectangles as double

*Outputs: boolean showing if one in another

********************************************/
    {
        return RectangleIntersect((int)x1,(int) y1, (int) width1, (int) height1,(int) x2, (int) y2, (int) width2, (int) height2 );
    }
    static BufferedImage getImage(String filePath)
      /******************************************

*Name: Tasbir Rahman

*Date: June 14 2016

*Function Name: getImage

*Description: retrieves image from filePath as a resource stream
*Inputs: filePath as string

*Outputs; BufferedImage obtained from file

********************************************/
    {
        try{
            InputStream f = ExtraFunct.class.getResourceAsStream(filePath);
            return ImageIO.read(f);
        }
        catch(IOException e)
        {
            System.out.println(e.getMessage());
        }
        return null;
       
        
    }
    static boolean isSameSign(int first, int second)
              /******************************************

*Name: Tasbir Rahman

*Date: June 14 2016

*Function Name: isSameSign

*Description: check if two numbers have same sign
*Inputs: numbers as int

*Outputs: boolean showing if same sign

********************************************/
    {
        if(first > 0 && second >0)return true;
        if(first<0 && second < 0)return true;
        if(first == 0 && second == 0) return true;
        return false;
               
    }
    static boolean isSameSign(double first, double second)
                       /******************************************

*Name: Tasbir Rahman

*Date: June 14 2016

*Function Name: isSameSign

*Description: check if two numbers have same sign
*Inputs: numbers as double

*Outputs: boolean showing if same sign

********************************************/
    {
        if(first > 0 && second >0)return true;
        if(first<0 && second < 0)return true;
        if(first == 0 && second == 0) return true;
        return false;
               
    }
    

}
