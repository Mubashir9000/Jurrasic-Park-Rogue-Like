package game;

/**
 * This class represents a Stegosaur Egg.
 */
public class StegosaurEgg extends Egg {

    /**
     * Constructor that instantiates a StegasaurEgg
     */
    public StegosaurEgg() {
        super("Stegosaur egg", 's');
        this.getCost().setValue(200);
        this.setEcopointsToIncreaseOnHatching(100);
    }

    /**
     * Returns {@link Stegosaur}
     *
     * @return {@link Stegosaur}
     */
    @Override
    public Dinosaur getDinosaur() {
        return new Stegosaur("Stego", 10, 40);
    }
}

