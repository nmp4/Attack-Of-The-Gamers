import java.util.*;
/**
 * Write a description of class Inventory here.
 *
 * @author (Natasha Muthoni Pregetter)
 * @version (01/09/2020)
 */
public class Inventory
{
    // instance variables - replace the example below with your own
    private List<Item> inventory;

    /**
     * Creates an object of type inventory that has a specified amount of slots..
     */
    public Inventory(){
        inventory = new ArrayList<>(3);
    }

    /**
     * This method returns the items inside of your inventory as a string description
     * @return String of items in inventory
     */
    public String showInventory(){
        String returnString = "";
        for(Item item : inventory){
            returnString += " " + item.getItemFullDescription();
        }
        return returnString;
    }
    
    /**
     * This method will add an item to the inventory and then print the new contents of the inventory
     */
    public void addItemToInventory(Item item){
        if(!(inventory.size() + 1 > inventory.size())){
            inventory.add(item);
            System.out.println(showInventory());
        }
    }
    
    /**
     * Remove an item from the inventory and print the new contents of the inventory
     */
    public void removeItemFromInventory(Item item){
        Iterator<Item> itr = inventory.listIterator();
        while(itr.hasNext()){
            if(itr.next().getItemName().equals(item.getItemName())){
                itr.remove();
                System.out.println("You have removed the item from your inventory.");
                System.out.println(showInventory());
            }
        }
    }
    
    /**
     * Find item in the inventory and return its name, through iteration and returns null if no item with that name is found.
     * @return name of item in inventory if found
     */
    public String findItem(Item item){
        String returnString = "";
        Iterator<Item> itr = inventory.listIterator();
        while(itr.hasNext()){
            if(itr.next().getItemName().equals(item.getItemName())){
                return "Item: " + itr.next().getItemName() + " has been found!";
            }
            else{
                System.out.println("The item could not be found in your inventory");
            }
        }
        return null;
    }
    
}
