package game;

/**
 * This class represents an AgilisaurusEgg item
 */
public class AgilisaurusEgg extends Egg {
    /**
     * Constructor. Instantiates an Agilisaurus egg
     */
    public AgilisaurusEgg() {
        super("Agilisaurus egg", 'l');
        this.getCost().setValue(500);
        this.setEcopointsToIncreaseOnHatching(500);
    }

    /**
     * Returns {@link Agilisaurus}
     *
     * @return {@link Agilisaurus}
     */
    @Override
    protected Dinosaur getDinosaur() {
        return new Agilisaurus("Agilisaurus", 10, 40);
    }
}
