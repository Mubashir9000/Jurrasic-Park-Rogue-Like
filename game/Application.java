package game;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.FancyGroundFactory;
import edu.monash.fit2099.engine.GameMap;

/**
 * The main class for the Jurassic World game.
 */
public class Application {
    static final int INVALID_CHOICE = 0;
    static final int CHALLENGE_MODE = 1;
    static final int SANDBOX_MODE = 2;
    static final int QUIT = 3;

    public static void main(String[] args) {
        JurassicWorld world = createWorld();
        int choice = chooseGameMode();
        processGame(world, choice);
    }


    private static JurassicWorld createWorld() {
        JurassicWorld world = new JurassicWorld(new Display());

        FancyGroundFactory groundFactory = new FancyGroundFactory(new Dirt(), new Wall(), new Floor(), new Tree(), new Grass(), new VendingMachine(), new Water());

        List<String> map = Arrays.asList(
                "...............................................................~~...............",
                "........................~~.....................................~~...............",
                ".....#######............~~......................................................",
                ".....#_V___#....................................................................",
                ".....#_____#.......................~~...........................................",
                ".....###.###.......................~~...........................................",
                "....................................................~~~.........................",
                "......~...............................+++............~..........................",
                ".....~~~...............................++++.....................................",
                ".....~~~...........................+++++.........................~~~............",
                "......~........~~....................++++++.......................~.............",
                "..............~~~~....................+++............~..........................",
                "...............~~....................+++............~~~.........................",
                ".....................................................~..........................",
                "............+++.................................................................",
                ".............+++++..............~~~~............................................",
                "...............++..............~~~~~~....................+++++..................",
                ".............+++....................................++++++++....................",
                "........~...+++.......................................+++.......................",
                ".......~~~......................................................................",
                "......................................~........................~~~~......++.....",
                ".............~~~.....................~~~.......................~~~~~....++.++...",
                "..............~.......................~........................~~~~......++++...",
                "..........................................................................++....",
                "................................................................................");
        GameMap gameMap = new GameMap(groundFactory, map);
        world.addGameMap(gameMap);

        List<String> map2 = Arrays.asList(
                "________________________________________________________________________________",
                "________________________________________________________________________________",
                "________________________________________________________________________________",
                "________________________________________________________________________________",
                "________________________________________________________________________________",
                "________________________________________________________________________________",
                "________________________________________________________________________________",
                "________________________________________________________________________________",
                "________________________________________________________________________________",
                "________________________________________________________________________________",
                "________________________________________________________________________________",
                "______________________________________V_________________________________________",
                "________________________________________________________________________________",
                "________________________________________________________________________________",
                "________________________________________________________________________________",
                "________________________________________________________________________________",
                "________________________________________________________________________________",
                "________________________________________________________________________________",
                "________________________________________________________________________________",
                "________________________________________________________________________________",
                "________________________________________________________________________________",
                "________________________________________________________________________________",
                "________________________________________________________________________________",
                "________________________________________________________________________________",
                "________________________________________________________________________________");
        GameMap secondMap = new GameMap(groundFactory, map2);
        world.addGameMap(secondMap);

        Actor player = new Player("Player", '@', 100);
        world.addPlayer(player, gameMap.at(9, 4));

        // Place a pair of stegosaurs in the middle of the map
        Stegosaur stegosaur = new Stegosaur("Stego 1", 60, 19);
        Stegosaur stegosaur1 = new Stegosaur("Stego 2", 60, 19);
        stegosaur.addCapability(State.MALE);
        stegosaur1.addCapability(State.FEMALE);

        gameMap.at(30, 18).addActor(stegosaur);
        gameMap.at(30, 20).addActor(stegosaur1);
        return world;
    }

    private static void processGame(JurassicWorld world, int choice) {
        if (choice == CHALLENGE_MODE || choice == SANDBOX_MODE) {
            if (choice == CHALLENGE_MODE) {
                if (!world.hasCapability(WorldMode.CHALLENGE))
                    world.addCapability(WorldMode.CHALLENGE);
                int numberOfMoves;
                int targetEcopoints;
                boolean flag;
                System.out.println("You have entered challenge mode");

                do {
                    try {
                        flag = false;
                        System.out.print("Select the number of moves: ");
                        Scanner scanner = new Scanner(System.in);
                        numberOfMoves = scanner.nextInt();
                        System.out.print("Select the target ecopoints: ");
                        targetEcopoints = scanner.nextInt();
                        world.setNumberOfMoves(numberOfMoves);
                        world.setTargetEcopoints(targetEcopoints);
                        world.run();
                    } catch (Exception e) {
                        System.out.println("Invalid choice. Try Again \n");
                        flag = true;
                    }
                }
                while (flag);
            } else {
                if (world.hasCapability(WorldMode.CHALLENGE)) {
                    world.removeCapability(WorldMode.CHALLENGE);
                }
                world.run();
            }
            restartGame();
        } else {
            System.out.println("Thank you for playing");
        }
    }

    private static void restartGame() {
        System.out.println("Would you like to play again? Enter Y/N");
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();
        if (userInput.equalsIgnoreCase("Y") || userInput.equalsIgnoreCase("yes")) {
            main(null);
        } else if (userInput.equalsIgnoreCase("N") || userInput.equalsIgnoreCase("No")) {
            System.out.println("Thank you for playing");
        } else {
            System.out.println("I didn't quite get that.");
            restartGame();
        }

    }

    private static int chooseGameMode() {
        String menu = "Game menu\n1.Challenge Mode\n2.Sandbox Mode\n3.Quit";
        int choice;

        do {
            System.out.println(menu);
            System.out.print("Please select a game mode: ");
            Scanner scanner = new Scanner(System.in);
            try {
                choice = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("Invalid choice. Try again\n");
                choice = INVALID_CHOICE;
            }
        } while (choice != CHALLENGE_MODE && choice != SANDBOX_MODE && choice != QUIT);
        return choice;
    }
}
