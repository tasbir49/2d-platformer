/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalisugame_tasbirrahman;

/**
 *
 * @author TASBIR
 */
class EnemyNodes
/******************************************

*Name: Tasbir Rahman

*Date: June 14, 2016

*Class Name: EnemyNodes

*Description: Nodes , data is enemy class, for linked list

********************************************/
{
    Enemy data;
    EnemyNodes next;
    EnemyNodes(Enemy data)
                    /******************************************

*Name: Tasbir Rahman

*Date: June 14 2016

*Function Name: construtor

*Description: Creaetes node with only data

*Inputs: data

*Outputs: none

********************************************/
    {
        this.next = null;
        this.data = data;
    }
    EnemyNodes(Enemy data, EnemyNodes next)
                    /******************************************

*Name: Tasbir Rahman

*Date: June 14 2016

*Function Name: constructor

*Description: creates node with data, and next reference

*Inputs: none

*Outputs: none

********************************************/
    {
        this.data = data;
        this.next = next;
    }
}
class EnemyLinkedList
/******************************************

*Name: Tasbir Rahman

*Date: June 14, 2016

*Class Name: EnemyLinkedList

*Description: made up of EnemyNodes

********************************************/
{
    EnemyNodes head;
    EnemyNodes tail;

    EnemyLinkedList()
                    /******************************************

*Name: Tasbir Rahman

*Date: June 14 2016

*Function Name: cosntrucyor

*Description: creates instance of class

*Inputs: none

*Outputs: none

********************************************/
    {
    }



    void addNode(Enemy data)
                    /******************************************

*Name: Tasbir Rahman

*Date: June 14 2016

*Function Name: addNode

*Description: adds node to node of list
*Inputs: data in node

*Outputs: none

********************************************/
    {
        if(head == null)
        {
            head = new EnemyNodes(data);
            tail = head;
        }
        else
        {
            tail.next = new EnemyNodes(data);
            tail = tail.next;
        }
    }
    int getCount()
                    /******************************************

*Name: Tasbir Rahman

*Date: June 14 2016

*Function Name: getCount

*Description: gives number of items in list

*Inputs: none

*Outputs: items in list as int

********************************************/
    {
        EnemyNodes temp = head;
        int count = 0;
        while(temp != null)
        {
            temp = temp.next;
            count++;
        }
        return count;
    }
    void deleteElement(int index)
                    /******************************************

*Name: Tasbir Rahman

*Date: June 14 2016

*Function Name: deleteElement

*Description: deletes item at certain index
*Inputs: index as int

*Outputs: none

********************************************/
    {
        EnemyNodes temp = head;
        if(index == 0)head = head.next;
        else if(index >0 && index < this.getCount())
        {
            for(int x = 0; x< index-1; x++)
            {
                temp = temp.next;
            }
            if(temp.next == this.tail)
            {
                temp.next = null;
                this.tail = temp;
            }
            else
            {
                temp.next = temp.next.next;
            }
        }
        
    }
    void reverseList()
                    /******************************************

*Name: Tasbir Rahman

*Date: June 14 2016

*Function Name: reverseList

*Description: reverses list in place

*Inputs: none

*Outputs: none

********************************************/
    {
        EnemyNodes current = this.head;
        EnemyNodes prev = null;
        EnemyNodes next  = null;
        while(current != null)
        {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        EnemyNodes temp = this.head;
        this.head = this.tail;
        this.tail = temp;
    }
}
