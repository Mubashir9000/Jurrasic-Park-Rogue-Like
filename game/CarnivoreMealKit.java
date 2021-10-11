package game;

/**
 * This class represents a carnivore meal kit that can be used to feed carnivores and
 * replenish their food level to full.
 */
public class CarnivoreMealKit extends MealKit {
    /**
     * Constructor that instantiates a CarnivoreMealKit
     */
    public CarnivoreMealKit() {
        super("Carnivore Meal Kit", 'c');
        this.getCost().setValue(500);
    }
}
