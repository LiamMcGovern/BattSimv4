package test;

import actor.*;
import army.Army;

/**
 * Test program to demonstrate that each newly created individual actor subclass (either a Orc or Human) has
 * unique modifiable attributes that can be displayed to terminal with a call to either toString, or naturally without.
 *
 * @author Liam McGovern [Input and SingletonRandom Classes are used with permission from Rex Woollard]
 * @version Assignment 3, Object Oriented Programming.
 *          Project:  BattleField Simulator
 *          Lab Professor: David Houtman
 */
public class ActorTest {
    public static void main(String[] args) {
        Army testArmy = new Army("ForcesOfAssignment3");

        //Create Objects of recently created types.

        Orc aOrc = new Orc(testArmy); //Demonstrates the calls to constructors on various layers of the hierarchy.
        Human aHuman = new Human(testArmy);

        //Testing of Orc object

        //Demonstrate indirect call to toString().
        System.out.printf("\nOrc object before modification, with randomly set instance fields.%S\n", aOrc);
        //Chance all fields
        aOrc.inputAllFields();

        aOrc.setIsRaging(!aOrc.getIsRaging());//Set the value to the opposite of it's current value.

        //Demonstrate boundary limit testing, and set and get Methods. Compounding into two statements.
        aOrc.setSize(aOrc.getSize() + 1000);
        aOrc.setSize(aOrc.getSize() - 1000);

        //Demonstrate direct call to toString(), and prove functionality of set and get methods.
        System.out.printf("\nOrc object attributes post change%S\n", aOrc.toString());


        //Testing of Human object

        //Demonstrate direct call to toString()
        System.out.printf("\nHuman object before modification, with randomly set instance fields.%S\n", aHuman.toString());
        //Chance all fields
        aHuman.inputAllFields();


        //Demonstrate boundary limit testing, and set and get Methods. Compounding into two statements.
        aHuman.setArmor(aHuman.getArmor() + 1000);
        aHuman.setArmor(aHuman.getArmor() - 1000);

        aHuman.setHasMount(!aHuman.getHasMount()); //Set the value to the opposite of it's current value.


        //Demonstrate indirect call to toString(), and prove functionality of set and get methods.
        System.out.printf("\nHuman object attributes post change%S\n", aHuman);

    }
}