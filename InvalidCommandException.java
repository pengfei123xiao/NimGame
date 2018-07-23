/**  
 * InvalidCommandException.java
 * This is a self-defined Exception for an invalid command.
 * @author Pengfei Xiao  pengfei123xiao@gmail.com
 * @date 23 May 2018  
 */

public class InvalidCommandException extends Exception
{
	private String invalidCmd;
	
	public InvalidCommandException(String cmd)
	{
		invalidCmd = cmd;
	}
	
	public void eMsg()
	{
		System.out.println("'" + invalidCmd + "'" + " is not a valid command.");
	}

}
