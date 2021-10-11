package game;

import edu.monash.fit2099.engine.*;

/**
 * A carnivorous dinosaur.
 */
public class Allosaur extends Dinosaur {

    /**
     * Constructor.
     * All Allosaurs are represented by an 'A' and have 100 hit points.
     *
     * @param name        the name of this Allosaur
     * @param foodLevel   the starting foodlevel of this Allosaur
     * @param thirstLevel the starting thristLevel of this Allosaur
     */
    public Allosaur(String name, int foodLevel, int thirstLevel) {
        super(name, 'A', 100, foodLevel, thirstLevel);
        addCapability(EatingHabit.CARNIVORE);
    }


    /**
     * Returns {@link AllosaurEgg}
     *
     * @return {@link AllosaurEgg}.
     */
    @Override
    protected Item getEgg() {
        return new AllosaurEgg();
    }

    /**
     * Returns the Allosaur's jaws/teeth to perform a bite action
     *
     * @return Allosaur's jaws/teeth to bite
     */
    @Override
    protected IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(100, "bites");
    }
}
