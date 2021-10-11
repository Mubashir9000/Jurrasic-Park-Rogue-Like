package game;

import edu.monash.fit2099.engine.*;

/**
 * This class is responsible for allowing the dinosaur to graze on grass.
 */

public class GrazingGrassAction extends Action {
    private Location location;

    /**
     * Constructor that instantiates the action
     *
     * @param location the location where the grass is
     */

    public GrazingGrassAction(Location location) {
        this.location = location;
    }

    /**
     * Responsible to perform the action
     *
     * @param actor The actor performing the action.
     * @param map   The map the actor is on.
     * @return a string telling us where the actor grazed on the grass
     */

    @Override
    public String execute(Actor actor, GameMap map) {
        map.moveActor(actor, location);
        Dinosaur dinosaur = (Dinosaur) actor;
        dinosaur.getFoodLevel().increaseLevel(Grass.FOOD_LEVEL.getValue());
        return String.format("%s ate grass at (%d, %d)", actor.toString(), map.locationOf(actor).x(), map.locationOf(actor).y());
    }

    @Override
    public String menuDescription(Actor actor) {
        return null;
    }
}
