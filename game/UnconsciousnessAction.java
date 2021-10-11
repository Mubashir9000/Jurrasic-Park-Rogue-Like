package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

/**
 * Handles when an actor becomes unconscious.
 */
public class UnconsciousnessAction extends Action {
    /**
     * Sets the actor to unconscious
     *
     * @param actor The actor performing the action.
     * @param map   The map the actor is on.
     * @return Message indicating that the actor has become unconscious.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        actor.addCapability(State.UNCONSCIOUS);
        return menuDescription(actor);
    }

    /**
     * Message indicating that an actor has become unconscious
     *
     * @param actor The actor performing the action.
     * @return A string containing the message.
     */
    @Override
    public String menuDescription(Actor actor) {
        return String.format("%s has become unconscious", actor);
    }
}
