/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalisugame_tasbirrahman;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 *
 * @author TASBIR
 */
public class Player extends Entity
/******************************************

*Name: Tasbir Rahman

*Date: June 14, 2016

*Class Name: Player

*Description: User controls an instance of this class

********************************************/
{

    Animation walkingAnim = new Animation("sprites/mario/big/walk_Anim03", true, 6);
    BufferedImage slideImg = ExtraFunct.getImage("sprites/mario/big/slide.png");
    boolean accelerateButtonPressed;//When shift is held the player accelerates an extra amount i.i. mario
    boolean moveButtonPressed;
    double currentHorizontalTargetVelocity;
    double walkTargetVelocity;//needs to be tracked in case accelerateButtonPressed is true
    boolean jumpButtonPressed;
    boolean bouncing;
    int healthPoints;
    double maxWalkSpeed;
    double maxDashSpeed;
   
    double jumpDelay;
     
void bounce()
        /******************************************

*Name: Tasbir Rahman

*Date: June 14 2016

*Function Name:isInAir

*Description: says if in air or not
*Inputs: nnone

*Outputs: inAir as boolean

********************************************/
{
          try {
         // Open an audio input stream. to play bounce sfx
         BufferedInputStream bsr = new BufferedInputStream(this.getClass().getResourceAsStream("sounds/mario/marioBounce.wav"));//for JARfile
         AudioInputStream audioIn = AudioSystem.getAudioInputStream(bsr);
         // Get a sound clip resource.
         Clip clip = AudioSystem.getClip();
         // Open audio clip and load samples from the audio input stream.
         clip.open(audioIn);
        FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        clip.start();
        
      } catch (UnsupportedAudioFileException e) {
         e.printStackTrace();
      } catch (IOException e) {
         e.printStackTrace();
      } catch (LineUnavailableException e) {
         e.printStackTrace();
      }

  
    this.bouncing = true;
    this.currentHorizontalVelocity = 0;
    this.currentHorizontalTargetVelocity = 0;
    this.currentVerticalVelocity = -18;
}
boolean isInAir()
                     /******************************************

*Name: Tasbir Rahman

*Date: June 14 2016

*Function Name:isInAir

*Description: says if in air or not
*Inputs: nnone

*Outputs: inAir as boolean

********************************************/
{
    return inAir;
}
    void stopJump()
             /******************************************

*Name: Tasbir Rahman

*Date: June 14 2016

*Function Name:stopJump

*Description: sets jumping to false
*Inputs: none

*Outputs: none

********************************************/
    {
        this.jumpButtonPressed = false;
    }
    
    void startMove(boolean left)
                       /******************************************

*Name: Tasbir Rahman

*Date: June 14 2016

*Function Name:startMove

*Description: enables moving boolean and maybe left Boolean if enabled and changes animation accordingly
*Inputs: left Boolean

*Outputs: none

********************************************/
    {
       
            

            this.moveButtonPressed = true;
            if(left)
            {
                
                
                this.directionIsLeft = true;
            }
            else
            {
                
                this.directionIsLeft = false;

            }
        
        
        
    }
    void stopMove(boolean left)
                       /******************************************

*Name: Tasbir Rahman

*Date: June 14 2016

*Function Name:stopMove

*Description: sets moving to false and changes animation and images accordingly
*Inputs: if its left

*Outputs: none

********************************************/
    {
        if(!this.inAir)
        {
            if(left && this.directionIsLeft ){
                this.changeAnimation(null);
                this.walkTargetVelocity = 0;
                this.currentHorizontalTargetVelocity = 0;
            

            }
            else if(!left && ! this.directionIsLeft ){
                this.changeAnimation(null);
                this.walkTargetVelocity = 0;
                this.currentHorizontalTargetVelocity = 0;
            

            }
        }
                        this.moveButtonPressed = false;

    }

    void accelerateToHorizontalVelocity(double target)
               /******************************************

*Name: Tasbir Rahman

*Date: June 14 2016

*Function Name:accelerateToHorizontalVelocity

*Description: every tick, it sets current velocity closer to target
*Inputs: target velocity as double

*Outputs: none

********************************************/
    {
        if(this.currentHorizontalVelocity > target)
        {
            this.accelerationAmt = Math.abs(this.accelerationAmt)*-1;
            if(this.currentHorizontalVelocity + this.accelerationAmt < target)this.currentHorizontalVelocity = target;
            else
            {
                
                this.currentHorizontalVelocity += this.accelerationAmt;
            }
        }
         if(this.currentHorizontalVelocity<target)
        {
            this.accelerationAmt = Math.abs(this.accelerationAmt);
            if(this.currentHorizontalVelocity + this.accelerationAmt > target)this.currentHorizontalVelocity = target;
            else
            {
                this.currentHorizontalVelocity += this.accelerationAmt;
            }
                
        }
    }

    void jump()
                       /******************************************

*Name: Tasbir Rahman

*Date: June 14 2016

*Function Name:stopJump

*Description: sets jumping to true and propells player up if right conditions are met
*Inputs: none

*Outputs: none

********************************************/
    {
        if(!inAir &&!jumpButtonPressed)
        { 
            
            jumpButtonPressed = true;
             try {
         // Open an audio input stream. to play jump sfx
         BufferedInputStream bsr = new BufferedInputStream(this.getClass().getResourceAsStream("sounds/mario/marioJump.wav"));//for JARfile
         AudioInputStream audioIn = AudioSystem.getAudioInputStream(bsr);
         // Get a sound clip resource.
         Clip clip = AudioSystem.getClip();
         // Open audio clip and load samples from the audio input stream.
         clip.open(audioIn);
        FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        clip.start();
        
      } catch (UnsupportedAudioFileException e) {
         e.printStackTrace();
      } catch (IOException e) {
         e.printStackTrace();
      } catch (LineUnavailableException e) {
         e.printStackTrace();
      }

            this.jumpDelay = 0;
            this.inAir = true;
            this.currentVerticalVelocity =  -18- Math.abs(this.currentHorizontalVelocity)/2;
            this.maxFallSpeed = 20;
            
        }
    }
    
   
    void update()
             /******************************************

*Name: Tasbir Rahman

*Date: June 14 2016

*Function Name:update

*Description: handles what happens each timer tick
*Inputs: none

*Outputs: none

********************************************/
            
    {
 
        if(this.alive){
            //walkspeed animation depends on walk speed
            if(Math.abs(this.currentHorizontalVelocity) > this.maxDashSpeed - 0.1)this.walkingAnim.delay = 1;
            else if(Math.abs(this.currentHorizontalVelocity)> this.maxWalkSpeed - 0.1)this.walkingAnim.delay =4;

            else if(Math.abs(this.currentHorizontalVelocity) > 0) this.walkingAnim.delay = 8;
            //Handle Images when Animating
            if((this.currentAnim == null)&& (this.currentImg == null || this.currentImg != this.slideImg) )
            {
                this.currentImg = this.defaultImg;
            }
            else if(this.currentAnim != null)
            {
                this.currentAnim.update();
                if(this.currentAnim.getCurrentFrame() != null)this.currentImg = this.currentAnim.getCurrentFrame();
                else{this.currentImg = this.defaultImg;}
            }

            //Handle When movement button is being pressed
            if(this.moveButtonPressed && !(inAir))//pressaed and on ground
            {
                            this.currentVerticalVelocity = 1;//Gravity and floor collision need it to be non 0 so every cycle it clips through floor and moves back up

                if(this.directionIsLeft)this.walkTargetVelocity = Math.abs(this.maxWalkSpeed) * -1;
                else if(!this.directionIsLeft)this.walkTargetVelocity = Math.abs(this.maxWalkSpeed);
                if(this.accelerateButtonPressed)
                {
                    if(this.walkTargetVelocity < 0)this.currentHorizontalTargetVelocity = Math.abs(this.maxDashSpeed) * -1;
                    else if(this.walkTargetVelocity > 0) this.currentHorizontalTargetVelocity = Math.abs(this.maxDashSpeed);
                }
                else 
                {
                    this.currentHorizontalTargetVelocity = this.walkTargetVelocity;   
                }
                if(!ExtraFunct.isSameSign(this.currentHorizontalTargetVelocity, this.currentHorizontalVelocity)//if he should slide 
                        && this.currentHorizontalVelocity != 0)//prevents flickering when starting to move
                {
                    this.changeAnimation(null);
                    this.currentImg = this.slideImg;

                }
                else if(currentAnim != walkingAnim && !(inAir))
                {
                changeAnimation(walkingAnim);
                currentAnim.play();
                }
                this.accelerationAmt =0.406250*2;//so sliding has an effect

            }
            else if(!(inAir))//if movement not pressed but on ground
            {
                this.currentVerticalVelocity = 1;//Gravity and floor collision need it to be non 0 so every cycle it clips through floor and moves back up
                this.currentHorizontalTargetVelocity = 0;
                if(this.currentHorizontalVelocity == 0)
                {
                    this.changeAnimation(null);
                    this.currentImg = this.defaultImg;
                }
                this.accelerationAmt =0.406250 ;

            }
            else if(this.moveButtonPressed)//in Air and Movement is pressed
            {
                if(this.directionIsLeft && this.currentHorizontalVelocity >=0)this.currentHorizontalTargetVelocity = Math.abs(this.maxWalkSpeed) * -1;
                else if(!this.directionIsLeft && this.currentHorizontalVelocity <= 0)    this.currentHorizontalTargetVelocity = Math.abs(this.maxWalkSpeed);

                this.accelerationAmt=0.406250 ;//mid air acceleration should be slow
            }
            if(inAir)
            {
                if(!jumpButtonPressed && this.currentVerticalVelocity <0&& !this.bouncing)this.currentVerticalVelocity *= -1;//if jump l;et go fall early
                 this.maxFallSpeed = 20;
                this.currentImg = ExtraFunct.getImage("sprites/mario/big/jumping.png");
                this.changeAnimation(null);
                if(this.currentVerticalVelocity>=0)this.bouncing =false;
                this.currentVerticalVelocity += this.gravity;
                if(this.currentVerticalVelocity> this.maxFallSpeed)
                {
                    this.currentVerticalVelocity = this.maxFallSpeed;
                }
            }
        
                
            //move

            this.accelerateToHorizontalVelocity(this.currentHorizontalTargetVelocity);
            this.move();
        }
        else if(active)
        {
            if(this.currentVerticalVelocity < this.maxFallSpeed)this.currentVerticalVelocity += this.gravity;
            this.deathDelay ++;
            if(this.deathDelay > this.maxDeathDelay)this.deactivate();
            this.move();
        }
    
       
    }
   void kill()     /******************************************

*Name: Tasbir Rahman

*Date: June 14 2016

*Function Name: kill

*Description: plays death fx  sets alive to false and then sends player falling off screen after a while, player is deactivated

*Inputs: none

*Outputs: none

********************************************/
   {
         try {
         // Open an audio input stream. to play death sfx
         BufferedInputStream bsr = new BufferedInputStream(this.getClass().getResourceAsStream("sounds/mario/marioDeath.wav"));//for JARfile
         AudioInputStream audioIn = AudioSystem.getAudioInputStream(bsr);
         // Get a sound clip resource.
         Clip clip = AudioSystem.getClip();
         // Open audio clip and load samples from the audio input stream.
         clip.open(audioIn);
        FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        clip.start();

        } catch (UnsupportedAudioFileException e) {
           e.printStackTrace();
        } catch (IOException e) {
           e.printStackTrace();
        } catch (LineUnavailableException e) {
           e.printStackTrace();
        }
        this.alive = false;
        this.canTileCollide = false;
        this.currentImg = ExtraFunct.getImage("sprites/mario/big/ded.png");
        this.currentHorizontalVelocity =0;
        this.currentVerticalVelocity = -15;
    }
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
    Player(int x, int y)
             /******************************************

*Name: Tasbir Rahman

*Date: June 14 2016

*Function Name:constructor

*Description: creates character given x and y coords IN TILES
*Inputs: x and y coords as ints

*Outputs: none

********************************************/
    {
        this.maxDeathDelay = 45;
        this.followsGravity = true;
        this.alive = true;
        this.active=true;
        this.canTileCollide = true;
      this.xImgOffset = -5;
      this.yImgOffset = -6;
        this.currentHorizontalVelocity=0;
        defaultImg = ExtraFunct.getImage("sprites/mario/big/idle.png");
                this.accelerationAmt = 0.203125;
        this.x = x*Tile.getWidth();
        this.y = y * Tile.getHeight();
        this.height = 58;
        this.width = 23;
        this.directionIsLeft = false;
        this.maxWalkSpeed = 4;
        this.maxDashSpeed = 10;
        currentImg = defaultImg;
        currentAnim =  null;

        inAir = true;
    }
}
