package game;


import edu.monash.fit2099.engine.DropItemAction;
import edu.monash.fit2099.engine.PickUpItemAction;

/**
 * A class that represents an item called Hay that can be harvested from grass
 */
public class Hay extends PortableItem {
    /**
     * Constructor. Used to instantiate a Hay item.
     */
    public Hay() {
        super("Hay", 'H');
        this.getCost().setValue(20);
        this.getFoodLevel().setValue(20);
    }

    /**
     * Allows a user to pick up an item
     *
     * @return PickUpAction
     */
    @Override
    public PickUpItemAction getPickUpAction() {
        if (this.hasCapability(HayStatus.GRASS))
            return new HarvestHayAction(this);
        else
            return super.getPickUpAction();
    }

    /**
     * Ensure status of hay changes when user drops it.
     *
     * @return DropItemAction
     */
    @Override
    public DropItemAction getDropAction() {
        if (this.hasCapability(HayStatus.GRASS)) {
            this.removeCapability(HayStatus.GRASS);
            this.addCapability(HayStatus.DROPPED);
        }
        return super.getDropAction();
    }
}
