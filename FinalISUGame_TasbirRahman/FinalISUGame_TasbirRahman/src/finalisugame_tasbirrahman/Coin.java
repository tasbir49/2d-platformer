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
public class Coin extends Entity {
    /******************************************

*Name: Tasbir Rahman

*Date: June 14, 2016

*Class Name: Coin

*Description: playuer must collect all of these in the level to win

********************************************/
    void update()
                      /******************************************

*Name: Tasbir Rahman

*Date: June 14 2016

*Function Name: update

*Description: does nothing for this, needed due to being abstract

*Inputs: none

*Outputs: none

********************************************/
    {
    }
    void kill()//for collecting
            /******************************************

*Name: Tasbir Rahman

*Date: June 14 2016

*Function Name: kill

*Description: either sets up a timer and sets alive to false, after timert done, active becomes false, or it just sets active to falsem for here its the latter but plays a jingle before deacticivatign

*Inputs: none

*Outputs: none

********************************************/
    {
        try {
         // Open an audio input stream. to play bounce sfx
         BufferedInputStream bsr = new BufferedInputStream(this.getClass().getResourceAsStream("sounds/coin.wav"));//for JARfile
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

        this.deactivate();
    }
    Coin(int x, int y)
            /******************************************

*Name: Tasbir Rahman

*Date: June 14 2016

*Function Name: constructor

*Description: creates a coin instance

*Inputs: none

*Outputs: none

********************************************/
    {
        this.directionIsLeft = false;
        this.x = x*Tile.getWidth();
        this.y = y*Tile.getHeight();
        this.active = true;
        this.currentImg = ExtraFunct.getImage("sprites/coin.png");
        this.xImgOffset = - 8;
        this.yImgOffset = - 2;
        this.width = 16;
        this.height = 28;
        
    }
}
