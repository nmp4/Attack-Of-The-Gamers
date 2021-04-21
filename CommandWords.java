
import java.util.*;
/**
 * This class is part of the "Attack of the Gamers" application. 
 * "Attack of the Gamers" is a very simple, text based adventure game.  
 * 
 * This class holds an enumeration of all command words known to the game.
 * It is used to recognise commands as they are typed in.
 *
 * @author  Michael Kölling and David J. Barnes
 * @version 2016.02.29
 * 
 * @author Natasha Muthoni Pregetter //code inside methods
 *  
 */
 //methods to write given by BlueJ, However code inside methods is written by Natasha.
public class CommandWords
{
    // a constant array that holds all valid command words
    private static final String[] validCommands = {
        "sit","look","go", "quit", "help", "back", "take", "drop"
    };

    /**
     * Constructor - initialise the command words.
     */
    public CommandWords()
    {
        // nothing to do at the moment...
    }

    /**
     * Check whether a given String is a valid command word. 
     * @return true if a given string is a valid command,
     * false if it isn't.
     */
    public boolean isCommand(String aString)
    {
        for(int i = 0; i < validCommands.length; i++) {
            if(validCommands[i].equals(aString))
                return true;
        }
        // if we get here, the string was not found in the commands
        return false;
    }
    
    /** 
     * Print all valid command words.
     */
    public String getCommandList(){
        StringBuilder returnString = new StringBuilder();
        for(String command : validCommands){
            returnString.append(" " + command);
        }
        String returnStrings = returnString.toString();
        return returnStrings;
    }
}
