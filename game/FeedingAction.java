package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;

/**
 * Class that allows a player to feed another actor
 */
public class FeedingAction extends Action {
    private Actor target;
    private Item item;

    /**
     * Constructor
     *
     * @param target Actor to feed
     * @param item   Item to feed
     */
    FeedingAction(Actor target, Item item) {
        this.target = target;
        this.item = item;
    }

    /**
     * Handles the game logic behind feeding. i.e. To remove the item fed from the inventory of the player,
     * increase the foodLevel of the target actor based on the corresponding foodLevel of the item and
     * to increase the player's ecopoints accordingly.
     *
     * @param actor The actor performing the action.
     * @param map   The map the actor is on.
     * @return Message informing what was fed to the target and their new foodLevel
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        int pointsIncreaseOfHay = 10;
        int pointsIncreaseOfFruit = 15;
        if (target instanceof Dinosaur) {
            Dinosaur dinosaur = (Dinosaur) target;
            dinosaur.getFoodLevel().increaseLevel(item.getFoodLevel().getValue());
            actor.removeItemFromInventory(item);
            if (item instanceof Hay)
                Player.getEcoPoints().increasePoints(pointsIncreaseOfHay);
            else if (item instanceof Fruit)
                Player.getEcoPoints().increasePoints(pointsIncreaseOfFruit);
            return item.toString() + " fed to " + target.toString() + ". Food level now at " + dinosaur.getFoodLevel().getValue();
        }
        return null;
    }

    /**
     * Returns the menu description of the feeding action
     *
     * @param actor The actor performing the action.
     * @return String describing what the target can be fed
     */
    @Override
    public String menuDescription(Actor actor) {
        return "Feed " + item.toString();
    }
}
