import java.util.*;
/**
 * This class is part of the "Attack of the Gamers" application. 
 * "Attack of the Gamers" is a very simple, text based adventure game. 
 * 
 * This class is in charge of creating items that are inside rooms.
 * "Item" represents an object in the game that can be used on a specific enemy or door to unlock it and move forward.
 * Items are added within the room class through an ArrayList<Item> so that a room can hold more than one item.
 * 
 *
 * @author (Natasha Muthoni Pregetter)
 * @version (31/08/2020)
 */
public class Item{ 
    private String name; //name of the object
    private String description; // description of the item aka what it is.
    private boolean canBePickedUp;

    /**
     * Constructor for objects of class Item
     */
    public Item(String name,String description, boolean canBePickedUp){
        this.name = name;
        this.description = description;
        this.canBePickedUp = canBePickedUp;
    }
    
    /**
     * Gets an items description.
     */
    public String getItemFullDescription(){
        return name + " Description: " + description;
    }
    
    /**
     * checks if an item can be picked up
     * @return boolean of whether it can be picked up or not
     */
    public boolean canBePickedUp(){
        if(canBePickedUp = true){
            return true;
        }
        else{
            return false;
        }
    }
    
    /**
     * Get an items name
     * @return Item name as StringcreatePlayer();
     */
    public String getItemName(){
        return name;
    }
}
