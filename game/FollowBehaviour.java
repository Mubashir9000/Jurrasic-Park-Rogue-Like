package game;

import edu.monash.fit2099.engine.*;

/**
 * A class that figures out a MoveAction that will move the actor one step
 * closer to a target Actor.
 */
public class FollowBehaviour implements Behaviour {

    private Actor target;

    /**
     * Constructor.
     *
     * @param subject the Actor to follow
     */
    public FollowBehaviour(Actor subject) {
        this.target = subject;
    }

    public void setTarget(Actor target) {
        this.target = target;
    }

    public Actor getTarget() {
        return target;
    }

    @Override
    public Action getAction(Actor actor, GameMap map) {
        if (!map.contains(target) || !map.contains(actor) || target.hasCapability(State.UNCONSCIOUS) || target.hasCapability(State.MALE)
                || target.hasCapability(State.BABY) || target.hasCapability(State.PREGNANT))
            return null;

        Location here = map.locationOf(actor);
        Location there = map.locationOf(target);

        for (Exit exit : here.getExits()) {
            Location destination = exit.getDestination();
            if (destination.equals(there)) {
                return new MatingBehaviour(target).getAction(actor, map);
            }
        }

        int currentDistance = distance(here, there);
        for (Exit exit : here.getExits()) {
            Location destination = exit.getDestination();
            if (destination.canActorEnter(actor)) {
                int newDistance = distance(destination, there);
                if (newDistance < currentDistance) {
                    return new MoveActorAction(destination, exit.getName());
                }
            }
        }

        return null;
    }

    /**
     * Compute the Manhattan distance between two locations.
     *
     * @param a the first location
     * @param b the first location
     * @return the number of steps between a and b if you only move in the four cardinal directions.
     */
    private int distance(Location a, Location b) {
        return Math.abs(a.x() - b.x()) + Math.abs(a.y() - b.y());
    }
}