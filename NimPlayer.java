
/**  
 * NimPlayer.java
 * This class contains details of each player and the removeStone method.
 * @author Pengfei Xiao   
 * @userName xiaop1
 * @studentID 871910
 * @date 23 May, 2018  
 */

import java.util.Scanner;
import java.io.*;

// Public abstract class NimPlayer implements Serializable{
public abstract class NimPlayer implements Serializable {
	private String userName;
	private String firstName;
	private String lastName;
	private int numPlay = 0;
	private int numWin = 0;
	private float winRate = 0;

	// Parameterless constructor for class NimPlayer
	public NimPlayer() {
	}

	// Parameterized constructor for class NimPlayer
	public NimPlayer(String userName, String firstName, String lastName) {
		this.userName = userName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.numPlay = 0;
		this.numWin = 0;
	}

	// Mutator
	public void setUserName(String userName) {
		this.userName = userName;
	}

	// Mutator
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	// Mutator
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	// Mutator
	public void setNumPlay(int num) {
		this.numPlay = num;
	}

	// Mutator
	public void setNumWin(int num) {
		this.numWin = num;
	}

	// Updates number of games played by each player
	public void updateNumPlay() {
		this.numPlay++;
	}

	// Updates number of games won by each player
	public void updateNumWin() {
		this.numWin++;
	}

	// Accessor
	public String getUserName() {
		return userName;
	}

	// Accessor
	public String getFirstName() {
		return firstName;
	}

	// Accessor
	public String getLastName() {
		return lastName;
	}

	// Accessor
	public int getNumPlay() {
		return numPlay;
	}

	// Accessor
	public int getNumWin() {
		return numWin;
	}

	// Accessor
	public float getWinRate() {
		if (this.numPlay == 0)
			this.winRate = 0;
		else
			this.winRate = (float) (this.numWin * 1.0 / this.numPlay) * 100;
		return winRate;
	}

	// Return the number of stones the player wants to remove
	public abstract int removeStone(int remains, int bound);
}
