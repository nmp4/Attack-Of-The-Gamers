import java.util.*;
/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "Attack of the Gamers" application. 
 * "Attack of the Gamers" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  The exits are labelled north, 
 * east, south, west.  For each direction, the room stores a reference
 * to the neighboring room, or null if there is no exit in that direction.
 * 
 * @author  Natasha Muthoni Pregetter
 * @version 02/09/2020
 */
public class Room 
{
    private String description;
    private HashMap<String, Room> exits;
    private ArrayList<Item> items;

    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description) 
    {
        this.description = description;
        exits = new HashMap<>();
        items = new ArrayList<>();;
    }

    /**
     * Define the exits of this room.  Every direction either leads
     * to another room or is null (no exit there).
     * @param direction is the key and direction.
     * @param neighbor is the room in a given direction.
     */
    public void setExits(String direction, Room neighbor) 
    {
        exits.put(direction, neighbor);
    }

    /**
     * @return The description of the room.
     */
    public String getRoomDescription(){
        return description;
    }

    /**
     * Return a description of this rooms exits eg; exits = north, west
     * @return  A description of the available exits
     */
    public Room getExit(String direction){
        return exits.get(direction);
    }
    
    /**
     * Returns the description of a rooms exits, eg: "Exits: north, west"
     * @return A description of the available exits.
     */
    public String getExitString(){
        String returnString = "Exits: ";
        Set<String> keys = exits.keySet();
        for(String exit : keys){
            returnString += " " + exit;
        }
        return returnString;
    }
    
    /**
    * Return a long description of this room, of the form:
    * You are in the kitchen.
    * Exits: north west
    * Item : key
    * @return A description of the room, including exits and every item in the room.
    */
    public String getLongDescription(){
        for(Item item : items){
            item.getItemFullDescription();
        }
        return "You are " + description + ".\n" + "Item: " + getItemsInRoom() +".\n" + getExitString();
    }
    
    /**
     * Gets the description of all items inside of a room
     */
    public String getItemsInRoom(){
        String returnString = "";
        for(Item item : items){
            returnString += " " + item.getItemFullDescription();
        }
        return returnString;
    }
    
    /**
     * Adds an item to the room
     */
    public void addItem(Item item){
        items.add(item);
    }
    
    /**
     * Check if an item is present in the room and returns the name String
     */
    public Item checkIfItemIsPresent(String item){
        Iterator<Item> itr = items.listIterator();
        while(itr.hasNext()){
            if(itr.next().getItemName().equals(item)){
                return itr.next();
            }
        }
        return null;
    }
    
}
