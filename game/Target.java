package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;

/**
 * This class is to automate the selection an appropriate Target to follow .
 */

public class Target extends FollowBehaviour {
    /**
     * Constructor
     */
    public Target() {
        super(null);
    }

    /**
     * Handles selection of target
     *
     * @param actor The actor performing the action
     * @param map The map the actor is on
     * @return Action to follow target
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        Location location = null;
        Actor subject = null;
        for (int x = 0; x < map.getXRange().max(); x++) {
            for (int y = 0; y < map.getYRange().max(); y++) {
                location = map.at(x, y);
                if (map.isAnActorAt(location)) {
                    subject = map.getActorAt(location);
                    if (subject instanceof Stegosaur && subject.hasCapability(State.FEMALE)) {
                        this.setTarget(subject);
                        break;
                    }
                }
            }
        }

        return super.getAction(actor, map);

    }
}
