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
  abstract class Entity
  /******************************************

*Name: Tasbir Rahman

*Date: June 14, 2016

*Class Name: Entity

*Description: all objects that move around and can interact with the player or be the player are entities

********************************************/
  {
    protected int deathDelay=0;//how long after dying before despanwing for enemies and players
    protected int maxDeathDelay = 1;
    protected boolean followsGravity;
    protected Animation currentAnim;
    protected boolean canTileCollide;
    protected BufferedImage currentImg;
    protected boolean cliffDetect;//for enemies and certain items if they are implemented yet
    protected boolean alive;
    protected boolean active;
    protected BufferedImage defaultImg;    //if entity has animation, then when no animatiuon is playing this image is shown
    protected boolean directionIsLeft;
    protected boolean inAir;  
    protected boolean inScreen;
    protected double currentHorizontalVelocity = 0;
    protected double currentVerticalVelocity = 0;
    protected double maxFallSpeed =20;
    protected final double  gravity = 1.5;
    protected double accelerationAmt;
    protected int xImgOffset;//gives location of x coord of image in relation to collision box
    protected int yImgOffset;//gives location of y coord of image in relation to  collision box
    protected double x;
    protected double y;
    protected double height;
    protected double width;
    void setX(double x)
               /******************************************

*Name: Tasbir Rahman

*Date: June 14 2016

*Function Name: setX

*Description: changes x vaule to imput

*Inputs: changed x val as double

*Outputs: none

********************************************/
    {
        this.x = x;
    }
    void setY(double y)
     /******************************************

*Name: Tasbir Rahman

*Date: June 14 2016

*Function Name: setY

*Description: changes y vaule to imput

*Inputs: changed y val as double

*Outputs: none

********************************************/       
    {
        this.y = y;
    }
    void switchDirection()
            /******************************************

*Name: Tasbir Rahman

*Date: June 14 2016

*Function Name: switchDirection

*Description: changes directionIsLeft field to opposite
*Inputs: none

*Outputs: none

********************************************/
    {
        this.directionIsLeft = !this.directionIsLeft;

    }
    BufferedImage getImg()
                    /******************************************

*Name: Tasbir Rahman

*Date: June 14 2016

*Function Name: getImg

*Description: returns currentImg field
*Inputs: none

*Outputs: currentImg as BufferedImage

********************************************/
    {
        return this.currentImg;
    }
    double getWidth()
              /******************************************

*Name: Tasbir Rahman

*Date: June 14 2016

*Function Name: getWidth

*Description: returns width field
*Inputs: none

*Outputs: width as double

********************************************/
    {
        return width;
    }
    double getHeight()
              /******************************************

*Name: Tasbir Rahman

*Date: June 14 2016

*Function Name: getHeight

*Description: returns height  field
*Inputs: none

*Outputs: height as double

********************************************/
    {
        return height;
    }
    int getImgX()
             /******************************************

*Name: Tasbir Rahman

*Date: June 14 2016

*Function Name: getImgX

*Description: returns x coord of image, considering direction and direction
*Inputs: none

*Outputs: int

********************************************/
    {
        if(!this.directionIsLeft)return (int)this.x + this.xImgOffset;
        else{return (int)this.x + this.xImgOffset + this.currentImg.getWidth();}
    }
    int getImgY()
              /******************************************

*Name: Tasbir Rahman

*Date: June 14 2016

*Function Name: getImgX

*Description: returns y coord of image, 
*Inputs: none

*Outputs: int

********************************************/
    {
        return (int)this.y + this.yImgOffset;
    }
    int getImgHeight()
              /******************************************

*Name: Tasbir Rahman

*Date: June 14 2016

*Function Name: getImgX

*Description: returns height of image, 
*Inputs: none

*Outputs: int

********************************************/
    {
        return this.currentImg.getHeight();
    }
    int getImgWidth()
              /******************************************

*Name: Tasbir Rahman

*Date: June 14 2016

*Function Name: getImgX

*Description: returns wifth of image, considering direction and direction
*Inputs: none

*Outputs: int

********************************************/
    {
        if(!this.directionIsLeft) return this.currentImg.getWidth();
        else{ return -1 * this.currentImg.getWidth();}
    }
    double getX()
                          /******************************************

*Name: Tasbir Rahman

*Date: June 14 2016

*Function Name: getX

*Description: returns x  field
*Inputs: none

*Outputs: x as double

********************************************/
    {
        return this.x;
    }
    double getY()
                          /******************************************

*Name: Tasbir Rahman

*Date: June 14 2016

*Function Name: getHeight

*Description: returns Y  field
*Inputs: none

*Outputs: y as double

********************************************/
    {
        return this.y;
    }
    abstract void update();
      /******************************************

*Name: Tasbir Rahman

*Date: June 14 2016

*Function Name: update

*Description: abstract method for each sublcass behaviour per tick
*Inputs: none

*Outputs: none

********************************************/
    void changeAnimation(Animation input)
              /******************************************

*Name: Tasbir Rahman

*Date: June 14 2016

*Function Name: changeAnimation

*Description: changes current animation
*Inputs: animation

*Outputs: none

********************************************/
    {
        if(currentAnim != null) currentAnim.stop();
        currentAnim = input;
    }
    void setInAir(boolean isInAir)
              /******************************************

*Name: Tasbir Rahman

*Date: June 14 2016

*Function Name: setInAir

*Description: sets inAir boolean
*Inputs: boolean

*Outputs: none

********************************************/
    {
        this.inAir = isInAir;
    }
    double getVerticalVel()
                          /******************************************

*Name: Tasbir Rahman

*Date: June 14 2016

*Function Name: getVerticalVel

*Description: returns currentVerticalVelocity  field
*Inputs: none

*Outputs: height as double

********************************************/
    {
        return this.currentVerticalVelocity;
    }
    double getHorizontVel()
             /******************************************

*Name: Tasbir Rahman

*Date: June 14 2016

*Function Name: getVerticalVel

*Description: returns currentHorizontalVelocity  field
*Inputs: none

*Outputs: height as double

********************************************/
    {
        return this.currentHorizontalVelocity;
    }
    void move()
              /******************************************

*Name: Tasbir Rahman

*Date: June 14 2016

*Function Name: moev

*Description: changes x and y fields according to velocitiy variables every update cycle
*Inputs: none

*Outputs: none

********************************************/
    {
           this.x += this.currentHorizontalVelocity;
           this.y += this.currentVerticalVelocity;
        
    }
    boolean followGravity()
                /******************************************

*Name: Tasbir Rahman

*Date: June 14 2016

*Function Name: followGravity

*Description: returns boolean of followsGravity
*Inputs: none

*Outputs: followsGravity as boolkean

********************************************/
    {
        return followsGravity;
    }
    boolean canTileCollide()
        /******************************************

*Name: Tasbir Rahman

*Date: June 14 2016

*Function Name: canTileCollide

*Description: returns boolean of canTileCollide
*Inputs: none

*Outputs: canTileCollide as boolkean

********************************************/
    {
    return this.canTileCollide;
    }
 boolean canDetectCliff()
              /******************************************

*Name: Tasbir Rahman

*Date: June 14 2016

*Function Name:canStomp()

*Description: boolean showing if entity can detect cliff and switch direction
*Inputs: n/a

*Outputs: cliffDetect as boolean

********************************************/
    {
        return this.cliffDetect;
    }
 boolean isActive()
                  /******************************************

*Name: Tasbir Rahman

*Date: June 14 2016

*Function Name:isActive

*Description: if active or not
*Inputs: none

*Outputs: if active as boolean

********************************************/
    {
        return active;
    }
 void deactivate()
                  /******************************************

*Name: Tasbir Rahman

*Date: June 14 2016

*Function Name:deactivate

*Description: sets active to false;
*Inputs: none
*Outputs: none

********************************************/
    {
     this.active = false;   
    }
 boolean isInScreen()
         /******************************************

*Name: Tasbir Rahman

*Date: June 14 2016

*Function Name: isInScreen

*Description: returns if in screen or not with variable

*Inputs: none

*Outputs: inScreen boolean

********************************************/
 {
     return inScreen;
 }
 void changeInScreen(boolean change)
                 /******************************************

*Name: Tasbir Rahman

*Date: June 14 2016

*Function Name: isInScreen

*Description: sets inScreen variable

*Inputs: boolean

*Outputs: none

********************************************/
 {
   inScreen = change;
 }
abstract void kill();
        /******************************************

*Name: Tasbir Rahman

*Date: June 14 2016

*Function Name: kill

*Description: living things are entities that can die

*Inputs: none

*Outputs: none

********************************************/
 boolean isAlive()
              /******************************************

*Name: Tasbir Rahman

*Date: June 14 2016

*Function Name:isAlive

*Description: says if alive or not
*Inputs: none

*Outputs: boolean alive

********************************************/
   {
       return alive;
   }
 void bounce()
        /******************************************

*Name: Tasbir Rahman

*Date: June 14 2016

*Function Name:isInAir

*Description: says if in air or not
*Inputs: nnone

*Outputs: none

********************************************/
{
    this.currentHorizontalVelocity = 0;
    this.currentVerticalVelocity = -18;
}
   
}
