package game;

import edu.monash.fit2099.engine.*;

/**
 * This class is responsible to handle the action of buying items from the vending machine.
 */
public class BuyItemAction extends Action {
    private final Item item;

    /**
     * Instantiates a buy action on the item provided
     *
     * @param item The item to buy
     */
    public BuyItemAction(Item item) {
        this.item = item;
    }

    /**
     * Checks if the player can afford an item, if true, the item is added to the player's inventory
     * and the cost is deducted respectively. If false, the player is notified that the item is not
     * affordable.
     *
     * @param actor The actor performing the action.
     * @param map   The map the actor is on.
     * @return Message whether purchase was successful or not
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        Player player = (Player) actor;
        EcoPoints playerEcoPoints = Player.getEcoPoints();
        EcoPoints itemCost = item.getCost();

        if (EcoPoints.isAffordable(playerEcoPoints, itemCost)) {
            player.addItemToInventory(this.item);
            Player.getEcoPoints().decreasePoints(itemCost.getValue());
            return item.toString() + " added to inventory";
        } else {
            return "You can't afford " + item.toString() + " right now";
        }
    }

    /**
     * Displays the menu description of the action
     *
     * @param actor The actor performing the action.
     * @return String informing what the user can buy and for how much
     */

    @Override
    public String menuDescription(Actor actor) {
        return actor + " buys " + item.toString() + " for " + item.getCost().getValue();
    }
}

