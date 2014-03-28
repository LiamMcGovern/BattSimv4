package actor;

import army.Army;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.CircleBuilder;
import javafx.scene.shape.RectangleBuilder;
import util.InputGUI;
import util.SingletonRandom;

/**
 * A reference to object of type <b>Human</b>, inherits all values from the super class <i>Actor</i>, and along with
 * these it contains values representing the Human state of hasMount. A armor double value has been introduced to this
 * class which represents a form of additional health.
 * Upon calling the constructor, presence of either asset is determined by a probability statement.
 * This statement is the result of a comparison between a random number (0-1) and a predefined constant representing
 * the probability.
 *
 * @author Liam McGovern
 * @version Assignment 3, Object Oriented Programming.
 *          Project:  BattleField Simulator
 *          Lab Professor: David Houtman
 */
public class Human extends Actor {
    /**
     * armor double value represents the quantity of armor a <b>Human</b> possess. It will act as extra health.
     */
    private double armor;
    /**
     * {@value}
     */
    private final double MAX_ARMOR = 50.0;
    /**
     * {@value}
     */
    private final double MIN_ARMOR = 0.0;
    /**
     * {@value}
     */
    private boolean hasMount;

    /**
     * <b>Human</b> constructor calls the <i>Actor</i> constructor, then establishes a random value for the
     * new <b>armor</b> instance field.
     */
    public Human() {
        super();
        final double CHANCEOFMOUNT = 0.42;
        setHasMount(Math.random() < CHANCEOFMOUNT); //91% Chance.
        setArmor(SingletonRandom.instance.getNormalDistribution(MIN_ARMOR, MAX_ARMOR, 5.0));
    }

    /**
     * Allows for a Human to be created with the a defined allegiance.
     *
     * @param allegiance the location information for a army object.
     */
    public Human(Army allegiance) {
        super(allegiance);
//        System.out.println("Human constructor called.");
        final double CHANCEOFMOUNT = 0.42;
        setHasMount(Math.random() < CHANCEOFMOUNT); //91% Chance.
        setArmor(SingletonRandom.instance.getNormalDistribution(MIN_ARMOR, MAX_ARMOR, 5.0));
    }



    //-----------Java FX Stuff-------------~
    protected Node buildBattleFieldAvatar() {
        return RectangleBuilder.create().strokeWidth(2.0).fill(Color.YELLOW).build();
    }

    //-----------Java FX Stuff------------//-|



    /**
     * Returns a formatted string containing all Attributes of <i>Actor</i> and <b>Human</b>
     *
     * @return Returns a <i>String</i> String representation of the <b>Human</b>.
     */
    @Override //Override the Superclass's  (Actor) toString Method.
    public String toString() {
        return String.format(super.toString() + "\t Armor:%4.1f \t Has Mount:%b", getArmor(), getHasMount());
    }

    /**
     * Allows user to input all value's of the <b>Human</b> object and its parent <i>Actor</i>
     */
    @Override //Override the Superclass's  (Actor) inputAllFields Method.
    public void inputAllFields() {
        super.inputAllFields();
        setHasMount(InputGUI.getBooleanGUI(String.format("Is %s riding a Mount?", super.getName())));
        setArmor(InputGUI.getDouble((String.format("Input %s's Armor [This must be between %4.1f and %4.1f]",
                super.getName(), MAX_ARMOR, MIN_ARMOR)), MIN_ARMOR, MAX_ARMOR));
    }

    /**
     * @param hasMount returns a boolean representing the presence or lack thereof of a mount.
     */
    public void setHasMount(boolean hasMount) {
        this.hasMount = hasMount;
    }

    /**
     * Checks the parameter value against predefined limits, and if within sets the Human's armor field to the
     * proper value.
     *
     * @param armor Value to be assigned to the Human's armor field.
     * @see actor.Human
     */
    public void setArmor(double armor) {
        if (armor > MAX_ARMOR) {
            System.out.printf("The entered Armor is greater than specified limits," +
                    " setting the value to defined max %.1f instead %n", MAX_ARMOR);
            this.armor = MAX_ARMOR;
        } else if (armor < MIN_ARMOR) {
            System.out.printf("The entered Armor is lower than specified limits," +
                    " setting the value to defined min %.1f instead %n", MIN_ARMOR);
            this.armor = MIN_ARMOR;
        } else {
            this.armor = armor;//If user input is valid set Attribute to that value.
        }
    }

    /**
     * Returns the value of the matching Object's armor field.
     *
     * @return Returns the Human's armor value (double).
     * @see actor.Human
     */
    public double getArmor() {
        return this.armor;
    }

    /**
     * @return Returns a boolean value representing the Human's possession of a Mount.
     */
    public boolean getHasMount() {
        return this.hasMount;
    }

}
