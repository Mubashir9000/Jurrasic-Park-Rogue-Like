package game;

import edu.monash.fit2099.engine.*;

/**
 * A class that figures out a BreedingAction that will make the Dinosaurs breed
 */

public class MatingBehaviour implements Behaviour {
    private Actor target;

    /**
     * Constructor.
     *
     * @param subject the Actor to breed on
     */
    public MatingBehaviour(Actor subject) {
        this.target = subject;
    }

    /**
     * Returns a BreedAction, if possible.
     * If no Breeding is possible, returns null.
     *
     * @param actor the Actor enacting the behaviour
     * @param map   the map that actor is currently on
     * @return an Action, or null if no MoveAction is possible
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        if (target.hasCapability(State.UNCONSCIOUS) || target.hasCapability(State.MALE)
                || target.hasCapability(State.BABY) || target.hasCapability(State.PREGNANT)) {
            return null;
        }
        if (actor.getClass().equals(target.getClass())) {
            Location here = map.locationOf(actor);
            if (isNextToDino(here) && target.hasCapability(State.FEMALE)) {
                return new BreedingAction(target);
            }
        }
        return null;
    }

    /**
     * Find out if a Dinosaur is nearby.
     *
     * @param location where the actor is standing
     * @return True if at least 1 instanceof Dinosaur is found
     */
    private boolean isNextToDino(Location location) {
        boolean flag = false;
        int counter = 0;
        for (Exit exit : location.getExits()) {
            if (exit.getDestination().containsAnActor()) {
                if (exit.getDestination().getActor() instanceof Dinosaur) {
                    counter++;
                }
            }
        }
        if (counter >= 1) {
            flag = true;
        }
        return flag;
    }
}
