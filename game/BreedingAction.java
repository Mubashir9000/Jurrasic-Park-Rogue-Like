package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

/**
 * This class is responsible for allowing the dinosaurs to breed.
 */
public class BreedingAction extends Action {
    private Actor target;

    /**
     * Constructor that instantiates the action
     *
     * @param target the actor that will be bred on
     */
    public BreedingAction(Actor target) {
        this.target = target;
    }

    /**
     * Responsible to perform the action
     *
     * @param actor The actor performing the action.
     * @param map   The map the actor is on.
     * @return a string telling us that actor bred with the target
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        target.removeCapability(State.FEMALE);
        target.addCapability(State.PREGNANT);
        return actor + " breeds with " + target;
    }

    @Override
    public String menuDescription(Actor actor) {
        return "breed";
    }
}
