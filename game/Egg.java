package game;

import edu.monash.fit2099.engine.Location;

/**
 * Abstract class to represent all eggs. i.e. {@link StegosaurEgg}, {@link AllosaurEgg}
 */
public abstract class Egg extends PortableItem {
    private int turn;
    private int ecopointsToIncreaseOnHatching;

    public int getEcopointsToIncreaseOnHatching() {
        return ecopointsToIncreaseOnHatching;
    }

    public void setEcopointsToIncreaseOnHatching(int ecopointsToIncreaseOnHatching) {
        this.ecopointsToIncreaseOnHatching = ecopointsToIncreaseOnHatching;
    }

    /**
     * Constructor
     *
     * @param name        name of the egg
     * @param displayChar character to represent the egg on the map
     */
    public Egg(String name, char displayChar) {
        super(name, displayChar);
        this.getFoodLevel().setValue(10);
    }

    /**
     * Gets the corresponding dinosaur of the egg.
     *
     * @return Type of dinosaur to whom this egg belongs.
     */
    protected abstract Dinosaur getDinosaur();

    /**
     * Gives the egg a sense of time. Handles the hatching of the egg.
     *
     * @param currentLocation The location of the ground on which we lie.
     */
    @Override
    public void tick(Location currentLocation) {
        turn++;
        int timeToHatch = 10;
        if (turn >= timeToHatch && !currentLocation.containsAnActor()) {
            currentLocation.removeItem(this);
            Dinosaur Dinosaur = getDinosaur();
            Dinosaur.addCapability(State.BABY);
            currentLocation.addActor(Dinosaur);
            System.out.println(this.toString() + " has hatched at " + "(" + currentLocation.x() + "," + currentLocation.y() + ")");
            Player.getEcoPoints().increasePoints(getEcopointsToIncreaseOnHatching());
        }
    }
}


