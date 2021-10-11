package game;

import edu.monash.fit2099.engine.Item;

/**
 * Base class for any item that can be picked up and dropped.
 */
public class PortableItem extends Item {
    private EcoPoints cost = new EcoPoints();
    private ConsumableLevel foodLevel = new ConsumableLevel(0);

    public PortableItem(String name, char displayChar) {
        super(name, displayChar, true);
    }

    /**
     * Gets the cost of an item
     *
     * @return cost The cost of an item
     */
    public EcoPoints getCost() {
        return this.cost;
    }

    public ConsumableLevel getFoodLevel() {
        return this.foodLevel;
    }
}
