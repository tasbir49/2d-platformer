/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalisugame_tasbirrahman;

import finalisugame_tasbirrahman.Enemy.EnemyTypes;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import javax.swing.JPanel;
import java.util.Timer;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class Game extends JPanel 
/******************************************

*Name: Tasbir Rahman

*Date: June 14, 2016

*Class Name: Game

*Description: Main panel where the game is displayed

********************************************/
{

    boolean gridDisplay= false;
    Font nesFont;
    Image offScreenImageCropped;//subimage after scrolling;
    Graphics offScreenGraphicsCropped;
    Image offScreenImage = null;
    BufferedImage winScreen = ExtraFunct.getImage("sprites/win.png");
    BufferedImage pauseScreen =    ExtraFunct.getImage("sprites/pause.png");
    SpriteSheetLoader spriteLoader = new SpriteSheetLoader("sprites/tileset.png",Tile.getWidth(), Tile.getHeight());
    Clip song;

    
    boolean paused = false;
    int screenX;// subimage drawing starts here for scrolling
    int screenY;// subimage drawing starts here
    Graphics offScreenGraphics = null;//for double buffering
    private final Timer timer = new Timer();
    Player mario;
    LevelClass currentLvl;
    public class MyKeyListener implements KeyListener
    /******************************************

*Name: Tasbir Rahman

*Date: June 14, 2016

*Class Name: MyKeyListener

*Description: for keyboard events

********************************************/
    {
	@Override
	public void keyTyped(KeyEvent e) {
          
        }

	@Override
	public void keyPressed(KeyEvent e)
                   /******************************************

*Name: Tasbir Rahman

*Date: June 14 2016

*Function Name: keyPressed

*Description: happens when key is pressed
*Inputs: which key iz pressed

*Outputs: none

********************************************/
        {
            if(e.getKeyCode() == KeyEvent.VK_SHIFT){if(!paused)mario.accelerateButtonPressed = true;}
            if(e.getKeyCode() == ' '){{if(!paused)mario.jump();}
          
                
            
            }
            if(e.getKeyCode() == 'D'){if(!paused)mario.startMove(false); }
            else if(e.getKeyCode() == 'A'){if(!paused)mario.startMove(true); }
            if(e.getKeyCode() == '.')gridDisplay = !gridDisplay;
            if(e.getKeyCode() == KeyEvent.VK_ENTER)paused = !paused;
            
        }
        @Override
	public void keyReleased(KeyEvent e)
                     /******************************************

*Name: Tasbir Rahman

*Date: June 14 2016

*Function Name: keyPressed

*Description: happens when key is released
*Inputs: which key is released

*Outputs: none

********************************************/
        {
            //these work paused cuz unpausing causes key to get stuck
                        if(e.getKeyCode() == KeyEvent.VK_SHIFT){mario.accelerateButtonPressed = false;}
                       if(e.getKeyCode() == 'D'){mario.stopMove(false); }
                       if(e.getKeyCode() == 'A'){mario.stopMove(true);}
                       if(e.getKeyCode() == ' '){mario.stopJump();}



        }
	
}

    private class MyTimer extends java.util.TimerTask
       /******************************************

*Name: Tasbir Rahman

*Date: June 14, 2016

*Class Name: MyTimer

*Description: game runs on a timer, refrshing every timer tick

********************************************/
    { 
                public void run()
                     /******************************************

*Name: Tasbir Rahman

*Date: June 14 2016

*Function Name: run

*Description: starts timer
*Inputs: none

*Outputs: none

********************************************/
                { 
                 // Run thread on event dispatching thread 
                 if (!EventQueue.isDispatchThread()) { 
                     EventQueue.invokeLater(this); 
                 } 
                 else { 
                     if (Game.this != null) { 
                         Game.this.repaint();                         
                     } 
                 } 
                  
             } // End of Run 
    } 
    public void shellCollisionHandle
                /******************************************

*Name: Tasbir Rahman

*Date: June 14 2016

*Function Name: shellCollisionHandle

*Description: handles collision with shell

*Inputs: entity colliding with shell, and shell

*Outputs: none

********************************************/
        (Entity shellFighter, Shell shelly)
    {
              if(ExtraFunct.RectangleIntersect(shellFighter.getX(), shellFighter.getY(), shellFighter.getWidth(),
                      shellFighter.getHeight(), shelly.getX(), shelly.getY(), shelly.getWidth(),
                      shelly.getHeight())&& shelly.isActive() && shellFighter.isAlive())
              {
                  double minTime = 1676767676;//least time = where it was first
                double topTime = -1;//for calculating how much time passed when the collision happend. Starts at -1 meaning it default doesnt count unless edge is enabled
                double bottomTime = -1;
                double leftTime = -1;
                double rightTime = -1;
                if(shellFighter.getY() +shellFighter.getHeight() >= shelly.getY()+shelly.getHeight())
                {
                    shellFighter.setInAir(true);
                    shellFighter.setY(shelly.getY()+4 - shellFighter.getHeight());
                }
                if(shellFighter.getY() + shellFighter.getHeight() > shelly.getY() && shellFighter.getVerticalVel()!=0)//calculate time for top
                {
                    topTime = ((shellFighter.getY()+shellFighter.getHeight() - shelly.getY())/shellFighter.getVerticalVel());

                }
                if(shellFighter.getY() < shelly.getY() + shelly.getHeight() &&  shellFighter.getVerticalVel()!=0)//for bottom
                {
                    bottomTime =((shellFighter.getY()-shelly.getY() - shelly.getHeight())/shellFighter.getVerticalVel());

                }
                if(shellFighter.getX() + shellFighter.getWidth() > shelly.getX() && shellFighter.getHorizontVel() != 0)//for left
                {
                    leftTime = (shellFighter.getX() + shellFighter.getWidth() - shelly.getX())/(shellFighter.getHorizontVel());
                }
                if(shellFighter.getX() < shelly.getX() + shelly.getWidth() &&shellFighter.getHorizontVel() != 0)
                {
                    rightTime = (shellFighter.getX() - shelly.getX() -  shelly.getWidth())/(shellFighter.getHorizontVel());
                }
                double [] times = {topTime, bottomTime, leftTime, rightTime};
                for(int i = 0; i < times.length; i++)
                {
                    if(times[i] < minTime && times[i] >= 0)
                    {
                        minTime = times[i];
                    }
                }
          
                if(minTime == topTime && shellFighter.inAir&&shellFighter==mario)//needs to be in air for a stomp  to work, stomp can be dony by player, it stops moving shells and kicks stationary shells
                {
                    shellFighter.setY(shelly.getY()- shellFighter.getHeight()- 7);//prevent accidental collision right after shell starts moving

                    
                    shellFighter.bounce();
                    if(shelly.movingHorizontally())shelly.stop();
                    else
                    {

                        if(shellFighter.getX() + shellFighter.getWidth()/2 < shelly.getX() + shelly.getWidth()/2)//if middle of kciker is left of middle of shell, kick right
                        {
                            shelly.kick(false);
                        }
                        else{//and vice versa
                            shelly.kick(true);
                        }
                    }

                }

                else{
                    if(shelly.movingHorizontally() && shellFighter.isAlive())
                    {
                        shellFighter.kill();
                    }
                    else if(shellFighter == mario)//only player can kick on ground 
                    {
                        if(shellFighter.getX() + shellFighter.getWidth()/2 < shelly.getX() + shelly.getWidth()/2)//if middle of player is left of middle of shell, kick right
                        {
                            shelly.kick(false);
                        }
                        else{//and vice versa
                            shelly.kick(true);
                        }
                    }
                }
                  
              }
  
    }
    public void enemyCollisionHandle(Player person, Enemy meanie)
            /*Name: Tasbir Rahman

*Date: June 14 2016

*Function Name: enemeyCollisionHandle

*Description: handles collision of enemies and responds accordingly
*Inputs: player, enemy collided with

*Outputs: none

********************************************/
    {
        if(ExtraFunct.RectangleIntersect(person.getX(), person.getY(), person.getWidth(), person.getHeight(), meanie.getX(), meanie.getY(), meanie.getWidth(), meanie.getHeight())&& meanie.isAlive() && person.isAlive())
        {
            if(meanie.canStomp())
            {
                double minTime = 1676767676;//least time = where it was first
                double topTime = -1;//for calculating how much time passed when the collision happend. Starts at -1 meaning it default doesnt count unless edge is enabled
                double bottomTime = -1;
                double leftTime = -1;
                double rightTime = -1;
                if(person.getY() + person.getHeight() > meanie.getY() && person.getVerticalVel()!=0)//calculate time for top
                {
                    topTime = ((person.getY()+person.getHeight() - meanie.getY())/person.getVerticalVel());

                }
                if(person.getY() < meanie.getY() + meanie.getHeight() &&  person.getVerticalVel()!=0)//for bottom
                {
                    bottomTime =((person.getY()-meanie.getY() - meanie.getHeight())/person.getVerticalVel());

                }
                if(person.getX() + person.getWidth() > meanie.getX() && person.getHorizontVel() != 0)//for left
                {
                    leftTime = (person.getX() + person.getWidth() - meanie.getX())/person.getHorizontVel();
                }
                if(person.getX() < meanie.getX() + meanie.getWidth() &&person.getHorizontVel() != 0)
                {
                    rightTime = (person.getX() - meanie.getX() -  meanie.getWidth())/person.getHorizontVel();
                }
                double [] times = {topTime, bottomTime, leftTime, rightTime};
                for(int i = 0; i < times.length; i++)
                {
                    if(times[i] < minTime && times[i] >= 0)
                    {
                        minTime = times[i];
                    }
                }
                if(minTime == topTime && person.inAir)//needs to be in air for a stomp attack to work
                {
                    meanie.kill(true);
                        person.setY(meanie.getY()- person.getHeight()- 7);//prevent accidental collision right after hitting enemy(multiple enemies can be in the same place)

                    
                    person.bounce();
                            

                }

                else{
                    if(person.isAlive())person.kill();
                }
            }
            else
            {
               if(person.isAlive()) person.kill();
            }
        }
    }
    public void tileCollisionHandle(Entity thingy)
                 /******************************************

*Name: Tasbir Rahman

*Date: June 14 2016

*Function Name: keyPressed

*Description: handles collision of blocks in currentLevel for an entiry
*Inputs: entity where collision should be handled

*Outputs: none

********************************************/
    {
        if(thingy.getX() < 0)
        {   
            if(thingy == mario )thingy.currentHorizontalVelocity = 0;
            else{thingy.switchDirection();}
            thingy.setX(0);
        }
        if(thingy.getY() < -10)
        {
            if(thingy == mario)thingy.currentVerticalVelocity = 0;
            thingy.setY(-10);
        }
        if(thingy.getY() > currentLvl.getYSizeAsPixels() + 30)
        {
            if(thingy.isAlive())thingy.kill();
        }
        if(thingy.getX() + thingy.getWidth() > this.currentLvl.getXSizeAsPixels())
        {
             if(thingy == mario )thingy.currentHorizontalVelocity = 0;
            else{thingy.switchDirection();}
            thingy.setX(this.currentLvl.getXSizeAsPixels() - thingy.getWidth())  ;
        }
        for(int y = (int)(thingy.getY())/Tile.getHeight(); y <= (int)((thingy.getY()+thingy.getHeight())/Tile.getHeight()); y++)//find tiles around thingy
            {
                for(int x = (int)(thingy.getX()/Tile.getWidth()); x <= (int)((thingy.getX() + thingy.getWidth())/Tile.getWidth());x++)//find tiles around thingy
                {
                    Block currentBlock = null;
                    if((x>= 0 && x < currentLvl.getXLength()) &&(y>= 0 && y < currentLvl.getYLength())) currentBlock = this.currentLvl.getCollisionBlockList()[x][y];//check if around thingy in bounds
                    if(currentBlock !=null)
                    {
                        boolean intersecting =ExtraFunct.RectangleIntersect(thingy.getX(), thingy.getY(), thingy.getWidth(), thingy.getHeight(), currentBlock.getXAsDouble(), currentBlock.getYAsDouble(), (double)Tile.getWidth(),(double)Tile.getHeight()
                        );
                        if(intersecting && thingy.canTileCollide())
                        {
                            double minTime = 1676767676;//least time = where it was first
                            double topTime = -1;//for calculating how much time passed when the collision happend. Starts at -1 meaning it default doesnt count unless edge is enabled
                            double bottomTime = -1;
                            double leftTime = -1;
                            double rightTime = -1;
                            if(thingy.getY() + thingy.getHeight() > currentBlock.getYAsDouble() && currentBlock.isTopEnabled() && thingy.getVerticalVel()!=0)//calculate time for top
                            {
                                topTime = ((thingy.getY()+thingy.getHeight() - currentBlock.getYAsDouble())/thingy.getVerticalVel());

                            }
                            if(thingy.getY() < currentBlock.getYAsDouble() + Tile.getHeight() && currentBlock.isBottomEnabled()&& thingy.getVerticalVel()!=0)//for bottom
                            {
                                bottomTime =((thingy.getY()-currentBlock.getYAsDouble() - Tile.getHeight())/thingy.getVerticalVel());

                            }
                            if(thingy.getX() + thingy.getWidth() > currentBlock.getXAsDouble() && currentBlock.isLeftEnabled()&& thingy.getHorizontVel() != 0)//for left
                            {
                                leftTime = (thingy.getX() + thingy.getWidth() - currentBlock.getXAsDouble())/thingy.getHorizontVel();
                            }
                            if(thingy.getX() < currentBlock.getXAsDouble() + Tile.getWidth() && currentBlock.isRightEnabled() && thingy.getHorizontVel() != 0)
                            {
                                rightTime = (thingy.getX() - currentBlock.getXAsDouble() -  Tile.getWidth())/thingy.getHorizontVel();
                            }
                            double [] times = {topTime, bottomTime, leftTime, rightTime};
                            for(int i = 0; i < times.length; i++)
                            {
                                if(times[i] < minTime && times[i] >= 0)
                                {
                                    minTime = times[i];
                                }
                            }
                            //perform collision response withg tile edge that had the smallest positve time
                            if(minTime == topTime)
                            {
                                if(thingy.followGravity())thingy.setInAir(false);
                                thingy.setY( currentBlock.getYAsDouble() - thingy.getHeight()) ; 
                            }
                            else if(minTime == bottomTime)
                            {
                                if(thingy.followGravity())thingy.currentVerticalVelocity =0;
                                 thingy.setY(currentBlock.getYAsDouble() + Tile.getHeight())  ;
                            }
                            else if(minTime == leftTime)
                            {
                                if(thingy == mario)thingy.currentHorizontalVelocity = 0;
                                else{thingy.switchDirection();}
                                thingy.setX(currentBlock.getXAsDouble() - thingy.getWidth());
                            }
                            else if(minTime == rightTime)
                            {
                                if(thingy == mario)thingy.currentHorizontalVelocity = 0;
                                                                else{thingy.switchDirection();}

                                thingy.setX( currentBlock.getXAsDouble() + Tile.getWidth());
                            }
                            if(thingy != mario)
                            {
                                if(currentBlock.isLeftEnabled() && thingy.getX() <currentBlock.getXAsDouble()&&thingy.canDetectCliff()&&
                                        minTime!= leftTime)//so it changes for walls
                                {
                                    thingy.switchDirection();
                                    thingy.setX( currentBlock.getXAsDouble() + 1) ;
                                }   
                                if(currentBlock.isRightEnabled() && thingy.getX() +thingy.getWidth() >currentBlock.getXAsDouble() + Tile.tileWidthSize && thingy.canDetectCliff()&&
                                        minTime!= rightTime)//so it changes for walls
                                {
                                    thingy.switchDirection();
                                    thingy.setX(currentBlock.getXAsDouble() + Tile.tileWidthSize - 1-thingy.getHeight()) ;

                                    
                                }

                                    
                            }
   
                            
                        }
                    }

                }
            }
    }
    public void loadLevel(String directory)
                 /******************************************

*Name: Tasbir Rahman

*Date: June 14 2016

*Function Name: loadLevel

*Description: loads data for level from folder containing decoratation tiles blocks, enemies and coins and makes level
*Inputs: directory of level is a folder

*Outputs: none

********************************************/
    {
        int xCount=0;
        int yCount=0;
        int spawnerCount = 0;
        String coinText = readTextFile(directory + "/coins.tasbir");
        String imgTileText = readTextFile(directory + "/imgTiles.tasbir");
        String enemyText = readTextFile(directory + "/enemies.tasbir");
        String spawnerText = readTextFile(directory + "/spawners.tasbir");;
        StringTokenizer coinToken;
        StringTokenizer enemyToken;
        StringTokenizer xImgToken;
        StringTokenizer spawnerToken;
        StringTokenizer yImgToken;
        StringTokenizer xColToken;
        StringTokenizer yColToken;
        String collisionTileText = readTextFile(directory +"/collTiles.tasbir");
        Tile[][] imgTiles;
        EnemySpawner[] spawner;
        Block[][] collisionBlocks;
        
        
        yImgToken = new StringTokenizer(imgTileText, "\n");
        while(yImgToken.hasMoreTokens())//counts width and length of lvl in tiles
        {
            yCount++;
            String currentToken = yImgToken.nextToken();
            xImgToken = new StringTokenizer(currentToken, " ");
            xCount = 0;
            while(xImgToken.hasMoreTokens())
            {
                xCount++;
                xImgToken.nextToken();
            }
        }
        yImgToken = new StringTokenizer(imgTileText, "\n");
        yColToken = new StringTokenizer(collisionTileText, "\n");
        imgTiles = new Tile[xCount][yCount];
        collisionBlocks = new Block[xCount][yCount];
        
        for(int y = 0; y < yCount;y++ )//Read from text and create blockList and imageTile LIst
        {
            String currentYImgString = yImgToken.nextToken();
            String currentYColString = yColToken.nextToken();
            xImgToken = new StringTokenizer(currentYImgString, " ");
            xColToken = new StringTokenizer(currentYColString, " ");
           


            for(int x = 0; x < xCount; x++)
            {
                String currentImgString = xImgToken.nextToken();
                String currentColString = xColToken.nextToken();
                int imgSpriteX = -1, imgSpriteY = -1, colSpriteX =-1, colSpriteY = -1;//each token represents a coordiante on a sprite sheet, the coordinates represents a tile on the sheet
                if(!currentImgString.equals("xxxx"))
                {
                    imgSpriteX = Integer.parseInt(currentImgString.substring(0,2));
                    imgSpriteY = Integer.parseInt(currentImgString.substring(2));
                }
                if(!currentColString.equals("xxxx"))
                {
                    colSpriteX = Integer.parseInt(currentColString.substring(0,2));
                    colSpriteY = Integer.parseInt(currentColString.substring(2));
                }
                if(imgSpriteY != -1 && imgSpriteX != -1)imgTiles[x][y] = new Tile(this.spriteLoader.getSprite(imgSpriteX,imgSpriteY), x, y);
                if(colSpriteY != -1 && colSpriteX != -1)collisionBlocks[x][y] = new Block(this.spriteLoader.getSprite(colSpriteX,colSpriteY), x, y);

            }
                
        }
        for(int y = 0; y < yCount; y++)//setting if left up down right edges of each tile should be enabled when doing collision
        {
            for(int x = 0; x < xCount; x++)
            {
                if(collisionBlocks[x][y] != null)
                {
                    if(x+1 < xCount)//if block on right
                    {
                        if(collisionBlocks[x+1][y] != null)collisionBlocks[x][y].disableRight();;
                    }
                    if(x-1 >= 0)//on left
                    {
                        if(collisionBlocks[x-1][y] != null)collisionBlocks[x][y].disableLeft();;
                    }
                    if(y+1 < yCount)//below
                    {
                        if(collisionBlocks[x][y+1] != null)collisionBlocks[x][y].disableBottom();;
                    }
                    if(y-1 >= 0)//on top
                    {
                        if(collisionBlocks[x][y-1] != null)collisionBlocks[x][y].disableTop();;
                    }
                }
            }
        }
        //addSpawner
        spawnerToken = new StringTokenizer(spawnerText,"\n");
        
        //count spawners
        while(spawnerToken.hasMoreTokens())
        {
            spawnerCount++;
            spawnerToken.nextToken();
        }
        //add to array
        spawnerToken = new StringTokenizer(spawnerText,"\n");
        spawner = new EnemySpawner[spawnerCount];
        for(int i = 0; i< spawnerCount;i++)
        {
            String currentToken = spawnerToken.nextToken();
             EnemyTypes type = null;
             int direction;
            int x;
            int y;
            double vertSpeed;
            double horiSpeed;
            //Format = TYPEXX_XXYY_YYYY_XXXX_X where second colmn is tile position and 3rd and 4th are speeds, 5th is direction
            //find type
            if(currentToken.startsWith("KOOPA"))
            {
                type = EnemyTypes.KOOPA;
            }
            else if(currentToken.startsWith("GOOMBA"))
            {
                type = EnemyTypes.GOOMBA;
            }     
            else if(currentToken.startsWith("SPINY"))
            {
                type = EnemyTypes.SPINY;
            }
            else if(currentToken.startsWith("FIRE"))
            {
                type = EnemyTypes.FIRE;
            }
            //find other tpyes
            x =Integer.parseInt(currentToken.substring(7,9));
            y =Integer.parseInt(currentToken.substring(9,11));
            direction = Integer.parseInt(currentToken.substring(18));
            vertSpeed =Double.parseDouble(currentToken.substring(12,14));
            horiSpeed =Double.parseDouble(currentToken.substring(15,17));
            spawner[i] = new EnemySpawner(type,vertSpeed,horiSpeed,x,y,direction);
        }
        
        this.currentLvl = new LevelClass(imgTiles, collisionBlocks, xCount, yCount,spawner);//after this all items in the world that can change amount are added
        //add enemies
        enemyToken = new StringTokenizer(enemyText,"\n");
        while(enemyToken.hasMoreTokens())
        {
            String currentToken;
            EnemyTypes type = null;
            int x;
            int y;
            double vertSpeed;
            double horiSpeed;
            currentToken = enemyToken.nextToken();
            //Format = TYPEXX_XXYY_YYYY_XXXX where second colmn is tile position and last two are speeds
            //find type
            if(currentToken.startsWith("KOOPA"))
            {
                type = EnemyTypes.KOOPA;
            }
            else if(currentToken.startsWith("GOOMBA"))
            {
                type = EnemyTypes.GOOMBA;
            }     
            else if(currentToken.startsWith("SPINY"))
            {
                type = EnemyTypes.SPINY;
            }
            else if(currentToken.startsWith("FIRE"))
            {
                type = EnemyTypes.FIRE;
            }
            //find other tpyes
            x =Integer.parseInt(currentToken.substring(7,9));
            y =Integer.parseInt(currentToken.substring(9,11));
            vertSpeed =Double.parseDouble(currentToken.substring(12,14));
            horiSpeed =Double.parseDouble(currentToken.substring(15,17));
            Enemy badDude = new Enemy(type,vertSpeed,horiSpeed,x,y);
            this.currentLvl.getEnemyList().addNode(badDude);
            

                
        }
        //add coins to level
        coinToken = new StringTokenizer(coinText,"\n");
        while(coinToken.hasMoreTokens())
        {
            String current = coinToken.nextToken();
            int x = Integer.parseInt(current.substring(0, 2));
            int y = Integer.parseInt(current.substring(2));
            Coin coin = new Coin(x,y);
            this.currentLvl.getCoinList().addNode(coin);
        }
       
    }
    public String readTextFile(String directory)
                 /******************************************

*Name: Tasbir Rahman

*Date: June 14 2016

*Function Name: readTextFile

*Description: reads text from resource stream
*Inputs: directory of t4ext file

*Outputs: String of contents from file

********************************************/
    {
        
        InputStream in = this.getClass().getResourceAsStream(directory);
        BufferedReader input = new BufferedReader(new InputStreamReader(in));
        String out = "";
        String line = null;
        try {
            line = input.readLine();
        } catch (IOException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
        while(line!= null)
        {
            out += line;
            out += "\n";
            try {
                line = input.readLine();
            } catch (IOException ex) {
                Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        out = out.substring(0,out.length() -1);
        return out;
    }
    public void renderOffScreen(Graphics g) 
                 /******************************************

*Name: Tasbir Rahman

*Date: June 14 2016

*Function Name: rendorOffScreen

*Description: draws on offscreen image every tick and handles game logic
*Inputs: offscreenImage

*Outputs: none

********************************************/
    {
                final Dimension d = getSize();
        g.setColor(new Color(98,133,250));
        System.out.println(this.currentLvl.getSpawnerArray().length);
        offScreenGraphics.fillRect(this.screenX - 64 , 0, d.width + 128, d.height) ; //draw part of background, only visible part to stop lag 
        int enemyIndexCount = 0;
        int shellIndexCount = 0;
        int coinIndexCount = 0;
        CoinNodes tempCoin;
        ShellNodes tempShell;
        EnemyNodes tempEnemy;
        //check if coins are in screen, delete inactive ones
        tempCoin = this.currentLvl.getCoinList().head;
        while(tempCoin != null)
            {
                
                if(ExtraFunct.RectangleIn((int)tempCoin.data.getX(),(int) tempCoin.data.getY(),(int) tempCoin.data.getWidth(), (int)tempCoin.data.getHeight(),screenX-2*Tile.getWidth(),
                        -2*Tile.getHeight(), d.width+4*Tile.getWidth(), d.height+4*Tile.getHeight()))
                {
                    tempCoin.data.changeInScreen(true);
                }
                else
                {
                    tempCoin.data.changeInScreen(false);
                }
                if(!tempCoin.data.isActive())
                {

                    this.currentLvl.getCoinList().deleteElement(coinIndexCount);
                }
                coinIndexCount++;
                tempCoin = tempCoin.next;
            }
        
        //check if enemies  are in screen, delete inactive ones
                    tempEnemy = this.currentLvl.getEnemyList().head;
        while(tempEnemy != null)
            {
                
                if(ExtraFunct.RectangleIn((int)tempEnemy.data.getX(),(int) tempEnemy.data.getY(),(int) tempEnemy.data.getWidth(), (int)tempEnemy.data.getHeight(),screenX-2*Tile.getWidth(),
                        -2*Tile.getHeight(), d.width+4*Tile.getWidth(), d.height+4*Tile.getHeight()))
                {
                    tempEnemy.data.changeInScreen(true);
                }
                else
                {
                    tempEnemy.data.changeInScreen(false);
                }
                if(!tempEnemy.data.isActive())
                {
                    if(tempEnemy.data.type == EnemyTypes.KOOPA)
                    {
                        Shell newShell = new Shell(tempEnemy.data.getX(),tempEnemy.data.getY());
                        this.currentLvl.getShellList().addNode(newShell);

                    }
                    this.currentLvl.getEnemyList().deleteElement(enemyIndexCount);
                }
                enemyIndexCount++;
                tempEnemy = tempEnemy.next;
            }
        
        
        //check if shells are in screen
        tempShell = this.currentLvl.getShellList().head;
        shellIndexCount = 0;
        while(tempShell!=null)
        {
            if(ExtraFunct.RectangleIn((int)tempShell.data.getX(),(int) tempShell.data.getY(),(int) tempShell.data.getWidth(), (int)tempShell.data.getHeight(),screenX-2*Tile.getWidth(),
                        -2*Tile.getHeight(), d.width+4*Tile.getWidth(), d.height+4*Tile.getHeight()))
                {
                    tempShell.data.changeInScreen(true);
                }
            else
            {
                tempShell.data.changeInScreen(false);
            }
            if(!tempShell.data.isActive())this.currentLvl.getShellList().deleteElement(shellIndexCount);
            tempShell = tempShell.next;
            shellIndexCount++;
        }
        
        
        //check if spawner in Screen
        for(int x = 0;x<this.currentLvl.getSpawnerArray().length;x++)
        {
            if(ExtraFunct.RectangleIn((int)this.currentLvl.getSpawnerArray()[x].getX(),(int) this.currentLvl.getSpawnerArray()[x].getY(),
                    (int) this.currentLvl.getSpawnerArray()[x].getWidth(), (int)this.currentLvl.getSpawnerArray()[x].getHeight(),screenX-2*Tile.getWidth(),
                        -2*Tile.getHeight(), d.width+4*Tile.getWidth(), d.height+4*Tile.getHeight()))
                {
                    this.currentLvl.getSpawnerArray()[x].changeInScreen(true);
                }
            else
            {
                this.currentLvl.getSpawnerArray()[x].changeInScreen(false);
            }
        }
        
        
        if(this.currentLvl.getCoinList().getCount() >0 && //0 coins = win the game
            !paused)
        //perform game logic
        {
            //update entities
            mario.update();
            mario.setInAir(true);
            tempEnemy = this.currentLvl.getEnemyList().head;
            
            while(tempEnemy != null)
            {
                
                if(tempEnemy.data.inScreen)
                {
                    tempEnemy.data.update();
                    tempEnemy.data.setInAir(true);
                }
                                tempEnemy = tempEnemy.next;

            }
            tempShell = this.currentLvl.getShellList().head;
            while(tempShell != null)
            {
                
                if(tempShell.data.inScreen)
                {
                    tempShell.data.update();
                    tempShell.data.setInAir(true);
                }
                                tempShell = tempShell.next;

            }
            for(int x = 0; x< this.currentLvl.getSpawnerArray().length;x++)
            {
                if(this.currentLvl.getSpawnerArray()[x].isInScreen())this.currentLvl.getSpawnerArray()[x].update();
            }
            
            //collisions
            if(this.currentLvl != null)
            {
                this.tileCollisionHandle(mario);
                //shell with player collision
                tempShell = this.currentLvl.getShellList().head;
                 while(tempShell != null)
                {

                    if(tempShell.data.inScreen)
                    {
                        this.shellCollisionHandle(mario, tempShell.data);
                    }
                    tempShell = tempShell.next;

                } 

                //enemy and player
                tempEnemy = this.currentLvl.getEnemyList().head;
                while(tempEnemy != null)
                {
                     if(tempEnemy.data.isInScreen())
                     {
                         this.tileCollisionHandle(tempEnemy.data);
                         this.enemyCollisionHandle(mario, tempEnemy.data);
                     }
                    tempShell = this.currentLvl.getShellList().head;
                    
                    
                    //shell and enemy
                    while(tempShell != null)
                    {

                       if(tempShell.data.inScreen)
                       {
                           this.shellCollisionHandle(tempEnemy.data, tempShell.data);
                       }
                       tempShell = tempShell.next;

                    } 
                    tempEnemy = tempEnemy.next;
                }
                
                
                //shell and world
                 tempShell = this.currentLvl.getShellList().head;
                 while(tempShell != null)
                {

                    if(tempShell.data.inScreen)
                    {
                        this.tileCollisionHandle(tempShell.data);
                    }
                    tempShell = tempShell.next;

                }
            }
            
            //coin and mario
            tempCoin = this.currentLvl.getCoinList().head;
            while(tempCoin !=null)
            {
                
                Coin coin = tempCoin.data;
                if(coin.isInScreen()&& ExtraFunct.RectangleIntersect(coin.getX(), coin.getY(), coin.getWidth(), coin.getHeight(), mario.getX(), mario.getY(), mario.getWidth(), mario.getHeight()))
                {
                    coin.kill();
                }
                tempCoin = tempCoin.next;
            }
            
            
            //if mario dies reset him at spawn, spawn is same for all levels
            if(!mario.isActive())mario = new Player(21,7);
        }
        //drawing
        //<editor-fold desc="drawing">
        if(this.currentLvl != null)
        {
            for(int y = 0; y < this.currentLvl.getYLength();y++)//drawing Decor 
            {
                for(int x = 0; x < this.currentLvl.getXLength();x++)
                {
                    Tile currentDecor = this.currentLvl.getDecorTileList()[x][y];
                    if(currentDecor != null)
                    {
                        if(ExtraFunct.RectangleIn(currentDecor.getX(), currentDecor.getY(), Tile.getWidth(), Tile.getHeight(), screenX-2*Tile.getWidth(), -2*Tile.getHeight(), d.width+4*Tile.getWidth(), d.height+4*Tile.getHeight()))
                            g.drawImage(currentDecor.getImg(), currentDecor.getX(), currentDecor.getY(), this);
                    }

                    if(gridDisplay)
                    {g.setColor(Color.GREEN);
                    g.drawRect(x*32, y*32, 32, 32);
                    g.drawString(x + " " + y, x*32, y*32+14);}

                   
                }
            }
            
            
                        //draw enemies in spawners
            for(int y = 0; y < this.currentLvl.getSpawnerArray().length;y++)
            {
                Enemy current;
                if(this.currentLvl.getSpawnerArray()[y].isInScreen())
                {
                    current =this.currentLvl.getSpawnerArray()[y].getBaddie();
                    g.drawImage(current.getImg(), current.getImgX(), current.getImgY(), this);
                }
                
            }
            
            
            for(int y = 0; y < this.currentLvl.getYLength();y++)//drawing blocks 
            {
                for(int x = 0; x < this.currentLvl.getXLength();x++)
                {
                    Tile currentCollision = this.currentLvl.getCollisionBlockList()[x][y];
                    if(currentCollision != null)
                    {
                        if(ExtraFunct.RectangleIn(currentCollision.getX(), currentCollision.getY(), Tile.getWidth(), Tile.getHeight(), screenX-2*Tile.getWidth(), -2*Tile.getHeight(), d.width+4*Tile.getWidth(), d.height+4*Tile.getHeight()))
                            g.drawImage(currentCollision.getImg(), currentCollision.getX(), currentCollision.getY(), this);
                        
                    }


                   
                }
            }
            
            
              tempEnemy = this.currentLvl.getEnemyList().head;//draw enemies
             while(tempEnemy != null)
             {
                  if(tempEnemy.data.isInScreen())
                  {
                      g.drawImage(tempEnemy.data.getImg(),tempEnemy.data.getImgX(),tempEnemy.data.getImgY(),tempEnemy.data.getImgWidth(),tempEnemy.data.getImgHeight(),this);
                  }
             tempEnemy = tempEnemy.next;
             }
             
             
             
             //draw Shells
             tempShell = this.currentLvl.getShellList().head;
            while(tempShell!=null)
            {
                if(tempShell.data.isInScreen())
                {
                    g.drawImage(tempShell.data.getImg(),tempShell.data.getImgX(),tempShell.data.getImgY(),tempShell.data.getImgWidth(),tempShell.data.getImgHeight(),this);
                }
                tempShell = tempShell.next;
            }
            
            
            //draw coins
            tempCoin = this.currentLvl.getCoinList().head;
            while(tempCoin!= null)
            {
                if(tempCoin.data.isInScreen())
                {
                    Coin coin = tempCoin.data;
                    g.drawImage(coin.getImg(), coin.getImgX(), coin.getImgY(),coin.getImgWidth(),coin.getImgHeight(), this);

                   
                }
                tempCoin = tempCoin.next;
            }
                
        }
        g.drawImage(mario.getImg(), mario.getImgX(), mario.getImgY(),mario.getImgWidth(),mario.getImgHeight(), this);
        //</editor-fold>
    }
    void renderOffScreenCropped(Graphics g)
                    /******************************************

*Name: Tasbir Rahman

*Date: June 14 2016

*Function Name: renderOffScreenCropped

*Description: draws stuff on screen that always stay in position no matter scrolling
*Inputs: graphics variable

*Outputs: inScreen boolean

********************************************/
    {
        g.setColor(Color.BLACK);
        
        g.setFont(nesFont);
        g.drawString("Press Enter for Pausing/Manual", 20, 20);
        if(this.currentLvl.getCoinList().getCount() <=0)
        {
            g.drawImage(winScreen, 160, 60, this);
        }
        else if(paused)
        {
            g.drawImage(pauseScreen, 160, 60, this);
        }
    }

    public void paint(Graphics g)
                 /******************************************

*Name: Tasbir Rahman

*Date: June 14 2016

*Function Name: paint

*Description: draws on screen
*Inputs: graphics object

*Outputs: none

********************************************/
    {
         final Dimension d = this.getSize(); 

             if (offScreenImage == null) {    
                 // Double-buffer: clear the offscreen image.                 
                 offScreenImage = createImage(this.currentLvl.getXSizeAsPixels(), this.currentLvl.getYSizeAsPixels());    

             }     
             this.offScreenGraphics = this.offScreenImage.getGraphics();
             offScreenGraphics.setColor(Color.BLUE); 
              
       
            renderOffScreen(offScreenGraphics);

             BufferedImage poyo = (BufferedImage)offScreenImage;
            screenX = (int)mario.getX() -d.width/2;
            if(screenX <0)screenX = 0;
            else if(screenX>offScreenImage.getWidth(this) - d.width)screenX = offScreenImage.getWidth(this) - d.width ;
             this.offScreenImageCropped = poyo.getSubimage(screenX, 0, d.width, d.height);
             this.offScreenGraphicsCropped = this.offScreenImageCropped.getGraphics();
             renderOffScreenCropped(this.offScreenGraphicsCropped);
             g.drawImage(this.offScreenImageCropped, 0, 0, this);
             this.offScreenImageCropped = null;
             this.offScreenImage = null;
        
    }
    Game()
                 /******************************************

*Name: Tasbir Rahman

*Date: June 14 2016

*Function Name: constructor

*Description: creates game panel
*Inputs: none

*Outputs: none

********************************************/
    {
            try {
         // Open an audio input stream. to play jump sfx
         BufferedInputStream bsr = new BufferedInputStream(this.getClass().getResourceAsStream("sounds/music.wav"));//for JARfile
         AudioInputStream audioIn = AudioSystem.getAudioInputStream(bsr);
         // Get a sound clip resource.
          song = AudioSystem.getClip();
         // Open audio clip and load samples from the audio input stream.
         song.open(audioIn);
        FloatControl gainControl = (FloatControl) song.getControl(FloatControl.Type.MASTER_GAIN);
        gainControl.setValue((float)-5.0);
        song.loop(Clip.LOOP_CONTINUOUSLY);
        
      } catch (UnsupportedAudioFileException e) {
         e.printStackTrace();
      } catch (IOException e) {
         e.printStackTrace();
      } catch (LineUnavailableException e) {
         e.printStackTrace();
      }
        try {
            
            this.nesFont = Font.createFont(Font.TRUETYPE_FONT, this.getClass().getResourceAsStream("Font.ttf")).deriveFont((float)15);
        } catch (FontFormatException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.loadLevel("levels/first");
        mario = new Player(21,7);
                setFocusable(true);
        timer.schedule(new MyTimer(), 0, 25);
        KeyListener listener = new MyKeyListener();
        this.addKeyListener(listener);
        
    }

   

}
