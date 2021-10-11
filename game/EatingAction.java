package game;

import edu.monash.fit2099.engine.*;

/**
 * This class is responsible for allowing the dinosaur to eat.
 */

public class EatingAction extends Action {
    private Item item;
    private Location location;

    /**
     * Constructor that instantiates the action
     *
     * @param item     the item to be eaten
     * @param location the location where the item is
     */
    public EatingAction(Item item, Location location) {
        this.item = item;
        this.location = location;
    }

    /**
     * Responsible to perform the action
     *
     * @param actor The actor performing the action.
     * @param map   The map the actor is on.
     * @return a string telling us where the actor ate the corpse
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        map.moveActor(actor, location);
        location.removeItem(item);
        Dinosaur dinosaur = (Dinosaur) actor;
        dinosaur.getFoodLevel().increaseLevel(item.getFoodLevel().getValue());
        return String.format("%s ate %s at (%d, %d)", actor.toString(), item.toString(), map.locationOf(actor).x(), map.locationOf(actor).y());
    }

    @Override
    public String menuDescription(Actor actor) {
        return null;
    }
}
