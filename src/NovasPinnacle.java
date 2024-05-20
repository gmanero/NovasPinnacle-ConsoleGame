import java.util.Scanner;
/********************************************************************
 * Main tester                                                      *
 *                                                                  *
 * PROGRAMMER: Gabe Manero                                          *
 * COURSE: CS201                                                    *
 * DATE: 12/06/2023                                                 *
 * REQUIREMENT: Final Project                                       *
 *                                                                  *
 * DESCRIPTION:                                                     *
 * The following Program runs the nova pinnacle game                *
 *                                                                  *
 *                                                                  *
 *                                                                  *
 * COPYRIGHT: This code is copyright (C) 2023 Gabe Manero Code and  *
 * Dean Zeller - CS201                                              *
 *                                                                  *
 *                                                                  *
 *                                                                  *
 *******************************************************************/
    public class NovasPinnacle {
        public static void main(String[] args) {

            //Begins the game with a brief prompt to have user start the game
            System.out.println("Welcome to Nova's Pinnacle!");
            System.out.print("Enter 'start' to embark on your journey. ");
            Scanner scanner = new Scanner(System.in);
            String start = scanner.next();

            //Waits for the user to enter start to begin the two game methods from the GameController method
            if (start.equals("start")) {
                GameController.initializeGame();
                GameController.runGame();
            }
            //If the user inputs something other than 'start' then the game exits
            else {
                System.out.println("Invalid input. Exiting the game.");
            }
        }

    }
