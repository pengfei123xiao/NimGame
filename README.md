# NimGame
A simple java program realizing the game of Nim.   

--Some specific functions:  
1.Contains a function to add AI player and provides a victory guaranteed strategy for AI player;  
2.Able to handle invalid input via Exceptions;  
3.Files are created to write(read) the game statistics.  

--Operation commands:  
$addplayer username,family_name,given_name //Add new players to the game.  
$removeplayer username //Remove a user.  
$editplayer username,new_family_name,new_given_name //Edit player details. Note that the player's username cannot be changed.  
$resetstats username //Allows player statistics to be reset.  
$displayplayer username //Displays player information.  
$rankings //Rank all users.(descending order in default)  
$rankings asc //Rank all users in ascending order  
$rankings desc //Rank all users in descending order  
$startgame initialstones,upperbound,username1,username2 
