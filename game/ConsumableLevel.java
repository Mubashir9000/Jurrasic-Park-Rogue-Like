package game;

/**
 * This class represents the level of any consumable item or consumable attribute of an actor.
 * That is this class is used to represent hunger and thirst levels of actors and the dietary values of
 * consumable objects i.e by how much hunger or thirst should increase based on the consumable item.
 */
public class ConsumableLevel {
    private int value;
    static final int MAX_VALUE = 100;
    static final int MIN_VALUE = 0;

    public ConsumableLevel(int value) {
        setValue(value);
    }

    /**
     * This function is responsible for returning the current value of the consumableLevel
     *
     * @return value/amount of consumableLevel
     */
    public int getValue() {
        return value;
    }

    /**
     * Sets the value of the consumableLevel
     *
     * @param value The value to be set
     */
    public void setValue(int value) {
        if (value >= MIN_VALUE && value <= MAX_VALUE)
            this.value = value;
        else if (value > MAX_VALUE)
            this.value = MAX_VALUE;
    }

    /**
     * Increments consumableLevel by a certain amount
     *
     * @param amount The amount to be incremented by
     */
    public void increaseLevel(int amount) {
        setValue(value + amount);
    }

    /**
     * Decrements consumableLevel by a certain amount
     *
     * @param amount The amount to be decremented by
     */
    public void decreaseLevel(int amount) {
        setValue(value - amount);
    }
}
