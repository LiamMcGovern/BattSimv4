package actor;// N.B.  Use of default package at this point in
// the program would indicate a lack of knowledge on the part of the student!

import army.Army;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;

import util.InputGUI;
import util.SingletonRandom;

import java.lang.String;

/**
 * A reference to object Actor, has the following attributes
 * A name with it's coinciding serial number, incremented at each call to the constructor.
 * Health, Speed, and Strength.
 * <b>Health</b>, <b>Speed</b> and <b>Strength</b> are represented by a double value between 0 and 100,
 * this is based on the fact that a percentage is a logical way of measuring Health, Speed, and Strength.
 * <p/>
 *
 * @author Liam McGovern [Input and SingletonRandom Classes are used with permission from Rex Woolard]
 * @version Assignment 3, Object Oriented Programming.
 *          Project:  BattleField Simulator
 *          Lab Professor: David Houtman
 */
public abstract class Actor {

    private Army actorAllegiance;
//	public abstract boolean isVisible();

    /**
     * Defining the Maximum and Minimum values for each attribute
     * it was decided that 1-100 is valid since a percentage style representation is very versatile.
     */
    /**
     * {@value}
     */
    public final static double MAX_HEALTH = 100.0;
    /**
     * {@value}
     */
    public final static double MIN_HEALTH = 1.0;
    /**
     * {@value}
     */

    public final static double MAX_SPEED = 100.0;
    /**
     * {@value}
     */
    public final static double MIN_SPEED = 1.0;
    /**
     * {@value}
     */

    public final static double MAX_STRENGTH = 100.0;
    /**
     * {@value}
     */
    public final static double MIN_STRENGTH = 1.0;

    /**
     * actorSerialNumber a class variable
     * used to assign each actor a unique number, static since it should not be unique to each actor.
     */
    public static SimpleIntegerProperty actorSerialNumber = new SimpleIntegerProperty(0);

    //Actor instance variables
    /**
     * A string object initialized with the value of 'Actor'
     */
    private SimpleStringProperty name = new SimpleStringProperty("Actor");



    /**
     * actorId represents each Actors unique number
     */
    private SimpleIntegerProperty actorId;

    /**
     * Actor Strength....
     */
    private SimpleDoubleProperty strength;
    /**
     * Actor Speed....
     */
    private SimpleDoubleProperty speed;

    /**
     * Actor Health...
     */
    protected SimpleDoubleProperty health;



    //-----------Java FX Stuff-------------~
    private Tooltip tooltip = new Tooltip(); //Adds popup

    private Node battlefieldAvatar; //Each shape on the map is a node... I think
    protected abstract Node buildBattleFieldAvatar(); //forces other classes to implement this method.

    //-----------Java FX Stuff------------//-|


    /**
     * <i>Actor</i> constructor establishes values for each attribute based on the generation of a random number.
     * The <i>Actor</i> has a name and serial number associated with it, to keep track of the various actors.
     */
    Actor() {
        actorSerialNumber.set(actorSerialNumber.get() + 1);//Increased upon each instantiation to correlate with the quantity of Actors created.
        actorId = actorSerialNumber; //Make id = value of actorSerialNumber
        name.set(String.format("%s%d", name, actorId)); //Combining name with id
        //Actor instance variables are automagically instantiated with random values between the defined limits.

        //-----------Attributes-------------
        //Generate random numbers for each attribute field.
        //SingletonRandom is Prof. Woolard's random number generator.
        strength.set(SingletonRandom.instance.getNormalDistribution(MIN_STRENGTH, MAX_STRENGTH, 3));
        speed.set(SingletonRandom.instance.getNormalDistribution(MIN_SPEED, MAX_SPEED, 3));
        health.set(SingletonRandom.instance.getNormalDistribution(MIN_HEALTH, MAX_HEALTH, 3));
        //-----------Attributes-------------

        //-----------Java FX Stuff-------------~
        battlefieldAvatar= buildBattleFieldAvatar();
        adjustAvatarBasedOnActorAttributes(); // New code to insert.
        Tooltip.install(battlefieldAvatar, tooltip); // New code to insert.
        //-----------Java FX Stuff------------//-|

    }

    /**
     * <i>Actor</i> constructor establishes values for each attribute based on the generation of a random number.
     * The <i>Actor</i> has a name and serial number associated with it, to keep track of the various actors.
     *
     * @param allegiance argument value will be associated with the Actor object's actorAllegiance field.
     */
    Actor(Army allegiance) {
        this.actorAllegiance = allegiance; //Allow's for the Actor's allegiance to be set to a specific Army.
//        System.out.println("Actor constructor called.");

        actorSerialNumber.set(actorSerialNumber.get() + 1);//Increased upon each instantiation to correlate with the quantity of Actors created.
        actorId = actorSerialNumber; //Make id = value of actorSerialNumber
        name.set(String.format("%s%d", name, actorId)); //Combining name with id
        //Actor instance variables are automagically instantiated with random values between the defined limits.

        //-----------Attributes-------------
        //Generate random numbers for each attribute field.
        //SingletonRandom is Prof. Woolard's random number generator.
        strength.set(SingletonRandom.instance.getNormalDistribution(MIN_STRENGTH, MAX_STRENGTH, 3));
        speed.set(SingletonRandom.instance.getNormalDistribution(MIN_SPEED, MAX_SPEED, 3));
        health.set(SingletonRandom.instance.getNormalDistribution(MIN_HEALTH, MAX_HEALTH, 3));
        //-----------Attributes-------------

        //-----------Java FX Stuff-------------~
        battlefieldAvatar= buildBattleFieldAvatar();
        adjustAvatarBasedOnActorAttributes(); // New code to insert.
        Tooltip.install(battlefieldAvatar, tooltip); // New code to insert.
        //-----------Java FX Stuff------------//-|
    }

    /**
     * Allows user to input all attributes of <i>Actor</i> Object.
     */
    public void inputAllFields() {
        //Utilizes the set methods to pass input to the coinciding variables.
        //Each value is checked by the corresponding set methods.
        System.out.println("You will now be asked to input the various fields of the chosen Actor");
        setName(InputGUI.getString((String.format("Input %s's Name", this.name.get())))); //change the name
        //Change the attributes
        setHealth(InputGUI.getDouble(String.format("Input %s's Health [This must be between %4.1f and %4.1f]",
                this.name.get(), MAX_HEALTH, MIN_HEALTH), MIN_HEALTH, MAX_HEALTH));
        setSpeed(InputGUI.getDouble((String.format("Input %s's Speed [This must be between %4.1f and %4.1f]",
                this.name.get(), MAX_SPEED, MIN_SPEED)), MIN_SPEED, MAX_SPEED));
        setStrength(InputGUI.getDouble((String.format("Input %s's Strength [This must be between %4.1f and %4.1f]",
                this.name.get(), MAX_STRENGTH, MIN_STRENGTH)), MIN_STRENGTH, MAX_STRENGTH));
        //This uses Prof. Woolard's Input parsing class to capture input.
    }

    @Override //This method overrides Java's inherited Object.toString method.
    //toString, used either directly or in the absence of a toString call for the object.
    /**
     * Creates a String based on the attributes of the actor.
     * @return Returns a formatted string, containing the Actor's attributes.
     */
    public String toString() {
        return (actorAllegiance == null) ? String.format("Name: %-4s Health:%4.1f \t Speed:%4.1f \t Strength:%4.1f",
                this.name.get(), this.health.get(), this.speed.get(), this.strength.get())
                : String.format("Name: %-8s \t Army:%s \t Health:%4.1f \t Speed:%4.1f \t Strength:%4.1f",
                this.name.get(), actorAllegiance.getArmyName(),this.health.get(),this.speed.get(),this.strength.get());
    }

    //----------get methods
    //Standard getMethods, return the value of the calling objects fields.s

    //-----------Java FX Stuff-------------~
    public Node getBattlefieldAvatar() {
        return battlefieldAvatar;
    }

    public void setRandomLocationAroundPoint(Point2D ptCenterOfDistribution, double spread) {
        final double range = 100.0/2.0;
        battlefieldAvatar.setTranslateX(SingletonRandom.instance.getNormalDistribution
                (ptCenterOfDistribution.getX()-range, ptCenterOfDistribution.getX()+range, spread));
        battlefieldAvatar.setTranslateY(SingletonRandom.instance.getNormalDistribution
                (ptCenterOfDistribution.getY()-range, ptCenterOfDistribution.getY()+range, spread));
    }

    private void adjustAvatarBasedOnActorAttributes() {
        //set size of shape based on Health and strength
        battlefieldAvatar.setScaleX((getStrength()+getHealth())/100.0);
        battlefieldAvatar.setScaleY((getStrength()+getHealth())/100.0);

        tooltip.setText(this.toString()); //fill contents of tooltip with toString contents.
    }

    public static TableView<Actor> createTable() {
        TableView<Actor> table = new TableView<Actor>();
        final double PREF_WIDTH_DOUBLE = 50.0;
        table.setPrefWidth(PREF_WIDTH_DOUBLE*8.0);
        table.setEditable(true);
        TableColumn<Actor, String> nameCol      = new TableColumn<>("Name");      nameCol.setCellValueFactory
                (new PropertyValueFactory<Actor, String>("name"));      nameCol.setPrefWidth(PREF_WIDTH_DOUBLE*2.0);
        TableColumn<Actor, Double> strengthCol  = new TableColumn<>("Strength");  strengthCol.setCellValueFactory
                (new PropertyValueFactory<Actor, Double>("strength"));  strengthCol.setPrefWidth(PREF_WIDTH_DOUBLE);
        TableColumn<Actor, Double> speedCol     = new TableColumn<>("Speed");     speedCol.setCellValueFactory
                (new PropertyValueFactory<Actor, Double>("speed"));     speedCol.setPrefWidth(PREF_WIDTH_DOUBLE);
        TableColumn<Actor, Double> healthCol    = new TableColumn<>("Health");    healthCol.setCellValueFactory
                (new PropertyValueFactory<Actor, Double>("health"));    healthCol.setPrefWidth(PREF_WIDTH_DOUBLE);
        table.getColumns().addAll(nameCol, strengthCol, speedCol, healthCol);
        return table; // end createTable()
    }




    //-----------END Java FX Stuff------------//-|



    /**
     * Returns selected actors name
     *
     * @return Returns actor's name
     */
    public String getName() {
        return this.name.get();
    }

    /**
     * Returns selected actors health
     * *
     *
     * @return Returns actor's health
     */
    public double getHealth() { //getHealth returns the respective value of health to the object it's called by.
        return this.health.get();
    }

    /**
     * Returns selected actors strength
     *
     * @return Returns actor's strength
     */
    public double getStrength() {//getStrength returns the respective value of health to the object it's called by.
        return this.strength.get();
    }


    /**
     * Returns selected actors speed
     *
     * @return Returns actor's speed
     */
    public double getSpeed() {//getSpeed returns the respective value of health to the object it's called by.
        return this.speed.get();
    }

    //----------get methods

    //----------set methods

    /**
     * Sets Actor's name
     *
     * @param name Value to set name to
     * @see actor.Actor
     */
    public void setName(String name) {
        this.name.set(String.format("%s%d", name, actorId)); //Set name and format it with the proper id number.
    }

    //-----Attribute set methods----------

    //Each double Attribute is checked against the corresponding predefined limit
    //If it exceeds either limit the value is set to the nearest limit and the user is notified.
    //Otherwise the selected field is set to the specified value.

    public void setActorAllegiance(Army actorAllegiance) {
        this.actorAllegiance = actorAllegiance;
    }

    /**
     * Sets the value of the Actor's health.
     * If the parameter inputed is greater than the limit, it sets it to the nearest limit.
     *
     * @param speed Value to set speed to
     * @see actor.Actor
     */
    public void setSpeed(double speed) {
        //If user input exceeds limit, set it to nearest limit
        if (speed > MAX_SPEED) {
            System.out.printf("The entered Speed is greater than specified limits," +
                    " setting the value to defined max %.1f instead %n", MAX_SPEED);
            this.speed.set(MAX_SPEED);
        } else if (speed < MIN_SPEED ) {
            System.out.printf("The entered Speed is lower than specified limits," +
                    " setting the value to defined min %.1f instead %n", MIN_SPEED);
            this.speed.set(MIN_SPEED);
        } else {
            this.speed.set(speed); //If user input is valid set Attribute to that value.
        }
    }

    /**
     * Sets the value of the Actor's health.
     * If the parameter inputed is greater than the limit, it sets it to the nearest limit.
     *
     * @param health Value to set health to
     * @see actor.Actor
     */
    public void setHealth(double health) {
        //If user input exceeds limit, set it to nearest limit
        if (health > MAX_HEALTH) {
            System.out.printf("The entered Health value is greater than specified limits," +
                    " setting the value to defined max %.1f instead %n", MAX_HEALTH);
            this.health.set(MAX_HEALTH);
        } else if (health < MIN_HEALTH) {
            System.out.printf("The entered Health value is lower than specified limits," +
                    " setting the value to defined min %.1f instead %n", MIN_HEALTH);
            this.health.set(MIN_HEALTH);
        } else {
            this.health.set(health);//If user input is valid set Attribute to that value.
        }
    }

    /**
     * Sets the value of the Actor's strength.
     * If the parameter inputed is greater than the limit, it sets it to the nearest limit.
     *
     * @param strength Value to set strength to
     * @see actor.Actor
     */
    public void setStrength(double strength) {
        //If user input exceeds limit, set it to nearest limit
        if (strength > MAX_STRENGTH) {
            System.out.printf("The entered Strength is greater than specified limits," +
                    " setting the value to defined max %.1f instead %n", MAX_STRENGTH);
            this.strength.set(MAX_STRENGTH);
        } else if (strength < MIN_STRENGTH) {
            System.out.printf("The entered Strength is lower than specified limits," +
                    " setting the value to defined min %.1f instead %n", MIN_STRENGTH);
            this.strength.set(MIN_STRENGTH);
        } else {
            this.strength.set(strength);//If user input is valid set Attribute to that value.
        }
    }
    //-----Attribute set methods----------

    /**
     * Returns true if the actors has at least a quarter of it's health remaining, otherwise it returns false.
     *
     * @return Return's a boolean value that represents the Object's ability to move based on it's health.
     * @see actor.Actor
     */
    public boolean isHealthyEnoughToMove() {
        final double healthToLowToMove = (MAX_HEALTH * 0.10);
        return (this.health.get() > healthToLowToMove) ? true : false;
    }
}
