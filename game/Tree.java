package game;

import edu.monash.fit2099.engine.*;

/**
 * This class represents a tree
 */
public class Tree extends Ground {
    private int age = 0;
    static final double PROB_OF_FRUIT_DROPPING = 0.05;

    /**
     * Constructor to instantiate a tree
     */
    public Tree() {
        super('+');
    }

    /**
     * Used to allow the tree to grow and to check if the player is at the same location.
     *
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location) {
        super.tick(location);

        final double PROBABILITY = Math.random();

        if (PROBABILITY <= PROB_OF_FRUIT_DROPPING) {
            Item fruit = new Fruit();
            fruit.addCapability(FruitLocation.DROPPED);
            location.addItem(fruit);
        }

        Item itemToRemove = null;
        boolean containsFruitInTree = false;
        for (Item item : location.getItems()) {
            if (item.hasCapability(FruitLocation.TREE)) {
                containsFruitInTree = true;
                itemToRemove = item;
                break;
            }
        }

        if (location.containsAnActor() && !containsFruitInTree) {
            Item fruit = new Fruit();
            fruit.addCapability(FruitLocation.TREE);
            location.addItem(fruit);
        } else if (!location.containsAnActor() && containsFruitInTree) {
            location.removeItem(itemToRemove);
        }

        age++;
        if (age == 10)
            displayChar = 't';
        if (age == 20)
            displayChar = 'T';
    }
}
