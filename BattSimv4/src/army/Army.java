package army;

import actor.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;

/**
 * A reference to object of type <b>Army</b> contains the following instance fields. armyName, a String representation
 * of the Army objects name. <br></br> armyForces a collection based ArrayList which contains the reference to
 * locations of the Army object's Actors.
 *
 * @author Liam McGovern
 * @version Assignment 3, Object Oriented Programming.d
 *          Project:  BattleField Simulator
 *          Lab Professor: David Houtman
 */
public class Army {
    /**
     * armyName is Army a String representing the Army object's name.
     */
    protected String armyName = "Unnamed Army";

    /**
     * armyForces will hold the 'Army' of Actor objects
     */
//    private ArrayList<Actor> armyForces;

    /**
     * <b>Army</b> constructor takes String armyName and to set the Army's name.
     *
     * @param armyName
     */



    //-----------Java FX Stuff-------------~
    private ObservableList<Actor> armyForces = FXCollections.observableArrayList(); //( new ArrayList<Actor>() ) ;

    private ListView<Actor> listView;
    private TableView<Actor> tableView;

    //-----------Java FX Stuff------------//-|



    public Army(String armyName) {
        this.armyName = armyName;
        //armyForces = new ArrayList<Actor>();
        //tableView=new TableView<Actor>();

        //-----------Java FX Stuff-------------~


        //List View
        listView=new ListView<Actor>();
        listView.setItems(armyForces);
        //Table View
        tableView = Actor.createTable();
        tableView.setItems(armyForces);
        //-----------Java FX Stuff------------//-|
    }

    /**
     * Constructor for army, that receives a name and automagically populates the army's forces based on the provided
     * quantityOfActors argument.
     *
     * @param armyName         String that will become the aArmy's name
     * @param quantityOfActors Quantity of actors to Populate the Army with.
     */
    public Army(String armyName, int quantityOfActors) {
        this.armyName = armyName;
//        armyForces = new ArrayList<Actor>(quantityOfActors);

        //-----------Java FX Stuff-------------~
        //armyForces = new ArrayList<Actor>();
        //armyForces = new ArrayList<Actor>();
        //tableView=new TableView<Actor>();

        //List View
        listView=new ListView<Actor>();
        listView.setItems(armyForces);
        //Table View
        tableView = Actor.createTable();
        tableView.setItems(armyForces);
        //-----------Java FX Stuff------------//-|
    }

    //-----------Java FX Stuff-------------~
    public void setArmyForces(ObservableList<Actor> armyForces) {
        this.armyForces = armyForces;
    }

    public ObservableList<Actor> getArmyForces() {
        return armyForces;
    }
    public TableView<Actor> getTableViewOfActors() {
        return tableView;
    }
    public Node getListViewOfActors() {
        return listView;
    }
    //-----------Java FX Stuff------------//-|



    /**
     * Populates the army with the provided arguments quantity. The choice of each is based on the probability of each
     * appearing. It makes sense that their is a higher probability to witness a Orc / Human in battle then a Wizard
     * so I have implemented the probability to address that, and to establish a realistic representation of
     * a live Battlefield.
     * This differs from populate because it automatically populates the army forces Randomly, without requiring a
     * predefined class. [Though the probabilities are predefined.]
     *
     * @param quantityOfActors the quantity of Actor's to create.
     * @param allegiance       the allegiance of the forces to fill the Army with.
     */
    public void fillArmy(int quantityOfActors, Army allegiance) {
        //Probabilities
        final double PROBABILITY_OF_ORC = 0.4;
        final double PROBABILITY_OF_HUMAN = 0.3;
        final double PROBABILITY_OF_HOBBIT = 0.25;
        final double PROBABILITY_OF_WIZARD = 0.05;

        /**
         * Really long winded method of implementing a probability based system, that generates actors
         * Currently rewriting a util Class to do the same thing, but as of Submission that is not prepared for
         * a release.
         */
        for (int i = 0; i < quantityOfActors; i++) {
            Actor tempActor;
            double tempNum = Math.random();
            double tempCount = 0;
            if (tempNum <= PROBABILITY_OF_WIZARD) {
                tempActor = new Wizard(allegiance);
                armyForces.add(tempActor);
            }
            tempCount += PROBABILITY_OF_WIZARD;
            if (tempNum > tempCount && tempNum <= tempCount + PROBABILITY_OF_ORC) {
                tempActor = new Orc(allegiance);
                armyForces.add(tempActor);
            }
            tempCount += PROBABILITY_OF_ORC;
            if (tempNum > tempCount && tempNum <= tempCount + PROBABILITY_OF_HUMAN) {
                tempActor = new Human(allegiance);
                armyForces.add(tempActor);
            }
            tempCount += PROBABILITY_OF_HUMAN;
            if (tempNum > tempCount && tempNum <= tempCount + PROBABILITY_OF_HOBBIT) {
                tempActor = new Hobbit(allegiance);
                armyForces.add(tempActor);
            }
        }

    }

    /**
     * Populates army with the type and quantity provided.
     *
     * @param typeOfActor Type of Actor (defined in ActorFactor)
     * @param numToAdd    quantity of Actors to populate forces with.
     */
    public void populate(ActorFactory.Type typeOfActor, int numToAdd, ObservableList<Node> listChildNodes) {
        for (int i = 0; i < numToAdd; i++) {
//            armyForces.add(typeOfActor.create(this));

            Actor tempActor = typeOfActor.create(this);
            //armyForces.add(tempActor);
            armyForces.add(tempActor);
            tempActor.setRandomLocationAroundPoint(new Point2D(Math.random()*800.0+30.0, Math.random()*100.0+23), 4.0);
            listChildNodes.add(tempActor.getBattlefieldAvatar());

        }
    }

    /**
     * Allows editing of Actor object at specified index.
     *
     * @param indexOfActorToEdit
     */
    public void edit(int indexOfActorToEdit) {
        Actor tempActor;
        tempActor = armyForces.get(indexOfActorToEdit);
        tempActor.inputAllFields();
        armyForces.set(indexOfActorToEdit, tempActor);
    }

    /**
     * Display each Actor to console (via toString)
     */
    public void display() {
        System.out.printf("Displaying %s's Forces", this.getName());
        for (Actor iActor : armyForces) {
            System.out.println(iActor);
        }
    }

    /**
     * Returns the size of the Army
     *
     * @return Returns the quantity of Actors in the Army.
     */
    public int getSize() {
        return armyForces.size();
    }

    /**
     * @return Returns the ArrayList object of the Army's Actor's
     */

//    public ArrayList<Actor> getArmyForces() {
//        return armyForces;
//    }

    /**
     * @return Returns the String representation of the Army
     */
    public String getName() {
        return armyName;
    }

    /**
     * Allows for the Army's forces to be reassigned to another ArrayList, it is however unlikely to be implemented.
     *
     * @param armyForces
     */
//    public void setArmyForces(ArrayList<Actor> armyForces) {
//        this.armyForces = armyForces;
//    }

    /**
     * Simple method to assign the respective Army's name
     *
     * @param armyName
     */
    public void setArmyName(String armyName) {
        this.armyName = armyName;
    }

}
