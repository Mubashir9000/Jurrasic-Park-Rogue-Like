package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;

/**
 * Handles the dying of an actor
 */
public class DeathAction extends Action {
    /**
     * Adds a corpse and removes the dead actor from the current location
     *
     * @param actor The actor that died
     * @param map   The map the actor is on.
     * @return A message indicating the actor has died
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        Location currentLocation = map.locationOf(actor);
        map.removeActor(actor);
        currentLocation.addItem(new Corpse(actor));
        return menuDescription(actor);
    }

    /**
     * Message indicating an actor has died
     *
     * @param actor The actor that died
     * @return String with a message
     */
    @Override
    public String menuDescription(Actor actor) {
        return String.format("%s has died", actor);
    }
}
