package game;

/**
 * This class represents an AllosaurEgg item
 */
public class AllosaurEgg extends Egg {
    /**
     * Constructor. Instantiates an Allosaur egg
     */
    public AllosaurEgg() {
        super("Allosaur egg", 'a');
        this.getCost().setValue(1000);
        this.setEcopointsToIncreaseOnHatching(1000);
    }

    /**
     * Returns {@link Allosaur}
     *
     * @return {@link Allosaur}
     */
    @Override
    protected Dinosaur getDinosaur() {
        return new Allosaur("Alo", 10, 40);
    }
}
