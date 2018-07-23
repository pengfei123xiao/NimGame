
/**  
 * NimHumanPlayer.java
 * This class is a subclass of NimPlayer.java 
 * and overwrite the removeStone method for a human player.
 * @author Pengfei Xiao  pengfei123xiao@gmail.com
 * @date 23 May 2018  
*/
import java.util.Scanner;

public class NimHumanPlayer extends NimPlayer {
	private int numStone;

	public NimHumanPlayer() {}

	public NimHumanPlayer(String userName, String firstName, String lastName) {
		super(userName, firstName, lastName);
	}

	// Mutator: read the input number of stones to be removed
	public void setStone(int num) {
		this.numStone = num;
	}

	// Overwrite the removeStone method
	public int removeStone(int remains, int bound) {
		return numStone;
	}
}
