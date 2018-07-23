
/** 
 * NimGame.java
 * This class implements the Nim game process.
 * @author Pengfei Xiao   
 * @userName xiaop1
 * @studentID 871910
 * @date 23 May, 2018 
 */

import java.util.Scanner;

public class NimGame {

	private static int bound; // Declare an int variable to store upper bound
	private static int initial; // Declare an int variable to store initial number of stones
	private static int remains; // Declare an int variable to store remain number of stones

	// Mutator
	public static void setBound(String num) {
		try {
			bound = Integer.parseInt(num);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
	}

	// Mutator
	public static void setInitial(String num) {
		try {
			initial = Integer.parseInt(num);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
	}

	// Accessor
	public static int getBound() {
		return bound;
	}

	// Accessor
	public static int getInitial() {
		return initial;
	}

	// Accessor
	public static int getRemains() {
		return remains;
	}

	// Draw asterisks
	public void draw(int remains) {
		for (int i = 0; i < remains; i++) {
			System.out.print(" *");
		}
	}

	// Return minimum of the two numbers
	public int minNum(int i, int j) {
		if (i <= j)
			return i;
		else
			return j;
	}

	// The main process of the game
	public void process(Scanner keyboard, NimPlayer[] playerSets, String username1, String username2) {
		final int ISEMPTY = 0; // Declare a constant variable
		NimPlayer player1 = null;
		NimPlayer player2 = null;
		boolean invalid1 = false;
		boolean invalid2 = false;
		int bound = getBound();
		int initial = getInitial();
		remains = initial;
		// Declare an int variable to store remain number of stones
		for (int i = 0; i < playerSets.length; i++) {
			if (playerSets[i] != null && playerSets[i].getUserName().equals(username1))
				player1 = playerSets[i];
			else if (playerSets[i] != null && playerSets[i].getUserName().equals(username2))
				player2 = playerSets[i];
		}
		player1.updateNumPlay();
		player2.updateNumPlay();
		while (remains > ISEMPTY) {
			if (player1 instanceof NimHumanPlayer) {
				do {
					System.out.printf("%n%d stones left:", remains);
					draw(remains);
					System.out.printf("%n%s's turn - remove how many?%n", player1.getFirstName());
					try { // Fractions, decimals, non-numeric input handling via Exceptions
						((NimHumanPlayer) player1).setStone(Integer.parseInt(keyboard.nextLine()));
					} catch (Exception e) {
					}
					invalid1 = false;
					try { // Invalid input handling via Exceptions
						if (((NimHumanPlayer) player1).removeStone(remains, bound) > bound
								|| ((NimHumanPlayer) player1).removeStone(remains, bound) == 0
								|| ((NimHumanPlayer) player1).removeStone(remains, bound) > remains)
							throw new Exception();
					} catch (Exception e) {
						invalid1 = true;
						System.out.println("\nInvalid move. You must remove between " + "1 and "
								+ minNum(bound, remains) + " stones.");
					}
				} while (invalid1);
				remains = remains - ((NimHumanPlayer) player1).removeStone(remains, bound);
			} else if (player1 instanceof NimAIPlayer) {
				System.out.printf("%n%d stones left:", remains);
				draw(remains);
				System.out.printf("%n%s's turn - remove how many?%n", player1.getFirstName());
				remains = remains - ((NimAIPlayer) player1).removeStone(remains, bound);
			}
			if (remains <= ISEMPTY) { // Judge whether the game is over or not
				System.out.println("\nGame Over");
				System.out.println(player2.getFirstName() + " " + player2.getLastName() + " wins!");
				player2.updateNumWin();
			} else {
				if (player2 instanceof NimHumanPlayer) {
					do {
						System.out.printf("%n%d stones left:", remains);
						draw(remains);
						System.out.printf("%n%s's turn - remove how many?%n", player2.getFirstName());
						try { // Fractions, decimals, non-numeric input handling via Exceptions
							((NimHumanPlayer) player2).setStone(Integer.parseInt(keyboard.nextLine()));
						} catch (Exception e) {
						}
						invalid2 = false;
						try { // Invalid input handling via Exceptions
							if (((NimHumanPlayer) player2).removeStone(remains, bound) > bound
									|| ((NimHumanPlayer) player2).removeStone(remains, bound) == 0
									|| ((NimHumanPlayer) player2).removeStone(remains, bound) > remains)
								throw new Exception();
						} catch (Exception e) {
							invalid2 = true;
							System.out.println("\nInvalid move. You must remove between " + "1 and "
									+ minNum(bound, remains) + " stones.");
						}
					} while (invalid2);
					remains = remains - ((NimHumanPlayer) player2).removeStone(remains, bound);
				} else if (player2 instanceof NimAIPlayer) {
					System.out.printf("%n%d stones left:", remains);
					draw(remains);
					System.out.printf("%n%s's turn - remove how many?%n", player2.getFirstName());
					remains = remains - ((NimAIPlayer) player2).removeStone(remains, bound);
				}
				if (remains <= ISEMPTY) { // Judge whether the game is over or not
					System.out.println("\nGame Over");
					System.out.println(player1.getFirstName() + " " + player1.getLastName() + " wins!");
					player1.updateNumWin();
				}
			}
		}
	}
}
