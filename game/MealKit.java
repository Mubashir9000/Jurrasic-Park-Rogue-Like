package game;

/**
 * Abstract class that represents a meal kit. {@link VegetarianMealKit} and {@link CarnivoreMealKit}
 * inherit from this class.
 */
public abstract class MealKit extends PortableItem {
    /**
     * Constructor to instantiate a meal kit.
     *
     * @param name        Name of meal kit
     * @param displayChar Display Character to represent the meal kit
     */
    public MealKit(String name, char displayChar) {
        super(name, displayChar);
        this.getFoodLevel().setValue(100);
    }
}
