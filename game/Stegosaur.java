package game;


import edu.monash.fit2099.engine.*;

/**
 * A herbivorous dinosaur.
 */
public class Stegosaur extends Dinosaur {
    /**
     * Constructor.
     * All Stegosaurs are represented by a 'd' and have 100 hit points.
     *
     * @param name        the name of this Stegosaur
     * @param foodLevel   the starting foodlevel of this Stegosaur
     * @param thirstLevel the starting thirstLevel of this Stegosaur
     */
    public Stegosaur(String name, int foodLevel, int thirstLevel) {
        super(name, 'd', 100, foodLevel, thirstLevel);
        addCapability(EatingHabit.HERBIVORE);
    }

    /**
     * Returns {@link StegosaurEgg}
     *
     * @return {@link StegosaurEgg}.
     */
    @Override
    protected Item getEgg() {
        return new StegosaurEgg();
    }
}
