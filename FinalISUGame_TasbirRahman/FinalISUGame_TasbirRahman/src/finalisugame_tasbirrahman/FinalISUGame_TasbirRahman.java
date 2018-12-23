    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalisugame_tasbirrahman;
/******************************************

*Name: Tasbir Rahman

*Date: June 14, 2016

*Title: finalisugame_tasbirrahman

*Description: a 2d mario like platformer where the goal is to get all coins, made using raptou's template with some major modifications

********************************************/
import java.awt.Color;
import javax.swing.JFrame;

/**
 *
 * @author TASBIR
 */
public class FinalISUGame_TasbirRahman {

    /******************************************

*Name: Tasbir Rahman

*Date: June 14, 2016

*Class Name: FinalISUGame_TasbirRahman

*Description: Entry point for program

********************************************/
    public static void main(String[] args)
            /******************************************

*Name: Tasbir Rahman

*Date: June 14 2016

*Function Name: main

*Description: entry point
*Inputs: none

*Outputs: none

********************************************/
    {
        JFrame gameWindow = new JFrame();
        gameWindow.setSize((int)(640), 480);
        gameWindow.setBackground(Color.YELLOW);
        gameWindow.setName("Tasbir Is Awesome");
        gameWindow.add(new Game());
        gameWindow.setResizable(false);
                gameWindow.setVisible(true);

        gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
}
