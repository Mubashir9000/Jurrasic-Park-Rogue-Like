package game;

import edu.monash.fit2099.engine.*;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a vending machine that is responsible for selling items.
 */
public class VendingMachine extends Ground {
    private List<Item> inventory = new ArrayList<>();

    /**
     * Constructor that creates a vending machine with the items that it contains
     */
    public VendingMachine() {
        super('V');
        inventory.add(new Hay());
        inventory.add(new Fruit());
        inventory.add(new VegetarianMealKit());
        inventory.add(new CarnivoreMealKit());
        inventory.add(new StegosaurEgg());
        inventory.add(new AllosaurEgg());
        inventory.add(new AgilisaurusEgg());
        inventory.add(new ArchaeopteryxEgg());
        inventory.add(new LaserGun());
    }

    /**
     * Lets the player purchase items from the machine
     *
     * @param actor     the Actor acting
     * @param location  the current Location
     * @param direction the direction of the Ground from the Actor
     * @return BuyItemAction Action to buy an item
     */
    @Override
    public Actions allowableActions(Actor actor, Location location, String direction) {
        Actions actions = new Actions();
        for (Item item : inventory) {
            actions.add(new BuyItemAction(item));
        }
        return actions;
    }

    /**
     * Doesn't allow the player to enter
     *
     * @param actor the Actor to check
     * @return boolean indicating that a player cannot enter
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        return false;
    }
}
