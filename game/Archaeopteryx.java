package game;

import edu.monash.fit2099.engine.IntrinsicWeapon;
import edu.monash.fit2099.engine.Item;

/**
 * A flying carnivorous dinosaur.
 */
public class Archaeopteryx extends Dinosaur {
    /**
     * Constructor.
     * All Archaeopteryx are represented by an'F' and have 100 hit points.
     *
     * @param name      the name of this Archaeopteryx
     * @param foodLevel the starting foodlevel of this Archaeopteryx
     * @param thirstLevel the starting thirstLevel of this Archaeopteryx
     */
    public Archaeopteryx(String name, int foodLevel, int thirstLevel) {
        super(name, 'F', 100, foodLevel, thirstLevel);
        addCapability(EatingHabit.CARNIVORE);
        addCapability(State.FLY);
    }

    /**
     * Returns {@link ArchaeopteryxEgg}
     *
     * @return {@link ArchaeopteryxEgg}.
     */
    @Override
    protected Item getEgg() {
        return new ArchaeopteryxEgg();
    }

    /**
     * Return the claws to perform a scratch action
     *
     * @return Archaeopteryx's claws to scratch.
     */
    @Override
    protected IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(20, "uses its claws to scratch");
    }
}
