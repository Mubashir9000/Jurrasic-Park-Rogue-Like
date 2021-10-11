package game;

import edu.monash.fit2099.engine.*;

/**
 * Represent the world of the Jurassic.
 */
public class JurassicWorld extends World implements Capable {
    private Capabilities capabilities = new Capabilities();
    private int numberOfMoves;
    private int targetEcopoints;

    /**
     * Constructor.
     *
     * @param display the Display that will display this World.
     */
    public JurassicWorld(Display display) {
        super(display);
    }

    /**
     * Run the game.
     * <p>
     * On each iteration the gameloop does the following: - displays the player's
     * map - processes the actions of every Actor in the game, regardless of map
     * <p>
     * We could either only process the actors on the current map, which would make
     * time stop on the other maps, or we could process all the actors. We chose to
     * process all the actors.
     * <p>
     * It also resets the player's EcoPoints and turn everytime the game is restarted.
     *
     * @throws IllegalStateException if the player doesn't exist
     */
    @Override
    public void run() {
        Player.ecoPoints = new EcoPoints();
        Player.turns = 0;
        super.run();
    }

    /**
     * Gives an Actor its turn.
     * <p>
     * The Actions an Actor can take include:
     * <ul>
     * <li>those conferred by items it is carrying</li>
     * <li>movement actions for the current location and terrain</li>
     * <li>actions that can be done to Actors in adjacent squares</li>
     * <li>actions that can be done using items in the current location</li>
     * <li>skipping a turn</li>
     * <li>movement across the two game maps</li>
     * </ul>
     *
     * @param actor the Actor whose turn it is.
     */
    @Override
    protected void processActorTurn(Actor actor) {
        Location here = actorLocations.locationOf(actor);
        GameMap map = here.map();
        Actions actions = new Actions();
        GameMap map1 = gameMaps.get(0);
        GameMap map2 = gameMaps.get(1);

        for (Item item : actor.getInventory()) {
            actions.add(item.getAllowableActions());
            // Game rule. If you're carrying it, you can drop it.
            actions.add(item.getDropAction());
        }

        for (Exit exit : here.getExits()) {
            Location destination = exit.getDestination();

            // Game rule. You don't get to interact with the ground if someone is standing
            // on it.
            if (actorLocations.isAnActorAt(destination)) {
                actions.add(actorLocations.getActorAt(destination).getAllowableActions(actor, exit.getName(), map));
            } else {
                actions.add(destination.getGround().allowableActions(actor, destination, exit.getName()));
            }
            actions.add(destination.getMoveAction(actor, exit.getName(), exit.getHotKey()));
        }

        for (Item item : here.getItems()) {
            actions.add(item.getAllowableActions());
            // Game rule. If it's on the ground you can pick it up.
            actions.add(item.getPickUpAction());
        }
        actions.add(new QuitAction());

        final int MAP1_TOP_EDGE = map1.getYRange().min();
        final int MAP2_BOTTOM_EDGE = map2.getYRange().max();

        if (actor instanceof Player && map.equals(map1) && here.y() == MAP1_TOP_EDGE) {
            Location destination = map2.at(here.x(), MAP2_BOTTOM_EDGE);
            actions.add(new MoveActorAction(destination, "out of the jungle"));
        } else if (actor instanceof Player && map.equals(map2) && here.y() == MAP2_BOTTOM_EDGE) {
            Location destination = map1.at(here.x(), MAP1_TOP_EDGE);
            actions.add(new MoveActorAction(destination, "into the jungle"));
        }

        actions.add(new DoNothingAction());

        Action action = actor.playTurn(actions, lastActionMap.get(actor), map, display);
        lastActionMap.put(actor, action);

        String result = action.execute(actor, map);
        display.println(result);
    }

    /**
     * Determines whether the game is still running depending on the game mode. In challenge, the game ends when either
     * the number of moves or the EcoPoints exceed the limit set.
     *
     * @return boolean indicating whether the game is still running
     */
    @Override
    protected boolean stillRunning() {
        if (hasCapability(WorldMode.CHALLENGE))
            return Player.getEcoPoints().getValue() < targetEcopoints && Player.turns < numberOfMoves;

        return super.stillRunning();
    }

    /**
     * Indicates whether the player has won or lost the game in challenge mode. Indicates that the game has ended in
     * sandbox mode
     *
     * @return String indicating the game ending message
     */
    @Override
    protected String endGameMessage() {
        if (hasCapability(WorldMode.CHALLENGE) && actorLocations.contains(player)) {
            if (Player.turns <= numberOfMoves && Player.getEcoPoints().getValue() >= targetEcopoints) {
                return "You win";
            } else {
                return "You lose";
            }
        } else {
            return super.endGameMessage();
        }
    }

    /**
     * Checks whether the world has a certain capability
     *
     * @param capability Certain capability
     * @return boolean indicating whether the world has this capability
     */
    @Override
    public boolean hasCapability(Enum<?> capability) {
        return capabilities.hasCapability(capability);
    }

    /**
     * Adds the capability to the world
     *
     * @param capability A certain capability
     */
    @Override
    public void addCapability(Enum<?> capability) {
        capabilities.addCapability(capability);
    }

    /**
     * Removes the capability to the world.
     *
     * @param capability A certain capability
     */
    @Override
    public void removeCapability(Enum<?> capability) {
        capabilities.removeCapability(capability);
    }

    /**
     * Allows the player to set the target/limit of the number of moves in challenge mode.
     *
     * @param numberOfMoves The target number of moves
     */
    public void setNumberOfMoves(int numberOfMoves) {
        this.numberOfMoves = numberOfMoves;
    }

    /**
     * Allows the player to set the target/limit of the EcoPoint in challenge mode.
     *
     * @param targetEcopoints The target EcoPoints
     */
    public void setTargetEcopoints(int targetEcopoints) {
        this.targetEcopoints = targetEcopoints;
    }
}
