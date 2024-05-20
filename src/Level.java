import java.util.ArrayList;

/********************************************************************
 * Level class                                                      *
 *                                                                  *
 * PROGRAMMER: Gabe Manero                                          *
 * COURSE: CS201                                                    *
 * DATE: 12/06/2023                                                  *
 * REQUIREMENT: Final Project                                       *
 *                                                                  *
 * DESCRIPTION:                                                     *
 * The following Program is the level class for the game.          *
 * This obtains difficulty and the various levels for the enemies  *
 * that are encountered. It updates the enemy and moves levels     *
 *                                                                  *
 *                                                                  *
 *                                                                  *
 * COPYRIGHT: This code is copyright (C) 2023 Gabe Manero Code and  *
 * Dean Zeller - CS201                                              *
 *                                                                  *
 *                                                                  *
 *                                                                  *
 *******************************************************************/
class Level {
    private final ArrayList<Enemy> enemies = new ArrayList<>();
    private int currentEnemyIndex = 0;

    /**
     * ********************************************************
     * METHOD: addEnemy(String enemyName, int enemyHealth, int enemyAttackDamage) *
     * DESCRIPTION: Adds a new enemy to the level with specified name, health, and attack damage.*
     * PARAMETERS:                                              *
     *   enemyName - The name of the enemy to add.               *
     *   enemyHealth - The health of the enemy to add.           *
     *   enemyAttackDamage - The damage of the enemy to add.     *
     * RETURN VALUE: None                                       *
     **********************************************************/
    public void addEnemy(String enemyName, int enemyHealth, int enemyAttackDamage) {
        Enemy enemy = new Enemy(enemyName, enemyHealth, enemyAttackDamage);
        enemy.setHealth(enemyHealth);
        enemy.setAttackDamage(enemyAttackDamage);
        enemies.add(enemy);
    }

    /**
     * ********************************************************
     * METHOD: enemyInRange()                                   *
     * DESCRIPTION: Checks if there is an enemy in the current range.*
     * RETURN VALUE: True if there is an enemy, otherwise false.*
     **********************************************************/
    public boolean enemyInRange() {
        return currentEnemyIndex < enemies.size();
    }

    /**
     * ********************************************************
     * METHOD: getEnemy()                                       *
     * DESCRIPTION: Gets the current enemy in the level.         *
     * RETURN VALUE: The current enemy or null if none is present.*
     **********************************************************/
    public Enemy getEnemy() {
        if (enemyInRange()) {
            return enemies.get(currentEnemyIndex);
        } else {
            return null;
        }
    }

    /**
     * ********************************************************
     * METHOD: nextEnemy()                                      *
     * DESCRIPTION: Moves to the next enemy in the level.        *
     * RETURN VALUE: None                                       *
     **********************************************************/
    public void nextEnemy() {
        currentEnemyIndex++;
    }

    /**
     * ********************************************************
     * METHOD: setDifficulty(String difficulty)                 *
     * DESCRIPTION: Sets the difficulty of the level, adjusting enemy health and attack damage.*
     * PARAMETERS:                                              *
     *   difficulty - The difficulty level to set.              *
     * RETURN VALUE: None                                       *
     **********************************************************/
    public void setDifficulty(String difficulty) {
        int multiple = 0;
        if (difficulty.equals("easy")) {
            multiple = 1;
        } else if (difficulty.equals("hard")) {
            multiple = 2;
        } else if (difficulty.equals("advance")) {
            multiple = 3;
        } else {
            System.out.println("Invalid difficulty. Setting to easy.");
        }

        for (Enemy enemy : enemies) {
            enemy.setHealth(enemy.getHealth() * multiple);
            enemy.setAttackDamage(enemy.getAttackDamage() * multiple);
        }
    }

    /**
     * ********************************************************
     * METHOD: newLevel()                                       *
     * DESCRIPTION: Initiates a new level encounter, displaying the current enemy name and stats.*
     * RETURN VALUE: None                                       *
     **********************************************************/
    public void newLevel() {
        if (enemyInRange()) {
            System.out.println("You encounter a " + getEnemy().getName() + ".");
            getEnemy().update();
        }
    }

    /**
     * ********************************************************
     * METHOD: updateEnemy()                                    *
     * DESCRIPTION: Updates the enemy's status and initiates a new level if the current enemy is defeated.*
     * RETURN VALUE: None                                       *
     **********************************************************/
    public void updateEnemy() {
        if (getEnemy().isDefeated()) {
            System.out.println("You defeated the " + getEnemy().getName() + "!");
            nextEnemy();
            if (enemyInRange()) {
                System.out.println("\nNow facing the " + getEnemy().getName() + ".");
                getEnemy().update();
            }
        } else {
            getEnemy().enemyTurn();
        }
    }
}
