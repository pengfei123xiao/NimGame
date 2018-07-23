
/**  
 * Nimsys.java
 * This class control the whole process of the Nim system.
 * @author Pengfei Xiao   
 * @userName xiaop1
 * @studentID 871910
 * @date 23 May, 2018 
 */

import java.util.Scanner;
import java.io.*;

public class Nimsys {

	private static int count = 0; // Declare an int variable to count player's order in the array
	private static final int NUMBER_OF_PLAYERS = 100; // Declare a constant variable
	private static NimPlayer[] playerSets;

	// Write player data as a NimPlayer[] object to the file
	public static void writeFile(File file) {
		ObjectOutputStream opStream;
		try {
			opStream = new ObjectOutputStream(new FileOutputStream(file));
			opStream.writeObject(playerSets);
			opStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Read the player data from a file to NimPlayer array
	public static void readFile(File file) {
		ObjectInputStream inStream;
		try {
			inStream = new ObjectInputStream(new FileInputStream(file));
			playerSets = (NimPlayer[]) inStream.readObject();
			inStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		int n=0;
		for(int i = 0; i < playerSets.length; i++)	{
		if(playerSets[i]!=null) n++;
		}
		count=n; // Update the player's order
	}

	// Add players
	private void addPlayer(NimPlayer[] playerSets, String playerInfo) {
		String[] splitUserInfo = playerInfo.split(",");
		// Split the commands with comma
		// and store separated commands in a string array
		boolean isExist = false;
		for (int i = 0; i < playerSets.length; i++) {
			if (playerSets[i] != null && playerSets[i].getUserName().equals(splitUserInfo[0]))
				isExist = true;
		}
		if (isExist == true) {
			System.out.println("The player already exists.");
		}
		if (isExist == false) {
			NimHumanPlayer p = new NimHumanPlayer(splitUserInfo[0], splitUserInfo[2], splitUserInfo[1]);
			playerSets[count] = p;
			count++;
		}
	}

	// Add AIplayers
	private void addAIPlayer(NimPlayer[] playerSets, String playerInfo) {
		String[] splitUserInfo = playerInfo.split(",");
		// Split the commands with comma
		// and store separated commands in a string array
		boolean isExist = false;
		for (int i = 0; i < playerSets.length; i++) {
			if (playerSets[i] != null && playerSets[i].getUserName().equals(splitUserInfo[0]))
				isExist = true;
		}
		if (isExist == true) {
			System.out.println("The player already exists.");
		}
		if (isExist == false) {
			NimAIPlayer p = new NimAIPlayer(splitUserInfo[0], splitUserInfo[2], splitUserInfo[1]);
			playerSets[count] = p;
			count++;
		}
	}

	// Edit players
	private void editPlayer(NimPlayer[] playerSets, String playerInfo) {
		String[] splitUserInfo = playerInfo.split(",");
		// Split the commands with comma
		// and store separated commands in a string array
		boolean isExist = false;
		for (int i = 0; i < count; i++) {
			if (playerSets[i].getUserName().equals(splitUserInfo[0])) {
				isExist = true;
				playerSets[i].setLastName(splitUserInfo[1]);
				playerSets[i].setFirstName(splitUserInfo[2]);
			}
		}
		if (isExist == false) {
			System.out.println("The player does not exist.");
		}
	}

	// Remove specific players
	private void removePlayers(NimPlayer[] playerSets, String playerInfo) {
		String[] splitUserInfo = playerInfo.split(",");
		boolean isExist = false;
		for (int i = 0; i < count; i++) {
			if (playerSets[i].getUserName().equals(splitUserInfo[0])) {
				isExist = true;
				playerSets[i] = playerSets[count - 1];
				playerSets[count - 1] = null;
				count -= 1;
			}
		}
		if (isExist == false) {
			System.out.println("The player does not exist.");
		}
	}

	// Remove all players
	private void removeAllPlayers(NimPlayer[] playerSets) {
		for (int i = 0; i < playerSets.length; i++) {
			playerSets[i] = null;
			count = 0;
		}
	}

	// Reset specific players
	private void resetPlayer(NimPlayer[] playerSets, String playerInfo) {
		String[] splitUserInfo = playerInfo.split(",");
		boolean isExist = false;
		for (int i = 0; i < count; i++) {
			if (playerSets[i].getUserName().equals(splitUserInfo[0])) {
				isExist = true;
				playerSets[i].setNumPlay(0);
				playerSets[i].setNumWin(0);
			}
		}
		if (isExist == false) {
			System.out.println("The player does not exist.");
		}
	}

	// Reset all players
	private void resetAllPlayers(NimPlayer[] playerSets) {
		for (int i = 0; i < playerSets.length; i++) {
			if (playerSets[i] != null) {
				playerSets[i].setNumPlay(0);
				playerSets[i].setNumWin(0);
			}
		}
	}

	// Display specific players
	private void displayPlayer(NimPlayer[] playerSets, String playerInfo) {
		boolean isExist = false;
		String[] splitUserInfo = playerInfo.split(",");
		for (int i = 0; i < count; i++) {
			if (playerSets[i].getUserName().equals(splitUserInfo[0])) {
				isExist = true;
				System.out.printf("%s,%s,%s,%d games,%d wins%n", playerSets[i].getUserName(),
						playerSets[i].getFirstName(), playerSets[i].getLastName(), playerSets[i].getNumPlay(),
						playerSets[i].getNumWin());
			}
		}
		if (isExist == false) {
			System.out.println("The player does not exist.");
		}
	}

	// Display all players
	private void displayAllPlayers(NimPlayer[] playerSets) {
		// Sort by username
		boolean swap;
		do {
			swap = false;
			for (int i = 0; i < count - 1; i++) {
				for (int j = i + 1; j < count; j++) {
					if (playerSets[i] != null && playerSets[j] != null
							&& playerSets[i].getUserName().compareTo(playerSets[j].getUserName()) > 0) {
						// Swapping players at positions i and j
						NimPlayer temp;
						temp = playerSets[i];
						playerSets[i] = playerSets[j];
						playerSets[j] = temp;
						swap = true;
					}
				}
			}
		} while (swap == true);
		for (int i = 0; i < count; i++) {
			if (playerSets[i] != null) {
				System.out.printf("%s,%s,%s,%d games,%d wins%n", playerSets[i].getUserName(),
						playerSets[i].getFirstName(), playerSets[i].getLastName(), playerSets[i].getNumPlay(),
						playerSets[i].getNumWin());
			} else
				return; // The function will display nothing if all players are removed
		}
	}

	// Sort in desc
	private void descSort(NimPlayer[] playerSets) {
		int index, indexOfNextLargest;
		boolean swap;
		for (index = 0; index < count; index++) {
			indexOfNextLargest = indexOfLargest(index, playerSets);
			interchange(index, indexOfNextLargest, playerSets);
		}
		do { // Sort by username if two players' win rate are the same
			swap = false;
			for (int i = 0; i < count - 1; i++) {
				for (int j = i + 1; j < count; j++) {
					if (playerSets[i].getWinRate() == playerSets[j].getWinRate()) {
						if (playerSets[i].getUserName().compareTo(playerSets[j].getUserName()) > 0) {
							// Swap players at positions i and j
							NimPlayer temp;
							temp = playerSets[i];
							playerSets[i] = playerSets[j];
							playerSets[j] = temp;
							swap = true;
						}
					}
				}
			}
		} while (swap == true);
		for (int i = 0; i < 10; i++) { // Display top 10 players
			if (playerSets[i] != null) {
				String strWinRate = String.valueOf(Math.round(playerSets[i].getWinRate()));
				if (strWinRate.length() == 3)
					System.out.printf("%d%% | %s games | %s %s%n", Math.round(playerSets[i].getWinRate()),
							String.format("%02d", playerSets[i].getNumPlay()), playerSets[i].getFirstName(),
							playerSets[i].getLastName());
				else if (strWinRate.length() == 2)
					System.out.printf("%d%%  | %s games | %s %s%n", Math.round(playerSets[i].getWinRate()),
							String.format("%02d", playerSets[i].getNumPlay()), playerSets[i].getFirstName(),
							playerSets[i].getLastName());
				else if (strWinRate.length() == 1)
					System.out.printf("%d%%   | %s games | %s %s%n", Math.round(playerSets[i].getWinRate()),
							String.format("%02d", playerSets[i].getNumPlay()), playerSets[i].getFirstName(),
							playerSets[i].getLastName());
			}
		}
	}

	// Sort in asc
	private void ascSort(NimPlayer[] playerSets) {
		int index, indexOfNextSmallest;
		boolean swap;
		for (index = 0; index < count; index++) {
			indexOfNextSmallest = indexOfSmallest(index, playerSets);
			interchange(index, indexOfNextSmallest, playerSets);
		}
		do { // Sort by username if two players' win rate are the same
			swap = false;
			for (int i = 0; i < count - 1; i++) {
				for (int j = i + 1; j < count; j++) {
					if (playerSets[i].getWinRate() == playerSets[j].getWinRate()) {
						if (playerSets[i].getUserName().compareTo(playerSets[j].getUserName()) > 0) {
							// Swap players at positions i and j
							NimPlayer temp;
							temp = playerSets[i];
							playerSets[i] = playerSets[j];
							playerSets[j] = temp;
							swap = true;
						}
					}
				}
			}
		} while (swap == true);
		for (int i = 0; i < 10; i++) { // Display top 10 players
			if (playerSets[i] != null) {
				String strWinRate = String.valueOf(Math.round(playerSets[i].getWinRate()));
				if (strWinRate.length() == 3)
					System.out.printf("%d%% | %s games | %s %s%n", Math.round(playerSets[i].getWinRate()),
							String.format("%02d", playerSets[i].getNumPlay()), playerSets[i].getFirstName(),
							playerSets[i].getLastName());
				else if (strWinRate.length() == 2)
					System.out.printf("%d%%  | %s games | %s %s%n", Math.round(playerSets[i].getWinRate()),
							String.format("%02d", playerSets[i].getNumPlay()), playerSets[i].getFirstName(),
							playerSets[i].getLastName());
				else if (strWinRate.length() == 1)
					System.out.printf("%d%%   | %s games | %s %s%n", Math.round(playerSets[i].getWinRate()),
							String.format("%02d", playerSets[i].getNumPlay()), playerSets[i].getFirstName(),
							playerSets[i].getLastName());
			}
		}
	}

	// Find the index of player with the smallest win rate
	private static int indexOfSmallest(int startIndex, NimPlayer[] playerSets) {
		float min = playerSets[startIndex].getWinRate();
		int indexOfMin = startIndex;
		int index;
		for (index = startIndex + 1; index < count; index++) {
			if (playerSets[index].getWinRate() < min) {
				min = playerSets[index].getWinRate();
				indexOfMin = index;
			}
		}
		return indexOfMin;
	}

	// Find the index of player with the largest win rate
	private static int indexOfLargest(int startIndex, NimPlayer[] playerSets) {
		float max = playerSets[startIndex].getWinRate();
		int indexOfMax = startIndex;
		int index;
		for (index = startIndex + 1; index < count; index++) {
			if (playerSets[index].getWinRate() > max) {
				max = playerSets[index].getWinRate();
				indexOfMax = index;
			}
		}
		return indexOfMax;
	}

	// Swap players
	private static void interchange(int i, int j, NimPlayer[] playerSets) {
		NimPlayer temp;
		temp = playerSets[i];
		playerSets[i] = playerSets[j];
		playerSets[j] = temp; // Original value of playSets[i]
	}

	// Start game
	private void start(NimPlayer[] playerSets, String commands, Scanner keyboard) {
		String[] splitCommands = commands.split(",");
		boolean isExist1 = false;
		boolean isExist2 = false;
		int index1 = 0;
		int index2 = 0;
		for (int i = 0; i < count; i++) { // Check player existence
			if (splitCommands[2].equals(playerSets[i].getUserName())) {
				index1 = i;
				isExist1 = true;
			}
			if (splitCommands[3].equals(playerSets[i].getUserName())) {
				index2 = i;
				isExist2 = true;
			}
		}
		if (isExist1 == false || isExist2 == false)
			System.out.println("One of the players does not exist.");
		else {
			System.out.println("\nInitial stone count: " + splitCommands[0]);
			System.out.println("Maximum stone removal: " + splitCommands[1]);
			System.out
					.println("Player 1: " + playerSets[index1].getFirstName() + " " + playerSets[index1].getLastName());
			System.out
					.println("Player 2: " + playerSets[index2].getFirstName() + " " + playerSets[index2].getLastName());
			NimGame.setInitial(splitCommands[0]);
			NimGame.setBound(splitCommands[1]);
			new NimGame().process(keyboard, playerSets, splitCommands[2], splitCommands[3]);
			// Anonymous object is created since the "process" method is called only once
		}
	}

	public static void main(String[] args) {
		String operation, flag;
		Scanner keyboard = new Scanner(System.in);
		playerSets = new NimPlayer[NUMBER_OF_PLAYERS];// Create an object array to store objects
		Nimsys sys = new Nimsys();
		File file = new File("players.dat");
		try {
			file.createNewFile();
		} catch (IOException e) {
			System.out.println("File already exists");
		}
		if(file.exists() && file.length()!= 0) {  
			readFile(file); // Read players' details from file 
		} 
		System.out.println("Welcome to Nim");
		while (true) { // Operations towards different input commands
			System.out.printf("%n$");
			operation = keyboard.nextLine(); // read input commands
			String[] splitOperation = operation.split(" ");
			// Split the commands with white space
			// and store separated commands in a string array
			InvalidCommandException ce = null;
			try { // Invalid command handling via Exceptions
				if (splitOperation[0].equals("addplayer"))
					try { // Invalid arguments handling via Exceptions
						sys.addPlayer(playerSets, splitOperation[1]);
					} catch (ArrayIndexOutOfBoundsException e) {
						System.out.println("Incorrect number of arguments supplied to command.");
					}
				else if (splitOperation[0].equals("addaiplayer"))
					try { // Invalid arguments handling via Exceptions
						sys.addAIPlayer(playerSets, splitOperation[1]);
					} catch (ArrayIndexOutOfBoundsException e) {
						System.out.println("Incorrect number of arguments supplied to command.");
					}
				else if (splitOperation[0].equals("editplayer"))
					try { // Invalid arguments handling via Exceptions
						sys.editPlayer(playerSets, splitOperation[1]);
					} catch (ArrayIndexOutOfBoundsException e) {
						System.out.println("Incorrect number of arguments supplied to command.");
					}
				else if (splitOperation[0].equals("removeplayer")) {
					if (splitOperation.length == 1) {
						System.out.println("Are you sure you want to remove all players? (y/n)");
						flag = keyboard.nextLine();
						if (flag.equals("y"))
							sys.removeAllPlayers(playerSets);
					} else
						sys.removePlayers(playerSets, splitOperation[1]);
				} else if (splitOperation[0].equals("resetstats")) {
					if (splitOperation.length == 1) {
						System.out.println("Are you sure you want to reset all player statistics? (y/n)");
						flag = keyboard.nextLine();
						if (flag.equals("y"))
							sys.resetAllPlayers(playerSets);
					} else
						try { // Invalid arguments handling via Exceptions
							sys.resetPlayer(playerSets, splitOperation[1]);
						} catch (ArrayIndexOutOfBoundsException e) {
							System.out.println("Incorrect number of arguments supplied to command.");
						}
				} else if (splitOperation[0].equals("displayplayer")) {
					if (splitOperation.length == 1)
						sys.displayAllPlayers(playerSets);
					else
						sys.displayPlayer(playerSets, splitOperation[1]);
				} else if (splitOperation[0].equals("rankings")) {
					if (splitOperation.length == 1 || splitOperation[1].equals("desc"))
						sys.descSort(playerSets);
					else if (splitOperation[1].equals("asc"))
						sys.ascSort(playerSets);
				} else if (splitOperation[0].equals("startgame")) {
					try { // Invalid arguments handling via Exceptions
						sys.start(playerSets, splitOperation[1], keyboard);
					} catch (ArrayIndexOutOfBoundsException e) {
						System.out.println("Incorrect number of arguments supplied to command.");
					}
				} else if (splitOperation[0].equals("exit")) {
					System.out.printf("%n");
					writeFile(file); // Write players' details to file
					keyboard.close();
					System.exit(0);
				} else {
					ce = new InvalidCommandException(splitOperation[0]);
					throw ce;
				}
			} catch (InvalidCommandException e) {
				e.eMsg();
			}
		}
	}
}
