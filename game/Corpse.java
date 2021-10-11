package game;

import edu.monash.fit2099.engine.Actor;

/**
 * A class to represent a dead actor.
 */
public class Corpse extends PortableItem {
    /**
     * Constructor
     *
     * @param target The actor that died
     */
    public Corpse(Actor target) {
        super(String.format("dead %s", target), '%');
        this.getFoodLevel().setValue(50);
    }
}
