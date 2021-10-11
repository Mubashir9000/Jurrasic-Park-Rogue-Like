package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

/**
 * Handles the action of drinking water
 */
public class DrinkingWaterAction extends Action {
    /**
     * Increases the thirstLevel of a dinosaur on drinking water
     *
     * @param actor The actor performing the action.
     * @param map   The map the actor is on.
     * @return Message indicating drinking of water.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        Dinosaur dinosaur = (Dinosaur) actor;
        dinosaur.getThirstLevel().increaseLevel(Water.THIRST_LEVEL.getValue());
        return menuDescription(actor);
    }

    /**
     * Message indicating drinking of water
     *
     * @param actor The actor performing the action.
     * @return String containing the message
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " drank water";
    }
}
