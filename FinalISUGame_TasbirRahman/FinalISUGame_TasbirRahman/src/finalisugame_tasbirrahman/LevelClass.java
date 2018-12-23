/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalisugame_tasbirrahman;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.Clip;

/**
 *
 * @author TASBIR
 */
public class LevelClass 
/******************************************

*Name: Tasbir Rahman

*Date: June 14, 2016

*Class Name: LevelClass

*Description: Stores data about individual levels

********************************************/
{
    Tile[][] decorTiles;
    Block[][] collisionBlocks;
    ShellList shells = new ShellList();
    EnemySpawner[] spawnerArray;
    CoinLinkedList coins = new CoinLinkedList();
    int xSize;
    int ySize;
    EnemyLinkedList baddieList = new EnemyLinkedList();
 
    LevelClass(Tile[][] decorTiles, Block[][] collisionTiles, int xSize, int ySize,EnemySpawner[] spawner)
         /******************************************

*Name: Tasbir Rahman

*Date: June 14 2016

*Function Name: constructor

*Description: creates level instance with all decorations ,blocks and dimensions
*Inputs: Tile array, block array, two integers for dimensions as tiles

*Outputs: none

********************************************/
    {
        this.spawnerArray = spawner;
        for(int x = 0;x<spawnerArray.length;x++)
        {
            spawnerArray[x].setLevelList(this.baddieList);
        }
        this.xSize = xSize;
        this.ySize = ySize;
        this.decorTiles = decorTiles;
        this.collisionBlocks = collisionTiles;
    }
    int getXSizeAsPixels()
                 /******************************************

*Name: Tasbir Rahman

*Date: June 14 2016

*Function Name:getXSizeAsPixels

*Description: reeturns width of level as pixels
*Inputs: none

*Outputs: integer showing width

********************************************/
    {
        return xSize * 32;
    }
    int getYSizeAsPixels()
                     /******************************************

*Name: Tasbir Rahman

*Date: June 14 2016

*Function Name:getYSizeAsPixels

*Description: reeturns heiggt of level as pixels
*Inputs: none

*Outputs: integer showing height

********************************************/
    {
        return ySize * 32;
    }
    int getXLength()
                     /******************************************

*Name: Tasbir Rahman

*Date: June 14 2016

*Function Name:getXLength

*Description: reeturns width of level as tiles
*Inputs: none

*Outputs: integer showing width

********************************************/
    {
        return xSize;
    }
    int getYLength()
             /******************************************

*Name: Tasbir Rahman

*Date: June 14 2016

*Function Name:getYLength

*Description: reeturns height of level as tiles
*Inputs: none

*Outputs: integer showing height

********************************************/
    {
        return ySize;
    }
    Tile[][] getDecorTileList()
             /******************************************

*Name: Tasbir Rahman

*Date: June 14 2016

*Function Name:getDecorTileList

*Description: reeturns array of decorTiles
*Inputs: none

*Outputs: Tile array

********************************************/
    {
        return decorTiles;

    }
    
    Block[][] getCollisionBlockList()
             /******************************************

*Name: Tasbir Rahman

*Date: June 14 2016

*Function Name:getCollisionBlockList

*Description: reeturns array of collision blocks
*Inputs: none

*Outputs: Block array

********************************************/
    {
        return collisionBlocks;

    }
    EnemyLinkedList getEnemyList()
                /******************************************

*Name: Tasbir Rahman

*Date: June 14 2016

*Function Name: getEnemyList

*Description: returns enemy linked list

*Inputs: none

*Outputs: enemy linked list

********************************************/
    {
        return this.baddieList;
    }
    ShellList getShellList()
                /******************************************

*Name: Tasbir Rahman

*Date: June 14 2016

*Function Name: getShellList

*Description: returns shell linked list

*Inputs: none

*Outputs: shell linked list

********************************************/
    {
        return this.shells;
    }
    CoinLinkedList getCoinList()
                /******************************************

*Name: Tasbir Rahman

*Date: June 14 2016

*Function Name: getCoinList

*Description: returns coin linked list

*Inputs: none

*Outputs: coin  linked list

********************************************/
    {
        return this.coins;
    }
    EnemySpawner[] getSpawnerArray()
                /******************************************

*Name: Tasbir Rahman

*Date: June 14 2016

*Function Name: getSpawnerArray

*Description: returns enemy Spanwr array

*Inputs: none

*Outputs: enemySpawner array

********************************************/
    {
        return this.spawnerArray;
    }

}
