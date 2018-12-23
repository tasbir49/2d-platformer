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
public class Animation 
/******************************************

*Name: Tasbir Rahman

*Date: June 14, 2016

*Class Name: Animation

*Description: Controls sprite sheet animation for any game object that can be animated

********************************************/
{
    BufferedImage[] frames;
    BufferedImage currentFrame;
    int count;//Tracks timer ticks
    int delay;
    int frameIndex;
    boolean repeat;
    boolean looping;
    boolean playing;
    void update()
    /******************************************

*Name: Tasbir Rahman

*Date: June 14 2016

*Function Name: Update

*Description: Every timer tick this runs 

*Inputs: none

*Outputs: none

********************************************/
    {
        if( playing )
        {
                    currentFrame = frames[frameIndex];

            if(count > delay)//for frame rate
            {
                if(frameIndex < frames.length -1)
                {
                    frameIndex +=1;
                }
                else if(repeat)
                {
                    frameIndex = 0;
                }
                else
                {
                    stop();
                }
                count = 0;
            }
            count++;
        }

    }
    void stop()
            /******************************************

*Name: Tasbir Rahman

*Date: June 14 2016

*Function Name: Stop

*Description: Sets playing to false, preventing the animation from updating in update method

*Inputs: none

*Outputs: none

********************************************/
    {
        currentFrame = null;
        playing = false;
        count = 0;
        frameIndex = 0;
        
    }
    void play()
            /******************************************

*Name: Tasbir Rahman

*Date: June 14 2016

*Function Name: play

*Description: Sets playing to true, allowing the animation to update

*Inputs: none

*Outputs: none

********************************************/
    {
        if(!playing)
        {
            this.playing = true;
        currentFrame = frames[0];
        this.frameIndex = 0;
        count = 0;}

    }
    boolean isPlaying()
            /******************************************

*Name: Tasbir Rahman

*Date: June 14 2016

*Function Name: isPlaying

*Description: returns playing field

*Inputs: none

*Outputs: playing field - boolean

********************************************/
    {
        return this.playing;
    }
    BufferedImage getCurrentFrame()
            /******************************************

*Name: Tasbir Rahman

*Date: June 14 2016

*Function Name: getCurrentFrame

*Description: returns currentFrame field

*Inputs: none

*Outputs: currentFrame field as BufferedImage

********************************************/
    {
        return currentFrame;
    }
    Animation(String filePath, boolean repeat, int delay) 
            /******************************************

*Name: Tasbir Rahman

*Date: June 14 2016

*Function Name: Constructor

*Description: Standard contructor for this class

*Inputs: where the folder containing frames is, if it repeats, and how long the delay between frames is in ticks

*Outputs: none

********************************************/
    {
        String numFilesStr = filePath.substring(filePath.length() - 2);
        int numFiles = Integer.parseInt(numFilesStr);
        this.frames = new BufferedImage[numFiles];
        for(int x = 0; x < numFiles; x++)
        {
            String finalPath = filePath +"/("+ (x+1) + ").png";
            this.frames[x] = ExtraFunct.getImage(finalPath);
            
            
        }
        currentFrame = null;
        this.repeat = repeat;
        this.delay = delay;
        this.playing= false;
        this.frameIndex = 0;
    }
}

