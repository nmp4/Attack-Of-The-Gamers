import java.util.*;
/**
 * Class Player = a player character in the game.
 * 
 * This class is part of the "Attack of the Gamers" application. 
 * "Attack of the Gamers" is a very simple, text based adventure game.  
 * 
 * This creates the player class and object that can then be used as the player interacts with the environment.
 * A player object represents the users character in the game and all the info the user has put into said character.
 * 
 *
 * @author (Natasha Muthoni Pregetter)
 * @version (01/09/2020)
 */
public class Player
{
    // instance variables
    private String name;
    private Inventory playerInventory;
    private Room currentRoom;
    private Room previousRoom;
    
    /**
     * Creates a player object called "name", with an ArrayList called "inventory" that stores held items, and a max
     */
    public Player(String name, Inventory playerInventory, Room currentRoom, Room previousRoom){
        this.name = name;
        this.playerInventory = playerInventory;
        this.currentRoom = currentRoom;
        this.previousRoom = previousRoom;
    }

    /**
     * Adds an item specifically to the players inventory
     */
    public void addToPlayerInventory(Item item){
        playerInventory.addItemToInventory(item);
    }
    
    /**
     * Removes an item specifically from the players inventory
     */
    public void removeFromPlayerInv(Item item){
        playerInventory.removeItemFromInventory(item);
    }
    
    /**
     * Set the players current location i.e which room they are in
     */
    public void setCurrentRoom(Room room){
        currentRoom = room;
    }
    
    /**
     * Set previous room that the player was in
     */
    public void setPreviousRoom(Room room){
        previousRoom = room;
    }
    
    /**
     * Get the current room that the player is in. 
     * @return the room the player is in i.e currentRoom
     */
    public Room getCurrentRoom(){
        return currentRoom;
    }
    
    /**
     * Get the previous room that the player was in.
     * @return previousRoom
     */
    public Room getPreviousRoom(){
        return previousRoom;
    }
}
