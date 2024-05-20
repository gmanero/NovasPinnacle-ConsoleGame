import java.util.Random;
/********************************************************************
 * Enemy class                                                      *
 *                                                                  *
 * PROGRAMMER: Gabe Manero                                          *
 * COURSE: CS201                                                    *
 * DATE: 12/06/2023                                                  *
 * REQUIREMENT: Final Project                                       *
 *                                                                  *
 * DESCRIPTION:                                                     *
 * The following Program is the enemy  class for the game.          *
 * This gets the health, attacks and different enemy methods        *
 *                                                                  *
 *                                                                  *
 *                                                                  *
 * COPYRIGHT: This code is copyright (C) 2023 Gabe Manero Code and  *
 * Dean Zeller - CS201                                              *
 *                                                                  *
 *                                                                  *
 *                                                                  *
 *******************************************************************/
class Enemy {
    public String name;
    private int health;
    private int attackDamage;
    private final int initialHealth;
    private final int initialAttackDamage;

    /**
     * ********************************************************
     * METHOD: Enemy(String name, int initialHealth, int initialAttackDamage)  *
     * DESCRIPTION: Constructor for the Enemy class.            *
     * PARAMETERS:                                              *
     *   name - The name of the enemy.                          *
     *   initialHealth - The initial health of the enemy.       *
     *   initialAttackDamage - The initial attack damage of the enemy.*
     **********************************************************/
    public Enemy(String name, int initialHealth, int initialAttackDamage) {
        this.initialHealth = initialHealth;
        this.initialAttackDamage = initialAttackDamage;
        this.name = name;
    }

    /**
     * ********************************************************
     * METHOD: attackPlayer(Player player)                      *
     * DESCRIPTION: Attacks the player and updates health display.*
     * PARAMETERS:                                              *
     *   player - The player being attacked.                    *
     * RETURN VALUE: None                                       *
     **********************************************************/
    public void attackPlayer(Player player) {
        player.takeDamage(attackDamage);
        System.out.println(name + "'s Health is " + health);
    }

    /**
     * ********************************************************
     * METHOD: takeDamage(int damage)                           *
     * DESCRIPTION: Reduces the enemy's health by the given damage amount.*
     * PARAMETERS:                                              *
     *   damage - The amount of damage taken.                   *
     * RETURN VALUE: None                                       *
     **********************************************************/
    public void takeDamage(int damage) {
        health -= damage;
        if (health <= 0) {
            health = 0;
        }
    }

    /**
     * ********************************************************
     * METHOD: resetAttackDamage()                               *
     * DESCRIPTION: Resets the enemy's attack damage to the initial value.*
     * PARAMETERS: None                                        *
     * RETURN VALUE: None                                       *
     **********************************************************/
    public void resetAttackDamage() {
        attackDamage = initialAttackDamage;
    }

    /**
     * ********************************************************
     * METHOD: getHealth()                                      *
     * DESCRIPTION: Gets the current health of the enemy.        *
     * RETURN VALUE: The current health of the enemy.            *
     **********************************************************/
    public int getHealth() {
        return health;
    }

    /**
     * ********************************************************
     * METHOD: getName()                                        *
     * DESCRIPTION: Gets the name of the enemy.                  *
     * RETURN VALUE: The name of the enemy.                      *
     **********************************************************/
    public String getName() {
        return name;
    }

    /**
     * ********************************************************
     * METHOD: isDefeated()                                      *
     * DESCRIPTION: Checks if the enemy is defeated.             *
     * RETURN VALUE: True if the enemy is defeated, otherwise false.*
     **********************************************************/
    public boolean isDefeated() {
        return health <= 0;
    }

    /**
     * ********************************************************
     * METHOD: setHealth(int enemyHealth)                        *
     * DESCRIPTION: Sets the health of the enemy.                *
     * PARAMETERS:                                              *
     *   enemyHealth - The health value to set.                  *
     **********************************************************/
    public void setHealth(int enemyHealth) {
        this.health = enemyHealth;
    }

    /**
     * ********************************************************
     * METHOD: setAttackDamage(int enemyAttackDamage)           *
     * DESCRIPTION: Sets the attack damage of the enemy.         *
     * PARAMETERS:                                              *
     *   enemyAttackDamage - The attack damage value to set.     *
     **********************************************************/
    public void setAttackDamage(int enemyAttackDamage) {
        this.attackDamage = enemyAttackDamage;
    }

    /**
     * ********************************************************
     * METHOD: update()                                        *
     * DESCRIPTION: Displays a message indicating the enemy is attacking.*
     * RETURN VALUE: None                                       *
     **********************************************************/
    public void update() {
        System.out.println("The " + name + " is attacking! | Health: " + health);
    }

    /**
     * ********************************************************
     * METHOD: heal()                                          *
     * DESCRIPTION: Heals the enemy and updates health display.  *
     * RETURN VALUE: None                                       *
     **********************************************************/
    public void heal() {
        int healingAmount = new Random().nextInt(5, 15) + 1;
        System.out.println("The " + name + " healed itself");
        health += healingAmount;
        System.out.println(name + "'s current Health: " + health);
    }

    /**
     * ********************************************************
     * METHOD: getAttackDamage()                                *
     * DESCRIPTION: Gets the current attack damage of the enemy. *
     * RETURN VALUE: The current attack damage of the enemy.    *
     **********************************************************/
    public int getAttackDamage() {
        return attackDamage;
    }

    /**
     * ********************************************************
     * METHOD: enemyTurn()                                      *
     * DESCRIPTION: Simulates the enemy's turn, deciding whether to attack or heal.*
     * RETURN VALUE: None                                       *
     **********************************************************/
    public void enemyTurn() {
        Random random = new Random();
        int decision = random.nextInt(2); // 0 for attack, 1 for heal

        if (decision == 0) {
            attackPlayer(GameController.player);
        } else {
            if (health < initialHealth) {
                heal();
                if (health > initialHealth) {
                    health = initialHealth;
                }
            } else {
                attackPlayer(GameController.player);
            }
        }
    }
}
