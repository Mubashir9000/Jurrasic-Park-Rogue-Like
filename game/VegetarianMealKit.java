package game;

/**
 * This class represents a carnivore meal kit that can be used to feed carnivores and
 * replenish their food level to full.
 */
public class VegetarianMealKit extends MealKit {
    /**
     * Constructor that instantiates a VegetarianMealKit
     */
    public VegetarianMealKit() {
        super("Vegetarian Meal Kit", 'v');
        this.getCost().setValue(100);
    }
}
