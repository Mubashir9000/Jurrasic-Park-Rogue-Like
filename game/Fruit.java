package game;

import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.PickUpItemAction;

/**
 * This class represents a fruit item
 */
public class Fruit extends PortableItem {
    private int turn = 0;

    /**
     * Constructor. Instantiates a Fruit item
     */
    public Fruit() {
        super("Fruit", 'o');
        this.getCost().setValue(30);
        this.getFoodLevel().setValue(30);
    }

    /**
     * Check is the fruit has rotted and if so removes the fruit
     *
     * @param currentLocation The location of the ground on which we lie.
     */
    @Override
    public void tick(Location currentLocation) {
        super.tick(currentLocation);

        turn++;

        if (turn == 20) {
            currentLocation.removeItem(this);
        }
    }

    /**
     * Checks whether the fruit is on the ground or if it is to be searched for in the tree
     * and returns the respective action
     *
     * @return PickUpItem action that varies based on the status of the fruit
     */
    @Override
    public PickUpItemAction getPickUpAction() {
        if (capabilities.hasCapability(FruitLocation.DROPPED))
            return super.getPickUpAction();
        else
            return new SearchFruitAction(this);
    }
}
