/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalisugame_tasbirrahman;

import java.awt.image.BufferedImage;
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
public class Enemy extends Entity {
    boolean canStomp;
    BufferedImage stompImg;
    
    double initialX;//for Fire motion is axis aligned straightline, line length is a certain distance from initial
    double initialY;//for fire ^
    double offSet;
    EnemyTypes type;
    public enum EnemyTypes {GOOMBA, KOOPA, SPINY, FIRE}
    Enemy(EnemyTypes type, double vertSpeed,double horiSpeed,int  x, int y)
              /******************************************

*Name: Tasbir Rahman

*Date: June 14 2016

*Function Name:constructor

*Description: creates enmey instance
*Inputs: x,y coords as int in tiles, speeds as double, type of enemy as enum

*Outputs: canStomp as boolean

********************************************/
    {
        this.alive = true;
        this.active = true;
        this.stompImg = ExtraFunct.getImage("sprites/null.png");
        this.maxFallSpeed = 20;
        this.canTileCollide = true;
        this.followsGravity = true;
        this.canStomp = true;
        this.inAir = true;
        this.canStomp = true;
        this.type = type;
        if(type == EnemyTypes.GOOMBA)
        {
            this.currentAnim = new Animation("sprites/goomba/walk_Anim02", true, 10);
            this.defaultImg = ExtraFunct.getImage("sprites/goomba/walk_Anim02/(1).png");
            this.stompImg = ExtraFunct.getImage("sprites/goomba/death.png");
            this.xImgOffset = -3;
            this.yImgOffset = -8;
            this.width = 25;
            this.maxDeathDelay = 30;
            this.cliffDetect = false;
            this.height = 24;
        }
        else if(type == EnemyTypes.KOOPA)
        {
            this.currentAnim = new Animation("sprites/koopa/walk_Anim02", true, 10);
            this.defaultImg = ExtraFunct.getImage("sprites/koopa/walk_Anim02/(1).png");
            this.width = 29;
            this.maxDeathDelay = 1;
            this.height = 32;
            this.cliffDetect = true;
            this.xImgOffset = -0;
            this.yImgOffset = -31;
        }
        else if(type == EnemyTypes.SPINY)
        {
            this.canStomp = false;
            this.currentAnim = new Animation("sprites/spiny/walk_Anim02", true, 10);
            this.stompImg = ExtraFunct.getImage("sprites/shell.png");
            this.defaultImg = ExtraFunct.getImage("sprites/spiny/walk_Anim02/(1).png");
            this.width = 23;
            this.height = 24;
            this.cliffDetect = true;
            this.xImgOffset = -6;
            this.yImgOffset = -7;
        }
        else if(type == EnemyTypes.FIRE)
        {
            this.offSet = Tile.tileHeightSize * 7;
            this.canStomp = false;
            this.canTileCollide = false;
            this.followsGravity = false;
            this.currentAnim = null;
            this.defaultImg = ExtraFunct.getImage("sprites/fire/default.png");
            this.width = 24;
            this.height = 24;
            this.cliffDetect = true;
            this.xImgOffset = -4;
            this.yImgOffset = -4;
        }
        this.currentImg = this.defaultImg;
        this.directionIsLeft = false;
        this.x = x*Tile.getWidth();

        this.y = y*Tile.getHeight();
                this.initialX = this.x;
        this.initialY = this.y;
        this.currentVerticalVelocity = vertSpeed;
        this.currentHorizontalVelocity = horiSpeed;
    }
    void update()
         /******************************************

*Name: Tasbir Rahman

*Date: June 14 2016

*Function Name:update

*Description: updats current position and status of enemy instance
*Inputs: nnone

*Outputs: none

********************************************/
    {
        if(alive)
        {

            if(this.currentImg == null)this.currentImg = this.defaultImg;
            else if(this.currentAnim != null){

                if(!this.currentAnim.isPlaying())currentAnim.play();
                this.currentAnim.update();
                this.currentImg = this.currentAnim.getCurrentFrame();
            }
            if(!inAir && followsGravity)
            {
                this.currentVerticalVelocity = 2; //Gravity and floor collision need it to be non 0 so every cycle it clips through floor and moves back up
            }
            else if(inAir && followsGravity)
            {
             this.currentVerticalVelocity += this.gravity;
             if(this.currentHorizontalVelocity > this.maxFallSpeed)this.currentHorizontalVelocity = this.maxFallSpeed;
            }
            if(directionIsLeft)
            {
                this.currentHorizontalVelocity = -1 * Math.abs(this.currentHorizontalVelocity);
            }
            else
            {
                this.currentHorizontalVelocity =  Math.abs(this.currentHorizontalVelocity);
            }
            if(!followsGravity)
            {
                if((this.x -this.initialX)*(this.x-this.initialX)+(this.initialY-this.y)*(this.initialY-this.y)> this.offSet*this.offSet)//measure distance between current and initial, if initial > offset , swithc
                {
                    this.switchDirection();
                    this.currentHorizontalVelocity*=-1;
                    this.currentVerticalVelocity*= -1;
                }
            }
            this.move();

        }
        else if(this.active)
        {
            this.deathDelay++;
            this.currentVerticalVelocity += this.gravity;
             if(this.currentHorizontalVelocity > this.maxFallSpeed)this.currentHorizontalVelocity = this.maxFallSpeed;
            this.move();
            if(this.deathDelay >this.maxDeathDelay)this.deactivate();
        }
            
    }
    

    boolean canStomp()
                  /******************************************

*Name: Tasbir Rahman

*Date: June 14 2016

*Function Name:canStomp()

*Description: boolean showing if player can jump on it to kill it
*Inputs: n/a

*Outputs: canStomp as boolean

********************************************/
    {
        return canStomp;
    }
   
    void kill()
                /******************************************

*Name: Tasbir Rahman

*Date: June 14 2016

*Function Name: kill

*Description: either sets up a timer and sets alive to false, after timert done, active becomes false, or it just sets active to falsem for here its the former
* but calls a overloaded case to see if the enemy was killed by jumping or not

*Inputs: none

*Outputs: none

********************************************/
    {
        kill(false);
    }
    EnemyTypes getType()
                /******************************************

*Name: Tasbir Rahman

*Date: June 14 2016

*Function Name: getType

*Description: returns type of enemy

*Inputs: none

*Outputs: type of enemy enumeration

********************************************/
    {
        return this.type;
    }
    void kill(boolean stompDeath)
                /******************************************

*Name: Tasbir Rahman

*Date: June 14 2016

*Function Name: kill

*Description: either sets up a timer and sets alive to false, after timert done, active becomes false, or it just sets active to falsem for here its the latter 
* but uses an extra variable to see if a kill was through jumping on it and acts according ly

*Inputs: if jumped on as boolean

*Outputs: none

********************************************/
    {
        this.deathDelay = 0;
        this.alive = false;
        this.currentHorizontalVelocity = 0;
        this.currentVerticalVelocity = 0;
        if(stompDeath)this.currentImg = this.stompImg;
        else if(!followsGravity)this.currentImg = ExtraFunct.getImage("Sprites/null.png");
        else
        {
            try {
         // Open an audio input stream. to play kick sfx
         BufferedInputStream bsr = new BufferedInputStream(this.getClass().getResourceAsStream("sounds/enemies/normalDeath.wav"));//for JARfile
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
            this.currentImg = this.defaultImg;
            this.canTileCollide = false;
            this.currentVerticalVelocity = -15;
        }
        
    }
}
