import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;

/********************************************************************
 * GameController                                                   *
 *                                                                  *
 * PROGRAMMER: Gabe Manero                                          *
 * COURSE: CS201                                                    *
 * DATE: 12/06/2023                                                  *
 * REQUIREMENT: Final Project                                       *
 *                                                                  *
 * DESCRIPTION:                                                     *
 * The following Program sets two methods one to initialize the game
 * and the other to run the game                                    *
 *                                                                  *
 *                                                                  *
 *                                                                  *
 * COPYRIGHT: This code is copyright (C) 2023 Gabe Manero Code and  *
 * Dean Zeller - CS201                                              *
 *                                                                  *
 *                                                                  *
 *                                                                  *
 *******************************************************************/
class GameController {
    static Player player;
    static ArrayList<Level> levels;

    /**

     * METHOD: initializeGame()
     * DESCRIPTION: Initializes the game by setting up the
     * player, levels, and enemies.
     * PARAMETERS: None
     * RETURN VALUE: None
     */
    public static void initializeGame() {
        Scanner scanner = new Scanner(System.in);
        player = new Player();
        levels = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            Level level = new Level();
            level.addEnemy("Goblin", 50, 12);
            level.addEnemy("Skeleton", 70, 18);
            level.addEnemy("Dragon", 100, 30);
            levels.add(level);
        }

        System.out.println("\nYou stand at the entrance of Nova's Pinnacle");

        System.out.print("Enter your difficulty ('easy', 'hard', 'advance'): ");
        String difficulty = scanner.next();
        for (Level level : levels) {
            level.setDifficulty(difficulty);
        }

        player.setHealth();
        System.out.println("\nPrepared for battle, you enter the journey of Nova's Pinnacle ");
        System.out.println("\n");
        System.out.println("You have been granted 3 potions! ");
        System.out.println("These potions can either be used to boost your attack damage or add a shield that reduces the enemy attack on you");
        System.out.println("These potions will last for a short period of time when used");
    }


     /**
      METHOD: runGame()
      DESCRIPTION: Runs the game loop until the player wins or loses.
      PARAMETERS: None
      RETURN VALUE: None
     **/
    public static void runGame() {
        int currentLevelIndex = 0;

        while (currentLevelIndex < levels.size() && player.getHealth() > 0) {
            Level currentLevel = levels.get(currentLevelIndex);
            System.out.println("\n");
            currentLevel.newLevel();
            player.update();
            while ( player.getHealth() > 0) {
                player.userInput(new Scanner(System.in), currentLevel);
                currentLevel.updateEnemy();
                System.out.println("\n");
                player.update();
            }
        }

        if (player.getHealth() != 0) {
            System.out.println("Congratulations! You conquered the towers of Nova's Pinnacle!");
        }
        else {
            System.out.println("Defeated! Nova's Pinnacle remains unconquered. Better luck next time!");
        }
    }

}
