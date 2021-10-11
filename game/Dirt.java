package game;

import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;

/**
 * A class that represents bare dirt.
 */
public class Dirt extends Ground {

    static final double PROB_OF_GRASS_TO_GROW = 0.02;
    static final double PROB_OF_GRASS_TO_GROW_NEXT_TO_GRASS = 0.10;
    static final double PROB_OF_GRASS_TO_GROW_NEXT_TO_TREE = 0.02;

    public Dirt() {
        super('.');
    }

    /**
     * Method to check on each turn whether grass should grow or not
     *
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location) {
        super.tick(location);

        final double PROBABILITY = Math.random();
        boolean grassShouldGrow = ((isNextToGrass(location) && PROBABILITY <= PROB_OF_GRASS_TO_GROW_NEXT_TO_GRASS)
                || (isNextToTree(location) && PROBABILITY <= PROB_OF_GRASS_TO_GROW_NEXT_TO_TREE)
                || PROBABILITY <= PROB_OF_GRASS_TO_GROW);

        if (grassShouldGrow) {
            location.setGround(new Grass());
            Player.getEcoPoints().increasePoints(1);
        }
    }


    private boolean isNextToGrass(Location location) {
        boolean flag = false;
        int counter = 0;
        for (Exit exit : location.getExits()) {
            if (exit.getDestination().getGround() instanceof Grass) {
                counter++;
            }
        }
        if (counter >= 2) {
            flag = true;
        }
        return flag;
    }

    private boolean isNextToTree(Location location) {
        boolean flag = false;
        for (Exit exit : location.getExits()) {
            if (exit.getDestination().getGround() instanceof Tree) {
                flag = true;
                break;
            }
        }
        return flag;
    }

}
