
/**  
 * NimAIPlayer.java
 * This class is a subclass of NimPlayer.java 
 * and overwrite the removeStone method for an AI player.
 * @author Pengfei Xiao  pengfei123xiao@gmail.com
 * @date 23 May 2018  
 */
import java.util.Scanner;
import java.util.Random;

public class NimAIPlayer extends NimPlayer implements Testable {

	public NimAIPlayer() {}

	public NimAIPlayer(String userName, String firstName, String lastName) {
		super(userName, firstName, lastName);
	}

	// Overwrite the removeStone method and implement the victory guaranteed strategy
	public int removeStone(int remains, int bound) {
		int numStones = 0;
		int k = ((remains - 1) / (bound + 1)); 
		if ((k * (bound + 1) + 1) == remains) {
			Random random = new Random();
			numStones = random.nextInt(bound) + 1;
			// Create a random number between 1 and the upper bound
		} else {
			numStones = remains - (k * (bound + 1) + 1);
		}
		return numStones;
	}

	public String advancedMove(boolean[] available, String lastMove) {
		// the implementation of the victory
		// guaranteed strategy designed by you
		String move = "";
		
		return move;
	}
}
