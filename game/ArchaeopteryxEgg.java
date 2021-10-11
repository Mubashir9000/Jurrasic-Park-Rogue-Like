package game;

/**
 * This class represents an ArchaeopteryxEgg item
 */
public class ArchaeopteryxEgg extends Egg {
    /**
     * Constructor. Instantiates an Archaeopteryx egg
     */
    public ArchaeopteryxEgg() {
        super("Archaeopteryx egg", 'f');
        this.getCost().setValue(500);
        this.setEcopointsToIncreaseOnHatching(500);
    }

    /**
     * Returns {@link Archaeopteryx}
     *
     * @return {@link Archaeopteryx}
     */
    @Override
    protected Dinosaur getDinosaur() {
        return new Archaeopteryx("Archaeopteryx", 10, 40);
    }

}
