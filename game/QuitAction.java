package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

/**
 * Allows a user to quit a game mode
 */
public class QuitAction extends Action {
    /**
     * Quits the game
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return Bye
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        map.removeActor(actor);
        return "Bye";
    }

    /**
     * Menu option to quit game
     * @param actor The actor performing the action.
     * @return String of the menu description
     */
    @Override
    public String menuDescription(Actor actor) {
        return "Quit game";
    }
}
