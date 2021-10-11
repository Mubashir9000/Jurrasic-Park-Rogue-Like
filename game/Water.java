package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;

/**
 * Represents water in the game
 */
public class Water extends Ground {
    static final ConsumableLevel THIRST_LEVEL = new ConsumableLevel(20);

    /**
     * Constructor.
     * Instantiates water
     */
    public Water() {
        super('~');
    }

    /**
     * Only allows actors that can fly to enter
     *
     * @param actor the Actor to check
     * @return boolean indicating who can enter
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        return actor.hasCapability(State.FLY);
    }
}
