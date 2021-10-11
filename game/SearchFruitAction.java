package game;

import edu.monash.fit2099.engine.*;

/**
 * This class represents an action that allows a player to search for fruit from the tree
 */
public class SearchFruitAction extends PickUpItemAction {

    static final double PROBABILITY_TO_FIND_FRUIT = 0.4;

    /**
     * Constructor to set fruit as the item to search
     *
     * @param item Fruit to search for
     */
    public SearchFruitAction(Item item) {
        super(item);
    }

    /**
     * Handles the searching of fruit
     *
     * @param actor The actor performing the action.
     * @param map   The map the actor is on.
     * @return Message indicating whether fruit was found or not
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        final double PROBABILITY = Math.random();
        String output = "";
        if (PROBABILITY <= PROBABILITY_TO_FIND_FRUIT) {
            super.execute(actor, map);
            output += actor + " successfully retrieves fruit from the tree";
        } else {
            output += "You search the tree for fruit, but you can’t find any ripe ones";
        }
        return output;
    }

    /**
     * Message to let the player know that they can search the tree for fruit
     *
     * @param actor The actor performing the action.
     * @return String containing the message
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " searches for fruit from the tree.";
    }
}

