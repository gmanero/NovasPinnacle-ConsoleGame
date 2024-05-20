import java.util.Scanner;
/********************************************************************
 * Player class                                                     *
 *                                                                  *
 * PROGRAMMER: Gabe Manero                                          *
 * COURSE: CS201                                                    *
 * DATE: 12/06/2023                                                  *
 * REQUIREMENT: Final Project                                       *
 *                                                                  *
 * DESCRIPTION:                                                     *
 * The following Program is the player class for the game.          *
 * This obtains the user input. Keeping track of attacks heals and  *
 * potions throughout the game                                      *
 *                                                                  *
 *                                                                  *
 *                                                                  *
 * COPYRIGHT: This code is copyright (C) 2023 Gabe Manero Code and  *
 * Dean Zeller - CS201                                              *
 *                                                                  *
 *                                                                  *
 *                                                                  *
 *******************************************************************/
class Player {
    private int health = 100;
    private int potions = 3;
    private int potionDuration = 0;
    private int shield = 0;
    private int attackDamage = 20;
    private int currentLevelIndex;

    /**
     * METHOD: SetHealth()
     * DESCRIPTION:Sets the health of the player
     * PARAMETERS: None
     * RETURN VALUE: None
     */
    public void setHealth() {
        health = 100;
    }
    /**
     * METHOD: getHealth()
     * DESCRIPTION: Gets the player health
     * PARAMETERS: None
     * RETURN VALUE: health;
     */
    public int getHealth() {
        return health;
    }

    /**
     * METHOD: takeDamage(int damage)
     * DESCRIPTION: decreases health based on the enemy attacks
     * PARAMETERS: int damage
     * RETURN VALUE: None
     */
    public void takeDamage(int damage) {
        health -= damage;
        if (health <= 0) {
            health = 0;
            System.out.println("OH NO! You were attacked and you took " + damage + " damage and have been defeated!\n");
        } else {
            System.out.println("The enemy attacked you! You took " + damage + " damage. Your Health is " + health);
            System.out.print("\n");
        }
    }

    /**
     * METHOD: update()
     * DESCRIPTION: updates the player stats
     * PARAMETERS: None
     * RETURN VALUE: None
     */
    public void update() {
        System.out.println("Your Health: " + health + "   | Your attack Damage: " + attackDamage + "   | Potions: " + potions);
    }

    /**
     * METHOD: userInput(Scanner scanner, Level currentLevel)
     * DESCRIPTION:Takes the user input for what their move is and directs it from there
     * PARAMETERS: Scanner scanner, Level currentLevel
     * RETURN VALUE: None
     */
    public void userInput(Scanner scanner, Level currentLevel) {
        System.out.print("\nEnter your move ('h/heal' to heal, 'a/attack' to attack, 'p/potion' to use a potion): ");
        String input = scanner.next();
        if (input.equals("heal") || input.equals("h")) {
            heal();
        } else if (input.equals("attack") || input.equals("a")) {
            if (currentLevel.enemyInRange()) {
                attack(currentLevel.getEnemy());
            }
        } else if (input.equals("potion") || input.equals("p")) {
            usePotion();
        } else {
            System.out.println("Invalid input. Please enter 'heal' to heal, 'attack' to attack, or 'potion' to use a potion ");
        }
    }

    /**
     * METHOD: attack(Enemy enemy)
     * DESCRIPTION: attacks the enemy and displays output
     * PARAMETERS: None
     * RETURN VALUE: Enemy enemy
     */
    private void attack(Enemy enemy) {
        System.out.println("\nYou attacked the " + enemy.getName() + "!");
        enemy.takeDamage(attackDamage);
        applyPotionDuration();
    }

    /**
     * METHOD: heal()
     * DESCRIPTION: Initializes the game by setting up the
     * player, levels, and enemies.
     * PARAMETERS: None
     * RETURN VALUE: None
     */
    private void heal() {
        System.out.print("\nYou healed yourself. ");
        health += 10;
        applyPotionDuration();
        if (health > 100) {
            health = 100; // Cap the health at 100
        }
        System.out.print("Your Health is " + health);
        System.out.print("\n");
    }
    private boolean potionUsed = false;  // Add a flag to track if a potion was used

    /**
     * METHOD:  usePotion()
     * DESCRIPTION: the main method that directs which potion is used
     * PARAMETERS:
     * RETURN VALUE:
     */
    private void usePotion() {
        if (potions > 0) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter potion boost type a/attack or s/shield: ");
            String potionType = scanner.next();
            potionDuration = 3;

            if (potionType.equals("attack") || potionType.equals("a")) {
                int damageIncrease = 5;
                useAttackDamagePotion(damageIncrease);
            } else if (potionType.equals("shield") || potionType.equals("s")) {
                int shieldAmount = 12;
                useShieldPotion(shieldAmount);
            } else {
                System.out.println("Invalid potion type. Please enter 'a/attack' or 's/shield' ");
            }

            potionUsed = true;
            potions--;
        } else {
            System.out.println("\nYou don't have any potions left!");
        }
    }


    /**
     * METHOD:  useAttackDamagePotion(int damageIncrease)
     * DESCRIPTION: This method is used when the attack damage potion is selected by the user
     * PARAMETERS: int damageIncrease
     * RETURN VALUE: None
     */
    private void useAttackDamagePotion(int damageIncrease) {
        System.out.println("\nYou used an attack damage potion ");
        attackDamage += damageIncrease;
        System.out.println("Temporary attack damage boosted to " + attackDamage);
        applyPotionDuration();
    }

    /**
     * METHOD: useShieldPotion(int shieldAmount)
     * DESCRIPTION: When user selects shield potion then this method is used
     * PARAMETERS: int shieldAmount
     * RETURN VALUE: None
     */
    private void useShieldPotion(int shieldAmount) {
        System.out.println("\nYou used a shield potion ");
        shield += shieldAmount;
        System.out.println("Temporary shield activated");
        applyPotionDuration();

        int originalAttackDamage = GameController.levels.get(currentLevelIndex).getEnemy().getAttackDamage();
        int damageReduced = Math.min(shield, originalAttackDamage / 2); // Player takes half of the original attack damage


        // Update the enemy's attack damage with the reduced value
        GameController.levels.get(currentLevelIndex).getEnemy().setAttackDamage(originalAttackDamage - damageReduced);

    }
    /**
     * METHOD: applyPotionDuration
     * DESCRIPTION: Tracks the amount of turns that are used with the potion
     * PARAMETERS: None
     * RETURN VALUE: None
     */
    private void applyPotionDuration() {
        if (potionUsed) {
            if (potionDuration > 0) {
                System.out.println("Potion duration remaining: " + potionDuration + " turns ");
                potionDuration--;
            } else {
                if (shield > 0) {
                    System.out.println("Shield no longer activated");
                    GameController.levels.get(currentLevelIndex).getEnemy().resetAttackDamage();
                    attackDamage = 20;  // Reset player's attack damage to the initial value
                } else if (attackDamage > 20) {
                    attackDamage -= 5;
                    System.out.println("Attack damage now back to: " + attackDamage);
                }
                System.out.println("Potion has worn off");
                potionUsed = false;  // after the potion wears off
            }
        }
    }

}