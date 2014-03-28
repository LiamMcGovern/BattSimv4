package actor;

import army.Army;
import com.sun.javafx.fxml.builder.TriangleMeshBuilder;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.CircleBuilder;
import javafx.scene.shape.PolygonBuilder;
import javafx.scene.shape.RectangleBuilder;
import util.InputGUI;


/**
 * A reference to object of type <b>Wizard</b>, inherits all values from the super class <i>Actor</i>, and along with
 * these it contains values representing the Wizard's possession of both a Horse and Staff, each being boolean values.
 * Upon calling the constructor, presence of either asset is determined by a probability statement.
 * This statement is the result of a comparison between a random number (0-1) and a predefined constant representing
 * the probability.
 * <p/>
 *
 * @author Liam McGovern
 * @version Assignment 3, Object Oriented Programming.
 *          Project:  BattleField Simulator
 *          Lab Professor: David Houtman
 */

public class Wizard extends Actor {

    private boolean hasHorse;
    /**
     * Represents <b>Wizards</b> possession of a horse.
     */
    private boolean hasStaff;/** Represents <b>Wizards</b> possession of a staff. */

    /**
     * <b>Wizard</b>, inherits all attributes of <i>Actor</i> and two of it's own: <br> <u>hasHorse</u> a boolean value
     * representing the <b>Wizard</b> possession of a horse.<br></br> <u>hasStaff</u> a boolean value representing the
     * <b>Wizards</b> possession of his staff.
     * <br></br> Both values are initialized with a value based on a predetermined chance
     * (CHANCEOFHORSE + CHANCEOFSTAFF)
     */
    public Wizard() {
        super(); //Upon a call of the Wizard constructor the Wizard inherits all of the fields of Actor + all Below.

        //Given that these following variables are not needed beyond the scope of the constructor, it would be poor
        //programming practise to declare them as instance fields.
        /** {@value} */
        final double CHANCEOFHORSE = 0.91;//Probability of starting with a horse
        /** {@value} */
        final double CHANCEOFSTAFF = 0.13;//Probability of starting with a horse

        //The below operations generate a random number in order between 0.0 and 1.0, then compare that number to the
        //predefined chance value, to result in a boolean value that represent the Wizard's possession of the
        //corresponding asset.

        //[-----------------RANDOM NUMBER[0-1]------ | Less than  \/ chance? | chance value |
        boolean hasHorse = (Math.random() < CHANCEOFHORSE); //91% Chance.
        boolean hasStaff = (Math.random() < CHANCEOFSTAFF);  //13% Chance.
    }

    public Wizard(Army allegiance) {
        super(allegiance);
        //Upon a call of the Wizard constructor the Wizard inherits all of the fields of Actor + all Below.
        //Given that these following variables are not needed beyond the scope of the constructor, it would be poor
        //programming practise to declare them as instance fields.
        /** {@value} */
        final double CHANCEOFHORSE = 0.91;//Probability of starting with a horse
        /** {@value} */
        final double CHANCEOFSTAFF = 0.13;//Probability of starting with a horse

        //The below operations generate a random number in order between 0.0 and 1.0, then compare that number to the
        //predefined chance value, to result in a boolean value that represent the Wizard's possession of the
        //corresponding asset.

        //[-----------------RANDOM NUMBER[0-1]------ | Less than  \/ chance? | chance value |
        boolean hasHorse = (Math.random() < CHANCEOFHORSE); //91% Chance.
        boolean hasStaff = (Math.random() < CHANCEOFSTAFF);  //13% Chance.
    }





    //-----------Java FX Stuff-------------~
    protected Node buildBattleFieldAvatar() {
        return CircleBuilder.create().radius(5.0).strokeWidth(2.0).fill(Color.GREY).build();
    }

    //-----------Java FX Stuff------------//-|


    /**
     * Returns a formatted string of the <b>Wizards</b> attributes including it's possessions (Staff or horse).
     *
     * @return Returns a formatted string version of the <b>Wizard</b> attributes.
     * @see actor.Wizard
     */
    @Override //Override the Superclass's  (Actor) toString Method.
    public String toString() {
        return String.format(super.toString() + "\t Has Horse:%5b \t Has Staff:%5b \t", getHasHorse(), getHasStaff());
    }//toString, used either directly or in the absence of a toString call for the object.

    /**
     * Allows user input of all the attributes of parent <i>Actor</i> and the <b>Wizard</b> object.
     */
    @Override //Override the Superclass's  (Actor) inputAllFields Method.
    public void inputAllFields() {
        super.inputAllFields();
        setHasHorse(InputGUI.getBooleanGUI(String.format("Is %s riding a horse?", super.getName())));
        setHasStaff(InputGUI.getBooleanGUI(String.format("Does %s possess a staff?", super.getName())));
    }

    /**
     * Returns a boolean value representing the <b>Wizards</b> possession of a Horse.
     *
     * @return Returns a boolean value representing the <b>Wizards</b> possession of a Horse.
     */
    public boolean getHasHorse() {
        return this.hasHorse;
    }

    /**
     * Returns a boolean value representing the <b>Wizards</b> possession of a Staff.
     *
     * @return Returns a boolean value representing the <b>Wizards</b> possession of a Staff.
     */
    public boolean getHasStaff() {
        return this.hasStaff;
    }

    /**
     * Permits change to <b>Wizard</b> possession of a Horse.
     *
     * @param hasHorse Boolean value representing the possession of a Horse.
     */
    public void setHasHorse(boolean hasHorse) {
        this.hasHorse = hasHorse;
    }

    /**
     * Permits change to <b>Wizard</b> possession of a Staff.
     *
     * @param hasStaff Boolean value representing the possession of a Staff.
     */
    public void setHasStaff(boolean hasStaff) {
        this.hasStaff = hasStaff;
    }

}