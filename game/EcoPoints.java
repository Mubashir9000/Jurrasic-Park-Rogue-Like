package game;

/**
 * This class represents in-game currency
 */
public class EcoPoints {
    private int value;

    public EcoPoints() {
        this.value = 0;
    }

    public EcoPoints(int value) {
        setValue(value);
    }

    /**
     * This function is responsible for returning the current value of ecopoint
     *
     * @return value/amount of money
     */
    public int getValue() {
        return value;
    }

    /**
     * Sets the value of the ecopoint
     *
     * @param value The value to be set
     */
    public void setValue(int value) {
        this.value = value;
    }

    /**
     * Increments ecopoints by a certain amount
     *
     * @param amount The amount to be incremented by
     */
    public void increasePoints(int amount) {
        this.value += amount;
    }

    /**
     * Decrements ecopoints by a certain amount
     *
     * @param amount The amount to be decremented by
     */
    public void decreasePoints(int amount) {
        this.value -= amount;
    }

    /**
     * Used to check whether a player is able to afford an item by comparing the player's current ecopoints with
     * the value of the item
     *
     * @param availableEcoPoints The ecopoints the player currently has
     * @param cost               The number of ecopoints required to by an item
     * @return True if the player can afford the item, else false
     */
    public static boolean isAffordable(EcoPoints availableEcoPoints, EcoPoints cost) {
        return availableEcoPoints.getValue() >= cost.getValue();
    }
}
