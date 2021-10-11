package game;

import edu.monash.fit2099.engine.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * This represents an abstract class representing all dinosaurs. It inherits from {@link Actor}
 * It has all the common attributes and behaviours of dinosaurs and therefore {@link Stegosaur} and
 * {@link Allosaur} inherit from this class.
 */
public abstract class Dinosaur extends Actor {
    protected List<Behaviour> actionfactories = new ArrayList<>();
    private ConsumableLevel foodLevel;
    private ConsumableLevel thirstLevel;
    private int tick;
    private int pregnancyPeriod;
    private int babyPeriod;
    static final double PROB_OF_BABY_GENDER = 0.50;

    /**
     * Constructor
     *
     * @param name        The name of the dinosaur
     * @param displayChar The character of the dinosaur in the map
     * @param hitPoints   The Dinosaur's starting hit points
     * @param foodLevel   The Dinosaur's starting food level
     * @param thirstLevel The Dinosaur's starting thirst level
     */
    public Dinosaur(String name, char displayChar, int hitPoints, int foodLevel, int thirstLevel) {
        super(name, displayChar, hitPoints);
        actionfactories.add(new WanderBehaviour());
        actionfactories.add(new HungryBehaviour());
        actionfactories.add(new ThirstyBehaviour());
        this.foodLevel = new ConsumableLevel(foodLevel);
        this.thirstLevel = new ConsumableLevel(thirstLevel);
    }

    /**
     * Provide actions that can be done on this actor
     *
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return Allowable actions
     */
    @Override
    public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
        Actions actions = new Actions();
        actions.add(new AttackAction(this));

        for (Item item : otherActor.getInventory()) {
            boolean isCarnivoreFood = item instanceof CarnivoreMealKit || item instanceof Egg;
            boolean isHerbivoreFood = item instanceof Hay || item instanceof Fruit || item instanceof VegetarianMealKit;
            boolean isOmnivoreFood = isCarnivoreFood || isHerbivoreFood;
            boolean canEat = false;
            if (this.hasCapability(EatingHabit.CARNIVORE))
                canEat = isCarnivoreFood;
            else if (this.hasCapability(EatingHabit.HERBIVORE))
                canEat = isHerbivoreFood;
            else if (this.hasCapability(EatingHabit.OMNIVORE))
                canEat = isOmnivoreFood;

            if (canEat) {
                actions.add(new FeedingAction(this, item));
            }
        }

        return actions;
    }

    /**
     * Handles what the dinosaur does every turn depending on different conditions.
     *
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return The action to be taken by the dinosaur
     */
    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
        display.println(this + ": FoodLevel = " + getFoodLevel().getValue());
        display.println(this + ": ThirstLevel = " + getThirstLevel().getValue());

        if (this.hasCapability(State.PREGNANT)) {
            incrementPregnancyPeriod(1);
            layEgg(map, display);
        } else if (this.hasCapability(State.BABY)) {
            incrementBabyPeriod(1);
            growIntoAdult(display);
        }

        int deathIndicator = 20;
        if (getTick() == deathIndicator) {
            return new DeathAction();
        }

        decreaseFoodAndThirstLevels();
        return actionBasedOnFoodLevel(map, display);
    }

    /**
     * Handles the actions to perform based on foodLevel
     *
     * @param map     The map the actor is on
     * @param display the I/O object to which messages may be written
     * @return The action to perform
     */
    private Action actionBasedOnFoodLevel(GameMap map, Display display) {
        Behaviour wander = actionfactories.get(0);
        Behaviour hungry = actionfactories.get(1);
        Behaviour thirsty = actionfactories.get(2);

        Action wandering = wander.getAction(this, map);
        Action hunger = hungry.getAction(this, map);
        Action follow = new Target().getAction(this, map);
        Action thirst = thirsty.getAction(this, map);

        int hungerIndicator = 10;
        int breedingIndicator = 50;
        int thirstIndicator = 20;

        boolean isNormalState = getFoodLevel().getValue() > hungerIndicator && getFoodLevel().getValue() < breedingIndicator
                && getThirstLevel().getValue() > thirstIndicator && !this.hasCapability(State.UNCONSCIOUS);
        boolean isHungry = getFoodLevel().getValue() <= hungerIndicator && !this.hasCapability(State.UNCONSCIOUS);
        boolean isThirsty = getThirstLevel().getValue() <= thirstIndicator && !this.hasCapability(State.UNCONSCIOUS);
        boolean isReadyToBreed = getFoodLevel().getValue() >= breedingIndicator;


        if ((getFoodLevel().getValue() == 0 || getThirstLevel().getValue() == 0) && !this.hasCapability(State.UNCONSCIOUS)) {
            return new UnconsciousnessAction();
        } else if (this.hasCapability(State.UNCONSCIOUS) && getFoodLevel().getValue() > 0 && getThirstLevel().getValue() > 0) {
            this.removeCapability(State.UNCONSCIOUS);
        } else if (this.hasCapability(State.UNCONSCIOUS)) {
            incrementTick(1);
            return new DoNothingAction();
        }

        if (isNormalState) {
            if (wandering != null) {
                return wandering;
            }
        }

        if (isHungry) {
            if (getFoodLevel().getValue() == hungerIndicator)
                display.println(this.name + " is getting hungry at " + "(" + map.locationOf(this).x() + "," + map.locationOf(this).y() + ")");
            if (hunger != null) {
                if (this.hasCapability(EatingHabit.HERBIVORE) || this.hasCapability(EatingHabit.CARNIVORE) || this.hasCapability(EatingHabit.OMNIVORE)) {
                    return hunger;
                }
            }
        }

        if (isThirsty) {
            if (getThirstLevel().getValue() == thirstIndicator)
                display.println(this.name + " is getting thirsty");
            if (thirst != null) {
                return thirst;
            }
        }

        if (isReadyToBreed && this.hasCapability(State.MALE)) {
            if (follow != null) {
                return follow;
            }
        }

        return Objects.requireNonNullElseGet(wandering, DoNothingAction::new);
    }

    /**
     * Helper method to decrease food and thirst levels
     */
    private void decreaseFoodAndThirstLevels() {
        getFoodLevel().decreaseLevel(1);
        getThirstLevel().decreaseLevel(1);
    }

    /**
     * Returns the current foodLevel of the dinosaur
     *
     * @return current foodLevel of the dinosaur
     */
    public ConsumableLevel getFoodLevel() {
        return foodLevel;
    }

    /**
     * Returns the current thirstLevel of the dinosaur
     *
     * @return current thirstLevel of the dinosaur
     */
    public ConsumableLevel getThirstLevel() {
        return thirstLevel;
    }

    /**
     * Returns the length that the dinosaur has remained unconscious
     *
     * @return length dinosaur remains unconscious
     */
    public int getTick() {
        return tick;
    }

    /**
     * Returns the pregnancy duration of the dinosaur
     *
     * @return pregnancy duration
     */
    public int getPregnancyPeriod() {
        return pregnancyPeriod;
    }

    /**
     * Returns the amount of time that the dinosaur is a baby
     *
     * @return baby duration
     */
    public int getBabyPeriod() {
        return babyPeriod;
    }

    /**
     * Increases the pregnancy duration by a specified amount
     *
     * @param amount The amount to increase
     */
    public void incrementPregnancyPeriod(int amount) {
        this.pregnancyPeriod += amount;
    }

    /**
     * Increases the baby duration by a specified amount
     *
     * @param amount The amount to increase
     */
    public void incrementBabyPeriod(int amount) {
        this.babyPeriod += amount;
    }

    /**
     * Increases the unconscious duration by a specified amount
     *
     * @param amount The amount to increase
     */
    public void incrementTick(int amount) {
        this.tick += amount;
    }

    /**
     * Handles laying of the egg
     *
     * @param map     The map on which the egg should be laid
     * @param display the I/O object to which messages may be written
     */
    private void layEgg(GameMap map, Display display) {
        if (getPregnancyPeriod() == 10) {
            this.removeCapability(State.PREGNANT);
            this.addCapability(State.FEMALE);
            display.println(this + " has laid an egg at " + "(" + map.locationOf(this).x() + "," + map.locationOf(this).y() + ")");
            map.locationOf(this).addItem(getEgg());
            incrementPregnancyPeriod(1);
        }
    }

    /**
     * Handles the baby to adult transition
     *
     * @param display the I/O object to which messages may be written
     */
    private void growIntoAdult(Display display) {
        if (getBabyPeriod() == 30) {
            display.println(this + " has grown to adult");
            this.removeCapability(State.BABY);
            final double Probability = Math.random();
            if (Probability <= PROB_OF_BABY_GENDER) {
                this.addCapability(State.MALE);
            } else if (Probability >= PROB_OF_BABY_GENDER) {
                this.addCapability(State.FEMALE);
            }
        }
    }

    /**
     * Returns the egg corresponding to the dinosaur. eg. {@link Stegosaur} : {@link StegosaurEgg},
     * {@link Allosaur} : {@link AllosaurEgg}
     *
     * @return Egg corresponding to dinosaur.
     */
    protected abstract Item getEgg();
}


