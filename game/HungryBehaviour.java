package game;

import edu.monash.fit2099.engine.*;

/**
 * A class that makes the Dinosaur move to a food source nearby and eat it.
 */
public class HungryBehaviour implements Behaviour {
    /**
     * Decides and returns either GrazingGrassAction,AttackAction or EatingAction based on EatingHabits
     * If no Action is possible, returns null.
     *
     * @param actor the Actor enacting the behaviour
     * @param map   the map that actor is currently on
     * @return one of the Actions, or null if no Action is possible
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        Location here = map.locationOf(actor);
        if (isNextToGrass(here) && (actor.hasCapability(EatingHabit.HERBIVORE) || actor.hasCapability(EatingHabit.OMNIVORE))) {
            for (Exit exit : here.getExits()) {
                Location destination = exit.getDestination();
                if (destination.getGround() instanceof Grass && destination.canActorEnter(actor)) {
                    destination.setGround(new Dirt());
                    return new GrazingGrassAction(destination);
                }
            }
        } else if (isNextToDino(here) && actor.hasCapability(EatingHabit.CARNIVORE)) {
            for (Exit exit : here.getExits()) {

                Actor target = exit.getDestination().getActor();

                boolean isSameTypeOfDino = true;
                if (target != null)
                    isSameTypeOfDino = actor.getClass().equals(target.getClass());

                boolean canAttack = target instanceof Dinosaur && !isSameTypeOfDino;
                if (canAttack) {
                    return new AttackAction(target);
                }
            }

        } else if (actor.hasCapability(EatingHabit.CARNIVORE) || actor.hasCapability(EatingHabit.OMNIVORE)) {
            for (Exit exit : here.getExits()) {
                for (Item item : exit.getDestination().getItems()) {
                    if (item instanceof Corpse || item instanceof Egg) {
                        Location destination = exit.getDestination();
                        return new EatingAction(item, destination);
                    }
                }
            }
        }
        return null;
    }


    /**
     * Find out if grass is nearby.
     *
     * @param location where the actor is standing
     * @return True if at least 1 instanceof Grass is found
     */
    private boolean isNextToGrass(Location location) {
        boolean flag = false;
        int counter = 0;
        for (Exit exit : location.getExits()) {
            if (exit.getDestination().getGround() instanceof Grass) {
                counter++;
            }
        }
        if (counter >= 1) {
            flag = true;
        }
        return flag;

    }

    /**
     * Find out if a Stegosaur is nearby.
     *
     * @param location where the actor is standing
     * @return True if at least 1 instance of Stegosaur is found
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
