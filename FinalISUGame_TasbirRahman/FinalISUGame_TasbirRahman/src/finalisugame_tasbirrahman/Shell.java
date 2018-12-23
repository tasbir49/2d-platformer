/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalisugame_tasbirrahman;

import java.io.BufferedInputStream;
import java.io.IOException;
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
public class Shell extends Entity {
    void kill()
                 /******************************************

*Name: Tasbir Rahman

*Date: June 14 2016

*Function Name: kill

*Description: deactivates shell
*Inputs: none

*Outputs: none

********************************************/
    {
        this.deactivate();
    }
    void kick(boolean isLeft)
               /******************************************

*Name: Tasbir Rahman

*Date: June 14 2016

*Function Name: kill

*Description: makes shell move in certain direction
*Inputs: direction as boolean

*Outputs: none

********************************************/
    {
         try {
         // Open an audio input stream. to play kick sfx
         BufferedInputStream bsr = new BufferedInputStream(this.getClass().getResourceAsStream("sounds/shell/shellKick.wav"));//for JARfile
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
        this.directionIsLeft = isLeft;
        this.currentHorizontalVelocity = 10;
        if(isLeft)
        {
            this.currentHorizontalVelocity = -10;
            this.x -= this.getWidth()/2;

        }
        else
        {
                        this.x += this.getWidth()/2;
        }

    }
    void switchDirection()
               /******************************************

*Name: Tasbir Rahman

*Date: June 14 2016

*Function Name: switchDirection

*Description: calls superclass method and plays bump sfx
*Inputs: none

*Outputs: none

********************************************/
    {
        super.switchDirection();
          try {
         // Open an audio input stream. to play bump sfx
         BufferedInputStream bsr = new BufferedInputStream(this.getClass().getResourceAsStream("sounds/shell/shellBump.wav"));//for JARfile
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
    }
    void update()
               /******************************************

*Name: Tasbir Rahman

*Date: June 14 2016

*Function Name: updates

*Description: updates shell status and position every tick
*Inputs: none

*Outputs: none

********************************************/
    {
        if(active)
        {
            if(this.currentVerticalVelocity < this.maxFallSpeed)this.currentVerticalVelocity+=this.gravity;
            if(this.directionIsLeft)
            {
                this.currentHorizontalVelocity = Math.abs(this.currentHorizontalVelocity)*-1;
            }
        
            else
            {
            this.currentHorizontalVelocity = Math.abs(this.currentHorizontalVelocity);
            }
        this.move();
        }
        
    }
    Shell(double x, double y)
               /******************************************

*Name: Tasbir Rahman

*Date: June 14 2016

*Function Name: conmstructor

*Description: creates shell object at specified x and y positions
*Inputs: x and y

*Outputs: none

********************************************/
    {
        this.alive = true;
        this.canTileCollide = true;
        this.active = true;
        this.x = x;
        this.y = y;
        this.xImgOffset =0;
        this.yImgOffset = -30;
        this.width =32;
        this.height = 32;
        this.currentHorizontalVelocity=0;
        this.defaultImg = ExtraFunct.getImage("sprites/shell.png");
        this.currentImg = this.defaultImg;
        this.directionIsLeft = false;
        this.currentVerticalVelocity= 0;
    }
    void stop()
               /******************************************

*Name: Tasbir Rahman

*Date: June 14 2016

*Function Name: stop

*Description: stops shell from moving
*Inputs: none

*Outputs: none

********************************************/
    {
        this.currentHorizontalVelocity = 0;
    }
    boolean movingHorizontally()
               /******************************************

*Name: Tasbir Rahman

*Date: June 14 2016

*Function Name: kill

*Description: states if shell is moving horizontally
*Inputs: none

*Outputs: bloolean saying if its moving

********************************************/
    {
        return this.currentHorizontalVelocity != 0;
    }
}
