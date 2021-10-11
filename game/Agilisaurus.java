package game;

import edu.monash.fit2099.engine.Item;

/**
 * An omnivorous dinosaur.
 */
public class Agilisaurus extends Dinosaur {
    /**
     * Constructor.
     * All Agilisaurus are represented by an'L' and have 100 hit points.
     *
     * @param name        the name of this Agilisaurus
     * @param foodLevel   the starting foodlevel of this Agilisaurus
     * @param thirstLevel the starting thristLevel of this Agilisaurus
     */
    public Agilisaurus(String name, int foodLevel, int thirstLevel) {
        super(name, 'L', 100, foodLevel, thirstLevel);
        addCapability(EatingHabit.OMNIVORE);
    }

    /**
     * Returns {@link AgilisaurusEgg}
     *
     * @return {@link AgilisaurusEgg}.
     */
    @Override
    protected Item getEgg() {
        return new AgilisaurusEgg();
    }
}
