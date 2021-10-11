package game;

import edu.monash.fit2099.engine.WeaponItem;

/**
 * This class represents a laser gun weapon that can be used to kill dinosaurs.
 */
public class LaserGun extends WeaponItem {

    static final EcoPoints COST = new EcoPoints(500);

    /**
     * Constructor. Instantiates a laser gun weapon.
     */
    public LaserGun() {
        super("Laser Gun", '|', 100, "pew-pew");
    }

    /**
     * Returns the cost of the laser gun
     *
     * @return Cost of laser gun
     */
    @Override
    public EcoPoints getCost() {
        return COST;
    }

    @Override
    public ConsumableLevel getFoodLevel() {
        return null;
    }
}
