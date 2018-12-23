/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalisugame_tasbirrahman;

import finalisugame_tasbirrahman.Enemy.EnemyTypes;

/**
 *
 * @author TASBIR
 */
public class EnemySpawner extends Entity{//spawners are tile sized
/******************************************

*Name: Tasbir Rahman

*Date: June 14, 2016

*Class Name: EnemySpawner

*Description: entities that spawn enemies, first they move them a bit and then add them to world, they are placed in the same place as pipe blocks to give the illusin
* that an enemy is coming out of them

********************************************/   
    Enemy baddie;//enemy it spawns
    Enemy oldBaddie;
    int direction;//1 = up, 2 = down, 
    int delay;
    double increment;//how much to move enmy
    int tileX;//as tile
    int tileY;//as tile
    int maxDelay;//wait before spawning
    EnemyLinkedList levelList;//to add enemy to level
    void setLevelList(EnemyLinkedList list)
                       /******************************************

*Name: Tasbir Rahman

*Date: June 14 2016

*Function Name: setLevelList

*Description: sets the list the enemies spawned by this should be added to, usually its the level's enemy list

*Inputs: enemy list

*Outputs: none

********************************************/
    {
        
        this.levelList = list;
    }
    void spawnBaddie()
                            /******************************************

*Name: Tasbir Rahman

*Date: June 14 2016

*Function Name: spawnBaddie

*Description: adds enemy to list and resets the cycle

*Inputs: none

*Outputs: none

********************************************/
    {
       levelList.addNode(baddie);
       baddie.switchDirection();
        baddie = new Enemy(baddie.getType(),baddie.getVerticalVel(),-1*Math.abs(baddie.getHorizontVel()),tileX,tileY);
        baddie.setX(baddie.getX()+baddie.getWidth());
        if(direction == 1)baddie.setY(baddie.getY() +Math.abs(baddie.getImgY()-baddie.getY()));
        if(direction ==2){baddie.setY(baddie.getY()+Math.abs((baddie.getImgY() -baddie.getY())));}

        delay = 0;

    }
    
    EnemySpawner(EnemyTypes type,double vertSpeed, double horiSpeed,int x, int y,int direction)
                            /******************************************

*Name: Tasbir Rahman

*Date: June 14 2016

*Function Name: construcctor

*Description: creates instance of class with sall enemy  properties and direction

*Inputs: type, vert and hori speeds, x and y as tiles, direction

*Outputs: none

********************************************/
    {
        this.tileX = x;
        this.tileY = y;
        this.x = this.tileX * Tile.getWidth();
        this.y = this.tileY * Tile.getHeight();
        this.height= Tile.getHeight();
        this.width = Tile.getWidth();
        this.inScreen = false;
        maxDelay = 280;//7 seconds
        this.direction = direction;
        //determine increment

        if(direction == 1 || direction == 2)increment = Tile.getHeight()/(maxDelay*1.5);
        else{increment = Tile.getWidth() / (1.5*maxDelay);}
        baddie = new Enemy(type,vertSpeed,-1*horiSpeed,x,y);//so it looks like enemy is coming out from spawner(pipes are spawners)
        baddie.setX(baddie.getX()+baddie.getWidth());
        if(direction == 1)baddie.setY(baddie.getY() +Math.abs(baddie.getImgY()-baddie.getY()));
        if(direction ==2){baddie.setY(baddie.getY()+Math.abs((baddie.getImgY() -baddie.getY())));}

        
    }
    void update()
                            /******************************************

*Name: Tasbir Rahman

*Date: June 14 2016

*Function Name: update

*Description: updates spawner status, incrementing posiotn until it spawns enemy

*Inputs: enemy list

*Outputs: none

********************************************/
    {
        if(direction == 1)baddie.setY(baddie.getY() - increment);
        if(direction == 2)baddie.setY(baddie.getY() + increment);

      
        delay++;
        if(delay >maxDelay)
        {
            this.spawnBaddie();
        }

    }
    Enemy getBaddie()
                            /******************************************

*Name: Tasbir Rahman

*Date: June 14 2016

*Function Name: getBaddie

*Description: returns enemy that it spawns

*Inputs: none

*Outputs: spanwed enemy

********************************************/
    {
    return baddie;
    }
    void kill()
               /******************************************

*Name: Tasbir Rahman

*Date: June 14 2016

*Function Name: kill

*Description: nothing, opnly here because kill is abstract

*Inputs: none

*Outputs: none

********************************************/
    {}

    
}
