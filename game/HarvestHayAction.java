package game;

import edu.monash.fit2099.engine.*;

/**
 * This class is responsible for allowing the player to harvest hay from grass.
 */
public class HarvestHayAction extends PickUpItemAction {
    /**
     * Constructor that instantiates the action
     *
     * @param item The item to be harvested i.e. hay
     */
    public HarvestHayAction(Item item) {
        super(item);
    }

    /**
     * Responsible to perform the action if player chooses to
     *
     * @param actor The actor performing the action.
     * @param map   The map the actor is on.
     * @return Description of hay being harvester
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        map.locationOf(actor).setGround(new Dirt());
        Player.getEcoPoints().increasePoints(1);
        return super.execute(actor, map);
    }

    /**
     * Menu description of the action
     *
     * @param actor The actor performing the action.
     * @return String informing that the user can choose to harvest
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " harvests grass for hay.";
    }
}
