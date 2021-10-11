package game;

import edu.monash.fit2099.engine.*;

/**
 * A class that represents grass
 */
public class Grass extends Ground {
    static final ConsumableLevel FOOD_LEVEL = new ConsumableLevel(5);

    /**
     * Constructor to instantiate an object of grass.
     */
    public Grass() {
        super('g');
    }

    /**
     * Used to check if player is standing on this particular piece of grass and enables allowable
     * actions
     *
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location) {
        super.tick(location);


        Item itemToRemove = null;
        boolean GrassContainsHay = false;
        for (Item item : location.getItems()) {
            if (item.hasCapability(HayStatus.GRASS)) {
                GrassContainsHay = true;
                itemToRemove = item;
                break;
            }
        }

        if (location.containsAnActor() && !GrassContainsHay) {
            Item hay = new Hay();
            hay.addCapability(HayStatus.GRASS);
            location.addItem(hay);
        } else if (!location.containsAnActor() && GrassContainsHay) {
            location.removeItem(itemToRemove);
        }

    }

}
