package game;

import edu.monash.fit2099.engine.*;

/**
 * A class that makes the Dinosaur drink water.
 */
public class ThirstyBehaviour implements Behaviour {
    /**
     * Decides and returns DrinkingWaterAction
     * If no Action is possible, returns null.
     *
     * @param actor the Actor enacting the behaviour
     * @param map   the map that actor is currently on
     * @return DrinkingWaterAction, or null if no Action is possible
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        Location here = map.locationOf(actor);
        if (isNextToWater(here)) {
            for (Exit exit : here.getExits()) {
                Location destination = exit.getDestination();
                if (destination.getGround() instanceof Water) {
                    return new DrinkingWaterAction();
                }
            }
        }
        return null;
    }

    /**
     * Find out if water is nearby.
     *
     * @param location where the actor is standing
     * @return True if at least 1 instance of Water is found
     */
    private boolean isNextToWater(Location location) {
        boolean flag = false;
        int counter = 0;
        for (Exit exit : location.getExits()) {
            if (exit.getDestination().getGround() instanceof Water) {
                counter++;
            }
        }
        if (counter >= 1) {
            flag = true;
        }
        return flag;

    }
}
